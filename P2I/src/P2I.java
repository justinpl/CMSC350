import java.util.Stack;

/**
 * File Name   : P2I.java
 * Author      : Justin Luce
 * Created on  : 08-04-2018
 * Description : This class convert a post fix expression string into an infox expression string 
 **/
public class P2I
{
    public static String convert(String string) throws RuntimeException
    {
        //strip whitespace
        string = string.replaceAll("[\t\n ]", "");
        //split on every char into an array
        String[] postfix = string.split("");
        Stack<String> piStack = new Stack<String>();
        String token = "";
        String result = "";
        try
        {            
            for (int i = 0; i < postfix.length; i++)
            {
                token = postfix[i];
                //validate token is number or operator
                validate(token);
                //if token is num, push to stack
                if (token.matches("[0123456789]"))
                {
                    piStack.push(token);
                }
                // if token is + or - operator, present as infix and push to stack
                if (token.matches("[+-]"))
                {
                    String rExp = piStack.pop();
                    String lExp = piStack.pop();
                    String nExp = lExp+token+rExp;
                    piStack.push(nExp);
                }
                // if token is / or * operator, present as infix and push to stack
                if (token.matches("[*/]"))
                {
                    String leftExp;
                    String rightExp;
                    String rExp = piStack.pop();
                    String lExp = piStack.pop();
                    if (rExp.matches("[+-]"))
                    {
                        rightExp = "("+rExp+")";
                    }
                    else
                    {
                        rightExp = "("+rExp+")"; 
                    }
                    if (lExp.matches("[+-]"))
                    {
                        leftExp = "("+lExp+")";
                    }
                    else
                    {
                        leftExp = "("+lExp+")"; 
                    }
                    String nExp = leftExp+token+rightExp;
                    piStack.push(nExp);
                }
            } 
            result = piStack.pop();            
        }
        catch(Exception e)
        {
            throw new RuntimeException(token);
        }
        return result;
    }
    //validate token and throw exception if invalid
    public static void validate(String token) throws RuntimeException
    {
        if (!token.matches("[0123456789/*+-]"))
        {
            throw new RuntimeException();
        }
    }
}