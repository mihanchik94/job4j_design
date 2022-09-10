package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    public void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .containsAnyOf("Masha", "Katya", "five")
                .containsExactly("first", "second", "three", "four", "five")
                .doesNotContain("Petya");
        assertThat(list).first().isNotNull().isEqualTo("first");
    }

    @Test
    public void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "first");
        assertThat(set).hasSize(4)
                .doesNotContain("five")
                .contains("three")
                .containsExactlyInAnyOrder("first", "second", "three", "four");
    }

    @Test
    public void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "first");
        assertThat(map).hasSize(4)
                .containsKeys("first", "three")
                .containsValues(0, 1, 2, 3)
                .doesNotContainKey("five")
                .doesNotContainValue(4)
                .containsEntry("four", 3);
    }
}
