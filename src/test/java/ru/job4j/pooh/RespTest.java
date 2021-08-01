package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RespTest {

    @Test
    public void whenLittleTest() {
        Resp resp = new Resp("Some text", "200 OK");
        assertThat(resp.status(), is("200 OK"));
    }
}
