import java.util.Arrays;

public class 자물쇠와_열쇠 {
	

	public static void main(String[] args) {
		int[][] key = {
				{0, 0, 0},
				{1, 0, 0},
				{0, 1, 1}
		};
		int[][] lock = {
				{1, 1, 1},
				{1, 1, 0},
				{1, 0, 1},
		};
		
		System.out.println(solution(key, lock));
	}

	private static int N;
	private static int M;
	private static int[][] rotateKey;
	private static int[][] broadKey;
	
	private static boolean solution(int[][] key, int[][] lock) {
		N = lock.length;
		M = key.length;
		
		for (int i = 0; i < 4; i++) {
			rotateKey = new int[M][M];
			rotate(i, key); 			// 0, 90, 180, 270도 회전
			expand();			// 확장으로 이동 효과

			if (check(lock)) {		// 만들어진 key가 자물쇠에 들어맞는지 확인
				return true;
			}
		}
		
		return false;
	}

	private static void rotate(int status, int[][] key) {
		if (status == 0) {			// 0도
			rotateKey = key;
			return;
		}
		if (status == 1) {			// 90도
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					rotateKey[i][j] = key[M-1-j][i];
				}
			}
		} else if (status == 2) {		// 180도
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					rotateKey[i][j] = key[M-1-i][M-1-j];
				}
			}
		} else if (status == 3) {		//  270도
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					rotateKey[i][j] = key[j][M-1-i];
				}
			}
			
		}
	}

	static int size;
	private static void expand() {
		size = M + (N-1)*2;
		//System.out.println(size);
		broadKey = new int[size][size];		// 확장시킬 키 생성
		
		for (int i =0; i < M; i++) {				// 회전시킨 키 중앙에 넣어주기
			for (int j = 0; j < M; j++) {
				broadKey[N - 1 + i][N - 1 + j] = rotateKey[i][j];
			}
		}
	}

	private static boolean check(int[][] lock) {
		for (int i = 0; i < size-(N-1); i++) {
			for (int j = 0; j < size-(N-1); j++) {
				boolean flag = true;
				loop:
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (broadKey[i+r][j+c] == lock[r][c]) {			// 돌기-돌기, 홈-홈 끼리 만나면 안됨
							flag = false;
							break loop;
						}
					}
				}
				if (flag) return true;
			}
		}
		return false;
	}

}
