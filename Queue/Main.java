package Queue;
public class Main {
    Node front,end;

    public void Enqueue(String name){
        Node nuevo=new Node(name);
        if (front==null){
            front=nuevo;
        }else{
            end.setLink(nuevo);//end se comporta como front
        }
        end=nuevo;//En el primer uso de esta funcion se enlazan front y end
        end.setLink(null);
    }

    public Node Search(String name){
        Node temp=front;
        if (!name.equals("")){
            while(temp!=null && !temp.getName().equalsIgnoreCase(name))
                temp=temp.getLink();
            if(temp==null) System.out.println("The name isn't exist");
            return temp;
        }
        System.out.println("The name is avoid");
        return null;
    }

    public void Update(String pre_name,String pos_name){
        Node temp=Search(pre_name);
        if (temp!=null) temp.setName(pos_name);
    }
    public void ShowAndSize(){
        Node temp=front;
        int count=0;
        while(temp!=null){
            System.out.print(temp.getName()+" ");
            count++;
            temp=temp.getLink();
        }

        System.out.println(" : " +count+" Activities \n");
    }

    public void Dequeue(){
        Node temp=front;
        front=front.getLink();
        temp.setLink(null);
    }

    public boolean IsEmpty(){
        return front==null;
    }

    public static void main(String[] args) {
        Main obj=new Main();

        obj.Enqueue("task 1");
        obj.Enqueue("task 2");
        obj.Enqueue("task 3");
        obj.Enqueue("task 4");
        ///*
        obj.ShowAndSize();
        obj.Dequeue();
        obj.ShowAndSize();
        obj.Update("task 2", "task 22");
        obj.ShowAndSize();
        System.out.println("Front: "+obj.front.getName()+" - End: "+obj.end.getName());

        /*obj.Update("B","2B");
        obj.ShowAndSize();
        obj.Dequeue();
        obj.ShowAndSize();
        */
    }
}
