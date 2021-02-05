import java.math.BigInteger;
import java.util.Scanner;

public class BOJ_1914 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		BigInteger bi = new BigInteger("2");
		bi = bi.pow(n);
		bi = bi.subtract(BigInteger.ONE);
		System.out.println(bi);
		
		if (n <= 20) {
			hanoi(n, 1, 2, 3);
		}
	}
	
	private static void hanoi (int num, int start, int temp, int end) {
		if (num == 1) {
			System.out.println(start + " " + end);
			return;
		}
		
		hanoi(num-1, start, end, temp);
		System.out.println(start + " " + end);
		hanoi(num-1, temp, start, end);
	}

}
