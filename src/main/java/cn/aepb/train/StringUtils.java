package cn.aepb.train;

public class StringUtils {

    public static void ensureNotEmpty(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException();
        }
    }
}
