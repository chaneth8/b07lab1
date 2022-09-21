public class Polynomial {
	double[] coefficients;
	
	public Polynomial(double[] c) {
		coefficients = c;
	}
	
	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	
	public Polynomial add(Polynomial p2) {
		Polynomial return_polynomial;
		int length1 = this.coefficients.length;
		int length2 = p2.coefficients.length;
		int i = 0;
		double array[];
		
		if (length1 >= length2) {
			array = new double[length1];
			while (length1 > i) {
				if (length2 > i) {
					array[i] = this.coefficients[i] + p2.coefficients[i];
				} else {
					array[i] = this.coefficients[i];
				}
				i++;
			}
		} else {
			array = new double[length2];
			while (length2 > i) {
				if (length1 > i) {
					array[i] = this.coefficients[i] + p2.coefficients[i];
				} else {
					array[i] = p2.coefficients[i];
				}
				i++;
			}
		}
		
		return_polynomial = new Polynomial(array);
		
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