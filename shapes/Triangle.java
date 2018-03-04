package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

public class Triangle implements Shape {
    private Pixel first;
    private Pixel second;
    private Pixel third;
    private int contourColor;
    private int fillColor;

    public Triangle(final Pixel first, final Pixel second, final Pixel third,
                    final int contourColor, final int fillColor) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.contourColor = contourColor;
        this.fillColor = fillColor;
    }

    public final Pixel getFirst() {
        return first;
    }

    public final Pixel getSecond() {
        return second;
    }

    public final Pixel getThird() {
        return third;
    }

    public final int getContourColor() {
        return contourColor;
    }

    public final int getFillColor() {
        return fillColor;
    }

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }
}
