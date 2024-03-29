# Practice-Problems

A collection of problems used to practice data structure, algorithm, and general software design.

<details>
  <summary><h2>Clever Thinking Algorithms<h2></summary>

  <details>
    <summary><h3>Duplicate<h3></summary>
  
Given a sorted array, determines if there is a duplicate value in the array

  </details>

  <details>
  <summary><h3>MCSS<h3></summary>

Compute the maximal contiguous subsequence sum of a given array

</details>

<details>
  <summary><h3>PositiveCount<h3></summary>

Write an efficient method that takes a sorted input array of n elements and:

1. Returns true if there are at least k positive elements in the array; otherwise return false
2. Returns the number of positive elements in the array

</details>

<details>
  <summary><h3>TargetSum<h3></summary>
  
Determines if a given sorted array has 1 or 2 values that sum up to a target value.

</details>

</details>

<details>
  <summary><h2>Design Principles<h2></summary>

A collection of programs that are meant to showcase software design principles with different problems

  <details>
    <summary><h3>Diamond<h3></summary>

  Prints a diamond of odd, positive width to the terminal. Focus on Test-Driven Development, Functional Decomposition,
  DRY, and Self-Documenting code practices.

  To run:
  ```
  javac Diamond.java

  java Diamond n
  ```

  This folder also includes sample outputs that the program should print based on the certain test case.

  * `output1.txt` - A diamond of width 7
  * `output2.txt` - Any diamond with non-positive width should print
  * `output3.txt` - Any diamond with even length should print

</details>

<details>
  <summary><h3>Chevron<h3></summary>

  * The first line of output contains *n* '@' symbols, followed immediately by *n* '*' symbols, followed immediately
    by another *n* '@' symbols, followed by a newline character
  * The following lines will be the same as the previous lines, but without the first character from that previous line.
  * Pattern continues until the last line of output, which will contain a single '@' symbol, followed by a newline character.

  To run:
  ```
  javac Chevron.java

  java Chevron n
  ```

  This folder also includes sample outputs that the program should print based on the certain test case.
  * `output1.txt` - Chevron of n = 2
  * `output2.txt` - Chevron when passed a non-positive value

</details>

<details>
  <summary><h3>Rando<h3></summary>

Take two integer parameters, m and n, repeatedly get a random integer within the range
of m through n (inclusively) until it has produced every value on the range of m through n
at least once.

This program acts as an exercise in automated testing.

To run:
```
javac Rando.java

java Rando lowBound highBound
```

</details>

</details>


<details>
  <summary><h2>Dynamic Programming<h2></summary>

A collection of Dynamic Programming problems. In each of the files, I provide  recursive, memoized, dp, and 
any dp optimization solutions to the problem at hand.

<details>
  <summary><h3>Fibonnaci<h3></summary>

Recursive, Memoized, and DP solutions to find the nth Fibonacci number

To run:
```
javac Fibonacci.java

java Fibonacci *n1 n2 ...*
```

</details>

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
  <summary><h2>Generic Data Structures<h2></summary>
  
  <details>
    <summary><h3>LinkedList<h3></summary>

My implementation of a Linked List that can store any Comparable data type. Methods include:

* *Node Constructor*
* *headInsert()*
* *tailInsert()*
* *deleteHead()*
* *deleteTail()*
* *sortedInsert()*
* *isEmpty()*
* *isFull()*
* *printList()*
  
  </details>

  <details>
    <summary><h3>Queue<h3></summary>

Implementation of a Queue data structure using the Generic Linked List file above. Methods include:

* *Queue Constructor*
* *enqueue*
* *dequeue*
* *front*
* *isEmpty*
* *isFull*

  </details>

  <details>
    <summary><h3>Stack<h3></summary>

Implementation of a Stack data structure using the Generic Linked List file above. Methods include:

* *Stack Constructor*
* *push*
* *pop*
* *peek*
* *isEmpty*
* *isFull*

  </details>

</details>

<details>
  <summary><h2>SieveOfEratosthenes<h2></summary>
  
An algorithm that computes prime numbers that are at least double the previous prime value. This is used for hash table expansions when the hash table
becomes at least half full.

This algorithm begins with values 0 through n, and "sieves" out the non-prime values until we are left with only prime values.

</details>