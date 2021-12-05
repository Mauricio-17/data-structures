package LinkedList;

public class Main {

    Node begin,end;

    public void insert(String name, int age){
        Node newNode = new Node(name, age);
        newNode.next = this.begin;
        begin = newNode;
    }
    /*
    public void insertEnd(String name, int age){
        Node newNode = new Node(name, age);
        if(end == null) end = newNode;
        else end.next = newNode;
        end = newNode;
        end.next = null;
    }
    */
    public Node search(String data){
        Node pos = this.begin;
        while (pos != null && data.equalsIgnoreCase(pos.name))
            pos = pos.next;
        return pos;
    }
    public Node getNode(String data){
        Node temp = this.begin;
        while(temp != null && !temp.name.equalsIgnoreCase(data))
            temp = temp.next;
        return temp;
    }
    public void update (String prevData, String posData){
        Node temp = this.getNode(prevData);
        if (temp != null) temp.name = posData;
    }

    public void remove(String name){
        // Store head node
        Node temp = this.begin, prev = null;
        // If head node itself holds the key to be deleted
        if (temp != null && temp.name.equalsIgnoreCase(name)) {
            this.begin = temp.next; // Changed head
            return;
        }
        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (temp != null && !temp.name.equalsIgnoreCase(name)) {
            prev = temp;
            temp = temp.next;
        }
        // If key was not present in linked list
        if (temp == null)
            return;
        // Unlink the node from linked list
        prev.next = temp.next;
    }

    public void printData(){
        Node aux = this.begin;
        while(aux != null){
            System.out.println(aux.name);
            aux = aux.next;
        }
    }

    public static void main(String[] args) {
        Main linkedList = new Main();
        linkedList.insert("mauricio", 23);
        linkedList.insert("juan", 34);
        linkedList.insert("carlos", 3);
        linkedList.printData();
        System.out.println("..........");
        linkedList.remove("juan");
        linkedList.printData();
        System.out.println("..........");
        linkedList.update("carlos", "alberto");
        linkedList.insert("johan", 32);
        linkedList.printData();
/*
        System.out.println("..........");
        linkedList.remove(new Node( "juan", 34));
        linkedList.printData();
 */
    }
}
