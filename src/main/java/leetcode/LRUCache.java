package leetcode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

/**
 *
 *
 *
 */
public class LRUCache {
    int maxCapacity = 0;

    java.util.LinkedHashMap<Integer,Integer> cache = new java.util.LinkedHashMap<Integer,Integer> () {
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maxCapacity;
        }
    };

    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
    }

    public int get(int key) {
        Integer  x = cache.get(key);
        if(x != null) {
            cache.remove(key);
            cache.put(key, x);
            return x;
        }
        else return -1;
    }

    public void set(int key, int value) {
        Integer x = cache.get(key);
        if(x != null) cache.remove(key);
        cache.put(key, value);
    }

}


