package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int expectedModCount = 0;
    private int modCount = 0;
    private int size = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (size >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            return false;
        } else {
            table[index] = new MapEntry<>(key, value);
            size++;
            modCount++;
        }
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >> 16;
    }

    private int indexFor(int hash) {
        return Math.abs(hash) % capacity;
    }


    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : oldTable) {
            if (mapEntry != null) {
                int bufferIndex = indexFor(hash(mapEntry.key.hashCode()));
                table[bufferIndex] = mapEntry;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null || !table[index].key.equals(key)) {
            return null;
        }
        return table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null || !table[index].key.equals(key)) {
            return false;
        } else {
            table[index] = null;
            size--;
            modCount++;
        }
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int indexPosition = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (size == 0) {
                    return false;
                }
                while (indexPosition < capacity && table[indexPosition] == null) {
                    indexPosition++;
                }
                return indexPosition < capacity && table[indexPosition] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[indexPosition++].key;
            }
        };

    }


    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity
                && expectedModCount == simpleMap.expectedModCount
                && modCount == simpleMap.modCount
                && size == simpleMap.size
                && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, expectedModCount, modCount, size);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}












