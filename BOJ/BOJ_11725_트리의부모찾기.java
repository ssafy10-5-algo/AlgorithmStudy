import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] list = new ArrayList[N+1];		// 연결 노드 저장
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		
		int[] answer = new int[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();		// 다음 탐색할 노드 저장
		queue.add(1);		// 1이 루트 노드
		answer[1] = -1;		// 이 문제에서는 쓰지않는 수로 초기화
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0, size=list[cur].size(); i < size; i++) {
				int node = list[cur].get(i);
				if (answer[node] != 0) continue;			// 이미 부모 정해졌으면 패스~!
				answer[node] = cur;			// 부모 저장해주고
				queue.add(node);			// 해당 노드가 부모일 수도 있는 노드들 탐색하기 위해 queue에 넣기
			}
		}
		
		for (int i = 2; i < N+1; i++) {
			System.out.println(answer[i]);
		}
	}

}
