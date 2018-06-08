package com.claylin.algorithm.exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CountNum {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && !line.isEmpty()) {
            System.out.println(doCount(line));
            line = br.readLine();
        }
    }

    private static String doCount(String str) {
        char[] chars = str.toCharArray();

        char ch = chars[0];
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (ch == chars[i]) {
                count++;
            } else {
                sb.append(ch);
                if (count >= 2) {
                    sb.append(count);
                }
                count = 1;
                ch = chars[i];
            }
        }

        sb.append(ch);
        if (count >= 2) {
            sb.append(count);
        }

        return sb.toString();
    }
}
