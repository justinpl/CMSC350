/**
 * File Name   : BST.java
 * Author      : Justin Luce
 * Created on  : 22-04-2018
 * Description : Inserts and sorts data within a Binary Search Tree.
 **/
public class BST<T extends Comparable<T>>
{
    private Node<T> root;
    private String output;
    // constructor for Binary Search Tree    
    public BST(T data) 
    {
        this.root = new Node<T>(data);
        this.output = "";
    }
    // begins recursive data insert into BST
    void insertData (T data) 
    {
        if (root == null) 
        {                
            root = new Node(data);      
            return;
        }
        insertData(data, root);   
    }
    // compares current value to node and inserts left if less and right if more.
    private void insertData(T data, Node<T> node) 
    {
        if (data.compareTo(node.data) <= 0) 
        {         
            if (node.left != null) 
            {                   
                insertData(data, node.left);  
            } 
            else 
            {                                    
                node.left = new Node(data);
            }
        } 
        else if (data.compareTo(node.data) > 0) 
        {   
            if (node.right !=null) 
            {                  
                insertData(data, node.right); 
            } 
            else 
            {                                   
                node.right = new Node(data);
            }
        }
    }
    //perform an inorder traversal of the BST and outputs results 
    public String sortAscend(Node<T> node)
    {
        if(node != null)
        {
            sortAscend(node.getLeft());
            output += node.getData().toString() + " ";
            sortAscend(node.getRight());
        }
        return output;
    }
    //perform a reverse inorder traversal of the BST and outputs results
    public String sortDescend(Node<T> node)
    {
        if(node != null)
        {
            sortDescend(node.getRight());
            output += node.getData().toString() + " ";
            sortDescend(node.getLeft());
        }
        return output;
    }
    //gets the BST root value
    public Node<T> getRoot()
    {
        return root;
    }
    // constructor for node objects. Each has a value and can store a value to the left and/or right 
    private class Node<T>
    {
       private T data;
       private Node<T> left;
       private Node<T> right;

       public Node(T data)
       {
          left = right = null;
          this.data = data;
       }
       public T getData() 
       {
           return data;
       }
       public Node<T> getLeft() 
       {
           return left;
       }
       public Node<T> getRight() 
       {
           return right;
       }
    }
}
