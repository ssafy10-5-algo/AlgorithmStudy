import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		
		PriorityQueue<Integer> cards = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			cards.add(Integer.parseInt(br.readLine()));
		}
		
		int temp = 1;		// 카드 묶음이 한 묶음일 때를 위해 temp=1로 설정
		while (!cards.isEmpty()) {
			if (cards.size() == 1) {		// 카드 한 묶음되면
				sum -= temp;				// 제일 마지막에 더해진 카드묶음 각각의 비교횟수는 sum에 더해줄 필요 없으므로 빼주고 종료
				break;
			}
			temp = cards.poll() + cards.poll();		// 비교횟수 가장 작은 두 묶음 더해서
			sum += temp;		// 카드 각각의 비교 횟수도 더해주어야 하므로 sum에 합치기 위한 비교횟수 더해줌
			cards.add(temp);	// 두 묶음을 합할때 필요한 비교횟수 cards에 넣어주기
		}
		
		System.out.println(cards.poll() + sum);
	}

}
