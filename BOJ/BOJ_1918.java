import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 후위 표기식
public class BOJ_1918 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String exp = br.readLine();
		Stack<Character> stk = new Stack<Character>();
		
		for (int i = 0; i < exp.length(); i++) {
			// 문자면 출력
			if (exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z') {
				sb.append(exp.charAt(i));
			} 
			
			// 여는 괄호이면 스택에 저장
			else if (exp.charAt(i) == '(') {
				stk.push(exp.charAt(i));
			} 
			
			// 닫는 괄호이면 여는 괄호가 나올 때까지 스택에서 pop해서 출력
			else if (exp.charAt(i) == ')') {
				while (!stk.isEmpty()) {
					if (stk.peek() == '(') {
						stk.pop();
						break;
					}
					sb.append(stk.pop());
				}
			}
			
			// +, - 연산자이면 스택에 저장되어 있는 모든 연산자를 pop해서 출력한 후 스택에 저장
			else if (exp.charAt(i) == '+' || exp.charAt(i) == '-') {
				while (!stk.isEmpty() && (stk.peek() == '*' || stk.peek() == '/' || stk.peek() == '+' || stk.peek() == '-')) {
					sb.append(stk.pop());
				} 
				stk.push(exp.charAt(i));
			}
			
			// *, / 연산자이면 스택에 제일 위 연산자가 *, / 인 경우 pop해서 출력한 뒤 저장하고, 아니면 그냥 스택에 저장
			else {
				while (!stk.isEmpty() && (stk.peek() == '*' || stk.peek() == '/')) {
					sb.append(stk.pop());
				} 
				stk.push(exp.charAt(i));
			}
		}
		
		// 스택에 남은 연산자 모두 출력
		while (!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		
		System.out.println(sb);
	}

}
