package AlgorithmApp;

public class InsertionSort {

    public static void insertionSort(int[] var0) {
        for(int var1 = 1; var1 < var0.length; ++var1) {
            int var2 = var0[var1];
            int var3;
            for(var3 = var1 - 1; var3 >= 0 && var0[var3] > var2; --var3) {
                var0[var3 + 1] = var0[var3];
            }
            var0[var3 + 1] = var2;
        }
    }
}
