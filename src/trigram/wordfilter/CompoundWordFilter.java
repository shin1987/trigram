package trigram.wordfilter;
import java.util.ArrayList;

/**
 * Compound multiple word filters into a single WordFilter. 
 *
 * A word will be accepted when any of the sub-filter accepts it.
 */
public class CompoundWordFilter implements WordFilter
{
    private ArrayList<WordFilter> filter = new ArrayList<WordFilter>();
    
    /**
     * Add a filter
     * @param filter filter to be added.
     * @return this instance to enable chain-style syntax
     */
    public CompoundWordFilter add(final WordFilter filter)
    {
        this.filter.add(filter);
        return this;
    }

    @Override
    public boolean accept(final String word)
    {
        for (var ins : this.filter)
            if (ins.accept(word))
                return true;
        return false;
    }
}
