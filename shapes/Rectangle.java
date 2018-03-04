package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

public class Rectangle implements Shape {
    private Pixel start;
    private int contourColor;
    private int fillColor;
    private int height;
    private int length;

    public Rectangle(final Pixel start, final int height, final int length,
                     final int contourColor, final int fillColor) {
        this.start = start;
        this.contourColor = contourColor;
        this.fillColor = fillColor;
        this.height = height;
        this.length = length;
    }

    public final Pixel getStart() {
        return start;
    }

    public final int getContourColor() {
        return contourColor;
    }

    public final int getFillColor() {
        return fillColor;
    }

    public final int getLength() {
        return length;
    }

    public final int getHeight() {
        return height;
    }

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }

}
