
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_20304 {

	public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			Deque<Integer> queue = new ArrayDeque<Integer>();
			int[] arr = new int[N+1];
			Arrays.fill(arr, -1);		// 안전거리 저장할 배열 -1로 초기화(방문 체크 기능도 함)
			
			st = new StringTokenizer(br.readLine());
			int p;
			for (int i = 0; i < M; i++) {
				p = Integer.parseInt(st.nextToken());
				queue.add(p);
				arr[p] = 0;		// 관리자 비밀번호와 시도된 비밀번호 같으면 안전거리 0
			}
			
			int num = 0, target = 0;
			int answer = 0;
			while (!queue.isEmpty()) {		// queue 다 빌때까지 실행
				num = queue.poll();			// queue에서 값 하나 빼서
				for (int i = 0; i < 20; i++) {		// 20번 수행
					target = num ^ (1<<i);			// 현재 값과, 이진수가 1이 하나만 있는 수를 XOR 연산
					//if (target > N) break;			// 연산 결과가 N보다 크면 더 볼 필요 없다
					if (target > N || arr[target] != -1) continue;		// 해당 수가 -1보다 크면 이미 방문한적 있는 것
					arr[target] = arr[num] + 1;		// 현재 값과 안전거리가 1이라는 뜻
					queue.add(target);				// 다음 비교를 위해 queue에 넣어줌
					answer = Math.max(answer, arr[target]);		// 최대값 갱신
				}
			}
			System.out.println(answer);
	}

}
