package 栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 思路：
 * 1. 遇见左字符 入栈
 * 2. 遇见右字符，匹配，弹栈
 * 如果第一个就遇见右字符，不合法
 */
public class _20_有效的括号 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }


    /**
     * 由于字符串是不可变的，比较耗费性能
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        while (s.contains("{}") || s.contains("()") || s.contains("[]")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }


    public static boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            //第一个是左括号，入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 说明遇到了右括号，判断此时栈是否为空,为空，说明第一个字符就是右括号，不符合
                if (stack.isEmpty()) return false;
                // 左括号弹栈
                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
            }
        }
        return stack.isEmpty();
    }

    private static HashMap<Character, Character> map = new HashMap<>();

    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    /**
     * 借助HashMap
     *
     * @param s
     * @return
     */
    public static boolean isValid4(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            //第一个是左括号，入栈
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                // 说明遇到了右括号，判断此时栈是否为空,为空，说明第一个字符就是右括号，不符合
                if (stack.isEmpty()) return false;
                // 左括号弹栈
                char left = stack.pop();
                if (map.get(left) != c) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "()[]";
        System.out.println(isValid4(s));
    }
}
