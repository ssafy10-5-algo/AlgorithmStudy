import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20440 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] mos = new int[N][2];
		
		int maxV = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			maxV = Math.max(maxV, end);
			
			mos[i][0] = start;
			mos[i][1] = end;
		}
		
		int[] cnt = new int[maxV];
		for (int i = 0; i < N; i++) {
			for (int j = mos[i][0]; j < mos[i][1]; j++) {
				cnt[j]++;
			}
		}
		
		int answer = 0;
		int from = 0, to = 0;
		boolean flag = false;
		for (int i = 0; i < maxV; i++) {
			if (answer < cnt[i]) {
				answer = cnt[i];
				from = i;
				to = i;
				flag = true;
			}
			
			if (answer == cnt[i] && flag) {
				to = i;
				flag = false;
			}
		}
		
		System.out.println(answer);
		System.out.println(from + " " + (to+1));
	}

}
