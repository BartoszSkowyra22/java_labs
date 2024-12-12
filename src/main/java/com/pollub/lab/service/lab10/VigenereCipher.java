package com.pollub.lab.service.lab10;

public class VigenereCipher {

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base - (key.charAt(keyIndex % key.length()) - 'A') + 26) % 26 + base);
                keyIndex++;
            }
            result.append(c);
        }

        return result.toString();
    }
}
