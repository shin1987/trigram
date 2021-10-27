package trigram.wordfilter;

/**
 * Accept some very basic punctuation marks.
 */
public class SimplePunctuationFilter implements WordFilter
{
    @Override
    public boolean accept(final String word)
    {
        boolean test = word.matches(",|.|;|\\?|\\!");
        return test;
    }
}
