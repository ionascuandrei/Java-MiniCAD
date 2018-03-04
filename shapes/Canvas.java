package shapes;

import tools.Shape;
import tools.Visitor;

public class Canvas implements Shape {
    private int height;
    private int width;
    private int color;

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    public final int getColor() {
        return color;
    }

    public Canvas(final int height, final int width, final int color) {
        this.height = height;
        this.width = width;
        this.color = color;
    }

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }
}
