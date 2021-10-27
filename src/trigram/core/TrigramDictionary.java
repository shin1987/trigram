package trigram.core;

/**
 * Look-up table for Trigram entries. 
 *
 * Internal storage and look-up mechanism is implemented by sub-classes.
 * A TrigramDictionary instance behaves like a look up table which
 * finds the next word(s) indexed by two preceeding string words as key.
 */
public interface TrigramDictionary
{    
    /**
     * Add an entry to the dictionary.
     *
     * Expect the sequence in the original sentence to be [key1, key2, word]
     * @param key1 first index, word appears first in the sequence
     * @param key2 second index, word appears second in the sequence
     * @param word the indexed word, it appears third in the sequence
     */
    public void addEntry(String key1, String key2, String word);

    /**
     * Find all words known to follow the index keys.
     *
     * @param key1 first word in the sequence
     * @param key2 second word in the sequence
     * @return array of all possible words following key1 and key2. If no
     *         words available, return null.
     */
    public String [] lookup(String key1, String key2);

    /**
     * Get the number of entries in the current dictionary.
     * @return number of entries
     */
    public int getNumOfEntries();

    /**
     * Get keys.
     * 
     * This method is designed to fetch a pair of words to initialise the 
     * generation of text. 
     * 
     * @param index index to the entry in this dictionary.
     * @return a pair of words (array length of 2)
     */
    public String [] getKey(int index);
}

