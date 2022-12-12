class Node
{
    int data;
    Node next;

    Node(int data)
    {
        this.data = data;
    }
}

public class LinkedList
{
    private Node head, tail;

    public void headInsert(int data)
    {
        Node newNode = new Node(data);
        newNode.next = head;

        if (tail == null)
        {
            tail = newNode;
        }
    }

    public void tailInsert(int data)
    {
        Node newNode = new Node(data);

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

    public Integer deleteHead()
    {
        int retval;

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

    public Integer deleteTail()
    {
        int retval;
        Node temp;

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

        for (temp = head; temp != null; temp = temp->next)
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
        for (Node temp = head; temp != null; temp = temp.next)
        {
            System.out.print(temp.data + ((temp.next == null) ? "\n" : " --> "));
        }
    }
}