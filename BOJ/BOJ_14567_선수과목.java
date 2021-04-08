import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 524ms
public class BOJ_14567_선수과목 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {					// 각 과목의 선수과목을 list에 저장
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[b].add(a);
		}
		
		int[] dp = new int[N+1];
		dp[1] = 1;				// 1번 과목은 1학기에 들을 수 있다
		
		for (int i = 2; i < N+1; i++) {			// 2번 과목부터
			int size = list[i].size();
			if (size == 0) dp[i] = 1;			// 저장된 선수과목 없으면 1학기에 수강 가능
			
			for (int j = 0; j < size; j++) {
				dp[i] = Math.max(dp[list[i].get(j)] + 1, dp[i]);		// 저장된 선수과목이 몇학기에 들을 수 있는지 + 1 중 최대값
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			sb.append(dp[i]).append(" ");
		}
		
		System.out.println(sb);
	}

}
