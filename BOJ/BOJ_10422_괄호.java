import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10422_괄호 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
			
		long[] dp = new long[5001];
		dp[0] = dp[2] = 1;
		
		for (int i = 2; i <= 2500; i++) {
			for (int j = 0; j <= i-1; j++) {
				dp[i*2] += dp[j*2] * dp[(i-1-j)*2];
				dp[i*2] %= 1000000007L;
			}
		}
		
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb);
	}

}
