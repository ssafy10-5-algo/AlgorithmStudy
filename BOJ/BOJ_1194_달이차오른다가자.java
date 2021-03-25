import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 104ms
public class BOJ_1194_달이차오른다가자 {

	private static int N, M;
	private static char[][] map;
	private static Queue<Point> queue;
	static List<Character> keys = new ArrayList<Character>();
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		// 세로
		M = Integer.parseInt(st.nextToken());		// 가로
		
		queue = new LinkedList<>();				// bfs에 이용할 큐
		visited = new boolean[N][M][1<<6];		// 어떤 키를 가지고 방문했는지 체크할 배열
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {				// 민식이가 있는 곳이면
					visited[i][j][0] = true;
					queue.add(new Point(i, j, 0));	// 큐에 저장
				}
			}
		}
		
		bfs();
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		int cnt = 0;
		while (!queue.isEmpty()) {
			// 현재 큐에 있는 요소 다 비우면 그게 움직임 한번
			for (int s = 0, size=queue.size(); s < size; s++) {
				Point current = queue.poll();
				int x = current.x;
				int y = current.y;
				int key = current.key;
				
				if (map[x][y] == '1') {		// 탈출
					System.out.println(cnt);
					return;
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i], ny = y + dy[i];
					int copy = key;
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][copy]) continue; 	// 범위, 방문 체크
					
					if (map[nx][ny] == '#') continue; 		// 벽이면 무시
					
					else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {	// 열쇠
						copy |= (1 << map[nx][ny] - 'a');
					}
					
					else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z')		// 문
						if ((copy & (1 << map[nx][ny] - 'A')) == 0) continue;
					
					visited[nx][ny][copy] = true;			// 방문체크하고
					queue.add(new Point(nx, ny, copy));		// 다음 방문 후보이므로 큐에 넣어줌
				}
			}
			cnt++;
				
		}
		System.out.println(-1);
	}

}

class Point {
	int x, y, key;

	public Point(int x, int y, int key) {
		this.x = x;
		this.y = y;
		this.key = key;
	}
	
	
}
