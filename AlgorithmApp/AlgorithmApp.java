package AlgorithmApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class AlgorithmApp {

    public static int getChoice(String question, ArrayList<String> choices) {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(question);
            for (String opt : choices) {
                System.out.println(opt);
            }
            choice = scanner.nextInt();
            while (choice < 1 || choice > choices.size()) {
                System.out.println("Enter a number within the specified range");
                choice = getChoice(question, choices);
            }
            return choice;
        } catch (Exception e) {
            System.out.println("Enter a valid number!");
            choice = getChoice(question, choices);
        }
        return choice;
    }

    public static int getKey(String query) {
        int key = 0;
        System.out.println(query);
        Scanner scanner = new Scanner(System.in);
        try {
            key = scanner.nextInt();
            if (query.contains("size")) {
                while (key < 1) {
                    System.out.println("Enter a number greater than 0");
                    key = getKey(query);
                }
            }
            return key;
        } catch (Exception e) {
            System.out.println("Enter a valid number!");
            key = getKey(query);
        }
        return key;
    }

    public static int[] getArray() {
        Scanner scanner = new Scanner(System.in);
        int n = getKey("Enter an array size ");
        int[] arr = new int[n];
        try {
            System.out.println("Enter the elements of the array: ");
            for (int i = 0; i < n; i++) {
                System.out.print("Element " + (i + 1) + ": ");
                arr[i] = scanner.nextInt();
            }
            return arr;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            arr = getArray();
        }
        return arr;
    }

    public static void runAlgorithms() {
        ArrayList<String> choiceOfAlgo = new ArrayList<>(
                List.of(
                        "1.Sorting",
                        "2.Searching "));
        int algorithmType = getChoice("Select an algorithm type: ", choiceOfAlgo);


        if (algorithmType == 2) {
            ArrayList<String> choiceOfSearchAlgo = new ArrayList<>(
                    List.of(
                            "1. Linear Search",
                            "2. Binary Search"));
            int searchAlgorithm = getChoice("Select  a searching algorithm: ", choiceOfSearchAlgo);
            int[] arr = getArray();

            int key = getKey("Enter the value you want to search for");

            long startTime = System.nanoTime();
            int result = -1;

            if (searchAlgorithm == 1) {
                result = LinearSearch.linearSearch(arr, key);
            } else if (searchAlgorithm == 2) {
                Arrays.sort(arr); // Binary search requires a sorted array
                result = BinarySearch.binarySearch(arr, key);
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            if (result != -1) {
                System.out.println("Value found at index: " + result);
            } else {
                System.out.println("Value not found.");
            }

            System.out.println("Running time (nanoseconds): " + duration);

        } else if (algorithmType == 1) {
            ArrayList<String> choiceOfSortingALgo = new ArrayList<>(
                    List.of("1. Quick Sort", "2. Bubble Sort", "3. Selection Sort", "4. Shell Sort",
                            "5. Insertion Sort", "6. Merge Sort", "7. Radix Sort", "8. Heap Sort"));
            int sortAlgorithm = getChoice("Select a sorting algorithm:", choiceOfSortingALgo);
            int[] arr = getArray();

            long startTime = System.nanoTime();

            switch (sortAlgorithm) {
                case 1:
                    BubbleSort.bubbleSort(arr);
                    break;
                case 2:
                    QuickSort.quickSort(arr, 0, arr.length - 1);
                    break;
                case 3:
                    SelectionSort.selectionSort(arr);
                    break;
                case 4:
                    ShellSort.shellSort(arr);
                    break;
                case 5:
                    InsertionSort.insertionSort(arr);
                    break;
                case 6:
                    MergeSort.mergeSort(arr);
                    break;
                case 7:
                    RadixSort.radixSort(arr);
                    break;
                case 8:
                    HeapSort.heapSort(arr);
                    break;
                default:
                    break;
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            System.out.println("Sorted list: " + Arrays.toString(arr));
            System.out.println("Running time (nanoseconds): " + duration);
        }

    }

    public static void runProgram() {
        System.out.println("Welcome Algorithm masters");
        int choice;
        do {
            runAlgorithms();
            ArrayList<String> programChoice = new ArrayList<>(
                    List.of("1. Run the program again", "2. Quit"));
            choice = getChoice("What would you like to do next? ", programChoice);
        } while (choice == 1);

        System.out.println("Bye 👋");

    }

    public static void main(String[] args) {
        runProgram();
    }
}
