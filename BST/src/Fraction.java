/**
 * File Name   : Fractions.java
 * Author      : Justin Luce
 * Created on  : 22-04-2018
 * Description : 
 **/
public class Fraction implements Comparable<Fraction>
{
    private String data;
    private int numerator;
    private int denominator;
   
    public Fraction(String input)
    {
        String[] x = input.split("/");
        if (x.length > 2)
        {
            throw new NumberFormatException();
        }
        this.numerator = Integer.parseInt(x[0]);
        this.denominator = Integer.parseInt(x[1]);
        this.data = input;
    }
    public String toString()
    {
        return data;
    }
    public int compareTo(Fraction other)
    {
        int x = numerator * other.denominator;
        int y = other.numerator * denominator;
        return x - y;
    }
}
