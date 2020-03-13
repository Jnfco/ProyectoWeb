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
    private File antenas; 
    private String path; 
    private Model model; 
    private Model regiones; 
    private String[] listaAntenas;
    private String[] listaCustomers;
    private Resource antena;
    private Resource customer;
     
     
    public ParseCustomers() throws FileNotFoundException, IOException 
    { 
        this.path = "us_cust.csv"; 
        this.antenas = new File(path); 
        this.readCSV(this.antenas); 
        this.writeModel(); 
    } 
     
    public void readCSV(File file) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto/"; 
        String ns2 = "http://websemantica.cl/Proyecto/customers/"; 
        String voc = "http://websemantica.cl/Proyecto/customers/vocabulario/"; 
        String schema = "http://www.w3.org/2000/01/rdf-schema#"; 
        
        String reg = "http://websemantica.cl/Proyecto/Regiones/"; 
        String vocReg = "http://websemantica.cl/Proyectos/Regiones/vocabulario/"; 
        String dbpedia = "http://dbpedia.org/resource/"; 
        String vocT ="http://www.w3.org/1999/02/22-rdf-syntax-ns#/";
             
        if(file.isFile()) 
        { 
            model = ModelFactory.createDefaultModel(); 
            regiones = ModelFactory.createDefaultModel(); 
             
            String line; 
            int cont = 0; 
             
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1")); 
             
            while ((line = csvReader.readLine()) != null) 
            { 
                cont++; 
                if(cont > 1 && cont<18493) 
                { 
                    String[]arregloR=new String[7];
                   String codigoRandom=generateCode();
                   String randomC="";
                    for (int i = 0; i < arregloR.length; i++)
                    {
                        randomC=randomC.concat(generateCode());
                    }
                    listaCustomers = line.split(","); 
                    //System.out.println("Lista antenas: "+listaAntenas[7]);
                    //antena=model.createResource(ns2+listaCustomers[0].replace(" ","_")+"_"+randomC);
                    
                    customer=model.createResource(ns2+listaCustomers[0].replace(" ","_"));
                    //Property tieneEmpresa=model.createProperty(voc,"Empresa");
                    //Resource empresa = model.createResource(ns1 + listaAntenas[0].replace(" ","_")); 
                    /* 
                    Property servicio = model.createProperty(voc, "Servicio"); 
                    Property tipo_servicio = model.createProperty(voc, "Tipo_Servicio"); 
                    Property sistema = model.createProperty(voc, "Sistema"); 
                    Property tipoElemento=model.createProperty(voc,"Tipo_Elemento");
                    Property direccion = model.createProperty(voc, "Dirección"); 
                    Property comuna = model.createProperty(voc, "Comuna"); 
                    Property lat_grados = model.createProperty(voc, "Lat_G"); 
                    Property lat_minutos = model.createProperty(voc, "Lat_M");; 
                    Property lat_segundos = model.createProperty(voc, "Lat_S");; 
                    Property lon_grados = model.createProperty(voc, "Long_G");; 
                    Property lon_minutos = model.createProperty(voc, "Long_M");; 
                    Property lon_segundos = model.createProperty(voc, "Long_S");;
                    Property type=model.createProperty(vocT,"type");*/
                    
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
                    /*
                    String nomCorRegion;
                   
                    String numero=listaAntenas[7];
                      switch (numero) 
                    { 
                        case "I": 
                            nomCorRegion = "Tarapacá"; 
                            break; 
                        case "II": 
                            nomCorRegion = "Antofagasta"; 
                            break; 
                        case "III": 
                            nomCorRegion = "Atacama"; 
                            break; 
                        case "IV": 
                            nomCorRegion = "Coquimbo"; 
                            break; 
                        case "V": 
                            nomCorRegion = "Valparaíso"; 
                            break; 
                        case "VI": 
                            nomCorRegion = "Libertador_B_Ohiggins"; 
                            break; 
                        case "VII": 
                            nomCorRegion = "Maule"; 
                            break; 
                        case "VIII": 
                            nomCorRegion = "Bio_Bio"; 
                            break; 
                        case "IX": 
                            nomCorRegion = "Araucanía"; 
                            break; 
                        case "X": 
                            nomCorRegion = "Los_Lagos"; 
                            break; 
                        case "XI": 
                            nomCorRegion = "Aisén"; 
                            break; 
                        case "XII": 
                            nomCorRegion = "Magallanes"; 
                            break; 
                        case "RM": 
                            nomCorRegion = "Metropolitana"; 
                            break; 
                        case "XIV": 
                            nomCorRegion = "Los_Ríos"; 
                            break; 
                        case "XV": 
                            nomCorRegion = "Arica_y_Parinacota"; 
                            break; 
                        default: 
                            nomCorRegion=numero;
                    } */
                     
                    //Resource region=model.createResource(reg+nomCorRegion);
                    //Property regionN=model.createProperty(reg);
                    //model.add(antena,regionN,region); 
                   // model.add(antena,lat_grados,listaAntenas[8]);
                    //model.add(antena,lat_minutos,listaAntenas[9]);
                    //model.add(antena,lat_segundos,listaAntenas[10]);
                    //model.add(antena,lon_grados,listaAntenas[11]);
                    //model.add(antena,lon_minutos,listaAntenas[12]);
                    //model.add(antena,lon_segundos,listaAntenas[13]);
                     
                 
                   
                     
                } 
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
    public static String generateCode() {

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String fullalphabet = alphabet + alphabet.toLowerCase() + "123456789";
    Random random = new Random();

    char code = fullalphabet.charAt(random.nextInt(9));

    return Character.toString(code);

}
     
     
     
} 
