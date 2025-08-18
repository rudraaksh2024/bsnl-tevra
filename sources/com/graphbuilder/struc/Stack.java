package com.graphbuilder.struc;

public class Stack extends LinkedList {
    public Object peek() {
        if (this.head == null) {
            return null;
        }
        return this.head.userObject;
    }

    public Object pop() {
        return removeHead();
    }

    public void push(Object obj) {
        addToHead(obj);
    }
}
