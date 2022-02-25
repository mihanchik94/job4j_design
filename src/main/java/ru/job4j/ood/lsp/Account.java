package ru.job4j.ood.lsp;

/**
 * Мы можем проследить следующее нарушение принципа LSP:
 * классе BankAccount отсутствует валидация, что нарушает принцип того, что все условия в базовом классе должны быть
 * сохранены в подклассе.
 */


class Account {
    private String name;
    private String password;

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void validate(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password length should be 8 or more symbols");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setPassword(String password) {
        validate(password);
        this.password = password;
    }
}

class BankAccount extends Account {
    private String name;
    private String password;

    public BankAccount(String name, String password) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}

