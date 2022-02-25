package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * В классах ниже мы можем наблюдать 2 нарушения принципа LSP:
 * 1. Метод shopPriceValue(Products products) класса Shop усилен в подклассе.
 * 2. Метод buyerPriceCount(Products products) класса Shop ослаблен в подклассе.
 */

class Product {
    private String name;
    private float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}


class Products implements Iterable<Product> {
private List<Product> products = new ArrayList<>();

    public Products(List<Product> products) {
        this.products = products;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}

class Shop {
    private Products products;

    public Shop(Products products) {
        this.products = products;
    }

    public int shopPriceValue(Products products) {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        } if (price < 200_000) {
            System.out.println("Need to buy more goods!");
        }
        return price;
    }

    public int buyerPriceCount(Products products) {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }
        if (price > 500) {
            price = price - 50;
        }
        return price;
    }
}

class SuperMarket extends Shop {
    public SuperMarket(Products products) {
        super(products);
    }

    @Override
    public int shopPriceValue(Products products) {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        } if (price < 500_000) {
            System.out.println("Need to buy more goods!");
        }
        return price;
    }

    @Override
    public int buyerPriceCount(Products products) {
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }
        return price;
    }
}
