package com.example.timeapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TimeApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Pattern TIME_PATTERN =
            Pattern.compile("\\d{2}/\\d{2}/\\d{4}T\\d{2}:\\d{2} \\([A-Z]{2,4}\)");

    @Test
    void testGetTimeDefault() throws Exception {
        mockMvc.perform(get("/time"))
                .andExpect(status().isOk())
                .andExpect(content().string(result -> assertTrue(TIME_PATTERN.matcher(result).matches(),
                        "Response does not match pattern: " + result)));
    }

    @Test
    void testGetTimeWithTimezone() throws Exception {
        mockMvc.perform(get("/time").param("timezone", "America/New_York"))
                .andExpect(status().isOk())
                .andExpect(content().string(result -> assertTrue(TIME_PATTERN.matcher(result).matches(),
                        "Response does not match pattern: " + result)));
    }
}
