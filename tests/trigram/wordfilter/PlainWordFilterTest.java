package trigram.wordfilter;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class PlainWordFilterTest
{
    private PlainWordFilter filter = new PlainWordFilter();
    
    @Test
    void testLowerCaseWord()
    {
        assertThat(filter.accept("cat"), is(true));
    }

    @Test
    void testUpperCaseWord()
    {
        assertThat(filter.accept("CD"), is(true));
    }

    @Test
    void testMixedCaseWord()
    {
        assertThat(filter.accept("Computer"), is(true));
    }

    @Test
    void testNumbers()
    {
        assertThat(filter.accept("H3llo"), is(false));
                
    }

    @Test
    void testPunctuation()
    {
        assertThat(filter.accept("Mr."), is(false)); 
    }

}

