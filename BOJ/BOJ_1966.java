import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_1966 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 문서 중요도 입력받기
			Queue<int[]> documents = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				documents.add(new int[] {Integer.parseInt(st.nextToken()), i});
			}

			int cnt = 0;
			while (!documents.isEmpty()) {
				boolean flag = true;
				int[] doc = documents.poll();		// 제일 앞에 있는 문서
				for (int[] d : documents) {
					if (doc[0] < d[0]) {			// 나머지 문서들 중 중요도 더 큰 문서가 있으면
						flag = false;			// flag = false로 바꿔준다
					}
				}
				
				if (flag) {			// 중요도 더 큰 문서 없으면
					cnt++;				// 문서 인쇄
					if (doc[1] == M) {			// 인쇄한 문서가 궁금한 문서였으면
						break;
					}
				} else {			// 중요도 더 큰 문서 있으면
					documents.add(doc);
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

}
