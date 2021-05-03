/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der generischen Klasse BinarySearchTree<T></h2>
 *  <p>
 *      Die Klasse verwaltet den Anfang und das Ender der Liste. Au&szlig;erdem wei&szlig; sie, wie viele Elemente die Liste enth&auml;lt
 *  </p>
 * </center>
 */
public class BinarySearchTree<T extends Comparable<T>> //Der Typparameter 'T' wird eingeschränkt
{
    private T content; //'content' ist eine Referenz auf 'T', da alle Inhalte eines 'BinarySearchTree' vergleichbar sind, besitzt 'T' also eine Methode 'compareTo'
    private BinarySearchTree<T> leftChild, rightChild;

    /**
     * <p>
     *  Der Konstruktor legt einen leeren Baum an
     * </p>
     */
    public BinarySearchTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * <p>
     *  liefert den Inhalt ('content') des aktuellen Knotens.<br />
     *  Ist der aktuelle Inhalt leer, wird eine Ausnahme geworfen.
     * </p>
     */
    public T getContent()
    {
        if (!isEmpty() )
        {
            return content;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <p>
     *  isEmpty() gibt true zurück, wenn der aktuelle Knoten leer ist.<br />
     *  ist er nicht leer, wird false zurück gegeben.
     * </p>
     */
    public boolean isEmpty() 
    {
        return content == null;
    }

    /**
     * <p>
     *  Wenn der aktuelle Knoten ein Blatt ist, also nicht leer ist und sein linker & rechter Teilbaum leer sind,<br />
     *  wird true zurückgegeben.<br />
     *  Sonst wird false zurückgegeben.
     * </p>
     */
    public boolean isLeaf() 
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    /**
     * <p>
     *  F&uuml;gt den als Argument &uuml;bergebenen Inhalt in den Baum ein.
     * </p>
     */
    public void add( T t )
    {
        //Rekrusive Methode
        if ( isEmpty() ) //Abbruchbedingung: Der (Teil-)Baum ist leer
        {
            content = t;//Der neue Inhalt wird in den aktuellen Knoten eingefügt
            leftChild = new BinarySearchTree<T>();//Erhaltung der rekursiven Datenstruktur: Erstelle einen neuen leeren linken Teilbaum
            rightChild = new BinarySearchTree<T>();//Erhaltung der rekursiven Datenstruktur: Erstelle einen neuen leeren rechten Teilbaum
        }
        else
        {
            if ( content.compareTo( t ) > 0 )//Wenn der Inhalt des aktuellen Knotens größer als 't' ist | also: wenn 't' kleiner als der Inhalt des aktuellen Knoten ist,
            {
                leftChild.add( t );//Selbstaufruf: Füge den neuen Inhalt ('t') im linken Teilbaum ein | Reduktion: Ein Teilbaum ist immer kleiner als der ganze Baum
            }
            else if ( content.compareTo( t ) < 0 )//Wenn der Inhalt des aktuellen Knotens kleiner als 't' ist | also: wenn 't' größer als der Inhalt des aktuellen Knotens ist
            {
                rightChild.add( t );//Selbstaufruf: Füge den neuen Inhalt ('t') im rechten Teilbaum ein | Reduktion: Ein Teilbaum ist immer kleiner als der ganze Baum
            }
            //Wenn der Inhalt bereits im Baum enthalten ist, passiert nichts
        }
    }

    /**
     * <p>
     *  Wenn der als argument &uuml;bergebene Inhalt ('t') im Baum vorkommt, wird 'true' zurückgegeben.<br />
     *  Sonst wird 'false' zur&uuml;ckgegeben
     * </p>
     */
    public boolean contains( T t )
    {
        //Rekursive Methode
        if ( isEmpty() ) //Abbruuchbedingung: Wenn der (Teil-)Baum leer ist,
        {
            return false;//Kam der Inhalt nicht vor -> Gib 'false' zurück
        }
        else
        {
            if ( content.compareTo( t ) > 0 )//Wenn der Inhalt des aktuellen Knotens größer als 't' ist | also: wenn 't' kleiner als der Inhalt des aktuellen Knoten ist,
            {
                return leftChild.contains( t );//Selbstaufruf: Suche im linken Teilbaum weiter | Reduktion: Ein Teilbaum ist immer kleiner als der ganze Baum
            }
            else if ( content.compareTo( t ) < 0 )//Wenn der Inhalt des aktuellen Knotens kleiner als 't' ist | also: wenn 't' größer als der Inhalt des aktuellen Knotens ist
            {
                return rightChild.contains( t );//Selbstaufruf: Suche im rechten Teilbaum weiter | Reduktion: Ein Teilbaum ist immer kleiner als der ganze Baum
            } 
            //Wenn der Inhalt gefunden wurde:
            return true;//gib 'true' zurück
        }
    }

    /**
     * <p>
     *  Bestimmt die Anzahl unterschiedlicher Knoten im bin&auml;ren Suchbaum
     * </p>
     */
    public int size() 
    {
        //Rekursive Methode
        if ( isEmpty() ) //Abbruchbedingung: Der (Teil-)Baum ist leer
        {
            return 0;
        }
        else
        {
            return 1 + leftChild.size() + rightChild.size();//Selbstaufruf: Zähle den aktuellen Knoten und addiere die Rückgabe des linken und rechten Teilbaums zusammen | Reduktion: Ein Teilbaum ist immer kleiner als der ganze Baum
        }       
    }

    /**
     * <p>
     *  F&uuml;hrt einen vollst&auml;ndigen inOrder Tiefendurchlauf durch und<br />
     *  Gibt die Inhalte der Knoten aufsteigend sortiert aus.
     * </p>
     */
    public void show()
    {
        //inOrder-Tiefendurchlauf
        if ( !isEmpty() ) 
        {
            leftChild.show();//Bearbeite den linken Teilbaum rekursiv
            System.out.println( content );//Bearbeite die Wurzel
            rightChild.show();//Bearbeite den rechten Teilbaum rekursiv
        }
    } 

    /**
     * <p>
     *  - Die Methode 'listBasedIterator()' liefert einen zum Inhaltstyp 'T' passenden Iterator.<br />
     *  - new DoublyLinkedList<T>() liefert ein 'DoublyLinkedList'-Objekt, das in seinen Elementen Inhalte des Typs 'T' verwalten kann.<br />
     *  - Der Aufruf 'toList()' tr&auml;gt alle Inhalte des Baumes in die Liste in aufsteigend sortierter Folge (mithilfe eines inOrder-Tiefendurchlaufs) ein<br />
     *  - Die Liste Stellt dann ein 'Iterator'-Objekt zur verf&uuml;gung<br />
     *      -> Da die Inhalte des Baumes durch das eintragen in die Liste nun &uuml;ber die Liste erreichbar sind, ist der Iterator f&uuml;r den Baum, der Iterator der Liste. 
     * </p>
     */
    public Iterator<T> listBasedIterator()
    {
        DoublyLinkedList<T> list = new DoublyLinkedList<T>();
        toList( list );
        return list.iterator();//Die Referenz 'list' verweist auf ein 'DoublyLinkedList'-Objekt -> Jedes 'DoublyLinkedList'-Objekt kann 'Iterator'-Objekte bereitstellen.
                               //Da alle Elemente, die über den Baum erreicht werden können, auch in der Liste enthalten sind, stellt das von dem 'DoublyLinkedList'-Objekt bereitgestellte 
                               //'Iterator'-Objekt ja auch einen 'Iterator' für den Baum zur Verfügung
    }

    /**
     * <p>
     *  - Die Methode 'toList' besitzt einen Parameter der generische Klasse 'DoublyLinkedList'.<br />
     *  - Durch &Uuml;bergabe von 'T' als Typargument werden nur Listen-Objekte als Argumente von 'toList' akzeptiert, bei denen der Typ ihrer Inhalte passt. <br />
     *  - Die Methode 'toList' durchl&auml;uft den Baum in 'InOrder'-Reihenfolge<br />
     *  - Die Methode 'toList' wird jetzt genutzt, um einen einfachen Iterator &uuml;ber den Baum bereitzustellen. 
     * </p>
     */
    private void toList( DoublyLinkedList<T> list )
    {
        if ( !isEmpty() ) 
        {
            //inOrder-Tiefendurchlauf
            leftChild.toList( list );
            list.add( content );//Fügt den Wurzelknoten hinten in der Liste ein.
            rightChild.toList( list );
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
     *  <h2>Dokumentation der inneren privaten instanz Klasse StackBasedIterator</h2> (Instanzklasse bedeutet, dass die Klasse nicht statisch ist)
     *  <p>
     *      Diese Klasse erweitert die generische Klasse 'Iterator<T>'<br />
     *      Der 'StackBasedIterator' Iteriert durch den bin&auml;ren Suchbaum (in inOrder-Reihenfolge), indem die noch nicht bearbeitetten Knoten auf einem Stapel abgelegt werden.<br />
     *      Wenn ein linker Teilbaum bearbeitet wurde, kann so zur&uuml;ck zum Vorgänger Knoten gesprungen werden, der dann bearbeitet wird und danach den selben Prozess f&uum;r seinen<br />
     *      rechten Teilbaum wiederholt.
     *  </p>
     *  <p>
     *      Der Typparameter 'T' ist in der inneren Klasse 'StackBasedIterator' sichtbar -> Der 'StackBasedIterator' ist ein Iterator &uuml;ber dem Typ 'T'
     *  </p>
     * </center>
     */
    private class StackBasedIterator extends Iterator<T>
    {
        private Stack<BinarySearchTree<T>> nodes;

        /**
         * <p>
         *  Der Konstruktor legt einen neuen leeren Stapel an und<br />
         * </p>
         * <p>
         *  sucht den &auml;u&szlig;ersten linken Knoten des Baums (Gem&auml;&szlig; dem InOrder-Tiefendurchlauf, startet hier die Iteration).<br />
         *  Alle Knoten die auf dem Weg zum &auml;u&szlig;ersten linken Knoten besucht wurden, werden auf den Stapel gelegt.<br />
         *      -> Das erledigt die 'descendLeftAndPush' Methode
         * </p>
         */
        public StackBasedIterator()
        {
            nodes = new Stack<BinarySearchTree<T>>(); //Da auf dem Stapel die Knoten eines binären Suchbaums abgelegt werden sollen, muss als Typargument 'BinarySearchTree<T>' angegeben werden
                                                      //-> Hier liegt eine Schachtelung von generischen Klassen vor
            descendLeftAndPush( BinarySearchTree.this );//Innerhalb der inneren Klasse 'StackBasedIterator' bezeichnet 'this' das den Zugriff umgebende Objekt der Klasse 'StackBasedIterator'.
                                                        //Die Konstruktion 'Klassenname.this' ermöglicht den Zugriff von einem Objekt einer inneren Klasse aus zu dem zugehörigen Objekt der umgebenden Klasse
                                                        //->Ein einfaches 'this' würde sich auf das Objekt der Klasse 'StackBasedIterator' beziehen
                                                        //=> Dieses Problem können wir mit dem Punktoperator beheben: Klassenname.this
        }

        /**
         * <p>
         *  Der oberste Knoten im Stapel wird zur&uuml;ckgegeben und<br />
         * </p>
         * <p>
         *  Der &auml;u&szlig;erste linke Knoten im rechten Teilbaum wird gesucht<br />
         *  Alle Knoten die auf dem Weg zum &auml;u&szlig;ersten linken Knoten besucht wurden, werden auf den Stapel gelegt.
         *      -> Das erledigt die 'descendLeftAndPush' Methode
         * </p>
         */
        public T next() 
        {
            if ( hasNext() ) {
                T content = nodes.peek().getContent();//Speichert den Inhalt des aktuellen Knotens in der Hilfsvariable 'content'
                descendLeftAndPush( nodes.pop().rightChild );//Suche den äußersten linken Knoten im rechten Teilbaum. -> Das rechte und alle Knoten auf dem Pfad zum äußersten linken Knoten werden auf den Stapel gelegt<br />
                                                             //Beachte: 'pop()' entfernt zwar die Referenz auf den aktuellen Knoten aus der Liste, gibt diesen Knoten aber auch zurück
                                                             //-> Daher können wir zum rechten Kind gehen
                return content;
            } else {
                throw new IllegalStateException();
            }
        }
        
        /**
         * <p>
         *  Liefert true, wenn es noch ein Knoten auf dem Stapel gibt, dessen Inhalt noch nicht &uuml;ber die 'next()'-Methode zur&uuml;ckgegeben wurde.<br />
         *  Wurde der binäre Suchbaum bereits vollst&auml;ndig durchlaufen, liefert der Aufruf von hasNext() false zur&uuml;ck
         * </p>
         */
        public boolean hasNext()
        {
            return !nodes.isEmpty();//Solange noch Knoten auf dem 'Stack' liegen, kann weiter iteriert werden.
        } 

        /**
         * <p>
         *  Sucht den äußersten linken Knoten des (Teil-)Baums und legt alle auf dem Weg besuchten Knoten auf den Stack
         * </p>
         * <p>
         *  Mit anderen Worten: Alle Knoten, die auf dem Pfad zum &auml;u&szlig;ersten linken Knoten liegen, werden auf den Stack 'nodes' abgelegt.<br />
         *  Durch das 'lifo'-Prinzip des Stapels k&ouml;nnen die Knoten also leicht in umgekehrter Reihenfolge zur Bearbeitung aufgerufen werden.
         * </p>
         */
        private void descendLeftAndPush( BinarySearchTree<T> root )//Die Wurzel unseres (Teil-)Baumes wird als Argument übergeben
        {
            BinarySearchTree<T> current = root;
            while ( !current.isEmpty() )//Die schleife wird abgebrochen, wenn der Baum leer ist, also wenn wir am äußersten linken Knoten angekommen sind.
            {
                nodes.push( current );//Der aktuelle Knoten (der sich ja auf dem Weg zum äußersten Linken befindet), wird auf dem Stapel abgelegt.
                current = current.leftChild;//Gehe zum linken Teilbaum
            }
        }

    }
    
    /**
     * <p>
     *  - Die Klasse 'Iterator' besitzt jetzt einen Typparameter 'T'.<br />
     *  - Ein 'Iterator', der die Klasse 'Iterator' spezialisiert, liefert also durch seinen 'next'-Methode immer zu 'T' kompatible Objekte<br />
     * </p>
     */
    public Iterator<T> iterator()
    {
        return new StackBasedIterator();
    }
}
