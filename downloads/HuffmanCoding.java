/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse HuffmanCoding</h2>
 *  <p>
 *      Die Klasse HuffmanCoding arbietet auf der Grundlage der Klasse HuffmanTree mit<br />
 *          - einem Feld zum Speichern der schrittweise entstehenden B&auml;me,<br />
 *          - einem geeigneten Konstruktor,<br />
 *          - einer Methode zum Sortieren der B&auml;me,<br />
 *          - einer Methode zum Aufbau des Huffman-Baums<br />
 *          - einer Methode zur Ausgabe der kodierung des Huffman-Baums.
 *  </p>
 *  <h3>Aufgabe der Klasse</h3>
 *  <p>
 *      1. Der Baum soll korrekt aufgebaut werden<br />
 *      2. Die Kodierung f&uuml;r die Zeichen soll erzeugt werden<br/>
 *      3. Der Baum soll durchlaufen werden, um die Kodierung anzuzeigen.
 *  </p>
 * </center>
 */
public class HuffmanCoding
{ 
    private HuffmanTree[] trees;//Das Feld 'trees' enthält due Wurzeln der Teilbäume

    /**
     * <h1>Konstruktor - HuffmanCoding( HuffmanTriple[] input )</h1>
     * <p>
     *  1. legt einzelne Zeichen als B&auml;ume an,<br />
     *  2. sortiert die B&auml;ume nach der Gr&ouml;&szlig;enangabe in ihrem Wurzelknoten aufsteigend<br />
     *  3. fasst die beiden kleinsten B&auml;ume unter einem neuen Wurzelknoten zusammen<br />
     *  4. Wiederholt Schritt 1-3 solange, bis nur noch ein Baum vorliegt, und<br />
     *  5. Erzeugt abschlie&szlig;end die Kodierung.
     * </p>
     */
    public HuffmanCoding( HuffmanTriple[] input )
    {
        // compression only if different tokens appear
        if ( input.length > 1 )//Es muss mehr als ein Zeichen geben, sonst lohnt sich die Datenkompression nicht
        {
            initializeTrees( input );//Bäume anlegen -> Nimmt die HuffmanTree Objekte & macht daraus den Inhalt des Feldes 'trees' ('trees' ist ein Feld mit HuffmanTree Objekten)
            buildTree();//Bäume zusammenfassen -> Fügt die im Feld 'trees' enthaltenen Bäume zu einem großen Baum zusammen
            trees[trees.length-1].generateCodes();//kodierung eintragen
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    /**
     * <h1>void initializeTrees( HuffmanTriple[] input )</h1>
     * <p>
     *  - Es werden die Elemente eines Feldes von 'HuffmanTriple'-Objekten als Inhalte in die Elemente eines Feldes von 'HuffmanTree'-Objekten &uuml;bernommen.<br />
     *      -> F&uuml;r jedes Objekt im Feld 'input' wird ein neues 'HuffmanTree'-Objekt erzeugt. (Dieses 'HuffmanTree'-Objekt ist dann ein Baum mit nur einem Knoten (->Dieser Knoten ist gleichzeitig eine Wurzel und ein Blatt))
     *  - F&uuml;r jedes Zeichen liegt anschlie&szlig;end ein einelementiger Baum im Feld 'trees' vor.
     * </p>
     */
    private void initializeTrees( HuffmanTriple[] input )//Das Argument ist ein Feld mit HuffmanTriple-Objekten
    {
        trees = new HuffmanTree[input.length];
        for ( int i = 0; i < input.length; i++ )
        {
            trees[i] = new HuffmanTree( input[i] );//'trees' ist ein Feld mit Bäumen (Also mit HuffmanTree Onjekten)
        }
    }

    private void insertionSort( int start ) 
    {
        for ( int i = start + 1; i < trees.length; i++ ) 
        {
            shiftTrees( i );
        }
    }

    private void shiftTrees( int start )
    {
        if (  start < trees.length )
        {
            HuffmanTree toInsert = trees[start];
            int i = start;
            while ( i > 0 && trees[i-1].compareTo( toInsert ) > 0 )
            {
                trees[i] = trees[i-1];
                i--;
            } 
            trees[i] = toInsert;
        }
    }
    
    /**
     * <h1>void buildTree()</h1>
     * <p>
     *  Die relvanten B&auml;ume (die, die noch nicht zusammengefasst worden sind), werden durch insertionSort( i ) sortiert.<br />
     *  Diese Methode verwendet eine Variation des 'insertionSort'-Algorithmus, bei dem das Sortieren mit dem Index 'start' beginnt (vorher (Kapitel 4) haben wir immer von index 0 bis i sortiert & wir konnten kein Startindex angeben). So kann darauf regiert werden, dass die B&auml;ume ja zusammengefasst und an den Index i+1 geschrieben werden.
     * </p>
     */
    private void buildTree()
    {
        for ( int i = 0; i+1 < trees.length; i++ )
        {
            insertionSort( i );//Sortieren ab dem Index i (weil die Einträge an den vorherigen Indezes bereits zu einem Baum zusammengefast wurden (Das passiert in der nächsten Codezeile))
            trees[i+1] = new HuffmanTree( trees[i], trees[i+1] );
        }
    }

    /**
     * <h1>void showCodeTable()</h1>
     * <p>
     *  Gibt die Kodierungstabelle aus.
     * </p>
     * <p>
     *  - Nach Ausf&uuml;hrung des Konstruktors in der Huffman-Baum aufgebaut und die Kodierung ist ermittelt und eingetragen.<br />
     *  - Der Baum steht im letzten Element des Feldes trees.<br />
     *  - Die Kodierung ist f&uuml;r jedes Zeichen in dem entsprechenden 'HuffmanTripple'-Objekt abgelegt
     * </p>
     */
    public void showCodeTable()
    {
        trees[trees.length-1].showCodes();//Am letzten Index steht der komplett aufgebaute Baum
    }

}
