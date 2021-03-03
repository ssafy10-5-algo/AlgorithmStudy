import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
	static int N, S;
	static int limit;
	static int[] arr;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			limit = i;
			combination(0, 0, 0);
		}
		//subSet(0, 0, 0, 0);
		System.out.println(answer);
	}

	private static void combination(int cnt, int start, int sum) {
		if (cnt == limit) {
			if (sum == S) {
				sum = 0;
				answer++;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			combination(cnt+1, i+1, sum+arr[i]);
		}
	}

}
