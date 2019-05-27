package org.bird.utils;

import com.google.common.reflect.ClassPath;
import javafx.scene.control.Labeled;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static int roundUp(int dividend, int divisor) {
        return (dividend + divisor - 1) / divisor;
    }

    public static boolean findString(String pattern, String str){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static String groupOneString(String pattern, String str){
        String result = null;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
            result = m.group(1);
        return result;
    }
}
