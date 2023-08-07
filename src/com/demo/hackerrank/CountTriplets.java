package com.demo.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {

	static long countTriplets(List<Long> arr, long r) {
		Map<Long, Long> potential = new HashMap<>();
		Map<Long, Long> counter = new HashMap<>();
		long count = 0;
		for (int i = 0; i < arr.size(); i++) {
			long a = arr.get(i);
			long key = a / r;

			if (counter.containsKey(key) && a % r == 0) {
				count += counter.get(key);
			}

			if (potential.containsKey(key) && a % r == 0) {
				long c = potential.get(key);
				counter.put(a, counter.getOrDefault(a, 0L) + c);
			}

			potential.put(a, potential.getOrDefault(a, 0L) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		// List<Long> lst = new ArrayList<Long>();
		List<Long> lst = Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L);
		long ratio = 3;
		long countTriplets = countTriplets(lst, ratio);
		System.out.println(countTriplets);
	}
}
