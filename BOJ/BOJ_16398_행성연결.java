import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BOJ_16398_행성연결 {
	static class Edge implements Comparable<Edge>{
		int x, y, cost;

		public Edge(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
		
	}

	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input != 0) {
					pq.add(new Edge(i, j, input));
				}
			}
		}
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		long answer = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			int xRoot = find(edge.x);
			int yRoot = find(edge.y);
			if (xRoot != yRoot) {
				answer += edge.cost;
				union(xRoot, yRoot);
			}
		}
		
		System.out.println(answer);
	}
	
	private static void union(int x, int y) {
		if (x < y) parent[y] = x;
		else parent[x] = y;
	}

	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		return parent[x] = find(parent[x]);
	}

}
