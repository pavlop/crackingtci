package photoshop;

/**
 *
 *
 *
 */
public class CircleShape implements ShapeComponent {

    int x1; int y1; int x2; int y2;

    public CircleShape(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void drow(Canvas canvas) {
        System.out.println("Drowing Circle");
        canvas.canvas[x1][y1] = 'c';
        canvas.canvas[x2][y2] = 'c';
    }



}
