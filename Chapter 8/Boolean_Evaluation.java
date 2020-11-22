package Chapter_8;

import java.util.HashMap;

public class Boolean_Evaluation
{
    /*
     * My Approach
     * Time: O(n^3)
     * Space: O(n^2)
     */
    static int countParenthesis(char[] symbol, char[] oper, int n)
    {
        int F[][] = new int[n][n];
        int T[][] = new int[n][n];

        //fill diaginal first and all T[i][i] are 1 if symbol[i] is T likewise F[i][i] for false
        for(int i = 0; i < n; i++)
        {
            F[i][i] = (symbol[i] == 'F') ? 1 : 0;
            T[i][i] = (symbol[i] == 'T') ? 1 : 0;
        }

        //fill T[i][i+1]... in order and F[i][i+1]...
        for(int gap = 1; gap < n; ++gap)
        {
            for(int i = 0, j = gap; j < n; ++i, ++j)
            {
                T[i][j] = F[i][j] = 0;
                for(int g = 0; g < gap; g++)
                {
                    //find place of parenthesis using current gap value
                    int k = i + g;

                    //store Total[i][k] and Total[k+1][j]
                    int totalIK = T[i][k] + F[i][k];
                    int totalKJ = T[k+1][j] + F[k + 1][j];

                    //follow recursive formulas according the current operator
                    if(oper[k] == '&')
                    {
                        T[i][j] += T[i][k] * T[k + 1][j];
                        F[i][j] += (totalIK * totalKJ - T[i][k] * T[k + 1][j]);
                    }
                    if(oper[k] == '|')
                    {
                        F[i][j] += F[i][k] * F[k + 1][j];
                        T[i][j] += (totalIK * totalKJ - F[i][k] * F[k + 1][j]);
                    }
                    if(oper[k] == '^')
                    {
                        T[i][k] += F[i][k] * T[k + 1][j] + T[i][k] * F[k + 1][j];
                        F[i][j] += T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j];
                    }
                }
            }
        }
        return T[0][n - 1];
    }

    //Book Support Class to help with testing
    public static class Others
    {
        public enum Term {
            True,
            False,
            And,
            Or,
            Xor,
            LeftParen,
            RightParen
        };

        public static String reduce(String expression, int start, int end) {
            if (start == end) {
                if (expression.charAt(start) == '1') {
                    return "1";
                } else if (expression.charAt(start) == '0') {
                    return "0";
                }
            }
            int count = 0;
            int i = 0;
            String[] reduced = new String[3];
            int index = 0;
            int left = start;
            int right = start;
            for (i = start; i <= end; i++) {
                if (expression.charAt(i) == '(') {
                    if (count == 0) {
                        left = i + 1;
                    }
                    count++;
                } else if (expression.charAt(i) == ')') {
                    count--;
                    if (count == 0) {
                        right = i - 1;
                    }
                }
                if (count == 0) {
                    reduced[index] = reduce(expression, left, right);
                    if (index == 0) {
                        reduced[index + 1] = Character.toString(expression.charAt(i + 1));
                        i += 1;
                        left = i + 1;
                        right = i + 1;
                    }
                    index += 2;
                }
            }
            if (reduced[1].equals("&")) {
                if (reduced[0].equals("1") && reduced[2].equals("1")) {
                    return "1";
                }
                return "0";
            } else if (reduced[1].equals("|")) {
                if (reduced[0].equals("1") || reduced[2].equals("1")) {
                    return "1";
                }
                return "0";
            } else if (reduced[1].equals("^")) {
                if (reduced[0].equals("1") && reduced[2].equals("0")) {
                    return "1";
                } else if (reduced[0].equals("0") && reduced[2].equals("1")) {
                    return "1";
                }
                return "0";
            }
            return "0";
        }

        public static boolean evaluate(String expression, int start, int end) {
            String result = reduce(expression, start, end);
            if (result == "0") {
                return false;
            } else {
                return true;
            }
        }

        public static boolean isOperator(char c) {
            switch (c) {
                case '&':
                case '|':
                case '^':
                    return true;
                default:
                    return false;
            }
        }

        public static String insertParensAround(String expression, int ind) {
            int left = 0;
            int right = 0;
            int index = 0;
            int count = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (isOperator(expression.charAt(i))) {
                    if (count == ind) {
                        index = i;
                    }
                    count++;
                }
            }
            count = 0;
            for (int i = index - 1; i >= 0; i--) {
                if (expression.charAt(i) == ')') {
                    count++;
                } else if (expression.charAt(i) == '(') {
                    count--;
                }
                if (count == 0) {
                    left = i;
                    break;
                }
            }
            count = 0;
            for (int i = index + 1; i <= expression.length(); i++) {
                if (expression.charAt(i) == '(') {
                    count++;
                } else if (expression.charAt(i) == ')') {
                    count--;
                }
                if (count == 0) {
                    right = i;
                    break;
                }
            }
            if (left == 0 && right == expression.length() - 1) {
                return expression;
            }
            String newexpression = expression.substring(0, left) + '(' + expression.substring(left, right + 1) + ')' + expression.substring(right + 1);
            return newexpression;

        }

        public static int bruteForce(String expression, HashMap<String, Boolean> completed, boolean result, boolean[] flags) {
            int count = 0;
            boolean isDone = true;
            if (completed.containsKey(expression)) {
                return 0;
            }

            for (int i = 0; i < flags.length; i++) {
                if (!flags[i]) {
                    flags[i] = true;
                    String newexpression = insertParensAround(expression, i);
                    isDone = false;
                    count += bruteForce(newexpression, completed, result, flags);
                    flags[i] = false;
                }
            }

            if (isDone) {
                if (evaluate(expression, 0, expression.length() - 1) == result) {
                    //System.out.println(expression + " = " + result);
                    return 1;
                } else {
                    //System.out.println(expression + " = " + !result);
                    return 0;
                }
            }
            completed.put(expression, true);
            return count;
        }

        public static int countR(String exp, boolean result, int start, int end) {
            if (start == end) {
                if (exp.charAt(start) == '1' && result) {
                    return 1;
                } else if (exp.charAt(start) == '0' && !result) {
                    return 1;
                }
                return 0;
            }
            int c = 0;
            if (result) {
                for (int i = start + 1; i <= end; i += 2) {
                    char op = exp.charAt(i);
                    if (op == '&') {
                        c += countR(exp, true, start, i - 1) * countR(exp, true, i + 1, end);
                    } else if (op == '|') {
                        c += countR(exp, true, start, i - 1) * countR(exp, false, i + 1, end);
                        c += countR(exp, false, start, i - 1) * countR(exp, true, i + 1, end);
                        c += countR(exp, true, start, i - 1) * countR(exp, true, i + 1, end);
                    } else if (op == '^') {
                        c += countR(exp, true, start, i - 1) * countR(exp, false, i + 1, end);
                        c += countR(exp, false, start, i - 1) * countR(exp, true, i + 1, end);
                    }
                }
            } else {
                for (int i = start + 1; i <= end; i += 2) {
                    char op = exp.charAt(i);
                    if (op == '&') {
                        c += countR(exp, false, start, i - 1) * countR(exp, true, i + 1, end);
                        c += countR(exp, true, start, i - 1) * countR(exp, false, i + 1, end);
                        c += countR(exp, false, start, i - 1) * countR(exp, false, i + 1, end);
                    } else if (op == '|') {
                        c += countR(exp, false, start, i - 1) * countR(exp, false, i + 1, end);
                    } else if (op == '^') {
                        c += countR(exp, true, start, i - 1) * countR(exp, true, i + 1, end);
                        c += countR(exp, false, start, i - 1) * countR(exp, false, i + 1, end);
                    }
                }
            }
            return c;
        }

        public static int countDP(String exp, boolean result, int start, int end, HashMap<String, Integer> cache) {
            String key = "" + result + start + end;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            if (start == end) {
                if (exp.charAt(start) == '1' && result == true) {
                    return 1;
                } else if (exp.charAt(start) == '0' && result == false) {
                    return 1;
                }
                return 0;
            }
            int count = 0;
            if (result) {
                for (int i = start + 1; i <= end; i += 2) {
                    char op = exp.charAt(i);
                    if (op == '&') {
                        count += countDP(exp, true, start, i - 1, cache) * countDP(exp, true, i + 1, end, cache);
                    } else if (op == '|') {
                        count += countDP(exp, true, start, i - 1, cache) * countDP(exp, false, i + 1, end, cache);
                        count += countDP(exp, false, start, i - 1, cache) * countDP(exp, true, i + 1, end, cache);
                        count += countDP(exp, true, start, i - 1, cache) * countDP(exp, true, i + 1, end, cache);
                    } else if (op == '^') {
                        count += countDP(exp, true, start, i - 1, cache) * countDP(exp, false, i + 1, end, cache);
                        count += countDP(exp, false, start, i - 1, cache) * countDP(exp, true, i + 1, end, cache);
                    }
                }
            } else {
                for (int i = start + 1; i <= end; i += 2) {
                    char op = exp.charAt(i);
                    if (op == '&') {
                        count += countDP(exp, false, start, i - 1, cache) * countDP(exp, true, i + 1, end, cache);
                        count += countDP(exp, true, start, i - 1, cache) * countDP(exp, false, i + 1, end, cache);
                        count += countDP(exp, false, start, i - 1, cache) * countDP(exp, false, i + 1, end, cache);
                    } else if (op == '|') {
                        count += countDP(exp, false, start, i - 1, cache) * countDP(exp, false, i + 1, end, cache);
                    } else if (op == '^') {
                        count += countDP(exp, true, start, i - 1, cache) * countDP(exp, true, i + 1, end, cache);
                        count += countDP(exp, false, start, i - 1, cache) * countDP(exp, false, i + 1, end, cache);
                    }
                }
            }
            cache.put(key, count);
            return count;
        }

        public static int total(int n) {
            // Function to return (2n) ! / ((n+1)! * n!)

            // To keep the numbers small, we divide by i when possible to do evenly. If not,
            // we store up the remainder and divide when possible.
            long num = 1;
            long rem = 1;
            for (int i = 2; i <= n; i++) {
                num *= (n + i);
                rem *= i;
                if (num % rem == 0) {
                    num /= rem;
                    rem = 1;
                }
            }
            return (int)num;
        }

        public static int countDPEff(String exp, boolean result, int start, int end, HashMap<String, Integer> cache) {
            String key = "" + start + end;
            int count = 0;
            if (!cache.containsKey(key)) {
                if (start == end) {
                    if (exp.charAt(start) == '1') {
                        count = 1;
                    } else {
                        count = 0;
                    }
                }

                for (int i = start + 1; i <= end; i += 2) {
                    char op = exp.charAt(i);
                    if (op == '&') {
                        count += countDPEff(exp, true, start, i - 1, cache) * countDPEff(exp, true, i + 1, end, cache);
                    } else if (op == '|') {
                        int left_ops = (i - 1 - start) / 2; // parens on left
                        int right_ops = (end - i - 1) / 2;  // parens on right
                        int total_ways = total(left_ops) * total(right_ops);
                        int total_false = countDPEff(exp, false, start, i - 1, cache) * countDPEff(exp, false, i + 1, end, cache);
                        count += total_ways - total_false;
                    } else if (op == '^') {
                        count += countDPEff(exp, true, start, i - 1, cache) * countDPEff(exp, false, i + 1, end, cache);
                        count += countDPEff(exp, false, start, i - 1, cache) * countDPEff(exp, true, i + 1, end, cache);
                    }
                }
                cache.put(key, count);
            } else {
                count = cache.get(key);
            }
            if (result) {
                return count;
            } else {
                int num_ops = (end - start) / 2;
                return total(num_ops) - count;
            }
        }
    }

    /*
     * Book Approach 1
     * Time:
     * Space:
     */
    public static int count = 0;
    public static boolean stringToBool(String c) {
        return c.equals("1") ? true : false;
    }

    public static int countEval(String s, boolean result) {
        count++;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return stringToBool(s) == result ? 1 : 0;

        int ways = 0;

        for (int i = 1; i < s.length(); i += 2) {
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1, s.length());
            int leftTrue = countEval(left, true);
            int leftFalse = countEval(left, false);
            int rightTrue = countEval(right, true);
            int rightFalse = countEval(right, false);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrue = 0;
            if (c == '^') { // required: one true and one false
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (c == '&') { // required: both true
                totalTrue = leftTrue * rightTrue;
            } else if (c == '|') { // required: anything but both false
                totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
            }

            int subWays = result ? totalTrue : total - totalTrue;
            ways += subWays;
        }

        return ways;
    }

    /*
     * Book Approach 2
     * Time:
     * Space:
     */
    public static int count2 = 0;
    public static boolean stringToBool2(String c) {
        return c.equals("1") ? true : false;
    }

    public static int countEval2(String s, boolean result, HashMap<String, Integer> memo) {
        count2++;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return stringToBool2(s) == result ? 1 : 0;
        if (memo.containsKey(result + s)) return memo.get(result + s);

        int ways = 0;

        for (int i = 1; i < s.length(); i += 2) {
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1, s.length());
            int leftTrue = countEval2(left, true, memo);
            int leftFalse = countEval2(left, false, memo);
            int rightTrue = countEval2(right, true, memo);
            int rightFalse = countEval2(right, false, memo);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrue = 0;
            if (c == '^') {
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            } else if (c == '&') {
                totalTrue = leftTrue * rightTrue;
            } else if (c == '|') {
                totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
            }

            int subWays = result ? totalTrue : total - totalTrue;
            ways += subWays;
        }

        memo.put(result + s, ways);
        return ways;
    }

    public static int countEval2(String s, boolean result) {
        return countEval2(s, result, new HashMap<String, Integer>());
    }

    /*
     * Book Approach 3
     * Time:
     * Space:
     */
    public static int count3 = 0;
    public static boolean stringToBool3(String c) {
        return c.equals("1") ? true : false;
    }

    public static int countEval3(String s, boolean result) {
        count3++;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return stringToBool3(s) == result ? 1 : 0;

        int ways = 0;

        for (int i = 1; i < s.length(); i += 2) {
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1, s.length());

            int subWays = 0;
            if (c == '^') { // required: one true and one false
                if (result) {
                    int leftTrue = countEval3(left, true);
                    int leftFalse = countEval3(left, false);
                    int rightTrue = countEval3(right, true);
                    int rightFalse = countEval3(right, false);
                    subWays = leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    int leftTrue = countEval3(left, true);
                    int leftFalse = countEval3(left, false);
                    int rightTrue = countEval3(right, true);
                    int rightFalse = countEval3(right, false);
                    subWays = leftTrue * rightTrue + leftFalse * rightFalse;
                }
            } else if (c == '&') { // required: both true
                if (result) {
                    int leftTrue = countEval3(left, true);
                    int rightTrue = countEval3(right, true);
                    subWays = leftTrue * rightTrue;
                } else {
                    int leftTrue = countEval3(left, true);
                    int leftFalse = countEval3(left, false);
                    int rightTrue = countEval3(right, true);
                    int rightFalse = countEval3(right, false);
                    subWays = leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
                }
            } else if (c == '|') { // required: anything but both false
                if (result) {
                    int leftTrue = countEval3(left, true);
                    int leftFalse = countEval3(left, false);
                    int rightTrue = countEval3(right, true);
                    int rightFalse = countEval3(right, false);
                    subWays = leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
                } else {
                    int leftFalse = countEval3(left, false);
                    int rightFalse = countEval3(right, false);
                    subWays = leftFalse * rightFalse;
                }
            }
            ways += subWays;
        }

        return ways;
    }
}
