package photoshop;

/**
 *
 *
 *
 */
public class ShapeEditor {

    private Canvas c;
    public ShapeEditor () {
        Canvas c = new Canvas(20, 20);
    }

    public void putShape(ShapeComponent s) {
        c.put(s);
    }

    public void removeShape(ShapeComponent s) {
        c.remove(s);
    }

    public static void main(String[] args) {
        ShapeEditor editor = new ShapeEditor();
        editor.putShape(new CircleShape(0, 0, 5, 5));
        editor.putShape(new CircleShape(10, 10, 15, 15));
        editor.putShape(new RecShape());

        ShapeComposite cirles = new ShapeComposite();

    }
}
