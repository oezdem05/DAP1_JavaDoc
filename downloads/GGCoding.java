/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse GGCoding</h2>
 *  <p>
 *      Diese Klasse Testet die Datenkomprimierung nach der HuffmanCodierung. <br />
 *      Das erste Beispiel komprimiert den Text "Halloween" und<br />
 *      Das zweite Beispiel komprimiert den Artikel 1 Abs. 1 aus dem Grundgesetz.
 *  </p>
 * </center>
 */
public class GGCoding 
{
    public static void main( String[] args )
    {
        firstTreeTest();
        secondTreeTest();
    }

    /**
     * <h1>static void firstTreeTest()</h1>
     * <p>
     *  Komprimiert den String "halloween" und gibt ihn als Bitmuster in komprimierter Form aus.
     * </p>
     * <p>
     *  Beachte, dass die codeTable (Ausgabe durch die Methode 'showCodeTable()' der Klasse HuffmanCoding) notwendig ist, um den Code wieder zu dekodieren
     * </p>
     */
    public static void firstTreeTest()
    {
        String s = "halloween" ;

        CharacterSearchTree hal = new CharacterSearchTree();
        for ( int i = 0; i < s.length() ; i++ )
        {
            hal.add( s.charAt( i ) );
        }
        System.out.println( "binary tree: " );
        System.out.println( "--------------------------" );
        hal.show();
        System.out.println();
        HuffmanCoding coding = new HuffmanCoding( hal.toArray() );//Das Feld wird erzeugt und an den Konstruktor von HuffmanCoding übergeben
        System.out.println( "code table: " );
        System.out.println( "--------------------------" );
        coding.showCodeTable();
        System.out.println();
        System.out.println( "binary tree with codes: " );
        System.out.println( "--------------------------" );
        hal.show();
        String codeOfHal = "";//Generiert die Kodierung für den ganzen Text
        for ( int i = 0; i < s.length() ; i++ )
        {
            codeOfHal += hal.getCode( s.charAt( i ) );//Komprimieren: Konkateniert den Code für jedes Zeichen an den String 'codOfHal'
        }
        System.out.println( "bit code: " );
        System.out.println( "--------------------------" );
        System.out.println( codeOfHal );//Gibt den code aus

    }

    /**
     * <h1>static void secondTreeTest()</h1>
     * <p>
     *  Analog zu der Methode 'firstTreeTest()' (Nur mit einem anderen Text) 
     * </p>
     */
    public static void secondTreeTest()
    {
        String s = "Die Würde des Menschen ist unantastbar. " +
            "Sie zu achten und zu schützen ist Verpflichtung aller staatlichen Gewalt. " +
            "Das Deutsche Volk bekennt sich darum zu unverletzlichen und unveräußerlichen " +
            "Menschenrechten als Grundlage jeder menschlichen Gemeinschaft, des Friedens und " +
            "der Gerechtigkeit in der Welt. ";

        CharacterSearchTree gg = new CharacterSearchTree();
        for ( int i = 0; i < s.length() ; i++ )
        {
            gg.add( s.charAt( i ) );
        }
        System.out.println( "binary tree: " );
        System.out.println( "--------------------------" );
        gg.show();
        System.out.println();
        System.out.println( "binary tree (toArray): " );
        System.out.println( "--------------------------" );
        HuffmanTriple[] hmts = gg.toArray();
        for ( HuffmanTriple hmt : hmts )
        {
            System.out.print( hmt.getToken() + " " );
        }
        System.out.println(); System.out.println();
        HuffmanCoding coding = new HuffmanCoding( gg.toArray() );
        System.out.println( "code table: " );
        System.out.println( "--------------------------" );
        coding.showCodeTable();
        System.out.println();
        System.out.println( "binary tree with codes: " );
        System.out.println( "--------------------------" );
        gg.show();
        String codeOfGG = "";
        for ( int i = 0; i < s.length() ; i++ )
        {
            codeOfGG += gg.getCode( s.charAt( i ) );
        }
        System.out.println( "bit code: " );
        System.out.println( "--------------------------" );
        for ( int i = 0; i < codeOfGG.length() ; i++ )
        {
            System.out.print( codeOfGG.charAt( i ) );
            if ( (i+1) % 80 == 0 )
            {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println( "input chars: " + s.length() );
        System.out.println( "input bits: " + s.length() * 8 );
        System.out.println( "output bits: " + codeOfGG.length() );
    }
}

