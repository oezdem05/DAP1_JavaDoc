/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Oezdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse LectureWithSortAlgorithms</h2>
 *  <p>
 *      In dieser Klasse wurden die aus der Vorlesung bekannten Sortieralgorithmen <em>Sortieren durch Ausw&auml;hlen (SelectionSort), Sortieren durch Einf&uuml;gen (InsertionSort) und der QuickSort-Algorithmus</em><br/>
 *      jeweils f&uuml;r zwei Sortierkriterien (Matrikelnummer und Name) implementiert.<br />
 *      Bei Allen Sortieralgorithmen wird das Feld 'students' aufsteigend sortiert.<br />
 *  </p>
 *  <h3>Aufsteigend sortiert</h3>
 *  <p>
 *      Aufsteigend sortiert bedeutet, dass jedes Element, das links von einem Element steht kleiner oder gleich dem Element an der aktuellen Position ist.<br />
 *      Jedes Element, das rechts steht ist gr&ouml;&szlig;er oder gleich.<br />
 *      <b>Beispiel:</b><br />
 *      1=1=1<2=2=2<3<4<5=5=5<6
 *  </p>
 * </center>
 */
public class LectureWithSortAlgorithms
{
    private String title;//Legt den Namen der Vorlesung fest
    private Student[] students;//Legt ein Feld vom Typ Student an, mit dem Namen students
    private int firstUnused;//Die Variable firstUnused merkt sich den kleinsten Index, der noch leer ist und kein Objekt enth�lt.

    /**
     * <h1>Konstruktor - LectureWithSortAlgorithms( String t, int cap )</h1>
     * <p>
     *  Der Konstruktor erstellt ein neues 'LectureWithSortAlgorithms-Objekt'.<br />
     *  Das Feld 'students' hat dabei die L&auml;nge 'cap', die neben dem String als int als Argument &uuml;bergeben wird
     * </p>
     */
    public LectureWithSortAlgorithms( String t, int cap ) 
    {
        title = t;//Wei�t den Namen der Vorlesung zu
        students = new Student[cap];//Legt ein Feld der l�nge 'cap' an, dass 'Student-Objekte' enthalten kann
        firstUnused = 0;//Zu beginn ist kein Feld belegt. Das bedeutet, dass das erste Feld leer ist.
    }

    /**
     * <h1>void add( Student s )</h1>
     * <p>
     *  Die add Methode f�gt ein Student Objekt in das Feld ein.
     * </p>
     */
    public void add( Student s ) 
    {
        if ( firstUnused < students.length ) //Solange noch Platz in dem Feld ist
        {
            students[firstUnused] = s;//Wird am Index firstUnused das als Argument �bergebene 'Student-Objekt' abgelegt
            firstUnused++;//Der Index von firstUnused wird um eins inkrementiert (+1 gerechnet)
        }
    }

    /**
     * <h1>void show()</h1>
     * <p>
     *  Diese Methode erzeugt f&uuml;r jedes Objekt in dem Feld 'students' eine geeignete Ausgabe, indem auf jedes 'Student-Objekt' (,also s) die toString() Methode aus der Klasse 'Student' aufgerufen wird.
     * </p>
     */
    public void show() 
    {
        System.out.println( "lecture: " + title );//Gibt den Namen der aktuellen Veranstaltung (Vorlesung aus)
        System.out.println( "participants:");//Gibt einen String mit dem Text "participants:" aus und erzeugt einen Zeilenumbruch
        for ( Student s : students )//F�r jedes 'Student-Objekt' in dem Feld 'students' wird das 'Student-Objekt' als s gemerkt
        {
            if ( s != null )//Wenn s nicht leer ist (Verhindert das Werfen einer NullPointerException),
            {
                System.out.println( s.toString() );//dann wird die toString Methode aus der Klasse Student auf dem aktuellen Objekt ausgef�hrt und eine Ordnungsgem��e Ausgabe erzeugt.
            }
        }
    }

    /**
     * <h1>void swapStudents( int i, int j )</h1>
     * <p>
     *  Das Vertauchen der Inhalte von Elementen des Feldes 'students' wird in verschiedenen Methoden innerhalb der Klasse 'Lecture' ben�tigt. Daher wird dieses Vertauschen als eigenst�ndige, private Methode realisiert.<br />
     *  Das Vertauschen von Inhalten von zwei Variablen oder Attribute erfordert immer den Einsatz einer Hilfsvariablen, um durch Zwischenspeichern ein �berschreiben zu vermeiden.<br />
     *  Die Methode vermeidetr das (unn�tze) Vertauschen von Objekten an der gleichen Position.
     * </p>
     */
    private void swapStudents( int i, int j )
    //Vertauscht die beiden Objekte, die am Index i und j stehen miteinander.
    {
        if ( i != j) 
        {
            //Hier findet ein dreieckstausch statt
            Student temp = students[i];//('temp' steht f�r 'tempor�r')
            students[i] = students[j];//Nach dieser Zeile code, steht am Index i und j das selbe Objekt
            students[j] = temp;//Daher ist eine Hilfsvariable ('temp') notwendig, da sonst das Objekt, das Urspr�nglich am index i gewesen ist nun verloren gegangen (weg) w�re.
        }
    }

    /**
     * <h1>void insertionSortByNumber()</h1>
     * <p>
     *  Bestimmt den allgemeinen Zwischenzustand (-> Der Index ab dem die Indezes im Feld leer sind)
     * </p>
     */
    public void insertionSortByNumber() 
    {
        for ( int i = 1; i < firstUnused; i++ ) //Beachte, dass i=0 schon sortiert ist (-> Das ist ja das erste Objekt.)
        {
            shiftStudentsByNumber( i );//Positionssuche, wo das Objekt eingef�gt werden soll.
        }
    }

    /**
     * <h1>void shiftStudentsByNumber( int start )</h1>
     * <p>
     *  Diese Methode verschiebt die Elemente.
     * </p>
     * <p>
     *  Ich muss diesen Platz nicht vorher (bei der insertionSortByNumber Methode) bestimmen, <br />
     *  den ich wei� ja, dass das Objekt, dass ich als n�chstes einf�gen will, in den sortierten Teil muss. <br />
     *  Ab da m�ssen alle Objekte um ein Index nach rechts r�cken -> Im idealfall (falls es das Maximum ist, muss es gar nicht r�cken => Es steht dann bereits an der richtigen Stelle<br />
     *  =->Dann muss nur der sortierte Bereich um ein Element vergr&ouml;&szlig;ert und der unsortierte Teil um eins verkleinert werden.)<br />
     *  <br />
     *  -> Das verschieben entsteht im Moment der Positionssuche.<br />
     *  => Die Position muss nicht im vorhinein ermittelt werden, sondern erst in der shiftStudentsByNumber( int start ) Methode.
     * </p>
     */
    private void shiftStudentsByNumber( int start )//Sortiert von 0 bis i
    {
        Student toInsert = students[start];//Lege eine Hilfsvariable 'toInsert' an um damit auf das aktuelle Objekt zu verweisen. (Dies ist f�r den Dreieckstausch notwendig).
        int i = start;
        while ( i > 0 && students[i - 1].hasGreaterNumber( toInsert ) )//Das Kriterium 'i>0' sorgt daf�r, dass wir nicht links aus dem Feld raus springen (Verhindert eine 'ArrayOutOfBoundsException')
                                                                        //das weitere Kriterium ist, dass das Objekt am Index i-1 gr��er ist, als das Objekt, dass wir einf�gen wollen (toInsert) (students[i - 1].hasGreaterNumber( toInsert ))
                                                                        //'&&' ist der logische UND-Operator: Der linke Teil wird immer ausgewertet. Der rechte wird nur ausgewertet, wenn der Linke Ausdruck true geliefert hat (Der rechte Ausdruck wird nur ausgewertet, wenn es notwendig ist).
                                                                        //(-> Das kann man alternativ auch mit mehreren if-Abfragen machen => Das w�re aber weniger Elegant)
                                                                        //=> Das spart nicht nur Zeit, sondern hat auch einen ausschlaggebende Funktion.
                                                                        //=-> F�r i = 0 w�rde der rechte Teil eine 'ArrayOutOfBounds' Exception werfen (weil der Index i -1 = 0-1 = -1 nat�rlich nicht existiert).
                                                                        //==> f�r i == 0 wird der fehlerhafte Zugriff students[-1] nicht mehr ausgef�hrt.
                                                                        //
                                                                        //- Die while Schleife durchl�uft nicht die ganze Schleife (wie bei der for-Schleife beim SelectionSort (selectionSortByNumber()) schon der Fall ist)
                                                                        //->Die while Schleife bricht ab, sobald die Bedingung nicht mehr erf�llt ist. 
                                                                        //
                                                                        //->'hasGreaterNumber' lifert false, wenn students[i-1] und toInsert gleichheit (also die selbe Gr��e haben) aufweisen
                                                                        //=> Da der Rechte Teil der Konjunktion dann false liefert, wird die while Schleife gestoppt.
        {
            //solange wir nicht die Position erreicht haben, wo wir einf�gen m�ssen,
            students[i] = students[i - 1];//m�ssen wir das Objekt am Index[i-1] um ein Index nach rechts schieben.
            i--;
        } 
        students[i] = toInsert;
    }

    /**
     * <h1>void insertionSortByName()</h1>
     * <p>
     *  <em>-> Diese Methode ist Analog zu der Methode 'insertionSortByNumber()'</em>
     * </p>
     * <p>
     *  Bestimmt den allgemeinen Zwischenzustand (-> Der Index ab dem die Indezes im Feld leer sind)<br />
     * </p>
     */
    public void insertionSortByName() 
    {
        for ( int i = 1; i < firstUnused; i++ ) //Beachte, dass i=0 schon sortiert ist (-> Das ist ja das erste Objekt.)
            shiftStudentsByName( i );//Positionssuche, wo das Objekt eingef�gt werden soll.
    }

    /**
     * <h1>void shiftStudentsByName( int start )<h1>
     * <p>
     *  <em>-> Diese Methode ist Analog zu der Methode 'shiftStudentsByNumber()'</em>
     * </p>
     * <p>
     *  Diese Methode verschiebt die Elemente.
     * </p>
     * <p>
     *  Ich muss diesen Platz nicht vorher (bei der insertionSortByName Methode) bestimmen, <br />
     *  den ich wei� ja, dass das Objekt, dass ich als n�chstes einf�gen will, in den sortierten Teil muss. <br />
     *  Ab da m�ssen alle Objekte um ein Index nach rechts r�cken -> Im idealfall (falls es das Maximum ist, muss es gar nicht r�cken => Es steht dann bereits an der richtigen Stelle<br />
     *  =->Dann muss nur der sortierte Bereich um ein Element vergr&ouml;&szlig;ert und der unsortierte Teil um eins verkleinert werden.)<br />
     *  <br />
     *  -> Das verschieben entsteht im Moment der Positionssuche.<br />
     *  => Die Position muss nicht im vorhinein ermittelt werden, sondern erst in der shiftStudentsByNumber( int start ) Methode.
     * </p>
     */
    private void shiftStudentsByName( int start )//Verschiebt die Objekte von 0 bis i (i wird von der Methode 'insertionSortByName()' in dem Moment �bergeben, indem 'insertionSortByName()' die 'shiftStudentsByName()' Methode aufruft.)
    {
        Student toInsert = students[start];//Lege eine Hilfsvariable 'toInsert' an um damit auf das aktuelle Objekt zu verweisen. (Dies ist f�r den Dreieckstausch notwendig).
        int i = start;
        while ( i > 0 && students[i - 1].hasGreaterName( toInsert ) )//Das Kriterium 'i>0' sorgt daf�r, dass wir nicht links aus dem Feld raus springen (Verhindert eine 'ArrayOutOfBoundsException')
                                                                        //das weitere Kriterium ist, dass das Objekt am Index i-1 gr��er ist, als das Objekt, dass wir einf�gen wollen (toInsert) (students[i - 1].hasGreaterNumber( toInsert ))
                                                                        //'&&' ist der logische UND-Operator: Der linke Teil wird immer ausgewertet. Der rechte wird nur ausgewertet, wenn der Linke Ausdruck true geliefert hat (Der rechte Ausdruck wird nur ausgewertet, wenn es notwendig ist).
                                                                        //(-> Das kann man alternativ auch mit mehreren if-Abfragen machen => Das w�re aber weniger Elegant)
                                                                        //=> Das (der logische UND-Operator) spart nicht nur Zeit, sondern hat auch eine ausschlaggebende Funktion.
                                                                        //=-> F�r i = 0 w�rde der rechte Teil eine 'ArrayOutOfBounds' Exception werfen (weil der Index i -1 = 0-1 = -1 nat�rlich nicht existiert).
                                                                        //==> f�r i == 0 wird der fehlerhafte Zugriff students[-1] nicht mehr ausgef�hrt.
                                                                        //
                                                                        //- Die while Schleife durchl�uft nicht die ganze Schleife (wie es bei der for-Schleife beim SelectionSort (selectionSortByNumber()) schon der Fall ist)
                                                                        //->Die while Schleife bricht ab, sobald die Bedingung nicht mehr erf�llt ist. 
                                                                        //
                                                                        //->'hasGreaterNumber' liefert false, wenn students[i-1] und toInsert gleichheit (also die selbe Gr��e haben) aufweisen
                                                                        //=> Da der Rechte Teil der Konjunktion dann false liefert, wird die while Schleife gestoppt.
        {
            //solange wir nicht die Position erreicht haben, wo wir einf�gen m�ssen,
            students[i] = students[i - 1];//m�ssen wir das Objekt am Index[i-1] um ein Index nach rechts schieben.
            i--;
        } 
        students[i] = toInsert;
    }
    
    /**
     * <h1>void selectionSortByName()</h1>
     * <p>
     *  <em>Diese Methode ist Analog zu der selectionSortByNumber()-Methode</em>
     * </p>
     * <p>
     *  Der letzte zu sortierende Eintrag muss nicht betrachtet werden, da er immer sein eigenes Minimum bildet. <br />
     *  Die Methode 'searchForMinimalName()' gibt den Index des Student-Objekts mit dem kleinsten Namen zur�ck.<br />
     *  Die Suche mit 'searchForMinimalName()' erfolgt im unsortierten Teil des Feldes students:<br />
     *      - Der unsortierte Teil beginnt bei Index i.<br />
     *      - Der unsortierte Teil endet bei Index firstUnused-1.<br />
     * </p>
     */
    public void selectionSortByName() 
    //Diese Methode kontrolliert den ganzen Ablauf, der das sortieren vornimmt.
    {
        for ( int i = 0; i < firstUnused - 1; i++ ) //f�r jeden Schleifendurchlauf in 'selectionSortByName()', wird searchForMinimalName() f�r den aktuellen Index aufgerufen.
        //in searchForMinimalName() Methode wird die schleife ab dem Index i+1 (neuer Startpunkt) bis zum Ende durchlaufen. 
        //die Methode searchForMinimalName() ruft dann f�r jeden Schleifendurchlauf die 'hasGreaterNumber()' Methode auf.
        //=>Also: Wir durchlaufen n mal die �u�ere Schleife & jedes mal wird das St�ck, dass wir in der Inneren Schleife durchlaufen m�ssen k�rzer.
        {
            int position = searchForMinimalName( i );//In position ist nun der index des Minimumobjektes gespeichert. 
            // -> Um das Minimumobjekt zu bestimmen, wird das i als Argument f�r den Parameter (int start) in 'searchForMinimalName()' �bergeben.
            // => Da der unsortierte Teil immer kleiner wird, wird der n�chste Durchlauf auch immer um eins k�rzer (Beispiel: n-2 (2. Druchlauf); n-3 (3.Durchlauf))
            swapStudents( i, position );
        }
    }

    /**
     * <h1>int searchForMinimalName( int start )</h1>
     * <p>
     *  Die beiden Methoden ('searchForMinimalName()' und 'hasGreaterName()') sind strukturell genau so, wie beim sortieren nach der Matrikelnummer.
     *  (=> Deswegen ist Stefan nicht genauer auf die Methode eingegangen.)
     * </p>
     */
    private int searchForMinimalName( int start )
    {
        int selected = start;
        for ( int i = start + 1; i < firstUnused; i++ ) 
        {
            if ( students[selected].hasGreaterName( students[i] ) )//Der Algorithmus bleibt gleich. Das einzige, was sich �ndert ist die Methode, mit der die Suche erfolgt (-> 'hasGreaterName()').
            {
                selected = i;
            } 
        }
        return selected;
    }

    /**
     * <h1>void selectionSortByNumber()</h1>
     * <p>
     *  Der letzte zu sortierende Eintrag muss nicht betrachtet werden, da er immer sein eigenes Minimum bildet. <br />
     *  Die Methode 'searchForMinimnalNumber()' gibt den Index des Student-Objekts mit der kleinsten Matrikelnummer zur�ck.<br />
     *  Die Suche mit 'searchForMinimalNumber()' erfolgt im unsortierten Teil des Feldes students:<br />
     *      - Der unsortierte Teil beginnt bei Index i.<br />
     *      - Der unsortierte Teil endet bei Index firstUnused-1.<br />
     * </p>
     */
    public void selectionSortByNumber() 
    //Diese Methode kontrolliert den ganzen Ablauf, der das sortieren vornimmt.
    {
        for ( int i = 0; i < firstUnused - 1; i++ ) //f�r jeden Schleifendurchlauf in 'selectionSortByNumber()', wird searchForMinimalNumber() f�r den aktuellen Index aufgerufen.
        //in searchForMinimalNumber() wird die schleife ab dem Index i+1 (neuer Startpunkt) bis zum Ende durchlaufen. 
        //die Methode searchForMinimalNumber() ruft dann f�r jeden Schleifendurchlauf die 'hasGreaterNumber()' Methode aus.
        //=>Also: Wir durchlaufen n mal die �u�ere Schleife & jedes mal wird das St�ck, dass wir in der Inneren Schleife durchlaufen m�ssen k�rzer.
        {
            int position = searchForMinimalNumber( i );//In position ist nun der index des Minimumobjekt gespeichert. 
            // -> Um das Minimumobjekt zu bestimmen, wird das i als Argument f�r den Parameter (int start) in 'searchForMinimalNumber()' �bergeben.
            // => Da der unsortierte Teil immer kleiner wird, wird der n�chste Durchlauf auch immer um eins k�rzer (Beispiel: n-2 (2. Druchlauf); n-3 (3.Durchlauf))
            swapStudents( i, position );
        }
    }

    /**
     * <h1>int searchForMinimalNumber( int start )</h1>
     * <p>
     *  Die Methode 'searchForMinimalNumber()' gibt den Index zur�ck, an dem das 'Student-Objekt' mit der kleinsten Matrikelnummer innerhalb des unsortierten Teils des Feldes 'students' steht.<br />
     *  Die Variable 'selected' enth�lt nach jedem Durchlauf durch die Schleife den Index, an dem das bis dahin gefundene 'Student-Objekt' mit der kleinsten Matrikelnummer steht.
     * </p>
     */
    private int searchForMinimalNumber( int start )
    //Sucht nach dem Objekt mit der kleinsten Matrikelnummer in dem Feld students, ab einer Startposition (Der Startindex w�chst ja immer (, weil der sortiertere Teil wird ja immer gr��er))
    {
        int selected = start;//Das Objekt am Index start ist zun�chst das Minimum-Objekt, da wir zu Beginn nur das Objekt am Index start kennen.
        for ( int i = start + 1; i < firstUnused; i++ ) //wir fangen bei start = 0, also i = 0+1 = 1 an.
        // -> Das sind n-1 vergleiche
        {
            if ( students[selected].hasGreaterNumber( students[i] ) )//Wenn das Objekt am Index selected gr��er ist als das Objekt am Index i, dann ist das Objekt am Index i
            //f�rs erste (das Feld wird ja noch durchlaufen und es k�nnte ja noch ein kleineres Objekt auftauchen) das aktuelle Minimum-Objekt
            //(Information: Die Methode 'boolean hasGreaterNumber( Student s )' ist eine Methode der Klasse Student)
            {
                selected = i;
            } 
        }
        return selected;
    }
    
    /**
     * <h1>void quickSortByNumber()</h1>
     * <p>
     *  Diese Methode ist notwendig, da sie den ersten Aufruf von '<b>private</b> void groupByNumber( int leftBound, int rightBound )' �bernimmt.<br />
     *  Dieser Umweg ist erforderlich, da Parameterwerte f�r die Gr��e und die Belegung des Feldes ebenfalls privat deklariert sind. 
     * </p>
     */
    public void quickSortByNumber() 
    {
        groupByNumber( 0, firstUnused-1 );
    }
    
    /**
     * <h1>void groupByNumber( int leftBound, int rightBound )</h1>
     * <p>
     *  - Die Methode ist privat:<br />
     *    <b>private</b> void groupByName( int leftBound, int rightBound )<br />
     *  - Sinnvoll, da die Parameterwerte ja auch abh�ngig sind von der Gr��e und der Belegung des Feldes 'students' und dieses ebenfalls 'privat' deklariert ist.<br />
     *  - Daher wird f�r eine L�sung, die ein einfaches Sortieren der Studierenden einer Vorlesung erlaubt, eine zus�tzliche �ffentliche Methode ben�tigt, die den ersten Aufruf von 'groupByNumber' �bernimmt.<br />
     *    -> Diese Methode hat die Signatur public void quickSortByNumber() und erwartet keine Parameter (die Klammern hinter dem Methodennamen sind leer)
     * </p>
     */
    private void groupByNumber( int leftBound, int rightBound )//Am Anfang ist leftBound 0 und rightBound ist am Anfng firstUnused-1
    //Wir m�chten in dem Bereich von leftBound und rightBound sortieren -> leftBound und rightBound sind die Grenzen der Ausgangsfolge
    {
        /**
         * Funktionsweise des Quicksort Algorithmusses
         * - Anhand des Pivot-Elements in Teilfolgen zerlegen
         * - Algorithmus auf Teilfolgen anwenden -> Selbstaufruf (Rekursionsanker)
         */
        if (leftBound < rightBound )
        /**
         * - Die Parameter geben den kleinsten (leftBound) und den gr��ten (rightBound) Index
         *   eines Ausschnitts des Feldes 'students' an, der mit der Methode bearbeitet werden soll.
         * - Die Methode ist privat, da au�erhalb der Klasse 'Lecture' das Feld unbekannt ist und damit eine
         *   Angabe von Indizes sinnvoll nicht erfolgen kann.
         *   
         *   -> Wenn leftBound < rightBound gilt, m�ssen mindestens zwei Elemente sortiert werden
         *   => Sonst ist leftBound = rightBound (dann gibt es nur ein Element) =-> Ein einzelnes Element ist schon sortiert.
         *   -> Wenn leftBound > rightBound ist, dann sind wir fertig (Da is t nichts mehr) ==> nichts mehr zu tun.
         */ 
        {
            int leftOfGreaterPart = leftBound; // -> Zu Beginn, gibt es keine Elemente, die in den Teil der Elemente gr��er als das Pivot zugeteilt weden k�nnen. 

            for ( int candidate = leftBound; candidate < rightBound; candidate++ )//es m�ssen noch Elemente verteilt werden | Also die zuteilung in die Teilfolge der gr��eren oder kleineren Elemente als das Pivot muss noch stattfinden.
                                                                                  //(Wenn candidate >= Pivot ist, m�ssen wir nichts machen und springen aus der for-Schleife)
                                                                                  //Die for-Schleife durchl�uft das Feld
            {
                if ( students[rightBound].hasGreaterNumber( students[candidate] ) )//wenn das Pivot-Element (Element am Index 'rightBound') gr��er ist, als betrachtete Element (Element am Index 'candidate')
                                                                                   //Alternativ ausgedr�ckt: Das betrachtete Element (Element am Index 'candidate') ist kleiner als das Pivot-Element (Element am Index 'rightBound')
                {
                    swapStudents( candidate, leftOfGreaterPart );//Vertausche das betrachtete Element (candidate) in den Teil der kleineren Elemente. | Vertauscht 'candidate' mit 'leftOfGreaterPart'
                    leftOfGreaterPart++;//Danach enth�lt die Teilfolge der kleineren Elemente ein Objekt mehr und der Index der Grenze der gr��eren Elemente (leftOfGreaterPart) wird um ein Index nach rechts verschoben
                }
            }

            swapStudents( leftOfGreaterPart, rightBound );//Das Pivot-Element (rightBound) wird mit leftOfGreaterPart vertauscht. | Das Pivot steht dann zwischen den beiden (kleinere & gr��ere) Teilfolgen und ist einsortiert (Es steht nun an der richtigen Stelle).
                                                          //Falls 'leftOfGreaterPart' == 'rightBound' gilt, wird nicht getauscht (-> Beachte, dass es bei zwei Inhaltsgleichen Objekten funktioniert aber nicht bei zwei Objektgleichen Elementen) 
            groupByNumber( leftBound, leftOfGreaterPart - 1 );//Rekursion: Algotithmus auf kleinere Teilfolge anwenden
            groupByNumber( leftOfGreaterPart + 1 , rightBound );//Rekursion: Algotithmus auf gr��ere Teilfolgen anwenden
        }
    }

    /**
     * <h1>void quickSortByName()</h1>
     * <p>
     *  <em>Diese Methode ist Analog zu der 'quickSortByNumber()' Methode</em>
     * </p>
     * <p>
     *  Diese Methode ist notwendig, da sie den ersten Aufruf von '<b>private</b> void groupByName( int leftBound, int rightBound )' �bernimmt.<br />
     *  Dieser Umweg ist erforderlich, da Parameterwerte f�r die Gr��e und die Belegung des Feldes ebenfalls privat deklariert sind. 
     * </p>
     */
    public void quickSortByName() 
    {
        groupByName( 0, firstUnused - 1 );
    }

    /**
     * <h1>void groupByName( int leftBound, int rightBound )</h1>
     * <p>
     *  <em>Die Methode arbeitet analog zur Methode 'private void groupByNumber( int leftBound, int rightBound )'</em>
     * </p>
     * <p>
     *  - Die Algorithmen der Methoden sind identisch, nur die Namen unterscheiden sich, da eine andere Vergleichsmethode ('public boolean hasGreaterName( Student s )' anstatt 'public boolean hasGreaterNumber( Student s )')
     *    der Klasse 'Student' zum Einsatz kommen muss.
     *  - Wir haben gelernt wie man das Duplizieren des Programmtextes vermeiden kann: Entwurfsmuster Strategie
     * </p>
     */
    private void groupByName( int leftBound, int rightBound )//Wir sortieren anhand der Namen der Studierenden
    {
        if (leftBound < rightBound )
        {
            int leftOfGreaterPart = leftBound; 

            for ( int candidate = leftBound; candidate < rightBound; candidate++ )
            {
                if ( students[rightBound].hasGreaterName( students[candidate] ) )//Anstatt mit der Methode 'hasGreaterNumber()' zu vergleichen, vergleichen wir jetzt mit der Methode 'hasGreaterName()'
                {
                    swapStudents( candidate, leftOfGreaterPart );
                    leftOfGreaterPart++;
                }
            }

            swapStudents( leftOfGreaterPart, rightBound );

            groupByName( leftBound, leftOfGreaterPart - 1 );//Rekursion: Algotithmus auf kleinere Teilfolge anwenden
            groupByName( leftOfGreaterPart + 1 , rightBound );//Rekursion: Algotithmus auf gr��ere Teilfolge anwenden
        }
    }

    /**
     * <h1>void showStudents()</h1>
     * <p>
     *  Ruft f&uuml;r alle 'Student'-Objekte im Feld 'students' die 'toString()'-Methode der Klasse 'Student' auf.
     * </p>
     */
    private void showStudents()
    {
        for ( Student s : students )//for-each-Schleife: F�r jedes 'Student'-Objekt, dass in dem Feld 'students' enthalten ist, zeigt die Referenzvariavle 's' auf das aktuelle 'Student'-Objekt
        {
            if ( s != null )//Wenn das 'Student-Objekt' nicht leer ist, 
            {
                System.out.println( s.toString() );//wird die toString() Methode der Klasse Student auf dem aktuellen Objekt 's' ausgef�hrt.
            }
        }
        System.out.println();//Erstelle einen Zeilenumbruch
    }

    /**
     * <h1>void show( String explain, int low, int high )</h1>
     * <p>
     *  Gibt die 'Student'-Objekte im Bereich von low bis high mithilfe der 'toString()'-Methode der Klasse 'Student' aus.
     * </p>
     */
    private void show( String explain, int low, int high )
    {
        System.out.println( explain + "( " + low + ", " + high + " )" );//Gibt die Argumente aus
        for (int i = low; i <= high; i++ )//Druchl�uft das Feld 'students' im Bereich von low bis high
        {
            System.out.println( "  " + students[i].toString() );//Ruft f�r das aktuelle 'Student'-Objekt die 'toString()' Methode der Klasse 'Student' auf und erstellt nach jedem Objekt ein Zeilenumbruch
        }
        System.out.println();//Zeilenumbruch
    }

    /**
     * <h1>void isSorted()</h1>
     * <p>
     *  Die Methode wirft eine Exception, falls das Feld 'students' nicht Aufsteigend sortiert ist.<br />
     *  Wenn das Feld aufsteigend sortiert ist, passiert nichts.
     * </p>
     */
    // isSorted supports testing of sort algorithms; will be quiet without errors
    public void isSorted()
    {
        for ( int i = 0; i < firstUnused-1; i++ ) 
        {
            if ( students[i].hasGreaterNumber( students[i+1] ) )//Wenn ein Beispiel gefunden wurde, f�r das 2 Objekte nicht das Sortierkriterium erf�llen (hier aufsteigend sortiert), dann
            {
                throw new RuntimeException( "sort violation" );//wird eine Exception (Ausnahme) geworfen
            }
        }
    }

}