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
    private Model modeloRegiones;
    private File obras;
    private File archivoAntenas;
    private String path;
    private String parsedList[];
    private Resource obra;
    private Resource Region;
    private Property region;

    public parseObras() throws FileNotFoundException, IOException
    {
        this.path = "obras.csv";
        this.obras = new File(path);
        this.readCSV(this.obras);
        this.writeModel();

    }

    public void addStatement(String s, String p, String o)
    {
        Resource subject = modelo.createResource(s);
        Property predicate = modelo.createProperty(p);
        RDFNode object = modelo.createResource(o);
    }

    public void readCSV(File file) throws FileNotFoundException, IOException
    {
        if (file.isFile())
        {
            modelo = ModelFactory.createDefaultModel();
            modeloRegiones = ModelFactory.createDefaultModel();
            String linea;
            int cont = 0;

            String ns = "http://websemantica.cl/Proyecto/Obras/";
            String ns2 = "http://websemantica.cl/Proyecto/Regiones/";
            String voc = "http://websemantica.cl/Proyecto/Obras/vocabulario/";
            String voc2 = "http://websemantica.cl/Proyecto/Regiones/vocabulario/";
            String db = "http://dbpedia.org/resource/";
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
            while ((linea = csvReader.readLine()) != null)
            {
                cont++;
                if (cont > 1)
                {
                    parsedList = linea.split(";");
                    //addStatement(ns+parsedList[0],nsRDFS+"subClassOf", ns+"Region");
                    //Resource r = modelo.getResource(ns+parsedList[3]);
                    //Resource r2=modelo.getResource(ns+parsedList[0]);
                    //r.addProperty(RDFS.label,"Nombre",parsedList[4]);
                    //r.addProperty(RDFS.label,"Region" ,r2);
                    //r.addProperty(RDFS.Resource,"region");
                    //System.out.println(parsedList[1]);
                    obra = modelo.createResource(ns + parsedList[3]);
                    Region = modeloRegiones.createResource(ns2 + parsedList[0].replace(" ","_"));
                    Property region = modelo.createProperty(voc, "Region");
                    Property nombre = modelo.createProperty(voc, "Nombre");
                    Property servicio = modelo.createProperty(voc, "Servicio");
                    Property codigoBip = modelo.createProperty(voc, "Codigo_BIP");
                    Property etapa = modelo.createProperty(voc, "Etapa");
                    Property item = modelo.createProperty(voc, "Item");
                    Property monto = modelo.createProperty(voc, "Monto");
                    obra.addProperty(nombre, parsedList[4]);
                    //obra.addProperty(region,parsedList[0]);
                    obra.addProperty(servicio, parsedList[1]);
                    obra.addProperty(codigoBip, parsedList[3]);
                    obra.addProperty(etapa, parsedList[5]);
                    obra.addProperty(item, parsedList[2]);
                    obra.addProperty(monto, parsedList[7]);
                    modelo.add(obra, region, Region);

                    Property nombreR = modeloRegiones.createProperty(voc2, "Nombre");
                    Property nombreRegion = modeloRegiones.createProperty(voc2, "Nombre_Region");
                    Property pais = modeloRegiones.createProperty(voc2, "País");
                    Property numRegion = modeloRegiones.createProperty(voc2, "Numero_Region");
                    
                    String numeroRegion="";
                    String nombree=parsedList[0];
                    System.out.println(parsedList[0]);
                    if(parsedList[0].equals("Tarapacá"))
                    {
                        
                        numeroRegion="I";
                    }
                    if(parsedList[0].equals("Antofagasta"))
                    {
                        numeroRegion="II";
                    }
                    if(parsedList[0].equals("Atacama"))
                    {
                        numeroRegion="III";
                    }
                    if(parsedList[0].equals("Coquimbo"))
                    {
                        numeroRegion="IV";
                    }
                    if(parsedList[0].equals("Valparaíso"))
                    {
                        numeroRegion="V";
                    }
                    if(parsedList[0].equals("Libertador B Ohiggins"))
                    {
                        numeroRegion="VI";
                    }
                    if(parsedList[0].equals("Maule"))
                    {
                        numeroRegion="VII";
                    }
                    if(parsedList[0].equals("Bio Bio"))
                    {
                        numeroRegion="VIII";
                    }
                    if(parsedList[0].equals("Araucanía"))
                    {
                        numeroRegion="IX";
                    }
                    if(parsedList[0].equals("Los Lagos"))
                    {
                        numeroRegion="X";
                    }
                    if(parsedList[0].equals("Aisen"))
                    {
                        numeroRegion="XI";
                    }
                    if(parsedList[0].equals("Magallanes"))
                    {
                        numeroRegion="XII";
                    }
                    if(parsedList[0].equals("Los Ríos"))
                    {
                        numeroRegion="XIV";
                    }
                    if(parsedList[0].equals("Arica y Parinacota"))
                    {
                        numeroRegion="XV";
                    }
                    if(parsedList[0].equals("No Regionalizable"))
                    {
                        numeroRegion=" ";
                    }
                    if(parsedList[0].equals("Metropolitana"))
                    {
                        numeroRegion="RM";
                    }
                    Region.addProperty(nombreR, "Region de " + parsedList[0]);
                    Region.addProperty(nombreRegion, parsedList[0]);
                    Region.addProperty(pais, db + "Chile");
                    Region.addProperty(numRegion,numeroRegion);
                   
                    

                }
                //System.out.println(linea);

            }

            System.out.println("Regiones");

        } else
        {
            System.out.println("Archivo no váldo.");
            return;
        }

    }

    public void writeModel() throws FileNotFoundException

    {

        File salidaObras = new File("obrasAuto.ttl");
        File salidaRegiones = new File("regionesAuto.ttl");
        FileOutputStream fos = new FileOutputStream(salidaObras);
        FileOutputStream fos2 = new FileOutputStream(salidaRegiones);
        modelo.write(fos, "TURTLE");
        modelo.write(System.out, "TURTLE");

        modeloRegiones.write(fos2, "TURTLE");
        modeloRegiones.write(System.out, "TURTLE");

    }

}
