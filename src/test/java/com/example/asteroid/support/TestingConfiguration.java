package com.example.asteroid.support;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootConfiguration
@ImportAutoConfiguration(JacksonAutoConfiguration.class)
public class TestingConfiguration {

}
