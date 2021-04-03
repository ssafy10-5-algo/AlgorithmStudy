import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5557_1학년 {

	private static int N, targetNum, answer = 0;
	private static int[] arr;
	private static int total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		total = 0;
		
		
		String input = br.readLine();
		for (int i = 0, idx=0; i < arr.length; i++) {
			arr[i] = input.charAt(idx) - '0';
			idx += 2;
			
			if (i == N-1) continue;
			total += arr[i];
		}
		targetNum = total - (total - arr[N-1]) / 2;
		
		visited = new boolean[N];
		subset(0, 0, 0, 0);
		System.out.println(answer);
	}

	static boolean[] visited;
	private static void subset(int idx, int start, int addNums, int subNums) {
		if (addNums+subNums < 0 || addNums+subNums > 20) 
			return;
		
		if (idx == N-1) {
			if (addNums == targetNum) {
				answer++;
				return;
			}
		}
		
		for (int i = start; i < N-1; i++) {
			subset(idx+1, i+1, addNums+arr[i], subNums);		// 더하기
			subset(idx+1, i+1, addNums, subNums-arr[i]);			// 빼기
		}
	}
}
