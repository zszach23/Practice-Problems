// Zach Sally
// October 29, 2022

// ==========================
// |  WeightedGraphAL.java  |
// ==========================

// Represents a weighted graph of n (0 <= n <= 26) vertices as an adjacency list
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

public class WeightedGraphAL
{
    ArrayList<ArrayList<Integer>> list;
    int numVertices;

    // Use 1e9 for cases where INT_MAX + INT_MAX = 0 from overflow, which would break the algorithms
    static final int oo = 1e9;

    public static void main(String [] args) throws IOException
    {
        if (args.length < 1)
        {
            System.out.println("Pass in a valid text file into the command line");
            return;
        }

        WeightedGraphAL g = new WeightedGraphAL(args[0]);

        //g.runDijkstra(0);
        g.runDijkstraMH(0);
        //g.runBellmanFord(0);
    }

    public WeightedGraphAL(String filename) throws IOException
    {
        Scanner scan = new Scanner(new File(filename));

        numVertices = scan.nextInt();

        
    }

    public void runDijkstra(int start)
    {
        if (!parameterCheck(start))
            return;

        System.out.println("Dijkstra's Algorithm:\n");
        System.out.println("Start Vertex: " + start + "\n");


    }

    private int minDistance(int [] dist, boolean [] visited)
    {

    }

    public void runDijkstraMH(int start)
    {

    }

    public void runBellmanFord(int start)
    {

    }

    public void pathTaken()
    {

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
}