import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        //this part is to form the tree (from inorder and postorder)
        int[] in = new int[]{3, 2, 7, 4, 1, 5, 8, 6};
        int[] post = new int[]{3, 7, 4, 2, 8, 6, 5, 1};

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < in.length; i++) {
            if(map.containsKey(in[i]) == false) {
                map.put(in[i], i);
            }
        }
        Node root = test.formTree(in, post, 0, 7, 0, 7, map);  

        System.out.println(root.val);
        

        // this is test of original tree, expected is [3,2,7,4,1,5,8,6]
        test.inOrder(root);

        //flatten the tree
        root = test.flatten(root);
        System.out.println();

        // this is test after flatten, same inorder should give [1,2,3,4,7,5,6,8]
        test.inOrder(root);
    }

    public Node formTree(int[] in, int[] post, int isi, int iei, int psi, int pei, Map<Integer, Integer> map) {
        if(isi == iei) {
            return new Node(in[isi]);
        }

        Node root = new Node(post[pei]);

        int idx = map.get(post[pei]);

        if(idx - 1 < isi) {
            root.left = null;
        } else {
            root.left = formTree(in, post, isi, idx - 1, psi, psi + idx - isi - 1, map);
        }

        if(idx + 1 > iei) {
            root.right = null;
        } else {
            root.right = formTree(in, post, idx + 1, iei, psi + idx - isi, pei - 1, map);
        }

        return root;
    }

    public void inOrder(Node root) {
        if(root == null) {
            return;
        }

        inOrder(root.left);

        System.out.print(root.val + " ");

        inOrder(root.right);
    }
    
    public Node flatten(Node root) {
        Node curr = root;

        while(curr != null) {
            //check for left
            if(curr.left != null) {
                Node temp = curr.left;
                //go to right most node
                while(temp.right != null) {
                    temp = temp.right;
                }

                //make the right of 'temp' as curr.right
                temp.right = curr.right;

                //the left of 'curr' to be shifted to right
                curr.right = curr.left;

                //left of'curr' to be nullified
                curr.left = null;
            }

            //we have already pruned the left, so only way is right
            curr = curr.right;
        }

        return root;
    }
}
