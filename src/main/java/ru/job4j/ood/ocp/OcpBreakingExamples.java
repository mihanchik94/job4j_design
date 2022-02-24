package ru.job4j.ood.ocp;

import java.util.ArrayList;

/**
 * Программа выводит способы передвижения животных.
 * В коде ниже представлено несколько нарушений принципа OCP.
 *
 * 1. В классе FirstAndSecondBreak первым нарушением принципа OCP является возвращение ArrayList вместо List.
 * В связи с этим программа теряет способность работать с другими реализациями интерфеса List.
 *
 * 2. В классе FirstAndSecondBreak вторым нарушением принципа OCP можно назвать использование Integer,
 * вместо Generic. Это лишает нас возможности выводить списки с другими типами данных.
 *
 * 3. В классах ThirdBreak и ExactlyBreak нарушением принципа OCP является наследование ссостояние объекта.
 */
public class OcpBreakingExamples {
    private static class FirstAndSecondBreak {
        public ArrayList<Integer> getArray() {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            return arrayList;
        }
    }

    private static class ThirdBreak {
        private final int count;

        public ThirdBreak(int count) {
            this.count = count;
        }

        public void someAction(int count) {
            System.out.println(count + count);
        }
    }

    public static class ExactlyBreak extends ThirdBreak {
        public ExactlyBreak(int count) {
            super(count);
        }

        @Override
        public void someAction(int count) {
            System.out.println(count * count);
        }
    }


}
