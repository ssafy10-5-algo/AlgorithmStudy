import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class 메뉴리뉴얼 {

	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		
		solution(orders, course);
	}

	private static String[] solution(String[] orders, int[] course) {
		int[] lenCnt = new int[course[course.length - 1] + 1];		// 글자 길이당 가장많이 등장한 횟수 저장할 배열
		ArrayList<String> ans = new ArrayList<String>();		// 정답 문자열 저장할 리스트
		
		for (int i = 0, size1 = orders.length; i < size1; i++) {	// 모든 손님의 주문 탐색
			char[] charArr = orders[i].toCharArray();		// string을 char 배열로 만들어주기
			Arrays.sort(charArr);		// 정렬
			
			for (int j = 0, size2 = course.length; j < size2; j++) {	// 주인장이 원하는 단품요리 갯수만큼 다 구하기
				if (orders[i].length() < course[j]) break;		// 손님이 시킨 요리 갯수가 주인장이 원하는 요리 갯수보다 작으면 그만
				resultStr = new char[course[j]];		// 조합된 코스요리 저장해놓을 배열 객체 생성
				combination(charArr, 0, 0, course[j]);	// 조합 실행
			}
		}
		
		// 조합 다 구해져서 나왔으면
		for (String key : map.keySet()) {		// map 다 돌면서
			int tmp = map.get(key);
			if (tmp >= 2) {			// 해당 코스 두번 이상 등장했을때만 비교
				if (lenCnt[key.length()] < tmp) {		// 현재 코스의 등장 횟수가 이때까지 나온 코스 중 요리갯수가 같은 코스의 등장횟수 최대값보다 크면
					lenCnt[key.length()] = tmp;			// 현재 코스의 등장횟수로 갱신
				}
			}
		}
		
		for(String key : map.keySet()) {			// map 다시 돌면서
			if (map.get(key) >= 2 && lenCnt[key.length()] == map.get(key)) {	// 등장횟수 최대로 갱신되어 있는 그 코스요리이면
				ans.add(key);		// 정답 리스트에 넣기
			}
		}
		
		Collections.sort(ans);		// 정렬해주고
		String[] answer = new String[ans.size()];		// 리스트를 string으로 바꿔주기
        answer = ans.toArray(answer);
        
        return answer;
	}

	static char[] resultStr;
	static HashMap<String, Integer> map = new HashMap<String, Integer>();	// 코스요리 조합과 해당 코스요리가 몇번 등장했는지 저장
	private static void combination(char[] arr, int index, int start, int limit) {
		if (index == limit) {		// 코스요리 음식 갯수가 주인장이 원하는 갯수가 되면 종료
			String strArr = new String(resultStr);		// 코스요리 배열을 string으로 만들어주기
			if (map.containsKey(strArr)) {		// 이미 나온적 있는 코스면 카운트 1 증가
				map.put(strArr, map.get(strArr)+1);
			} else {		// 나온적 없는 코스면 map에 넣어주기
				map.put(strArr, 1);
			}
			return;
		}
		
		for (int i = start, size=arr.length; i < size; i++) {		// 조합 구하기
			resultStr[index] = arr[i];
			combination(arr, index+1, i+1, limit);
		}
	}

}
