package net.daum.tutorials.regexp;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleExampleTest {
    private Candidates candidates;

    @Before
    public void setUp() {
        candidates = new Candidates();
    }

    @Test
    public void simple_regex() {
        // [A-Za-z]+_[A-Za-z]+@[A-Za-z]+\.org
        String emailRegex = "[A-Za-z]+_[A-Za-z]+@[A-Za-z]+\\.org";

        candidates
                .addMatchers("homer_simpson@burns.org")
                .addMatchers("m_burns@burns.org");

        candidates
                .addNonMatchers("wsmithers@burns.com")
                .addNonMatchers("Homer9_simpson@somewhere.org");

        for(String c : candidates.thatMatch())
            assertThat(c.matches(emailRegex), is(true));
        for(String c : candidates.notMatching())
            assertThat(c.matches(emailRegex), is(false));
    }
}
