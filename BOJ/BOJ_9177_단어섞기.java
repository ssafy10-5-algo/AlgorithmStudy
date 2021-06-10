import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_9177_단어섞기 {
	static int size1, size2, totSize;
	static String[] datas;
	static boolean flag, isExit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("Data set ").append(tc).append(": ");

			datas = br.readLine().split(" ");
			size1 = datas[0].length();		// 첫번째 단어 길이
			size2 = datas[1].length();		// 두번째 단어 길이
			totSize = size1 + size2;		// 단어 길이 총합
			flag = isExit = false;			// flag: 두단어 합친 것이 맞냐 아니냐, isExit: 단어에 쓰인 알파벳 맞지 않을경우 조기종료
			
			HashSet<Character> hs = new HashSet<Character>();
			for (int i = 0; i < 2; i++) {
				for (int j = 0, size=datas[i].length(); j < size; j++) {
					hs.add(datas[i].charAt(j));		// 단어에 쓰인 알파벳 조사하기위해 중복제거
				}
			}
			
			for (int i = 0; i < totSize; i++) {
				// 합친단어에 있는 알파벳이 두 단어 어디에도 없으면 조기종료
				if (!hs.contains(datas[2].charAt(i))) {		
					sb.append("no\n");
					isExit = true;
				}
			}
			if (isExit) continue;
			hs.clear();
			
			go(0, 0, 0);
			if (flag) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
			}
			
		}
		System.out.println(sb);
	}

	private static void go(int level, int idx1, int idx2) {
		if (level == totSize) {		// 합친단어 끝까지 도달했으면 성공
			flag = true;
			return;
		}
		
		if (flag) return;
		
		char find = datas[2].charAt(level);		// 찾을 알파벳
		
		if (idx1 < size1 && find == datas[0].charAt(idx1))		// 찾을 알파벳이 첫단어에 있으면
			go(level+1, idx1+1, idx2);			// 첫단어 인덱스 1증가
		if (idx2 < size2 && find == datas[1].charAt(idx2))		// 두번째 단어에 있으면
			go(level+1, idx1, idx2+1);			// 두번째 단어 인덱스 1증가
	}

}
