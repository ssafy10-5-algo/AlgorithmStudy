import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 여행가자
// 252ms

public class BOJ_1976 {
	static ArrayList<Integer>[] link;	// 도시연결 여부
	static boolean[] visited;			// 방문 체크
	static Stack<Integer> stack;		// dfs를 위한 스택
	static Queue<Integer> plan;			// 여행 계획

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		link = new ArrayList[N];		// 인자가 ArrayList로 된 배열 생성
		
		for (int i = 0; i < N; i++) {
			link[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N; i++) {		// 도시 연결
			String input = br.readLine();
			for (int j = i, index=i*2; j < N; j++, index+=2) {	// 현재 행 인덱스보다 작은 열 인덱스는 비교해줄 필요 없음
				if (input.charAt(index)-'0' == 1) {
					link[i].add(j);			// i와 j가 연결되어 있으면
					link[j].add(i);			// j와 i도 연결되어 있는 것
				}
			}
		}
		
		plan = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {		// 여행 계획 큐에 입력받기
			plan.add(Integer.parseInt(st.nextToken())-1);	// 편의를 위해 -1하고 넣어줌
		}
		
		stack = new Stack<Integer>();
		stack.add(plan.poll());				// 첫번째 여행지 큐에서 빼와서 스택에 넣기
		for (int i = 0; i < M-1; i++) {		// 마지막 여행지에 도착하면 큐에서 뺄거니까 마지막-1까지만 반복
			visited = new boolean[N];		// 방문체크 초기화
			if(dfs(i)) {					// dfs() 결과 참이면
				stack.clear();				// 스택에 남아있는 것 다 지워주고
				stack.add(plan.poll());		// 다음 여행지 탐색을 위해 여행계획 큐에서 빼서 스택에 넣어줌
			}
		}
		
		if (plan.isEmpty()) System.out.println("YES");		// 여행계획 다 비웠으면 YES!!!!
		else System.out.println("NO");
	}

	private static boolean dfs(int start) {
		
		while(!stack.isEmpty()) {
			int currentCity = stack.pop();			// 스택에서 도시 빼서 탐색 시작
			if (plan.peek() == currentCity) {		// 다음 여행 계획지와 현재 도시 같으면 true 반환하고 종료
				return true;
			}
			
			if (visited[currentCity]) continue;		// 이미 방문한 도시면 넘어감
			visited[currentCity] = true;			// 방문체크 해주고~
			
			stack.addAll(link[currentCity]);		// 다음 순회할 도시니까 스택에 넣어주기
		}
		return false;
	}

}
