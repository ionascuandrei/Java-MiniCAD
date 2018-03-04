package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

public class Line implements Shape {
    private Pixel start;
    private Pixel end;
    private int color;

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;

    public Line(final Pixel start, final Pixel end, final int color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public final int getColor() {
        return color;
    }

    public final Pixel getStart() {
        return start;
    }

    public final Pixel getEnd() {
        return end;
    }

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }
}
