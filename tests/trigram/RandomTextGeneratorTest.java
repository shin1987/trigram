package trigram;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

// The random text genertor creates text randomly, it is difficult to test.
// In order to fix the random sampling order, java.util.Random object is
// overloaded to make result more predictable - it may not be very appropriate
// to override a standard JDK class in this fashion...
public class RandomTextGeneratorTest
{
    @Test
    void testGeneration()
    {
        Dictionary dict = new Dictionary();
        dict.addEntry("I", "wish", "I");
        dict.addEntry("I", "wish", "I");
        dict.addEntry("wish", "I", "may");
        dict.addEntry("wish", "I", "might");
        dict.addEntry("may", "I", "wish");
        dict.addEntry("I", "may", "I");
        // Make sure the dictionary class is valid
        assertThat("Invalid test, dictionary not correctly created",
                   dict.getNumOfEntries(), is(4));
        int [] sequence = {0, 1, 0, 0, 0, 1, 1};
        var textgen = new RandomTextGenerator(new DummyRandom(sequence));
        assertThat(textgen.generate(dict), is("I wish I may I wish I might"));
    }
}

class DummyRandom extends java.util.Random
{
    private int index = 0;
    private int [] sequence;

    DummyRandom(final int [] sequence)
    {
        this.sequence = sequence;
    }
    
    @Override
    public int nextInt(int bound)
    {
        if (index >= sequence.length)
            throw new RuntimeException("Run out of random numbers");
        if (sequence[index] >= bound)
            throw new RuntimeException("Test sequence not prepared right");
        return sequence[index++];
    }
}

