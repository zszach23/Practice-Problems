// Zach Sally
// October 29, 2022

// ==========================
// |  WeightedGraphAM.java  |
// ==========================

// Represents a weighted graph of n (0 <= n <= 26) vertices as an adjacency matrix
// Supports algorithms to find smallest-cost path from source to all vertices
// Note: I represent nodes 0->n as A->Z
//       Therefore, this implementation can hold a max of 26 nodes to keep naming conventions

import java.util.*;
import java.io.*;

class Vertex implements Comparable<Vertex>
{
    int id;
    int dist;

    public Vertex(int id, int dist)
    {
        this.id = id;
        this.dist = dist;
    }

    public int compareTo(Vertex v)
    {
        return this.dist - v.dist;
    }
}

public class WeightedGraphAM
{
    int [][] matrix;
    int numVertices;

    // Use 1e9 for cases where INT_MAX + INT_MAX = 0 from overflow, which would break the algorithms
    static final int oo = (int)(1e9);

    public static void main(String [] args) throws IOException
    {
        if (args.length < 1)
        {
            System.out.println("Pass in a valid text file into the command line");
            return;
        }

        WeightedGraphAM g = new WeightedGraphAM(args[0]);

        //g.runDijkstra(0);
        g.runDijkstraMH(0);
        //g.runBellmanFord(0);
    }

    public WeightedGraphAM(String filename) throws IOException
    {
        Scanner scan = new Scanner(new File(filename));

        numVertices = scan.nextInt();
        matrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < numVertices; j++)
            {
                matrix[i][j] = scan.nextInt();
            }
        }
    }

    // Runs Bellman-Ford Algorithm from a start vertex
    // This will print out the shortest path from the start vertex to every other vertex in graph
    //   Runtime: O(|V|*|E|) OR O(|V|^3)
    //   Space C: O(|V|)
    public void runBellmanFord(int start)
    {
        if (!parameterCheck(start))
            return;

        System.out.println("Bellman-Ford Algorithm:\n");
        System.out.println("Start Vertex: " + (char)(start + 'A') + "\n");

        int [] dist = new int[numVertices];
        int [] prev = new int[numVertices];

        Arrays.fill(dist, oo);
        Arrays.fill(prev, start);
        dist[start] = 0;

        boolean keepGoing = false;

        // Loop through |V| times, the |V|-th iteration should not change anything
        // Early termination if we don't change anything during the current iteration
        for (int k = 0; k < numVertices; k++)
        {
            keepGoing = false;

            // Loop through all edges in graph
            for (int i = 0; i < numVertices; i++)
            {
                for (int j = 0; j < numVertices; j++)
                {
                    // There is an edge from vertex i to vertex j
                    if (matrix[i][j] >= 0)
                    {
                        if (dist[i] + matrix[i][j] < dist[j])
                        {
                            dist[j] = dist[i] + matrix[i][j];
                            prev[j] = i;
                            keepGoing = true;
                        }
                    }
                }
            }

            // We didn't change anything on this iteration, so we can terminate early
            if (!keepGoing)
                break;
        }

        // We changed a distance on the |V|-th iteration, so we have a negative cycle
        if (keepGoing)
        {
            System.out.println("There is a negative cycle\n");
            return;
        }

        pathTaken(start, prev, dist);
        System.out.println();
    }

    // Runs Dijkstra's Algorithm on a graph from the start vertex
    // Assumes no negative edge weights
    // This will print out the shortest path from the start vertex to every other vertex in graph
    // This method performs a linear search to find smallest distance for each iteration, which leads to:
    //      Runtime: O(|V|^2)
    //      Space C: O(|V|)
    public void runDijkstra(int start)
    {
        if (!parameterCheck(start))
            return;

        System.out.println("Dijsktra's Algorithm:\n");
        System.out.println("Start Vertex: " + (char)(start + 'A') + "\n");

        int [] dist = new int[numVertices];
        int [] prev = new int[numVertices];
        boolean [] visited = new boolean[numVertices];

        Arrays.fill(prev, start);
        Arrays.fill(dist, oo);
        dist[start] = 0;

        // Takes advantage of the fact that if we've visited the first n - 1 vertices,
        // we won't update anything since the algorithm has already processed the node's smallest cost path
        for (int i = 0; i < numVertices - 1; i++)
        {
            int smallestDistIndex = minDistance(dist, visited);

            visited[smallestDistIndex] = true;

            // Loop through the current node's adjacent nodes and update dist if smaller cost
            for (int v = 0; v < numVertices; v++)
            {
                int newDist = dist[smallestDistIndex] + matrix[smallestDistIndex][v];
                if (!visited[v] && matrix[smallestDistIndex][v] >= 0 && newDist < dist[v])
                {
                    prev[v] = smallestDistIndex;
                    dist[v] = newDist;
                }
            }
        }

        pathTaken(start, prev, dist);
        System.out.println();
    }

    // Returns the index of the smallest unvisted distance value via linear search
    // Called by runDijkstra() to find the min distance vertex to process
    private int minDistance(int [] dist, boolean [] visited)
    {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < numVertices; i++)
        {
            if (!visited[i] && dist[i] < min)
            {
                minIndex = i;
                min = dist[i];
            }
        }

        return minIndex;
    }

    // Runs Dijkstra's Algorithm on a graph from the start vertex
    // This will print out the shortest path from the start vertex to every other vertex in graph
    // This method utilizes the minHeap implementation of the algorithm, which leads to:
    //      Runtime: O(|V|^2 log|V|)
    //      Space C: O(|V|^2) in the worst case (minheap fills up)
    public void runDijkstraMH(int start)
    {
        if (!parameterCheck(start))
            return;

        System.out.println("Dijsktra's Algorithm (MinHeap):\n");
        System.out.println("Start Vertex: " + (char)(start + 'A') + "\n");

        boolean [] visited = new boolean[numVertices];
        int [] dist = new int[numVertices];
        int [] prev = new int[numVertices];

        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();

        int numVisited = 0;

        // Fill our distance array with max values, and have our start index set to 0 distance
        Arrays.fill(prev, start);
        Arrays.fill(dist, oo);
        dist[start] = 0;

        // Add each vertex and its current distance to the minHeap
        for (int i = 0; i < numVertices; i++)
        {
            minHeap.add(new Vertex(i, dist[i]));
        }

        // Loops until the minHeap is empty or we have visited every node
        while (!minHeap.isEmpty() && numVisited < numVertices)
        {
            Vertex v = minHeap.remove();

            // Don't care if we have already visited this node before
            if (visited[v.id])
                continue;

            // Update unique vertices visited, the current min distance, and mark that it has been visited
            numVisited++;
            dist[v.id] = v.dist;
            visited[v.id] = true;

            // Loop through every vertex and check if it has a valid edge, and whether our new path
            // is less than the current distance stored at vertex i
            for (int i = 0; i < numVertices; i++)
            {
                if (matrix[v.id][i] >= 0 && !visited[i] && dist[v.id] + matrix[v.id][i] < dist[i])
                {
                    // Update our distance array to be the min value and add it to the minHeap
                    // The smaller distance will come first in our minHeap, so we process the 
                    // smaller distances and ignore the duplicates 
                    dist[i] = dist[v.id] + matrix[v.id][i];
                    prev[i] = v.id;
                    minHeap.add(new Vertex(i, dist[i]));
                }
            }
        }

        // This should never print because we added all vertices into the minHeap from the start
        if (numVisited != numVertices)
        {
            System.out.println("There's a disconnect in the graph");
        }
        
        pathTaken(start, prev, dist);
        System.out.println();
    }

    public boolean parameterCheck(int start)
    {
        // Check for valid start point
        if (start < 0 || start >= numVertices)
        {
            System.out.println("Invalid Start Index");
            return false;
        }

        // Run algorithm only on a graph of at least one node
        if (numVertices <= 0)
        {
            System.out.println("Empty Graph or Negative # of Vertices, Abort");
            return false;
        }

        return true;
    }

    private void pathTaken(int start, int [] prevNode, int [] dist)
    {
        int prev;

        for (int i = 0; i < numVertices; i++)
        {
            prev = prevNode[i];
            System.out.println((char)(i + 'A') + ": ");
            System.out.print("Path: ");

            if (i != start)
                System.out.print((char)(i + 'A') + " <-- ");
            

            while (prev != start)
            {
                System.out.print((char)(prev + 'A') + " <-- ");
                prev = prevNode[prev];
            }

            System.out.print((char)(start + 'A') + "\nPath Cost: " + dist[i]);
            System.out.println("\n");
        }
    }
}