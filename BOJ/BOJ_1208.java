import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1208 {
	static int N, S;
	static int[] arr;
	static long answer = 0;
	static Map<Long, Long> map = new HashMap<Long, Long>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, N/2, 0, 0);		// left
		dfs(N/2, N, 0, 1);		// right
		
		if (S == 0) {
			answer -= 1;
		}
		
		System.out.println(answer);
	}

	private static void dfs(int s, int e, long tmp, int flag) {
		if (s == e && flag == 0) {
			if (!map.containsKey(tmp)) {
				map.put(tmp, 0L);
			}
			long val = map.get(tmp);
			map.put(tmp, val+1);
			return;
		} else if (s == e && flag == 1) {
			if (map.containsKey(S-tmp)) {
				answer += map.get(S-tmp);
			}
			return;
		}
		dfs(s+1, e, tmp+arr[s], flag);
		dfs(s+1, e, tmp, flag);
	}

}
