package com.ljn.hashmap;

/**
 * Created by 12390 on 2019/2/7.
 */
public class MapTest {
    public static void main(String[] args){
        DIYMap<Integer, String> map = new DIYHashMap<>();
        map.put(1, "ljn");
        map.put(2, "admin");
        System.out.println(map.get(1));
    }
}
