package entities;

public class BinarySearchTree {
    class Node {
        public Word element;
        public Node right;
        public Node left;

        public Node(Word element) {
            this.right = null;
            this.left = null;
            this.element = element;
        }
    }

    public Node root;
    public int nElements;

    public BinarySearchTree() {
        this.root = null;
        this.nElements = 0;
    }

    public void insert(Word element, int line){
        this.root = insert(element, this.root, line);
    }

    private Node insert(Word element, Node node, int line) {
        if (node == null) {
            element.getIndex().insertAtEnd(line);  // Adiciona a linha na lista de índices da nova palavra
            nElements++;
            return new Node(element);
        }

        int comparison = element.getWord().compareTo(node.element.getWord());

        if (comparison < 0) {
            node.left = insert(element, node.left, line);
        } else if (comparison > 0) {
            node.right = insert(element, node.right, line);
        } else {
            node.element.getIndex().insertAtEnd(line);  // Palavra já existe, adiciona linha à lista de ocorrências
        }

        return node;
    }

    public void print() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.element); // Imprime apenas node.element, que já inclui a lista de índices
            printInOrder(node.right);
        }
    }
    public Word search(Word element) {
        return this.search(element, this.root);
    }

    private Word search(Word element, Node node) {
        if (node == null) {
            return null;
        }
        int comparison = element.getWord().compareTo(node.element.getWord());
        if (comparison < 0) {
            return this.search(element, node.left);
        } else if (comparison > 0) {
            return this.search(element, node.right);
        } else {
            return node.element;  // Palavra encontrada
        }
    }

}
