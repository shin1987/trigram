package trigram.wordfilter;

/**
 * Filter only words composed of letters.
 */
public class PlainWordFilter implements WordFilter
{
    /**
     * Filter words.
     *
     * @param word word to be checked.
     * @return true if word contains only letters.
     */
    public boolean accept(final String word)
    {
        return word.matches("\\w+");
    }
}
