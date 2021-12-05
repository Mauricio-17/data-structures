package Stack;

public class Main {

    Node tope;
    public Main(){
        this.tope = null;
    }
    public void push(String data){
        Node newNode = new Node(data);
        newNode.setLink(this.tope);
        this.tope = newNode;
    }
    public void pop(){
        Node temp = this.tope;
        this.tope = this.tope.getLink();
        temp.setLink(null);
    }
    public Node top(){
        return this.tope;
    }
    public void clear(){
        this.tope = null;
    }
    public boolean isEmpty(){
        return this.tope == null;
    }
    public Node getNode(String data){
        Node temp = this.tope;
        while (temp != null && !data.equalsIgnoreCase(temp.getData()))
            temp = temp.getLink();
        return temp;
    }
    public void update(String prevData, String posData){
        Node temp = this.getNode(prevData);
        if (temp != null) temp.setData(posData);
    }
    public void print(){
        Node temp = this.tope;
        while(temp != null){
            System.out.print(temp.getData() + "-");
            temp = temp.getLink();
        }
        System.out.println("\n-----------------");
    }

    public static void main(String[] args) {
        Main pile = new Main();
        pile.push("Linux");
        pile.push("Ubuntu");
        pile.push("CLI");
        pile.push("APT");
        pile.print();
        pile.pop();
        pile.print();
        pile.update("ubuntu", "Fedora");
        pile.print();
    }
}
