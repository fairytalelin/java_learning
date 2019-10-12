package com.claylin.javaResource.string;

public class StringIntern {
    public static void main(String[] args) {
        String str1 = "test";
        String str2 = "test";

        String str3 = new String("test");

        System.out.println(str1 == str2);

        System.out.println(str1 == str3);

        String str4 = new String("test");
        String str5 = str3.intern();

        System.out.println(str5 == str1);
    }
}
