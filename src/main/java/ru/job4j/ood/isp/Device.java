package ru.job4j.ood.isp;

public interface Device {
    void input(String data);
    void calculate();
    void output();
}

class Computer implements Device {
    private String buffer;

    @Override
    public void input(String data) {
        this.buffer = data;
    }

    @Override
    public void calculate() {
        this.buffer = "Calc by computer: " + buffer;
    }

    @Override
    public void output() {
        System.out.println(buffer);
    }
}

class Server implements Device {

    @Override
    public void input(String data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void calculate() {
        System.out.println("Do some work!");
    }

    @Override
    public void output() {
        throw new UnsupportedOperationException();
    }
}

class TV implements Device {

    private String command;

    @Override
    public void input(String data) {
        this.command = command;
    }

    @Override
    public void calculate() {
        System.out.println("Execute: " + command);
    }

    @Override
    public void output() {
        System.out.println("Show TV program");
    }
}

interface Input {
    void in(String data);
}

interface Output {
    void output();
}

interface Calculator {
    void calculate();
}

interface Internet {
    void connect();
}

class SecondServer implements Calculator, Internet {

    @Override
    public void calculate() {
        System.out.println("Do work!");
    }

    @Override
    public void connect() {
        System.out.println("Try connect ...");
    }
}
