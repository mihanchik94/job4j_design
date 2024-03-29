package ru.job4j.tree;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;



public class SimpleTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

    @Test
    public void whenChildNotExistOnLeafThenAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertTrue(tree.add(2, 6));
    }

    @Test
    public void whenParentNotExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(7, 8));
    }

    @Test
    public void whenHaveMoreThen2LeafThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.isBinary());

    }

    @Test
    public void whenHaveLessOr2LeafThenTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(3, null);
        assertTrue(tree.isBinary());

    }

}