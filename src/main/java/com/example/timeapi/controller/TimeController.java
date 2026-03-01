package com.example.timeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeController {

    private static final ZoneId DEFAULT_ZONE = ZoneId.of("Europe/Paris");
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm (z)");

    @GetMapping("/time")
    public String getTime(@RequestParam(required = false) String timezone) {
        ZoneId zone = DEFAULT_ZONE;
        if (timezone != null && !timezone.isBlank()) {
            try {
                zone = ZoneId.of(timezone);
            } catch (Exception e) {
                // fallback to default if invalid
                zone = DEFAULT_ZONE;
            }
        }
        ZonedDateTime now = ZonedDateTime.now(zone);
        return now.format(FORMATTER);
    }
}
