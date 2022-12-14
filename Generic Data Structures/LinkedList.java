// Zach Sally
// December 14, 2022

// =====================
// |  LinkedList.java  |
// =====================

// My implementation of a linked list that can store any Comparable data type

class Node<AnyType>
{
    AnyType data;
    Node<AnyType> next;

    Node(AnyType data)
    {
        this.data = data;
        next = null;
    }
}

// Linked List that can hold any Comparable data type
public class LinkedList<AnyType extends Comparable<AnyType>>
{
    private Node<AnyType> head, tail;

    // Insert data at the head of the list
    public void headInsert(AnyType data)
    {
        Node<AnyType> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;

        if (tail == null)
        {
            tail = newNode;
        }
    }

    // Insert data at the tail of the list
    public void tailInsert(AnyType data)
    {
        Node<AnyType> newNode = new Node<>(data);

        if (head == null)
        {
            head = tail = newNode;
        }
        else
        {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Delete the data held at the head of the list
    public AnyType deleteHead()
    {
        AnyType retval;

        if (head == null)
        {
            return null;
        }

        retval = head.data;
        head = head.next;

        if (head == null)
        {
            tail = null;
        }

        return retval;
    }

    // Delete the data held at the tail of the list
    public AnyType deleteTail()
    {
        AnyType retval;
        Node<AnyType> temp;

        if (tail == null)
        {
            return null;
        }

        retval = tail.data;

        // Head only ever equals tail if there is one element in list
        if (head == tail)
        {
            head = tail = null;
            return retval;
        }

        // Have to loop through list to update tail pointer
        for (temp = head; temp != null; temp = temp.next)
        {
            if (temp.next == tail)
            {
                tail = temp;
                tail.next = null;
                break;
            }
        }

        return retval;
    }

    // Inserts the data into the linked list in sorted order
    // Note: if list not sorted, then this algorithm will find the first place
    //       to insert data in sorted order
    public void sortedInsert(AnyType data)
    {
        if (head == null)
        {
            head = tail = new Node<>(data);
            return;
        }

        // If the head has greater or equal value, then data goes before head via head insert
        if (head.data.compareTo(data) >= 0)
        {
            headInsert(data);
            return;
        }

        Node<AnyType> curr = head;

        // While we haven't reached tail and current data is smaller than given data, update
        while (curr != null && curr.next != null && curr.next.data.compareTo(data) < 0)
        {
            curr = curr.next;
        }

        // Broke because we reached the tail, so tail insertion
        if (curr.next == null)
        {
            tailInsert(data);
            return;
        }

        Node<AnyType> newNode = new Node<>(data);
        newNode.next = curr.next;
        curr.next = newNode;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    // A linked list is never full (unless you somehow run out of memory)
    public boolean isFull()
    {
        return false;
    }

    public void printList()
    {
        for (Node<AnyType> temp = head; temp != null; temp = temp.next)
        {
            System.out.print(temp.data + ((temp.next == null) ? "\n" : " --> "));
        }
    }

    public static void main(String [] args)
    {
        LinkedList<Integer> list = new LinkedList<>();

        // 5 10 5 11 2
        list.headInsert(5);
        list.headInsert(10);
        list.tailInsert(11);
        list.tailInsert(2);
        list.sortedInsert(2);
        
        list.printList();
        System.out.println(list.deleteTail());
        list.printList();
    }
}