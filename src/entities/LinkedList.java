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

    private Node head;
    private int elements;

    public LinkedList() {
        head = null;
        elements = 0;
    }

    public boolean isEmpty() {
        return elements == 0;
    }

    public void insertAtEnd(int element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        elements++;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            result.append(current.ELEMENT);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }
}
