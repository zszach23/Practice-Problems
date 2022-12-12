# Practice-Problems

A collection of problems used to practice data structure and algorithm design.

<details>
  <summary><h2>Duplicate<h2></summary>
  
Given a sorted array, determines if there is a duplicate value in the array

</details>

<details>
  <summary><h2>Graphs<h2></summary>
  
<details>
  <summary><h3>GraphAL and GraphAM<h3></summary>
  
Adjacency List and Adjacency Matrix representations of a given graph. Nodes are represented *A* to *Z*, so to keep naming conventions, 
0 <= numVertices <= 26

Supports:

* *BFS*
* *DFS* (Recursive and Iterative Versions)
* *isBipartite*
* *countConnectedComponents*
* *Topological Sort*
* *Print*
  
</details>

<details>
  <summary><h3>WeightedGraphAL and WeightedGraphAM<h3></summary>
  
Adjacency List and Adjacency Matrix representations of a given weighted graph. Nodes are represented *A* to *Z*, so to keep naming conventions,
0 <= numVertices <= 26

Supports:

* Find Shortest Cost from a start node to every other node
  * Bellman-Ford
  * Djikstra (Traditional Linear Search and MinHeap Versions)
  * Path Taken
  
</details>

<details>
  <summary><h3>Hamilton<h3></summary>
  
Determines whether a given graph has a Hamiltonian Cycle. Graph is represented as an Adjacency Matrix. Nodes are represented *A* to *Z*, so to keep naming
conventions, 0 <= numVertices <= 26.

</details>
  
</details>

<details>
  <summary><h2>LinkedList<h2></summary>
  
Implements a linked list data structure in Java

</details>

<details>
  <summary><h2>SieveOfEratosthenes<h2></summary>
  
An algorithm that computes prime numbers that are at least double the previous prime value. This is used for hash table expansions when the hash table
becomes at least half full.

This algorithm begins with values 0 through n, and "sieves" out the non-prime values until we are left with only prime values.

</details>

<details>
  <summary><h2>TargetSum<h2></summary>
  
Determines if a given sorted array has 1 or 2 values that sum up to a target value. This algorithm uses a two-pointer approach to produce O(n) runtime with O(1)
extra space.

