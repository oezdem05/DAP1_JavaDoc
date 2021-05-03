/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse Element</h2>
 *  <p>
 *      Elemente enthalten beliebige Objekte der Klasse Object. Ein Element Objekt kennt immer seinen Nachfolger und seinen Vorg�nger.
 *  </p>
 * </center>
 */
public class Element
{
    private Object content;//Die Contents sind beliebige Inhalte: Object ist die Oberklasse aller Klassen
    private Element pred, succ;//Verkettung der Elemente

    /**
     * <h1>Konstruktor -> Element (Object c)</h1>
     * <p>
     *  Erstellt ein neues Element Objekt, dass den Inhalt eines Objects in dem Attribut content speichert.<br />
     *  Das einzelne Element ist noch nicht mit der Liste Verbunden.<br />
     *      -> Das einf&uuml;gen der Elemente in die 'DoublyLinkedList' �bernehmen Methoden der Klasse 'DoublyLinkedList'<br />
     *          => add(), addFirst(), connectAsSucc(), connectAsPred() etc.
     * </p>
     */
    public Element( Object c )
    {
        content = c;
        pred = succ = null;
    }

    /**
     * <h1>getContent()</h1>
     * <p>
     *  Gibt das Objekt, auf das das Attribut 'content' referenziert zur�ck.
     * </p>
     */
    public Object getContent()
    {
        return content;
    }

    /**
     * <h1>setContent( Object c )</h1>
     * <p>
     *  Setzt die Referenz des 'contents'
     * </p>
     */
    public void setContent( Object c )
    {
        content = c;
    }

    //Methoden, die sich mit dem Nachfolger besch�ftigen sich mit ihrem Nachfolger
    /**
     * <h1>hasSucc()</h1>
     * <p>
     *  Gibt true zur�ck, falls es einen Nachfolger gibt.<br />
     *  Gibt es keinen Nachfolger, wird false zur�ckgegeben. <br />
     *      -> Pr&uuml;ft, ob ein Nachfolger existiert
     * </p>
     */
    public boolean hasSucc()
    {
        return succ != null;
    }

    /**
     * <h1>getSucc()</h1>
     * <p>
     *  Gibt den Nachfolger zur�ck.<br />
     *      -> Greift auf den Nachfolger zu.
     * </p>
     */
    public Element getSucc()
    {
        return succ;
    }

    /**
     * <h1>disconnectSucc()</h1>
     * <p>
     *  Trennt den Nachfolger von dem aktuellen Element ab.<br />
     *  Der Nachfolger bleibt bestehen, weil die 'pred' Referenz des Nachfolgers vom Nachfolger ja noch auf den Nachfolger des aktuellen Elementes zeigt.<br />
     *  -> Diese Methode stellt die Invariante sicher.<br />
     *  => Diese Methode trennt 2 Elemete sauber voneinander ab.
     * </p>
     */
    public void disconnectSucc()
    {
        if ( hasSucc() ) 
        {
            succ.pred = null;//gehe zum Nachfolger und setze seinen Vorg�nger auf null
            succ = null;//setze den Nachfolger auf null
        }
    }

    /**
     * <h1>connectAsSucc( Element e)</h1>
     * <p>
     *   Das als Argument �bergebene Element e wird als Nachfolger des Element-Objektes gesetzt, dass diese Methode aufruft.<br />
     *   Nach dieser Methode ist das Element Objekt, dass diese Methode aufgerufen hat, der Vorg�nger von e, sofern e nicht leer ist.<br />
     *   Wenn e leer ist, verweist die Nachfolge Referenz (succ) des aktuellen (aufrufenden) Elements auf null.
     * </p>
     */
    public void connectAsSucc( Element e)
    {
        disconnectSucc();//Das Element kann mit einem anderen Nachfolger verkn�pft sein, dann wird das Element von seinem Nachfolger befreit werden -> So wird Platz f�r das als Argument �bergebene 'Element' e als neuen Nachfolger geschaffen
        succ = e;//e wird in der Variablen succ gespeichert. Dadurch ist e der neue Nachfolger | Alle Elemente die zwischen dem Element, dass diese Methode ausgef�hrt hat und dem Element, dass eingef�gt wurde, haben wir vergessen (, weil es keine Referenzen mehr auf diese Objekte gibt -> Der GarbageCollector hat die gel�scht) (-> Die Elemente sind dann gel�scht)
        //=> Veranschaulichung des Vergessens von Elementen, die dazwischen liegen: https://www.clanneko.tk/Files/hotlink-ok/Asuna_2cb205f3-ddf1-4be4-a851-9209c846308a_1ux_21-02-2021_21-59.png
        if ( e != null ) //Wenn das als Argument �bergebene 'Element' nicht null ist | Ein leeres Element ist ein nicht existierendes Element. Es kann aber Elemente geben deren Inhalt (Also content) leer (also == null) ist.
        {
            e.disconnectPred();//Das als Argument �bergebene 'Element' ('e') kann einen Vorg�nger haben, daher wird 'e' von seinem vorg�nger getrennt und
            e.pred = this;//e ist der neue Nachfolger von dem Element, dass diese Methode aufgerufen hat. (=> Das Element, dass diese Methode aufgerufen hat, ist der Vorg�nger von e)
            //Das als Argument �bergebene 'Element' ('e') wurde als Nachfolger hinzugef�gt
            //Die Invariante wurde eingehalten
        }
    }

    //Analoge Methoden, die sich mit dem Vorg�nger besch�ftigen
    
    /**
     * <h1>hasPred()</h1>
     * <p>
     *  Gibt true zur�ck, wenn es einen Vorg�nger gibt<br />
     *  Gibt false zur�ck, wenn es keinen Vorg�nger gibt.
     * </p>
     */
    public boolean hasPred()
    {
        return pred != null;
    }

    /**
     * <h1>
     *  getPred()
     * </h1>
     * <p>
     *  Gibt den Vorg�nger zur�ck
     * </p>
     */
    public Element getPred()
    {
        return pred;
    }

    /**
     * <h1>disconnectPred()</h1>
     * <p>
     *  Trennt den Vorg�nger vom Aktuelllen Element ab.<br />
     *  Der Vorg�nger vom Aktuellen Element wird dann vom Garbagecollector mitgenommen und gel�scht.
     * </p>
     */
    public void disconnectPred()
    {
        if ( hasPred() )//Wenn es einen Vorg�nger gibt
        {
            pred.succ = null;//Der Nachfolger des Vorg�ngers des aktuellen Elements: 
            //Also insgesammt die Referenz auf das aktuelle Element auf null gesetzt.
            //Das aktuelle Element bleibt bestehen. Das aktuelle Element zeigt noch auf seinen Vorg�nger (Aber der vorg�nger zeigt nicht mehr auf das aktuelle Element)
            pred = null;//Der Vorg�nger des aktuellen Elementes wird auf null gesetzt. Jetzt gibt es keine Referenzvariable mehr auf den Vorg�nger und seit z. 66 auch keine mehr auf das aktuelle Element.
            //Somit wird der vorg�nger des aktuellen Elements vom Garbage collector eingesammelt und das aktuelle Element hat keinen vorg�nger mehr.
            //Daraus folgt: Der Vorg�nger wurde gel�scht

        }
    }

    /**
     * <h1>connectAsPred( Element e)</h1>
     * <p>
     *   Das als Argument �bergebene Element e wird als Vorg�nger des Element-Objektes gesetzt, dass diese Methode aufruft.<br />
     *   Nach dieser Methode ist das Element Objekt, dass diese Methode aufgerufen hat, der Nachfolger von e, sofern e nicht leer ist.<br />
     *   Wenn e leer ist, verweist die Nachfolge Referenz (pred) des aktuellen (aufrufenden) Elements auf null.
     * </p>
     */
    public void connectAsPred( Element e )
    {
        disconnectPred();
        pred = e;
        if ( e != null )
        {
            e.disconnectSucc();
            e.succ = this;
        }
    }
}
