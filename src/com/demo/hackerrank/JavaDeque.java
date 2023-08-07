package com.demo.hackerrank;

import java.util.*;

public class JavaDeque {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.offer(num);
            set.add(num);
            if (deque.size() == m){
                int set_size = set.size();
                if(set_size > max){
                    max = set_size;
                }
                int k = (int)deque.remove();
                if(!deque.contains(k)){
                    set.remove(k);
                }
            }
        }
        System.out.println(max);
    }
}
