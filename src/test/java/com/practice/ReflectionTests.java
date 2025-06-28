package com.practice;

import com.coreJava.ReflectionTesting;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Private Method	Use Method.setAccessible(true) and invoke
 * Static Method	Use Method.invoke(null, args) or PowerMock
 * Private Static	Same: Reflection or PowerMock
 * Public Method	Test normally via class instance
 */
public class ReflectionTests {

    @Test
    public void ques1() {

        System.out.println("Hello, World!");
        List<String> l1= Arrays.asList("ganesh","123", "j12", "45","ashwani45");
        List<Integer> l2=Arrays.asList(0,9);

        List<Integer> result = l1.stream()
                .filter(
                        word  -> word.matches("\\d+")
                ).peek(System.out::println)
                .map(Integer:: parseInt)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void testPrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflectionTesting obj = new ReflectionTesting();
        Method method = ReflectionTesting.class.getDeclaredMethod("printHelloPrivate", String.class);
        method.setAccessible(true); //bypass private access
        String result = (String) method.invoke(obj,"Test User");
        System.out.println(result);
    }

    @Test
    public void testStaticMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflectionTesting obj = new ReflectionTesting();
        Method method = ReflectionTesting.class.getDeclaredMethod("printHelloStatic", String.class);
        method.setAccessible(true); //bypass private access
        String result = (String) method.invoke(null,"Test User");
        //null because not static
        System.out.println(result);
    }

//    @Test
//    void testInvokePrivateMethodWithReflectionTestUtils() {
//        // Invoke private method 'formatMessage'
//        Object result = ReflectionTestUtils.invokeMethod(notificationService, "formatMessage", "Hi");
//
//        assertEquals("[NOTIFICATION]: Hi", result);
//    }


}
