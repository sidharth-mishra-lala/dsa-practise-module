
import java.util.*;

public class Sample {

    public static void main(String[] args) {
        Sample s = new Sample();

        //System.out.println(s.validParanthesis("{([])}"));
        //System.out.println(s.removeDuplicates("abbbcbbcacx"));
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        s.climb(ans, new ArrayList<>(), 4);

        System.out.println(ans);
    }

    public void climb(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> curr, int N) {
        if (N == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        //climb 1 stair and check for N-1 stairs
        curr.add(1);

        climb(ans, curr, N - 1);

        //release as we explored 1 jump
        curr.remove(curr.size() - 1);

        // climb 2 stairs and check N-2 stairs
        if (N >= 2) {
            curr.add(2);

            climb(ans, curr, N - 2);

            // release as we explored 2 jumps
            curr.remove(curr.size() - 1);
        }
    }

    public String removeDuplicates(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (!stack.isEmpty() && str.charAt(i) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(str.charAt(i));
            }
        }

        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            s.append(stack.peek());
            stack.pop();
        }

        return new String(s.reverse());
    }

    public boolean validParanthesis(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                stack.push(str.charAt(i));
            } else {
                if (stack.isEmpty() == true) {
                    return false;
                }

                if ((str.charAt(i) == ')' && stack.peek() != '(')
                        || (str.charAt(i) == '}' && stack.peek() != '{')
                        || (str.charAt(i) == ']' && stack.peek() != '[')) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public PairJobs[] totalJobs(int[] start, int[] end) {
        PairJobs[] jobs = new PairJobs[start.length];

        // form the pair array
        for (int i = 0; i < start.length; i++) {
            jobs[i] = new PairJobs(start[i], end[i]);
        }

        // sort the pair[]
        Arrays.sort(jobs, new MyComparator());

        return jobs;
    }
}

class PairJobs {

    int start;
    int end;

    public PairJobs(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MyComparator implements Comparator<PairJobs> {

    @Override
    public int compare(PairJobs first, PairJobs second) {
        if (first.end == second.end) {
            return first.start - second.start;
        }

        return first.end - second.end;
    }
}
