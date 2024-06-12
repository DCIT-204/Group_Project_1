package AlgorithmApp;
import java.util.ArrayList;
import java.util.List;
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

        public static void selectionSort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
              int minIndex = i;
              for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                  minIndex = j;
                }
              }
              int temp = arr[minIndex];
              arr[minIndex] = arr[i];
              arr[i] = temp;
            }
            // Selection sort has a time complexity of O(n^2)
            //Selection sort has a space complexity of O(1)
          }
        
          public static void shellSort(int[] arr) {
            int n = arr.length;
            for (int gap = n / 2; gap > 0; gap /= 2) {
              for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                  arr[j] = arr[j - gap];
                }
                arr[j] = temp;
              }
            }
            // Shell sort has a time complexity of O(n^(3/2)) ie O(n^2)
            //Shell sort has a space complexity of O(1)
          }

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


        public static int getChoice(String question, ArrayList<String> choices){
            int choice = 0;
            Scanner scanner = new Scanner(System.in);
            try{
                System.out.println(question);
                for (String opt: choices ) {
                    System.out.println(opt);
                }
                choice = scanner.nextInt();
                while(choice < 1 || choice > choices.size()){
                    System.out.println("Enter a number within the specified range");
                    choice = getChoice(question,choices);
                }
                return choice;
            }
            catch (Exception e){
                System.out.println("Enter a valid number!");
                choice = getChoice(question, choices);
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

        public static void runAlgorithms(){
            ArrayList<String> choiceOfAlgo = new ArrayList<>(
                    List.of(
                            "1.Sorting",
                            "2.Searching "
                    )
            );
            int algorithmType = getChoice("Select an algorithm type: ", choiceOfAlgo);

            int[] arr = getArray();

            if (algorithmType == 2) {
                ArrayList<String> choiceOfSearchAlgo = new ArrayList<>(
                        List.of(
                                "1. Linear Search",
                                "2. Binary Search"
                        )
                );
                int searchAlgorithm = getChoice("Select  a searching algorithm: ",choiceOfSearchAlgo );

                int key = getKey("Enter the value you want to search for");

                long startTime = System.nanoTime();
                int result = -1;

                if (searchAlgorithm == 1) {
                    result = linearSearch(arr, key);
                } else if (searchAlgorithm == 2) {
                    Arrays.sort(arr);  // Binary search requires a sorted array
                    result = binarySearch(arr, key);
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
                        List.of("1. Quick Sort", "2. Bubble Sort", "3. Selection Sort", "4. Shell Sort", "5. Insertion Sort", "6. Merge Sort")
                );
                int sortAlgorithm = getChoice("Select a sorting algorithm:", choiceOfSortingALgo);
                long startTime = System.nanoTime();

                switch (sortAlgorithm) {
                    case 1:
                        bubbleSort(arr);
                        break;
                    case 2:
                        quickSort(arr, 0, arr.length - 1);
                        break;
                    case 3:
                        selectionSort(arr);
                        break;
                    case 4:
                        shellSort(arr);
                        break;
                    case 5:
                        insertionSort(arr);
                        break;
                    case 6:
                        mergeSort(arr);
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

        public static void runProgram(){
            System.out.println();
            System.out.println("Welcome Algorithm masters");
            ArrayList<String> programChoice = new ArrayList<>(
                    List.of("1. Run the program", "2. Quit")
            );
            int choice = getChoice("What would you like to do? ", programChoice);
            switch (choice){
                case 1:
                    runAlgorithms();
                    runProgram();
                    break;
                case 2:
                    System.out.println("Bye \uD83D\uDC4B ");
                    System.exit(1);
                    break;
            }
        }

    public static void main(String[] args) {
        runProgram();
    }
    }
