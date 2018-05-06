import java.io.*;
import java.util.*;
import java.util.Map.*;

/**
 * File Name   : Graph.java
 * Author      : Justin Luce
 * Created on  : 05-05-2018
 * Description : This program build a directed graph from a source file, provides a topological sorting of the 
 *               graph and performs a depth first search.
 **/

public class Graph<T> {
    //declare variables
    private HashMap<Integer, String> edges;
    private ArrayList<LinkedList<Integer>> adjacentNode;
    private int currentIndex;
    private HashMap<String, Node> map;
    private ArrayList<String> headList;
    private Stack<Node> edgeStack;
    // constructor for graph object
    public Graph() {
        map = new HashMap<>();
        edges = new HashMap<>();
        adjacentNode = new ArrayList<>();
        headList = new ArrayList<>();
        edgeStack = new Stack();
        currentIndex = 0;
    }
    // build a graph from an input file
    public void buildGraph(String fileName) throws CycleDetectedException, FileNotFoundException {
        Scanner file = null;
        Scanner line;
        String headNode;
        Node<String> currentNode;
        file = new Scanner(new BufferedReader(new FileReader(fileName)));
        //read file one line at a time
        while (file.hasNextLine()) {
            line = new Scanner(file.nextLine());
            //get next node from line 
            headNode = line.next();
            headList.add(headNode);
            //if map already contains this node, throw exception
            if (map.containsKey(headNode)) {
                currentNode = map.get(headNode);
                if (currentNode.isFound())        {
                    throw new CycleDetectedException();
                } else {
                    currentNode.setFound(true);
                }
            } else { 
                //add node to map as new head node
                map.put(headNode, new Node<String>(headNode));
            }
            //place the remaining nodes in the line adjacent to the head node 
            while (line.hasNext()) {
                String adjacentNode = line.next();
                if (map.containsKey(adjacentNode)) {
                    Node<String> newNode = map.get(adjacentNode);
                    map.get(headNode).addNode(newNode);
                    //check for cycle
                    if (newNode.isFound()) {
                        throw new CycleDetectedException();
                    } else {
                        newNode.setFound(true);
                    }
                } else {
                    //add adjacent node to map
                    map.put(adjacentNode, new Node<String>(adjacentNode));
                    map.get(headNode).addNode(map.get(adjacentNode));
                }
            }
        }
        for (String head : headList) {
            currentNode = map.get(head);
            buildList(currentNode);
        }
    }
    // recursively builds the list
    private void buildList(Node<String> node) {
        if (!edges.containsValue(node.getHead())) {
            edges.put(currentIndex, node.getHead());
            currentIndex++;
            adjacentNode.add(new LinkedList<Integer>());
        } 
        if (node.hasAdjacent()) {
            for (Node<String> adjacent : node.getAdjacent()) {
                addEdge(node, adjacent);
                buildList(adjacent);
            }
        }
    }
    // get the key from the list of map of edges for the value
    private Integer getKey(String data) {
        for (Entry<Integer, String> entry : edges.entrySet()) {
            if (entry.getValue().equals(data)) {
                return entry.getKey();
            }
        }
        return -1;
    }
    // adds edge to map of edges 
    private void addEdge(Node<String> x, Node<String> y) {
        int yIndex;
        if (edges.containsValue(y.getHead())) {
            yIndex = getKey(y.getHead());
        } else {
            yIndex = currentIndex;
            currentIndex++;
            edges.put(yIndex, y.getHead());
            adjacentNode.add(new LinkedList<Integer>());
        }
        adjacentNode.get(getKey(x.getHead())).add(yIndex);
    }
    // search the graph using depth first searching 
    private void searchGraph(Node<String> node) throws CycleDetectedException {
        if (node.isFound()) {
            throw new CycleDetectedException();
        }
        if (node.isDone()) {
            return;
        }
        node.setFound(true);

        if (node.hasAdjacent()) {
            for (Node<String> adjacent : node.getAdjacent()) {
                searchGraph(adjacent);
            }
        }
        node.setDone(true);
        edgeStack.push(node);
    }
    // order the graph topologically
    public String orderGraph(String nodeName) throws InvalidClassNameException, CycleDetectedException {
        String result = "";
        for (Entry<String, Node> entry : map.entrySet()) {
            entry.getValue().resetNode();
        }
        if (edges.containsValue(nodeName)) {
            searchGraph(map.get(nodeName));
            while (!edgeStack.isEmpty()) {
                result += edgeStack.pop().getHead() + " ";
            }
            return result;
        } else {
            throw new InvalidClassNameException();
        }
    }
    // declare node class
    public class Node <T> {
        //declare variables
        private ArrayList<Node<T>> adjacent;
        private T head;
        private boolean found;
        private boolean done;
        //node constructor
        public Node(T data){
            head = data;
            adjacent = new ArrayList<>();
            found = false;
            done = false;
        }
        //node constructor for adjacent nodes
        public Node(T data, ArrayList<Node<T>> node) {
            head = data;
            adjacent = node;
            found = false;
            done = false;
        }
        // add new node to list
        public void addNode(Node node) {
            adjacent.add(node);
        }
        // return value of variable head
        public T getHead() {
            return head;
        }
        // returns list of all nodes adjacent to head
        public ArrayList<Node<T>> getAdjacent() {
            return adjacent;
        }
        // determine if node has any adjacent nodes
        public boolean hasAdjacent() {
            return (adjacent.size() > 0);
        }
        //checks if the node has been found during search
        public boolean isFound() {
            return found;
        }
        //checks if the node is done during search
        public boolean isDone() {
            return done;
        } 
        //reset node values to false
        public void resetNode() {
            found = false;
            done = false;
        }
        //sets whether the node was found 
        public void setFound(boolean bool) {
            found = bool;
        }
        //sets whether the node was found
        public void setDone(boolean bool) {
            done = bool;
        }
    }
}