package parenthesis;

import java.util.Stack;

public class Validator {

    public static boolean checker(String s){

        Stack<Character> one = new Stack<>();

        for (int i = 0; i < s.length(); i++){

            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '('){
                one.push(s.charAt(i));
            }

            else if (s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')'){
                if(!one.isEmpty() && ((one.peek() == '{' && s.charAt(i) == '}') ||
                        (one.peek() == '[' && s.charAt(i) == ']') ||
                        (one.peek() == '(' && s.charAt(i) == ')'))){
                    one.pop();
                }
                else return false;
            }
        }

        return one.isEmpty();
    }

    public static void main(String[] args) {

        String s = "{{[{()}}]} ";
        System.out.println(checker(s));

    }

}
