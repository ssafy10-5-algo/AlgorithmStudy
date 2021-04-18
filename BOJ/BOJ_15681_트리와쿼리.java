import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15681_트리와쿼리 {

	private static int[] childCnt;
	private static ArrayList<Integer>[] link;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());		// 노드 갯수
		int R = Integer.parseInt(st.nextToken());		// 루트 노드
		int Q = Integer.parseInt(st.nextToken());		// 쿼리 갯수
		
		link = new ArrayList[N+1];		// 연결 정보 저장할 리스트
		for (int i = 0; i < N+1; i++) {
			link[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			link[x].add(y);
			link[y].add(x);
		}
		
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(R);
		visited[R] = true;
		
		childCnt = new int[N+1];
		while (!queue.isEmpty()) {			// 연결정보에서 자식만 남겨놓고 부모는 제외시키기 위한 과정
			int cur = queue.poll();
			int size=link[cur].size();
			
			for (int i = size-1; i >= 0; i--) {
				int next = link[cur].get(i);
				if (visited[next]) {		// 이미 방문한적 있다는 것은 현재 노드보다 부모라는 뜻
					link[cur].remove(i);	// 삭제
				} else {
					queue.add(next);
					visited[next] = true;
				}
				
			}
		}
		
		getChildCount(R);
		
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(childCnt[q]).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void getChildCount(int x) {		
		int size = link[x].size();
		if (size == 0) {
			childCnt[x] = 1;
			return;
		}
		
		childCnt[x]++;			// 자기 자신 포함
		for (int i = 0; i < size; i++) {
			getChildCount(link[x].get(i));
			childCnt[x] += childCnt[link[x].get(i)];		// 자식 노드가 몇개의 노드로 구성된 서브트리인지
		}
	}

}
