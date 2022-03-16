package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.*;
import net.sf.cglib.proxy.Enhancer;
import org.assertj.core.util.Streams;
import org.assertj.core.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootDemosApplicationTests {
    private static Logger log = LoggerFactory.getLogger(SpringBootDemosApplication.class);

    @Autowired
    ApplicationContext app;

    @Test
    public void test() throws Exception {
        /*User user = new User();
        user.setNickName("绰号");
        user.setAge(16);
        user.setGender(1);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("./ttt")));
        objectOutputStream.writeObject(user);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("./ttt")));
        User o = (User) objectInputStream.readObject();
        System.out.println(o.getNickName());

        User u = new User();
        Class<?> uClass = u.getClass();
        Class<?> clazz = User.class;
        Class<?> fClass = Class.forName("com.yangweiye.springbootdemos.pojo.User");

        System.out.println(uClass);
        System.out.println(clazz);
        System.out.println(fClass);

        Method[] declaredMethods = clazz.getDeclaredMethods();
        Field[] declaredFields = clazz.getDeclaredFields();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        for (Method method : declaredMethods) {
            System.out.println(method.toString());
        }

        for (Field field : declaredFields) {
            System.out.println(field.toString());
        }

        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor.toString());
        }

        User clazzUser = (User) clazz.newInstance();
        User constructorUser = (User) clazz.getDeclaredConstructor().newInstance();

        System.out.println(clazzUser);
        System.out.println(constructorUser);

        Method setNickName = clazz.getDeclaredMethod("setNickName", String.class);
        setNickName.invoke(clazzUser, "clazzUser");
        setNickName.invoke(constructorUser, "constructorUser");

        System.out.println(clazzUser.getNickName());
        System.out.println(constructorUser.getNickName());*/
    }

    @Test
    public void testProxy() {
        StaticProxyAnimal staticProxyAnimal = new StaticProxyAnimal(new Cat());
        staticProxyAnimal.call();
        Cat cat = new Cat();
        Animal animal = (Animal) Proxy.newProxyInstance(cat.getClass().getClassLoader(),
                cat.getClass().getInterfaces(),
                new TargetInvoker(cat));
        animal.call();
    }

    @Test
    public void testCglib() {
        Cat cat = new Cat();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cat.getClass());
        enhancer.setCallback(new AnimalInterceptor());
        Cat o = (Cat) enhancer.create();
        o.call();
    }

    @Test
    public void testStatic() {
        Solutions solutions = new Solutions();
        System.out.println(String.format("%04d", 9));
        System.out.println(String.format("%04d", 99));
        System.out.println(String.format("%04d", 999));
        System.out.println(String.format("%04d", 9999));
        System.out.println(String.format("%05d", 9999));
        System.out.println(String.format("%06d", 9999));
        System.out.println(String.format("%016d", 9999));

    }

    @Test
    public void testStream() {
        Stream<String> a = Stream.of("a", "b", "c");
        long a1 = a.filter(i -> i.equals("a")).count();
        List<Integer> randoms = Stream.generate(new Random()::nextInt).limit(10).collect(Collectors.toList());
        List<Integer> collect = Stream.iterate(1, i -> i + 1).skip(10).limit(10).map(i -> i *= 10).peek(i -> System.out.println(i)).collect(Collectors.toList());

        List<String> strings = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
        List<String> strings1 = Arrays.asList("eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty");
        List<String> strings2 = Arrays.asList("thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred", "thousand", "million");
        List<List<String>> nest = Arrays.asList(strings, strings1, strings2);
        nest.stream().map(
                i -> i.stream().map(
                        z -> z.toUpperCase()
                )
        ).forEach(
                i -> i.forEach(
                        z -> System.out.println(z)
                )
        );

        //flatMap 将返回的流 中的数据 整合成一个流
        //flatMap 必须返回流
        nest.stream().flatMap(
                i -> i.stream().map(
                        z -> z.toUpperCase()
                )
        ).forEach(
                i -> System.out.println(i)
        );

        Stream.of("a", "b", "c").flatMap(i -> Stream.of(i)).forEach(i -> System.out.println(i));
    }

    public void finalize() {
    }

    public int mergeSort(int[] arr, int start, int end) {
        if (start >= end)
            return 0;
        int middle = start + (end - start >> 1);
        mergeSort(arr, start, middle);
        mergeSort(arr, middle + 1, end);

        _merge(arr, start, middle, middle + 1, end);

        return result;
    }

    int result = 0;

    public void _merge(int[] arr, int s1, int e1, int s2, int e2) {
        int[] temp = new int[e2 - s1 + 1];
        int i = 0;
        int start = s1;
        int end = e2;

        while (s1 <= e1 && s2 <= e2) {
            if (arr[s1] > arr[s2]) {
                result += (e1 - s1 + 1);
                temp[i++] = arr[s2++];
            } else {
                temp[i++] = arr[s1++];
            }
        }

        while (s1 <= e1) {
            temp[i++] = arr[s1++];
        }

        while (s2 <= e2) {
            temp[i++] = arr[s2++];
        }

        i = 0;
        while (start <= end) {
            arr[start++] = temp[i++];
        }
        return;
    }

    public int countDigitOne(int n) {
        int high = n / 10;
        int low = 0;
        int current = n % 10;
        int base = 1;
        int result = 0;

        while (current > 0 || high > 0) {
            if (current == 0) {

            } else if (current == 1) {
                result += 1;
                result += low;
            } else {
                result += base;
            }
            result += high * base;

            low += current * base;
            current = high % 10;
            high /= 10;
            base *= 10;
        }

        return result;
    }

    public int count1(int n) {
        if (n < 1)
            return 0;
        int count = 0;
        int base = 1;
        int round = n;
        while (round > 0) {
            int weight = round % 10;
            round /= 10;
            count += round * base;
            if (weight == 1)
                count += (n % base) + 1;
            else if (weight > 1)
                count += base;
            base *= 10;
        }
        return count;
    }

    class Solution {
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();
            boolean dp[][] = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int i = 0; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            return dp[m][n];
        }

        public boolean matches(String s, String p, int i, int j) {
            if (i == 0)
                return false;

            if (p.charAt(j - 1) == '.')
                return true;

            if (s.charAt(i - 1) == p.charAt(j - 1))
                return true;

            return false;
        }
    }

    class MedianFinder {

        MyHeap<Integer> bigTop;
        MyHeap<Integer> smallTop;
        int size;

        public MedianFinder() {
            size = 0;
            this.bigTop = new MyHeap<Integer>(100, (a, b) -> a - b);
            this.smallTop = new MyHeap<Integer>(100, (a, b) -> b - a);
        }

        public void addNum(int num) {
            if (size == 0) {
                bigTop.put(num);
                size++;
                return;
            }

            if (bigTop.get() < num)
                smallTop.put(num);
            else
                bigTop.put(num);

            size++;
            if ((size & 1) == 0 && (smallTop.size - bigTop.size) != 0) {
                if (smallTop.size > bigTop.size) {
                    bigTop.put(smallTop.remove());
                } else {
                    smallTop.put(bigTop.remove());
                }
            }
        }

        public double findMedian() {
            if ((size & 1) == 0) {
                return (smallTop.get().doubleValue() + bigTop.get().doubleValue()) / 2;
            } else {
                if (smallTop.size > bigTop.size)
                    return smallTop.get();
                else
                    return bigTop.get();
            }
        }
    }

    class MyHeap<T extends Comparable<? super T>> {
        Object arr[];
        int size;
        int capacity;
        Comparator<? super T> comparator;

        MyHeap(int capacity, Comparator<? super T> comparator) {
            this.arr = new Object[capacity + 1];
            this.size = 0;
            this.capacity = capacity;
            this.comparator = comparator;
        }

        public void put(T data) {
            this.arr[++size] = data;
            int p = size;

            while ((p >> 1) > 0 && comparator.compare((T) arr[p], (T) arr[p >> 1]) > 0) {
                Object temp = arr[p >> 1];
                arr[p >> 1] = arr[p];
                arr[p] = temp;
                p = p >> 1;
            }
        }

        private void heapify(int i) {
            while (true) {
                int p = i;
                if (i << 1 <= size && comparator.compare((T) arr[i << 1], (T) arr[p]) > 0)
                    p = i << 1;

                if ((i << 1) + 1 <= size && comparator.compare((T) arr[(i << 1) + 1], (T) arr[p]) > 0)
                    p = (i << 1) + 1;

                if (p == i)
                    break;

                Object temp = arr[i];
                arr[i] = arr[p];
                arr[p] = temp;
                i = p;
            }
        }

        public T get() {
            return (T) this.arr[1];
        }

        public T remove() {
            if (size == 0)
                return null;
            T result = this.get();
            arr[1] = arr[size--];
            this.heapify(1);

            return result;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {
        String result;
        Integer[] arr;
        int index = 0;

        public Codec() {
            result = "";
            arr = new Integer[10];
        }

        void preOrder(TreeNode root) {
            if (null == root) {
                result += "null,";
                return;
            }

            result += root.val + ",";
            preOrder(root.left);
            preOrder(root.right);
            return;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            preOrder(root);

            return result;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");

            return preInsert(split);
        }

        TreeNode preInsert(String[] split) {
            if (split[index].equals("null")) {
                index++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(split[index++]));
            root.left = preInsert(split);
            root.right = preInsert(split);

            return root;
        }
    }

    class Solutions {
        public String longestCommonPrefix(String[] strs) {
            StringBuilder prefix = new StringBuilder();
            int index = 1;
            for (String str : strs) {
                if (str.length() < index)
                    break;

            }

            return "";
        }
    }

}
//1221
