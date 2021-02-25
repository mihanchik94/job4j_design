package ru.job4j.list;

import java.util.*;

/**
 * Расмотрим структуру данных – список, который описывается интерфейсом List. В такой структуре в определенной последовательности хранятся элементы одного типа.
 * Очень важная особенность данной структуры – мы всегда можем пронумеровать элементы списка, т.е. проводя аналогию с массивом – у каждого элемента есть свой индекс.
 * При этом у нас могут быть одинаковые элементы.
 * Самое важное для работы с коллекциями является набор CRUD – операций которые мы можем с ними сделать (CRUD – сокращение от слов create, read, update and delete).
 * Т.е. нас в первую очередь интересует набор методов, которые объявлены в интерфейсе List<E> и как они работают.
 * Для рассмотрения будем использовать средства обобщенного программирования, т.е. везде используем generics, вместо E в объявлении интерфейса
 *может быть определен любой тип ссылочных данных.
 */

public class ListUsage {
    public static void main(String[] args) {
/**
 *  1. Добавление элементов. Для добавления новых элементов предназначены 4 метода:
 * 1.1. boolean add(E e) – добавляет элемент e в конец списка.
 * 1.2. void add(int index, E element) – добавляет указанный элемент (element) в указанную позицию(index) в списке.
 * При этом сдвигает элемент, который находится в этой позиции(если есть), и все последующие элементы вправо.
 * 1.3. boolean addAll(Collection<? extends E> c) – добавляет все элементы из переданной коллекции в конец списка в том порядке, в котором они возвращаются
 * итератором переданной коллекции.
 * 1.4. boolean addAll(int index, Collection<? extends E> c) – добавляет все элементы из коллекции c в список в указанную позицию(index).
 * При этом сдвигает элемент, который находится в этой позиции, и все последующие элементы вправо. Добавляемые элементы будут расставлены в том порядке,
 * в котором они возвращены итератором переданной коллекции.
 *
 * Важно отметить, что добавление по индексу может бросить исключение класса IndexOutOfBoundsException, если будет выполнено условие index < 0 || index > size().
 *
 * Также для создания и одновременного заполнения списка можно использовать фабричный метод of():
 * List<E> of(E ... elements) - метод возвращает список, в которые помещены список элементов elements типа E.
 * Как мы видим метод принимает переменное количество аргументов (обозначается ...). Т.е. мы можем перечислять большое количество аргументов через запятую.
 * Данный метод возвращает неизменяемый список, т.е. вызвать метод add(), remove() и т.п. на такой коллекции не получится, будет сгенерировано исключение UnsupportedOperationException.
 */
        //1. Добавление элементов.
        System.out.println("Методы для добавления элементов: ");
        List<String> rsl = new ArrayList<>();
        rsl.add("one"); // boolean add(E e) – добавляет элемент e в конец списка.
        rsl.add("two"); // boolean add(E e) – добавляет элемент e в конец списка.
        rsl.add("three"); // boolean add(E e) – добавляет элемент e в конец списка.
        //при выводе содержимого на консоль, получим one\n two\n three\n
        System.out.println("Использование метода boolean add(E e)");
        new ListPrinter(rsl);
        rsl.add(1, "four"); // void add(int index, E element) - добавляет элемент (element) в позицию(index) в списке, сдвигая последующие элементы вправо
        //при выводе содержимого на консоль, получим one\n four\n two\n three\n
        System.out.println("Использование метода void add(int index, E element)");
        new ListPrinter(rsl);
        List<String> list = new ArrayList<>();
        list.add("four");
        list.add("five");
        rsl.addAll(list); // boolean addAll(Collection<? extends E> c) – добавляет все элементы из переданной коллекции в конец списка в исходном порядке
        //при выводе содержимого на консоль, получим one\n four\n two\n three\n four\n five/n
        System.out.println("Использование метода boolean addAll(Collection<? extends E> c)");
        new ListPrinter(rsl);
        List<String> secondList = new ArrayList<>();
        secondList.add("six");
        secondList.add("seven");
        rsl.addAll(2, secondList); //boolean addAll(int index, Collection<? extends E> c) – добавляет все элементы из коллекции c в список в указанную позицию(index).
        //при выводе содержимого на консоль, получим one\n four\n six/n seven/n two\n three\n four\n five/n
        System.out.println("Использование метода boolean addAll(int index, Collection<? extends E> c)");
        new ListPrinter(rsl);
        List<String> res = List.of("one", "two", "three"); //List<E> of(E ... elements) - метод возвращает список, в которые помещены список элементов elements типа E.
        //при выводе содержимого на консоль, получим one\n two\n three\n
        System.out.println("Использование фабричного метода of(E ... elements)");
        new ListPrinter(rsl);
  /**
  *2. Чтение элементов из списка
   * Прочитать элементы из списка можно двумя способами:
   * 2.1. Использование метода get(): E get(int index) – метод возвращает элемент, который находится в позиции index в этом списке.
   * Метод может кинуть исключение класса IndexOutOfBoundsException, если будет выполнено условие index < 0 || index > size().
   * 2.2. использовать итератор для доступа к элементам. Для того чтобы получить экземпляр итератора в интерфейсе List<E> определены 3 метода:
   * - Iterator<E> iterator() – метод возвращает объект Iterator, который содержит в себе все элементы исходной коллекции.
   * - ListIterator<E> listIterator() – возвращает итератор списка для элементов в этом списке.
   * - ListIterator<E> listIterator(int index) – возвращает итератор списка для элементов в этом списке, начиная с элемента индекс которого равен index.
   *
   * ListIterator имеет несколько отличий от Iterator:
   * 1. ListIterator может использоваться только со списками, т.е. реализациями интерфейса List<E>, тогда как Iterator<E>
   * применим к любым наследникам и реализациям интерфейса Collection<E>.
   * 2. ListIterator позволяет перебирать список в обоих направлениях, Iterator<E> только в прямом порядке.
   * 3. ListIterator допускает модификацию списка, за счет его дополнительных методов add и remove. Iterator<E> такой возможности не имеет.
  */
        System.out.println("Методы для чтения элементов: ");
        System.out.println("Использование метода E get(int index)");
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i)); // E get(int index) – метод возвращает элемент, который находится в позиции index в этом списке.
        }
        System.out.println("Использование метода Iterator<E> iterator()");
        Iterator<String> iterator = res.iterator(); // Iterator<E> iterator() – метод возвращает объект Iterator, который содержит в себе все элементы исходной коллекции
        while (iterator.hasNext()) {
            System.out.println("Текущий элемент: " + iterator.next());
        }
        System.out.println("Использование метода ListIterator<E> listIterator()");
        ListIterator<String> listIterator = res.listIterator(); // ListIterator<E> listIterator() – возвращает итератор списка для элементов в этом списке.
        while (listIterator.hasNext()) {
            System.out.println("Текущий элемент: " + listIterator.next());
        }
        System.out.println("Использование метода ListIterator<E> listIterator(int index)");
        ListIterator<String> listItSecond = res.listIterator(1); // ListIterator<E> listIterator(int index) - возвращает итератор списка для элементов в этом списке, начиная с элемента индекс которого равен index
        while (listItSecond.hasNext()) {
            System.out.println("Текущий элемент: " + listItSecond.next());
        }
        /**
         *3. Изменение элементов в списке
         *3.1 E set(int index, E element) – заменяет элемент позиция которого равна index на элемент который мы передаем в метод (element).
         * При этом метод возвращает старое значение элемента с индексом index.
         *3.2 default void replaceAll(UnaryOperator<E> operator) – заменяет каждый элемент в списке результатом применения оператора (operator) к каждому элементу.
         */
        System.out.println("Методы для Изменение элементов в списке: ");
        System.out.println("Использование метода E set(int index, E element)");
        rsl.set(1, "two"); // E set(int index, E element) – заменяет элемент позиция которого равна index на элемент который мы передаем в метод (element)
        new ListPrinter(rsl);
        System.out.println("Использование метода default void replaceAll(UnaryOperator<E> operator)");
        rsl.replaceAll(String::toUpperCase); // default void replaceAll(UnaryOperator<E> operator) – заменяет каждый элемент в списке результатом применения оператора (operator) к каждому элементу.
        new ListPrinter(rsl);
        /**
         * 4. Удаление элементов из списка
         * Для выполнения данной операции в интерфейсе List<E> определены 5 методов:
         * 4.1. E remove(int index) – удаляет элемент из списка по индексу index.
         * 4.2. boolean remove(E e) – удаляет элемент е типа E из коллекции при его ПЕРВОМ вхождении в список, если он есть в коллекции.
         * ВАЖНО! Метод remove(E e) реализован с помощью цикла for(), подразумевает под собой первоначальный поиск удаляемого элемента и только потом он удаляется.
         * Соответственно, использование этого метода внутри цикла, который перебирает список, не рекомендуется, поскольку мы будем проходить по списку дважды.
         * 4.3. boolean removeAll(Collection<?> col) – метод удаляет из списка все элементы, которые содержатся в коллекции col,
         * если в результате работы метода исходный список изменился - метод возвращает true.
         * 4.4. boolean retainAll(Collection<?> col) – метод также удаляет элементы из списка, за исключением тех, которые находятся в коллекции col,
         * если в результате работы метода исходный список изменился - метод возвращает true.
         * 4.5. default boolean removeIf(Predicate<? super E> filter) – метод удаляет все элементы из коллекции,
         * которые удовлетворяют условию определенному в предикате filter(передается в виде лямбда выражения).
         * Если в результате работы метода список изменился - метод возвращает true.
         */
        System.out.println("Методы для удаления элементов из списка: ");
        System.out.println("Использование метода E remove(int index)");
        rsl.remove(2); // E remove(int index) – удаляет элемент из списка по индексу index.
        new ListPrinter(rsl);
        System.out.println("Использование метода boolean remove(E e)");
        rsl.remove("TWO"); // boolean remove(E e) – удаляет элемент е типа E из коллекции при его ПЕРВОМ вхождении в список, если он есть в коллекции.
        new ListPrinter(rsl);
        System.out.println("Использование метода boolean removeAll(Collection<?> col)");
        List<String> removeEl = new ArrayList<>();
        removeEl.add("SEVEN");
        removeEl.add("FIVE");
        rsl.removeAll(removeEl);
        new ListPrinter(rsl);
        System.out.println("Использование метода boolean retainAll(Collection<?> col)");
        List<String> retainEl = new ArrayList<>();
        retainEl.add("ONE");
        retainEl.add("TWO");
        retainEl.add("THREE");
        rsl.retainAll(retainEl); // boolean retainAll(Collection<?> col) – метод также удаляет элементы из списка, за исключением тех, которые находятся в коллекции col
        new ListPrinter(rsl);
        System.out.println("Использование метода boolean default boolean removeIf(Predicate<? super E> filter)");
        rsl.removeIf(s -> s.length() > 3); // default boolean removeIf(Predicate<? super E> filter) – удаляет все элементы из коллекции которые удовлетворяют условию filter(передается в виде лямбда выражения)
        new ListPrinter(rsl);
        /**
         * Также обратим свое внимание на ряд методов, которые определены в интерфейсе List<E> и находят широкое применение:
         * 1. boolean contains*(E element) – метод возвращает true, если список содержит переданный в метод элемент element.
         * 2. int indexOf*(E element) – метод возвращает индекс элемента element при его первом вхождении в список. Если элемент не найден - метод возвращает -1.
         * 3. int lastIndexOf*(E element) - метод возвращает индекс элемента element при его последнем вхождении в список. Если элемент не найден - метод возвращает -1.
         * 4. int size() - метод возвращает целочисленное значение, и говорит нам о том, сколько элементов находится в списке.
         * 5. List<E> subList(int fromIndex, int toIndex) - метод возвращает список, который содержит все элементы исходного списка начиная с индекса fromIndex(включительно)
         * и до toIndex(значение исключается). При этом, если выполняется условие fromIndex == toIndex,- метод вернет пустой список.
         * 6. default void sort(Comparator<? super E> comp) – метод осуществляет сортировку списка в соответствии с компаратора comp, который мы передаем в метод.
         */
        System.out.println("Использование метода boolean contains*(E element)");
        boolean b = rsl.contains("ONE"); // boolean contains*(E element) – метод возвращает true, если список содержит переданный в метод элемент element
        System.out.println("Список содержит элемент: " + b);
        //
        System.out.println("Использование метода int indexOf*(E element)");
        int i = rsl.indexOf("TWO"); // метод возвращает индекс элемента element при его первом вхождении в список. Если элемент не найден - метод возвращает -1.
        System.out.println("Индекс элемента в списке: " + i);
        //
        System.out.println("Использование метода int lastIndexOf*(E element)");
        rsl.add("ONE");
        rsl.add("three");
        int ind = rsl.lastIndexOf("ONE"); // возвращает индекс элемента element при его последнем вхождении в список. Если элемент не найден - метод возвращает -1.
        System.out.println("Индекс элемента в списке: " + ind);
        //
        System.out.println("Использование метода int size()");
        int size = rsl.size(); // возвращает целочисленное значение, и говорит нам о том, сколько элементов находится в списке.
        System.out.println("Размер списка равен: " + size);
        //
        System.out.println("Использование метода List<E> subList(int fromIndex, int toIndex)");
        List<String> sublist = rsl.subList(1, 2); // метод возвращает список, со всеми элементами исходного с индекса fromIndex(включительно)и до toIndex(значение исключается).
        new ListPrinter(sublist);
        //
        System.out.println("Использование метода default void sort(Comparator<? super E> comp)");
        rsl.sort(Comparator.reverseOrder());
        new ListPrinter(rsl);

    }
}


