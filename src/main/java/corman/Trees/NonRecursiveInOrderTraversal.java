package corman.Trees;

import crackinginterview.datastructures.x3.StacksAndQues.Stack;
import crackinginterview.datastructures.x4.TreesAndGraphs.MyBinaryTree;
import crackinginterview.datastructures.x4.TreesAndGraphs.TNode;
import java.util.*;
import org.junit.Test;

/**
 *
 Give a nonrecursive algorithm that performs an in order tree walk.
 (Hint: An easy solution uses a stack as an auxiliary data structure. A more complicated, but ele- gant,
 solution uses no stack but assumes that we can test two pointers for equality.)
 */
public class NonRecursiveInOrderTraversal {

    MyBinaryTree tree;

    public static void printInOrderNoRecursion(TNode head) {
        Stack<TNode> stack = new Stack();
        Set<TNode> alreadyVisited = new HashSet<TNode>();
        stack.push(head);
        while (stack.peek() != null) {
            System.out.println(stack);
            if(stack.peek().left != null && !alreadyVisited.contains(stack.peek().left)) {
                stack.push(stack.peek().left);
            } else {
                TNode toPrint = stack.pop();
                alreadyVisited.add(toPrint);
                System.out.println(toPrint.value);
                if (toPrint.right != null) {
                    stack.push(toPrint.right);
                }
            }
        }
    }

    public static void printInOrderNoRecursionVova(TNode head) {
        Stack<TNode> stack = new Stack();
        addAllLeftElementsToStack(stack, head);
        while (stack.peek() != null) {
            TNode cur = stack.pop();
            System.out.println("vova: " + cur.value);
            if(cur.right != null) {
                addAllLeftElementsToStack(stack, cur.right);
            }
        }
    }

    private static void addAllLeftElementsToStack(Stack<TNode> s, TNode cur) {
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
    }

    @Test
    public void doTest() {
        //         10
        //    5         14
        //  3    7   13   21
        //-1  4

        TNode botL = new TNode(null, null, -1);
        TNode botR = new TNode(null, null, 4);

        TNode n3 = new TNode(botL, botR, 3);
        TNode n7 = new TNode(null, null, 7);
        TNode n5 = new TNode(n3, n7, 5);

        TNode n13 = new TNode(null, null, 13);
        TNode n21 = new TNode(null, null, 21);
        TNode n14 = new TNode(n13, n21, 14);

        TNode head = new TNode(n5, n14, 10);

        printInOrderNoRecursion(head);
        System.out.println("\n----------------------");
        System.out.println("\n----------------------");
        printInOrderNoRecursionVova(head);
    }
}


