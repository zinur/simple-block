package com.simple.block;



import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashingUtil {

    public static String sha256(String input) {
        return Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();

    }
}