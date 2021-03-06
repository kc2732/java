package com.company;

import java.util.HashMap;

/**
 * Created by PC on 2017/1/29.
 */
class Node{
    int key;
    int value;
    Node pre;
    Node next;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}
public class LRUCache {

    private HashMap<Integer, Node> map;
    private Node head, tail;
    private int count,capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        count = 0;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        head.pre = null;
        tail.next = null;
        tail.pre = head;

    }

    private void deleteNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public int get(int key) {
        if(map.get(key)!=null){
            Node node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            return result;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.get(key)!=null){
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }else{
            Node node = new Node(key, value);
            map.put(key,node);
            if(count < capacity){
                count++;
                addToHead(node);
            }else{
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */