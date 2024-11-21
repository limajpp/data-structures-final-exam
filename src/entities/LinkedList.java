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

    public boolean contains(int element) {
        if (isEmpty()) throw new IllegalStateException("LinkedList is empty.");

        Node current = tail;
        while (current != null) {
            if (current.ELEMENT == element) return true;
            current = current.next;
        }

        return false;
    }

    public void insertAtStart(int element) {
        Node node = new Node(element);

        if (!isEmpty()) {
            node.next = tail;
        }
        tail = node;

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

    public void insertAtIndex(int index, int element) {
        if (index < 0 || index > elements) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            insertAtStart(element);
            return;
        }

        Node newNode = new Node(element);
        Node currentNode = tail;

        for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
            currentNode = currentNode.next;
        }

        newNode.next = currentNode.next;
        currentNode.next = newNode;
        elements++;
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= elements) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            removeAtStart();
            return;
        }

        Node currentNode = tail;
        for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
            currentNode = currentNode.next;
        }

        currentNode.next = currentNode.next.next;
        elements--;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = tail;
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
