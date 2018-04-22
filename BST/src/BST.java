/**
 * File Name   : BST.java
 * Author      : Justin Luce
 * Created on  : 22-04-2018
 * Description : 
 **/
public class BST<T extends Comparable<T>>
{
    private Node<T> root;
    private String output;
        
    public BST(T data) 
    {
        this.root = new Node<T>(data);
        this.output = "";
    }
    void insertData (T data) 
    {
        if (root == null) 
        {                
            root = new Node(data);      
            return;
        }
        insertData(data, root);   
    }
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
    public Node<T> getRoot()
    {
        return root;
    }
    
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
