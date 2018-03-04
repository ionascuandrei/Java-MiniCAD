package tools;

import shapes.Canvas;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

import java.awt.Color;
import java.util.ArrayList;

public final class ShapeFactory {
    /* Initializarea instantei ShapeFactory-ului pentru oferirea sa catre main, doar la cerere,
    cu ajutorul Singleton Pattern-ului.*/
    private static ShapeFactory instance = new ShapeFactory();

    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;
    private static final int FIFTH = 5;
    private static final int SIXTH = 6;
    private static final int SEVENTH = 7;
    private static final int HEX = 16;

    /*Constructor privat pentru a nu putea fi accesat din exteriorul clasei*/
    private ShapeFactory() {
    }

    /**
     * Metoda ce primeste un string cu datele complete despre forma dorita pentru a fi instantiata,
     * care va parsa toate datele si le va trimite catre contrusctorii clasei dorite.
     *
     * @param data String-ul primit din fisier cu date despre forma
     * @return Instanta formei dorite
     */
    public Shape getShape(final String data) {
        //Descompunerea string-ului pe componente.
        String[] line = data.split(" ");
        //Construirea fundalului
        if (line[0].equals("CANVAS")) {
            return new Canvas(Integer.parseInt(line[FIRST]), Integer.parseInt(line[SECOND]),
                    getRGBValue(line[THIRD], line[FOURTH]));
        }
        //Construirea liniei
        if (line[0].equals("LINE")) {
            return new Line(new Pixel(Integer.parseInt(line[FIRST]),
                    Integer.parseInt(line[SECOND])), new Pixel(Integer.parseInt(line[THIRD]),
                    Integer.parseInt(line[FOURTH])), getRGBValue(line[FIFTH], line[SIXTH]));
        }
        //Construirea triunghiului
        if (line[0].equals("TRIANGLE")) {
            return new Triangle(new Pixel(Integer.parseInt(line[FIRST]),
                    Integer.parseInt(line[SECOND])), new Pixel(Integer.parseInt(line[THIRD]),
                    Integer.parseInt(line[FOURTH])), new Pixel(Integer.parseInt(line[FIFTH]),
                    Integer.parseInt(line[SIXTH])), getRGBValue(line[7], line[8]),
                    getRGBValue(line[9], line[10]));
        }
        //Construirea patratului
        if (line[0].equals("SQUARE")) {
            return new Square(new Pixel(Integer.parseInt(line[FIRST]),
                    Integer.parseInt(line[SECOND])), Integer.parseInt(line[THIRD])-1,
                    getRGBValue(line[FOURTH], line[FIFTH]), getRGBValue(line[SIXTH], line[7]));
        }
        //Construirea dreptunghiului
        if (line[0].equals("RECTANGLE")) {
            return new Rectangle(new Pixel(Integer.parseInt(line[FIRST]),
                    Integer.parseInt(line[SECOND])), Integer.parseInt(line[THIRD])-1,
                    Integer.parseInt(line[FOURTH])-1, getRGBValue(line[FIFTH], line[SIXTH]),
                    getRGBValue(line[7], line[8]));
        }
        //Construirea cercului
        if (line[0].equals("CIRCLE")) {
            return new Circle(new Pixel(Integer.parseInt(line[FIRST]),
                    Integer.parseInt(line[SECOND])), Integer.parseInt(line[THIRD]),
                    getRGBValue(line[FOURTH], line[FIFTH]), getRGBValue(line[SIXTH], line[7]));
        }
        //Construirea rombului
        if (line[0].equals("DIAMOND")) {
            return new Diamond(new Pixel(Integer.parseInt(line[FIRST]),
                    Integer.parseInt(line[SECOND])), getRGBValue(line[FIFTH], line[SIXTH]),
                    getRGBValue(line[7], line[8]), Integer.parseInt(line[THIRD]),
                    Integer.parseInt(line[FOURTH]));
        }
        //Construirea poligonului
        if (line[0].equals("POLYGON")) {
            int numberOfPoints = Integer.parseInt(line[FIRST]);
            ArrayList<Pixel> pixels = new ArrayList<>();
            for (int i = 0; i < numberOfPoints * 2; i += 2) {
                pixels.add(new Pixel(Integer.parseInt(line[i + 2]), Integer.parseInt(line[i + 3])));
            }
            return new Polygon(numberOfPoints, pixels,
                    getRGBValue(line[numberOfPoints * 2 + 2], line[numberOfPoints * 2 + 3]),
                    getRGBValue(line[numberOfPoints * 2 + 4], line[numberOfPoints * 2 + 5]));
        }
        return null;
    }

    /**
     * Returneaza valoarea RGBA in functie de string-ul dat.
     *
     * @param rgb   String-ul in hexa pentru valoarea RGB
     * @param alpha String-ul pentru alpha
     * @return Valoarea int pentru RGBA-ul dat ca intrare
     */
    private static int getRGBValue(final String rgb, final String alpha) {
        int red = Integer.parseInt(rgb.substring(FIRST, THIRD), HEX);
        int green = Integer.parseInt(rgb.substring(THIRD, FIFTH), HEX);
        int blue = Integer.parseInt(rgb.substring(FIFTH, SEVENTH), HEX);
        int a = Integer.parseInt(alpha);
        Color color = new Color(red, green, blue, a);
        return color.getRGB();

    }

    /**
     * Returnarea instantei pentru apelarea ShapeFactory-ului.
     *
     * @return instanta clasei ShapeFactory
     */
    public static ShapeFactory getInstance() {
        return instance;
    }
}
