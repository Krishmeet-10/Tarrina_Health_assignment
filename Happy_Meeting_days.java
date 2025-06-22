package Merge_Sort;

import java.util.*;

public class Happy_Meeting_days {
    //the logic behind this is the number of concurrent meetings that occur together will be the total no of daysrequired.
    //this can be calculated using mergesort with O(nlogn) time complexity
    
    private static int[] temp;
    
    public int minMeetingDays(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        int n = intervals.length;
        
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        temp = new int[n];
        mergeSort(starts, 0, n - 1);
        mergeSort(ends, 0, n - 1);
        
        int maxConcurrentMeetings = 0;
        int currentMeetings = 0;
        int startIdx = 0;
        int endIdx = 0;
        
        while (startIdx < n) {
            if (endIdx >= n || starts[startIdx] < ends[endIdx]) {
                currentMeetings++;
                maxConcurrentMeetings = Math.max(maxConcurrentMeetings, currentMeetings);
                startIdx++;
            } else {
                currentMeetings--;
                endIdx++;
            }
        }
        
        return maxConcurrentMeetings;
    }
    
    public static void mergeSort(int[] arr, int f, int l) {
        if (f < l) {
            int mid = (f + l) / 2;
            mergeSort(arr, f, mid);
            mergeSort(arr, mid + 1, l);
            merge(arr, f, mid, l);
        }
    }
    
    public static void merge(int[] arr, int f, int mid, int l) {
        int i = f;
        int j = mid + 1;
        int k = f;
        
        while (i <= mid && j <= l) {
            if (arr[i] <= arr[j]) { 
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        
        while (j <= l) {
            temp[k++] = arr[j++];
        }
        
        for (int z = f; z <= l; z++) {
            arr[z] = temp[z];
        }
    }
    
    public static void main(String[] args) {
        Happy_Meeting_days solution = new Happy_Meeting_days();
        
        int[][] intervals1 = {{0,30},{5,10},{15,20}};
        System.out.println("Example 1: " + solution.minMeetingDays(intervals1)); 
        
        int[][] intervals2 = {{7,10},{2,4}};
        System.out.println("Example 2: " + solution.minMeetingDays(intervals2)); 
        
        int[][] intervals3 = {{1,5},{8,9},{8,9}};
        System.out.println("Example 3: " + solution.minMeetingDays(intervals3)); 
        
        int[][] intervals4 = {{1,4},{2,5},{3,6}};
        System.out.println("Example 4: " + solution.minMeetingDays(intervals4)); 
        
        int[][] intervals5 = {{1,2},{2,3},{3,4},{4,5}};
        System.out.println("Example 5: " + solution.minMeetingDays(intervals5)); 
    }
}