package deque;

import java.util.Iterator;
import java.util.prefs.NodeChangeEvent;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node(){
            item = null;
            next = null;
            prev = null;
        }

        public Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentfront;
    private Node sentback;
    private int size;

    public LinkedListDeque(){
        sentfront = new Node();
        sentback = new Node();
        sentfront.next = sentback;
        sentback.prev = sentfront;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentfront.next, sentfront);
        newNode.next.prev = newNode;
        sentfront.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentback, sentback.prev);
        sentback.prev.next = newNode;
        sentback.prev = newNode;
        size++;
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T item = sentfront.next.item;
        sentfront.next = sentfront.next.next;
        sentfront.next.prev = sentfront;
        size--;
        return item;
    }

    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T item = sentback.prev.item;
        sentback.prev = sentback.prev.prev;
        sentback.prev.next = sentback;
        size--;
        return item;
    }

    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        Node current = sentfront.next;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }

    private T getRecursize1(int index, Node current){
        if(index < 0 || index >= size){
            return null;
        }
        if(index == 0){
            return current.item;
        }
        return getRecursize1(index-1, current.next);
    }

    public T getRecursive(int index){
        return getRecursize1(index, sentfront.next);
    }

    @Override
    public void printDeque(){
        Node current = sentfront.next;
        while(current != sentback){
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque other){
            if(this.size() != other.size()){
                return false;
            }

            Node p1 = this.sentfront.next;
            Node p2 = other.sentfront.next;
            while(p1 != this.sentback && p2 != other.sentback){
                if(p1.item != p2.item){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node current;
        public LinkedListIterator(){
            current = sentfront.next;
        }

        @Override
        public boolean hasNext(){
            return current != sentback;
        }

        @Override
        public T next(){
            T ret = current.item;
            current = current.next;
            return ret;
        }
    }

}
