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
     *  Ruft den Konstruktor der Klasse ForwardIterator auf und &uuml;bergibt ihr als Argument die Referenz auf das Erste 'Element'-Objekt der Liste.<br />
     *      -> Dies ist notwendig, da die Referenz 'first' als privates Attribut der Klasse 'DoublyLinkedList' deklariert und initialisiert ist.
     * </p>
     * <p>
     *  => Es wird ein Objekt der Klasse 'ForwardIterator' zur&uuml;ckgegeben, dessen Attribut 'current' (also 'current' des 'ForwardIterator'-Objekts) auf das Erste 'Element' der Liste verweist<br />
     *      (=-> Innerhalb der Klasse 'DoublyLinkedList', kann auf das private Attribut 'first' der Klasse 'DoublyLinkedList' zugegriffen werden (au&szlig;erhalb der Klasse 'DoublyLinkedList', ist das Attribut 'first' also "unsichtbar"))
     * </p>
     */
    public ForwardIterator iterator()
    {
        return new ForwardIterator( first );//Durchlaufen der Liste beginnt bei 'first'
    }

    /**
     * <h1>ReverseIterator reverseIterator()</h1>
     * <p>
     *  Ruft den Konstruktor der Klasse ReverseIterator auf und &uuml;bergibt ihr als Argument die Referenz auf das Letzte 'Element'-Objekt der Liste.<br />
     *      -> Dies ist notwendig, da die Referenz 'last' als privates Attribut der Klasse 'DoublyLinkedList' deklariert und initialisiert ist.
     * </p>
     * <p>
     *  => Es wird ein Objekt der Klasse 'ReverseIterator' zur&uuml;ckgegeben, dessen Attribut 'current' (also 'current' des 'ReverseIterator'-Objekts) auf das Letzte 'Element' der Liste verweist<br />
     *      (=-> Innerhalb der Klasse 'DoublyLinkedList', kann auf das private Attribut 'last' der Klasse 'DoublyLinkedList' zugegriffen werden (au&szlig;erhalb der Klasse 'DoublyLinkedList', ist das Attribut 'last' also "unsichtbar"))
     * </p>
     */
    public ReverseIterator reverseIterator()
    {
        return new ReverseIterator( last );//Durchlaufen der Liste beginnt bei 'last'
    }
}
