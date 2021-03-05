import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파일 합치기
public class BOJ_11066 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for (int tc = 0; tc < testCase; tc++) {
			int K = Integer.parseInt(br.readLine());
			int[] cost = new int[K+1];
			int[] sum = new int[K+1];
			int[][] dp = new int[K+1][K+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + cost[i];
			}
			
			for (int i = 1; i <= K; i++) {
				for (int j = 1; i + j <= K; j++) {
					dp[j][i+j] = Integer.MAX_VALUE;
					for (int k = j; k < i+j; k++) {
						dp[j][i+j] = Math.min(dp[j][i+j], dp[j][k] + dp[k+1][i+j] + sum[i+j] - sum[j-1]);
					}
				}
			}
			
			System.out.println(dp[1][K]);
		}
	}

}
