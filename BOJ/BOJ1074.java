import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 72ms
public class BOJ1074 {
	static int r;
	static int c;
	static int N;
	static int order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int size = (int)Math.pow(2, N);		// 가로, 세로 사이즈
		
		recursion(size, 0, 0);		// 재귀 호출
	}
	
	static void recursion(int size, int x, int y) {
		// 종료 조건) 가로 세로 사이즈 2가 되면 getOrder함수 불러서 결과 얻고 종료
		if (size == 2) {
			getOrder(x, y);
			System.out.println(order-1);
			return;
		} 
		
		// 실행문
		// 네등분 했을 때 왼쪽 위에 있는 경우
		if (r < x+size/2 && c < y+size/2) {
			// 4칸이 한 묶음 이므로(최소 단위) 4의 N-1 제곱한 칸을 0개 지나쳐왔다는 의미
			order += (int)Math.pow(4, N-1)*0;
			N--;
			recursion(size/2, x, y);			// 칸 사이즈 반으로 줄여주고 재귀
			
		// 네등분 했을 때 오른쪽 위에 있는 경우
		} else if (r < x+size/2 && c >= y+size/2) {
			order += (int)Math.pow(4, N-1)*1;
			N--;
			recursion(size/2, x, y+size/2);
			
		// 네등분 했을 때 왼쪽 아래에 있는 경우
		} else if (r >= x+size/2 && c < y+size/2) {
			order += (int)Math.pow(4, N-1)*2;
			N--;
			recursion(size/2, x+size/2, y);
		
		// 네등분 했을 때 오른쪽 아래에 있는 경우
		} else {
			order += (int)Math.pow(4, N-1)*3;
			N--;
			recursion(size/2,  x+size/2, y+size/2);
		}
		
	}

	static int getOrder(int x, int y) {
		// 다음 방향 지정
		// Z자 모양이 될 수 있게 순서 설정
		int[] dx = {0, 0, 1, 1};
		int[] dy = {0, 1, 0, 1};
		
		for (int i = 0; i < 4; i++) {			// Z자 모양으로 반복하면서
			if (x + dx[i] == r && y + dy[i] == c) {		// 찾으려는 칸 나오면
				++order;
				return order;				// 순서 하나 증가해주고 종료
			}
			++order;			// 찾으려는 칸 아니면 그냥 순서만 증가
		}
		return 0;
	}
}
