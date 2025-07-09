package deque;

import java.util.Comparator;

public class MaxArrayDeque <T> extends ArrayDeque<T> {
    public final Comparator<T> defaultComparator;

    public MaxArrayDeque(Comparator<T> c){
        super();
        this.defaultComparator = c;
    }

    public T max(){
        if(isEmpty()){
            return null;
        }
        T maxItem = get(0);
        for(int i = 1; i < this.size(); i++){
            T current = this.get(i);
            if(defaultComparator.compare(current, maxItem) > 0){
                maxItem = current;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T maxItem = get(0);
        for(int i=1; i<size(); i++){
            T current = get(i);
            if(c.compare(current, maxItem) > 0){
                maxItem = current;
            }
        }
        return maxItem;
    }


    public class MaxComparator implements Comparator<T>{
        @Override
        public int compare(T o1, T o2) {
            return defaultComparator.compare(o1, o2);
        }
    }
}
