package com.graphbuilder.struc;

public class LinkedList {
    protected Node head = null;
    protected int size = 0;
    protected Node tail = null;

    public static class Node {
        protected LinkedList list;
        protected Node next = null;
        protected Node prev = null;
        protected Object userObject;

        protected Node(LinkedList linkedList, Object obj) {
            this.list = linkedList;
            this.userObject = obj;
        }

        public LinkedList list() {
            return this.list;
        }

        public Node next() {
            return this.next;
        }

        public Node prev() {
            return this.prev;
        }

        public Object getUserObject() {
            return this.userObject;
        }

        public void setUserObject(Object obj) {
            this.userObject = obj;
        }

        public void insertBefore(Object obj) {
            this.list.insertBefore(this, obj);
        }

        public void insertAfter(Object obj) {
            this.list.insertAfter(this, obj);
        }

        public void remove() {
            this.list.removeNode(this);
        }
    }

    /* access modifiers changed from: protected */
    public Node createNode(Object obj) {
        return new Node(this, obj);
    }

    /* access modifiers changed from: protected */
    public void insertBefore(Node node, Object obj) {
        Node createNode = createNode(obj);
        if (this.size == 0) {
            this.head = createNode;
            this.tail = createNode;
        } else {
            Node node2 = this.head;
            if (node == node2) {
                createNode.next = node2;
                this.head.prev = createNode;
                this.head = createNode;
            } else {
                node.prev.next = createNode;
                createNode.prev = node.prev;
                node.prev = createNode;
                createNode.next = node;
            }
        }
        this.size++;
    }

    /* access modifiers changed from: protected */
    public void insertAfter(Node node, Object obj) {
        Node createNode = createNode(obj);
        if (this.size == 0) {
            this.head = createNode;
            this.tail = createNode;
        } else {
            Node node2 = this.tail;
            if (node == node2) {
                createNode.prev = node2;
                this.tail.next = createNode;
                this.tail = createNode;
            } else {
                node.next.prev = createNode;
                createNode.next = node.next;
                node.next = createNode;
                createNode.prev = node;
            }
        }
        this.size++;
    }

    /* access modifiers changed from: protected */
    public Object removeNode(Node node) {
        if (this.size == 0) {
            return null;
        }
        Object obj = node.userObject;
        Node node2 = this.head;
        if (node == node2) {
            Node node3 = node2.next;
            this.head = node3;
            if (node3 == null) {
                this.tail = null;
            } else {
                node3.prev = null;
            }
        } else {
            Node node4 = this.tail;
            if (node == node4) {
                Node node5 = node4.prev;
                this.tail = node5;
                node5.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
        node.list = null;
        this.size--;
        return obj;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public void addToHead(Object obj) {
        insertBefore(this.head, obj);
    }

    public void addToTail(Object obj) {
        insertAfter(this.tail, obj);
    }

    public Object removeHead() {
        return removeNode(this.head);
    }

    public Object removeTail() {
        return removeNode(this.tail);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(this.size * 6);
        stringBuffer.append("[");
        Node node = this.head;
        if (node != null) {
            stringBuffer.append(node.userObject);
            node = node.next;
        }
        while (node != null) {
            stringBuffer.append(", ");
            stringBuffer.append(node.userObject);
            node = node.next;
        }
        return stringBuffer.append("]").toString();
    }
}
