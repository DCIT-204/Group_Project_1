package AlgorithmApp;

import java.util.ArrayList;
import java.util.Arrays;


public class RadixSort {
    private static ArrayList<Integer> radixSort(int[] array) {
    // convert to arraylist
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (int i : array) {
                     arrayList.add(i);
            }
            ArrayList<ArrayList<Integer>> radixArr = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < 10; i++) {
                    radixArr.add(new ArrayList<Integer>());
            }
            int max = Arrays.stream(array).max().getAsInt();
            int digitRep = 1; // Position in focus (1, 10, 100, 1000, ...)
            while (Math.floorDiv(max, digitRep) > 0) {
                while (arrayList.size() > 0) {
                            int value = arrayList.remove(arrayList.size()-1);
                            int radixIdx = (Math.floorDiv(value, digitRep)) % 10;
                            radixArr.get(radixIdx).add(value);
                }
                for (ArrayList<Integer> bucket : radixArr) {
                    while (bucket.size() > 0) {
                        int value = bucket.remove(bucket.size()-1);
                        arrayList.add(value);
 }
        }
        digitRep *= 10;
    }
        for (int i = 0; i < array.length; i++) {
                 array[i] = arrayList.get(i);
    }
        return arrayList;
 }
}
