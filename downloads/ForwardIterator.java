/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse ForwardIterator</h2>
 *  <p>
 *      - Es wird ein Attribut 'current' als Referenz auf die Klasse 'Element' deklariert, in dem die Position f&uuml;r einen Durchlauf gemerkt wird<br />
 *      - Es wird ein Konstruktor deklariert, der die Referenz 'current' initialisiert.<br />
 *      - Es wird eine Methode 'next()' deklariert, die anzeigt, ob es ein weiteres, noch nicht besuchtes Element gibt.
 *  </p>
 * </center>
 */
public class ForwardIterator extends ListIterator
{
    /**
     * <h1>Konstruktor - ForwardIterator( Element elem )</h1>
     * <p>
     *  Erstellt ein neues 'ForwardIterator'-Objekt, indem der Konstruktor der Oberklasse ('ListIterator') aufgerufen wird.<br />
     *  Das Argument bestimmt den Startpunkt der Iteration und wird von der Aufrufenden Klasse ('DoublyLinkedList') als erstes Element ('first') der Liste vorgegeben.
     * </p>
     */
    public ForwardIterator( Element elem )
    {
        super( elem );//Ruft den Konstruktor der Oberklasse ('ListIterator') auf.
    }

    /**
     * <h1>Implementierung - Element step()</h1>
     * <p>
     *  Das n&auml;chste Element des 'ForwardIterator's ist der Nachfolger.
     * </p>
     */
    protected Element step()
    {
        return current.getSucc();
    }

}
