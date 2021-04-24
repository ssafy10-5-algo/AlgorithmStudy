import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_19535_ㄷㄷㄷㅈ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] link = new ArrayList[N+1];		// 연결 정보
		boolean[] visited = new boolean[N+1];
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
		
		long D = 0, G = 0;
		
		// ㅈ
		for (int i = 1; i <= N; i++) {
			if (link[i].size() >= 3) {
				long n = link[i].size();
				G += n * (n-1) * (n-2) / 6;
			}
		}
		
		// ㄷ
		for (int i = 1; i <= N; i++) {
			long child = link[i].size() - 1;
			
			for (int j = 0, size=link[i].size(); j < size; j++) {
				if (visited[link[i].get(j)]) continue;
				
				D += child * (link[link[i].get(j)].size() - 1);
			}
			
			visited[i] = true;
		}
		
		
		if (D > G*3) System.out.println("D");
		if (D < G*3) System.out.println("G");
		if (D == G*3) System.out.println("DUDUDUNGA");
		
	}

}
