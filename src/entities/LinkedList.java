// Classe LinkedList
package entities;

public class LinkedList {
    private static class Node {
        private final int ELEMENT;
        private Node next;

        private Node(int ELEMENT) {
            this.ELEMENT = ELEMENT;
            next = null;
        }
    }

    private Node tail;
    private int elements;

    public LinkedList() {
        tail = null;
        elements = 0;
    }

    public boolean isEmpty() {
        return elements == 0;
    }

    public void insertAtEnd(int element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            tail = newNode;
        } else {
            Node current = tail;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        elements++;
    }

    public void print() {
        Node current = tail;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.ELEMENT);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}