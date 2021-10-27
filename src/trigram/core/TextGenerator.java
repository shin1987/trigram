package trigram.core;

/**
 * Heuristic text generator based on Trigram look-up.
 * 
 * A general interface, many different algorithms can be implemented to achieve
 * the text generation.
 */
public interface TextGenerator
{
    /**
     * Create text based on some dictionary.
     * @param dict lookup table stores trigram sequences.
     * @param length maximal number of words to generate.
     * @return a single string represents the final text.
     */
    String generate(TrigramDictionary dict, long length);
}
