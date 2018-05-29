package miniMap;

/**
 * Created by json on 2018/5/29.
 * Describe:
 */
interface DIYMap<K, V>{

    public V put(K k,V v) ;
    public V get(K k) ;


    public interface Entry<K,V>{
        Entry<?, ?> next = null;
        public K getKey() ;
        public V getValue() ;

    }
}
