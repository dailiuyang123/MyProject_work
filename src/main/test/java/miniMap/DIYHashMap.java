package miniMap;

/**
 * Created by json on 2018/5/29.
 * Describe:
 */
public class DIYHashMap<K,V> {

    /** 默认的table数组大小 */
    static final int DEFAULTCAPACITY = 2;
    int capacity;
    /** 默认的负载因子 */
    final float loadFactor;
    /** 键值对桶数组 */
    Entry[] table;
    /** 放入的键值对数目 */
    int size;

    /**
     * 默认构造方法
     */
    public DIYHashMap() {
        capacity = DEFAULTCAPACITY;
        this.table = new Entry[capacity];
        this.loadFactor = 0.75f;
        this.size = 0;
    }


    /**
     * 指定table大小和负载因子的构造方法
     */
    public DIYHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    /**
     * 获取哈希值
     */
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * 根据对象的哈希值和table数组长度，计算出对象在table数组中的下标
     *
     * @param h
     *            对象的哈希值
     * @param length
     *            table数组长度
     * @return 计算出的table数组中的下标
     */
    static int indexFor(int h, int length) {
        // 源代码中一直保持length为2的倍数，这样可使算出的index相同的几率较小
        return h & (length - 1);
    }

    /**
     * 获取map中key值对应的value
     */
    public V get(K key) {
        int hash = hash(key.hashCode());
        // 根据key的hash值计算出应放入的table数组下标
        int index = indexFor(hash, table.length);
        // 找到table数组中对应的下标的桶，迭代桶中的键值对元素
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            Object ek = e.key;
            // 如果该元素的key值和要放入元素的key值相同，返回value值
            if (e.hash == hash && (key == ek || key.equals(ek))) {
                return e.value;
            }
        }
        // 没有找到，返回Null
        return null;
    }

    /**
     * 向map中放值
     */
    public V put(K key, V value) {
        int hash = hash(key.hashCode());
        // 根据key的hash值计算出应放入的table数组下标
        int index = indexFor(hash, table.length);
        // 找到table数组中对应的下标的桶，迭代桶中的键值对元素
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            Object ek = e.key;
            // 如果该元素的key值和要放入元素的key值相同，覆盖该元素的value值
            if (e.hash == hash && (key == ek || key.equals(ek))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        // 如果没有找到相同key值的元素，新放入这个元素进map
        addEntry(hash, key, value, index);
        return null;
    }



    /**
     * 将键值对放入table中，若元素个数>=capacity * loadFactor，进行table数组的扩容
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        // 获取对应下标的桶的头节点
        Entry<K, V> e = table[bucketIndex];
        // 设置头节点为新的元素，并设定新元素的next为旧头节点
        table[bucketIndex] = new Entry<K, V>(key, value, hash, e);
        table[bucketIndex].setHash(hash);
        table[bucketIndex].setKey(key);
        table[bucketIndex].setValue(value);
        // 如果map中的元素>容量*负载因子，进行数组空间扩容
        if (size++ >= (capacity * loadFactor))
            reSize(2 * table.length);
    }

    /**
     * table数组的扩容
     */
    void reSize(int newCapacity) {
        Entry[] oldTable = table;
        // 创建新容量的table数组
        Entry[] newTable = new Entry[newCapacity];
        // 把旧talbe数组中的元素重新放入新table数组
        transfer(newTable);
        // 设置map的table为新数组
        table = newTable;
        // 更新map的容量
        capacity = newCapacity;
    }

    /**
     * 把旧table数组中的元素放入扩容后的table数组中
     */
    void transfer(Entry[] newTable) {
        Entry[] oldTable = table;
        int newCapacity = newTable.length;
        // 迭代旧table中的桶
        for (int j = 0; j < oldTable.length; j++) {
            // 旧桶的第一个节点
            Entry<K, V> e = oldTable[j];
            if (e != null) {
                oldTable[j] = null;
                // 迭代旧桶，把旧桶中的元素放入新数组
                do {
                    // 保留下旧节点的下一个节点
                    Entry<K, V> next = e.next;
                    // 获取扩容后该节点的数组下标
                    int i = indexFor(e.hash, newCapacity);
                    // 设置该节点的下个节点指向新桶的头节点
                    e.next = newTable[i];
                    // 设置新桶的第一个节点为该节点
                    newTable[i] = e;
                    // e为旧桶的下一个节点
                    e = next;
                } while (e != null);
            }
        }
    }
}