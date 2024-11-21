package entities;

// TAD do professor Túlio, modificado apenas a forma de comparação para compareTo.
public class BinarySearchTree {
    private static class Node {
        public Word element;
        public Node right;
        public Node left;

        public Node(Word element) {
            this.right = null;
            this.left = null;
            this.element = element;
        }
    }

    private Node root;
    private int elements;

    public BinarySearchTree() {
        this.root = null;
        this.elements = 0;
    }

    public void insert(Word element, int line) {
        insert(element, this.root, line); // Chama o método privado
    }

    private void insert(Word element, Node node, int line) {
        if (node == null) {
            // Se o nó é null, criamos um novo nó como raiz
            Node newNode = new Node(element);
            element.getOCCURRENCES().insertAtEnd(line); // Adiciona a linha na lista de índices
            elements++;

            if (this.root == null) {
                this.root = newNode; // Caso especial: a árvore estava vazia
            }
            return;
        }

        int comparison = element.getWord().compareTo(node.element.getWord());

        if (comparison < 0) {
            if (node.left == null) {
                node.left = new Node(element); // Cria o novo nó no lado esquerdo
                element.getOCCURRENCES().insertAtEnd(line); // Adiciona a linha na lista de índices
                elements++;
            } else {
                insert(element, node.left, line); // Continua recursão pela esquerda
            }
        } else if (comparison > 0) {
            if (node.right == null) {
                node.right = new Node(element); // Cria o novo nó no lado direito
                element.getOCCURRENCES().insertAtEnd(line); // Adiciona a linha na lista de índices
                elements++;
            } else {
                insert(element, node.right, line); // Continua recursão pela direita
            }
        } else {
            // Palavra já existe, adiciona linha à lista de ocorrências
            node.element.getOCCURRENCES().insertAtEnd(line);
        }
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
