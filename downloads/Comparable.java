/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der <span style="color:orange">abstrakten</span> generischen Klasse Comparable<T></h2>
 *  <p>
 *      Die Klasse verwaltet den Anfang und das Ender der Liste. Au&szlig;erdem wei&szlig; sie, wie viele Elemente die Liste enth&auml;lt
 *  </p>
 * </center>
 */
public abstract class Comparable<T>
{
    /**
     * <p>
     *  Beispielauf: content.compareTo( t )
     * </p>
     * <p>
     *  Der R&uuml;ckgabewert soll folgenderma&szligen bestimmt werden:<br />
     *  - falls content gr&ouml;&szlig;er ist als t -> Die R&uuml;ckgabe ist &gt; 0<br />
     *  - falls content kleiner ist als t -> Die R&uuml;ckgabe ist &lt; 0<br />
     *  - falls content gleich t ist -> Die R&uuml;ckgabe ist = 0
     * </p>
     * <h2>Anmerkung</h2>
     * <p>
     *  - Es muss immer gelten: content.compareTo( t ) == -(t.compareTo( content ))<br />
     *  - Es sollte immer gelten: content.compareTo( t ) == 0 => content.equals( t )
     * </p>
     */
    public abstract int compareTo( T t );
}
