package trigram.wordfilter;

/**
 * Filters words that can be used during text generation.
 */
public interface WordFilter
{
    /**
     * Detect if a word can be accepted.
     *
     * @param word word to be filtered for use.
     * @return true if word should be used in the program, otherwise false.
     */
    public boolean accept(String word);
}
