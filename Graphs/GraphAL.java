// Zach Sally
// October 28, 2022

// ==================
// |  GraphAL.java  |
// ==================

// Represents a graph of n (0 <= n <= 26) vertices as an adjacency list
// Supports BFS and DFS operations
// Note: I represent nodes 0->n as A->Z
//       Therefore, this implementation can hold a max of 26 nodes to keep naming conventions

import java.util.*;
import java.io.*;

public class GraphAL
{
    ArrayList<LinkedList<Integer>> list;
    int numVertices;

    public static void main(String [] args) throws IOException
    {
        if (args.length < 1)
        {
            System.out.println("Pass in a text file through the command line");
            return;
        }

        GraphAL g1 = new GraphAL(args[0]);

        g1.printGraph();

        System.out.println("Bipartite: " + g1.isBipartite() + "\n");
        System.out.println("Connected Components: " + g1.countConnectedComponents() + "\n");
        g1.topoSort();

        g1.BFS(0);
        g1.iterativeDFS(0);
        g1.recursiveDFS(0);
    }

    // Constructs an adjacency list representation of a graph from a text file
    public GraphAL(String filename) throws IOException
    {
        Scanner scan = new Scanner(new File(filename));

        numVertices = scan.nextInt();

        list = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++)
        {
            list.add(new LinkedList<Integer>());

            for (int j = 0; j < numVertices; j++)
            {
                int edge = scan.nextInt();
                if (edge == 1)
                    list.get(i).add(j);
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
        
        int [] color = new int[numVertices];
        
        // Loop through all vertices, as the graph may be disconnected
        for (int start = 0; start < numVertices; start++)
        {
            // Visited node already
            if (color[start] != 0)
                continue;
            
            Queue<Integer> nodes = new ArrayDeque<>();
            color[start] = 1;
            nodes.add(start);

            while (!nodes.isEmpty())
            {
                int node = nodes.remove();

                for (int i = 0; i < list.get(node).size(); i++)
                {
                    if (color[node] == color[list.get(node).get(i)])
                        return false;

                    if (color[list.get(node).get(i)] == 0)
                    {
                        nodes.add(i);
                        color[list.get(node).get(i)] = (color[node] == 1 ? 2 : 1);
                    }
                }
            }
        }
        
        return true;
    }

    // Counts the number of connected components in the graph
    public int countConnectedComponents()
    {
        boolean [] visited = new boolean[numVertices];
        int count = 0;

        // Loop through all vertices in the graph
        for (int i = 0; i < numVertices; i++)
        {
            if (!visited[i])
            {
                count++;
                CC_DFS(i, visited);
            }
        }

        return count;
    }

    // Performs DFS to visit all connected nodes in a graph
    // Called by countConnectedComponents() to mark all connected nodes in a graph
    private void CC_DFS(int node, boolean [] visited)
    {
        // Immediately mark node as visited
        visited[node] = true;

        // Loop through and process all of its unvisted adjacent nodes
        for (int i = 0; i < list.get(node).size(); i++)
        {
            if (!visited[list.get(node).get(i)])
                CC_DFS(list.get(node).get(i), visited);
        }
    }

    // Implementation of Topological Sort on the graph
    // This method will also check if there are any cycles in the graph
    public void topoSort()
    {
        int [] edges = new int[numVertices];
        Queue<Integer> nodes = new ArrayDeque<>();
        int count = 0;

        System.out.println("Topological Sort:");

        // Initialize incoming edges array
        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < list.get(i).size(); j++)
            {
                edges[list.get(i).get(j)]++;
            }
        }

        // Put all nodes that have no incoming edges into queue to process
        for (int i = 0; i < numVertices; i++)
        {
            if (edges[i] == 0)
                nodes.add(i);
        }

        // Loop until we process every edge
        while (!nodes.isEmpty())
        {
            // Keep track of the unique number of nodes we process
            count++;

            // Process node
            int node = nodes.remove();
            System.out.print((char)(node + 'A') + " -> ");

            // Decrement the number of incoming edges for this node's neighbors
            // and add to queue if there are no more incoming edges
            for (int i = 0; i < list.get(node).size(); i++)
            {
                edges[i]--;
                if (edges[i] == 0)
                    nodes.add(i);
            }
        }    

        System.out.println(count != numVertices ? "There is a cycle in the graph\n" : "END\n");
    }

    // Performs BFS traversal using a Queue
    // Start traversal at given index (0 -> numVertices - 1)
    public void BFS(int start)
    {
        boolean [] visited = new boolean[numVertices];
        Queue<Integer> nodes = new LinkedList<>();
        LinkedList<Integer> adjacent = null;
        int adjacentNode = -1;

        System.out.println("BFS:");

        // Starting node is visited, add to queue to process
        visited[start] = true;
        nodes.add(start);

        // Loop until we don't have any more connected vertices
        while (!nodes.isEmpty())
        {
            // Process the first node in the queue
            int node = nodes.remove();
            System.out.print((char)(node + 'A') + " -> ");
            adjacent = list.get(node);

            // Loop through the current node's neighbors and add them to queue if not visited yet
            for (int i = 0; i < adjacent.size(); i++)
            {
                adjacentNode = adjacent.get(i);
                if (!visited[adjacentNode])
                {
                    visited[adjacentNode] = true;
                    nodes.add(adjacentNode);
                }
            }
        }

        System.out.println("END\n");
    }

    // Performs DFS traversal using a Stack
    // Start traversal at given start index (0 -> numVertices - 1)
    public void iterativeDFS(int start)
    {
        Stack<Integer> nodes = new Stack<>();
        boolean [] visited = new boolean[numVertices];
        int adjacentNode = -1;
        LinkedList<Integer> adjacent;

        System.out.println("Iterative DFS:");

        // Starting node is visited, add to stack
        visited[start] = true;
        nodes.push(start);

        // Loop until we've visited every connected vertex of start
        while (!nodes.isEmpty())
        {
            // Pop off node at top of stack and process it
            int node = nodes.pop();
            System.out.print((char)(node + 'A') + " -> ");
            adjacent = list.get(node);

            // Loop through its adjacent nodes and push onto stack if not already visited
            for (int i = 0; i < adjacent.size(); i++)
            {
                adjacentNode = adjacent.get(i);

                if (!visited[adjacentNode])
                {
                    visited[adjacentNode] = true;
                    nodes.push(adjacentNode);
                }
            }
        }

        System.out.println("END\n");
    }

    // Gateway function to recursive DFS
    public void recursiveDFS(int start)
    {
        boolean [] visited = new boolean[numVertices];

        System.out.println("Recursive DFS:");
        DFS(start, visited);
        System.out.println("END\n");
    }

    // Performs DFS traversal using recursion
    // Node is the current node we are processing
    private void DFS(int node, boolean [] visited)
    {
        // Immediately mark as visited
        visited[node] = true;

        // Process current node
        System.out.print((char)(node + 'A') + " -> ");

        // Process the unvisited neighbors recursively
        for (int i = 0; i < list.get(node).size(); i++)
        {
            if (!visited[list.get(node).get(i)])
            {
                DFS(list.get(node).get(i), visited);
            }
        }
    }

    // Print the adjacency list representing the graph
    public void printGraph()
    {
        System.out.println("\nGraph: \n");

        for (int i = 0; i < numVertices; i++)
        {
            LinkedList<Integer> adjacent = list.get(i);
            System.out.print((char)(i + 'A') + ": ");
            for (int j = 0; j < adjacent.size(); j++)
            {
                System.out.print((char)(adjacent.get(j) + 'A') + (j == adjacent.size() - 1 ? "\n" : " -> "));
            }
        }

        System.out.println("\n");
    }
}
