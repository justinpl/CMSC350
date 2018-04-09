/**
 * File Name   : RuntimeException.java
 * Author      : Justin Luce
 * Created on  : 08-04-2018
 * Description : Exception the informs user the charaqcter enter is invalid
 **/
public class RuntimeException extends Exception
{
    public RuntimeException() 
    {
        super("Invalid Character");
    }
    public RuntimeException(String token) 
    {
        super("Invalid Character: "+token);
    }
}
