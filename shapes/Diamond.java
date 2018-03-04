package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

public class Diamond implements Shape {
    private Pixel center;
    private int contourColor;
    private int fillColor;
    private int orizontalDimension;
    private int verticalDimension;

    public Diamond(final Pixel center, final int contourColor, final int fillColor,
                   final int orizontalDimension, final int verticalDimension) {
        this.center = center;
        this.contourColor = contourColor;
        this.fillColor = fillColor;
        this.orizontalDimension = orizontalDimension;
        this.verticalDimension = verticalDimension;
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

    public final int getOrizontalDimension() {
        return orizontalDimension;
    }

    public final int getVerticalDimension() {
        return verticalDimension;
    }

    @Override
    public final void accept(final Visitor visitor) {
        visitor.draw(this);
    }
}
