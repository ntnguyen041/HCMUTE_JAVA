package C5_D1_Assignment.src.model;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" + "key=" + key + ", value=" + value + '}';
    }

    // Generic static method swap
    public static <K, V> Pair<V, K> swap(Pair<K, V> pair) {
        return new Pair<>(pair.getValue(), pair.getKey());
    }

    // Generic static method comparePairs (V extends Comparable<V>)
    // Trả về true nếu p1.value >= p2.value, ngược lại false
    public static <K, V extends Comparable<V>> boolean comparePairs(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getValue().compareTo(p2.getValue()) >= 0;
    }
}
