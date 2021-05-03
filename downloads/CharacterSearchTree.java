/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse CharacterSearchTree</h2>
 *  <p>
 *      - Die Klasse 'CharacterSearchTree hat viele Gemeinsamkeiten mit der Klasse 'HuffmanTree': Beide Klassen dienen dem Aufbau bin&auml;rer B&auml;ume<br />
 *      - Die Klassen enthalten Attribute f&uuml;r die gleichen Aufgaben: 'leftChild' und 'rightChild' f&uuml;r die Konstruktion des Baums, 'content' f&uuml;r den Inhalt eines Knotens<br />
 *      - Die Konstruktionsregeln des bin&auml;ren Suchbaums dr&uuml;cken sich nicht in seinen Atrributen aus. Die Konstruktionsregeln werden von Methoden sichergestellt, die einen Baum erzeugen und diesen verwalten.<br />
 *          -> Der 'CharacterSearchTree' wird ganz anders aufgebaut als der 'HuffmanTree'<br />
 *              => Der 'CharacterSearchTree' wird &uuml;ber Methoden aufgebaut, w&auml;hrend der 'HuffmanTree' &uuml;ber seine Konstruktoren aufgebaut wird.
 *  </p>
 * </center>
 */
public class CharacterSearchTree
{

    // Vorgaben

    private HuffmanTriple content;//Attribute f�r Zeichen, H�ufigkeiten (und Kodierung)
    private CharacterSearchTree leftChild, rightChild;//Attribute f�r die Baumstruktur
    /**
     * <h1>Konstruktor - CharacterSearchTree()</h1>
     * <p>
     *  Der Konstruktor ohne Parameter, legt einen leeren Baum an
     * </p>
     */
    public CharacterSearchTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * <h1>HuffmanTriple getContent()</h1>
     * <p>
     *  getContent liefert den Inhalt des aktuellen Knotens, wenn dieser nicht leer ist.<br />
     *  Wird versucht auf einen leeren Knoten zuzugreifen, dann wird eine Ausnahme geworfen.
     * </p>
     */
    public HuffmanTriple getContent()
    {
        if ( !isEmpty() )
        {
            return content;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <h1>boolean isEmpty()</h1>
     * <p>
     *  isEmpty() gibt true zur�ck, wenn der aktuelle Knoten leer ist.<br />
     *  ist er nicht leer, wird false zur�ck gegeben.
     * </p>
     */
    public boolean isEmpty() 
    {
        return content == null;
    }

    /**
     * <h1>boolean isLeaf()</h1>
     * <p>
     *  Wenn der aktuelle Knoten ein Blatt ist, also nicht leer ist und sein linker & rechter Teilbaum leer sind,<br />
     *  wird true zur�ckgegeben.<br />
     *  Sonst wird false zur�ckgegeben.
     * </p>
     */
    public boolean isLeaf() 
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    /**
     * <h1>void add( char t )</h1>
     * <h2>Das Einf&uuml;gen eines Zeichens in den Baum erfordert</h2>
     * <p>
     *  - entweder das Anlegen eines neuen Knotens f&uuml;r dieses Zeichen<br />
     *  - oder das Erh&ouml;hen der H&auml;ufigkeit f&uuml;r das schon vorhandene Zeichen:
     * </p>
     */
    public void add( char t )
    {
        //Rekursive Methode
        if ( isEmpty() ) //Abbruchbedingung: Der (Teil-)Baum leer ist 
        {
            //Erzeugt einen neues Blatt mit dem Inhalt 't' als token und zwei leeren Teilb�umen
            //-> Der leere Baum wird in ein Blatt umgewandelt, indem
            //  => ein passendes 'HuffmanTriple'-Objekt angelegt und in dem aktuellen Knoten im 'content' abgelegt wird,
            //  => die Referenzen f�r die nachfolgenden Teilb�ume auf leere B�ume verweisen.
            content = new HuffmanTriple( t );
            leftChild = new CharacterSearchTree();
            rightChild = new CharacterSearchTree();
        }
        else
        {
            //suche, ob bereits ein Knoten mit Inhalt 't' existiert, falls dieser gefunden wurde, erh�he dessen H�ufigkeit, oder, 
            //falls die Suche endg�ltig erfolglos war, f�ge einen neuen Knoten mit dem Inhalt 't' ein -> Wenn die Suche erfolglos war, landen wir in einem leeren Baum.
            if ( content.getToken() > t )//Wenn das Zeichen 't', dass eingef�gt werden soll Lexikografisch kleiner als das token im content des aktuellen Knotens ist, 
            {
                leftChild.add( t );//Selbstaufruf/Rekursion: Suche im linken Teilbaum weiter nach einem Knoten, dass als 'content' ein 'HuffmanTripple'-Objekt enth�lt, 
                //dessen 'token' mit 't' �bereinstimmt.
                //Reduktion: Teilb�ume sind immer kleiner als der ganze Baum
            }
            else if ( content.getToken() < t )//Sonst pr�fe, ob das als Argument �bergebene Zeichen 't' gr��er ist,
            {
                rightChild.add( t );//Selbstaufruf/Rekursion: Suche im rechten Teilbaum weiter nach einem Knoten, dass als 'content' ein 'HuffmanTripple'-Objekt enth�lt, 
                //dessen 'token' mit 't' �bereinstimmt.
                //Reduktion: Teilb�ume sind immer kleiner als der ganze Baum
            }
            else //wenn das als Argument �bergebene Zeichen 't' nicht gr��er und gleichzeitig nicht kleiner ist,
            //dann muss es Lexikografisch genau gleich sein (-> Logische Schlussfolgerung)
            {
                content.incrementQuantity();//Abbruchbedingung: Das als Argument �bergebene Zeichen ist nicht neu (es kam also bereits mindestens einmal vor), dann
                //wird die H�ufigkeit mithilfe der Methode 'incrementQuantity()' aus der Klasse 'HuffmanTripple' erh�ht.
            }
        }
    }

    /**
     * <h1>void iterativeAdd( char t )</h1>
     * <p>
     *  Da die Methode 'add()' immer einem bestimmten, durch die Ergebnisse der Vergleiche vorgegebenen Pfad von Knoten durch einen Teilbaum folgt, kann sie auch iterativ, also durch die Nutzung einer Schleife formuliert werden.
     * </p>
     */
    public void iterativeAdd( char t )
    {
        CharacterSearchTree current = this;//'this' ist eine Referenz auf das ausf�hrende Objekt -> Das ist notwendig, da wir sonst die Wurzel nicht �berpr�fen k�nnten
        while ( !current.isEmpty() && current.content.getToken() != t )//Wir suchen so lange nach 't', bis wir es gefunden haben, oder einen leeren Baum erreichen
                                   //Der '&&'-Operator sorgt daf�r, dass wir die Zweite Bedingung nur pr�fen, wenn die erste wahr ist.
                                   // -> Wir verhindern den ung�ltigen Zugriff auf ein 'content' Attribut eines Null-Objektes
        {
            //gehe zu der Position, an der der Knoten des Baumes sein m�sste
            if ( current.content.getToken() > t )
            {
                current = current.leftChild;
            }
            else
            {
                current = current.rightChild;
            }
        }
        if ( current.isEmpty() ) //Wenn der Knoten an dem das Zeichen 't' sein m�sste leer ist, 
        {
            //Erstelle ein neues 'HuffmanTriple'-Objekt und f�ge es als 'content' in den aktuellen Knoten ein
            current.content = new HuffmanTriple( t );
            //Erstelle f�r die erhaltung der Baumstruktur ein leeres linkes und ein leeres rechtes Kind
            current.leftChild = new CharacterSearchTree();
            current.rightChild = new CharacterSearchTree();
        }
        else //wenn das Zeichen 't' gefunden wurde, 
        {
            current.content.incrementQuantity();//Inkrementiere die H�ufigkeit des Zeichens
        }
    }

    /**
     * <h1>String getCode( char t )</h1>
     * <p>
     *  Sucht nach dem Zeichen im 
     * </p>
     */
    public String getCode( char t )
    {
        //Rekursive Methode
        if ( !isEmpty() ) //Wenn der Baum nicht leer ist, wird nach dem Zeichen gesucht
        {
            //suche nach dem Zeichen
            if ( content.getToken() > t )
            {
                return leftChild.getCode( t );
            }
            else if ( content.getToken() < t )
            {
                return rightChild.getCode( t );
            }
            else//Abbruchbedingung: Wenn das Zeichen gefunden wurde
            {
                return content.getCode();//Besorg den Code mithilfe der 'getCode()'-Methode aus der Klasse 'HuffmanTriple'
            }
        }
        else //Wenn der Baum leer ist, oder das Zeichen nicht gefunden werden konnte,
        {
            throw new IllegalStateException();//Wird eine IllegalStateException() geworfen.
        }
    }

    /**
     * <h1>int size()</h1>
     * <p>
     *  Z&auml;hlt die Anzahl der unterschiedlichen Zeichen ('token's), der 'HuffmanTripple'-Objekte, die sich als 'content' in unserem Baum befinden<br />
     *      -> Das Feststellen der Anzahl der abgelegten, unterschiedlichen Zeichen l&auml;sst sich ebenfalls an die nachfolgenden Teilb&auml;ume &uuml;bertragen und daher rekursiv formulieren:<br />
     *          => Jeder nicht-leere Teilbaum besteht aus der Anzahl der Zeichen, die in den Teilb�umen Abgelegt sind (<span style="color:red;">leftChild.size() + rightChild.size()</span>)<br />
     *          => und aus dem einen Zeichen in seiner Wurzel (<span style="color:red">+1</span>)
     * </p>
     */
    public int size() 
    {
        //Rekursive Methode
        if ( isEmpty() )//Abbruchbedingung: Der (Teil-)Baum ist leer
        {
            return 0;//Ein leerer (Teil-)Baum enth�lt kein Zeichen -> Es wird 0 zur�ckgegeben
        }
        else
        {
            return 1 + leftChild.size() + rightChild.size();//F�r den aktuellen Knoten (Stefan nennt es nicht Knoten, sondern (Teil)-Baum) wird ein Zeichen hochgez�hlt (+1)
            //Selbstaufruf/Rekursion: Die Anzahl der unterschiedlichen Zeichen im rechten und linken Teilbaum wird gez�hlt und auf den aktuellen Knoten aufsummiert
            //Reduktion: Teilb�ume sind immer kleiner als der gesamte Baum
        }       
    }

    /**
     * <h1>void show()</h1>
     * <p>
     *  Die Ausfgabe der abgelegten, unterschiedlichen Zeicheen und ihrer H&auml;ufigkeiten l&auml;sst sich ebenfalls an die nachfolgenden Teilb&auml;ume &uuml;bertragen und daher rekursiv formulieren
     * </p>
     * <p>
     *  Gibt den Baum in einem in-order Tiefendurchlauf aus. (-> InOrder-Durchlauf: Die Wurzel wird zwischen (, also in) den beiden Teilb&auml;umen bearbeitet)<br />
     *  <!--Notiz (Fr, 08.01.2021) (Quelle: Folie: 893)-->Da der Tiefendurchlauf in-Order statfindet, ist die Liste anschlie�end aufsteigend sortiert.
     * </p>
     */
    public void show()
    {
        //Rekursive Methode 
        if ( !isEmpty() ) //Abbruchbedingung: Aufruf f�r den leeren Baum -> Der leere Baum enth�lt kein Zeichen
        {
            leftChild.show();//Rekursion: Ausgabe des linken Teilbaums durch rekursiven Aufruf der Methode 'show' | Reduktion: Teilb�ume sind immer kleiner als der ganze Baum
            System.out.println( content.toString() );//Ausgabe des Inhalts der Wurzel
            rightChild.show();//Rekursion: Ausgabe des rechten Teilbaums durch rekursiven Aufruf der Methode 'show' | Reduktion: Teilb�ume sind immer kleiner als der ganze Baum
        }
    }

    /**
     * <h1>HuffmanTriple[] toArray()</h1>
     * <p>
     *  - Die &Uuml;bergabe der abgelegten, unterschiedlichen Zeichen und ihrer H&auml;ufigkeiten an ein Feld wird als Eingabe f�r Klasse 'HuffmanCoding' ben&ouml;tigt.<br />
     *  - Das Erzeugen eines solchen Feldes l�sst sich ebenfalls &uuml;ber einen InOrder-Durchlauf rekursiv formulieren. <br />
     *  - Die Methode 'toArray()' bereitet den InOrder-Durchlauf vor, indem ein passendes Feld f�r 'HuffmanTriple'-Objekte bereitgestellt wird.<br />
     *  - Die Anzahl der ben�tigten Elemente wird durch den Aufruf der Methode 'size()' ermittelt.
     * </p>
     * <h2>Wie k&ouml;nnen wir den CharacterSearchTree benutzen, um die Eingaben f�r unseren Huffman Algorithmus zu schaffen?</h2>
     * <p>
     *  -> Wir m�chten ein Feld von 'HuffmanTriple'-Objekten haben<br />
     *  -> Der Inhalt unserer Knoten sind 'HuffmanTriple'-Objekte <br />
     *  => Wir m�ssen unsere Knoten in ein Feld ablegen.
     * </p>
     */
    public HuffmanTriple[] toArray()
    {
        //Rekursive Methode
        if ( !isEmpty() ) //Abbruchbedingung: Der (Tail-)Baum ist leer
        {
            HuffmanTriple[] collector = new HuffmanTriple[size()];//Erzeugt ein Feld mit der richtigen gr��e, sodass alle Knoten in das Feld passen
            toArray( collector, 0 );//Macht den ersten Aufruf f�r die Private Methode 'private int toArray( HuffmanTriple[] collector, int index )' und beginnt beim Index 0
            return collector;
        }
        return new HuffmanTriple[0];
    }

    /**
     * <h1>int toArray( HuffmanTriple[] collector, int index )</h1>
     * <p>
     *  - Die Methode 'toArray( HuffmanTriple[] collector, int index )' f&uuml;hrt den InOrder-Durchlauf durch, indem die in den Wurzeln der Teilb&auml;ume gefundenen 'HuffmanTriple'-Objekte in das Feld eingef&uuml;gt werden. <br />
     *  - Die Position f&uuml;r den n&auml;chsten Zugriff auf das Feld wird im Parameter index &uuml;bergeben<br />
     *  - Der Ablauf der Rekursion wird &uuml;ber die besuchten Teilb&auml;ume und nicht &uuml;ber die beiden Parameter der Methode gesteuert.
     * </p>
     */
    private int toArray( HuffmanTriple[] collector, int index ) 
    {
        if ( !isEmpty() )
        {
            index = leftChild.toArray( collector, index );
            collector[index] = content;
            index = rightChild.toArray( collector, index + 1 );
        }
        return index;
    }  
}