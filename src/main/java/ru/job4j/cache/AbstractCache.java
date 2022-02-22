package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) throws IOException {
        V value;
        if (cache.containsKey(key)) {
            SoftReference<V> softReference = cache.get(key);
            value = softReference.get();
        } else {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    protected abstract V load(K key) throws IOException;

}
