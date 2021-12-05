package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;

// Developing a non-directed graph
public class MyGraph {
    public ArrayList<Character> charList;
    public ArrayList<HashMap<Character, Integer>> adjacencyList;
    public int[][] adjacencyMatrix ;

    public  MyGraph(){
        this.charList = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();
        this.adjacencyMatrix = new int[0][0];
    }

    public void addVertex(char ch){
        this.charList.add(ch);
        this.adjacencyList.add(new HashMap<>());
        this.increaseMatrix();
    }
    public void increaseMatrix(){
        int[][] temp = this.adjacencyMatrix;
        int newSize = this.charList.size();
        this.adjacencyMatrix = new int[newSize][newSize];
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, this.adjacencyMatrix[i], 0, temp.length);
        }
    }
    public void addEdge(char i, char j, int weight){
        if(i != j){
            // searching the index of each node
            int indexI = charList.indexOf(i);
            int indexJ = charList.indexOf(j);
            if(indexI != -1 && indexJ != -1){
                // ADJACENCY LIST
                // if both nodes exist, adds each other to its own list
                adjacencyList.get(indexI).put(j, weight);
                adjacencyList.get(indexJ).put(i, weight);
                // ADJACENCY MATRIX
                this.adjacencyMatrix[indexI][indexJ] = weight;
                this.adjacencyMatrix[indexJ][indexI] = weight;
            }
            else System.out.println("Uno de los nodos no existe!!");
        }
    }
    public void updateMatrix(int indexDel){
        int newSize = this.charList.size();
        int[][] temp = new int[newSize][newSize];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                // if any of both indexes reaches to the deleted index
                if (j >= indexDel || i >= indexDel) {
                    // putting the elements at the new matrix at its correct index (i,j)
                    if(j >= indexDel && i >= indexDel)
                        temp[i][j] = this.adjacencyMatrix[i + 1][j + 1];
                    else if(j >= indexDel)
                        temp[i][j] = this.adjacencyMatrix[i][j+1];
                    else
                        temp[i][j] = this.adjacencyMatrix[i+1][j];
                }
                // if none of the indexes reaches it yet
                else
                    temp[i][j] = this.adjacencyMatrix[i][j];
            }
        }
        this.adjacencyMatrix = temp;
    }
    public void removeVertex(char ch){
        System.out.println("Removing "+ch+"...");
        int index = this.charList.indexOf(ch);
        if (index != -1){
            this.charList.remove(index);
            // removing from adjacencyList
            this.adjacencyList.remove(index);
            for (HashMap<Character, Integer> characterIntegerHashMap : this.adjacencyList)
                characterIntegerHashMap.remove(ch);
            // removing from adjacencyMatrix
            this.updateMatrix(index);
        }
        else System.out.println("El vértice no existe!");
    }
    public void printAdjacencyList(){
        for (int i = 0; i < charList.size(); i++) {
            char ch = charList.get(i);
            System.out.print("Node " + ch + " --> ");
            // prints the list of adjacent nodes and its value
            for (char node: this.adjacencyList.get(i).keySet()) //keySet() return a set of keys, on the other hand,
                                                                // values() the values
                System.out.print(node+ "(" + this.adjacencyList.get(i).get(node) + ") --> ");
            System.out.println();
        }
    }
    public void printAdjacencyMatrix(){
        for (int[] vector: this.adjacencyMatrix) {
            for (int i: vector)
                System.out.print(i+"-");
            System.out.println();
        }
    }

    public void BFS(char u){
        // LOCAL VALUES AND DATA
        int currentSize = this.charList.size();
        boolean[] visited = new boolean[currentSize];
        LinkedList<Character> queue = new LinkedList<>();
        int currentIndex = this.charList.indexOf(u);
        // if the initial node exists at the charList of nodes
        if (currentIndex != -1) {
            // mark the current or initial node as a visited and add to the dynamic list
            visited[currentIndex] = true;
            queue.add(u);
            // Begin the total traversal
            while(queue.size() != 0){
                // Dequeue the head vertex from the list and print it
                u = queue.poll();
                System.out.print(u+" ");
                currentIndex = this.charList.indexOf(u); // obtaining the current index of traversing
                /* traversal all the adjacent nodes from the dequeued vertex using Iterator for better handling */
                Iterator<Character> adjVertexes = this.adjacencyList.get(currentIndex).keySet().iterator();
                while(adjVertexes.hasNext()){
                    char currentAdj = adjVertexes.next();// adjacent vertex
                    currentIndex = this.charList.indexOf(currentAdj);
                    // if the adjacent vertex isn't marked yet, will be marked and added to the dynamic list
                    if(!visited[currentIndex]){
                        visited[currentIndex] = true;
                        queue.add(currentAdj);
                    }
                }
            }
        }
    }

    public void DFS(char u){
        int currentSize = this.charList.size();
        boolean[] visited = new boolean[currentSize];
        // using the helper function for DFS recursion
        int currentIndex = this.charList.indexOf(u);
        if (currentIndex != -1)
            this.DFSUtil(u, visited);
    }
    // a function used by BFS for traversal recursion
    public void DFSUtil(char u, boolean[] visited){
        int currentIndex = this.charList.indexOf(u);
        visited[currentIndex] = true; // mark the current vertex as a visited
        System.out.print(u+" ");
        // Recursion for all adjacent nodes from this vertex, beginning from the first adjacent unmarked
        Iterator<Character> adjVertexes = this.adjacencyList.get(currentIndex).keySet().iterator();
        while(adjVertexes.hasNext()){
            //obtaining its adjacent nodes and verifying for traversing
            char currentAdj = adjVertexes.next();
            currentIndex = this.charList.indexOf(currentAdj);
            if(!visited[currentIndex]){
                // traversing by the first unmarked vertex
                this.DFSUtil(currentAdj, visited);
            }
        }
    }

    public static void main(String[] args) {
        MyGraph list = new MyGraph();
        list.addVertex('1');
        list.addVertex('2');
        list.addVertex('3');
        list.addVertex('4');
        list.addVertex('5');
        list.addVertex('6');
        list.addVertex('7');

        list.addEdge('1', '2', 1);
        list.addEdge('1', '3', 1);
        list.addEdge('2', '4', 1);
        list.addEdge('2', '5', 1);
        list.addEdge('3', '6', 1);
        list.addEdge('3', '7', 1);

        /*
        list.printAdjacencyList();
        list.printAdjacencyMatrix();
        System.out.println();
        list.removeVertex('2');
        */

        list.printAdjacencyList();
        list.printAdjacencyMatrix();

        System.out.println("BFS Traversal");
        list.BFS('1');
        System.out.println();
        System.out.println("DFS Traversal");
        list.DFS('1');
    }
}
