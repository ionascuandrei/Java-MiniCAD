package tools;

import shapes.Canvas;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

public interface Visitor {
    /**
     * Metoda ce va aplica un algoritm de desenare pentru linie.
     * @param object Instanta liniei pe care dorim sa o desenam.
     */
    void draw(Line object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru cerc.
     * @param object Instanta cercului pe care dorim sa il desenam.
     */
    void draw(Circle object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru triunghi.
     * @param object Instanta triunghiului pe care dorim sa il desenam.
     */
    void draw(Triangle object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru patrat.
     * @param object Instanta patratului pe care dorim sa il desenam.
     */
    void draw(Square object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru dreptunghi.
     * @param object Instanta dreptunghiului pe care dorim sa il desenam.
     */
    void draw(Rectangle object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru romb.
     * @param object Instanta rombului pe care dorim sa il desenam.
     */
    void draw(Diamond object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru poligon.
     * @param object Instanta poligonului pe care dorim sa o desenam.
     */
    void draw(Polygon object);
    /**
     * Metoda ce va aplica un algoritm de desenare pentru canvas.
     * @param object Instanta canvas-ului pe care dorim sa o desenam.
     */
    void draw(Canvas object);
}
