package photoshop;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
public class ShapeComposite implements ShapeComponent{

    //Collection of child graphics.
    private List<ShapeComponent> childGraphics = new ArrayList<ShapeComponent>();

    @Override
    public void drow(Canvas c) {
        for (ShapeComponent shape:childGraphics) shape.drow(c);
    }

    //Adds the shape to the composition.
    public void add(ShapeComponent shape) {
        childGraphics.add(shape);
    }

    //Removes the shape from the composition.
    public void remove(ShapeComponent shape) {
        childGraphics.remove(shape);
    }
}
