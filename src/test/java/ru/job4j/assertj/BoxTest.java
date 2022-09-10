package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    public void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    public void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .doesNotContain("Sphere")
                .isEqualTo("Cube");
    }

    @Test
    public void isThisUnknown() {
        Box box = new Box(4, -10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .startsWith("Un")
                .endsWith("ct")
                .isEqualTo("Unknown object");
    }

    @Test
    public void whenZeroVertex() {
        Box box = new Box(0, 12);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isNotPositive()
                .isLessThan(5)
                .isEven()
                .isGreaterThan(-1)
                .isEqualTo(0);
    }

    @Test
    public void whenNegativeVertex() {
        Box box = new Box(21, 3);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isNotPositive()
                .isNotZero()
                .isOdd()
                .isLessThan(0)
                .isGreaterThan(-5)
                .isEqualTo(-1);
    }

    @Test
    public void whenExist() {
        Box box = new Box(0, 12);
        boolean result = box.isExist();
        assertThat(result)
                .isNotNull()
                .isTrue();
    }

    @Test
    public void whenNotExist() {
        Box box = new Box(21, 12);
        boolean result = box.isExist();
        assertThat(result)
                .isNotNull()
                .isFalse();
    }

    @Test
    public void whenSphereArea() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area)
                .isCloseTo(50.27d, Percentage.withPercentage(1.0d))
                .isGreaterThan(50.26d)
                .isLessThan(50.27d)
                .isEqualTo(50.26d, withPrecision(0.006d));
    }

    @Test
    public void whenNoArea() {
        Box box = new Box(-1, 2);
        double area = box.getArea();
        assertThat(area)
                .isGreaterThan(-1.0d)
                .isLessThan(1.0d)
                .isCloseTo(0.0d, Percentage.withPercentage(0d))
                .isZero();
    }
}
