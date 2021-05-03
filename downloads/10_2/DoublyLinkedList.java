/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse DoublyLinkedList</h2>
 *  <p>
 *      Die Klasse verwaltet den Anfang und das Ender der Liste. Au&szlig;erdem wei&szlig; sie, wie viele Elemente die Liste enth&auml;lt
 *  </p>
 * </center>
 */
public class DoublyLinkedList
{
    private Element first, last;//Anfangs- und Endelement
    private int size;//Anzahl der Elemente -> Das Attribut size muss beim Hinzuf�gen von Elementen zu der Liste oder beim L�schen von Elementen von der Liste korrigiert werden.
    // => Dann liefert die Methode 'size()' die Anzahl der Elemente

    /**
     * <h1>Konstruktor -> DoublyLinkedList</h1>
     * <p>
     *  Der Konstruktor erzeugt eine leere Liste
     * </p>
     */
    public DoublyLinkedList()
    {
        first = last = null;
        size = 0;
    }

    /**
     * <h1>size()</h1>
     * <p>
     *  Methode size() gibt die gr��e der Liste zur�ck.<br />
     *  Ist die Liste leer, wird 0 zur�ckgegeben.
     * </p>
     */
    public int size()
    {
        return size;
    }

    /**
     * <h1>isEmpty()</h1>
     * <p>
     *  isEmpty() liefert einen Wahrheitswert.<br />
     *  Ist die Liste leer, so wird true zur�ckgegeben.<br />
     *  Ist die Liste nicht leer, wird false zur�ckgegeben.
     * </p>
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * <h1>add( Object content )</h1>
     * <p>
     *  Erg&auml;nzt ein 'Element'-Objekt am <em>Ende</em> der Liste, dessen Inhalt ('content'-Attribut der 'Element'-Klasse) das als Argument �bergebene Objekt wird. 
     * </p>
     */
    public void add( Object content ) //wir �bergeben den Inhalt den wir einf�gen wollen (content)
    {
        Element e = new Element( content );//Erstelle ein neues 'Element'-Objekt, dass das als Argument �bergebene Objekt ('content') als 'content' enth�lt. | Das Element 'e' steht zu Beginn erstmal alleine, da wir es �ber den Konstruktor erzeugt haben
        if ( isEmpty() ) //wenn die Liste leer ist, erg�nzen wir das einzige Element
        {
            first = last = e; //Das einzige Element ist gleichzeitig das erste und letzte Element in der Liste 
        }
        else //Wenn die Liste nicht leer ist,
        {
            last.connectAsSucc( e );//wird das als Argument �bergebene Element als Nachfolger des letzten Elements in der Liste eingef�gt. -> Verkn�pft succ von last mit 'e' & pred von 'e' mit last
            last = e;//Last wird korrigiert: Das letzte Element ist nun das als Argument �bergebene Element ('e')
        }
        size++;//korrigiere das Attribut size, da ein neues Element eingef�gt wurde
    }

    /**
     * <h1>addFirst( Object content )</h1>
     * <p>
     *  Erg�nzt ein Element am Anfang der Liste, dessen inhalt das als Argument �bergebene Objekt wird.
     * </p>
     * <p>
     *  Diese Methode ist Analog zu der add() Methode. <br />
     *  Der einzige Unterschied ist, dass das als Argument &uuml;bergebene Objekt in einer content Variablen eines neuen 'Element'-Objektes ganz am Anfang eingef&uuml;gt wird
     * </p>
     */
    public void addFirst( Object content ) 
    {
        Element e = new Element( content );//Erstellt ein neues Element mit dem Inhalt, des als Argument �bergebenen Objekts.
        if ( isEmpty() ) //Wenn die Liste leer ist,
        {
            first = last = e;//Wird das einzige und letzte Element 'e' hinzugef�gt.
        }
        else //Wenn die Liste nicht leer ist,
        {
            first.connectAsPred( e );//wird 'e' vor dem ersten Element eingef�gt
            first = e;//'e' ist das neue erste Element.
        }
        size++;
    }

    /**
     * <h1>getFirst()</h1>
     * <p>
     *  Wenn die Liste nicht leer ist, wird das erste Element der Liste zur�ckgegeben.<br />
     *  Sonst wird eine Ausnahme vom Typ 'IllegalStateException' geworfen, da eine leere Liste kein Objekt an erster Stelle enth�lt.
     * </p>
     * <p>
     *  - Die Methode 'getFirst()' gibt eine Referenz auf den inhalt des ersten Elements in der Liste zur&uuml;ck, falls die Liste mindestens ein Element enth&auml;lt.<br />
     *  - Die Liste wird dabei nicht ver�ndert<br />
     *      -> Wir schauen uns an, was vorne in der Liste steht.
     * </p>
     */
    public Object getFirst() 
    {
        if ( !isEmpty() )
        {
            return first.getContent();
        }
        else
        {
            throw new RuntimeException();
        }
    }

    /**
     * <h1>get(int index)</h1>
     * <p>
     *  Wenn der als Argument �bergebene Index ein g�ltiger Index ist (also ein Element an diesem Index in der Liste existiert),<br />
     *  wird dieses Element zur�ckgegeben, indem die Liste vom Anfang, bis zum gew�nschten Index durchlaufen wird.<br />
     *  Wird ein Index au�erhalb der Liste �bergeben, wird eine Ausnahme vom Typ 'IllegalStateException' geworfen.
     * </p>
     */
    public Object get( int index ) 
    {
        if ( index >= 0 && index < size )//Wenn die Bedingung 'true' liefert, liegt der Index im g�ltigen Bereich.
        {
            Element current = first;//Wir starten beim ersten Element der Liste
            //In der Schleife gehen wir so lange ein Element weiter, bis wir den gew�nschten Index (also den, der als Argument �bergeben wurde) erreicht haben
            for ( int i = 0; i < index; i++ )
            {
                current = current.getSucc();
            }
            return current.getContent();//Der Inhalt, der in der Variablen 'content', des 'Element'-Objektes an der als Argument �bergeben Stelle wird zur�ckgegeben
        }
        else
        {
            throw new RuntimeException();
        }
    }

    /**
     * <h1>removeFirst()</h1>
     * <p>
     *  Wenn die Liste nicht leer ist, gibt die Methode eine Referenz auf den Inhalt ('content') des ersten Elements zur&uuml;ck und l&ouml;scht das Objekt aus der Liste<br />
     *  Ist die Liste leer und die Methode wird dennoch aufgerufen, wird eine Ausnahme vom Typ 'IllagalStateException' geworfen.
     * </p>
     */
    public Object removeFirst()
    {
        if ( !isEmpty() ) //Wenn die Liste nicht leer ist,
        {
            Object result = first.getContent();//Merke dir den Inhalt des ersten Elements in der Hilfsvariable 'result'

            //Entfernen des ersten 'Element'-Objektes aus der Liste
            if ( first.hasSucc() )//Wenn das erste Element einen Nachfolger hat (also nicht das einzige zu entfernende Element ist)
            {
                first = first.getSucc();//Die Referenz 'first' zeigt nun auf das zweite 'Element'-Objekt in der Liste
                first.disconnectPred();//Trenne den Vorg�nger (der Vorg�nger ist das "alte" erste 'Element'-Objekt) des neuen ersten Elements
                // -> Das "alte" erste 'Element'-Objekt ist nun nicht mehr �ber die Liste erreichbar und damit aus der Liste gel�scht
                // => Wir haben aber noch eine Referenz auf dieses Objekt ('result')
            }
            else//Wenn es nur ein Element in der Liste gab (-> Das Erste Element hat keinen Nachfolger => Es ist das erste und letzte Element in der Liste)
            {
                first = last = null;//Setzte die Referenzvariable des ersten und letzten Elements auf 'null'
            }
            size--;//Korrigiere das Attribut size -> size muss dekrementiert werden, da ein Element entfernt worden ist.
            return result;//Gib das "alte" erste 'Element'-Objekt (, dass nun nicht mehr �ber die Liste erreichbar ist), mithilfe der Hilfsvariablen 'result' zur�ck
        }
        else
        {
            throw new RuntimeException();
        }
    }

    /**
     * <h1>showAll()</h1>
     * <p>
     *  Gibt die Inhalte ('content') aller Elemente der Liste aus.
     * </p>
     */
    public void showAll()
    {
        Element current = first;//Gehe zum ersten 'Element'-Objekt der Liste
        while ( current != null )//Solange die Liste nicht vollst�ndig durchlaufen wurde, 
        {
            System.out.print( current.getContent() );  // impliziter Aufruf von toString, falls != null -> 'toString()' ist f�r jedes Objekt verf�gbar => Die Methode 'toString()' ist f�r Object definiert.
            // Alternativ: System.out.print( current.getContent().toString() );  => Wenn ein Objekt "gedruckt" wird, wird immer die 'toString()' Methode des Objekt aufgferufen und der R�ckgabewert (vom Typ String) wird gedruckt.
            if ( current != last )//Wenn current nicht auf das letzte 'Element'-Objekt referenziert,
            //(=> F�r das letzte 'Element'-Objekt wird der Inhalt des if-Blocks nicht ausgef�hrt)
            {
                System.out.print(", ");//Trenne die Ausgaben der Inhalte ('content') der Elemente durch ein Komma ab.
            }
            current = current.getSucc();//Gehe zum Nachfolger des aktuellen 'Element'-Objektes
        }
        System.out.println();
    }

    //Erg�nzungen aus Kapitel 10. Entwurfsmuster - Iterator
    /**
     * <h1>ForwardIterator iterator()</h1>
     * <p>
     *  Ruft den Konstruktor der Klasse ForwardIterator auf.<br />
     * </p>
     * <p>
     *  => Es wird ein Objekt der Klasse 'ForwardIterator' zur&uuml;ckgegeben, dessen Attribut 'current' (also 'current' des 'ForwardIterator'-Objekts) auf das Erste 'Element' der Liste verweist<br />
     * </p>
     */
    public ForwardIterator iterator()
    {
        return new ForwardIterator();//Durchlaufen der Liste beginnt bei 'first'
    }

    /**
     * <h1>ReverseIterator reverseIterator()</h1>
     * <p>
     *  Ruft den Konstruktor der Klasse ReverseIterator auf.<br />
     * </p>
     * <p>
     *  => Es wird ein Objekt der Klasse 'ReverseIterator' zur&uuml;ckgegeben, dessen Attribut 'current' (also 'current' des 'ReverseIterator'-Objekts) auf das Letzte 'Element' der Liste verweist<br />
     * </p>
     */
    public ReverseIterator reverseIterator()
    {
        return new ReverseIterator();//Durchlaufen der Liste beginnt bei 'last'
    }

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
     *  <p>
     *      - Die Klasse 'ListIterator' wird au�erhalb der Klasse 'DoublyLinkedList' nicht ben&ouml;tigt, da der Zugriff auf die Iteratoren ausschlie&szlig;lich &uuml;ber Referenzen auf die abstrakte
     *      Klasse 'Iterator' erfolgen. Daher kann die Klasse 'ListIterator' mit dem Zugriffsrecht <b>private</b> versehen werden
     *  </p>
     * </center>
     */
    private abstract class ListIterator extends Iterator//'ListIterator' ist Typkompatibel zu Iterator
    {
        Element current; //Referenz, die sich die aktuelle Position merkt, also das Element, bei dem der Iterator aktuell ist

        //Der Konstruktor F�llt im vergleich zur alten Version (https://www.clanneko.tk/Files/hotlink-ok/Asuna_32e9a2fd-e23e-4db3-a3ee-c93a8f0275bd_1ve_24-02-2021_16-44.png), wie beim Java Iterator weg.

        /**
         * <h1>boolean hasNext()</h1>
         * <p>
         *  Liefert true, wenn es noch ein Objekt in der Liste gibt, dessen Inhalt noch nicht &uuml;ber die 'next()'-Methode zur&uuml;ckgegeben wurde.<br />
         *  Wurde die Liste bereits vollst&auml;ndig durchlaufen, liefert der Aufruf von hasNext() false zur&uuml;ck
         * </p>
         */
        public boolean hasNext()
        {
            return current != null;//current verweist auf das Objekt, das vom n�chsten Aufruf von 'next()' geliefert wird
        }

        /**
         * <h1>Object next()</h1>
         * <p>
         *  - Merkt sich das aktuelle 'Element'-Objekt in dem Attribut 'current'<br />
         *  - Setzt 'current' ein 'Element' in der Liste weiter<br />
         *  - Gibt den Inhalt der Hilfsvariablen ('content') zur�ck
         * </p>
         * <p>
         *  <b>Beachte:</b> Die next()-Methode setzt die Referenz von dem 'Element', das ausgegeben wurde auf das N&auml;chste 'Element'-Objekt der Liste.<br />
         *      -> Soll ein Inhalt eines 'Element'-Objektes mehrfach verwendet werden, so muss man sich die R&uuml;ckgabe in einer Hilfsvariablen zwischenspeichern.
         * </p>
         */
        public Object next()//Der R�ckgabetyp ist 'Object', da der Inhalt des 'content'-Attributes der Klasse Element vom Typ 'Object' ist.
        {
            if ( hasNext() )
            {
                Object content = current.getContent();//'content' ist eine Referenz auf das Attribut 'content' der Klasse Element.
                //Das 'content'-Attribut der Klasse Element, enth�lt die Inhalte der Liste
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
         *              =-> W&auml;hrend der Ausf&uuml;hrung wird dann die zum Objekt geh&ouml;rende Implementierung von 'step()' ausgef&uuml;hrt. (Also 'getSucc()' (aus der Klasse 'Element') f&uuml;r den 'ForwardIterator' und 'getPred()' (aus der Klasse 'Element') f�r den 'ReverseIterator')
         * </p>
         * <p>
         *  Signatur:<br />
         *  [zugriffstyp] Element step();<br />
         *  -> Daraus folgt: Der Zugriffstyp ist frei w&auml;hlbar, muss aber mindestens den Zugriffstyp package oder h&ouml;her haben.
         * </p>
         */
        abstract Element step();
    }

    private class ForwardIterator extends ListIterator
    {
        /**
         * <h1>Konstruktor - ForwardIterator()</h1>
         * <p>
         *  Erstellt ein neues 'ForwardIterator'-Objekt, dass zum iterieren &uuml;ber die Liste verwendet wird.<br />
         *  Der Iterator beginnt beim ersten Element der Liste ('first')
         * </p>
         */
        ForwardIterator() 
        { 
            current = first; //Die Instanzklasse (ForwardIterator) kann auf das (Instanz-)Attribut 'first' der umgebenden Klasse 'DoublyLinkedList' zugreifen
        }

        /**
         * <h1>Implementierung - Element step()</h1>
         * <p>
         *  Das n&auml;chste Element des 'ForwardIterator's ist der Nachfolger ('getSucc()').
         * </p>
         */
        Element step()
        {
            return current.getSucc();
        }

    }

    private class ReverseIterator extends ListIterator
    {
        /**
         * <h1>Konstruktor - ReverseIterator()</h1>
         * <p>
         *  Erstellt ein neues 'ReverseIterator'-Objekt, dass zum iterieren &uuml;ber die Liste verwendet wird<br />
         *  Der Iterator beginnt beim letzten Element der Liste ('last')
         * </p>
         */
        ReverseIterator() 
        { 
            current = last; 
        }

        /**
         * <h1>Implementierung - Element step()</h1>
         * <p>
         *  Das n&auml;chste Element des 'ReverseIterator's ist der Vorg&auml;nger ('getPred()').
         * </p>
         */
        Element step()
        {
            return current.getPred(); 
        }

    }

    // Die 'Element'-Klasse kann verborgen werden

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
    private class Element
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

}    
