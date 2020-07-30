package com.again.www;

import java.util.*;

public class App {

    static class Node {

        public int key;
        public int value;
        
        public Node(int key, int value) {

            this.key = key;
            this.value = value;
        }
    }

    static class LRUdata {

        List<Node> LRUDATA;
        final int capacity;
        int size;

        public LRUdata(int capacity) {

            this.LRUDATA = new ArrayList<>();
            this.capacity = capacity;
            this.size = -1;
        }

        public int getData(int key) {

            if (this.size == -1) return -1;
            Node tmp = null;
            Node result = null;
            for (int i = this.size; i >= 0; i--) {
                if ((tmp = LRUDATA.get(i)).key != key) continue;
                LRUDATA.remove(i);
                LRUDATA.add(tmp);
                result = tmp;
                break;
            }
            return result == null ? -1 : result.value;
        }

        public void put(int key, int value) {
            /**
             * 考虑不同的可能性 key值的要求
             *     key-value为一个本体
             *     key为单独一个本体确认
             */
            if (getData(key) != -1) {
                LRUDATA.remove(this.size);
                Node newNode = new Node(key, value);
                LRUDATA.add(newNode);
                return;
            }

            if (this.size == this.capacity - 1) {

                LRUDATA.remove(0);
                Node newNode = new Node(key, value);
                LRUDATA.add(newNode);
            } else if (this.size < this.capacity) {

                Node newNode = new Node(key, value);
                LRUDATA.add(newNode);
                this.size++;
            }
        }
    }

    class LRUCache {

        class Node {

            public int key;
            public int value;

            public Node(int key, int value) {

                this.key = key;
                this.value = value;
            }
        }

        List<Node> LRUDATA;
        final int capacity;
        int size;

        public LRUCache(int capacity) {

            this.LRUDATA = new ArrayList<>();
            this.capacity = capacity;
            this.size = -1;
        }

        public int get(int key) {
            if (this.size == -1) return -1;
            Node tmp = null;
            Node result = null;
            for (int i = this.size; i >= 0; i--) {
                if ((tmp = LRUDATA.get(i)).key != key) continue;
                LRUDATA.remove(i);
                LRUDATA.add(tmp);
                result = tmp;
                break;
            }
            return result == null ? -1 : result.value;
        }

        public void put(int key, int value) {

            if (get(key) != -1) {
                LRUDATA.remove(this.size);
                Node newNode = new Node(key, value);
                LRUDATA.add(newNode);
                return;
            }

            if (this.size == this.capacity - 1) {

                LRUDATA.remove(0);
                Node newNode = new Node(key, value);
                LRUDATA.add(newNode);
            } else if (this.size < this.capacity) {

                Node newNode = new Node(key, value);
                LRUDATA.add(newNode);
                this.size++;
            }
        }
    }

}
