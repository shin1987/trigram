package trigram;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class DictionaryTest
{
    private static Dictionary dict;

    @BeforeAll
    static void init()
    {
        dict = new Dictionary();
        dict.addEntry("a", "b", "c");
        dict.addEntry("a", "b", "d");
        dict.addEntry("key1", "key2", "word");
    }

    @Test
    void testSize()
    {
        assertThat(dict.getNumOfEntries(), is(2));
    }

    @Test
    void testGetKeys()
    {
        // This test can be improved:
        // The order of keys may not be the same as they were added
        // to dict. More sophisticated assertions could be used.
        String [] key1 = dict.getKey(0);
        assertThat(key1[0], is("a"));
        assertThat(key1[1], is("b"));
        String [] key2 = dict.getKey(1);
        assertThat(key2[0], is("key1"));
        assertThat(key2[1], is("key2"));
    }

    @Test
    void testLookup()
    {
        // This test may be improved:
        // The order of word stored in this dict may not match the order they
        // they were added to dict. Be careful with assertions...
        var word1 = dict.lookup("a", "b");
        assertThat(word1.length, is(2));
        assertThat(word1[0], is("c"));
        assertThat(word1[1], is("d"));
        var word2 = dict.lookup("key1", "key2");
        assertThat(word2.length, is(1));
        assertThat(word2[0], is("word"));
    }
}

