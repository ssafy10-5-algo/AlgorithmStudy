import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] list = new ArrayList[V+1];
		for (int i = 0; i < V+1; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Edge(v, w));
		}
		
		int[] D = new int[V+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[K] = 0;
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(K, 0));
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
			int curNode = current.n;
			
			if (visited[curNode]) continue;
			visited[curNode] = true;
			
			for (Edge edge : list[curNode]) {
				if (D[edge.n] > D[curNode] + edge.weight) {
					D[edge.n] = D[curNode] + edge.weight;
					pq.add(new Edge(edge.n, D[edge.n]));
				}
			}
		}
		
		for (int i = 1; i <= V; i++)  {
			if (D[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(D[i]);
		}
		
	} 	// end of main()
	

	static class Edge implements Comparable<Edge>{
		int n, weight;
		
		public Edge(int n, int weight) {
			super();
			this.n = n;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
		
	}
}	 // end of main class

