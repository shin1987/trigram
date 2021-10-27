package trigram;

import trigram.wordfilter.WordFilter;
import trigram.core.TrigramDictionary;

/**
 * Scan words into a TrigramDictionary with words being filtered.
 *
 * The filtered scanner goes through a sequence of words with filter applied. 
 * Any Trigram patterns accepted after filtering will be added to a dictionary.
 */
public class FilteredScanner implements trigram.core.TrigramScanner
{
    private WordFilter filter;

    /**
     * Constructor.
     * 
     * @param filter word filter - determines valid words to add.
     */
    public FilteredScanner(final WordFilter filter)
    {
        this.filter = filter;
    }
    
    @Override
    public void scan(final String [] words, final TrigramDictionary dict)
    {
        // This is a "lazy" implementation:
        // The use of infinite loop with Exception to handle the end of loop
        // may not be very efficient - or not the most readable code - but
        // it saves many if-else statements which may lead to buggy conditional
        // checks.
        
        try {
            var ind1 = nextIndex(0, words, this.filter);
            var ind2 = nextIndex(ind1 + 1, words, this.filter);
            var ind3 = nextIndex(ind2 + 1, words, this.filter);
            while (true) {
                dict.addEntry(words[ind1], words[ind2], words[ind3]);
                ind1 = ind2;
                ind2 = ind3;
                ind3 = nextIndex(ind3 + 1, words, this.filter);
            }
        }
        catch (ItemNotFoundException err) {
            // End of program
            return;
        }                                                  
    }

    // Loop through the word list and return the next valid word in the sequence
    // start - starting point for search
    // words - word list.
    // filter - the filter employed by an actual scanner instance.
    private static int nextIndex(final int start,
                                 final String [] words,
                                 final WordFilter filter)
            throws ItemNotFoundException
    {
        for (int i = start; i < words.length; ++i)
            if (filter.accept(words[i]))
                return i; // Break the loop and return the current word index
        throw new ItemNotFoundException();
    }
}

// Auxiliary exception used internally when no available words found
class ItemNotFoundException extends Exception
{
}
