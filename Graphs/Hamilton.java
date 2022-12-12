// Zach Sally
// December 10, 2022

// ==================
// |  Hamilton.java  |
// ==================

// Represents a graph of n (0 <= n <= 26) vertices as an adjacency matrix
// Determines whether the graph has a Hamiltonian Cycle
// Note: I represent nodes 0->n as A->Z
//       Therefore, this implementation can hold a max of 26 nodes to keep naming conventions


import java.util.*;
import java.io.*;

public class Hamilton
{
    boolean [][] matrix;
    int numVertices;

    public static void main(String [] args) throws IOException
    {
        if (args.length < 1)
        {
            System.out.println("Pass in a file in command arguments");
            return;
        }

        Hamilton g1 = new Hamilton(args[0]);
        g1.findCycle();
        // g1.findAllCycles();
    }

    // Constructs an adjacency matrix of a graph from a text file
    public Hamilton(String filename) throws IOException
    {
        Scanner scan = new Scanner(new File(filename));

        numVertices = scan.nextInt();
        
        matrix = new boolean[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < numVertices; j++)
            {
                matrix[i][j] = (scan.nextInt() == 1);
            }
        }
    }

    // Find a hamiltonian cycle in the graph, if there is one
    public void findCycle()
    {
        boolean [] visited = new boolean[numVertices];
        int [] cycle = new int[numVertices];

        // If there is a Hamiltonian Cycle, we can start anywhere, so start at 0 (A)
        cycle[0] = 0;
        visited[0] = true;

        if (findCycle(visited, cycle, 1))
        {
            System.out.println("Found Hamiltonian Cycle:");

            for (int i = 0; i < numVertices; i++)
            {
                System.out.print((char)(cycle[i] + 'A') + " => ");
            }

            System.out.println("A");
        }
        else
        {
            System.out.println("Graph does not have a Hamiltonian Cycle\n");
        }
    }

    private boolean findCycle(boolean [] visited, int [] cycle, int numUsed)
    {
        int v = cycle[numUsed - 1];

        if (numUsed >= numVertices)
            return matrix[v][cycle[0]];

        for (int i = 0; i < numVertices; i++)
        {
            if (matrix[v][i] && !visited[i])
            {
                visited[i] = true;
                cycle[numUsed] = i;

                if (findCycle(visited, cycle, numUsed + 1))
                    return true;

                visited[i] = false;
            }
        }

        return false;
    }
}