package corman.Trees;

import dynamicProgramming.MyArrayUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.swing.tree.TreeNode;
import java.util.*;


/**
 *
 */
public class MySuperBinarySearchTree<Key extends Comparable> implements BinaryTree<Key> {

    private Node root;             // root of BST

    @Override
    public void delete(Key value) {
        Node toDelete = getNodeByValue(value);
        Node toDeleteParent = getParent(toDelete);
        Node newHead = null;

        if(toDelete.left == null && toDelete.right == null) {
            replaceChild(toDeleteParent, toDelete, null);
        } else if(toDelete.left == null) {
            replaceChild(toDeleteParent, toDelete, toDelete.right);
            newHead = toDelete.right;
        } else if (toDelete.right == null) {
            replaceChild(toDeleteParent, toDelete, toDelete.left);
            newHead = toDelete.left;
        } else {
            Node toDeleteLeft = toDelete.left;
            Node toDeleteRight = toDelete.right;

            newHead = getNodeByValue(next(value));
            Node newHeadParent = getParent(newHead);

            ///DELETION
            if (toDeleteParent != null && toDeleteParent.left == toDelete) toDeleteParent.left = newHead;
            if (toDeleteParent != null && toDeleteParent.right == toDelete) toDeleteParent.right = newHead;
            newHeadParent.left = newHead.right;

            newHead.left = (toDeleteLeft == newHead) ? null : toDeleteLeft;
            newHead.right = (toDeleteRight == newHead) ? null : toDeleteRight;
        }

        if (toDelete == root) root = newHead;
    }

    public void flattenToLinkedList(Node root) {
        java.util.Stack<Node> rightElements = new java.util.Stack<Node>();
        Node cur = root;
        if(cur == null) return;
        while (cur != null || !rightElements.empty()) {
            if (cur != null && cur.right != null) rightElements.push(cur.right);
            if (cur != null && cur.left != null) {
                Node leftref = cur.left;
                cur.right = leftref;
                cur.left = null;
                cur = leftref;
                continue;
            } else if (!rightElements.empty()){
                cur.right = rightElements.pop();
            }
            cur = cur.right;
        }
    }


    @Override
    public void put(Key value) {
        if (root == null) {
            root = new Node(value, null, null );
        } else {
            Node cur = root;
            Node next = null;
            boolean insertToLeft;
            while (cur != null) {
                if(cur.isLessThen(value)) {
                    next = cur.right;
                    insertToLeft = false;
                }
                else if(cur.isGreaterThen(value)) {
                    next = cur.left;
                    insertToLeft = true;
                }
                else return;
                if (next == null) {
                    if(insertToLeft) cur.left = new Node(value, null, null );
                    else cur.right = new Node(value, null, null );
                    return;
                } else {
                    cur = next;
                }
            }
        }
    }

   @Override
    public Iterable<Key> inOrderTraversal() {
        List<Key> res = new ArrayList<Key>();
        Stack<Node> stack = new Stack();
        addAllLeftElementsToStack(stack, root);
        while (stack.size() >0) {
            Node cur = stack.pop();
            res.add(cur.key);
            if(cur.right != null) {
                addAllLeftElementsToStack(stack, cur.right);
            }
        }
        return res;
    }

    @Override
    public Iterable<Key> preOrderTraversal() {
        List<Key> res = new ArrayList<Key>();
        preOrderTraversalRecursive(root, res);
        return res;
    }


    @Override
    public Iterable<Key> postOrderTraversal() {
        List<Key> res = new ArrayList<Key>();
        postOrderTraversalRecursive(root, res);
        return res;
    }

    @Override
    public List<List<Key>> levelOrderTraversalToLists() {
        List<List<Key>> res = new ArrayList<List<Key>>();
        if (root==null) return res;

        Deque<Node> que = new LinkedList<Node>();

        Node firstNodeOfLevel = root;
        que.addLast(root);
        List<Key> curList = null;
        while (que.size()>0) {
            Node cur = que.pollFirst();
            if(cur == firstNodeOfLevel) {
                firstNodeOfLevel = null;
                if(curList != null) res.add(curList);
                curList = new ArrayList<Key>();
            }
            curList.add(cur.key);

            if(cur.left != null) {
                que.addLast(cur.left);
                firstNodeOfLevel = firstNodeOfLevel == null?cur.left:firstNodeOfLevel;
            }
            if(cur.right != null) {
                que.addLast(cur.right);
                firstNodeOfLevel = firstNodeOfLevel == null?cur.right:firstNodeOfLevel;
            }

        }
        res.add(curList);
        return res;
    }

    @Override
    public int height() {
        Object[] unbalanced = new Object[1];
        int h = getHeightAndLastUnbalancedNode(root, unbalanced);
        return h;
    }

    //The path may start and end at any node in the tree.
    public int maxPassSum() {
        if(root == null) return 0;
        if(root.key.getClass() != Integer.class) throw new IllegalArgumentException("wrongCollectionType. need <Integer>");
        //Collection<Integer> res = new ArrayList<Integer>();
        //maxPassSumRecursiveCollection(root, res);
        //return res;
        long[] res = new long[1];
        res[0] = Integer.MIN_VALUE;
        maxPassSumRecursiveNumber(root, res);
        return (int)res[0];
    }

    @Override
    public boolean isBalanced() {
        Object[] unbalanced = new Object[1];
        int h = getHeightAndLastUnbalancedNode(root, unbalanced);

        if(unbalanced[0] != null) return false;
        else return true;
    }

    @Override
    public int sumRootToLeafNumbers() {
        List<Integer> res = new ArrayList<Integer>();
        sumRootToLeafNumbers(root, new StringBuilder(), res);

        int sum = 0;
        for(int i: res) {
            sum +=i;
        }
        return sum;
    }

    @Override
    public void sortedArraytoBST(Key[] num) {
        Node root;
        if(num.length < 1) return;
        root = sortedArrayToBST(num, 0, num.length-1 );
        buildTreeFromRoot(root);
    }

    @Override
    public Key next(Key key) {
        // first check the right subtree of current node
        // the element will be in the very left position of subtree
        //   X
        // 1   3
        // need to return 3
        // if there is no right node
        // we need to build path to the target element
        // and choose the smallest element form the path  which is >= target
        Node minNodeGraterThenTarget = null;
        Node cur = root;

        while ( cur != null && !cur.key.equals(key)) {
            if(cur.isGreaterThen(key) && (minNodeGraterThenTarget == null || cur.isLessThen(minNodeGraterThenTarget))) {
                minNodeGraterThenTarget = cur;
            }
            if(cur.isGreaterThen(key)) {
                cur = cur.left;
            } else if(cur.isLessThen(key)) {
                cur = cur.right;
            } else {
                return null;
            }
        }
        if (cur != null && cur.right != null) return getMinInTree(cur.right).key;
        return  minNodeGraterThenTarget == null ? null : minNodeGraterThenTarget.key;
    }

    @Override
    public Key previous(Key key) {
        Node maxNodeLessThenTarget = null;
        Node cur = root;

        while ( cur != null && !cur.key.equals(key)) {
            if(cur.isLessThen(key) && (maxNodeLessThenTarget == null || cur.isGreaterThen(maxNodeLessThenTarget))) {
                maxNodeLessThenTarget = cur;
            }
            if(cur.isGreaterThen(key)) {
                cur = cur.left;
            } else if(cur.isLessThen(key)) {
                cur = cur.right;
            } else {
                return null;
            }
        }
        if (cur != null && cur.left != null) return getMaxInTree(cur.left).key;
        return  maxNodeLessThenTarget == null ? null : maxNodeLessThenTarget.key;
    }


    @Override
    public Key max() {
        return getMaxInTree(root).key;
    }

    @Override
    public Key min() {
        return getMinInTree(root).key;
    }

    @Override
    public BinaryTree<Key> merge(BinaryTree<Key> tree) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySuperBinarySearchTree thatTree = (MySuperBinarySearchTree) o;
        if (root != null ? !root.equals(thatTree.root) : thatTree.root != null) return false;
        return this.preOrderTraversal().equals(thatTree.preOrderTraversal());
    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }

    @Override
    public boolean equals(BinaryTree<Key> tree) {
        if(tree == null || tree.getClass() != this.getClass()) return false;
        MySuperBinarySearchTree mytree = (MySuperBinarySearchTree) tree;
        Node nodeA = root;
        Node nodeB = mytree.root;
        return equals(nodeA, nodeB);
    }

    @Override
    public Object serialize() {

        Map<Key, Integer> keyToId= new HashMap<Key, Integer>();
        List<Key> values = new ArrayList<Key>();
        int id = 0;

        for(Key key:preOrderTraversal()) {
            keyToId.put(key, id++);
            values.add(key);
        }

        int[] leftRefs = new int[keyToId.size()];
        int[] rightRefs = new int[keyToId.size()];
        id = 0;
        for (Key key: values) {
            Node cur = getNodeByValue(key);
            int leftID = -1, rightID = -1;
            if(cur.left!= null) {
                leftID = keyToId.get(cur.left.key);
            }
            if(cur.right!= null) {
                rightID = keyToId.get(cur.right.key);
            }
            leftRefs[id] = leftID;
            rightRefs[id] = rightID;
            id++;
        }

        List result = new ArrayList();
        result.add(values);
        result.add(leftRefs);
        result.add(rightRefs);
        return result;
    }

    @Override
    public BinaryTree<Key> deserializer(Object o) {
        List serializedTree = (List) o;
        List<Key> values = (List<Key>)serializedTree.get(0);
        int[] leftReft = (int[])serializedTree.get(1);
        int[] rightRefs = (int[])serializedTree.get(2);
        List<Node> nodes = new ArrayList<Node>();
        for(int i =0; i<values.size(); i++) {
            nodes.add(new Node(values.get(i), null, null));
        }

        for(int i =0; i<nodes.size(); i++) {
            nodes.get(i).left = (leftReft[i]<0?null:nodes.get(leftReft[i]));
            nodes.get(i).right =(rightRefs[i]<0?null:nodes.get(rightRefs[i]));
        }

        return buildTreeFromRoot(nodes.get(0));
    }

    @Override
    public BinaryTree<Key> subTree(Key key) {
        return  buildTreeFromRoot(getNodeByValue(key));
    }

    @Override
    public void balance() {
        //Using tree rotations, convert the tree into a degenerate linked list.
        //By applying selective rotations to the linked list, convert the list back into a completely balanced tree.

        //make sorted list
        while (root.left != null) {
            rotateRight(null, root);
        }
//        now flattern the list, using rotations
//        if (balance_factor(L) == 2) { //The left column
//            let P=left_child(L)
//            if (balance_factor(P) == -1) { //The "Left Right Case"
//                rotate_left(P) //reduce to "Left Left Case"
//            }
//            //Left Left Case
//            rotate_right(L);
//        } else { // balance_factor(L) == -2, the right column
//            let P=right_child(L)
//            if (balance_factor(P) == 1) { //The "Right Left Case"
//                rotate_right(P) //reduce to "Right Right Case"
//            }
//            //Right Right Case
//            rotate_left(L);
//        }
        Object[] fakeobject = new Object[1];


        boolean balanceRequred = true;
        boolean needRotateLeft = true;

        while (balanceRequred) {
            int rootRHeight = getHeightAndLastUnbalancedNode(root.right, fakeobject);
            int rootLHeight = getHeightAndLastUnbalancedNode(root.left, fakeobject);
            System.out.println("rootRHeight="+rootRHeight+" rootLHeight="+rootLHeight);
            balanceRequred = false;
            Node curRoot = root;
            Node grandParent = null;
            Node parent = null;

            while (rootRHeight - rootLHeight >= 2 || rootLHeight - rootRHeight>= 2) {
                balanceRequred = true;
               // System.out.println("curRoot" + curRoot);
                grandParent = parent;
                parent = curRoot;
                if(rootRHeight - rootLHeight >= 2 ) {
                    System.out.println("MOVING right: rootRHeight="+rootRHeight+" rootLHeight="+rootLHeight);
                    curRoot = curRoot.right;
                    needRotateLeft = true;
                } else {
                    System.out.println("MOVING left: rootRHeight="+rootRHeight+" rootLHeight="+rootLHeight);
                    curRoot = curRoot.left;
                    needRotateLeft = false;
                }
                rootRHeight = getHeightAndLastUnbalancedNode(curRoot.right, fakeobject);
                rootLHeight = getHeightAndLastUnbalancedNode(curRoot.left, fakeobject);
            }
            if(balanceRequred) {
                System.out.println("rotateLeft at " + parent + " parent"+grandParent);
                if(needRotateLeft)
                     rotateLeft(grandParent, parent);
                else
                    rotateRight(grandParent, parent);
                System.out.println(levelOrderTraversalToLists());
            }
        }
    }

    @Override
    public void constructFromInOrderAndPreOrder(List<Key> in, List<Key> pre) {
        Node head = constructFromInOrderAndPreOrderRec(in, pre);
        this.root = head;
    }


    @Override
    public void constructFromInOrderAndPostOrder(List<Key> in, List<Key> post) {
        Node head = constructFromInOrderAndPostOrderRec(in, post);
        this.root = head;
    }








    //////////////// PRIVATE ////////////////



    private void rotateRight(Node parent, Node curRoot) {
        Node oldRoot = curRoot;
        Node newRoot = curRoot.left;

        //change parent
        if(parent != null) {
            if (parent.left == oldRoot) parent.left = newRoot;
            if (parent.right == oldRoot) parent.right = newRoot;
        } else {
            this.root = newRoot;
        }

        Node right = newRoot.right;
        newRoot.right = oldRoot;
        oldRoot.left = right;
    }

    private void rotateLeft(Node parent, Node curRoot) {
        Node oldRoot = curRoot;
        Node newRoot = curRoot.right;
        //change parent
        if(parent != null) {
            if (parent.left == oldRoot) parent.left = newRoot;
            else if (parent.right == oldRoot) parent.right = newRoot;
        } else {
            this.root = newRoot;
        }
        Node left = newRoot.left;
        newRoot.left = oldRoot;
        oldRoot.right = left;
    }



    private Node constructFromInOrderAndPostOrderRec(List<Key> in, List<Key> post) {
        if(in.size() == 0) return null;
        Key curPre = post.get(post.size()-1); post.remove(post.size()-1);
        int i = 0;
        for (; !curPre.equals(in.get(i)); i++) {}
        Key curHeadKey = in.get(i);
        List<Key> leftList = in.subList(0, i);
        List<Key> rightList = in.subList(i+1, in.size());
        Node head  = new Node(curHeadKey, null, null);
        head.right = constructFromInOrderAndPostOrderRec(rightList, post);
        head.left = constructFromInOrderAndPostOrderRec(leftList, post);
        return head;
    }

    private Node constructFromInOrderAndPreOrderRec(List<Key> in, List<Key> pre) {
        if(in.size() == 0) return null;
        Key curPre = pre.get(0); pre.remove(0);
        int i = 0;
        for (; !curPre.equals(in.get(i)); i++) {}
        Key curHeadKey = in.get(i);
        List<Key> leftList = in.subList(0, i);
        List<Key> rightList = in.subList(i+1, in.size());
        Node head  = new Node(curHeadKey, null, null);
        head.left = constructFromInOrderAndPreOrderRec(leftList, pre);
        head.right = constructFromInOrderAndPreOrderRec(rightList, pre);
        return head;
    }

    private Node sortedArrayToBST(Key[] num, int i, int j) {
        if (i > j) return null;
        if (i == j) return new Node(num[i], null, null);
        int mid = (i+j)/2;
        Node curRoot = new Node(num[mid], null, null);
        curRoot.left = sortedArrayToBST(num, i, mid-1);
        curRoot.right = sortedArrayToBST(num, mid+1, j);
        return curRoot;

    }

    private void sumRootToLeafNumbers( Node cur, StringBuilder previousVal, List<Integer> leaves) {
        if (cur.left == null && cur.right == null) {
            leaves.add(Integer.parseInt(previousVal.append(cur.key)+""));
            return;
        }

        if (cur.left !=  null) {
            sumRootToLeafNumbers(cur.left, new StringBuilder().append(previousVal).append(cur.key), leaves);
        }

        if (cur.right !=  null) {
            sumRootToLeafNumbers(cur.right, new StringBuilder().append(previousVal).append(cur.key), leaves);
        }
    }

    private boolean equals(Node a, Node b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        return (a.key.equals(b.key) && equals(a.left, b.left) && equals(a.right, b.right));
    }

    private long maxPassSumRecursiveNumber(Node n, long[] bestresult) {
        if (n == null) return Integer.MIN_VALUE;
        long curVal = (Integer) n.key;
        long leftSum = maxPassSumRecursiveNumber(n.left, bestresult);
        long rightSum = maxPassSumRecursiveNumber(n.right, bestresult);

        // itry to have path with current node as root
        long resultWithCurNode = curVal;
        if (leftSum > 0) resultWithCurNode+=leftSum;
        if (rightSum > 0) resultWithCurNode+=rightSum;
        if (resultWithCurNode > bestresult[0]) {
            bestresult[0] = resultWithCurNode;
        }

        if (curVal >= leftSum + curVal && curVal >= rightSum + curVal) {
            return curVal;
        } else if (leftSum + curVal >= curVal && leftSum + curVal >= rightSum + curVal) {
            return leftSum+curVal;
        } else {
            return rightSum+curVal;
        }
    }



    public int collectionSum(Collection<Integer>c) {
        int sum = 0;
        for(int i: c) {
            sum += i;
        }
        return sum;
    }


    private Node getParent(Node n) {
        Node cur = root;
        if (root == n) return null;
        while (true) {
            if (((cur.left != null && cur.left == n) || (cur.right != null && cur.right == n))) return cur;
            if(cur.isLessThen(n.key)) {
                cur = cur.right;
            }
            else if(cur.isGreaterThen(n.key)) {
                cur = cur.left;
            }
            else return null;
        }
    }

    private void preOrderTraversalRecursive(Node curNode, List<Key> result) {
        if(curNode == null) return;
        result.add(curNode.key);
        preOrderTraversalRecursive(curNode.left, result);
        preOrderTraversalRecursive(curNode.right, result);
    }

    private void postOrderTraversalRecursive(Node curNode, List<Key> result) {
        if(curNode == null) return;
        postOrderTraversalRecursive(curNode.left, result);
        postOrderTraversalRecursive(curNode.right, result);
        result.add(curNode.key);
    }

    private void addAllLeftElementsToStack(Stack<Node> s, Node cur) {
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
    }

    private Node getMinInTree(Node node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node getMaxInTree(Node node) {
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node getNodeByValue (Key value) {
        Node cur = root;
        while (cur != null && !cur.key.equals(value)) {
            if(cur.isLessThen(value)) {
                cur = cur.right;
            }
            else if(cur.isGreaterThen(value)) {
                cur = cur.left;
            }
            else return null;
        }
        return cur;
    }

    private int getHeightAndLastUnbalancedNode(Node curNode, Object lastUnbalanced[]) {
        if (curNode == null) {
            return 0/*Height, MAX*/;
        }
        int heightL = getHeightAndLastUnbalancedNode(curNode.left, lastUnbalanced);
        int heightR = getHeightAndLastUnbalancedNode(curNode.right, lastUnbalanced);
        //System.out.println("cur="+curNode.key+" left h="+heightL+ "right h="+heightR);
        if(Math.abs(heightL - heightR) > 1) lastUnbalanced[0] = curNode;
        return 1+Math.max(heightL, heightR) ;
    }

    private Object[] getMinAndMaxDepth(Node curNode) {
        if (curNode == null) {
            return new Object[]{new Integer(0) /*Min*/, new Integer(0)/*Max*/, null/*problematic */};
        } else {
            Object[] minmaxLeft = getMinAndMaxDepth(curNode.left);
            Object[] minmaxRight = getMinAndMaxDepth(curNode.right);

            Object[] res = new Object[3];
            res[0] = 1 + Math.min((Integer) minmaxLeft[0], (Integer) minmaxRight[0]);
            res[1] = 1 + Math.max((Integer) minmaxLeft[1], (Integer) minmaxRight[1]);
            //set abort flag if already clear that three is not balanced
            res[2] = ((Integer)res[1] - (Integer)res[0] > 1)?curNode:null;
            return res;
        }
    }

    //TODO
    private Object[] getMostDeepUnbalancedNode(Node curNode) {
        //Object[6]
        // 0 -curLeft size
        // 1 curRight size
        // 2 - worst left
        // 3 - worst right
        // 4 - worst node


        if (curNode == null) {
            return new Object[]{new Integer(0) /*Left*/, new Integer(0)/*Right*/, null/*problematic */};
        }
        Object[] unbalancedLeft = getMostDeepUnbalancedNode(curNode.left);
        Object[] unbalancedRight = getMostDeepUnbalancedNode(curNode.right);
        int unbalancedLeftLeftObjects =  (Integer)unbalancedLeft[0];
        int unbalancedLeftRightObjects = (Integer)unbalancedLeft[1];

        int unbalancedRightLeftObjects =  (Integer)unbalancedRight[0];
        int unbalancedRightRightObjects = (Integer)unbalancedRight[1];

        Object[] res = new Object[6];
        int curLefts = 1 + MyArrayUtils.getMaxElement(unbalancedLeftLeftObjects, unbalancedLeftRightObjects);
        int curRights= 1 + MyArrayUtils.getMaxElement(unbalancedRightLeftObjects, unbalancedRightRightObjects);
        res[0] = curLefts;
        res[1] = curRights;

        int diffLeft = Math.abs(unbalancedLeftLeftObjects - unbalancedLeftRightObjects);
        int diffRight= Math.abs(unbalancedRightLeftObjects - unbalancedRightRightObjects);
        int diffCur =  Math.abs(curLefts - curRights);

        if(diffLeft > diffRight && diffLeft > diffCur ) {

        }
        return res;
    }


    private BinaryTree<Key> buildTreeFromRoot(Node root) {
        MySuperBinarySearchTree tree = new MySuperBinarySearchTree();
        tree.root = root;
        return tree;
    }

    private  void replaceChild(Node parent, Node toDelete, Node newNode) {
        if(parent != null && parent.left == toDelete) parent.left = newNode;
        if(parent != null && parent.right == toDelete) parent.right = newNode;
    }









    //////////////// TESTS ////////////////












    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void balanceTest() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();

        input.put(1);input.put(2);input.put(3);input.put(4);input.put(5);
        input.put(6);input.put(7);/*input.put(8);input.put(9);input.put(10);
        input.put(11);input.put(12);input.put(13);input.put(14);input.put(15);
        input.put(16);input.put(17);input.put(18);input.put(19);input.put(20);
        System.out.println("height " + input.height());*/
        input.balance();
        System.out.println("NOT WORKING"+input.levelOrderTraversalToLists());

    }

    @Test
    public void rotateRightLeftTest() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(40);
        input.put(30);

        //right LEFT
        input.rotateRight(null, input.root);
        Assert.assertEquals("[30, 40, 50]", "" + input.inOrderTraversal());

        input.rotateRight(null, input.root);
        Assert.assertEquals("[30, 40, 50]", "" + input.inOrderTraversal());

        Assert.assertEquals(3, input.height());

        //LEFT
        input.rotateLeft(null, input.root);
        Assert.assertEquals("[30, 40, 50]", "" + input.inOrderTraversal());

        input.rotateLeft(null, input.root);
        Assert.assertEquals("[30, 40, 50]", "" + input.inOrderTraversal());

        Assert.assertEquals(3, input.height());
    }

    @Test
    public void  constructFromInOrderAndPostOrderTest() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        List<Integer> in = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,4,5,7,10,20}));
        List<Integer> post = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,4,3,7,20,10,5}));
        input.constructFromInOrderAndPostOrder(in, post);
        Assert.assertEquals( ""+in, ""+input.inOrderTraversal());
    }

    @Test
    public void constructFromInOrderAndPreOrderTest() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        List<Integer> in = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,4,5,7,10,20}));
        List<Integer> pre = new ArrayList<Integer>(Arrays.asList(new Integer[]{5,3,1,4,10,7,20}));
        input.constructFromInOrderAndPreOrder(in, pre);
        Assert.assertEquals( ""+in, ""+input.inOrderTraversal());
    }

    @Test
    public void flattenToLinkedListTest() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(40);
        input.put(30);
        input.flattenToLinkedList(input.root);
        Assert.assertEquals("[50, 40, 30]", "" + input.inOrderTraversal());


        input = new MySuperBinarySearchTree<Integer>();
        input.put(10);
        input.put(20);
        input.put(30);
        input.flattenToLinkedList(input.root);
        Assert.assertEquals("[10, 20, 30]", ""+input.inOrderTraversal());
    }


    @Test
    public void levelOrderTraversalToListsTest() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        Assert.assertEquals("[[50]]", "" + input.levelOrderTraversalToLists());

        input.put(30);
        input.put(20);
        Assert.assertEquals("[[50], [30], [20]]", ""+input.levelOrderTraversalToLists());

        input.put(60);
        Assert.assertEquals("[[50], [30, 60], [20]]", "" + input.levelOrderTraversalToLists());

        //          50
        //      30       60
        //  20
        //10
        input.put(10);
        Assert.assertEquals("[[50], [30, 60], [20], [10]]", ""+input.levelOrderTraversalToLists());

        //          50
        //      30       60
        //  20          55  70
        //10               65   75
        input.put(55);input.put(70);input.put(65);input.put(75);
        Assert.assertEquals("[[50], [30, 60], [20, 55, 70], [10, 65, 75]]", ""+input.levelOrderTraversalToLists());
    }

    @Test
    public void testInOrderTraversalAndPut() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        Assert.assertEquals("[]", input.inOrderTraversal() + "");

        input.put(50);
        input.put(30);input.put(70);
        input.put(20);input.put(40); input.put(60);input.put(80);

        Assert.assertEquals("[20, 30, 40, 50, 60, 70, 80]", input.inOrderTraversal() + "");

        exception.expect(RuntimeException.class);
        input.put(null);

    }

    @Test
    public void testGetNodeByValue() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);input.put(70);
        input.put(20);input.put(40); input.put(60);input.put(80);

        Assert.assertEquals(new Integer(20), input.getNodeByValue(20).key);
        Assert.assertEquals(new Integer(50), input.getNodeByValue(50).key);
        Assert.assertNull(input.getNodeByValue(54));
    }

    @Test
    public void testNext() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        Assert.assertNull(input.next(50));

        input.put(50);
        Assert.assertNull(input.next(50));

        input.put(30);
        Assert.assertSame(50, input.next(30));
        Assert.assertNull(input.next(50));

        input.put(70);
        Assert.assertSame(50, input.next(30));
        Assert.assertSame(70, input.next(50));

        input.put(20);input.put(40); input.put(60);input.put(80);
        Assert.assertSame(30, input.next(20));
        Assert.assertSame(80, input.next(70));
        Assert.assertNull(input.next(80));
        Assert.assertNull(input.next(90000));
        Assert.assertSame(20, input.next(0));
    }

    @Test
    public void testPrevious() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        Assert.assertNull(input.previous(50));

        input.put(50);
        Assert.assertNull(input.previous(50));

        input.put(30);
        Assert.assertNull(input.previous(30));
        Assert.assertSame(30, input.previous(50));
    }

    @Test
    public void testHeightAndBalanced() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        Assert.assertSame(0, input.height());

        input.put(50);
        Assert.assertSame(1, input.height());
        Assert.assertEquals(null, getMinAndMaxDepth((Node) input.root)[2]);

        input.put(30);
        Assert.assertSame(2, input.height());

        input.put(20);
        Assert.assertSame(3, input.height());

        input.put(40); input.put(60);input.put(80);
        Assert.assertSame(3, input.height());

        //          50
        //  30          60
        //20    40          80
        input.put(40);
        Assert.assertSame(3, input.height());
        input.put(60);
        Assert.assertSame(3, input.height());
        input.put(80);
        Assert.assertSame(3, input.height());


        input.put(81);
        Assert.assertSame(4, input.height());
        input.put(82);
        Assert.assertSame(5, input.height());
        input.put(83);
        Assert.assertSame(6, input.height());
        System.out.println("TBD testHeightAndBalanced ");

    }


    @Test
    public void testSerialize() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);input.put(70);
        input.put(80);

        List serializedTree = (List)input.serialize();
        List values = (List)serializedTree.get(0);
        int[] leftReft = (int[])serializedTree.get(1);
        int[] rightRefs = (int[])serializedTree.get(2);

        Assert.assertEquals("[50, 30, 70, 80]", values.toString());
        Assert.assertEquals("[1, -1, -1, -1]", Arrays.toString(leftReft));
        Assert.assertEquals("[2, -1, 3, -1]", Arrays.toString(rightRefs));
    }


    @Test
    public void testDeserialize() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);input.put(70);
        input.put(80);
        List serializedTree = (List)input.serialize();
        BinaryTree<Key> deserializedTree = deserializer(serializedTree);
        Assert.assertEquals(input.preOrderTraversal(), deserializedTree.preOrderTraversal());
    }

    @Test
    public void testDelete() {
        MySuperBinarySearchTree<Integer> input = new MySuperBinarySearchTree<Integer>();

        //  50x
        input.put(50);
        input.delete(50);
        Assert.assertEquals("[]", input.inOrderTraversal() + "");

        //  50x
        //30
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);
        input.delete(50);
        Assert.assertEquals("[30]", input.inOrderTraversal() + "");

        //      50x
        //  30
        //    40
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);
        input.put(40);
        input.delete(50);
        Assert.assertEquals("[30, 40]", input.inOrderTraversal() + "");

        //      50
        //  30x
        //    40
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);
        input.put(40);
        input.delete(30);
        Assert.assertEquals("[40, 50]", input.inOrderTraversal() + "");

        //      50
        //  30
        //    40x
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);
        input.put(40);
        input.delete(40);
        Assert.assertEquals("[30, 50]", input.inOrderTraversal() + "");

        //      50
        //  30x
        //20   40
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);
        input.put(40);
        input.put(20);
        input.delete(30);
        Assert.assertEquals("[20, 40, 50]", input.inOrderTraversal() + "");

        //      50
        //  30
        //20x   40
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);
        input.put(40);
        input.put(20);
        input.delete(20);
        Assert.assertEquals("[30, 40, 50]", input.inOrderTraversal() + "");


        //  30x
        //      50
        input = new MySuperBinarySearchTree<Integer>();
        input.put(30);
        input.put(50);
        input.delete(30);
        Assert.assertEquals("[50]", input.inOrderTraversal() + "");


        //  30
        //      50x
        input = new MySuperBinarySearchTree<Integer>();
        input.put(30);
        input.put(50);
        input.delete(50);
        Assert.assertEquals("[30]", input.inOrderTraversal() + "");

        //  30
        //      50x
        //    40
        input = new MySuperBinarySearchTree<Integer>();
        input.put(30);
        input.put(50);
        input.put(40);
        input.delete(50);
        Assert.assertEquals("[30, 40]", input.inOrderTraversal() + "");

        //  30
        //      50x
        //    40  60
        input = new MySuperBinarySearchTree<Integer>();
        input.put(30);
        input.put(50);
        input.put(40);
        input.put(60);
        input.delete(50);
        Assert.assertEquals("[30, 40, 60]", input.inOrderTraversal() + "");

        //         50
        //    30        70
        // 20   40     60  80
        input = new MySuperBinarySearchTree<Integer>();
        input.put(50);
        input.put(30);input.put(70);
        input.put(20);input.put(40); input.put(60);input.put(80);

        Assert.assertEquals("[20, 30, 40, 50, 60, 70, 80]", input.inOrderTraversal() + "");
        input.delete(50);
        Assert.assertEquals("[20, 30, 40, 60, 70, 80]", input.inOrderTraversal() + "");
        Assert.assertSame(60, input.root.key);
        input.delete(30);
        Assert.assertEquals("[20, 40, 60, 70, 80]", input.inOrderTraversal() + "");
    }

    @Test
    public void testMaxSum() {
        MySuperBinarySearchTree input = new MySuperBinarySearchTree<Integer>();
        input.put(-3);
        Assert.assertEquals(-3, input.maxPassSum());

        input = new MySuperBinarySearchTree<Integer>();
        input.put(2);
        input.put(-1);
        Assert.assertEquals(2, input.maxPassSum());


        //  3
        //2     4
        //1         5
        input = new MySuperBinarySearchTree<Integer>();
        input.put(3);
        input.put(2);
        input.put(1);
        input.put(4);
        input.put(5);
        Assert.assertEquals(15, input.maxPassSum());


        input = new MySuperBinarySearchTree<Integer>();
        //         50
        input.put(50);
        Assert.assertEquals(50, input.maxPassSum());

        //         50
        //    30
        input.put(30);
        Assert.assertEquals(80, input.maxPassSum());

        //         50
        //    30
        // 20
        input.put(20);
        Assert.assertEquals(100, input.maxPassSum());

        //         50
        //    30
        // 20   40
        input.put(40);
        Assert.assertEquals(120, input.maxPassSum());

        //         50
        //    30        70
        // 20   40
        input.put(70);
        Assert.assertEquals(190, (input.maxPassSum()));

        //         50
        //    30        70
        // 20   40     60  80
        input.put(70);
        input.put(60);input.put(80);
        Assert.assertEquals(270, (input.maxPassSum()));

    }

    @Test
    public void testIsBalanced() {
        MySuperBinarySearchTree input = new MySuperBinarySearchTree<Integer>();
        //         50
        input.put(50);
        Assert.assertEquals(50, input.maxPassSum());
        Assert.assertTrue(input.isBalanced());

        //         50
        //    30
        input.put(30);
        Assert.assertTrue(input.isBalanced());

        //              50
        //          30
        //      20
        input.put(20);
        Assert.assertFalse(input.isBalanced());


        //                      50
        //              30            60
        //         20      40     55     70
        //    10    25    35 45  51 57
        //  5   15
        input.put(40);
        input.put(60);
        input.put(55);
        input.put(70);
        input.put(10);
        input.put(25);
        input.put(35);
        input.put(45);
        input.put(51);
        input.put(57);
        input.put(5);
        input.put(15);

        Assert.assertTrue(input.isBalanced());
        //{1,2,2,3,3,3,3,4,4,4,4,4,4,#,#,5,5}
        input = new MySuperBinarySearchTree<Integer>();
    }



    private class Node {
        public Node(Key key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        private Key key;           // sorted by key
        private Node left, right;  // left and right subtrees

        private boolean isLessThen(Node n) {
            return this.key.compareTo(n.key) < 0;
        }

        private boolean isGreaterThen(Node n) {
            return this.key.compareTo(n.key) > 0;
        }

        private boolean isLessThen(Key k) {
            return this.key.compareTo(k) < 0;
        }

        private boolean isGreaterThen(Key k) {
            return this.key.compareTo(k) > 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            if (key != null ? !key.equals(node.key) : node.key != null) return false;
            return true;
        }

        @Override
        public String toString() {
            return "Node="+ key+"|"+(left==null?null:left.key)+"|"+(right==null?null:right.key);
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }
}
