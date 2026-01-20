package utils;

import net.bytebuddy.utility.RandomString;

public class RandomUtils {

    public static String randomString(int length) {
        return RandomString.make(length);
    }

    public static void main(String[] args) {
        System.out.println(randomString(555));
    }
}
