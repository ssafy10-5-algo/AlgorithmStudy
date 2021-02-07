import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17213 {

	static int combi[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		System.out.println(combination(M-1, M-N));
	}
	
	static int combination(int x, int y) {
		if (y == 0 || x == y) {
			return 1;
		}
		
		return combination(x-1, y-1) + combination(x-1, y);
	}

}
