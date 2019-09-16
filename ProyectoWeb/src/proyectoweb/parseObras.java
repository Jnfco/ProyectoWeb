/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoweb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author jnfco
 */
public class parseObras
{

    private Model modelo;
    private File obras;
    private String path;
    private String parsedList [];
   

    public parseObras() throws FileNotFoundException, IOException
    {
       this.path="obras.csv";
       this.obras=new File(path);
       this.readCSV(this.obras);
       this.writeModel();

    }
    
    public void addStatement(String s,String p,String o)
    {
        Resource subject=modelo.createResource(s);
        Property predicate=modelo.createProperty(p);
        RDFNode object=modelo.createResource(o);
        Statement stmt=modelo.createStatement(subject,predicate,object);
    }
    
        public void readCSV(File file) throws FileNotFoundException, IOException
    { 
        if(file.isFile()) 
        { 
            modelo =ModelFactory.createDefaultModel();
            String linea;
            int cont=0;
            String parsed;
            
            String ns ="http://websemantica.cl/obras/";
            String voc="http://websemantica.cl/Proyecto/obras/vocabulario/";
            String ns2 ="http://websemantica.cl/";
            String nsRDFS="http://www.w3.org/2000/01/rdf-schema#";
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1")); 
            while (( linea=csvReader.readLine()) != null)
            {
                cont++;
                if(cont>1)
                {
                  parsedList=linea.split(";");
                  //addStatement(ns+parsedList[0],nsRDFS+"subClassOf", ns+"Region");
                  //Resource r = modelo.getResource(ns+parsedList[3]);
                  //Resource r2=modelo.getResource(ns+parsedList[0]);
                  //r.addProperty(RDFS.label,"Nombre",parsedList[4]);
                  //r.addProperty(RDFS.label,"Region" ,r2);
                  //r.addProperty(RDFS.Resource,"region");
                    //System.out.println(parsedList[1]);
                 Resource obra =modelo.createResource(ns+parsedList[3]);
                 Property region =modelo.createProperty(voc,"Region");
                 Property nombre = modelo.createProperty(voc,"Nombre");
                 obra.addProperty(nombre,parsedList[4]);
                 obra.addProperty(region,parsedList[0]);
                    
                }
                //System.out.println(linea);
                
                
            }
            
            System.out.println("Regiones");
            
                
            
        } 
        else 
        { 
            System.out.println("Archivo no váldo."); 
            return; 
        } 
        
        
         
    } 

    public void writeModel() throws FileNotFoundException

    {
        /*
        File salida = new File("archivo.ttl");
        FileOutputStream fos = new FileOutputStream(salida);
        modelo.write(fos,"TURTLE");*/
        modelo.write(System.out,"TURTLE");
    }

}
