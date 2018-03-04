import tools.Drawer;
import tools.Shape;
import tools.ShapeFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    protected Main() {
    }

    public static void main(final String[] args) throws IOException {
        //Fisierul de input
        File in = new File(args[0]);
        Scanner read = new Scanner(in);
        //Numarul de forme
        int shapesNumber = read.nextInt();
        read.nextLine();
        //Initializarea desenatorului
        Drawer drawer = new Drawer();
        //Salvarea formelor
        ArrayList<Shape> shapes = new ArrayList<>();
        //Intantierea formelor
        for (int i = 0; i < shapesNumber; i++) {
            String line = read.nextLine();
            shapes.add(ShapeFactory.getInstance().getShape(line));
        }
        //Desenarea formelor
        for (Shape shape : shapes) {
            shape.accept(drawer);
        }
        //Fisier de output
        File f = new File("drawing.png");
        ImageIO.write(drawer.getImage(), "PNG", f);
    }
}
