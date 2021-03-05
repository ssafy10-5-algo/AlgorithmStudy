import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RGB거리 2
// 96ms
public class BOJ_17404 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N][3];
		int answer = Integer.MAX_VALUE;
		
		int[][] rgb = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int first = 0; first < 3; first++) {
			for (int i = 0; i < 3; i++) {				// 첫번째 집의 색을 고정해놓고 나머지색은 무한대로 줌
				if (first == i) dp[0][i] = rgb[0][i];
				else {
					dp[0][i] = 1000*N+1;
				}
			}
			
			for (int i = 1; i < N; i++) {	// 전 순서까지 비용의 최소 + 현 순서의 비용
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + rgb[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + rgb[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + rgb[i][2];
			}
			
			for (int i = 0; i < 3; i++) {
				if (first == i) continue;	// 마지막 값이 첫값과 같으면 안됨
				else {
					answer = Math.min(answer, dp[N-1][i]);
				}
			}
		}
		
		System.out.println(answer);
	}
}
