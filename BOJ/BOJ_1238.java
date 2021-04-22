import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파티
public class BOJ_1238 {
	static int N, M, X;
	static boolean[] visited;
	static ArrayList<Node>[] cost;
	static int INF = 100 * 10000 + 1;		// N * M 보다 커야함

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		
		cost = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			cost[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;		// 마을은 인덱스로 표현하기 위해 -1 해주고 저장
			int end = Integer.parseInt(st.nextToken())-1;
			int time = Integer.parseInt(st.nextToken());
			cost[start].add(new Node(end, time));
		}
		
		int[] total = new int[N];			// 왕복 시간 저장할 배열
		for (int i = 0; i < N; i++) {		// 첫 마을부터 끝 마을까지 파티장 가는 시간 구하기
			if (i == X) continue;			// 파티 개최하는 마을은 구할필요 없음
			visited = new boolean[N];
			dijstra(i);
			total[i] += d[X];
		}
		
		visited = new boolean[N];			// 파티장에서 각 마을로 돌아가는 시간 구하기
		dijstra(X);
		for (int i = 0; i < N; i++) {
			total[i] += d[i];
		}
			
		int answer = 0;
		for (int i = 0; i < N; i++) {				// 최대 왕복 시간 갱신
			answer = Math.max(answer, total[i]);
		}
		
		System.out.println(answer);
	}

	static int[] d;
	private static void dijstra(int start) {			// 다익스트라 알고리즘 이용
		d = new int[N];						// 걸리는 시간 저장해줄 배열
		Arrays.fill(d, INF);				// 무한대로 초기화
		for(Node node : cost[start]) {		// 현재 마을에 연결된 모든 마을에 대해서
			d[node.n] = node.time;			// 걸리는 시간 저장
		}
		
		for (int i = 0; i < N; i++) {		// 출발 도착 같은 마을이면 걸리는 시간 0
			if (i == start) {
				d[i] = 0;
			}
		}
		int index = start;				// 소요시간 제일 작은 노드의 인덱스 저정할 변수
		visited[start] = true;			// 현재 마을은 방문체크 해주고
		
		for (int count = 0; count < N-1; count++) {			// 모든 마을 갯수 - 1(현재 마을) 만큼 반복
			int minV = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {			// 소요시간 제일 작은 노드 찾기
				if (!visited[i] && d[i] < minV) {	// 방문한적 없고 소요시간 가장 작은 노드
					minV = d[i];
					index = i;
				}
			}
			
			visited[index] = true;			// 방문 체크
			for (int i = 0, size=cost[index].size(); i < size; i++) {		// 해당 마을과 연결된 마을만큼 반복
				Node node = cost[index].get(i);			// 연결된 마을 가져오기
				if (!visited[node.n]) {					// 연결된 마을 방문한적 없으면
					// 현재마을에서 해당마을까지 가는것 vs 현재마을에서 소요시간 최소인 마을 거쳐서 해당마을까지 가는것 중 작은값 갱신
					if (d[index] + node.time < d[node.n]) {
						d[node.n] = d[index] + node.time;
					}
				}
			}
		}
	}
	
	static class Node {
		int n, time;
		
		Node(int n, int time) {
			this.n = n;
			this.time = time;
		}
	}

}
