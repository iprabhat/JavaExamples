package com.demo.hackerrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrequencyQueries {

    public static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> out = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> mapCount = new HashMap<>();
        for (List<Integer> l : queries) {
            Integer idx = l.get(0);
            Integer val = l.get(1);
            Integer v = map.getOrDefault(val, 0);
            if (idx == 1) {
                if (v > 0) {
                    map.put(val, v + 1);
                    mapCount.put(v, mapCount.getOrDefault(v, 0) > 0 ? mapCount.getOrDefault(v, 0) - 1 : 0);
                    mapCount.put(v + 1, mapCount.getOrDefault(v + 1, 0) + 1);
                } else {
                    map.put(val, 1);
                    mapCount.put(1, mapCount.getOrDefault(1, 0) + 1);
                }
            } else if (idx == 2) {
                if (v > 0) {
                    map.put(val, v - 1);
                    mapCount.put(v, mapCount.getOrDefault(v, 0) - 1);
                    mapCount.put(v - 1, mapCount.getOrDefault(v - 1, 0) + 1);
                }
            } else if (idx == 3) {
                if (mapCount.getOrDefault(val, 0) > 0)
                    out.add(1);
                else
                    out.add(0);
            }
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        // List<List<Integer>> lst = new ArrayList<>();

        // --- TEST CASE 1
        // lst.add(Arrays.asList(1, 3));
        // lst.add(Arrays.asList(2, 3));
        // lst.add(Arrays.asList(3, 2));
        // lst.add(Arrays.asList(1, 4));
        // lst.add(Arrays.asList(1, 5));
        // lst.add(Arrays.asList(1, 5));
        // lst.add(Arrays.asList(1, 4));
        // lst.add(Arrays.asList(3, 2));
        // lst.add(Arrays.asList(2, 4));
        // lst.add(Arrays.asList(3, 2));

        // --- TEST CASE 2
        // lst.add(Arrays.asList(3, 4));
        // lst.add(Arrays.asList(2, 1003));
        // lst.add(Arrays.asList(1, 16));
        // lst.add(Arrays.asList(3, 1));

        // --- TEST CASE 3
        // lst.add(Arrays.asList(1, 5));
        // lst.add(Arrays.asList(1, 6));
        // lst.add(Arrays.asList(3, 2));
        // lst.add(Arrays.asList(1, 10));
        // lst.add(Arrays.asList(1, 10));
        // lst.add(Arrays.asList(1, 6));
        // lst.add(Arrays.asList(2, 5));
        // lst.add(Arrays.asList(3, 2));

        // List<Integer> ans = freqQuery(lst);
        // System.out.println(ans);

        String filePath = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                new File(filePath)));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();
        long start = System.currentTimeMillis();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);
        long end = System.currentTimeMillis();
        // System.out.println(ans.size());
        ans.stream().forEach(System.out::println);

        bufferedReader.close();
        System.out.printf("Time elapsed: %d ms\n", (end - start));

    }
}
