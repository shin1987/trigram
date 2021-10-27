package trigram.wordfilter;

/**
 * Accept some very basic punctuation marks.
 */
public class SimplePunctuationFilter implements WordFilter
{
    @Override
    public boolean accept(final String word)
    {
        return word == "," || word == "." || word == "?" || word == "!" ||
                word == ";";
    }
}
