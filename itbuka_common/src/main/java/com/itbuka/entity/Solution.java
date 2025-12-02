package com.itbuka.entity;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @param n int整型
     * @return string字符串
     */
    public static String trans(String s, int n) {
        // write code here
        String[] ss = s.split(" ");
        StringBuilder newS=new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            ss[i]=convertCase(ss[i]);
        }
        int l=0;
        int r=ss.length-1;
        while(l<r){
            String temp=ss[r];
            ss[r]=ss[l];
            ss[l]=temp;
            l++;
            r--;
        }
        for(int i=0;i<ss.length;i++){
            if(i==ss.length-1){
                newS.append(ss[i]);
                continue;
            }
            newS.append(ss[i]+" ");
        }
        return newS.toString();
    }
    public static String convertCase(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                chars[i] = (char) (c - 32); // 小写转大写
            } else if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) (c + 32); // 大写转小写
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String s="This is a sample";
        int n=16;
        String s1=trans(s,n);
        System.out.println(s1);
    }
}