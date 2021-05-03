/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der generischen Klasse Stack<T></h2>
 *  <p>
 *      Diese Klasse implementiert die Datenstruktor 'Stack' nach dem 'lifo'-Prinzip (last in first out)
 *  </p>
 * </center>
 */
public class Stack<T>
{
    private DoublyLinkedList<T> elements;//Die Inhalte werden in einer Liste gespeichert (Der Stapel delegiert das Speichern der Objekte an ein Objekt der Klasse 'DoublyLinkedList')

    /**
     * <p>
     *  Der Konstruktor erzeugt einen neuen Stapel.<br />
     *  Da der Stapel die Speicherunug der Inhalte an ein Objekt der Klasse 'DoublyLinkedList' delegiert, kann der Stapel undendlich viele Inhalte aufnehmen.
     * </p>
     */
    public Stack() {
        elements = new DoublyLinkedList<T>();
    }

    /**
     * <p>
     *  Das als Argument &uuml;bergebene Objekt vom Typ 'T' wird vorne an die 'DoublyLinkedList' eingef&uuml;gt.
     * </p>
     */
    public void push( T o ) {
        elements.addFirst( o );
    }

    /**
     * <p>
     *  Das Objekt an der ersten Position (index 0) in der 'DoublyLinkedList' wird aus der 'DoublyLinkedList' entfernt
     * </p>
     */
    public T pop() {
        return elements.removeFirst();
    }

    /**
     * <p>
     *  Der Inhalt ('content' aus der Klasse 'Element') des ersten Objektes (Index 0), wird zur&uuml;ckgegeben.<br />
     *  Beachte, dass der Inhalt nur inspiziert, also nicht aus der 'DoublyLinkedList' entfernt wird.
     * </p>
     */
    public T peek() {
        return elements.getFirst();
    }

    /**
     * <p>
     *  Prüft, ob noch Elemente auf dem Stapel liegen.<br />
     *  Ist die Liste leer, so wird true zur&uuml;ckgegeben.
     *  Ist die Liste nicht leer, wird false zur&uuml;ckgegeben. 
     * </p>
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

}