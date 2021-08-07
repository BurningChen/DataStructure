package RedBlackTree;

import map.Map;

import java.util.ArrayList;

/**
 * AVL
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>,V> implements Map<K,V> {
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;  // 树的高度

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
        private Node root;
        private int size;
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node,K key,V value){
        if (node == null){
            size++;
            return new Node(key,value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else
            node.value = value;

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            System.out.println("unbalanced：" + balanceFactor);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node getNode(Node node,K key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else
            return getNode(node.right,key);
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node,K key){
        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            retNode = node;
        }else{
            //待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            //待删除节点右子数为空的情况
            else if(node.right==null){
                Node leftNode=node.left;
                node.left=null;
                size--;
                retNode = leftNode;
            }
            else{
                //待删除节点左右子数均不为空的情况
                //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //用这个节点顶替待删除节点的位置
                Node successor=mininum(node.right);
                successor.right=remove(node.right,successor.key);

                successor.left=node.left;

                node.left=node.right=null;
                retNode = successor;
            }
        }

        if (retNode == null){
            return null;
        }

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if (Math.abs(balanceFactor) > 1)
            System.out.println("unbalanced：" + balanceFactor);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node mininum(Node node){
        if (node.left == null)
            return node;
        return mininum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if (node == null)
            throw new IllegalArgumentException("key" + "doesn't exist!");
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 判断该二叉树是否是一颗二叉搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        // 中序遍历 ，会是从小到大的一个排序
        inOrder(root,keys);
        // 判断排序是否对，不对返回false，不是二分搜索树
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    // 判断该二叉树是否是一颗平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){

        // 空树为平衡二叉树
        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node,ArrayList<K> keys){
        if (node == null)
            return;
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    /**
     * 获取节点的高度
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 计算平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 右旋转
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 左旋转
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        // 向右旋转
        x.left = y;
        x.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }
}
