package deque;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayNoResizing<Integer> noResizing = new ArrayNoResizing<>();

        int N = 500000;
        for(int i=0; i<N; i++){
            int operationNumber = StdRandom.uniform(0, 6);
            if(operationNumber == 0){
                assertEquals(deque.size(), noResizing.size());
            } else if(operationNumber == 1){
                int num = StdRandom.uniform(0, N);
                deque.addFirst(num);
                noResizing.addFirst(num);
                assertEquals(deque.size(), noResizing.size());
            } else if(operationNumber == 2){
                Integer correct = noResizing.removeFirst();
                Integer test = deque.removeFirst();
                if(!Objects.equals(correct, test)){
                    noResizing.print();
                    System.out.println("-------");
                    deque.printDeque();
                }
                assertEquals(correct, test);
            } else if(operationNumber == 3){
                int num = StdRandom.uniform(0, N);
                deque.addLast(num);
                noResizing.addLast(num);
                assertEquals(deque.size(), noResizing.size());
            } else if(operationNumber == 4){
                Integer correct = noResizing.removeLast();
                Integer test = deque.removeLast();
                assertEquals(correct, test);
            } else if(operationNumber == 5){
                if(noResizing.isEmpty()){
                    continue;
                }
                int num = StdRandom.uniform(0, noResizing.size());
                Integer correct = noResizing.get(num);
                Integer test = deque.get(num);
                if(!Objects.equals(correct, test)){
                    noResizing.print();
                    System.out.println("-------");
                    deque.printDeque();
                }
                assertEquals(correct, test);
            }
        }
    }

}
