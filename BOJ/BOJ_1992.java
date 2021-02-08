import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1992 {
	static int[][] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int size = Integer.parseInt(br.readLine());
		arr = new int[size][size];
		
		// 배열 입력받기
		for (int i = 0; i < size; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		solve(0, 0, size);
		System.out.println(sb);
	}
	
	static void solve(int x, int y, int size) {
		// 배열 칸 수 1이면 출력하고 종료
		if (size == 1) {
			sb.append(arr[x][y]);
			return;
		}
		
		
		int target = arr[x][y];				// 해당 칸 모두 같은 수인지 비교할 제일 첫 숫자 받아오기
		boolean flag = true;				// 다른 숫자를 만나는 순간 false로 바뀔 flag 변수 생성
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				if (target != arr[i][j]) {			// 다른 숫자를 만나면
					flag = false;					// flag 변수 false로 바뀜
				}
			}
		}
		
		if (flag) {						// 다른 숫자를 만난적이 없으면 (-> 해당 범위의 칸이 모두 같은 숫자이면)
			sb.append(target);			// 그 칸의 숫자 출력
		} else {						// 범위 안에 숫자가 섞여있으면
			sb.append("(");				// 괄호 열기
			solve(x, y, size/2);					// 왼쪽 위
			solve(x, y+size/2, size/2);				// 오른쪽 위
			solve(x+size/2, y, size/2);				// 왼쪽 아래
			solve(x+size/2, y+size/2, size/2);		// 오른쪽 아래로 범위 반으로 줄여서 재귀
			sb.append(")");			// 괄호 닫기
		}
	}

}
