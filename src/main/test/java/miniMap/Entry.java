package miniMap;

/**
 * Created by json on 2018/5/29.
 * Describe:
 */
public class Entry<K,V> implements DIYMap.Entry<K,V>{
    Entry<K,V> next = null;

    public V key;
    public V value;
    public int hash;


    public Entry(K k,V v,Entry<K,V> next){
        this.key = (V) k;
        this.value = v;
        this.next = next ;
    }

    public Entry(K key, V value, int hash, Entry<K, V> e) {
    }

    @Override
    public K getKey() {
        // TODO Auto-generated method stub
        return getKey();
    }

    @Override
    public V getValue() {
        // TODO Auto-generated method stub
        return getValue();
    }

    public void setKey(V key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}