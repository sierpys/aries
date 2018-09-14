package com.alibaba.message.serializer.impl;

import com.alibaba.message.serializer.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * @author sier.pys 9/14/18
 */
public class JsonSerializer implements Serializer<String> {
    static final GsonBuilder builder = new GsonBuilder();


    @Override
    public String serialize(Object input) {
        Gson gson = builder.create();
        return gson.toJson(input);
    }

    public static void main(String[] args) {
        Gson gson = builder.create();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("compay", "alibaba");
        String toJson = gson.toJson(stringObjectHashMap);
        System.out.println(toJson);
    }
}
