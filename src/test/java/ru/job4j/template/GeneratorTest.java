package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {
    @Ignore
    @Test
    public void whenSuccess() {
        PhraseGenerator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Valentin",
                "subject", "you");
        String expected = "I am a Valentin, Who are you? ";
        assertThat(generator.produce(template, args), is(expected));

    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenNotAllKeys() {
        PhraseGenerator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Valentin");
        generator.produce(template, args);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenExtraKeys() {
        PhraseGenerator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Valentin",
                "subject", "you",
                "profession", "butcher");
        generator.produce(template, args);

    }

}