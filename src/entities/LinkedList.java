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

    public void insertAtStart(int element) {
        Node node = new Node(element);

        if (isEmpty()) {
            tail = node;
        } else {
            node.next = tail;
            tail = node;
        }

        elements++;
    }

    public void removeAtStart() {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty.");

        tail = tail.next;
        elements--;
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

    public void removeAtEnd() {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty.");

        if (tail.next == null) {  // If there is only one element
            tail = null;
        } else {
            Node current = tail;
            while (current.next != null && current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        elements--;
    }

    // Created for debugging...
    @Override
    public String toString() {
        if (isEmpty()) return "Tail - null\nelements: " + elements + "\n";

        StringBuilder linkedListString = new StringBuilder("Tail - ");
        Node current = tail;

        while (current != null) {
            linkedListString.append(current.ELEMENT).append(" -> ");
            current = current.next;
        }

        linkedListString.append("Head\n").append("elements: ").append(elements).append("\n");
        return linkedListString.toString();
    }
}
