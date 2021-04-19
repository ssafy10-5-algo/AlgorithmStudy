import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 나무 재테크
// 1268ms

public class BOJ_16235 {
	static int N, M, K;
	static int[][] food, ground;
	static Queue<Tree> pq;
	static Queue<Tree> aliveTree;
	static Queue<Tree> deadTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());		// 땅 크기
		M = Integer.parseInt(st.nextToken());		// 나무 수
		K = Integer.parseInt(st.nextToken());		// 몇년 지나야 하는지

		food = new int[N][N];		// 겨울철 로봇이 뿌릴 양분
		ground = new int[N][N];		// 현재 땅에 있는 양분
		pq = new PriorityQueue<Tree>();			// 나무 위치와 나이 정보 저장할 pq
		aliveTree = new PriorityQueue<Tree>();
		deadTree = new LinkedList<Tree>();
		
		for (int i = 0; i < N; i++) {		// 로봇이 뿌릴 양분 입력받기
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(ground[i], 5);				// 제일 처음 땅 양분은 5로 초기화
		}
		
		for (int i = 0; i < M; i++) {				// 나무 위치, 나이 정보 입력받기
			st = new StringTokenizer(br.readLine(), " ");
			pq.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		
		for (int i = 0; i < K; i++) {			// K년 반복
			spring();			// 봄
			summer();
			fall();				// 가을
			winter();			// 겨울
		}
		
		System.out.println(pq.size());
	}

	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static void spring() {
		while(!pq.isEmpty()) {			// pq 돌면서
			Tree tree = pq.poll();
			if (ground[tree.x][tree.y] < tree.age) {		// 땅에 있는 양분이 나이보다 작을 때만
				deadTree.add(tree);			// 나무 죽는다
			} else {
				ground[tree.x][tree.y] -= tree.age;			// 나이만큼 양분을 먹고
				tree.age++;			// 나이도 한살 먹기
				aliveTree.add(tree);
			}
		}
	}
	
	private static void summer() {
		while (!deadTree.isEmpty()) {
			Tree tree = deadTree.poll();
			ground[tree.x][tree.y] += tree.age/2;				// 나이/2 만큼 양분으로 돌아감
		}
		
	}
	
	private static void fall() {
		while (!aliveTree.isEmpty()) {
			Tree tree = aliveTree.poll();
			
			if (tree.age % 5 == 0) {		// 나이가 5의 배수이면
				for (int i = 0; i < 8; i++) {
					int nx = tree.x + dx[i];
					int ny = tree.y + dy[i];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {		// 범위 체크
						pq.add(new Tree(nx, ny, 1));
					}
				}
			}
			pq.add(tree);
		}
	}
	
	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ground[i][j] += food[i][j];		// 땅에 영양분 추가
			}
		}
	}
}

class Tree implements Comparable<Tree>{
	int x, y, age;
	boolean life;
	
	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
		this.life = true;
	}

	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
}