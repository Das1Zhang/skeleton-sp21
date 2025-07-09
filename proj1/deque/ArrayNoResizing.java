package deque;

public class ArrayNoResizing<T> {
    T[] array = (T[]) new Object[1000000];
    int size = 0;

    public void addFirst(T item){
        for(int i = size;i > 0;i--){
            array[i] = array[i-1];
        }
        array[0] = item;
        size++;
    }

    public void addLast(T item){
        array[size] = item;
        size++;
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T item = array[0];
        for(int i = 1;i < size;i++){
            array[i-1] = array[i];
        }
        size--;
        return item;
    }

    public void print(){
        for(int i = 0;i < size;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T item = array[size-1];
        size--;
        return item;
    }

    public T get(int index){
        return array[index];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


}
