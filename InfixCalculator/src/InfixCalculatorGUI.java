import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * File Name   : InfixCalculatorGUI.java
 * Author      : Justin Luce
 * Created on  : 25-03-2018
 * Description : 
 **/
public class InfixCalculatorGUI extends JFrame
{
    // creating GUI elements
    private JLabel enterL = new JLabel("Enter Infix Expression");
    private JTextField enterInput = new JTextField(50);
    private JLabel emptyL = new JLabel("");
    private JButton evaluateB = new JButton("Evaluate");
    private JLabel resultL = new JLabel("Result");
    private JTextField resultOutput = new JTextField(20);
        
    // GUI constructor
    public InfixCalculatorGUI()
    {
        super("Infix Expression Evaluator");
        setSize(400,125);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2,10,10));
        add(enterL);
        add(enterInput);
        add(emptyL);
        add(evaluateB);
        add(resultL);
        add(resultOutput);
        resultOutput.setEditable(false);
        evaluateB.addActionListener
            (event -> evaluate());
    }
    public void evaluate()
    { 
        try
        {
            resultOutput.setText(InfixCalculator.evaluate(enterInput.getText()));
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static void main(String[] args)
    {
        InfixCalculatorGUI window = new InfixCalculatorGUI();
        window.setVisible(true);
    }
}