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

    public AnyType deleteTail()
    {
        AnyType retval;
        Node<AnyType> temp;

        if (tail == null)
        {
            return null;
        }

        retval = tail.data;

        if (head == tail)
        {
            head = tail = null;
            return retval;
        }

        for (temp = head; temp != null; temp = temp.next)
        {
            if (temp.next == tail)
            {
                tail = temp;
                break;
            }
        }

        return retval;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

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
}