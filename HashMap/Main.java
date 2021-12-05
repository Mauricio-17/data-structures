package HashMap;

import java.util.HashMap;

public class Main {
    HashMap<Integer, Node> hashMap;
    public static int counter = 0;
    public Main(){
        this.hashMap = new HashMap<Integer, Node>();
    }
    public void add(String name, int age){
        this.hashMap.put(Main.counter, new Node(name, age));
        Main.counter ++;
    }
    public Node getNodeByIndex(int index){
        return this.hashMap.get(index);
    }
    public void Delete(int index){
        this.hashMap.remove(index);
    }
    public void print(){
        for (int index: this.hashMap.keySet()) { // values() for values
            System.out.println(this.hashMap.get(index).name);
        }
    }
    public static void main(String[] args) {
        Main hashMap = new Main();
        hashMap.add("alvaro", 23);
        hashMap.add("felipe", 29);
        hashMap.add("ferdinand", 43);
        hashMap.print();
    }
}
