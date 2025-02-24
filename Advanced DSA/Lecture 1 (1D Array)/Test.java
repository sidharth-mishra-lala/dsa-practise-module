
import java.util.*;

class TreeNode {

    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Test {

    public static void main(String[] args) {
        Test test = new Test();

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(9);

        // test.traverse(root);
        test.verticalOrderTraversal(root);
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);

        System.out.println(root.val);

        traverse(root.right);
    }

    public void verticalOrderTraversal(TreeNode A) {
        TreeNode curr = A;
        int minLevel = 0, maxLevel = 0;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, curr));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(curr.val);
        map.put(0, list);
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            minLevel = Math.min(minLevel, pair.level - 1);
            maxLevel = Math.max(maxLevel, pair.level + 1);
            if (pair.node.left != null) {
                queue.add(new Pair(pair.level - 1, pair.node.left));
                if (map.containsKey(pair.level - 1) == true) {
                    ArrayList<Integer> values = map.get(pair.level - 1);
                    values.add(pair.node.left.val);
                    map.put(pair.level - 1, values);
                } else {
                    ArrayList<Integer> values = new ArrayList<>();
                    values.add(pair.node.left.val);
                    map.put(pair.level - 1, values);
                }
            }
            if (pair.node.right != null) {
                queue.add(new Pair(pair.level + 1, pair.node.right));
                if (map.containsKey(pair.level + 1) == true) {
                    ArrayList<Integer> values = map.get(pair.level + 1);
                    values.add(pair.node.right.val);
                    map.put(pair.level + 1, values);
                } else {
                    ArrayList<Integer> values = new ArrayList<>();
                    values.add(pair.node.right.val);
                    map.put(pair.level + 1, values);
                }
            }
        }

        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        for (int i = minLevel + 1; i < maxLevel; i++) {
            values.add(map.get(i));
        }
        return values;
    }
}

class Pair {

    int level;
    TreeNode node;

    public Pair(int level, TreeNode node) {
        this.level = level;
        this.node = node;
    }
}
