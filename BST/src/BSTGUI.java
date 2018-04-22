import java.awt.*;
import javax.swing.*;

/**
 * File Name   : BSTGUI.java
 * Author      : Justin Luce
 * Created on  : 22-04-2018
 * Description : 
 **/
public class BSTGUI extends JFrame
{
    // creating GUI elements
    private JLabel enterL = new JLabel("Original List");
    private JTextField enterInput = new JTextField(20);
    private JLabel emptyL = new JLabel("                                                             ");
    private JLabel resultL = new JLabel("Sorted List  ");
    private JTextField resultOutput = new JTextField(20);
    private JButton sortB = new JButton("Perform Sort");
    private JPanel sortOrderP = new JPanel(new GridLayout(2,1,10,10));
    private ButtonGroup sortOrderBG = new ButtonGroup();
    private JRadioButton ascendR = new JRadioButton("Ascending                  ");
    private JRadioButton descendR = new JRadioButton("Descending                 ");
    private JPanel numTypeP = new JPanel(new GridLayout(2,1,10,10));
    private ButtonGroup numTypeBG = new ButtonGroup();
    private JRadioButton intR = new JRadioButton("Integer                    ");
    private JRadioButton fracR = new JRadioButton("Fraction                   ");
        
    // GUI constructor
    public BSTGUI()
    {
        super("Binary Search Tree Sort");
        setSize(320,200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sortOrderP.setBorder(BorderFactory.createTitledBorder("Sort Order"));
        sortOrderBG.add(ascendR);
        sortOrderBG.add(descendR);
        sortOrderP.add(ascendR);
        sortOrderP.add(descendR);
        ascendR.setSelected(true);
        numTypeP.setBorder(BorderFactory.createTitledBorder("Numeric Type"));
        numTypeBG.add(intR);
        numTypeBG.add(fracR);
        numTypeP.add(intR);
        numTypeP.add(fracR);
        intR.setSelected(true);
        setLayout(new FlowLayout());
        add(enterL);
        add(enterInput);
        add(resultL);
        add(resultOutput);
        add(emptyL);
        add(sortB);
        add(sortOrderP);
        add(numTypeP);
        resultOutput.setEditable(false);
        sortB.addActionListener
            (event -> sortData());
    }
    //display converted expression
    public void sortData()
    {       
        try
        {
            if (enterInput.getText().isEmpty()==true)
            {
                throw new NullPointerException();
            }
            BST tree;
            String data[] = enterInput.getText().split(" ");
            if(fracR.isSelected())
            {
                tree = new BST(new Fraction(data[0]));
                
                for(int i = 1; i < data.length; i++)
                {
                    if(data[i].split("/").length > 2)
                    {                      
                        throw new NumberFormatException();
                    }
                    if(!data[i].contains("/"))
                    {
                        Integer.parseInt(data[i]);
                        data[i] = data[i] + "/1";
                    } 
                    tree.insertData(new Fraction(data[i]));
                }
            } 
            else
            {
                tree = new BST(Integer.parseInt(data[0]));
                for (int i = 1; i < data.length; i++) 
                {
                    tree.insertData(Integer.parseInt(data[i]));
                }   
            }
            if (ascendR.isSelected()) 
            {
                resultOutput.setText(tree.sortAscend(tree.getRoot()));
            } 
            else if (descendR.isSelected()) 
            {
                resultOutput.setText(tree.sortDescend(tree.getRoot())); 
            }
        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null, "Please enter a space separated list", "NullPointerException", JOptionPane.ERROR_MESSAGE);
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Improperly Formatted Input", "NumberFormatException", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args)
    {
        BSTGUI window = new BSTGUI();
        window.setVisible(true);
    }
}
