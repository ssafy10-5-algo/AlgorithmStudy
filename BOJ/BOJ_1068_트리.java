import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 76ms
public class BOJ_1068_트리 {

	private static int N, answer;
	static ArrayList<Integer>[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		answer = 0;
		
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		st = new  StringTokenizer(br.readLine());
		int root = -1;
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (input == -1) {		// -1 입력받으면 root 노드라는 뜻
				root = i;
				continue;
			}
			tree[input].add(i);		// 자식 노드들 저장시켜주기
		}
		
		int remove = Integer.parseInt(br.readLine());		// 제거할 노드
		if (remove == root) {				// 제거할 노드가 루트노드이면 0 출력 후 종료
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (tree[i].contains(remove)) {		// 제거할 노드가 자식노드이면
				tree[i].remove(tree[i].indexOf(remove));		// 제거할 노드 자식에서 없애주기
			}
		}
		
		tree[remove].clear();		// 제거할 노드의 자식들도 다 없애줌
		
		find(root);			// bfs
		System.out.println(answer);
	}

	private static void find(int root) {
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);		// 루트노드부터 시작
		
		while (!queue.isEmpty()) {
			int node = queue.poll();		// 자식 하나 뽑아서
			if (visited[node]) continue;	// 방문 체크 하고
			visited[node] = true;
			
			if (tree[node].size() == 0) {	// 그 노드가 자식 가지고 있지 않으면 리프노드라는 뜻
				answer++;			// 리프노드 1 증가
				continue;
			}
			queue.addAll(tree[node]);		// 자식 가지고 있으면 자식들 몽땅 queue에 저장
		}
	}

}
