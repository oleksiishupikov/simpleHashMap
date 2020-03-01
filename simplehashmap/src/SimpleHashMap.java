public class SimpleHashMap {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int size, capacity;
    private Integer[] keys;
    private Long[] values;
    private final double load_factor;


    public SimpleHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    //region [Additional function]
    private SimpleHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    private SimpleHashMap(int capacity, double load_factor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0 and integer.");
        }

        if (Double.compare(load_factor, 0) <= 0 || Double.compare(load_factor, 1) > 0) {
            throw new IllegalArgumentException("Load factor must greater than 0 and less than or equal to 1.");
        }

        this.capacity = capacity;
        this.load_factor = load_factor;
        keys = new Integer[capacity];
        values = new Long[capacity];
    }

    private int hash(Integer key) {
        return (key.hashCode() & Integer.MAX_VALUE) % capacity;
    }

    private void resize(int new_capacity) {
        SimpleHashMap resized_map = new SimpleHashMap(new_capacity);
        for (int i = 0; i < capacity; ++i) {
            if (keys[i] != null) {
                resized_map.put(keys[i], values[i]);
            }
        }

        this.keys = resized_map.keys;
        this.values = resized_map.values;
        this.capacity = resized_map.capacity;
    }
    //endregion

    //region [Visible functions]
    public void put(Integer key, Long value) {
        if (key == null) {
            throw new NullPointerException("Null is not allowed to be used as a key.");
        }
        // Double the capacity when the load factor exceeds the desired one
        if (size >= load_factor * capacity) {
            resize(2 * capacity);
        }

        int i = hash(key); // "hashed-to" slot
        // Loop through the array of keys until unoccupied slot is found
        for (; keys[i] != null; i = (i + 1) % capacity) {
            // If the same key is found, reset the value and return the previous one
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        // Unoccupied slot is found. Put the new entry in
        keys[i] = key;
        values[i] = value;
        size++;
        // No previous value
    }

    public Long get(Integer key) {
        if (key == null) {
            throw new NullPointerException("Null is not allowed to be used as a key.");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % size) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public int size() {
        return size;
    }
    //endregion
}