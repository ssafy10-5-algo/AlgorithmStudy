import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 84ms
public class BOJ_2629_양팔저울 {

	private static int N;
	private static int[] choo;
	private static boolean[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		choo = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			choo[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new boolean[N+1][55001];		// 추 최대 갯수 * 추 최대 무게 + 구슬 최대 무게
		
		dfs(0, 0);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int input = Integer.parseInt(st.nextToken());
			
			if (dp[N][input]) sb.append("Y ");
			else sb.append("N ");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int idx, int weight) {
		if (dp[idx][weight]) return;
		dp[idx][weight] = true;
		
		if (idx == N) return;
		
		dfs(idx+1, weight);			// 추 포함 X
		dfs(idx+1, weight + choo[idx]);				// 추 구슬 반대편에 놓기
		dfs(idx+1, Math.abs(weight - choo[idx]));		// 추 구슬편에 놓기
		
	}

}
