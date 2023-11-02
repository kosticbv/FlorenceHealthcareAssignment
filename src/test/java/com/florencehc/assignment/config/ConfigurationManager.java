package com.florencehc.assignment.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ConfigurationManager {
    private static Logger log = LoggerFactory.getLogger(ConfigurationManager.class);

    // --- Global Configuration Properties --------------------------------
    public static Duration ULTRA_LONG_WAIT_TIMEOUT = Duration.ofMinutes(2);
    public static Duration LONG_WAIT_TIMEOUT = Duration.ofSeconds(50);
    public static Duration DEFAULT_WAIT_TIMEOUT = Duration.ofSeconds(20);
    public static Duration MEDIUM_WAIT_TIMEOUT = Duration.ofSeconds(5);
    public static Duration SHORT_WAIT_TIMEOUT = Duration.ofMillis(4000);
    public static Duration ULTRA_SHORT_WAIT_TIMEOUT = Duration.ofMillis(500);
    public static Duration DEFAULT_POLLING_FREQUENCY = Duration.ofMillis(500);
    public static Duration MEDIUM_POLLING_FREQUENCY = Duration.ofMillis(250);
    public static Duration SHORT_POLLING_FREQUENCY = Duration.ofMillis(5);
}