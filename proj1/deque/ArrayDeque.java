package deque;

// the front points at the first element
// the rear points at the next available position, but not the last element

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    T[] array;
    int size = 0;
    int front = 0;
    int rear = 0;

    public ArrayDeque(int capacity) {
        array = (T[]) new Object[capacity];
    }

    public ArrayDeque() {
        this(16);
    }

    @Override
    public int size() {
        return size;
    }

    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        array = newArray;
        front = 0;
        rear = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == array.length) resize(array.length * 2);
        front = (front - 1 + array.length) % array.length;
        array[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == array.length) resize(array.length * 2);
        array[rear] = item;
        rear = (rear + 1) % array.length;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        T item = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        if (array.length > 16 && size < array.length / 4) resize(array.length / 2);
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        rear = (rear - 1 + array.length) % array.length;
        T item = array[rear];
        array[rear] = null;
        size--;
        if (array.length > 16 && size < array.length / 4) resize(array.length / 2);
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return array[(front + index) % array.length];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque other){
            if(this.size() != other.size()){
                return false;
            }
            for(int i = 0; i < size;i++){
                if(other.get(i) == this.get(i)){
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int current;

        ArrayDequeIterator(){
            current = front % array.length;
        }
        @Override
        public boolean hasNext() {
            return (current + 1) % array.length != rear;
        }

        @Override
        public T next() {
            T result = get(current);
            current = (current + 1) % array.length;
            return result;
        }
    }

}
