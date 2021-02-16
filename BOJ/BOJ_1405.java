import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 미친 로봇
// 136ms
public class BOJ_1405 {
	static int N;
	static int[] percent;
	static double answer;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static double value;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		percent = new int[4];
		visited = new boolean[N*2+1][N*2+1];
		
		// 퍼센트 입력받기
		for (int i = 0; i < 4; i++) {
			percent[i] = Integer.parseInt(st.nextToken());
		}
		
		// 보드의 제일 중앙에서 시작
		permutation(0, 1, N, N);
		
		System.out.println(answer);
	}
	
	static void permutation(int cnt, double result, int x, int y) {
		if (cnt == N) {			// 로봇 움직일만큼 다 움직였으면
			if (visited[x][y]) {	// 방문한적 있는 곳이면 단순한 것 아님
				return;
			}
			answer += result;	// 방문한적 없으면 정답 변수에 확률 더해줌
			return;
		}
		
		if (visited[x][y]) {	// 움직이는 중간에라도 왔던 곳 또 오면 종료
			return;
		}
		visited[x][y] = true;	// 왔던 곳 아니면 방문 표시해주고
		
		for (int i = 0; i < 4; i++) {		// 동서남북으로 움직인다
			value = percent[i] * 0.01;			// 해당 방향에 대한 확률 구해주기
			permutation(cnt+1, result*value, x+dx[i], y+dy[i]);
		}
		
		visited[x][y] = false;
	}
}
