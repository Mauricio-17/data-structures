package Tree;

public class Main {
    Node root;

    public Main() {
        root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void Add(String name) {
        Node aux = null, back = null;
        Node nuevo = new Node(name);
        if (root == null) {
            root = nuevo;
        } else {
            aux = root;
            while (aux != null) {
                back = aux;
                if (name.compareTo(aux.getName()) < 0) {
                    aux = aux.getLeft();
                } else {
                    aux = aux.getRight();
                }
            }
            if (name.compareTo(back.getName()) < 0) { //If getName > name is 1,2... but they're equal it'll be 0
                back.setLeft(nuevo);
            } else {
                back.setRight(nuevo);
            }
        }
    }
    public Node insertRecursive(Node temp, String name) {
        Node nuevo = new Node(name);
        if (temp == null) {
            temp = nuevo;
        } else {
            if (temp.getName().compareTo(name) < 0) // compareTo() determines if a string value is lexicographically
                // grater or less than the another one by ASCII format
                temp.setLeft(this.insertRecursive(temp.getLeft(), name));// setLeft(getLeft()): si getLeft() == null
                // este sera el nuevo nodo u hoja
            else
                temp.setRight(this.insertRecursive(temp.getRight(), name));
        }
        return temp;
    }// INDETERMINADO
    public Node getMin(Node temp) {
        Node aux = temp;
        while (aux != null && aux.getLeft() != null)
            aux = aux.getLeft();
        return aux;
    }

    public Node deleteMajorLeft(Node auxiliar) {
        if (auxiliar == null) return null;
        else if (auxiliar.getRight() != null) {
            auxiliar.setRight(this.deleteMajorLeft(auxiliar.getRight()));
            return auxiliar;
        }
        return auxiliar.getLeft();
    }

    public Node searchMajorLeft(Node aux) {
        if (aux != null) {
            while (aux.getRight() != null)
                aux = aux.getRight();
        }
        return aux;
    }

    public Node delete(Node aux, String data) {
        if (aux == null) return null;
        if (data.compareTo(aux.getName()) < 0)
            aux.setLeft(this.delete(aux.getLeft(), data));
        else if (data.compareTo(aux.getName()) > 0)
            aux.setRight(this.delete(aux.getRight(), data));
        else if (aux.getLeft() != null && aux.getRight() != null) { // Se encuentra el nodo
            aux.setName(this.searchMajorLeft(aux.getLeft()).getName());  //
            aux.setLeft(this.deleteMajorLeft(aux.getLeft()));
        } else {
            aux = (aux.getLeft() != null) ?
                    aux.getLeft() : aux.getRight();
        }
        return aux;
    }



    public Node Search(String name) {
        Node temp = root;
        if (!name.equals("")) {
            while (temp != null) {
                if (temp.getName().equalsIgnoreCase(name)) return temp;
                else {
                    if (name.compareTo(temp.getName()) < 0)
                        temp = temp.getLeft();
                    else
                        temp = temp.getRight();
                }
            }
            if (temp == null) System.out.println("Name not found");
            else return temp;
        } else System.out.println("Name is void");
        return null;
    }

    public void Update(String pre_name, String pos_name) {
        Node temp = Search(pre_name);
        if (temp != null) temp.setName(pos_name);
    }

    public void showInOrder(Node temp) {
        if (temp != null) {
            showInOrder(temp.getLeft());
            System.out.print(temp.getName() + "-");
            showInOrder(temp.getRight());
        }
    }

    public void showPreOrder(Node temp) {
        if (temp != null) {
            System.out.print(temp.getName() + "-");
            this.showPreOrder(temp.getLeft());
            this.showPreOrder(temp.getRight());
        }
    }

    public void showPostOrder(Node temp) {
        if (temp != null) {
            this.showPreOrder(temp.getLeft());
            this.showPreOrder(temp.getRight());
            System.out.println(temp.getName() + "-");
        }
    }

    public void Delete(Node xroot, String name) {
        if (!name.equals("")) {
            if (xroot == null) System.out.println("Tree of binary search not found");
            else if (xroot.getName().compareTo(name) < 0) Delete(xroot.getLeft(), name);
            else if (xroot.getName().compareTo(name) > 0) Delete(xroot.getRight(), name);
            else { // Node found
                //Node t=xroot; unnecessary
                // the node to delete it links if one branch is void
                if (xroot.getLeft() == null) xroot = xroot.getRight();
                else if (xroot.getRight() == null) xroot = xroot.getLeft();
                else { // Has two branches
                    Node dr = xroot.getLeft();
                    Node anter = null;
                    //t=xroot;
                    while (dr.getRight() != null) {
                        anter = dr;
                        dr = dr.getRight();
                    }
                    //  replace the data of the major of the minority
                    xroot.setName(dr.getName());
                    if (anter == null) xroot.setLeft(dr.getLeft());
                        //else if (anter.getLeft()==dr) anter.setLeft(dr.getLeft());
                    else if (anter.getRight() == dr) anter.setRight(dr.getLeft());
                }
                //t=null;
            }
        } else
            System.out.println("Name is void");

    }


    public static void main(String[] args) {

        Main obj = new Main();
/*
        obj.insertRecursive(obj.getRoot(), "A");
        obj.insertRecursive(obj.getRoot(), "B");
        obj.insertRecursive(obj.getRoot(), "C");
        obj.insertRecursive(obj.getRoot(), "D");
        ///*
*/
        obj.Add("D");
        obj.Add("A");
        obj.Add("C");
        obj.Add("G");
        obj.Add("E");
        obj.Add("B");
        obj.Add("F");
        obj.Add("H");

        obj.showInOrder(obj.getRoot());
        System.out.println();

        obj.setRoot(obj.delete(obj.getRoot(), "A"));
        obj.showInOrder(obj.getRoot());
        System.out.println();
        obj.Update("F", "L");
        obj.showInOrder(obj.getRoot());
        System.out.println("\n" + obj.getRoot().getLeft().getName());
        System.out.println("A".compareTo("B"));

    }
}

