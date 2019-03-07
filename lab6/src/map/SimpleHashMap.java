package map;

public class SimpleHashMap<K, V> implements Map<K, V> {

    private Entry<K,V>[] table;
    private int size = 0;
    private int capacity = 100;

    private double loadFactor = 0.75;
    private double growthFactor = 2;

    private final static int defaultCapacity = 100;

    /** Constructs an empty hashmap with the default initial capacity (16)
     and the default load factor (0.75). */
    public SimpleHashMap() {
        this(defaultCapacity);
    }
    /** Constructs an empty hashmap with the specified initial capacity
     and the default load factor (0.75). */
    public SimpleHashMap(int capacity) {
        this.capacity = capacity;
        this.table = (Entry<K,V>[]) new Entry[capacity];
    }


    @Override
    public V get(Object obj) {
        try {
            return getGeneric((K) obj); //<-- Varför varning?
        } catch (ClassCastException e) {
            // Hantera andra fall?
            return null;
        }
    }

    private V getGeneric(K key) {
        Entry<K, V> entry = find(index(key), key);
        return entry == null ? null : entry.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        rehashIfOverloaded();

        int index = index(key);
        Entry<K, V> e = table[index];

        if (e == null) {
            // New element
            table[index] = new Entry<>(key, value);
            size++;
            return null;
        } else if (e.key.equals(key)) {
            // Duplicate in the beginning of the list
            // Kan göras utan detta fall om man inte gör nya entries varje gång :)
            table[index] = new Entry<>(key, value, e.next);
            return e.value;
        }

        while (e.next != null) {
            if (e.next.key.equals(key)) {
                V oldValue = e.next.value;
                // Duplicate in the middle of the list
                e.next = new Entry<>(key, value, e.next);
                return oldValue;
            }
            e = e.next;
        }

        // No duplicate, add to the end of the list
        e.next = new Entry<>(key, value);
        size++;
        return null;
    }

    private void rehashIfOverloaded() {
        // Might rehash too soon if a duplicate key is entered since then size won't actually change
        // Will probably mean little in practice :)
        double nextSize = size + 1;
        if (nextSize / table.length > loadFactor) {
            rehash();
        }
    }
    private void rehash() {
        Entry<K,V>[] prevTable = table;

        this.capacity *= growthFactor;
        this.table = (Entry<K,V>[]) new Entry[capacity];
        this.size = 0;

        for (Entry<K, V> entry : prevTable) {
            iterateEntries(entry, (e) -> put(e.key, e.value));
        }
    }

    @Override
    public V remove(Object obj) {
        try {
            return removeGeneric((K) obj); //<-- Varför varning?
        } catch (ClassCastException e) {
            // Hantera andra fall?
            return null;
        }
    }

    private V removeGeneric(K key) {
        int index = index(key);
        Entry<K, V> e = table[index];
        if (e == null) {
            return null;
        } else if (e.key.equals(key)) {
            table[index] = e.next;
            size--;
            return e.value;
        }

        while (e.next != null) {
            if (e.next.key.equals(key)) {
                V value = e.next.value;
                // Duplicate in the middle of the list
                e.next = new Entry<>(key, value, e.next);
                size--;
                return value;
            }
            e = e.next;
        }
        return null;

    }

    @Override
    public int size() {
        return size;
    }

    public String show() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            sb.append(i);
            sb.append("    ");
            iterateEntries(table[i], (e) -> {
                sb.append(e.toString());
                sb.append(" ");
            });
            sb.append("\n");
        }
        return sb.toString();
    }

    private void iterateEntries(Entry<K, V> e, EntryConsumer<K, V> consumer) {
        if (e == null) {
            return;
        }
        do {
            consumer.consume(e);
            e = e.next;
        } while (e != null);
    }

    private int index(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    private Entry<K,V> find(int index, K key) {
        Entry<K, V> e = table[index];
        if (e == null) {
            return null;
        }
        do {
            if (e.key.equals(key)) {
                return e;
            } else {
                e = e.next;
            }
        } while (e != null);

        return null;
    }

    private static class Entry<K,V> implements Map.Entry<K,V> {
        private K key;
        private V value;
        private Entry<K,V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private Entry(K key, V value, Entry<K,V> next) {
            this(key, value);
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        @Override
        public String toString() {
            return key.toString() + "=" + value.toString();
        }
    }

    private interface EntryConsumer<K,V> {
        void consume(Entry<K,V> e);
    }
}
