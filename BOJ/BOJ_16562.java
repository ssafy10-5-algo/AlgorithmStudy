import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 친구비
// 368ms
public class BOJ_16562 {
	static int N;
	private static boolean[] visited;
	private static int[] cost;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		cost = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cost.length; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;			// 초기 자기 자신의 부모는 자신
		}
		
		for (int i = 0; i < M; i++) {
			st  = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			union(x, y);
		}
		
		int money = 0;
		for (int i = 0; i < N; i++) {		// 비용 계산
			int root = find(i);	
			if (visited[root]) continue;
			visited[root] = true;
			money += cost[root];
		}
		
		if (money > k) System.out.println("Oh no");
		else System.out.println(money);
		
	}

	private static void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if (xRoot == yRoot) {			// 부모 같으면 이미 합쳐져 있는 것
			return;
		}
		
		if (cost[xRoot] < cost[yRoot]) {			// 비용 작은 것 찾아서
			for (int i = 0; i < N; i++) {			// 비용 큰 노드를 부모로 가지고 있는 노드 모두 부모를 바꾸어줌
				if (parent[i] == yRoot) parent[i] = xRoot;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (parent[i] == xRoot) parent[i] = yRoot;
			}
		}
	}

	private static int find(int k) {
		if (parent[k] == k) return k;
		
		return parent[k] = find(parent[k]);
	}


}
