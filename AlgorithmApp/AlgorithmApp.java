package AlgorithmApp;
import java.util.Scanner;
import java.util.Arrays;

public class AlgorithmApp {

        // Linear Search Algorithm
        public static int linearSearch(int[] arr, int key) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == key) {
                    return i;
                }
            }
            return -1;
        }

        // Binary Search Algorithm
        public static int binarySearch(int[] arr, int key) {
            int left = 0;
            int right = arr.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == key) {
                    return mid;
                } else if (arr[mid] < key) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }

        // Bubble Sort Algorithm
        public static void bubbleSort(int[] arr) {
            int n = arr.length;
            boolean swapped;
            for (int i = 0; i < n - 1; i++) {
                swapped = false;
                for (int j = 0; j < n - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        swapped = true;
                    }
                }
                if (!swapped) break;
            }
        }

        // Quick Sort Algorithm
        public static void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        private static int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (arr[j] < pivot) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            return i + 1;
        }

        public static int getChoice(String question, String choiceOne, String choiceTwo){
            int choice = 0;
            Scanner scanner = new Scanner(System.in);
            try{
                System.out.println(question);
                System.out.println(choiceOne);
                System.out.println(choiceTwo);
                System.out.println();
                choice = scanner.nextInt();
                while(choice != 1 && choice != 2){
                    System.out.println("Enter a number within the specified range");
                    choice = getChoice(question, choiceOne, choiceTwo);
                }
                return choice;
            }
            catch (Exception e){
                System.out.println("Enter a valid number!");
                choice = getChoice(question, choiceOne, choiceTwo);
            }
            return choice;
        }

        public static int getKey(String query){
            int key = 0;
            System.out.println(query);
            Scanner scanner = new Scanner(System.in);
            try{
                key = scanner.nextInt();
                if(query.contains("size")){
                    while(key < 1){
                        System.out.println("Enter a number greater than 0");
                        key = getKey(query);
                    }
                }
                return key;
            }
            catch (Exception e){
                System.out.println("Enter a valid number!");
                key = getKey(query);
            }
            return key;
        }
    public static int[] getArray() {
        Scanner scanner = new Scanner(System.in);
        int n = getKey("Enter an array size ");
        int[] arr = new int[n];
        try
        {
            System.out.println("Enter the elements of the array: ");
            for (int i = 0; i < n; i++) {
                System.out.print("Element " + (i + 1) + ": ");
                arr[i] = scanner.nextInt();
            }
            return arr;
        }
        catch (Exception e){
            System.out.println("Please enter a valid number!");
            arr = getArray();
        }
        return arr;
    }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);


            int algorithmType = getChoice("Select  an algorithm type: ", "1.Sorting", "2.Searching " );

            int[] arr = getArray();

            if (algorithmType == 2) {
                int searchAlgorithm = getChoice("Select  a searching algorithm: ", "1. Linear Search","2. Binary Search" );

                int key = getKey("Enter the value you want to search for");

                long startTime = System.nanoTime();
                int result = Integer.MIN_VALUE;

                if (searchAlgorithm == 1) {
                    result = linearSearch(arr, key);
                } else if (searchAlgorithm == 2) {
                    Arrays.sort(arr);  // Binary search requires a sorted array
                    result = binarySearch(arr, key);
                }

                long endTime = System.nanoTime();
                long duration = (endTime - startTime);

                if (result != Integer.MIN_VALUE) {
                    System.out.println("Value found at index: " + result);
                } else {
                    System.out.println("Value not found.");
                }

                System.out.println("Running time (nanoseconds): " + duration);

            } else if (algorithmType == 1) {
                int sortAlgorithm = getChoice("Select a sorting algorithm:","1. Quick Sort", "2. Bubble Sort");
                long startTime = System.nanoTime();

                if (sortAlgorithm == 1) {
                    bubbleSort(arr);
                } else if (sortAlgorithm == 2) {
                    quickSort(arr, 0, arr.length - 1);
                }

                long endTime = System.nanoTime();
                long duration = (endTime - startTime);

                System.out.println("Sorted list: " + Arrays.toString(arr));
                System.out.println("Running time (nanoseconds): " + duration);
            }

            scanner.close();
        }

    }
