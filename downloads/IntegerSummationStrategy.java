/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse IntegerSummationStrategy</h2>
 *   <p>
 *      Dieses Objekt überlebt die Aufrufe von 'inspectAll' -> Die Attribute des Objektes bleiben also erhalten, solange das (Strategie-)Objekt existiert.
 *  </p>
 *  <p>
 *      <em>Hinweis:</em> Das 'sum'-Attribut wird nie auf 0 zur&uuml;ckgesetzt -> Mehrfaches Benutzen eines 'IntegerSummationStrategy'-Objekts f&uuml;hrt zu fehlerhaften Ergebnissen.<br />
 *      -> Die Objekte werden nur einmal verwendet.<br />
 *      => Wenn ich nochmal z&auml;hlen m&ouml;chte, benötige ich ein neues Objekt
 *  </p>
 * </center>
 */
public class IntegerSummationStrategy 
extends DoublyLinkedList.InspectionStrategy
{
    private int sum;//Attribut 'sum' speichert die Zwischenergebnisse ab.
    
    public IntegerSummationStrategy() { sum = 0; }
    
    /**
     * <p>
     *  Inkrementiert den Wert von 'sum' um den Wert, den das als Argument erhaltene Objekt enth&auml;lt
     * </p>
     */
    public void inspect( Object ref )
    {
        sum += (int)ref;//Berechnet die Summe aller Werte.
    }
    
    public int getSum()
    {
        return sum;//Abruf 
    }
}