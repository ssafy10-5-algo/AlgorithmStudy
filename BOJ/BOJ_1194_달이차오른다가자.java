import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {

	private static int N, M, answer = Integer.MAX_VALUE;
	private static char[][] map;
	private static Queue<int[]> queue;
	static List<Character> keys = new ArrayList<Character>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		queue = new LinkedList<>();
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {				// 민식이가 있는 곳이면
					queue.add(new int[] {i, j});	// 큐에 저장해놓고
					map[i][j] = '.';				// 빈곳으로 바꾸어주기
				}
			}
		}
		
		answer = bfs() ? answer : -1;
		System.out.println(answer);
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int cnt = 0;
	private static boolean bfs() {
		while (!queue.isEmpty()) {
			cnt++;
			int x = queue.peek()[0];
			int y = queue.poll()[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; 	// 범위 체크
				
				if (map[nx][ny] == '#') continue; 		// 벽이면 무시
				else if (map[nx][ny] == '.') {
					queue.add(new int[] {nx, ny}); 		// 빈곳이면 큐에 저장
				}
				else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {	// 열쇠이면 열쇠 주워서 리스트에 저장
					keys.add(map[nx][ny]);
					queue.add(new int[] {nx, ny});
				}
				else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
					// 문에 해당하는 키를 주운적 있으면 지나다닐 수 있으므로 빈칸으로 만들어줌
					if (keys.contains((char)(map[nx][ny] + 32))) {
						map[nx][ny] = '.';
						queue.add(new int[] {nx, ny});
					}
					else continue;		// 키 주운적 없으면 못지나감
				}
				else if (map[nx][ny] == '1') {
					answer = Math.min(answer, cnt);
					return true;
				}
			}
		}
		return false;
	}

}
