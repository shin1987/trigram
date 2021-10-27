package trigram;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

// This test employes dummy filter and dummy dictionary implementation
// which is defined at the end of this file. The purpose of using dummy classes
// is to reduce test dependency on any actual dictionary or filter.

// The dummy filter accept any word with length over 1
// The dummy dictionary saves all adding results as raw data so that
// results can be easily verified.

public class FilteredScannerTest
{
    private FilteredScanner scanner;
    private DummyDictionary dict;
    
    @BeforeEach
    void initTest()
    {
        this.scanner = new FilteredScanner(new DummyFilter());
        this.dict = new DummyDictionary();
    }

    // Test the scan function terminate with desired length in dict
    // and the words are added in the correct order
    @Test
    void testScanTerminate()
    {
        var test = "123 234 456 678 789 901 012".split(" ");
        scanner.scan(test, dict);
        assertThat(dict.getNumOfEntries(), is(5));
        for (int i = 0; i < 5; ++i) {
            assertThat(dict.key1.get(i), is(test[i]));
            assertThat(dict.key2.get(i), is(test[i + 1]));
            assertThat(dict.word.get(i), is(test[i + 2]));
        }
    }

    @Test
    void testApplyFilter()
    {
        // Actual input to trigger filter
        var mixed = "123 234 a 456 678 789 b 901 012".split(" ");
        // Expected result sequence after filering
        var test = "123 234 456 678 789 901 012".split(" ");
        
        scanner.scan(mixed, dict);
        assertThat(dict.getNumOfEntries(), is(5));
        for (int i = 0; i < 5; ++i) {
            assertThat(dict.key1.get(i), is(test[i]));
            assertThat(dict.key2.get(i), is(test[i + 1]));
            assertThat(dict.word.get(i), is(test[i + 2]));
        }        
    }

    // The scanning must terminate correctly when the input is short
    @Test
    void testShortSequence()
    {
        var test = "123 234".split(" ");
        scanner.scan(test, dict);
        assertThat(dict.getNumOfEntries(), is(0));
    }

    // Input sequence may be long, but after filtering not enough entries.
    @Test
    void testShortSequenceWithFilter()
    {
        var test = "123 a 242 b e d".split(" ");
        scanner.scan(test, dict);
        assertThat(dict.getNumOfEntries(), is(0));
    }

}

// Dummy filter to reject short words
class DummyFilter implements trigram.wordfilter.WordFilter
{
    @Override
    public boolean accept(final String word)
    {
        return word.length() > 1;
    }
}

// Dummy dictionary used to record the sequence words are added
class DummyDictionary implements trigram.core.TrigramDictionary
{
    // All records are saved for public access so that unit tests can easily
    // verify the results
    public final ArrayList<String> key1 = new ArrayList<String>();
    public final ArrayList<String> key2 = new ArrayList<String>();
    public final ArrayList<String> word = new ArrayList<String>();
    
    @Override
    public void addEntry(String key1, String key2, String word)
    {
        this.key1.add(key1);
        this.key2.add(key2);
        this.word.add(word);
    }

    @Override
    public String [] lookup(String key1, String key2)
    {
        return null;
    }

    @Override
    public String [] getKey(int i)
    {
        return null;
    }

    @Override
    public int getNumOfEntries()
    {
        return this.key1.size();
    }
}


