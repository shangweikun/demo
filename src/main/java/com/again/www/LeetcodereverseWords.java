package com.again.www;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class LeetcodereverseWords {
    class Solution {
        public String reverseWords(String s) {

            String[] input = s.split(" ");

            if(input.length == 0) return "";

            Deque<String> deque = new LinkedList<>();
            for(String one : input){
                if(one.trim().isEmpty()) continue;
                deque.add(one);
            }

            if(deque.isEmpty()) return  "";

            StringBuffer result = new StringBuffer();
            result.append(deque.removeLast());
            while(!deque.isEmpty()){
                result.append(" ");
                result.append(deque.removeLast());
            }
            return result.toString();
        }
    }

    /* DeepCloneTest falure */
    public static void main(String args[]){
        LeetcodereverseWords app = new LeetcodereverseWords();
        Test test1 = app.new Test(app.new Innertest());
        Test test2 = test1.iDeepClone();

        test1.test.a =2;
        System.out.println(test2.test.a);
        boolean flag;
        if(flag = true)
            System.out.println(1);
    }

    class Test{
        Innertest test ;
        public Test(Innertest test){
            this.test = test;
        }
        public Test iDeepClone(){
            return new Test(this.test);
        }
    }

    class Innertest{
        public int a =1;
    }
}
