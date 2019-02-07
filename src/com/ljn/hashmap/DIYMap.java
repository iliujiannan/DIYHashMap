package com.ljn.hashmap;

/**
 * Created by 12390 on 2019/2/7.
 */
public interface DIYMap<K, V> {
    V put(K k, V v);
    V get(K k);

    interface Entry<K, V>{
        K getKey();
        V getValue();
    }
}
