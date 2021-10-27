package trigram.wordfilter;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class SimplePunctuationFilterTest
{
    private static SimplePunctuationFilter filter =
            new SimplePunctuationFilter();
    
    @Test
    void acceptPunctuation()
    {
        String [] valid = {".", ",", "!", "?", ";"};
        for (var mark : valid)
            assertThat("mark: " + mark, filter.accept(mark), is(true));

    }

    @Test
    void rejectSymbols()
    {
        String [] invalid = {"#", "@", "^", "*", "\"", "'"};
        for (var mark : invalid)
            assertThat("mark: " + mark, filter.accept(mark), is(false));
    }
    
    @Test
    void rejectWords()
    {
        assertThat(filter.accept("hello"), is(false));
    }
}

