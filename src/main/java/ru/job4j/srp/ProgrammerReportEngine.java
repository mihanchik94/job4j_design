package ru.job4j.srp;

import java.util.function.Predicate;

public class ProgrammerReportEngine implements Report {
    private Store store;

    public ProgrammerReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html> <head> <title>Name; Hired; Fired; Salary;</title> </head> <body>");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body> </html>");
        return text.toString();
    }
}
