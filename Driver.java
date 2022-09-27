import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {
	public static void main(String [] args) throws Exception {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		int [] e1 = {0, 1, 2, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {0,-2,0,0,-9};
		int [] e2 = {0, 1, 2, 3, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1)) {
			System.out.println("1 is a root of s");
		} else {
			System.out.println("1 is not a root of s");
		}
		
		Polynomial s2 = p1.multiply(p2);
		System.out.println("s2(0.1) = " + s2.evaluate(0.1));
		
		File f = new File("/Users/Ethan/b07lab1/polynomial.txt");
		
		//s2.saveToFile(f);
		
		Polynomial s3 = p = new Polynomial(f);
		

	}
}