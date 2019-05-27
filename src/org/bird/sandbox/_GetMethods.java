package org.bird.sandbox;

import javafx.scene.control.Label;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class _GetMethods {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> labelClass = Class.forName("javafx.scene.control.Label");
        Method[] methods = labelClass.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>(Arrays.asList(methods));
        methodList.forEach(new Consumer<Method>() {
            @Override
            public void accept(Method method) {
                System.out.println(method.getName());
            }
        });

    }
}
