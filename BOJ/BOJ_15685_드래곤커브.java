import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {

	static final int SIZE = 101;
	static int N, cnt = 0;
	static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};		// x, y 바꿔서 입력받음. 우상좌하 순서
	static boolean[][] visited;
	static LinkedList<Integer> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		visited = new boolean[SIZE][SIZE];
		N = Integer.parseInt(br.readLine());
		
		int x, y, d, g;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			list = new LinkedList<Integer>();
			list.add(d);
			curveMake(g);
			drawCurveMap(x, y);
		}
		
		check();
		System.out.println(cnt);
	}

	private static void check() {
		for (int i = 0; i < SIZE-1; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]) cnt++;
			}
		}
	}

	private static void drawCurveMap(int x, int y) {
		int nx = x, ny = y;
		int size=  list.size();
		
		visited[x][y] = true;
		
		for (int i = 0; i < size; i++) {
			int d = list.get(i);
			
			nx += dx[d]; ny += dy[d];
			visited[nx][ny] = true;
		}
	}

	private static void curveMake(int curve) {
		for (int i = 0; i < curve; i++) {
			int size = list.size();
			
			for (int j = 0; j <= size; j++) {
				list.add((list.get(size-i) + 1) % 4);
			}
		}
	}

}
