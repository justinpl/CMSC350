import java.util.Stack;
import java.util.StringTokenizer;

/**
 * File Name   : test.java
 * Author      : Justin Luce
 * Created on  : 25-03-2018
 * Description : 
 **/
public class test
{
    public static String evaluate(String input)
    {
        input=input.replaceAll("[\t\n ]", "")+"=";
        String[] tokens = input.split("");
        Stack operatorStack=new Stack();
        Stack operandStack=new Stack();
        for (String token : tokens) 
        {
            if(token.matches("[0123456789]"))
                operandStack.push(token);
            else
                operatorStack.push(token);
            equals(operandStack, operatorStack);
        }
        return (String)operandStack.peek();   
    }
    
    public static void equals(Stack operands, Stack operators)
    {
        while(operators.size()>=2)
        {
            String op1=(String)operators.pop();
            String op2=(String)operators.pop();
            if(getPrecedance(op1)<getPrecedance(op2))
            {
                operators.push(op2);
                operators.push(op1);
                return;
            }
            else
            {
                String num1=(String)operands.pop();
                String num2=(String)operands.pop();
                operands.push(getResults(num1, op2, num2));
                operators.push(op1);
            }
        }
    }
        
    public static int getPrecedance(String op)
    {
        switch(op)
        {
            case "*": 
                return 1;
            case "/": 
                return 1;
            case "+": 
                return 2;
            case "-": 
                return 2;
            case "=": 
                return 3;
        }
        return 0;
    }
    
    
    public static String getResults(String number1, String op, String number2)
    {
        double num1=Double.parseDouble(number1);
        double num2=Double.parseDouble(number2);
        switch(op)
        {
            case "*": 
                return ""+(num1*num2);
            case "/": 
                return ""+(num1/num2);
            case "+": 
                return ""+(num1+num2);
            case "-": 
                return ""+(num1-num2);
        }
        return null;
    }
}
