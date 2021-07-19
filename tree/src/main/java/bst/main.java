package bst;

public class main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();

        int[] nums = {5,3,6,7,2,4};
        for (int num : nums){
            bst.add(num);
        }
        bst.preOrder();

        System.out.println();
        System.out.println(bst);
    }
}
