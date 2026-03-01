package com.example.timeapi.controller;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TimeControllerTest {

    private final TimeController controller = new TimeController();

    private static final Pattern TIME_PATTERN =
            Pattern.compile("\\d{2}/\\d{2}/\\d{4}T\\d{2}:\\d{2} \\([A-Z]{2,4}\\)");

    @Test
    void testDefaultTimeZone() {
        String result = controller.getTime(null);
        assertNotNull(result);
        assertTrue(TIME_PATTERN.matcher(result).matches(), "Result does not match pattern: " + result);
    }

    @Test
    void testSpecifiedTimeZone() {
        String result = controller.getTime("America/New_York");
        assertNotNull(result);
        assertTrue(TIME_PATTERN.matcher(result).matches(), "Result does not match pattern: " + result);
    }

    @Test
    void testInvalidTimeZoneFallsBack() {
        String result = controller.getTime("invalid_zone");
        assertNotNull(result);
        assertTrue(TIME_PATTERN.matcher(result).matches(), "Result does not match pattern: " + result);
    }
}
