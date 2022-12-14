// Zach Sally
// December 14, 2022

// ================
// |  Queue.java  |
// ================

// My implementation of a Queue using LinkedList

import java.util.*;

public class Queue<AnyType extends Comparable<AnyType>>
{
    private LinkedList<AnyType> queue;

    public Queue()
    {
        queue = new LinkedList<>();
    }

    public void enqueue(AnyType data)
    {
        queue.tailInsert(data);
    }

    public AnyType dequeue()
    {
        return queue.deleteHead();
    }

    public AnyType front()
    {
        if (!queue.isEmpty())
            return queue.getHeadData();

        return null;
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public boolean isFull()
    {
        return false;
    }
}