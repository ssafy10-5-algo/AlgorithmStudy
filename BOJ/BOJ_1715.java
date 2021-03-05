import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_1715{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		
		PriorityQueue<Integer> cards = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			cards.add(Integer.parseInt(br.readLine()));
		}
		
		int temp = 1;
		while (!cards.isEmpty()) {
			if (cards.size() == 1) {
				sum -= temp;
				break;
			}
			temp = cards.poll() + cards.poll();
			sum += temp;
			cards.add(temp);
		}
		
		System.out.println(cards.poll() + sum);
	}

}
