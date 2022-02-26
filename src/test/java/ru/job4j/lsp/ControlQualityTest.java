package ru.job4j.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    @Test
    public void whenAddToShopWithSale() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        LocalDate now = LocalDate.now();
        control.add(new Food("Milk", now.minusDays(15), now.plusDays(5), 60.0, 0.8));
        assertThat(shop.getAll().size(), is(1));
        assertThat(warehouse.getAll().size(), is(0));
        assertThat(trash.getAll().size(), is(0));
        assertThat(shop.getAll().get(0).getPrice(), is(48.0));
    }

    @Test
    public void whenAddToShopWithoutSale() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        LocalDate now = LocalDate.now();
        control.add(new Food("Milk", now.minusDays(10), now.plusDays(10), 60.0, 0.8));
        assertThat(shop.getAll().size(), is(1));
        assertThat(warehouse.getAll().size(), is(0));
        assertThat(trash.getAll().size(), is(0));
        assertThat(shop.getAll().get(0).getPrice(), is(60.0));
    }

    @Test
    public void whenAddToWareHouse() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        LocalDate now = LocalDate.now();
        control.add(new Food("Milk", now.minusDays(2), now.plusDays(30), 60.0, 0.8));
        assertThat(shop.getAll().size(), is(0));
        assertThat(warehouse.getAll().size(), is(1));
        assertThat(trash.getAll().size(), is(0));
    }

    @Test
    public void whenAddToTrash() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality(Arrays.asList(warehouse, shop, trash));
        LocalDate now = LocalDate.now();
        control.add(new Food("Milk", now.minusDays(10), now.minusDays(1), 60.0, 0.8));
        assertThat(shop.getAll().size(), is(0));
        assertThat(warehouse.getAll().size(), is(0));
        assertThat(trash.getAll().size(), is(1));
    }

}