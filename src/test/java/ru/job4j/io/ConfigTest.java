package ru.job4j.io;


import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test
    public void whenWithComment() {
        String path = "./data/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test
    public void whenWithEmptyLines() {
        String path = "./data/with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithoutValue() {
        String path = "./data/without_value.properties";
        Config config = new Config(path);
        config.load();
    }


    @Test (expected = IllegalArgumentException.class)
    public void whenWithoutEquals() {
        String path = "./data/without_equals.properties";
        Config config = new Config(path);
        config.load();
    }
}
