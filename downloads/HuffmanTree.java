/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse HuffmanTree</h2>
 *  <p>
 *      Die Klasse HuffmanTree dient der Repräsentation eines aus Knoten bestehenden Baums mit (->  Mehrere Knoten werden zu einem Baum zusammen geflochten)<br />
 *          - einem Attribut für ein HuffmanTripple <br />
 *          - zwei Attributen für den linken und rechten Teilbaum zum Aufbau der Baumstruktur,<br />
 *          - geeigneten Konstruktoren, (Plural: Mehrere Konstruktoren)<br />
 *          - einer Methode zur Generierung der komprimierenden Kodierung f&uuml;r den Baum<br />
 *          - einer Methode zur Ausgabe der Kodierung eines Baums.
 *  </p>
 *  <h3>
 *      &Uuml;berlegungen zur Struktur von Huffman-B&auml;umen
 *  </h3>
 *  <p>
 *      - Jedes einzelne Zeichen bildet zunächst einen Baum mit genau einem Knoten: Hierf&uuml;r wird ein geeigneter Konstruktor ben&ouml;tigt -> public HuffmanTree( HuffmanTriple t ) <br />
 *      - Jeweils zwei Bäume werden unter einer neuen Wurzel zusammengesetzt: Auch hierf&uuml;r wir ein geeigneter Konstruktor ben&ouml;tigt.<br />
 *      - Jeder innere Knoten eines Huffman-Baums besitzt immer genau zwei Nachfolgeknoten: Diese Eigenschaft kann beim bearbeiten eines Baums ausgenutzt werden.<br />
 *      - Die Bl&auml;tter eines Huffman-Baums enthalten die zu kodierenden Zeichen
 *  <p>
 * </center>
 */
public class HuffmanTree
{ 
    private HuffmanTriple content; //Ein 'HuffmanTripple'-Objekt hat ein token, eine quantity und ein code als Attribute
    private HuffmanTree leftChild, rightChild;//Baumstruktur | Die Deklaration der Klasse nimmt bezug auf sich selbst -> Daher ist die Klasse HuffmanTree eine rekursiv definierte Datenstruktur.
    // - Jedes Huffman-Tree-Objekt enthält:
    //    -> eine Referenz auf ein HuffmanTripple-Objekt,
    //    -> eine Referenz auf ein HuffmanTree-Objekt, das den linken Nachfolger bildet (leftChild),
    //    -> eine Referenz auf ein HuffmanTree-Objekt, das den rechten Nachfolger bildet (rightChild)
    // - Ein leerer Baum ist dadurch gekennzeichnet, dass content auf null verweist.
    // - Ein Blatt ist dadurch gekennzeichnet, dass leftChild und rightChild auf leere Bäume verweisen.
    // - Die Datenstruktur hat keine vorgegebene Größe, sondern kann dadurch das Setzen der Referenzen auf neu erzeugte 'HuffmanTree'-Objekte vergrößert werden. (-> Im Gegensatz zu einem Feld ist der Platz in nicht begrenzt)

    // empty tree
    /**
     * <h1>Konstruktor - HuffmanTree()</h1>
     * <p>
     *  Erzeugt einen <em>leeren Baum</em>
     * </p>
     */
    public HuffmanTree() 
    {
        content = null;
        leftChild = null;
        rightChild = null;
    }

    // single node tree 
    /**
     * <h1>Konstruktor - HuffmanTree( HuffmanTriple t )</h1>
     * <p>
     *  Erzeugt einen Knoten Inhalt -> content wird auf das &uuml;bergebene Argument gesetzt (=> content = t) 
     * </p>
     */
    public HuffmanTree( HuffmanTriple t ) 
    {
        content = t;
        leftChild = new HuffmanTree();
        rightChild = new HuffmanTree();
    }

    // new root for multiple nodes tree 
    /**
     * <h1>Konstruktor - HuffmanTree( HuffmanTree lc, HuffmanTree rc )</h1>
     * <p>
     *  Erzeugt eine neue wurzel. <br />
     *  content = Der content hat kein Zeichen, und als quantity die Summe der quantity Werte, der beiden Kinder.<br />
     *  leftChild ist der Inhalt des HuffmanTree Objektes, dessen Content einen quantity Wert hat, der kleiner ist, als der vom rechten Teilbaum<br />
     *  rightChild ist der Inhalt des HuffmanTree Objektes, dessen Content einen quantity Wert hat, der gr&ouml;&szlig;er ist, als der vom linken Teilbaum
     * </p>
     */
    public HuffmanTree( HuffmanTree lc, HuffmanTree rc ) 
    {
        content = new HuffmanTriple ( ' ', lc.getContent().getQuantity() + rc.getContent().getQuantity() );//Hier steht die Summe der Häufigkeiten beider Teilbäume
        leftChild = lc;
        rightChild = rc;
    }

    /**
     * <h1>boolean isEmpty()</h1>
     * <p>
     *  Identifiziert einen lehren Baum.<br />
     *  Falls der (Teil-)Baum leer, also der content, des aktuellen Knotens gleich null ist, wird true zur&uuml;ckgegeben.<br />
     *  Sonst wird false zur&uuml;ckgegeben
     * </p>
     */
    public boolean isEmpty()
    {
        return content == null;
    }

    /**
     * <h1>boolean isLeaf()</h1>
     * <p>
     *  Liefert true zur&uuml;ck, wenn der aktuelle Knoten ein Blatt ist, also content nicht null ist und die Nachfolger des Knotens leer sind<br />
     *  Wenn der aktuelle Knoten nicht die Bedingungen eines Blattes (1. content != null, 2. content von leftChild == null, 3. content von rightChild == null)
     * </p>
     */
    public boolean isLeaf()
    {
        return !isEmpty() && leftChild.isEmpty() && rightChild.isEmpty();
    }

    /**
     * <h1>HuffmanTriple getContent()</h1>
     * <p>
     *  Wenn der aktuelle Knoten nicht leer ist, wird das content (also das 'HuffmanTripple'-Objekt, dass der Inhalt 'content'-Variable ist) zur&uuml;ckgegeben.<br />
     *  Ist der aktuelle Knoten leer, wird eine Ausnahme geworfen, falls die Ausnahme nicht in einem try-catch-Block gefangen wird, bricht das Programm ab.
     * </p>
     */
    public HuffmanTriple getContent() 
    {
        if ( !isEmpty() )
        {
            return content;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * <h1>int compareTo ( HuffmanTree other )</h1>
     * <p>
     *  F&uuml;hrt einen Vergleich auf der Grundlage der 'compareTo'-Methode aus der 'HuffmanTripple'-Klasse durch:<br />
     * </p>
     * <p>
     *  Auszug aus der 'compareTo'-Methode aus der Klasse HuffmanTripple<br />
     *  Vergleicht das aktuelle Objekt mit einem anderen HuffmanTripple Objekt (other)<br />
     *  Kommt das Zeichen des aktuellen Objektes häufiger vor als das Zeichen von other, wird eine Zahl größer als 0 zurück gegeben. -> quantity > other.quantity<br />
     *  Kommen die Zeichen gleich oft vor, wird 0 zurück gegeben. -> quantity = other.quantity<br />
     *  Kommt das Zeichen des aktuellen Objektes weniger häufiger vor als das Zeichen von other, wird eine Zahl kleiner als 0 zurück gegeben. -> quantity < other.quantity
     * </p>
     */
    public int compareTo ( HuffmanTree other ) 
    {
        if ( !isEmpty() && !other.isEmpty() )
        {
            return content.compareTo( other.content );
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * <h1>void generateCodes()</h1>
     * <p>
     *  Erstellt die f&uuml;r die 'HuffmanTripple'-Objekte<br />
     *  (-> Dies ist für die Bl&auml;tter, deren content (='HuffmanTripple'-Objekt) ja auch die Tokens enth&auml;lt, nat&uuml;rlich am interessantesten)
     * </p>
     * <p>
     *  Die Methode 'generateCodes' arbeitet <em>rekursiv</em> auf dem Baum und betrachtet immer zuerst den linken und danach den rechten nachfolgenden Teilbaum.
     * </p>
     */
    public void generateCodes()
    {
        //Rekursive Methode
        if ( !isEmpty() && !isLeaf() ) //Abbruchkriterium: Der aktuelle Knoten ist die Wurzel eines leeren (Teil-)Baumes oder ein Blatt
        {
            leftChild.content.setCode( content.getCode() + "0" );//Wenn wir zum linken kind gehen, hängen wir an den codierungs-Sting des aktuellen Knotens eine 0 und schreiben den neuen Wert für die Code Variable des HuffmanTripple Objektes in den aktuellen Knoten (also nicht in das linke Kind)
            leftChild.generateCodes();//Selbstaufruf: Generiere die Codes für den linken Teilbaum | Reduktion: Teilbäume sind immer kleiner als der ganze Baum
            rightChild.content.setCode( content.getCode() + "1" );//Wenn wir zum rechten kind gehen, hängen wir an den codierungs-Sting des aktuellen Knotens eine 0 und schreiben den neuen Wert für die Code Variable des HuffmanTripple Objektes in den aktuellen Knoten (also nicht in das rechte Kind)
            rightChild.generateCodes();//Selbstaufruf: Generiere die Codes für den rechten Teilbaum | Reduktion: Teilbäume sind immer kleiner als der ganze Baum

            //Am Ende stehen in den Code Attributen der contents (-> 'HuffmanTripple'-Objekte) der Blätter die Codes zum zugehörigen Zeichen drinnen
            //(=> Und eben nicht in deren nachfolgern, daher wird der code auch immer in den aktuellen Knoten geschrieben)
        }
    }

    /**
     * <h1>void showCodes()</h1>
     * <p>
     *  Gibt die Kodierung f&uuml;r die Bl&auml;tter aus
     * </p>
     */
    public void showCodes()
    {
        //Rekursive Methode
        if ( !isEmpty() )//Rekursionsabbruch: Leerer Baum
        {
            if ( isLeaf() )//Diese Bedingung muss eingehalten werden, da die Codes nur in den Blättern stehen. | Rekursionsabbruch: Blatt
                           //Erinnerung: Kodierungen von Zeichen stehen nur in den Blättern des Huffman-Baums
            {
                System.out.println( content.toString() );
            }
            else
            {
                leftChild.showCodes();//Selbstaufruf: code für den linken Teilbaum ausgeben | Reduktionsschritt: Teilbäume sind immer kleiner als der gesamte Baum.
                rightChild.showCodes();//Selbstaufruf: code für den rechten Teilbaum ausgeben | Reduktionsschritt: Teilbäume sind immer kleiner als der gesamte Baum.
            }
        }
    }

}
