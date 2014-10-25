package crackinginterview.x8.dynamic;

import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

/**
 *
 *
 *
 */
public class FirstCommonAncestorImpl {
    Node one;
    Node two;
    Node commonParent = null;

    Node commonAncestor(Node root, Node one, Node two) {
        this.one = one;
        this.two = two;
        numberOfNodesUnder(root);
        return commonParent;
    }

    int numberOfNodesUnder(Node root) {
        int res = 0;
        if (root == null) return 0;
        if (commonParent!= null) return 0; // already found!

        res += numberOfNodesUnder(root.left);
        res += numberOfNodesUnder(root.right);

        if (root == one || root == two) res += 1;
        if (res == 2) commonParent = root;
        return res;
    }

    class Node {
        Node(int val) {
            this.val = val;
        }

        public Node left, right;
        public int val;

    }

    @Test
    public void test() {
        FirstCommonAncestorImpl fca = new FirstCommonAncestorImpl();
        Node n1 = new Node(1);
        Assert.assertSame(null, fca.commonAncestor(n1, n1, n1));

        Node n2 = new Node(2);
        n2.left = n1;
        Assert.assertSame(n2, fca.commonAncestor(n2, n1, n2));
        Assert.assertSame(n2, fca.commonAncestor(n2, n2, n1));

        Node n3 = new Node(3);
        n2.right = n3;
        Assert.assertSame(n2, fca.commonAncestor(n2, n2, n3));

        Node n4 = new Node(4);
        Node n5 = new Node(5);
    }

}

class HashTable<T> {

    Collection<T> table [] ;

    public HashTable() {
        this(100);
    }

    public HashTable(int initialCapacity) {
        table = new ArrayList[initialCapacity]; //Load 0.75
        // set with empty lists
        for (int i = 0; i < table.length; i++) table[i] = new ArrayList<T>();
    }

    public void put(T element) {
        int pos = getPosition(element);
        isElementPresent(element, true);
        table[pos].add(element);
    }

    public boolean contains(T element) {
        return isElementPresent(element, false);
    }

    public void remove(T element) {
        isElementPresent(element, true);
    }

    private boolean isElementPresent(T element, boolean shouldRemoveIfFind) {
        int divPart = getPosition(element);
        Collection<T> elemntsTehere = table[divPart];
        for (Iterator<T> it = elemntsTehere.iterator(); it.hasNext();) {
            T elementTehere = it.next();
            if (elementTehere.equals(element)) {
                if (shouldRemoveIfFind) it.remove();
                return true;
            }
        }
        return false;
    }

    private int getPosition(T element) {
        int hashCode = element.hashCode();
        int divPart =  hashCode % table.length;
        return divPart;
    }

}
