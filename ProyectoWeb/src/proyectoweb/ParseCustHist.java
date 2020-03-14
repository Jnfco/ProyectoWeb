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
public class ParseCustHist
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
    private String path11; 
    private String path12; 
    private Model model; 
    private Model customersM; 
    private Model ordersM; 
    private Model productsM; 
    private String[] listaHist;
    private Resource Order;
    private Resource Customer;
    private Resource Product;
    private Resource custHist;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
     
     
    public ParseCustHist() throws FileNotFoundException, IOException 
    { 
        this.path = "jan_cust_hist.csv"; 
        this.path2 = "feb_cust_hist.csv"; 
        this.path3 = "mar_cust_hist.csv"; 
        this.path4 = "apr_cust_hist.csv"; 
        this.path5 = "may_cust_hist.csv"; 
        this.path6 = "jun_cust_hist.csv"; 
        this.path7 = "jul_cust_hist.csv"; 
        this.path8 = "aug_cust_hist.csv"; 
        this.path9 = "sep_cust_hist.csv"; 
        this.path11 = "nov_cust_hist.csv"; 
        this.path12 = "dec_cust_hist.csv"; 
        this.jan = new File(path);
        this.feb = new File(path2);
        this.mar = new File(path3);
        this.apr = new File(path4);
        this.may = new File(path5);
        this.jun = new File(path6);
        this.jul = new File(path7);
        this.aug = new File(path8);
        this.sep = new File(path9);
        this.nov = new File(path11);
        this.dec = new File(path12);
        model = ModelFactory.createDefaultModel(); 
        customersM = ModelFactory.createDefaultModel();
        ordersM = ModelFactory.createDefaultModel();
        productsM = ModelFactory.createDefaultModel();
        
        this.readCSV(this.jan);
        this.readCSV(this.feb);
        this.readCSV(this.mar);
        this.readCSV(this.apr);
        this.readCSV(this.may);
        this.readCSV(this.jun);
        this.readCSV(this.jul);
        this.readCSV(this.aug);
        this.readCSV(this.sep);
        this.readCSV(this.nov);
        this.readCSV(this.dec);
        this.writeModel(); 
    } 
     
    public void readCSV(File file ) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto/"; 
        String ns2 = "http://websemantica.cl/Proyecto/cust_hist/"; 
        String ns3 = "http://websemantica.cl/Proyecto/customers/"; 
        String ns4 = "http://websemantica.cl/Proyecto/products/"; 
        String ns5 = "http://websemantica.cl/Proyecto/orders/"; 
        String voc = "http://websemantica.cl/Proyecto/cust_hist/vocabulario/"; 
        String schema = "http://www.w3.org/2000/01/rdf-schema#"; 
        
        if(file.isFile()) 
        { 
            
             
            String line; 
            int cont = 0; 
             
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1")); 
             
            while ((line = csvReader.readLine()) != null) 
            { 
                cont++; 
                   
                    listaHist = line.split(","); 
                   
                   String[]arregloR=new String[7];
                   String codigoRandom=generateCode(1000);
                   String randomC="";
                   
                    for (int i = 0; i < arregloR.length; i++)
                    {
                        randomC=randomC.concat(generateCode(10));
                    }
                    custHist=this.model.createResource(ns2+randomC);
                    Customer = this.customersM.createResource(ns3 + listaHist[0].replace(" ","_"));
                    Order = this.ordersM.createResource(ns4 + listaHist[1].replace(" ","_"));
                    Product = this.productsM.createResource(ns5 + listaHist[2].replace(" ","_"));
                    
                    
                    Property customer = this.model.createProperty(voc,"Customer");
                    Property order= this.model.createProperty(voc,"Order");
                    Property product = this.model.createProperty(voc,"Product");
                            
                    //model.add(antena,tieneEmpresa,empresa);
                    this.model.add(custHist,customer,Customer);
                    this.model.add(custHist,order,Order);
                    this.model.add(custHist,product,Product);
                   
                    
                   
                     
                
            } 
        } 
        else 
        { 
            System.out.println("Archivo no váldo. Cust_Hist"); 
            return; 
        } 
         
    }
    
  
     
    public void writeModel() throws FileNotFoundException 
    { 
        File salidaCustomers = new File("custHistAuto.ttl"); 
        FileOutputStream fos = new FileOutputStream(salidaCustomers); 
        model.write(fos, "TURTLE"); 
        //model.write(System.out, "TURTLE"); 
         
    } 

     
   
public static String generateCode(int count) {
StringBuilder builder = new StringBuilder();
while (count-- != 0) {
int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
builder.append(ALPHA_NUMERIC_STRING.charAt(character));
}
return builder.toString();
}
     
     
     
} 
