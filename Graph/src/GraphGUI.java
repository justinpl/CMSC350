import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * File Name   : GraphGUI.java
 * Author      : Justin Luce
 * Created on  : 05-05-2018
 * Description : this program builds a GUI to allow user to input a file containing a directed graph. it then display it recompiled
 **/
public class GraphGUI extends JFrame {
    // creating GUI elements
    private JLabel inputL = new JLabel("Input File Name:");
    private JTextField inputF = new JTextField(20);
    private JButton buildB = new JButton("Build Directed Graph");
    private JLabel classL = new JLabel("Class to recompile:");
    private JTextField classF = new JTextField(20);
    private JButton orderB = new JButton("Topological Order");
    private JPanel buttonP = new JPanel(new GridLayout(2,3,10,10));
    private JPanel orderP = new JPanel();
    private JTextArea orderT = new JTextArea(10, 42);
    private JScrollPane scrollPane = new JScrollPane(orderT);
    private Graph graph;
              
    // GUI constructor
    public GraphGUI() {
        super("Class Dependency Graph");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderP.setBorder(BorderFactory.createTitledBorder("Recompilation Order"));
        setLayout(new BorderLayout());
        add(buttonP, BorderLayout.NORTH);
        buttonP.setSize(500,100);
        buttonP.add(inputL);
        buttonP.add(inputF);
        buttonP.add(buildB);
        buttonP.add(classL);
        buttonP.add(classF);
        buttonP.add(orderB);
        add(orderP, BorderLayout.CENTER);
        orderP.setSize(500,200);
        orderT.setLineWrap(true);
        orderT.setWrapStyleWord(true);
        orderT.setEditable(false);
        orderP.add(scrollPane);
        buildB.addActionListener
            (event -> buildButton());
        orderB.addActionListener
            (event -> orderGraph());
    }
    // checks for file and builds graph if available
    private void buildButton() {
        
        String fileName = inputF.getText();
        try {
            if(fileName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter a File Name", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {  
                graph = new Graph();
                graph.buildGraph(fileName);
                JOptionPane.showMessageDialog(null, "Graph Built Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
            } 
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Opening File", "IOException", JOptionPane.ERROR_MESSAGE);
        } 
        catch (CycleDetectedException e) {
            JOptionPane.showMessageDialog(null, "Cycle Detected", "CycleDetectedException", JOptionPane.ERROR_MESSAGE);
        } 
    }
    // orders the graph topologically
    private void orderGraph() {
        String nodeName = classF.getText();
           
        try {          
            if(graph == null) {
                JOptionPane.showMessageDialog(null, "No Graph Available", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(nodeName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter A Valid Node", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                orderT.setText(graph.orderGraph(nodeName));
            }
        }
        catch(InvalidClassNameException e) {
            JOptionPane.showMessageDialog(null, "Invalid Class Name", "InvalidClassNameException", JOptionPane.ERROR_MESSAGE);
        }
        catch(CycleDetectedException e) {
            JOptionPane.showMessageDialog(null, "Cycle Detected", "CycleDetectedException", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        GraphGUI window = new GraphGUI();
        window.setVisible(true);
    }
}