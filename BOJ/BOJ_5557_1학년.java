import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5557_1학년 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		long[][] dp = new long[N][21];
		
		String input = br.readLine();
		for (int i = 0, idx=0; i < arr.length; i++) {
			arr[i] = input.charAt(idx) - '0';
			idx += 2;
		}
		
		dp[0][arr[0]] = 1;
		
		for (int i = 1; i < N-1; i++) {
			for (int j = 0; j <= 20; j++) {
				if (dp[i-1][j] == 0) continue;
				
				if (j - arr[i] >= 0)
					dp[i][j-arr[i]] += dp[i-1][j];
				
				if(j+arr[i] <= 20)
					dp[i][j+arr[i]] += dp[i-1][j];
			}
		}
		
		System.out.println(dp[N-2][arr[N-1]]);
	}

}
