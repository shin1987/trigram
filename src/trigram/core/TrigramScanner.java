package trigram.core;

/**
 * Generating TrigramDictionary from input strings.
 */
public interface TrigramScanner
{
    /**
     * Scan a sequence of words and append to a dictionary.
     * 
     * @param words array of words in sequence matching some original text.
     * @param dict target dictionary to which the scanned words are appended.
     */
    public void scan(String [] words, TrigramDictionary dict);
}
