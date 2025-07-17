package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        int size;

        public BSTNode(K key, V value, int size){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.size = size;
        }
    }

    BSTNode root;

    public BSTMap(){
        root = null;
    }

    public BSTMap(K key, V value){
        root = new BSTNode(key, value, 1);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(this.root, key);
    }

    private boolean containsKey(BSTNode r, K key){
        if(r == null){
            return false;
        }
        int cmp = key.compareTo(r.key);
        if(cmp < 0){
            return containsKey(r.left, key);
        } else if(cmp > 0){
            return containsKey(r.right, key);
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        return get(this.root, key);
    }

    private V get(BSTNode r, K key){
        if(r == null){
            return null;
        }
        int cmp =key.compareTo(r.key);
        if(cmp < 0){
            return get(r.left, key);
        } else if(cmp > 0){
            return get(r.right, key);
        } else {
            return r.value;
        }
    }

    @Override
    public int size() {
        return size(this.root);
    }

    private int size(BSTNode r){
        if(r == null){
            return 0;
        }
        return r.size;
    }

    @Override
    public void put(K key, V value) {
        this.root = put(this.root, key ,value);
    }

    private BSTNode put(BSTNode r, K key, V value){

        BSTNode newNode = new BSTNode(key, value,1);
        if(r == null){
            return newNode;
        }
        int cmp = key.compareTo(r.key);
        if(cmp < 0){
            r.left = put(r.left, key, value);
        } else if(cmp > 0){
            r.right = put(r.right, key, value);
        } else {
            r.value = value;
        }
        r.size = 1 + size(r.left) + size(r.right);
        return r;
    }

    @Override
    public Set keySet() {
        Set<K> set = new HashSet<>();
        LinkedList<BSTNode> list = new LinkedList<>();
        list.addLast(root);
        while(!list.isEmpty()){
            BSTNode node = list.removeFirst();
            if(node == null){
                continue;
            }
            list.addLast(node.left);
            list.addLast(node.right);
            set.add(node.key);
        }

        return set;
    }

    public void printInOrder(){
        printInOrder(this.root);
    }

    private void printInOrder(BSTNode r){
        if(r == null){
            return;
        }
        printInOrder(r.left);
        System.out.print(r.key + " ");
        printInOrder(r.right);
    }

    @Override
    public V remove(K key) {
        if(containsKey(key)){
            V value = get(key);
            root = remove(root, key);
            return value;
        }
        return null;
    }

    private BSTNode remove(BSTNode r, K key){
        if(r == null){
            return null;
        }

        int cmp = key.compareTo(r.key);
        if(cmp < 0){
            r.left = remove(r.left, key);
        } else if(cmp > 0){
            r.right = remove(r.right, key);
        } else {
            // 找到了这个节点
            // 两种解决方案
            // 1.左子树最大的替换之
            // 2.右子树最小的替换之
            if(r.left == null){
                return r.right;
            }
            if(r.right == null){
                return r.left;
            }

            BSTNode temp = r;
            r = min(r.right);
            r.right = removeMin(temp.right);
            r.left = temp.left;
        }

        r.size = 1 + size(r.left) + size(r.right);
        return r;
    }
    @Override
    public V remove(K key, V value) {
        if(containsKey(key)){
            V val = get(key);
            if(val == value){
                root = remove(root, key);
                return val;
            }
        }
        return null;
    }

    private BSTNode min(BSTNode r){
        if(r.left == null){
            return r;
        }
        return min(r.left);
    }

    private BSTNode removeMin(BSTNode r){
        if(r == null){
            return null;
        }
        if(r.left == null){
            return r.right;
        }
        r.left = removeMin(r.left);
        r.size = 1 + size(r.left) + size(r.right);
        return r;
    }

    @Override
    public Iterator iterator() throws UnsupportedOperationException{
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        LinkedList<BSTNode> list;

        public BSTIterator(){
            list = new LinkedList<>();
            list.addLast(root);
        }

        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }

        @Override
        public K next() {
            BSTNode node = list.removeFirst();
            list.addLast(node.left);
            list.addLast(node.right);
            return node.key;
        }
    }
}
