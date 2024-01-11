package com.teachmeskills.mycourse.coder;

import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

public class Coder {
    public static String coder(String cred) {
        String encoderString = Base64.getEncoder().encodeToString(cred.getBytes());
        String result = addSalt(encoderString);
        return result;
    }
    public static String deCoder(String cred) {
        byte[] bytes =  Base64.getDecoder().decode(cred.substring(10));
        String deCode = new String(bytes);
        return deCode;
    }
    private static String addSalt(String coderCred) {
        String symbols = "qwertyuiopasdfghjklzxcvbnm1234567890";

        String salt = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        return salt + coderCred;
    }
}


