package LiteSnacks.backend;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LastFiveTest {
    @Test
    public void getLastFiveTest() {
        System.out.println(LastFive.getLastFive("anon"));
        System.out.println(LastFive.getLastFive("stupid"));
        assertTrue(true);
    }
}
