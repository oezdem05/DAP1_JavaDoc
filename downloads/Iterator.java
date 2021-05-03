/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der <span style="color:orange">abstrakten</span> Klasse ListIterator</h2>
 *  <p>
 *      Gibt Methodensignaturen vor, die jeder Iterator konkret implementieren muss.
 *  </p>
 * </center>
 */
public abstract class Iterator
{
    /**
     * <p>
     *  Liefert true, falls es noch ein Element gibt, dessen Inhalt nicht &uuml;ber die 'next()'-Methode zur&uuml;ckgegeben wurde,<br />
     *  sonst liefert die Methode false.
     * </p>
     */
    public abstract boolean hasNext();
    
    /**
     * <p>
     *  Liefert den Inhalt des n&auml;chsten Elements, &uuml;ber das iteriert werden soll
     * </p>
     */
    public abstract Object next();
}
