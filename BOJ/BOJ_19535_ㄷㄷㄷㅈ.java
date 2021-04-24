import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_19535_ㄷㄷㄷㅈ {

	private static int N, dCnt, gCnt;
	private static ArrayList<Integer>[] link;
	private static Stack<Integer> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		link = new ArrayList[N+1];		// 연결 정보
		for (int i = 0; i < N+1; i++) {
			link[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		
		for (int i = 1; i < N+1; i++) {
			//stack = new Stack<Integer>();
			//stack.add(i);
			visited = new boolean[N+1];
			arr[0] = i;
			visited[i] = true;
			dfs(i, 1);
		}
		
		if (dCnt > gCnt * 3) System.out.println('D');
		if (dCnt < gCnt * 3) System.out.println('G');
		if (dCnt == gCnt * 3) System.out.println("DUDUDUNGA");
	}

	static int[] arr = new int[4];
	static boolean[] visited;
	private static void dfs(int start, int idx) {
		if (idx == 4) {
			getLinkCnt();
			
			int maxLink = 0;
			for (int i = 0; i < 4; i++) {
				maxLink = Math.max(maxLink, count[i]);
			}
			
			if (maxLink == 2)
				++dCnt;
			else if (maxLink == 3)
				++gCnt;

			return;
		}
		
		for (int i = 0, size=link[start].size(); i < size; i++) {
			int next = link[start].get(i);
			if (visited[next]) continue;
			arr[idx] = next;
			visited[next] = true;
			dfs(next, idx+1);
		}
	}
	
	static int[] count = new int[4];
	private static void getLinkCnt() {
		for (int i = 0; i < 4; i++) {
			int cur = arr[i];
			for (int j = 0, size=link[cur].size(); j < size; j++) {
				int candidate = link[cur].get(j);
				for (int k = 0; k < 4; k++) {
					if (arr[k] == cur) continue;
					if (arr[k] == candidate) {
						count[i]++;
					}
				}
			}
		}
	}

}
