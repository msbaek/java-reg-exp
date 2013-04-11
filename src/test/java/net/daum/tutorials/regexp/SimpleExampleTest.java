package net.daum.tutorials.regexp;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern p = Pattern.compile(emailRegex);
        for (String c : candidates.thatMatch()) {
            Matcher m = p.matcher(c);
            assertThat(c.matches(emailRegex), is(true));
        }

        for (String c : candidates.notMatching())
            assertThat(p.matcher(c).matches(), is(false));
    }

    @Test
    public void case_insensitive_searches_with_pattern_flags() {
        String regex = "A really long sentence";
        String candidate = "A really long SENTENCE";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(candidate);
        assertThat(m.matches(), is(true));
    }

    @Test
    public void search_with_find() {
        String odysseyPartOne =
                "Athene, you are my favorite" +
                        "Athene, you are my favorite" +
                        "Athene, you are my favorite" +
                        "Athene, you are my favorite" +
                        "Athene, you are my favorite";
        String regexToMatch = "Athene";
        Pattern p = Pattern.compile(regexToMatch);
        Matcher m = p.matcher(odysseyPartOne);
        int count = 0;
        while(m.find()) {
            count++;
            assertThat(m.group(), is(regexToMatch));
        }
        assertThat(count, is(5));
    }

    @Test
    public void groups() {
        String regex = "(\\w)(\\d)";
        String candidates = "J2 comes before J5, which both come before H7";
        String [] matchers = {"J2", "J5", "H7"};
        Matcher m = Pattern.compile(regex).matcher(candidates);
        int i = 0;
        while(m.find()) {
            assertThat(m.group(), is(matchers[i]));
            assertThat(m.group(1), is(matchers[i].substring(0, 1)));
            assertThat(m.group(2), is(matchers[i].substring(1, 2)));
            i++;
        }
    }
}
