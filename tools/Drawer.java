package tools;

import shapes.Canvas;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Drawer implements Visitor {
    //BufferedImage-ul in care ne construim imaginea
    private BufferedImage image;

    private static final int TWO = 2;

    public final BufferedImage getImage() {
        return image;
    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru canvas.
     *
     * @param object Instanta canvas-ului pe care dorim sa o desenam.
     */
    @Override
    public final void draw(final Canvas object) {
        int width = object.getWidth();
        int height = object.getHeight();
        //Instantierea canvas-ului in functie de inaltime si latime
        this.image = new BufferedImage(width, height, TYPE_INT_ARGB);

        //Colorarea canvas-ului
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, object.getColor());
            }
        }
    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru linie.
     *
     * @param object Instanta liniei pe care dorim sa o desenam.
     */
    @Override
    public final void draw(final Line object) {
        bresenhamAlgorithm(this.image, object.getStart(), object.getEnd(), object.getColor());
    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru cerc.
     *
     * @param object Instanta cercului pe care dorim sa il desenam.
     */
    @Override
    public final void draw(final Circle object) {
        int xc = object.getCenter().getX();
        int yc = object.getCenter().getY();
        int r = object.getRadius();
        int x = 0, y = r;
        int d = 3 - 2 * r;

        //Algoritmul de desenarea a conturului unui cerc
        while (y >= x) {
            drawCircle(image, xc, yc, x, y, object.getContourColor());
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            drawCircle(image, xc, yc, x, y, object.getContourColor());
        }
        //Colorarea interiorului cercului, pornind din centrul acestuia
        floodFillImage(image, object.getCenter(), object.getFillColor(), object.getContourColor());
    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru triunghi.
     *
     * @param object Instanta triunghiului pe care dorim sa il desenam.
     */
    @Override
    public final void draw(final Triangle object) {
        //Desenarea conturului
        bresenhamAlgorithm(this.image, object.getFirst(), object.getSecond(),
                object.getContourColor());
        bresenhamAlgorithm(this.image, object.getSecond(), object.getThird(),
                object.getContourColor());
        bresenhamAlgorithm(this.image, object.getThird(), object.getFirst(),
                object.getContourColor());
        //Calcularea punctului care va fi sigur in interiorul formei
        int centerX = (object.getFirst().getX() + object.getSecond().getX()
                + object.getThird().getX()) / 3;
        int centerY = (object.getFirst().getY() + object.getSecond().getY()
                + object.getThird().getY()) / 3;
        //Colorarea interiorului triunghiului
        floodFillImage(image, new Pixel(centerX, centerY), object.getFillColor(),
                object.getContourColor());
    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru patrat.
     *
     * @param object Instanta patratului pe care dorim sa il desenam.
     */
    @Override
    public final void draw(final Square object) {
        //Calcularea fiecarui colt al formei
        Pixel upRight = new Pixel(object.getStart().getX() + object.getLength(),
                object.getStart().getY());
        Pixel downLeft = new Pixel(object.getStart().getX(),
                object.getStart().getY() + object.getLength());
        Pixel downRight = new Pixel(downLeft.getX() + object.getLength(), downLeft.getY());
        //Desenarea conturului
        bresenhamAlgorithm(this.image, object.getStart(), upRight, object.getContourColor());
        bresenhamAlgorithm(this.image, object.getStart(), downLeft, object.getContourColor());
        bresenhamAlgorithm(this.image, downLeft, downRight, object.getContourColor());
        bresenhamAlgorithm(this.image, upRight, downRight, object.getContourColor());
        //Colorarea formei
        for (int i = object.getStart().getX() + 1;
             i < (object.getStart().getX() + object.getLength()); i++) {
            for (int j = object.getStart().getY() + 1;
                 j < (object.getStart().getY() + object.getLength()); j++) {
                if (isInside(image, new Pixel(i, j))) {
                    image.setRGB(i, j, object.getFillColor());
                }
            }
        }

    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru dreptunghi.
     *
     * @param object Instanta dreptunghiului pe care dorim sa il desenam.
     */
    @Override
    public final void draw(final Rectangle object) {
        //Calcularea fiecarui colt al formei
        Pixel upRight = new Pixel(object.getStart().getX() + object.getLength(),
                object.getStart().getY());
        Pixel downLeft = new Pixel(object.getStart().getX(),
                object.getStart().getY() + object.getHeight());
        Pixel downRight = new Pixel(downLeft.getX() + object.getLength(), downLeft.getY());
        //Desenarea conturului
        bresenhamAlgorithm(this.image, object.getStart(), upRight, object.getContourColor());
        bresenhamAlgorithm(this.image, object.getStart(), downLeft, object.getContourColor());
        bresenhamAlgorithm(this.image, downLeft, downRight, object.getContourColor());
        bresenhamAlgorithm(this.image, upRight, downRight, object.getContourColor());
        //Colorarea formei
        for (int i = object.getStart().getX() + 1;
             i < (object.getStart().getX() + object.getLength()); i++) {
            for (int j = object.getStart().getY() + 1;
                 j < (object.getStart().getY() + object.getHeight()); j++) {
                if (isInside(image, new Pixel(i, j))) {
                    image.setRGB(i, j, object.getFillColor());
                }
            }
        }

    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru romb.
     *
     * @param object Instanta rombului pe care dorim sa il desenam.
     */
    @Override
    public final void draw(final Diamond object) {
        //Calcularea punctelor rombului
        Pixel right = new Pixel(object.getCenter().getX() + object.getOrizontalDimension() / 2,
                object.getCenter().getY());
        Pixel left = new Pixel(object.getCenter().getX() - object.getOrizontalDimension() / 2,
                object.getCenter().getY());
        Pixel bottom = new Pixel(object.getCenter().getX(),
                object.getCenter().getY() - object.getVerticalDimension() / 2);
        Pixel top = new Pixel(object.getCenter().getX(),
                object.getCenter().getY() + object.getVerticalDimension() / 2);
        //Desenarea conturului
        bresenhamAlgorithm(this.image, left, top, object.getContourColor());
        bresenhamAlgorithm(this.image, top, right, object.getContourColor());
        bresenhamAlgorithm(this.image, right, bottom, object.getContourColor());
        bresenhamAlgorithm(this.image, bottom, left, object.getContourColor());
        //Colorarea formei
        floodFillImage(image, object.getCenter(), object.getFillColor(), object.getContourColor());
    }

    /**
     * Metoda ce va aplica un algoritm de desenare pentru poligon.
     *
     * @param object Instanta poligonului pe care dorim sa o desenam.
     */
    @Override
    public final void draw(final Polygon object) {
        int sumX = 0;
        int sumY = 0;
        //Desenarea conturului si calcularea sumei tuturor coordonatelor
        for (int i = 0; i < object.getNumberOfPoints() - 1; i++) {
            sumX += object.getPixels().get(i + 1).getX();
            sumY += object.getPixels().get(i + 1).getY();
            bresenhamAlgorithm(this.image, object.getPixels().get(i), object.getPixels().get(i + 1),
                    object.getContourColor());
        }
        sumX += object.getPixels().get(0).getX();
        sumY += object.getPixels().get(0).getY();
        bresenhamAlgorithm(this.image, object.getPixels().get(object.getNumberOfPoints() - 1),
                object.getPixels().get(0), object.getContourColor());
        sumX = sumX / object.getNumberOfPoints();
        sumY = sumY / object.getNumberOfPoints();
        //Colorarea formei
        floodFillImage(image, new Pixel(sumX, sumY), object.getFillColor(),
                object.getContourColor());
    }

    /**
     * Metoda statica ce verifica daca pixelul actual este in interiorul canvas-ului.
     *
     * @param image Imaginea actuala
     * @param pixel Picelul curent
     * @return True/False
     */
    private static boolean isInside(final BufferedImage image, final Pixel pixel) {
        return pixel.getX() >= 0 && pixel.getY() >= 0
                && pixel.getX() < image.getWidth() && pixel.getY() < image.getHeight();
    }

    /**
     * Metoda de desenare a punctelor de pe conturul cercului.
     *
     * @param image Imaginea actuala
     * @param xc    Coordonata x pentru centrul cercului
     * @param yc    Coordonata y pentru centrul cercului
     * @param x     Coordonata x pentru un punct de ajutor in desenarea punctelor pe contur
     * @param y     Coordonata y pentru un punct de ajutor in desenarea punctelor pe contur
     * @param color Culoarea
     */
    private static void drawCircle(final BufferedImage image, final int xc, final int yc,
                                   final int x, final int y, final int color) {
        if (isInside(image, new Pixel(xc + x, yc + y))) {
            image.setRGB(xc + x, yc + y, color);
        }
        if (isInside(image, new Pixel(xc - x, yc + y))) {
            image.setRGB(xc - x, yc + y, color);
        }
        if (isInside(image, new Pixel(xc + x, yc - y))) {
            image.setRGB(xc + x, yc - y, color);
        }
        if (isInside(image, new Pixel(xc - x, yc - y))) {
            image.setRGB(xc - x, yc - y, color);
        }
        if (isInside(image, new Pixel(xc + y, yc + x))) {
            image.setRGB(xc + y, yc + x, color);
        }
        if (isInside(image, new Pixel(xc - y, yc + x))) {
            image.setRGB(xc - y, yc + x, color);
        }
        if (isInside(image, new Pixel(xc + y, yc - x))) {
            image.setRGB(xc + y, yc - x, color);
        }
        if (isInside(image, new Pixel(xc - y, yc - x))) {
            image.setRGB(xc - y, yc - x, color);
        }
    }

    /**
     * Metoda ce traseaza o linie intre doua puncte, pe baza algoritmului Bresenham.
     *
     * @param image Imaginea actuala
     * @param start Punctul initial
     * @param end   Punctul final
     * @param color Culoarea
     */
    private static void bresenhamAlgorithm(final BufferedImage image, final Pixel start,
                                           final Pixel end, final int color) {

        int x = start.getX();
        int y = start.getY();
        boolean interchange = false;

        int deltaX = Math.abs(end.getX() - x);
        int deltaY = Math.abs(end.getY() - y);
        int s1 = (int) Math.signum(end.getX() - x);
        int s2 = (int) Math.signum(end.getY() - y);

        if (deltaY > deltaX) {
            deltaX = deltaX + deltaY;
            deltaY = deltaX - deltaY;
            deltaX = deltaX - deltaY;
            interchange = true;
        }

        int error = TWO * deltaY - deltaX;

        for (int i = 0; i <= deltaX; i++) {
            if (isInside(image, new Pixel(x, y))) {
                image.setRGB(x, y, color);
            }

            while (error > 0) {
                if (interchange) {
                    x = x + s1;
                } else {
                    y = y + s2;
                }
                error = error - TWO * deltaX;
            }

            if (interchange) {
                y = y + s2;
            } else {
                x = x + s1;
            }
            error = error + TWO * deltaY;
        }
    }

    /**
     * Metoda ce coloreaza interiorul unei formei pe baza algoritmului FloodFill.
     *
     * @param image       Imaginea actuala
     * @param center      Centrul formei
     * @param fillColor   Culoarea interiorului
     * @param borderColor Culoarea marginii formei pentru respectarea marginilor
     */
    private static void floodFillImage(final BufferedImage image, final Pixel center,
                                       final int fillColor, final int borderColor) {
        boolean[][] hits = new boolean[image.getHeight()][image.getWidth()];

        Queue<Pixel> queue = new LinkedList<Pixel>();
        queue.add(center);

        while (!queue.isEmpty()) {
            Pixel p = queue.remove();

            if (floodFillImageDo(image, hits, p.getX(), p.getY(), borderColor, fillColor)) {
                queue.add(new Pixel(p.getX(), p.getY() - 1));
                queue.add(new Pixel(p.getX(), p.getY() + 1));
                queue.add(new Pixel(p.getX() - 1, p.getY()));
                queue.add(new Pixel(p.getX() + 1, p.getY()));
            }
        }
    }

    /**
     * Desenarea pixelilor valizi din algoritmul FloodFill.
     *
     * @param image       Imaginea curenta
     * @param hits        Pixelii care au fost parcursi in algoritm
     * @param x           Coordonata x a pixelului
     * @param y           Coordonata y a pixelului
     * @param borderColor Culoarea marginii
     * @param fillColor   Culoarea interiorului
     * @return True/False
     */
    private static boolean floodFillImageDo(final BufferedImage image, final boolean[][] hits,
                                            final int x, final int y,
                                            final int borderColor, final int fillColor) {
        if (y < 0) {
            return false;
        }
        if (x < 0) {
            return false;
        }
        if (y > image.getHeight() - 1) {
            return false;
        }
        if (x > image.getWidth() - 1) {
            return false;
        }
        if (hits[y][x]) {
            return false;
        }
        if (image.getRGB(x, y) == borderColor) {
            return false;
        }
        image.setRGB(x, y, fillColor);
        hits[y][x] = true;
        return true;
    }
}
