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
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.commons.lang3.StringUtils; 
import org.apache.jena.rdf.model.Model; 
import org.apache.jena.rdf.model.ModelFactory; 
import org.apache.jena.rdf.model.Property; 
import org.apache.jena.rdf.model.Resource; 


 
/** 
 * 
 * @author Juan Abello
 */ 
public class ParseOrders
{ 
    private File jan; 
    private File feb; 
    private File mar; 
    private File apr; 
    private File may; 
    private File jun; 
    private File jul; 
    private File aug; 
    private File sep; 
    private File oct; 
    private File nov; 
    private File dec; 
    private String path; 
    private String path2; 
    private String path3; 
    private String path4; 
    private String path5; 
    private String path6; 
    private String path7; 
    private String path8; 
    private String path9; 
    private String path10; 
    private String path11; 
    private String path12; 
    private Model model; 
    private Model customersM; 
    private String[] listaOrders;
    private Resource order;
    private Resource Customer;
     
     
    public ParseOrders() throws FileNotFoundException, IOException 
    { 
        this.path = "jan_orders.csv"; 
        this.path2 = "feb_orders.csv"; 
        this.path3 = "mar_orders.csv"; 
        this.path4 = "apr_orders.csv"; 
        this.path5 = "may_orders.csv"; 
        this.path6 = "jun_orders.csv"; 
        this.path7 = "jul_orders.csv"; 
        this.path8 = "aug_orders.csv"; 
        this.path9 = "sep_orders.csv"; 
        this.path10 = "oct_orders.csv"; 
        this.path11 = "nov_orders.csv"; 
        this.path12 = "dec_orders.csv"; 
        this.jan = new File(path);
        this.feb = new File(path2);
        this.mar = new File(path3);
        this.apr = new File(path4);
        this.may = new File(path5);
        this.jun = new File(path6);
        this.jul = new File(path7);
        this.aug = new File(path8);
        this.sep = new File(path9);
        this.oct = new File(path10);
        this.nov = new File(path11);
        this.dec = new File(path12);
        model = ModelFactory.createDefaultModel(); 
        customersM = ModelFactory.createDefaultModel();
        
        this.readCSV(this.jan);
        this.readCSV(this.feb);
        this.readCSV(this.mar);
        this.readCSV(this.apr);
        this.readCSV(this.may);
        this.readCSV(this.jun);
        this.readCSV(this.jul);
        this.readCSV(this.aug);
        this.readCSV(this.sep);
        this.readCSV(this.oct);
        this.readCSV(this.nov);
        this.readCSV(this.dec);
        this.writeModel(); 
    } 
     
    public void readCSV(File file ) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto/"; 
        String ns2 = "http://websemantica.cl/Proyecto/orders/"; 
        String ns3 = "http://websemantica.cl/Proyecto/customers/"; 
        String voc = "http://websemantica.cl/Proyecto/orders/vocabulario/"; 
        String schema = "http://www.w3.org/2000/01/rdf-schema#"; 
        
        if(file.isFile()) 
        { 
            
             
            String line; 
            int cont = 0; 
             
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1")); 
             
            while ((line = csvReader.readLine()) != null) 
            { 
                cont++; 
                    String[]arregloR=new String[7];
                    listaOrders = line.split(","); 
                   
                    
                    order=this.model.createResource(ns2+listaOrders[0].replace(" ","_"));
                    Customer = this.customersM.createResource(ns3 + listaOrders[2].replace(" ","_"));
                    
                    Property orderdate = this.model.createProperty(voc,"orderdate");
                    Property customer = this.model.createProperty(voc,"customer");
                    Property netamount= this.model.createProperty(voc,"netamount");
                    Property tax = this.model.createProperty(voc,"tax");
                    Property totalamount = this.model.createProperty(voc,"totalamount");
                            
                    //model.add(antena,tieneEmpresa,empresa);
                    this.model.add(order,orderdate,listaOrders[1].replace(" ","_"));
                    this.model.add(order, customer, Customer);
                    this.model.add(order,netamount,listaOrders[3].replace(" ","_"));
                    this.model.add(order,tax,listaOrders[4].replace(" ","_"));
                    this.model.add(order,totalamount,listaOrders[5].replace(" ","_"));
                   
                    
                   
                     
                
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
        File salidaCustomers = new File("ordersAuto.ttl"); 
        FileOutputStream fos = new FileOutputStream(salidaCustomers); 
        model.write(fos, "TURTLE"); 
        //model.write(System.out, "TURTLE"); 
         
    } 

    
   
     
     
     
} 
