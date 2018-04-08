import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * File Name   : P2I.java
 * Author      : Justin Luce
 * Created on  : 08-04-2018
 * Description : 
 **/
public class P2IGUI extends JFrame
{
    // creating GUI elements
    private JLabel enterL = new JLabel("Enter Postfix Expression");
    private JTextField enterInput = new JTextField(50);
    private JLabel emptyL = new JLabel("");
    private JButton convertB = new JButton("Convert");
    private JLabel resultL = new JLabel("Infix Expression");
    private JTextField resultOutput = new JTextField(20);
        
    // GUI constructor
    public P2IGUI()
    {
        super("Postfix-Infix Expression Convertor");
        setSize(400,125);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2,10,10));
        add(enterL);
        add(enterInput);
        add(emptyL);
        add(convertB);
        add(resultL);
        add(resultOutput);
        resultOutput.setEditable(false);
        convertB.addActionListener
            (event -> convert());
    }
    public void convert()
    { 
        try
        {
            resultOutput.setText(P2I.convert());
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void main(String[] args)
    {
        P2IGUI window = new P2IGUI();
        window.setVisible(true);
    }
}
