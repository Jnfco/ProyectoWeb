
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;
import proyectoweb.ParseAntenas;
import proyectoweb.parseObras;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jnfco
 */
public class ProyectoWeb
{

    static String personURI = "http://somewhere/JohnSmith";
    static String fullName = "John Smith";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
       
        ParseAntenas pa= new ParseAntenas();
        parseObras po= new parseObras();
        
        
    }
    
    
}
