package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

public class Circle implements Shape {
    private Pixel center;
    private int contourColor;
    private int fillColor;
    private int radius;

    public Circle(final Pixel center, final int radius,
                  final int contourColor, final int fillColor) {
        this.center = center;
        this.contourColor = contourColor;
        this.fillColor = fillColor;
        this.radius = radius;
    }

    public final Pixel getCenter() {
        return center;
    }

    public final int getContourColor() {
        return contourColor;
    }

    public final int getFillColor() {
        return fillColor;
    }

    public final int getRadius() {
        return radius;
    }

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }
}
