/**
 * <img src="https://www.uni-due.de/imperia/md/images/jcs-ruhr/logo_tudortmund.png" width="15%" height="auto">
 * <center>
 *  <h1>Autor</h1>
 *  <p>
 *      Dokumentiert von: <em>Mert Can &Ouml;zdemir</em><br />
 *      Modul: <em>DAP1</em>
 *  </p>
 *  <h2>Dokumentation der Klasse DoubleIntegersStrategy</h2>
 *   <p>
 *      - Diese Strategie verdoppelt die Werte jedes einzelnen Elements in der Liste<br />
 *      -> Der Inhalt des als Argument &uuml;bergebenen Objektes wird verdoppelt und das 'Integer'-Objekt mit dem Doppelten Wert, wird an die 'SubstituteAll'-Methode
 *         zurückgegeben.<br />
 *         => Die 'SubstituteAll()'-Methode ersetzt dann das aktuelle Objekt in der Liste, mit dem Objekt, dass von der 'substitute( Object ref )'-Methode zurückgegeben wurde.
 *  </p>
 * </center>
 */
public class DoubleIntegersStrategy 
extends DoublyLinkedList.SubstitutionStrategy
{
    /**
     * <p>
     *  Es wird ein neues Objekt zur&uuml;ckgegeben, dass den Wert des als Argument erhaltenen Objekt verdoppelt.
     * </p>
     */
    public Object substitute( Object ref )
    {
        return 2 * (int)ref;//Erinnerung: Aufgrund des Typs 'Object' der Rückgabe wird zu dem in der return-Anweisung berechneten int-Wert durch Boxing in ein passendes Integer-Objekt erzeugt,
        //das zurückgegeben wird.
    }
}