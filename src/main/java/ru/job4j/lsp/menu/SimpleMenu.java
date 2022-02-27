package ru.job4j.lsp.menu;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        if (parentName == Menu.ROOT) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        } else {
            Optional<ItemInfo> parentInfo = findItem(parentName);
            Optional<ItemInfo> childInfo = findItem(childName);
            if (parentInfo.isPresent() && childInfo.isEmpty()) {
                MenuItem childItem = new SimpleMenuItem(childName, actionDelegate);
                parentInfo.get().menuItem.getChildren().add(childItem);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> menuItemInfo = Optional.empty();
        Optional<ItemInfo> itemInfo = findItem(itemName);
        if (itemInfo.isPresent()) {
            menuItemInfo = Optional.of(
                    new MenuItemInfo(
                            itemInfo.get().menuItem, itemInfo.get().number));
        }
        return menuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<MenuItemInfo> menuItemInfoIterator = new Iterator<MenuItemInfo>() {
            DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
        return menuItemInfoIterator;
    }

    private void iterate(Consumer<ItemInfo> consumer) {
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo itemInfo = dfsIterator.next();
            consumer.accept(itemInfo);
        }
    }

    private Optional<ItemInfo> findItem(String name) {
        return findByPredicate(itemInfo -> itemInfo.menuItem.getName().equals(name));
    }

    private Optional<ItemInfo> findByPredicate(Predicate<ItemInfo> condition) {
        Optional<ItemInfo>[] rsl = new Optional[]{Optional.empty()};
        iterate(i -> {
            if (condition.test(i)) {
                rsl[0] = Optional.of(i);
            }
        });
        return rsl[0];
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
