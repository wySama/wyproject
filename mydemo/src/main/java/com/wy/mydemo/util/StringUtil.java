package com.wy.mydemo.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

    /**
     * 将properties中字符串转成map
     *
     * @param str 目标字符串 例如："999290053110222:25;999290053110223:24"
     * @param st  分割条件  例如：";"
     * @return Map
     * wy
     */
    public static Map<String, String> StringChangeToMap(String str, String st) {
        Map<String, String> map = new HashMap<String, String>();
        String[] strs = str.split( "\\" + st + "" );
        for (String s : strs) {
            String[] ms = s.split( ":" );
            map.put( ms[0], ms[1] );
        }
        return map;
    }

    public static void main(String[] args) {

        String str = "999290053110222:25;999290053110223:24";
        String st = ";";
        Map<String, String> m = new HashMap<String, String>();
        m = StringChangeToMap( str, st );
        if (!m.isEmpty() && m.containsKey( "999290053110222" )) {
            String activeId = m.get( "999290053110222" );
            System.out.println( activeId );
        }


    }
}
