import java.util.Stack;
import java.util.StringTokenizer;

/**
 * File Name   : InfixCalculator.java
 * Author      : Justin Luce
 * Created on  : 25-03-2018
 * Description : 
 **/

public class InfixCalculator
{
    public static String evaluate(String input) throws DivideByZero
    {
        input=input.replaceAll("[\t\n ]", "")+"=";
        String operator="()*/+-=";
        StringTokenizer tokenizer=new StringTokenizer(input, operator, true);
        Stack<String> operatorStack=new Stack<String>();
        Stack<String> operandStack=new Stack<String>();
        try
        {
            
            while(tokenizer.hasMoreTokens()) 
            {
                String token=tokenizer.nextToken();
                
                if(token.matches("[0123456789]"))
                {
                    operandStack.push(token);
                }
                else if(token.matches("[(]"))
                {
                    operatorStack.push(token);
                }
                
                else if(token.matches("[)]"))
                {
                    while(!operatorStack.peek().equals("(") && operandStack.size()>=2)
                    {
                        String op=operatorStack.pop();
                        String num1=operandStack.pop();
                        String num2=operandStack.pop();
                        operandStack.push(getResults(num1, op, num2));
                    }
                    operatorStack.pop();
                }
                else if(token.matches("[*/+-]"))
                {
                    while (operatorStack.empty() == false && getPrecedence((String)operatorStack.peek()) <= getPrecedence(token) && operandStack.size()>=2)
                    {
                        String op=operatorStack.pop();
                        if (op == "(")
                        {
                            op=operatorStack.pop();
                        }
                        String num1=operandStack.pop();
                        String num2=operandStack.pop();
                        operandStack.push(getResults(num1, op, num2));
                    }
                    operatorStack.push(token);
                    
                } 
                
            }
            while (operatorStack.empty() == false && operandStack.size()>=2)
            {
                String op=operatorStack.pop();
                String num1=operandStack.pop();
                String num2=operandStack.pop();
                operandStack.push(getResults(num1, op, num2));
            }
        }
        catch(Exception e)
        {
            throw new DivideByZero();
        }
        return operandStack.peek();   
    }
     
    public static int getPrecedence(String op)
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
            case "(": 
                return 4;
        }
        return 0;
    }
    
    public static String getResults(String number1, String op, String number2) throws DivideByZero
    {
        double num1=Double.parseDouble(number1);
        double num2=Double.parseDouble(number2);
        switch(op)
        {
            case "*": 
                return ""+(num2*num1);
            case "/": 
                if (num1 == 0 || num2 == 0)
                {
                    throw new DivideByZero();
                }
                return ""+(num2/num1);
            case "+": 
                return ""+(num2+num1);
            case "-": 
                return ""+(num2-num1);

        }
        return null;
    }
    
}
