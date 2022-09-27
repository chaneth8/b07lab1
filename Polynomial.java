import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Polynomial {
	double[] coefficients;
	int[] exponents;
	
	public Polynomial(double[] c, int[] e) {
		coefficients = c;
		exponents = e;
	}
	
	public Polynomial() {
		coefficients = new double[1];
		exponents = new int[1];
		coefficients[0] = 0;
		exponents[0] = 0;
	}
	
	
	public Polynomial(File f) throws Exception{
		Scanner Reader;
		Reader = new Scanner(f);
		String str = Reader.nextLine();
		String [] values = str.split("x|\\+|-");
		String [] signs = str.split("x|[0-9]");
		
		int polydegree = Integer.parseInt(values[values.length - 1]);
		
		double[] coff = new double[polydegree + 1];
		int[] exp = new int[polydegree + 1];
		double cv = 0;
		
		for (int a = 0; a < polydegree+ 1; a++) {
			coff[a] = 0;
			exp[a] = a;
		}
		
		for (int i = 0; i < values.length; i++) {
			if (i == 0) {
				coff[i] =  Double.parseDouble(values[i]);
			} else if (i % 2 == 1) {
				cv = Double.parseDouble(values[i]);
				if (signs[i].equals('-')) {
					cv = -1 * Double.parseDouble(values[i]);
				} else {
					cv = Double.parseDouble(values[i]);
				}
			} else {
				coff[Integer.parseInt(values[i])] = cv;
			}
		} 
			
		
		coefficients = coff;
		exponents = exp;
	}
    
	
	public void saveToFile(File f) throws Exception{
		String output = "";
		for (int i = 0; i < coefficients.length; i++) {
			if (i == 0) {
				output = output + Double.toString(coefficients[i]);
			} else if (coefficients[i] != 0) {
				if (coefficients[i] > 0) {
					output = output + "+" + Double.toString(coefficients[i]) + "x" + Integer.toString(exponents[i]);
				} else {
					output = output + Double.toString(coefficients[i]) + "x" + Integer.toString(exponents[i]);
				}
			}
		}
	    FileWriter newfile; 
	    newfile = new FileWriter(f);
	    newfile.write(output);
	    newfile.close();
	}
	
	
	public Polynomial add(Polynomial p2) {
		Polynomial return_polynomial;
		int length1 = this.coefficients.length;
		int length2 = p2.coefficients.length;
		int i = 0;
		double coefficient_array[];
		int exponent_array[];
		
		if (length1 >= length2) {
			coefficient_array = new double[length1];
			exponent_array = new int[length1];
			while (length1 > i) {
				if (length2 > i) {
					coefficient_array[i] = this.coefficients[i] + p2.coefficients[i];
				} else {
					coefficient_array[i] = this.coefficients[i];
				}
				exponent_array[i] = i;
				i++;
			}
		} else {
			coefficient_array = new double[length2];
			exponent_array = new int[length2];
			while (length2 > i) {
				if (length1 > i) {
					coefficient_array[i] = this.coefficients[i] + p2.coefficients[i];
				} else {
					coefficient_array[i] = p2.coefficients[i];
				}
				exponent_array[i] = i; 
				i++;
			}
		}
		
		return_polynomial = new Polynomial(coefficient_array, exponent_array);
		
		return return_polynomial;
	}
	
	public Polynomial multiply(Polynomial p2) {
		Polynomial return_polynomial;
		int length1 = this.coefficients.length;
		int length2 = p2.coefficients.length;
		double coefficient_array[];
		int exponent_array[];
		int a = 0;
		int newlength = exponents[length1 - 1] + p2.exponents[length2 - 1] + 1; 
		
		coefficient_array = new double[newlength];
		exponent_array = new int[newlength];
		
		while (a < newlength) {
			exponent_array[a] = a;
			coefficient_array[a] = 0;
			a++;
		}
		
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length2; j++) {
				coefficient_array[i + j] = coefficient_array[i + j] + coefficients[i] * p2.coefficients[j];
			}
		}
		
		return_polynomial = new Polynomial(coefficient_array, exponent_array);
		
		return return_polynomial;
	}
	
	public double evaluate (double input) {
		int length1 = this.coefficients.length;
		int i = 0;
		double value = 0;
		
		while (i < length1) {
			value = value + this.coefficients[i] * Math.pow(input, i);
			i++;
		}
		
		return value;
	}
	
	public boolean hasRoot (double root) {
		return evaluate(root) == 0;
	}
	

}