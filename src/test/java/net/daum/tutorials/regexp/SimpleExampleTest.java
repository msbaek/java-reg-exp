package net.daum.tutorials.regexp;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleExampleTest {
    @Test
    public void simple_regex() {
        // [A-Za-z]+_[A-Za-z]+@[A-Za-z]+\.org
        String emailRegex = "[A-Za-z]+_[A-Za-z]+@[A-Za-z]+\\.org";
        String [] matchers = new String [] {
                "homer_simpson@burns.org",
                "m_burns@burns.org"
        };

        String [] nonMatchers = new String [] {
                "wsmithers@burns.com",
                "Homer9_simpson@somewhere.org"
        };

        for(String c : matchers)
            assertThat(c.matches(emailRegex), is(true));
        for(String c : nonMatchers)
            assertThat(c.matches(emailRegex), is(false));
    }
}
