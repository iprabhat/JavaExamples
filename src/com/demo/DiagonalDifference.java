package com.demo;

import java.util.Arrays;
import java.util.List;

public class DiagonalDifference {

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(11, 2, 4);
        List<Integer> l2 = Arrays.asList(4, 5, 6);
        List<Integer> l3 = Arrays.asList(10, 8, -12);

        List<List<Integer>> lst = Arrays.asList(l1, l2, l3);
        System.out.println(lst);

        int lsum = 0;
        int rsum = 0;

        for (int i = 0; i < lst.size(); i++) {
            List<Integer> tmp = lst.get(i);
            if (Math.ceil(lst.size() / 2.0) == i) {
                lsum += tmp.get(i);
                rsum += tmp.get(i);
            }
            lsum += tmp.get(i);
            rsum += tmp.get(tmp.size() - 1 - i);
        }

        System.out.println(Math.abs(lsum - rsum));

    }

}
