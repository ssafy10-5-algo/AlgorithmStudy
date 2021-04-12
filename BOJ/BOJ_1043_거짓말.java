import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 84ms
public class BOJ_1043_거짓말 {

	private static int[] parent;
	static int p = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());		// 사람 수
		int M = Integer.parseInt(st.nextToken());		// 파티 수
		
		st = new StringTokenizer(br.readLine());
		int knowCnt = Integer.parseInt(st.nextToken());		// 진실을 아는 사람 수
		
		if (knowCnt == 0) {				// 진실 아는 사람 아무도 없으면 파티 모두 거짓말 성공
			System.out.println(M);
			return;
		}
		
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {			// 초기 자신의 부모는 자신
			parent[i] = i;
		}
		
		boolean flag = false;
		int answer = 0;
		for (int i = 0; i < knowCnt; i++) {		// 진실을 아는 사람 중 제일 첫번째 사람을 root로 생각하기
			int input = Integer.parseInt(st.nextToken());
			if (!flag) {		// root 아직 안정해졌으면 (-> 진실 아는 첫사람이면)
				p = input;		// root 지정
			}
			
			parent[input] = p;	// 진실 아는 사람들끼리는 root를 부모로 지정해줌
			flag = true;
		}
		
		ArrayList<Integer>[] partyPeople = new ArrayList[M];		// 각 파티마다 모이는 사람 저장할 리스트
		for (int i = 0; i < M; i++) {
			partyPeople[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int personCnt = Integer.parseInt(st.nextToken());		// 각 파티의 사람 수
			
			for (int j = 0; j < personCnt; j++) {
				partyPeople[i].add(Integer.parseInt(st.nextToken()));
			}
			
			for (int j = 0; j < personCnt-1; j++) {			// 한 파티에 모인 사람들 전부 union
				union(partyPeople[i].get(j), partyPeople[i].get(j+1));
			}
		}
		
		for (int i = 0; i < M; i++) {			// 부모가 root가 아니면 진실 모르는 사람들만 있는 파티
			if (find(partyPeople[i].get(0)) != p)
				answer++;
		}
		
		System.out.println(answer);
		
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return;
		
		if (rootA == p)
			parent[rootB] = rootA;
		else
			parent[rootA] = rootB;
		
	}
	
	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		int p = find(parent[x]);
		parent[x] = p;
		return p;
	}
}
