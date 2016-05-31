package de.roth;
import java.util.Scanner;

public class App
{ 
    public static void main(String args[]) 
    { 
    	Scanner sc = new Scanner(System.in); 
    	System.out.println("Eingabe: "); 
    	String eingabe = sc.next(); 
    	System.out.println("Das wurde eingegeben: " + eingabe); 
    	String gross = eingabe.toUpperCase(); 
    	System.out.println("Umgewandelt: " + gross);
    	sc.close();
    }	
		
}
	


