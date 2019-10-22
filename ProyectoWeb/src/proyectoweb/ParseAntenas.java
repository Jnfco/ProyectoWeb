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
public class ParseAntenas 
{ 
    private File antenas; 
    private String path; 
    private Model model; 
    private Model regiones; 
    private String[] listaAntenas;
    private Resource antena;
     
     
    public ParseAntenas() throws FileNotFoundException, IOException 
    { 
        this.path = "Antenas.csv"; 
        this.antenas = new File(path); 
        this.readCSV(this.antenas); 
        this.writeModel(); 
    } 
     
    public void readCSV(File file) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto/"; 
        String ns2 = "http://websemantica.cl/Proyecto/antenas/"; 
        String voc = "http://websemantica.cl/Proyecto/antenas/vocabulario/"; 
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
                    listaAntenas = line.split(";"); 
                    //System.out.println("Lista antenas: "+listaAntenas[7]);
                    antena=model.createResource(ns2+listaAntenas[0].replace(" ","_")+"_"+randomC);
                    Property tieneEmpresa=model.createProperty(voc,"Empresa");
                    Resource empresa = model.createResource(ns1 + listaAntenas[0].replace(" ","_")); 
                     
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
                    Property type=model.createProperty(vocT,"type");
                    model.add(antena,tieneEmpresa,empresa);
                    model.add(antena,servicio,listaAntenas[1].replace(" ","_"));
                    model.add(antena,tipo_servicio,listaAntenas[2].replace(" ","_"));
                    model.add(antena,sistema,listaAntenas[3].replace(" ","_"));
                    model.add(antena,tipoElemento,listaAntenas[4].replace(" ","_"));
                    model.add(antena,direccion,listaAntenas[5].replace(" ","_"));
                    model.add(antena,comuna,listaAntenas[6].replace(" ","_"));
                    model.add(antena,type,"Antena");
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
                    } 
                     
                    Resource region=model.createResource(reg+nomCorRegion);
                    Property regionN=model.createProperty(reg);
                    model.add(antena,regionN,region); 
                    model.add(antena,lat_grados,listaAntenas[8]);
                    model.add(antena,lat_minutos,listaAntenas[9]);
                    model.add(antena,lat_segundos,listaAntenas[10]);
                    model.add(antena,lon_grados,listaAntenas[11]);
                    model.add(antena,lon_minutos,listaAntenas[12]);
                    model.add(antena,lon_segundos,listaAntenas[13]);
                     
                 
                   
                     
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
        File salidaAntenas = new File("antenasAuto.ttl"); 
        FileOutputStream fos = new FileOutputStream(salidaAntenas); 
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
