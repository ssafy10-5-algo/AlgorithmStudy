import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603 {
	private static StringBuilder sb = new StringBuilder();
	private static int[] S;
	private static int[] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			if (K == 0) break;			// K가 0이면 종료
			
			S = new int[K];
			answer = new int[6];
			for (int i = 0; i < K; i++) {		// 집합 S 입력받기
				S[i] = Integer.parseInt(st.nextToken());
			}
			combination(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void combination(int index, int start) {
		if (index == 6) {			// 로또 번호 6자리 완성되면 출력하고 종료
			for (int i = 0; i < answer.length; i++) {
				sb.append(answer[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < S.length; i++) {
			answer[index] = S[i];
			combination(index+1, i+1);
		}
	}
}
