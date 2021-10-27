package trigram.wordfilter;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class CompoundWordFilterTest
{
    @Test
    void testFilter()
    {
        var filter = new CompoundWordFilter();
        filter.add(new DummyFilter(3)).add(new DummyFilter(5));
        assertThat("length 3", filter.accept("abc"), is(true));
        assertThat("length 5", filter.accept("abcde"), is(true));
        assertThat("length 4", filter.accept("abcd"), is(false));
    }
}

class DummyFilter implements WordFilter
{
    private int target;

    DummyFilter(int length)
    {
        this.target = length;
    }

    @Override
    public boolean accept(String word)
    {
        return word.length() == this.target;
    }
}
