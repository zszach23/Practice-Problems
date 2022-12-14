// Zach Sally
// December 14, 2022

// ================
// |  Stack.java  |
// ================

// My implementation of a Stack using LinkedList

import java.util.*;
import java.io.*;

public class Stack<AnyType extends Comparable<AnyType>>
{
    private LinkedList<AnyType> stack;

    public Stack()
    {
        stack = new LinkedList<AnyType>();
    }

    public void push(AnyType data)
    {
        stack.headInsert(data);
    }

    public AnyType pop()
    {
        return stack.deleteHead();
    }

    public AnyType peek()
    {
        if (!stack.isEmpty())
            return stack.getHeadData();

        return null;
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public boolean isFull()
    {
        return false;
    }
}