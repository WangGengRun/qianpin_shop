package com.itbuka.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Merge {
    public static void merge(int[] arr,int[] tempArr,int left,int mid,int right){
        //标记左半区第一个未排序的元素
        int l_pos=left;
        //标记右半区第一个未排序的元素
        int r_pos=mid+1;
        //临时数组的下标
        int pos=left;
        //合并
        while (l_pos<=mid && r_pos<=right){
            if(arr[l_pos]<arr[r_pos])
                tempArr[pos++]=arr[l_pos++];
            else
                tempArr[pos++]=arr[r_pos++];
        }
        //合并左半区剩余元素
        while(l_pos<=mid){
            tempArr[pos++]=arr[l_pos++];
        }
        //合并右半区剩余元素
        while(r_pos<=right){
            tempArr[pos++]=arr[r_pos++];
        }
        //把临时数组中合并后的元素复制到原来的数组
        while(left<=right){
            arr[left]=tempArr[left];
            left++;
        }

    }
    //归并排序
    public static void msort(int[] arr,int[] tempArr,int left,int right){
        //如果只有一个元素那么不需要继续划分
        if(left<right){
            //找中间点
            int mid =(left+right)/2;
            //递归划分左半区
            msort(arr,tempArr,left,mid);
            //递归划分右半区
            msort(arr,tempArr,mid+1,right);
            //合并已经排序的部分
            merge(arr,tempArr,left,mid,right);
        }

    }
    public static int[] merge_sort(){
        int[] arr=new int[]{9,5,2,7,12,4,3,1,11};
        int n=9;
        int[] tempArr=new int[n];
        msort(arr,tempArr,0,n-1);
        return arr;
    }

    public static void main(String[] args) {
//        List<String> list1= Arrays.asList("1","11","1111","2222","3333");
//        List<String> list2=list1.stream().filter(s -> s.length()>3).collect(Collectors.toList());
////        for(String s:list2){
////            System.out.println(s);
////        }
//        System.out.println(list2);

//        List<Integer> list3=Arrays.asList(1,2,3);
//        int sum=list3.stream().mapToInt(Integer::intValue).sum();
//        System.out.println(sum);
        int[] arr=merge_sort();
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
