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
 *      Implementiert das grundprinzip der Iteratoren ('ForwardIterator' und 'ReverseIterator'), da sich bei beiden Iteratoren ausschlie&azlig;lich die 'step()'-Methode unterscheidet.<br />
 *      Die 'step()'-Methode ist daher eine Abstrakte Methode, die jeweils in der Klasse 'ForwardIterator' oder 'ReverseIterator' konkret implementiert wird.
 *  </p>
 * </center>
 */
public abstract class ListIterator
{
    Element current; //Referenz, die sich die aktuelle Position merkt, also das Element, bei dem der Iterator aktuell ist

    /**
     * <h1>Konstruktor - Erstellt ein neues 'ListIterator'-Objekt</h1>
     * <p>
     *  Der ListIterator beginnt bei 'elem' ('elem' ist eine Referenz auf ein Objekt der Kalsse'Element') zu iterieren.
     * </p>
     */
    ListIterator( Element elem )//die Referenz ('elem') auf das Anfangsobjekt wird als Argument übergeben
    {
        current = elem;//die Aktuelle Position ist die Referenz auf das Argument übergebene Objekt
    }

    /**
     * <h1>boolean hasNext()</h1>
     * <p>
     *  Liefert true, wenn es noch ein Objekt in der Liste gibt, dessen Inhalt noch nicht &uuml;ber die 'next()'-Methode zur&uuml;ckgegeben wurde.<br />
     *  Wurde die Liste bereits vollst&auml;ndig durchlaufen, liefert der Aufruf von hasNext() false zur&uuml;ck
     * </p>
     */
    public boolean hasNext()
    {
        return current != null;//current verweist auf das Objekt, das vom nächsten Aufruf von 'next()' geliefert wird
    }
    
    /**
     * <h1>Object next()</h1>
     * <p>
     *  - Merkt sich das aktuelle 'Element'-Objekt in dem Attribut 'current'<br />
     *  - Setzt 'current' ein 'Element' in der Liste weiter<br />
     *  - Gibt den Inhalt der Hilfsvariablen ('content') zurück
     * </p>
     * <p>
     *  <b>Beachte:</b> Die next()-Methode setzt die Referenz von dem 'Element', das ausgegeben wurde auf das N&auml;chste 'Element'-Objekt der Liste.<br />
     *      -> Soll ein Inhalt eines 'Element'-Objektes mehrfach verwendet werden, so muss man sich die R&uuml;ckgabe in einer Hilfsvariablen zwischenspeichern.
     * </p>
     */
    public Object next()//Der Rückgabetyp ist 'Object', da der Inhalt des 'content'-Attributes der Klasse Element vom Typ 'Object' ist.
    {
        if ( hasNext() )
        {
            Object content = current.getContent();//'content' ist eine Referenz auf das Attribut 'content' der Klasse Element.
                                                  //Das 'content'-Attribut der Klasse Element, enthält die Inhalte der Liste
            current = step();//step() ist eine Abtrakte Methode, die 'current' auf das nachfolgende Element (beim ForwardIterator) ein Element weiter setzt.
                             //step() ist sinnvoll, da sich der 'ForwardIterator' und der 'ReverseIterator' nur in diesem Punkt unterscheiden und wir uns so das anlegen der 'next()' Methode
                             //in den Unterklassen sparen
            return content;
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    /**
     * <h1>Abstrakte Methode</h1>
     * <p>
     *  Eine Abstrakte Methode gibt die Signatur einer Methode ohne konkrete Implementierung vor.<br />
     *      -> In der Klasse 'ListIterator' ist also noch nicht festgelegt, was 'step()' tun wird<br />
     *      -> Jede Klasse die von 'ListIterator' erbt, muss die Methode konkret implementieren.<br />
     *          => Daher kann die Methode 'step()' bereits in der Klasse 'ListIterator' verwendet werden.<br />
     *              =-> W&auml;hrend der Ausf&uuml;hrung wird dann die zum Objekt geh&ouml;rende Implementierung von 'step()' ausgef&uuml;hrt. (Also 'getSucc()' (aus der Klasse 'Element') f&uuml;r den 'ForwardIterator' und 'getPred()' (aus der Klasse 'Element') für den 'ReverseIterator')
     * </p>
     * <p>
     *  Signatur:<br />
     *  [zugriffstyp] Element step();<br />
     *  -> Daraus folgt: Der Zugriffstyp ist frei w&auml;hlbar, muss aber mindestens den Zugriffstyp package oder h&ouml;her haben.
     * </p>
     */
    abstract Element step();
}