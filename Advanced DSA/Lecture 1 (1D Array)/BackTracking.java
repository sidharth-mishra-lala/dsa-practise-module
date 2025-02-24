import java.util.*;

public class BackTracking {
    String[] container;
    ArrayList<String> res;

    public static void main(String[] args) {
        BackTracking bck = new BackTracking();

        System.out.println(bck.solve("273"));

        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(1);
        A.add(2);

        Map<Integer, Integer> freq = new HashMap<>();

        for(int i = 0; i < A.size(); i++) {
            if(!freq.containsKey(A.get(i))) {
                freq.put(A.get(i), 1);
            } else {
                int val = freq.get(A.get(i));
                val += 1;
                freq.put(A.get(i), val);
            }
        }

        System.out.println(freq);
    }

    public ArrayList<String> solve(String A) {
        this.res = new ArrayList<>();
        this.container = new String[10];

        this.container[0] = "0";
        this.container[1] = "1";
        this.container[2] = "abc";
        this.container[3] = "def";
        this.container[4] = "ghi";
        this.container[5] = "jkl";
        this.container[6] = "mno";
        this.container[7] = "pqrs";
        this.container[8] = "tuv";
        this.container[9] = "wxyz";

        traverse("", 0, A);

        return res;
    }

    public void traverse(String seq, int idx, String A) {
        if(idx == A.length()) {
            res.add(seq);
            return;
        }

        int pos = A.charAt(idx) - '0';

        String help = container[pos];

        generate(help, seq, idx, A);
    }

    public void generate(String help, String seq, int idx, String A) {

        for(int i = 0; i < help.length(); i++) {
            traverse(seq + help.charAt(i), idx + 1, A);
        }
    }
}