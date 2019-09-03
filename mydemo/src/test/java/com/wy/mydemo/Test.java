package com.wy.mydemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();//数据集合
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        stringList.add("e");
        for (String name :stringList ) {
            if("e".equals(  name)){
                stringList.remove( name );
            }
        }

        
    }

}
