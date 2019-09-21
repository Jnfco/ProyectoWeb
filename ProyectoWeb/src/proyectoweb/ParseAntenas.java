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
    
    private Resource resRegion;
    
    
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
                if(cont > 1)
                {
                    listaAntenas = line.split(";");
                    
                    Resource empresa = model.createResource(ns1 + listaAntenas[0].replace(' ', '_'));
                    
                    Property servicio = model.createProperty(voc, "Servicio");
                    Property tipo_servicio = model.createProperty(voc, "Tipo Servicio");
                    Property sistema = model.createProperty(voc, "Sistema");
                    
                    Resource elemento = model.createResource(ns1 + listaAntenas[4]);
                    Property direccion = model.createProperty(voc, "Dirección");
                    Property comuna = model.createProperty(voc, "Comuna");
                    Property nroRegion = model.createProperty(voc, "Región");
                    Property lat_grados = model.createProperty(voc, "Latitud Grados");
                    Property lat_minutos = model.createProperty(voc, "Latitud Minutos");;
                    Property lat_segundos = model.createProperty(voc, "Latitud Segundos");;
                    Property lon_grados = model.createProperty(voc, "Longitud Grados");;
                    Property lon_minutos = model.createProperty(voc, "Longitud Minutos");;
                    Property lon_segundos = model.createProperty(voc, "Longitud Segundos");;
                    
                    empresa.addProperty(servicio, StringUtils.stripAccents(listaAntenas[1]));
                    empresa.addProperty(tipo_servicio, StringUtils.stripAccents(listaAntenas[2]));
                    empresa.addProperty(sistema, StringUtils.stripAccents(listaAntenas[3]));
                    
                    elemento.addProperty(direccion, StringUtils.stripAccents(listaAntenas[5]));
                    elemento.addProperty(comuna, StringUtils.stripAccents(listaAntenas[6]));
                    elemento.addProperty(nroRegion, StringUtils.stripAccents(listaAntenas[7]));
                    elemento.addProperty(lat_grados, StringUtils.stripAccents(listaAntenas[8]));
                    elemento.addProperty(lat_minutos, StringUtils.stripAccents(listaAntenas[9]));
                    elemento.addProperty(lat_segundos, StringUtils.stripAccents(listaAntenas[10]));
                    elemento.addProperty(lon_grados, StringUtils.stripAccents(listaAntenas[11]));
                    elemento.addProperty(lon_minutos, StringUtils.stripAccents(listaAntenas[12]));
                    elemento.addProperty(lon_segundos, StringUtils.stripAccents(listaAntenas[13]));
                    
                    //model.add(elemento, region, resRegion);
                    
                    Property nombreCompletoRegion = regiones.createProperty(vocReg, "Nombre");
                    Property nombreCortoRegion = regiones.createProperty(vocReg, "Nombre_Region");
                    Property pais = regiones.createProperty(vocReg, "Pais");
                    Property numRegion = regiones.createProperty(vocReg, "Numero_Region");
                    
                    String nomCorRegion = "";
                    String numero = listaAntenas[7];
                    
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
                            nomCorRegion = "Libertador B Ohiggins";
                            break;
                        case "VII":
                            nomCorRegion = "Maule";
                            break;
                        case "VIII":
                            nomCorRegion = "Bio Bio";
                            break;
                        case "IX":
                            nomCorRegion = "Araucanía";
                            break;
                        case "X":
                            nomCorRegion = "Los Lagos";
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
                            nomCorRegion = "Los Ríos";
                            break;
                        case "XV":
                            nomCorRegion = "Arica y Parinacota";
                            break;
                        default:
                            throw new AssertionError();
                    }
                    
                    resRegion.addProperty(nombreCompletoRegion, "Región de " + nomCorRegion);
                    resRegion.addProperty(nombreCortoRegion, nomCorRegion);
                    resRegion.addProperty(pais, dbpedia + "Chile");
                    resRegion.addProperty(numRegion, listaAntenas[7]);
                    
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
        model.write(System.out, "TURTLE");
        
    }
    
    
    
}
