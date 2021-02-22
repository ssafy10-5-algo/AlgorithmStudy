import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2491 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int upCnt = 1;
		int downCnt = 1;
		int maxV = 0;
		
		// 배열 끝까지 반복하면서
		for (int i = 0; i < N-1; i++) {
			if (arr[i] < arr[i+1]) {			// 증가하는 부분이면
				++upCnt;			// 증가하는 수열 길이 1 증가
				maxV = Math.max(downCnt, maxV);		// 감소하는 부분의 더 큰 값 구해준 뒤
				downCnt = 1;		// 1로 초기화
			}
			else if (arr[i] > arr[i+1]) {		// 감소하는 부분도 마찬가지
				++downCnt;
				maxV = Math.max(upCnt, maxV);
				upCnt = 1;
			}
			else {			// 같으면 둘 다 1 증가
				++upCnt;
				++downCnt;
			}
		}
		
		maxV = Math.max(maxV, upCnt);
		maxV = Math.max(maxV, downCnt);
		
		System.out.println(maxV);
	}

}
