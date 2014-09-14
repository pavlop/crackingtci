package crackinginterview.x8.dynamic;

import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * You have a stack of n boxes, with widths w.,
 * heights l\ and depths d r
 * The boxes cannot be rotated and can only be stacked
 * on top of one another if each box in the stack is strictly
 * larger than the box above it in width, height, and depth.
 * Implement a method to build the tallest stack possible,
 * where the height of a stack is the sum of the heights of each box.
 */
public class BoxesPiramida {

    public static int orderBoxesGetMaxHeight(Stack<Box> usedBoxes, Queue<Box> boxes, int curHeight) {
        int curMaxHeight = curHeight;
        Box bestBox = null;
        //System.out.println("boxes.siezs "+boxes.size());
        //System.out.println("engagedBoxes:" + usedBoxes + " boxes:" + boxes);

        for (int i = 0 ; i < boxes.size(); i++) {
            Box curBox = boxes.remove();
            //System.out.println("curBox +"+curBox);
            if(usedBoxes.isEmpty() || curBox.canBePlacedOnTopOf(usedBoxes.peek())) {
                Stack<Box> newUsedBoxes = new Stack<Box>();
                newUsedBoxes.addAll(usedBoxes);
                newUsedBoxes.push(curBox);
                int calculatedHeight = orderBoxesGetMaxHeight(newUsedBoxes, new LinkedList<Box>(boxes), curHeight + curBox.height);
                curMaxHeight = Math.max(curMaxHeight, calculatedHeight);
                if(curMaxHeight == calculatedHeight) bestBox = curBox;
                //usedBoxes.pop();
            }

            boxes.add(curBox);
        }
        if (bestBox != null) usedBoxes.push(bestBox);
        System.out.println("engagedBoxes:" + usedBoxes );
        return curMaxHeight;
    }


    @Test
    public void orderBoxesGetMaxHeightTesting() {
        Queue<Box> boxes = new LinkedList<Box>();
        boxes.add(new Box(3, 3, 3));
        boxes.add(new Box(2, 2, 2));
        boxes.add(new Box(1, 1, 1));
        assertEquals(6, orderBoxesGetMaxHeight(new Stack<Box>(), boxes, 0));

        boxes.add(new Box(5, 5, 5));
        assertEquals(11, orderBoxesGetMaxHeight(new Stack<Box>(), boxes, 0));

        //boxes.add(new Box(0, 0, 0));
        //assertEquals(11, orderBoxesGetMaxHeight(new Stack<Box>(), boxes, 0));

        //boxes.add(new Box(-5, -5, -5));
        //assertEquals(11, orderBoxesGetMaxHeight(new Stack<Box>(), boxes, 0));
    }

}
class Box {
    public int width, length, height;

    Box(int width, int length, int height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public boolean canBePlacedOnTopOf(Box under) {
        //always can plce if there is no previous box
        if(under == null) return true;

        return under.height > height && under.width > width && under.length > length;
    }

    @Override
    public String toString() {
        return "{" +
                "w=" + width +
                ", l=" + length +
                ", h=" + height +
                '}';
    }
}
