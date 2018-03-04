package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

public class Square implements Shape {
    private Pixel start;
    private int contourColor;
    private int fillColor;
    private int length;

    public Square(final Pixel start, final int length,
                  final int contourColor, final int fillColor) {
        this.start = start;
        this.fillColor = fillColor;
        this.contourColor = contourColor;
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

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }

}
