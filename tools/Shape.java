package tools;

public interface Shape {
    /**
     * Metoda accept din cadrul Visitor Pattern ce va oferi spre desenare forma curenta.
     * @param visitor Instanta ce doreste sa foloseasca forma curenta.
     */
    void accept(Visitor visitor);
}
