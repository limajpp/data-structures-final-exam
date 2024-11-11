package debugging;

import entities.LinkedList;

public class DebuggingJP {
    public static void main(String[] args) {
        try {
            LinkedList linkedList = new LinkedList();

            System.out.println(linkedList);

            linkedList.insertAtStart(1);
            linkedList.insertAtStart(2);
            linkedList.insertAtStart(3);
            linkedList.insertAtStart(4);

            System.out.println(linkedList);

            linkedList.removeAtStart();
            linkedList.removeAtStart();

            System.out.println(linkedList);

            linkedList.insertAtEnd(1);
            linkedList.insertAtEnd(2);
            linkedList.insertAtEnd(3);

            System.out.println(linkedList);

            linkedList.removeAtEnd();
            linkedList.removeAtEnd();

            System.out.println(linkedList);

            System.out.println(linkedList.contains(1) + "\n");

            linkedList.insertAtIndex(1, 4);

            System.out.println(linkedList);

            linkedList.removeAtIndex(1);

            System.out.println(linkedList);

            linkedList.removeAtIndex(0);

            System.out.println(linkedList);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {

        }
    }
}
