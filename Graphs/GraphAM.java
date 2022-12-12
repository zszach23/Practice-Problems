// Zach Sally
// October 28, 2022

// ==================
// |  GraphAM.java  |
// ==================

// Represents a graph of n (0 <= n <= 26) vertices as an adjacency matrix
// Supports BFS and DFS operations
// Note: I represent nodes 0->n as A->Z
//       Therefore, this implementation can hold a max of 26 nodes to keep naming conventions

import java.util.*;
import java.io.*;

public class GraphAM
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

        GraphAM g1 = new GraphAM(args[0]);
        g1.printGraph();

        System.out.println("Bipartite: " + g1.isBipartite() + "\n");
        System.out.println("Connected Components: " + g1.countConnectedComponents() + "\n");
        g1.topoSort();

        g1.BFS(0);
        g1.recursiveDFS(0);
        g1.iterativeDFS(0);
    }

    // Constructs an adjacency matrix of a graph from a text file
    public GraphAM(String filename) throws IOException
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

    // Determines if a given graph is bipartite
    // A graph is bipartite if it has at least one node and is 2-colorable
    // Utilizes BFS to check adjacent node colors
    public boolean isBipartite()
    {
        // Need at least one vertex to be bipartite
        if (numVertices < 1)
            return false;

        Queue<Integer> nodes = new ArrayDeque<>();
        int [] color = new int[numVertices];

        // Initialize queue
        nodes.add(0);
        color[0] = 1;

        // Loop until we've seen all nodes
        while (!nodes.isEmpty())
        {
            // Process the current node
            int node = nodes.remove();
            
            // Loop through its adjacent nodes
            for (int i = 0; i < numVertices; i++)
            {
                if (matrix[node][i])
                {
                    // If there's an edge that has the same color as our current node,
                    // then it is not 2-colorable, and therefore is not bipartite
                    if (color[i] == color[node])
                        return false;

                    // If we haven't colored this adjacent node, color it opposite add it to queue
                    if (color[i] == 0)
                    {
                        color[i] = (color[node] == 1 ? 2 : 1);
                        nodes.add(i);
                    }
                }
            }
        }

        // No two adjacent nodes have the same color, therefore it is bipartite
        return true;
    }

    // Counts the number of connected components in a graph
    public int countConnectedComponents()
    {
        boolean [] visited = new boolean[numVertices];
        int count = 0;

        // Loop through all vertices of the graph
        for (int i = 0; i < numVertices; i++)
        {
            // If we haven't visited the node, we have found a new connected component
            // DFS through the component until we traverse through every connected node of the component
            if (!visited[i])
            {
                count++;
                CC_DFS(i, visited);
            }
        }

        return count;
    }

    // Performs DFS traversal to mark nodes as visited
    // Called by countConnectedComponents() to mark all connected nodes in a graph
    private void CC_DFS(int node, boolean [] visited)
    {
        visited[node] = true;

        for (int i = 0; i < numVertices; i++)
        {
            if (matrix[node][i] && !visited[i])
                CC_DFS(i, visited);
        }
    }

    // Implementation of Topological Sort on the graph
    // This method will check if there are any cycles in the graph
    public void topoSort()
    {
        int [] edges = new int[numVertices];
        Queue<Integer> nodes = new ArrayDeque<>();
        int count = 0;

        System.out.println("Topological Sort:");

        // Mark the number of incoming edges for each vertex
        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < numVertices; j++)
            {
                if (matrix[i][j])
                {
                    edges[j]++;
                }
            }
        }

        // Put all vertices with 0 incoming edges into queue
        for (int i = 0; i < numVertices; i++)
        {
            if (edges[i] == 0)
                nodes.add(i);
        }

        while (!nodes.isEmpty())
        {
            // Keep track of the number of unique vertices we see
            count++;

            // Process current node
            int node = nodes.remove();
            System.out.print((char)(node + 'A') + " -> ");

            // Loop through the each adjacent vertex, and add the vertex if it reaches 0
            for (int i = 0; i < numVertices; i++)
            {
                if (matrix[node][i])
                {
                    edges[i]--;
                    if (edges[i] == 0)
                        nodes.add(i);
                }
            }
        }

        System.out.println(count != numVertices ? "There is a cycle in the graph\n" : "END\n");
    }

    // Performs BFS traversal using a queue
    // Start traversal at given start index (0 -> numVertices - 1)
    public void BFS(int start)
    {
        if (start < 0 || start >= numVertices)
        {
            System.out.println("Invalid Start Vertex");
            return;
        }

        System.out.println("BFS:");

        Queue<Integer> nodes = new LinkedList<Integer>();
        boolean [] visited = new boolean[numVertices];

        // Add start to the queue and mark as visited
        nodes.add(start);
        visited[start] = true;

        // Loop until we process every node
        while (!nodes.isEmpty())
        {
            // Remove the first node from the queue and print it
            int node = nodes.remove();
            System.out.print((char)(node + 'A') + " -> ");

            // Process all of this node's adjacent vertices that we haven't visited yet
            for (int i = 0; i < numVertices; i++)
            {
                if (matrix[node][i] && !visited[i])
                {
                    visited[i] = true;
                    nodes.add(i);
                }
            }
        }

        System.out.println("END\n");
    }

    // Gateway to perform recursive DFS
    public void recursiveDFS(int start)
    {
        if (start < 0 || start >= numVertices)
        {
            System.out.println("Invalid Start Vertex");
            return;
        }

        System.out.println("Recursive DFS:");

        boolean [] visited = new boolean[numVertices];

        dfs(visited, start);
        System.out.println("END\n");
    }

    // Performs DFS traversal using recursion
    private void dfs(boolean [] visited, int node)
    {
        // Don't want to process current node again, so immediately mark it as visited
        visited[node] = true;

        // Print out the current node
        System.out.print((char)(node + 'A') + " -> ");

        // Go through each of the nodes adjacent to the current node that we haven't visited
        for (int i = 0; i < numVertices; i++)
        {
            if (matrix[node][i] && !visited[i])
                dfs(visited, i);
        }
    }

    // Performs DFS traversal iteratively using a stack
    // DFS starts at the given start node (0 -> numVertices)
    public void iterativeDFS(int start)
    {
        if (start < 0 || start >= numVertices)
        {
            System.out.println("Invalid Start Vertex");
            return;
        }
        System.out.println("Iterative DFS:");

        Stack<Integer> nodes = new Stack<Integer>();
        boolean [] visited = new boolean[numVertices];

        // Push the starting node onto stack and mark it as visited
        nodes.push(start);
        visited[start] = true;

        // Continue until we processed all nodes
        while (!nodes.isEmpty())
        {
            // Pop the top of the stack and print it out
            int node = nodes.pop();
            System.out.print((char)(node + 'A') + " -> ");

            // Push adjacent nodes that haven't been visited onto the stack
            for (int i = 0; i < numVertices; i++)
            {
                if (matrix[node][i] && !visited[i])
                {
                    visited[i] = true;
                    nodes.push(i);
                }
            }
        }
        System.out.println("END\n");
    }

    // Print the adjacency matrix representing the graph
    public void printGraph()
    {
        System.out.println("\nGraph: \n");

        // Print label row
        System.out.print("   ");
        for (int i = 0; i < numVertices; i++)
        {
            System.out.print((char)(i + 'A') + " ");
        }
        System.out.println();

        // Print lines
        System.out.print("  ");
        for (int i = 0; i < numVertices * 2; i++)
        {
            System.out.print("_");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++)
        {
            // Print the node and its following adjacency to other nodes
            System.out.print((char)(i + 'A') + "| ");
            for (int j = 0; j < numVertices; j++)
            {
                System.out.print((matrix[i][j] ? '1' : '0')+ " ");
            }
            System.out.println();
        }

        System.out.println("\n");
    }
}