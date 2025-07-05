package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        correct.addLast(4);
        correct.addLast(5);
        correct.addLast(6);

        buggy.addLast(4);
        buggy.addLast(5);
        buggy.addLast(6);

        assertEquals(correct.size(), buggy.size());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedFunctionCalls(){
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }

    @Test
    public void randomizedFunctionCalls1(){
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for(int i = 0; i < N; i += 1){
            int operationNumber = StdRandom.uniform(0,3);
            if(operationNumber == 0){
                int randVal = StdRandom.uniform(0,100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if(operationNumber == 1){
                int size = L.size();
                if(size == 0){
                    System.out.println("size: " + size + ", can't getLast().");
                    continue;
                }
                int last = L.getLast();
                System.out.println("getLast(): " + last);
            } else if(operationNumber == 2){
                int size = L.size();
                if(size == 0){
                    System.out.println("size: " + size + ", can't removeLast.");
                    continue;
                }
                int last = L.removeLast();
                System.out.println("removeLast(): " + last);
            }
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int N = 50000;
        for(int i = 0; i < N; i += 1){
            int operationNumber = StdRandom.uniform(0, 4);
            if(operationNumber == 0){
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                buggy.addLast(randVal);
                assertEquals(correct.size(), buggy.size());
                System.out.println("addLast(" + randVal + ")");
            } else if(operationNumber == 1){
                int size = buggy.size();
                System.out.println("size: " + size);
            } else if(operationNumber == 2){
                int size = buggy.size();
                if(size == 0){
                    System.out.println("size: " + size + ", can't getLast().");
                    continue;
                }
                int last_correct = correct.getLast();
                int last_buggy = buggy.getLast();
                assertEquals(last_correct, last_buggy);
                System.out.println("getLast(): " + last_correct);
            } else if(operationNumber == 3){
                int size = buggy.size();
                if(size == 0){
                    System.out.println("size: " + size + ", can't removeLast().");
                    continue;
                }
                int last_correct = correct.removeLast();
                int last_buggy = buggy.removeLast();
                assertEquals(last_correct, last_buggy);
                System.out.println("removeLast(): " + last_correct);
            }
        }
    }
}
