package com.kaishengit.util.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/27.
 */
public class SimpleCache {

    private static Map<String ,Object> cache=new HashMap<>();
    public static Object getCache(String key){
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        return null;
    }
    public static void setCache(String key,Object value){
        cache.put(key, value);
    }
    public static void removeCache(String key){
        cache.remove(key);
    }

}
