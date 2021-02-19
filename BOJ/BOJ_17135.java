import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 캐슬 디펜스
public class BOJ_17135 {
	static int N, M, D;
	static int[][] board;
	static int[] position = new int[3];
	static int cnt;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		N = input.charAt(0) - '0';
		M = input.charAt(2) - '0';
		D = input.charAt(4) - '0';
		
		board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				board[i][j] = input.charAt(index) - '0';
			}
		}
		
		combination(0, 0);
		System.out.println(answer);
	}

	// 궁수의 위치 조합으로 구하기
	private static void combination(int index, int start) {
		if (index == 3) {
			System.out.print(Arrays.toString(position) +" ");
			cnt = 0;
			killEnemy();
			System.out.println(cnt);
			answer = Math.max(answer, cnt);
			return;
		}
		
		for (int i = start; i < M; i++) {
			position[index] = i;
			combination(index+1, i+1);
		}
	}

	private static void killEnemy() {
		// 배열 하나 카피해서 쓰기
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(board[i], 0, tmp[i], 0, M);
		}
		
		for (int i = 0; i < N; i++) {
			int[][] nextKill = {{2, 2}, {2, 2}, {2, 2}};
			
			for (int j = 0; j < 3; j++) {		// 궁수 세명
				int minV = Integer.MAX_VALUE;
				
				for (int c = 0; c < M; c++) {
					for (int r = N-1; r >= 0; r--) {
						if (tmp[r][c] == 1) {
							int distance = Math.abs(position[j] - c) + N-r;
							if (distance > D) break;
							if (minV > distance) {
								minV = distance;
								nextKill[j][0] = r;
								nextKill[j][1] = c;
							}
						}
					}
				}
			}
			
			for (int j = 0; j < 3; j++) {
				int[] kill = nextKill[j];
				
				if (kill[0] == 2) continue;
				
				if (tmp[kill[0]][kill[1]] == 1) {
					tmp[kill[0]][kill[1]] = 0;
					++cnt;
				}
			}
			
			// 적들 한 줄씩 밑으로 내려옴
			for (int j = N-1; j >= i+1; j--) {
				for (int k = 0; k < M; k++) {
					tmp[j][k] = tmp[j-1][k];
				}
			}
			
			// 
			for (int j = 0; j < M; j++) {
				tmp[i][j] = 0;
			}
			
		}
	}
}
