package com.example.demo.leetcode.week.www;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App11findLeastNumOfUniqueInts {

	class Solution {
		public int findLeastNumOfUniqueInts(int[] arr, int k) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();

//			Integer tmp;
			for (int one : arr) {
				map.put(one, map.getOrDefault(one, 0) + 1);
				/*if ((tmp = map.putIfAbsent(one, 1)) != null) {
					map.put(one, tmp + 1);
				}*/
			}

			List<Map.Entry<Integer, Integer>> lst = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());

			lst.sort(new Comparator<Map.Entry<Integer, Integer>>() {

				@Override
				public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			int res = lst.size();
			for (Map.Entry<Integer, Integer> one : lst) {
				System.out.println(one.getKey());
				if (k >= one.getValue()) {
					k -= one.getValue();
					res--;
				} else {
					break;
				}
			}
			return res;
		}
	}

	public static void main(String[] args) {
		App11findLeastNumOfUniqueInts app = new App11findLeastNumOfUniqueInts();
		Solution sol = app.new Solution();
		int[] a = { 5, 5, 4 };
		System.out.println(sol.findLeastNumOfUniqueInts(a, 1));
	}
}
