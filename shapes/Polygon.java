package shapes;

import tools.Pixel;
import tools.Shape;
import tools.Visitor;

import java.util.ArrayList;

public class Polygon implements Shape {
    private int numberOfPoints;
    private ArrayList<Pixel> pixels;
    private int contourColor;
    private int fillColor;

    public Polygon(final int numberOfPoints, final ArrayList<Pixel> pixels,
                   final int contourColor, final int fillColor) {
        this.numberOfPoints = numberOfPoints;
        this.pixels = pixels;
        this.contourColor = contourColor;
        this.fillColor = fillColor;
    }

    public final int getNumberOfPoints() {
        return numberOfPoints;
    }

    public final ArrayList<Pixel> getPixels() {
        return pixels;
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
