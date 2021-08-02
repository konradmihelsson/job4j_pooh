package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReqTest {

    @Test
    public void whenTest() {
        String ls = System.lineSeparator();
        String text = "POST /queue/weather HTTP/1.1" + ls +
                "Host: localhost:9000" + ls +
                "User-Agent: curl/7.72.0" + ls +
                "Accept: */*" + ls +
                "Content-Length: 7" + ls +
                "Content-Type: application/x-www-form-urlencoded" + ls +
                "" + ls +
                "text=13";
        Req req = Req.of(text);
        assertThat(req.text(), is("text=13"));
    }
}
