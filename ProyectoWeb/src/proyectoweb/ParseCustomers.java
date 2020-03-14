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
public class ParseCustomers
{ 
    private File customers; 
    private File customers2; 
    private String path; 
    private String path2; 
    private Model model; 
    private String[] listaCustomers;
    private Resource customer;
     
     
    public ParseCustomers() throws FileNotFoundException, IOException 
    { 
        this.path = "us_cust.csv";
        this.path2 = "row_cust.csv";
        this.customers = new File(path); 
        this.customers2 = new File(path2); 
        model = ModelFactory.createDefaultModel(); 
        this.readCSV(this.customers); 
        this.readCSV(this.customers2); 
        this.writeModel(); 
    } 
     
    public void readCSV(File file) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto/"; 
        String ns2 = "http://websemantica.cl/Proyecto/customers/"; 
        String voc = "http://websemantica.cl/Proyecto/customers/vocabulario/"; 
             
        if(file.isFile()) 
        { 
            
             
            String line; 
            int cont = 0; 
             
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1")); 
             
            while ((line = csvReader.readLine()) != null) 
            { 
                   
                    listaCustomers = line.split(","); 
                    
                    customer=model.createResource(ns2+listaCustomers[0].replace(" ","_"));
                    
                    Property firstName = model.createProperty(voc,"firstname");
                    Property lastName = model.createProperty(voc,"lasttname");
                    Property address1= model.createProperty(voc,"address1");
                    Property address2 = model.createProperty(voc,"address2");
                    Property region = model.createProperty(voc,"region");
                    Property city = model.createProperty(voc,"city");
                    Property username = model.createProperty(voc,"username");
                    Property password = model.createProperty(voc,"password");
                    Property country = model.createProperty(voc,"country");
                    Property zip = model.createProperty(voc,"zip");
                    Property state = model.createProperty(voc,"state");
                    Property phone = model.createProperty(voc,"phone");
                    Property income = model.createProperty(voc,"income");
                    Property creditcard = model.createProperty(voc,"creditcard");
                    Property creditcardType = model.createProperty(voc,"creditcardtype");
                    Property creditcardExpiration = model.createProperty(voc,"creditcardexpiration");
                    Property age = model.createProperty(voc,"age");
                    Property gender = model.createProperty(voc,"gender");
                    Property email = model.createProperty(voc,"email");
                            
                    //model.add(antena,tieneEmpresa,empresa);
                    model.add(customer,firstName,listaCustomers[1].replace(" ","_"));
                    model.add(customer,lastName,listaCustomers[2].replace(" ","_"));
                    model.add(customer,address1,listaCustomers[3].replace(" ","_"));
                    model.add(customer,address2,listaCustomers[4].replace(" ","_"));
                    model.add(customer,city,listaCustomers[5].replace(" ","_"));
                    model.add(customer,state,listaCustomers[6].replace(" ","_"));
                    model.add(customer,zip,listaCustomers[7].replace(" ","_"));
                    model.add(customer,country,listaCustomers[8].replace(" ","_"));
                    model.add(customer,region,listaCustomers[9].replace(" ","_"));
                    model.add(customer,email,listaCustomers[10].replace(" ","_"));
                    model.add(customer,phone,listaCustomers[11].replace(" ","_"));
                    model.add(customer,creditcardType,listaCustomers[12].replace(" ","_"));
                    model.add(customer,creditcard,listaCustomers[13].replace(" ","_"));
                    model.add(customer,creditcardExpiration,listaCustomers[14].replace(" ","_"));
                    model.add(customer,username,listaCustomers[15].replace(" ","_"));
                    model.add(customer,password,listaCustomers[16].replace(" ","_"));
                    model.add(customer,age,listaCustomers[17].replace(" ","_"));
                    model.add(customer,income,listaCustomers[18].replace(" ","_"));
                    model.add(customer,gender,listaCustomers[19].replace(" ","_"));
                  
                 
                   
                     
                
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
        File salidaCustomers = new File("customersAuto.ttl"); 
        FileOutputStream fos = new FileOutputStream(salidaCustomers); 
        model.write(fos, "TURTLE"); 
        //model.write(System.out, "TURTLE"); 
         
    } 
   
     
     
     
} 
