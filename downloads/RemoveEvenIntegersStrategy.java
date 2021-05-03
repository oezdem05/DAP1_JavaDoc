/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse RemoveEvenIntegersStrategy</h2>
 *   <p>
 *      Werte von 'Integer'-Objekten, die gerade sind (also restlos durch 2 Teilbar), liefert true zur&uuml;ck.<br />
 *      Im Zusammenwirken mit der Methode 'deleteSelected()' aus der Klasse 'DoublyLinkedList', werden genau diese Elemente, aus der Liste gel&ouml;scht.
 *  </p>
 * </center>
 */
public class RemoveEvenIntegersStrategy
extends DoublyLinkedList.DeletionStrategy
{
    /**
     * <p>
     *  Die Methode liefert für alle 'Element'-Objekte in einer Liste von 'Integer'-Objekten Liste 'true', wenn der Inhalt (das 'content'-Attribut der Klasse 'Element' referenziert auf ein Objekt vom Typ 'Object')
     *  eine gerade Zahl ist.<br />
     *  Ist der Inhalt eine ungerade Zahl, wird 'false' zur&uuml;ckgegeben
     * </p>
     * <p>
     *  Im Zusammenwirken mit der Methode 'deleteSelected()' aus der Klasse 'DoublyLinkedList', werden so genau die Elemente, die 'true' liefern (also gerade sind), aus der Liste gel&ouml;scht.
     * </p>
     */
    public boolean select( Object ref )
    {
        return (int)ref % 2 == 0;//liefert true, wenn der Wert des Elementes einen geraden Wert hat
    }
}