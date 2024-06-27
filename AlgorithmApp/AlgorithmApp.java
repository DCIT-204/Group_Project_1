package AlgorithmApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class AlgorithmApp {

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // currentElement stores the current element in the array to be sorted
            int currentElement = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > currentElement) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = currentElement;
        }
    }

    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int middleIndex = array.length / 2;
        int[] leftArray = Arrays.copyOfRange(array, 0, middleIndex); // works like the splice method in javaScript
        int[] rightArray = Arrays.copyOfRange(array, middleIndex, array.length);
        array = merge(mergeSort(leftArray), mergeSort(rightArray));
        return array;
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        int[] resultArray = new int[leftArray.length + rightArray.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                resultArray[resultIndex] = leftArray[leftIndex];
                leftIndex++;

            } else {
                resultArray[resultIndex] = rightArray[rightIndex];
                rightIndex++;

            }
            resultIndex++;
        }
        while (leftIndex < leftArray.length) {
            resultArray[resultIndex] = leftArray[leftIndex];
            leftIndex++;
            resultIndex++;
        }

        while (rightIndex < rightArray.length) {
            resultArray[resultIndex] = rightArray[rightIndex];
            rightIndex++;
            resultIndex++;
        }

        return resultArray;
    }

    private static void radixSort(int[] array) {
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
            while (!arrayList.isEmpty()) {
                int value = arrayList.removeLast();
                int radixIdx = (Math.floorDiv(value, digitRep)) % 10;

                radixArr.get(radixIdx).add(value);
            }
            for (ArrayList<Integer> bucket : radixArr) {
                while (!bucket.isEmpty()) {
                    int value = bucket.removeLast();
                    arrayList.add(value);
                }
            }

            digitRep *= 10;
        }

        // Passing by reference lol. This is gonna mess with the running time.
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }

    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

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

        int[] arr = getArray();

        if (algorithmType == 2) {
            ArrayList<String> choiceOfSearchAlgo = new ArrayList<>(
                    List.of(
                            "1. Linear Search",
                            "2. Binary Search"));
            int searchAlgorithm = getChoice("Select  a searching algorithm: ", choiceOfSearchAlgo);

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
                    insertionSort(arr);
                    break;
                case 6:
                    mergeSort(arr);
                    break;
                case 7:
                    radixSort(arr);
                    break;
                case 8:
                    heapSort(arr);
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
