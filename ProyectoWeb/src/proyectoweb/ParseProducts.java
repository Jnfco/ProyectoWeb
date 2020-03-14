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
import java.io.FileReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.commons.lang3.StringUtils; 
import org.apache.jena.rdf.model.Model; 
import org.apache.jena.rdf.model.ModelFactory; 
import org.apache.jena.rdf.model.Property; 
import org.apache.jena.rdf.model.Resource; 


 
/** 
 * 
 * @author Nicolás Hervias 
 */ 
public class ParseProducts
{ 
    private File products;  
    private String path; 
    private Model model; 
    private String[] listaProducts;
    private Resource Products;
     
     
    public ParseProducts() throws FileNotFoundException, IOException 
    { 
        this.path = "prod.csv";
        this.products = new File(path);  
        model = ModelFactory.createDefaultModel(); 
        this.readCSV(this.products);  
        this.writeModel(); 
    } 
     
    public void readCSV(File file) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto/"; 
        String ns2 = "http://websemantica.cl/Proyecto/products/"; 
        String voc = "http://websemantica.cl/Proyecto/products/vocabulario/"; 
             
        if(file.isFile()) 
        { 
            
             
            String line; 
            int cont = 0; 
             
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1")); 
             
            while ((line = csvReader.readLine()) != null) 
            { 
                   
                    listaProducts = line.split(","); 
                    
                    
                    
                    Products=model.createResource(ns2+listaProducts[0].replace(" ","_"));
                    
                    Property category = model.createProperty(voc,"Category");
                    Property title = model.createProperty(voc,"Title");
                    Property actor= model.createProperty(voc,"Actor");
                    Property price = model.createProperty(voc,"Price");
                    Property special = model.createProperty(voc,"Special");
                    Property common_prod_id = model.createProperty(voc,"Common_Prod_Id");
                            
                    //model.add(antena,tieneEmpresa,empresa);
                    model.add(Products,category,listaProducts[1].replace(" ","_"));
                    model.add(Products,title,listaProducts[2].replace(" ","_"));
                    model.add(Products,actor,listaProducts[3].replace(" ","_"));
                    model.add(Products,price,listaProducts[4].replace(" ","_"));
                    model.add(Products,special,listaProducts[5].replace(" ","_"));
                    model.add(Products,common_prod_id,listaProducts[6].replace(" ","_"));
                  
                 
                   
                     
                
            } 
        } 
        else 
        { 
            System.out.println("Archivo no váldo."); 
            return; 
        } 
         
    } 
     
    public void writeModel() throws FileNotFoundException 
    { 
        File salidaCustomers = new File("productsAuto.ttl"); 
        FileOutputStream fos = new FileOutputStream(salidaCustomers); 
        model.write(fos, "TURTLE"); 
        //model.write(System.out, "TURTLE"); 
         
    } 
    
   
   
     
     
     
} 
