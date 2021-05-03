/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse ReverseIterator</h2>
 *  <p>
 *      - Die Methode 'reverseIterator()' gibt ein Objekt der Klasse 'ReverseIterator' zurück, dessen Attribut 'current' auf das letzte Element der Liste verweist. (-> Innerhalb von 'DoublyLinkedList' kann auf das private Attribut 'last' zugegriffen werden)<br />      
 *  </p>
 * </center>
 */
public class ReverseIterator extends ListIterator
{
    /**
     * <h1>Konstruktor - ReverseIterator( Element elem )</h1>
     * <p>
     *  Erstellt ein neues 'ReverseIterator'-Objekt, indem der Konstruktor der Oberklasse ('ListIterator') aufgerufen wird.<br />
     *  Das Argument bestimmt den Startpunkt der Iteration und wird von der Aufrufenden Klasse ('DoublyLinkedList') als erstes Element ('last') der Liste vorgegeben (durch den Aufruf der 'reverseIterator()'-Methode).
     * </p>
     */
    public ReverseIterator( Element elem )
    {
        super( elem );//Ruft den Konstruktor der Oberklasse ('ListIterator') auf.
    }

    /**
     * <h1>Implementierung - Element step()</h1>
     * <p>
     *  Das n&auml;chste Element des 'ReverseIterator's ist der Vorg&auml;nger.
     * </p>
     */
    protected Element step()
    {
        return current.getPred();
    }

}
