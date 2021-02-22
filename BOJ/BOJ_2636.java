import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 92ms
public class BOJ_2636 {
	static int[][] board;
	static int r;
	static int c;
	static int count;
	static int hour = 0;
	static Queue<int[]> cheese = new LinkedList<>();
	static Queue<int[]> air = new LinkedList<int[]>();


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		board = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 입력받기 완료=======================================
		
		air.add(new int[] {0, 0});		// (0, 0)은 무조건 공기
		check();		// 공기와 공기에 접촉된 치즈 분류
		while(!cheese.isEmpty()) {
			count = cheese.size();			// 최후의 치즈 구해주기 위한 변수
			melt();				// 공기에 접촉된 치즈 녹이기
			check();			// 공기에 접촉된 치즈를 공기로 바꿔준 뒤 해당 부분만 다시 분류
		}
		
		System.out.println(hour + "\n" + count);
	}
	
	static void melt() {
		++hour;				// 치즈 녹이는 함수 한 번 들어올 때마다 1시간 지난 것
		int tmp = cheese.size();
		for (int i = 0; i < tmp; i++) {
			int[] meltingCheese = cheese.poll();		// 녹고있는 치즈
			board[meltingCheese[0]][meltingCheese[1]] = -1;		// 치즈 -> 공기로 바꿔줌
			air.add(new int[] {meltingCheese[0], meltingCheese[1]});
		}
	}
	
	static void check() {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		
		while (!air.isEmpty()) {
			int[] target = air.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = target[0] + dx[i];
				int ny = target[1] + dy[i];
				if (nx >= 0 && nx < r && ny >= 0 && ny < c) {		// 다음 방문할 곳이 범위 내에 있고
					if (board[nx][ny] == 0) {		// 공기 부분이면
						board[nx][ny] = -1;			// -1로 바꾸어줌 (0은 구멍을 표시하는 것으로 쓸 예정)
						air.add(new int[] {nx, ny});
					}
					if (board[nx][ny] == 1) {		// 치즈 부분이면
						board[nx][ny] = 2;			// 방문 체크 기능 (바로 다음 차례에 녹을 치즈라는 표시)
						cheese.add(new int[] {nx, ny});		// 녹일 치즈에 저장
					}
				}
			}
		}
	}

}
