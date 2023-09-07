package com.intercorp.mstwo.util;

public class StringUtil {

    public static final char[] CHARS_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final String createRandomString(int longitud, char[] baseChars) {
        int totalChars = baseChars.length - 1;

        String randomString = "";

        for (int i = 0; i < longitud; i++) {
            int randomIndex = Integer.parseInt(Math.round(Math.random() * totalChars) + "");
            randomString = randomString.concat(baseChars[randomIndex] + "");
        }

        return randomString;
    }

}
