package week_07.commit;

import java.util.TreeMap;

// 1122. 数组的相对排序
// https://leetcode-cn.com/problems/relative-sort-array/
public class Solution_1122_01 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] cnt = new int[1001];
        for(int n : arr1) cnt[n]++;
        int i = 0;
        for(int n : arr2) {
            while(cnt[n]-- > 0) {
                arr1[i++] = n;
            }
        }
        for(int n = 0; n < cnt.length; n++) {
            while(cnt[n]-- > 0) {
                arr1[i++] = n;
            }
        }
        return arr1;
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int n : arr1) map.put(n, map.getOrDefault(n, 0) + 1);
        int i = 0;
        for(int n : arr2) {
            while(map.get(n) > 0) {
                arr1[i++] = n;
                map.put(n, map.get(n)-1);
            }
        }
        for(int n : map.keySet()){
            while(map.get(n) > 0) {
                arr1[i++] = n;
                map.put(n, map.get(n)-1);
            }
        }
        return arr1;
    }
}
