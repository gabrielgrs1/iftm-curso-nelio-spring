package com.gabrielgrs.aulaspring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParam(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String url) {
        String[] urlSplitted = url.split(",");
        List<Integer> integerList = new ArrayList<>();

        for (String id :
                urlSplitted) {
            integerList.add(Integer.parseInt(id));
        }

        return integerList;
    }
}
