/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse DoublyLinkedList [12.1]</h2>
 *  <p>
 *      Die Klasse verwaltet den Anfang und das Ender der Liste. Au&szlig;erdem wei&szlig; sie, wie viele Elemente die Liste enth&auml;lt
 *  </p>
 * </center>
 */
public class DoublyLinkedList<T>
{
    private Element first, last;//Anfangs- und Endelement
    private int size;//Anzahl der Elemente -> Das Attribut size muss beim Hinzufügen von Elementen zu der Liste oder beim Löschen von Elementen von der Liste korrigiert werden.
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
     *  Methode size() gibt die größe der Liste zurück.<br />
     *  Ist die Liste leer, wird 0 zurückgegeben.
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
     *  Ist die Liste leer, so wird true zurückgegeben.<br />
     *  Ist die Liste nicht leer, wird false zurückgegeben.
     * </p>
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * <h1>add( T content )</h1>
     * <p>
     *  Erg&auml;nzt ein 'Element'-Objekt am <em>Ende</em> der Liste, dessen Inhalt ('content'-Attribut der 'Element'-Klasse) das als Argument übergebene Objekt wird. 
     * </p>
     */
    public void add( T content ) 
    {
        Element e = new Element( content );//Erstelle ein neues 'Element'-Objekt, dass das als Argument übergebene Objekt ('content') als 'content' enthält. | Das Element 'e' steht zu Beginn erstmal alleine, da wir es über den Konstruktor erzeugt haben
        if ( isEmpty() ) //wenn die Liste leer ist, ergänzen wir das einzige Element
        {
            first = last = e; //Das einzige Element ist gleichzeitig das erste und letzte Element in der Liste 
        }
        else //Wenn die Liste nicht leer ist,
        {
            last.connectAsSucc( e );//wird das als Argument übergebene Element als Nachfolger des letzten Elements in der Liste eingefügt. -> Verknüpft succ von last mit 'e' & pred von 'e' mit last
            last = e;//Last wird korrigiert: Das letzte Element ist nun das als Argument übergebene Element ('e')
        }
        size++;//korrigiere das Attribut size, da ein neues Element eingefügt wurde
    }

    /**
     * <h1>addFirst( T content )</h1>
     * <p>
     *  Ergänzt ein Element am Anfang der Liste, dessen inhalt das als Argument übergebene Objekt wird.
     * </p>
     * <p>
     *  Diese Methode ist Analog zu der add() Methode. <br />
     *  Der einzige Unterschied ist, dass das als Argument &uuml;bergebene Objekt in einer content Variablen eines neuen 'Element'-Objektes ganz am Anfang eingef&uuml;gt wird
     * </p>
     */
    public void addFirst( T content ) 
    {
        Element e = new Element( content );//Erstellt ein neues Element mit dem Inhalt, des als Argument übergebenen Objekts.
        if ( isEmpty() ) //Wenn die Liste leer ist,
        {
            first = last = e;//Wird das einzige und letzte Element 'e' hinzugefügt.
        }
        else //Wenn die Liste nicht leer ist,
        {
            first.connectAsPred( e );//wird 'e' vor dem ersten Element eingefügt
            first = e;//'e' ist das neue erste Element.
        }
        size++;
    }

    /**
     * <h1>getFirst()</h1>
     * <p>
     *  Wenn die Liste nicht leer ist, wird das erste Element der Liste zurückgegeben.<br />
     *  Sonst wird eine Ausnahme vom Typ 'IllegalStateException' geworfen, da eine leere Liste kein Objekt an erster Stelle enthält.
     * </p>
     * <p>
     *  - Die Methode 'getFirst()' gibt eine Referenz auf den inhalt des ersten Elements in der Liste zur&uuml;ck, falls die Liste mindestens ein Element enth&auml;lt.<br />
     *  - Die Liste wird dabei nicht verändert<br />
     *      -> Wir schauen uns an, was vorne in der Liste steht.
     * </p>
     */
    public T getFirst() 
    {
        if ( !isEmpty() )
        {
            return first.getContent();
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    /**
     * <h1>get(int index)</h1>
     * <p>
     *  Wenn der als Argument übergebene Index ein gültiger Index ist (also ein Element an diesem Index in der Liste existiert),<br />
     *  wird dieses Element zurückgegeben.<br />
     *  Wird ein Index außerhalb der Liste übergeben, wird eine Ausnahme vom Typ 'IllegalStateException' geworfen.
     * </p>
     */
    public T get( int index ) 
    {
        if ( index >= 0 && index < size )//Wenn die Bedingung 'true' liefert, liegt der Index im gültigen Bereich.
        {
            Element current = first;//Wir starten beim ersten Element der Liste
            //In der Schleife gehen wir so lange ein Element weiter, bis wir den gewünschten Index (also den, der als Argument übergeben wurde) erreicht haben
            for ( int i = 0; i < index; i++ )
            {
                current = current.getSucc();
            }
            return current.getContent();//Der Inhalt, der in der Variablen 'content', des 'Element'-Objektes an der als Argument übergeben Stelle wird zurückgegeben
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
    public T removeFirst()
    {
        if ( !isEmpty() ) //Wenn die Liste nicht leer ist,
        {
            T result = first.getContent();//Merke dir den Inhalt des ersten Elements in der Hilfsvariable 'result'

            //Entfernen des ersten 'Element'-Objektes aus der Liste
            if ( first.hasSucc() )//Wenn das erste Element einen Nachfolger hat (also nicht das einzige zu entfernende Element ist)
            {
                first = first.getSucc();//Die Referenz 'first' zeigt nun auf das zweite 'Element'-Objekt in der Liste
                first.disconnectPred();//Trenne den Vorgänger (der Vorgänger ist das "alte" erste 'Element'-Objekt) des neuen ersten Elements
                // -> Das "alte" erste 'Element'-Objekt ist nun nicht mehr über die Liste erreichbar und damit aus der Liste gelöscht
                // => Wir haben aber noch eine Referenz auf dieses Objekt ('result')
            }
            else//Wenn es nur ein Element in der Liste gab (-> Das Erste Element hat keinen Nachfolger => Es ist das erste und letzte Element in der Liste)
            {
                first = last = null;//Setzte die Referenzvariable des ersten und letzten Elements auf 'null'
            }
            size--;//Korrigiere das Attribut size -> size muss dekrementiert werden, da ein Element entfernt worden ist.
            return result;//Gib das "alte" erste 'Element'-Objekt (, dass nun nicht mehr über die Liste erreichbar ist), mithilfe der Hilfsvariablen 'result' zurück
        }
        else
        {
            throw new RuntimeException();
        }
    }

    /**
     * <h1>showAll()</h1>
     * <p>
     *  Druckt jedes Element in der Liste nebeneinander.
     * </p>
     */
    public void showAll()
    {
        Element current = first;//Gehe zum ersten 'Element'-Objekt der Liste
        while ( current != null )//Solange die Liste nicht vollständig durchlaufen wurde, 
        {
            System.out.print( current.getContent() );  // impliziter Aufruf von toString, falls != null -> 'toString()' ist für jedes Objekt verfügbar => Die Methode 'toString()' ist für Object definiert.
            // Alternativ: System.out.print( current.getContent().toString() );  => Wenn ein Objekt "gedruckt" wird, wird immer die 'toString()' Methode des Objekt aufgferufen und der Rückgabewert (vom Typ String) wird gedruckt.
            if ( current != last )//Wenn current nicht auf das letzte 'Element'-Objekt referenziert,
            //(=> Für das letzte 'Element'-Objekt wird der Inhalt des if-Blocks nicht ausgeführt)
            {
                System.out.print(", ");//Trenne die Ausgaben der Inhalte ('content') der Elemente durch ein Komma ab.
            }
            current = current.getSucc();//Gehe zum Nachfolger des aktuellen 'Element'-Objektes
        }
        System.out.println();
    }

    // Iterator
    /**
     * <h1>Konstruktor -> Iterator</h1>
     * <p>
     *  Ruft den Konstruktor der Klasse ForwardIterator auf.<br />
     * </p>
     * <p>
     *  => Es wird ein Objekt der Klasse 'ForwardIterator' zur&uuml;ckgegeben, dessen Attribut 'current' (also 'current' des 'ForwardIterator'-Objekts) auf das Erste 'Element' der Liste verweist<br />
     * </p>
     */
    public Iterator<T> iterator()
    {
        return new ForwardIterator();
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
     *      - Die Klasse 'ListIterator' wird außerhalb der Klasse 'DoublyLinkedList' nicht ben&ouml;tigt, da der Zugriff auf die Iteratoren ausschlie&szlig;lich &uuml;ber Referenzen auf die abstrakte
     *      Klasse 'Iterator' erfolgen. Daher kann die Klasse 'ListIterator' mit dem Zugriffsrecht <b>private</b> versehen werden
     *  </p>
     * </center>
     */
    private abstract class ListIterator extends Iterator<T>//'ListIterator' ist Typkompatibel zu Iterator
    {
        Element current; //Referenz, die sich die aktuelle Position merkt, also das Element, bei dem der Iterator aktuell ist

        //Der Konstruktor Fällt im vergleich zur alten Version (https://www.clanneko.tk/Files/hotlink-ok/Asuna_32e9a2fd-e23e-4db3-a3ee-c93a8f0275bd_1ve_24-02-2021_16-44.png), wie beim Java Iterator weg.

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
        public T next()//Der Rückgabetyp ist 'Object', da der Inhalt des 'content'-Attributes der Klasse Element vom Typ 'Object' ist.
        {
            if ( hasNext() )
            {
                T content = current.getContent();//'content' ist eine Referenz auf das Attribut 'content' der Klasse Element.
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

    // Ergänzung aus Kapitel 11. Entwurfsmuster - Strategie
    /**
     * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
     * <center>
     *  <h1>Autor</h1>
     *  <p>
     *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
     *      Modul: <em>DAP1</em>
     *  </p>
     *  <h2>Dokumentation der &ouml;ffentlichen statischen inneren Klasse SubstitutionStrategy</h2>
     *   <p>
     *      - Definiert, wie Methoden deklariert werden k&ouml;nnen, die der Klasse 'DoublyLinkedList' zur Ausf&uuml;hrung von 'void substituteAll( SubstitutionStrategy s )' 
     *      als Argument übergeben werden können.<br />
     *      - Die Methode gibt gem&auml;&szlig; der Implementierung der abstrakten Klasse (außerhalb der Klasse 'DoublyLinkedList') ein Objekt vom Typ ('<E>') an die 
     *      innere Methode 'void substituteAll( SubstitutionStrategy s )' zur&uuml;ck. Dieses Objekt wird dann in 'substituteAll( SubstitutionStrategy s )' verwendet, um
     *      das aktuelle Objekt zu ersetzen. (-> Die 'substituteAll()'-Methode gibt also vor, was wir mit den zu 'SubstitutionStrategy' kompatiblen Objekt &uuml;berhaupt tun)<br />
     *          -> Es k&ouml;nnen also &Auml;nderungen an der Liste vorgenommen werden, die in der ausf&uuml;hrung von 'substitute( E ref )' bestimmt werden.
     *  </p>
     * </center>
     */
    public static abstract class SubstitutionStrategy<E>
    {
        /**
         * <p>
         *  Jede Klasse, die 'SubstitutionStrategy' erweitert und somit zu 'SubstitutionStrategy' kompatibel ist, muss eine konkrete implementierung der
         *  Methode 'Object substitute( Object ref )' haben, die mindestens vom Zugriffstyp public ist.
         * </p>
         */
        public abstract E substitute( E ref );
    }

    /**
     * <p>
     *  - F&uuml;r alle Elemente der Liste wird nacheinander eine Implementierung der Methode substitute aufgerufen<br />
     *  -> Der Methode 'substitute( Object ref )' wird jeweils das aktuelle Element als Argument übergeben<br />
     *  => 'substitute( Object ref )' liefert ein Objekt (vom Typ 'Object') zur&uuml;ck, das den Inhalt der Liste an der Position der Referenz 'current' ersetzt.
     * </p>
     */
    public void substituteAll( SubstitutionStrategy<T> s )//Als Argument wird ein zu 'SubstitutionStrategy' kompatibles Objekt erwartet | Typargument 'T' garantiert die Kompatibilität zum ausführenden 'DoublyLinkedList'-Objekt
    {
        Element current = first;//Beginne beim ersten Element
        while ( current != null )//durchlaufe alle Elemente, bis null erreicht wurde
        {
            current.setContent( s.substitute( current.getContent() ) );//Da das Objekt 's' zu 'SubstitutionStrategy' kompatibel ist, muss 's' eine 'substitute()'-Methode haben.
            //Der Inhalt der Liste wird verändert, indem das aktuelle Objekt durch das Objekt ersetzt wird, dass von der Methode
            //'substitute( Object ref )' zurückgegeben wurde
            current = current.getSucc();//Die referenz wird vom aktuellen Element auf dessen Nachfolger geesetzt.
        }
    }

    /**
     * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
     * <center>
     *  <h1>Autor</h1>
     *  <p>
     *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
     *      Modul: <em>DAP1</em>
     *  </p>
     *  <h2>Dokumentation der &ouml;ffentlichen statischen inneren Klasse InspectionStrategy</h2>
     *   <p>
     *      Geht in der Funktionalit&auml;t auf das zur&uuml;ck, was der Iterator konnte<br />
     *      -> Sollen Inhalte betrachtet -aber nicht ersetzt werden, vermeidet eine eingeschr&auml;nkte Strategie unabsichtige ersetzungen, indem der R&uuml;ckgabetyp
     *         dieser Strategie 'void' ist
     *   </p>
     * </center>
     */
    public static abstract class InspectionStrategy<E>
    {
        /**
         * <p>
         *  - Sollen nur Inhalte betrachtet - aber nicht ersetzt - werden, vermeidtet eine eingeschränkte Strategie unbeabsichtigte Ersetzungen<br />
         *  -Die Signatur der Methode 'inspect' schränkt die Realisierung ein:<br />
         *   Der Parameter ('ref') ist eine Referenz auf 'Object', es erfolgt keine R&uuml;ckgabe ('void'),<br />
         *   der Inhalt eines Elements kann also nicht ersetzt werden.
         * </p>
         */
        public abstract void inspect( E ref );
    }

    /**
     * <p>
     *  - Die Methode 'inspectAll()' ruft eine Implementierung der Methode 'inspect' nacheinander f&uuml;r alle Elemente der Liste auf<br />
     *  - Dabei wird 'inspect' der Inhalt eines Elements als Argument &uuml;bergeben<br />
     *  - Das Ergebnis der in der Methode 'inspect' vorgenommenen Untersuchung muss die Methode (des Strategieobjekts) selbst in geeigneter Form in einem Objekt ablegen, beispielsweise in ihrem Strategie-Objekt
     * </p>
     */
    public void inspectAll( InspectionStrategy<T> s )
    {
        Element current = first;
        while ( current != null )
        {
            s.inspect( current.getContent() );
            current = current.getSucc();
        }
    }

    /**
     * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
     * <center>
     *  <h1>Autor</h1>
     *  <p>
     *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
     *      Modul: <em>DAP1</em>
     *  </p>
     *  <h2>Dokumentation der &ouml;ffentlichen statischen inneren Klasse DeletionStrategy</h2>
     *   <p>
     *      Diese Klasse soll entscheiden, ob eine &auml;nderung an der Liste vorgenommen werden soll oder nicht.
     *   </p>
     * </center>
     */
    public static abstract class DeletionStrategy<E>
    {
        /**
         * <p>
         *  Die Signatur der 'select'-Methode gibt einen <b>boolean</b>-Wert an die 'deleteSelected( DeletionStrategy s )'-Methode zur&uuml;ck, die die Methode 'select' aufruft.<br />
         *  Aufgrund des Wertes, der an die 'deleteSelected( DeletionStrategy s )'-Methode zur&uuml;ckgegeben wird, kann die 'deleteSelected( DeletionStrategy s )'-Methode eine &Auml;nderung an der Struktur der Liste vorgenommen werden
         * </p>
         */
        public abstract boolean select( E ref );
    }

    /**
     * <p>
     *  - Die Methode 'deleteSelected' pr&uuml;ft alle Elemente der Liste durch einen Aufruf der 'select'-Methode<br />
     *  - Wenn die 'select'-Methode f&uuml;r das Element, dass ihr als Argument &uuml;bergeben wurde (also das aktuelle Element der Liste), 'true' liefert, wird das aktuelle Element
     *    (und damit auch die Referenz auf den Inhalt ('content'-Attribut der Klasse 'Element') gel&ouml;scht)<br />
     *    -> Der Garbage-Collector wirft das Element weg (=>, weil es nicht mehr &uuml;ber eine Referenz erreichbar gewesen ist)<br />
     *  - Zum L&ouml;schen wird die private 'remove()'-Methode verwendet
     * </p>
     */
    public void deleteSelected( DeletionStrategy<T> s )
    {
        Element current = first;
        while ( current != null )//Solange nicht das Ende der Liste erreicht wurde,
        {
            Element candidate = current; // Erstelle eine Referenzvariable 'candidate', die auf 'current' (das zu löschende Element) zeigt
            current = current.getSucc(); //Bevor das Element gelöscht wird, wird current bereits ein Element weiter gesetzt
            // -> Das machen wir, damit die Liste nicht kaputt geht.
            if ( s.select( candidate.getContent() ) )
            {
                remove( candidate );//
            }
        }
    }

    /**
     * <p>
     *  L&ouml;scht das  Element, dass als Argument &uuml;bergeben wurde, aus der Liste
     * </p>
     * <p>
     *  -> Die Methode ist privat deklariert, da die Klasse 'Element' in 'DoublyLinkedList' verborgen ist (https://t.ly/FSJR)
     * </p>
     */
    private void remove( Element e )
    {
        if ( e != null ) //Das Element darf nicht leer sein. Es muss also ein echtes Element in der Liste sein
        {
            //Beim löschen von Elementen gibt es 4 Fälle, die Auftreten können, die wir beachten müssen
            if ( e.hasSucc() && e.hasPred() )//1. Fall: Es handelt sich um ein Element, dass irgendwo in der Liste zwischen 2 anderen Elementen liegt
            {
                e.getPred().connectAsSucc( e.getSucc() );//Dann müssen wir den Vorgänger und den Nachfolger, des zu löschenden Elements miteinander bekannt machen.
                //Durch das saubere Abtrennen durch die 'disconnectSucc()'- und 'disconnectPred()'-Methode, die in der Methode
                //'connectAsSucc( Element e )' der privaten inneren Klasse von 'Element' aufgerufen werden, werden alle Referenzen auf
                //das zu löschende 'Element'-Objekt ('e') aufgehoben und 'e' wird vom GarbageCollector weggeworfen. 
                //Veranschaulichung: https://t.ly/b0Ns
            } else if ( e == first && e.hasSucc() )//2. Fall: Das zu löschende Element, ist das erste Element aber nicht das einzige Element
            // -> Wir wollen also den Listenanfang löschen
            {
                first = first.getSucc();//Die Referenz vom ersten Element der Liste ('first') wird auf dessen Nachfolger gesetzt
                first.disconnectPred();//Das Element, auf das 'first' referenziert, wird von seinem Vorgänger getrennt
                //Veranschaulichung: https://t.ly/UA9d
            } else if ( e == last && e.hasPred() )//3. Fall: Das zu entfernende Element ('e') ist das letzte, aber nicht das einzige Element der Liste
            {
                last = last.getPred();//Die Referenz vom letzten Element der Liste ('last') wird auf dessen Vorgänger gesetzt 
                last.disconnectSucc();//Das Element, auf das 'last' referenziert, wird von seinem Nachfolger getrennt
            } else {//4. Fall: Das Einzige Element soll gelöscht werden.
                first = last = null;//Da die Liste nach dem Entfernen keinen Inhalt mehr hat, wird die Rereferenz von first und last auf 'null' gesetzt
            }
            size--;//Nach dem löschen eines Elements in der Liste  muss immer die Länge ('size'-Attribut der Klasse 'DoublyLinkedList') korrigiert werden
        }
    }

    // Die 'Element'-Klassse kann verborgen werden

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
     *      Elemente enthalten beliebige Objekte der Klasse Object. Ein Element Objekt kennt immer seinen Nachfolger und seinen Vorgänger.
     *  </p>
     * </center>
     */
    private class Element
    {
        private T content;//Die Contents sind beliebige Inhalte: Object ist die Oberklasse aller Klassen
        private Element pred, succ;//Verkettung der Elemente

        /**
         * <h1>Konstruktor -> Element (Object c)</h1>
         * <p>
         *  Erstellt ein neues Element Objekt, dass den Inhalt eines Objects in dem Attribut content speichert.<br />
         *  Das einzelne Element ist noch nicht mit der Liste Verbunden.<br />
         *      -> Das einf&uuml;gen der Elemente in die 'DoublyLinkedList' übernehmen Methoden der Klasse 'DoublyLinkedList'<br />
         *          => add(), addFirst(), connectAsSucc(), connectAsPred() etc.
         * </p>
         */
        public Element( T c )
        {
            content = c;
            pred = succ = null;
        }

        /**
         * <h1>getContent()</h1>
         * <p>
         *  Gibt das Objekt, auf das das Attribut 'content' referenziert zurück.
         * </p>
         */
        public T getContent()
        {
            return content;
        }

        /**
         * <h1>setContent( T c )</h1>
         * <p>
         *  Setzt die Referenz des 'contents'
         * </p>
         */
        public void setContent( T c )
        {
            content = c;
        }

        //Methoden, die sich mit dem Nachfolger beschäftigen sich mit ihrem Nachfolger
        /**
         * <h1>hasSucc()</h1>
         * <p>
         *  Gibt true zurück, falls es einen Nachfolger gibt.<br />
         *  Gibt es keinen Nachfolger, wird false zurückgegeben. <br />
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
         *  Gibt den Nachfolger zurück.<br />
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
                succ.pred = null;
                succ = null;
            }
        }

        /**
         * <h1>connectAsSucc( Element e)</h1>
         * <p>
         *   Das als Argument &uuml;bergebene Element e wird als Nachfolger des Element-Objektes gesetzt, dass diese Methode aufruft.<br />
         *   Nach dieser Methode ist das Element Objekt, dass diese Methode aufgerufen hat, der Vorgänger von e, sofern e nicht leer ist.<br />
         *   Wenn e leer ist, verweist die Nachfolge Referenz (succ) des aktuellen (aufrufenden) Elements auf null.
         * </p>
         */
        public void connectAsSucc( Element e)
        {
            disconnectSucc();//Das Element kann mit einem anderen Nachfolger verknüpft sein, dann wird das Element von seinem Nachfolger befreit werden -> So wird Platz für das als Argument übergebene 'Element' e als neuen Nachfolger geschaffen
            succ = e;//e wird in der Variablen succ gespeichert. Dadurch ist e der neue Nachfolger | Alle Elemente die zwischen dem Element, dass diese Methode ausgeführt hat und dem Element, dass eingefügt wurde, haben wir vergessen (, weil es keine Referenzen mehr auf diese Objekte gibt -> Der GarbageCollector hat die gelöscht) (-> Die Elemente sind dann gelöscht)
            //=> Veranschaulichung des Vergessens von Elementen, die dazwischen liegen: https://www.clanneko.tk/Files/hotlink-ok/Asuna_2cb205f3-ddf1-4be4-a851-9209c846308a_1ux_21-02-2021_21-59.png
            if ( e != null ) //Wenn das als Argument übergebene 'Element' nicht null ist | Ein leeres Element ist ein nicht existierendes Element. Es kann aber Elemente geben deren Inhalt (Also content) leer (also == null) ist.
            {
                e.disconnectPred();//Das als Argument übergebene 'Element' ('e') kann einen Vorgänger haben, daher wird 'e' von seinem vorgänger getrennt und
                e.pred = this;//e ist der neue Nachfolger von dem Element, dass diese Methode aufgerufen hat. (=> Das Element, dass diese Methode aufgerufen hat, ist der Vorgänger von e)
                //Das als Argument übergebene 'Element' ('e') wurde als Nachfolger hinzugefügt
                //Die Invariante wurde eingehalten
            }
        }

        //Analoge Methoden, die sich mit dem Vorgänger beschäftigen
        /**
         * <h1>hasPred()</h1>
         * <p>
         *  Gibt true zurück, wenn es einen Vorgänger gibt<br />
         *  Gibt false zurück, wenn es keinen Vorgänger gibt.
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
         *  Gibt den Vorgänger zurück
         * </p>
         */
        public Element getPred()
        {
            return pred;
        }

        /**
         * <h1>disconnectPred()</h1>
         * <p>
         *  Trennt den Vorgänger vom Aktuelllen Element ab.<br />
         *  Der Vorgänger vom Aktuellen Element wird dann vom Garbagecollector mitgenommen und gelöscht.
         * </p>
         */
        public void disconnectPred()
        {
            if ( hasPred() )
            {
                pred.succ = null;
                pred = null;

            }
        }

        /**
         * <h1>connectAsPred( Element e)</h1>
         * <p>
         *   Das als Argument übergebene Element e wird als Vorgänger des Element-Objektes gesetzt, dass diese Methode aufruft.<br />
         *   Nach dieser Methode ist das Element Objekt, dass diese Methode aufgerufen hat, der Nachfolger von e, sofern e nicht leer ist.<br />
         *   Wenn e leer ist, verweist die Nachfolge Referenz (pred) des aktuellen (aufrufenden) Elements auf null.
         * </p>
         */
        public void connectAsPred( Element e )
        {
            disconnectPred();
            if ( e != null )
            {
                e.disconnectSucc();
                e.succ = this;
            }
            pred = e;
        }
    }

}
