package trigram;

import trigram.core.TrigramDictionary;
import java.util.Random;

/**
 * Algorithm to randomly sample from TrigramDictionary to create text.
 */
public class RandomTextGenerator implements trigram.core.TextGenerator
{
    private Random rand;

    /**
     * Default constructor, set seed to 0
     */
    public RandomTextGenerator()
    {
        this(0);
    }

    /** 
     * Initialise internal random number generator with given seed.
     *
     * @param seed to seed the random generator
     */
    public RandomTextGenerator(long seed)
    {
        this(new Random(seed));
    }

    /** 
     * Directly initialise internal random number generator.
     * @param rand use an external random number generator.
     */
    public RandomTextGenerator(final Random rand)
    {
        this.rand = rand;
    }

    @Override
    public String generate(final TrigramDictionary dict, final long length)
    {
        String ans = "";
        // Begin by sampling keys
        String [] key = dict.getKey(rand.nextInt(dict.getNumOfEntries()));
        ans += key[0] + " " + key[1];
        String [] word = dict.lookup(key[0], key[1]);

        long count = 2; // 2 words has been added
        
        while (word != null && count < length) // terminate when hit a dead end.
        {
            // Sample a random word from the collection
            String selected = word[rand.nextInt(word.length)];
            ans += " " + selected;

            count++; // increment count

            // Sample next word
            key[0] = key[1];
            key[1] = selected;
            word = dict.lookup(key[0], key[1]);
        }
        return ans;
    }
}
