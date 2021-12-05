package DoubleLinkedList;

public class Main {
    Node head, end; //head es la lista principal y end es la lista invertida

    public Main() {
        this.head = null;
    }

    public void insertBegin(String data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        // the first conditional only executes once
        if (this.head == null) {
            this.end = newNode;
            this.end.next = null;
        }
        if (this.head != null)
            this.head.prev = newNode;

        this.head = newNode;
    }

    // Adding a node at the front of the list
    public void push(String new_data) {
        /* 1. allocate node
         * 2. put in the data */
        Node new_Node = new Node(new_data);

        /* 3. Make next of new node as head and previous as NULL */
        new_Node.next = head;
        new_Node.prev = null;

        /* 4. change prev of head node to new node */
        if (head != null)
            head.prev = new_Node;

        /* 5. move the head to point to the new node */
        head = new_Node;
    }

    public Node getNode(String data) {
        Node temp = this.head;
        if (temp.data.equalsIgnoreCase(data)) return temp; // if it's the first element
        while (temp != null && !temp.data.equalsIgnoreCase(data))
            temp = temp.next;
        return temp;
    }

    public void update(String prevData, String posData) {
        Node updated = this.getNode(prevData);
        if (updated != null) updated.data = posData;
    }

    public void delete(String data) {
        Node temp = this.getNode(data);
        if (temp != null) {
            if (temp == this.head) {
                this.head = temp.next; // IF it deletes the first
                if (temp.next != null) temp.next.prev = null;
            } else if (temp.next != null) { // Isn't the last
                temp.prev.next = temp.next;
                temp.next.prev = (temp.prev);
            } else {
                temp.prev.next = null;
                this.end = temp.prev;
            }
        }
    }

    public void printForward(boolean yes) {
        Node temp;
        if (yes) {
            temp = this.head;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        } else {
            temp = this.end;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.prev;
            }
        }
    }

    public static void main(String[] args) {
        Main ddl = new Main();
        ddl.insertBegin("uno");
        ddl.insertBegin("DOS");
        ddl.insertBegin("Tree");
        ddl.insertBegin("quad");
        ddl.printForward(true);
        System.out.println("-------------");
        ddl.update("DOS", "doce");
        ddl.printForward(false);
        System.out.println("-------------");
        ddl.delete("Tree");
        ddl.printForward(false);
        System.out.println("-------------");
        ddl.insertBegin("penta");
        ddl.printForward(true);
        System.out.println("First: "+ddl.head.next.prev.data + " - Last: "+ddl.end.prev.next.data);
    }
}
