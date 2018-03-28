/**
 * File Name   : DivideByZero.java
 * Author      : Justin Luce
 * Created on  : 28-03-2018
 * Description : 
 **/
public class DivideByZero extends Exception
{
    public DivideByZero() 
    {
        super("Cannot divide by zero");
    }
}
