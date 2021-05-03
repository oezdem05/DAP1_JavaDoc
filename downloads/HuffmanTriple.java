/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse HuffmanTriple</h2>
 *  <p>
 *      Die Klasse HuffmanTripple enth&auml;lt die f�r die Kompression notwendigen Attribute f�r das Zeichen, die H�ufigkeit des Zeichens und die Kodierung des Zeichens
 *  </p>
 * </center>
 */
public class HuffmanTriple
{
    private char token; //Enth�lt das Zeichen
    private String code; //Enth�lt die Kodierung
    private int quantity; //Speichert wie h�ufig kein bestimmtes Zeichen vorkommt.

    /**
     * Wird der Konstruktor ohne Parameter aufgerufen, wird ein HuffmanTripple Objekt erstellt, dass ein leeres char (token = ' ') erstellt, welches nicht einmal vorkommt (quantity = 0)
     */
    public HuffmanTriple() 
    {
        this( ' ', 0 );
    }
    
    /**
     * Wird der Konstruktor mit einem Parameter vom Typ char aufgerufen, wird ein neues HuffmanTripple Objekt erstellt, welches offensichtlich selbst einmal vorkommt (quantity = 1).
     */
    public HuffmanTriple( char t ) 
    {
        this( t, 1 );
    }
    
    /**
     * <h1>HuffmanTriple( char t, int q )</h1>
     * <p>
     *  - Die Zeichen (char t) werden zun&auml;chst mit ihrer H�ufigkeit (int q) bestimmt.<br />
     *  - Die Kodierung wird sp&auml;ter ermittelt und kann daher nicht im Konstruktor gesetzt werden -> Zu beginn ist code leer (ein leerer String: "")
     * </p>
     */
    public HuffmanTriple( char t, int q ) 
    {
        token = t;
        code = "";
        quantity = q;
    }
    
    /**
     * Gib den token zur�ck.
     */
    public char getToken()
    {
        return token;
    }
    
    /**
     * Gibt den code zur�ck.
     */
    public String getCode()
    {
        return code;
    }
    
    /**
     * Ver�ndert den code auf den �bergebenen String
     */
    public void setCode( String c )
    {
        code = c;
    }
    
    /**
     * Gibt zur�ck wie h�ufig ein zeichen vorkommt.
     */
    public int getQuantity()
    {
        return quantity;
    }
    
    /**
     * Erh�ht die H�ufigkeit eines Zeichens, indem das Attribut 'quantity' inkrementiert wird.
     */
    public void incrementQuantity()
    {
        quantity++;
    }
    
    /**
     * Vergleicht das aktuelle Objekt mit einem anderen HuffmanTripple Objekt (other)<br />
     * Kommt das Zeichen des aktuellen Objektes h�ufiger vor als das Zeichen von other, wird eine Zahl gr��er als 0 zur�ck gegeben. -> quantity > other.quantity<br />
     * Kommen die Zeichen gleich oft vor, wird 0 zur�ck gegeben. -> quantity = other.quantity<br />
     * Kommt das Zeichen des aktuellen Objektes weniger h�ufiger vor als das Zeichen von other, wird eine Zahl kleiner als 0 zur�ck gegeben. -> quantity < other.quantity
     */
    public int compareTo( HuffmanTriple other)
    {
        return quantity - other.quantity;
    }
    
    /**
     * "Konvertiert" das aktuelle HuffmanTripple Objekt in einen angemessenen String.
     */
    public String toString()
    {
        return "token (quantity: " + quantity + "): " + token + " -> code: " + code ;
    }
}
