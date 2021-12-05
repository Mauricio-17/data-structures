package Graph;

import java.util.ArrayList;

public class AdjacencyMatrix {
    ArrayList<Character> list;
    int[][] adjacencyMatrix;
    public AdjacencyMatrix(){
        this.list = new ArrayList<Character>();
        this.adjacencyMatrix = new int[list.size()][list.size()];
    }

    public void updateMatrix(){
        int[][] tempMatrix = this.adjacencyMatrix;

    }
    public void addVertex(char ch){
        this.list.add(ch);
    }
    public void removeVertex(char ch){
        int index = this.list.indexOf(ch);
        char delChar;
        if(index != -1)
            delChar = this.list.remove(index);

    }

}
