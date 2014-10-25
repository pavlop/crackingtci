package photoshop;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
public class Canvas {

    List<ShapeComponent> componentOnCanvas = new ArrayList<>();
    public char[][] canvas;

    public Canvas (int vert, int hor) {
        canvas = new char[vert][hor];
    }

    public void put (ShapeComponent s) {
        componentOnCanvas.add(s);
    }

    public void remove (ShapeComponent s) {
        componentOnCanvas.remove(s);
    }

}
