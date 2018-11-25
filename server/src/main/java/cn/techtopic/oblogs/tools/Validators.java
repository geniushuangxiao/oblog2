package cn.techtopic.oblogs.tools;

public class Validators {
    public static boolean nullOrEmpty(String str) {
        return null == str || str.isEmpty();
    }
}
