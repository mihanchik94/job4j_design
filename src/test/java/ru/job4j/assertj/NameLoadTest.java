package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    public void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    public void whenNoEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"First: Bobby"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    public void whenStartFromEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=Bobby"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain a key");
    }

    @Test
    public void whenFinishFromEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"Bobby="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .hasMessageMatching("^.+")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain a value");
    }
}
