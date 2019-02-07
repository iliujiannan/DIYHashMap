package com.ljn.hashmap;

/**
 * Created by 12390 on 2019/2/7.
 */
public class DIYHashMap<K, V> implements DIYMap<K, V> {
    private int defaultLength = 16;
    private double loadedFactor = 0.75;
    private int usedSize;
    private Entry<K, V>[] table;

    public DIYHashMap(int defaultLength, double loadedFactor) {
        if (defaultLength > 0) {
            this.defaultLength = defaultLength;
        } else {
            throw new IllegalArgumentException("defaultLength's value is illegal.");
        }
        if (loadedFactor > 0 && loadedFactor < 1) {
            this.loadedFactor = loadedFactor;
        } else {
            throw new IllegalArgumentException("loadedFactor's value is illegal.");
        }

        table = new Entry[defaultLength];
    }

    public DIYHashMap() {
        table = new Entry[defaultLength];
    }


    @Override
    public V put(K k, V v) {

        //判断容量，如果大于长度*负载因子就扩容
        if (usedSize > defaultLength * loadedFactor) {
            up2Size();
        }


        int index = getIndex(k, table.length);
        Entry<K, V> entry = table[index];
        Entry<K, V> newEntry = new Entry<>(k, v, null);

        if (entry == null) {
            table[index] = newEntry;
            usedSize++;
        }else{
            Entry<K, V> tmp = entry;
            while(tmp.next!=null){
                tmp = tmp.next;
            }
            tmp.next = newEntry;

        }


        return newEntry.getValue();
    }


    @Override
    public V get(K k) {
        int index = getIndex(k, table.length);
        Entry<K, V> entry = table[index];

        if(entry==null){
            throw new NullPointerException("non");
        }else{
            while(entry!=null&&(!entry.getKey().equals(k))){
                entry = entry.next;
            }
            return entry.getValue();
        }
    }

    private int getIndex(K k, int len) {
        int m=len-1;
        int index=hash(k.hashCode()) & m;
        return index >= 0 ? index : -index;
    }

    private int hash(int hashCode){
        hashCode=hashCode^((hashCode>>>20)^(hashCode>>>12));
        return hashCode^((hashCode>>>7)^(hashCode>>>4));

    }
    private void up2Size() {

    }

    class Entry<K, V> implements DIYMap.Entry<K, V> {

        private K key;
        private V value;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.key = k;
            this.value = v;
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


    }
}
