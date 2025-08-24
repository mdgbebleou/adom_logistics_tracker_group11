package datastructures;

public class MyHashMap {
    private static class Entry {
        String key;
        Object value;
        Entry next;

        Entry(String key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry[] buckets;
    private int size;

    public MyHashMap() {
        buckets = new Entry[16];
        size = 0;
    }

    public void put(String key, Object value) {
        int index = hash(key);
        Entry entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    public Object get(String key) {
        int index = hash(key);
        Entry entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public String[] keys() {
        String[] keys = new String[size];
        int index = 0;
        for (Entry bucket : buckets) {
            Entry entry = bucket;
            while (entry != null) {
                keys[index++] = entry.key;
                entry = entry.next;
            }
        }
        return keys;
    }

    public Object[] values() {
        Object[] values = new Object[size];
        int index = 0;
        for (Entry bucket : buckets) {
            Entry entry = bucket;
            while (entry != null) {
                values[index++] = entry.value;
                entry = entry.next;
            }
        }
        return values;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}