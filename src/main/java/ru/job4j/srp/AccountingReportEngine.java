package ru.job4j.srp;

import java.util.function.Predicate;

public class AccountingReportEngine implements Report {
    private Store store;
    private static final double THOUSANDS = 1000;

    public AccountingReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double salaryWithThousand = employee.getSalary() * 1000;
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salaryWithThousand).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
