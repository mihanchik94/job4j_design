package ru.job4j.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateForProgrammer() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ProgrammerReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<html> <head> <title>Name; Hired; Fired; Salary;</title> </head> <body>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body> </html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }


    @Test
    public void whenGenerateForAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccountingReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * AccountingReportEngine.THOUSANDS).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }


    @Test
    public void whenGenerateForHr() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee firstWorker = new Employee("Ivan", now, now, 100);
        Employee secondWorker = new Employee("Mikhail", now, now, 300);
        Employee thirdWorker = new Employee("Nikita", now, now, 250);
        store.add(firstWorker);
        store.add(secondWorker);
        store.add(thirdWorker);
        Report engine = new HrReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(secondWorker.getName()).append(";")
                .append(secondWorker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(thirdWorker.getName()).append(";")
                .append(thirdWorker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(firstWorker.getName()).append(";")
                .append(firstWorker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JsonReport(store);
        final String expect =
                "["
                        + "{\"name\":\""
                        + worker.getName()
                        + "\",\"hired\":"
                        + "{\"year\":"
                        + now.get(Calendar.YEAR)
                        + ",\"month\":"
                        + now.get(Calendar.MONTH)
                        + ",\"dayOfMonth\":"
                        + now.get(Calendar.DAY_OF_MONTH)
                        + ",\"hourOfDay\":"
                        + now.get(Calendar.HOUR_OF_DAY)
                        + ",\"minute\":"
                        + now.get(Calendar.MINUTE)
                        + ",\"second\":"
                        + now.get(Calendar.SECOND)
                        + "},\"fired\":"
                        + "{\"year\":"
                        + now.get(Calendar.YEAR)
                        + ",\"month\":"
                        + now.get(Calendar.MONTH)
                        + ",\"dayOfMonth\":"
                        + now.get(Calendar.DAY_OF_MONTH)
                        + ",\"hourOfDay\":"
                        + now.get(Calendar.HOUR_OF_DAY)
                        + ",\"minute\":"
                        + now.get(Calendar.MINUTE)
                        + ",\"second\":"
                        + now.get(Calendar.SECOND)
                        + "},"
                        + "\"salary\":"
                        + worker.getSalary()
                        + "}"
                        + "]";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenGenerateXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XmlReport(store);
        String expect =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                        + "\n<employees>\n"
                        + "    <employee>\n"
                        + "        <name>" + worker.getName() + "</name>\n"
                        + "        <hired>" + dateFormat.format(now.getTime()) + "</hired>\n"
                        + "        <fired>" + dateFormat.format(now.getTime()) + "</fired>\n"
                        + "        <salary>" + worker.getSalary() + "</salary>\n"
                        + "    </employee>\n"
                        + "</employees>\n";
        assertThat(engine.generate(em -> true), is(expect));
    }

}