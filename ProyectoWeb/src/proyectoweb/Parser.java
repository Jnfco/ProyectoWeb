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
public class Parser 
{ 
    private File products; 
    private File productVendors;
    private File vendors;
    private String pathProd; 
    private String pathPrVen;
    private String pathVend;
    private Model modelOutput; 
    //private Model regiones; 
    private String[] listaProductos;
    private String[] listaVendors;
    private String[] listaProdVend;
    private Resource productResource;
     
     
    public Parser() throws FileNotFoundException, IOException 
    { 
        this.pathProd = "Product.csv";
        this.pathPrVen = "ProductVendor.csv";
        this.pathVend = "Vendor.csv";
        this.products = new File(this.pathProd);
        this.productVendors = new File(this.pathPrVen);
        this.vendors = new File(this.pathVend);
        this.readCSVs(this.products, this.vendors, this.productVendors); 
        this.writeModel(); 
    } 
     
    public void readCSVs(File file1, File file2, File file3) throws FileNotFoundException, IOException 
    { 
        String ns1 = "http://websemantica.cl/Proyecto3/"; 
        String nsProd = "http://websemantica.cl/Proyecto3/product/"; 
        String vocProd = "http://websemantica.cl/Proyecto3/product/voc/";
        String nsVend = "http://websemantica.cl/Proyecto3/vendor/";
        String vocVend = "http://websemantica.cl/Proyecto3/vendor/voc/";
        String nsPV = "http://websemantica.cl/Proyecto3/productvendor/";
        String vocPV = "http://websemantica.cl/Proyecto3/productvendor/voc/";
        String schema = "http://www.w3.org/2000/01/rdf-schema#"; 
        //String reg = "http://websemantica.cl/Proyecto3/Regiones/"; 
        //String vocReg = "http://websemantica.cl/Proyecto3/Regiones/vocabulario/"; 
        String dbpedia = "http://dbpedia.org/resource/"; 
        String vocT ="http://www.w3.org/1999/02/22-rdf-syntax-ns#/";
             
        if(file1.isFile() && file2.isFile() && file3.isFile()) 
        { 
            modelOutput = ModelFactory.createDefaultModel(); 
            //regiones = ModelFactory.createDefaultModel(); 
             
            String line; 
            int cont = 0; 
             
            BufferedReader csvReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "ISO-8859-1"));
            BufferedReader csvReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "ISO-8859-1"));
            BufferedReader csvReader3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3), "ISO-8859-1"));
             
            while ((line = csvReader1.readLine()) != null) 
            { 
                listaProductos = line.split("\t"); 

                //System.out.println("Lista products: "+listaProductos[7]);
                productResource = modelOutput.createResource(nsProd + listaProductos[0]);

                Resource product = modelOutput.createResource(ns1 + listaProductos[0].replace(" ","_")); 

                Property productID = modelOutput.createProperty(vocProd, "ProductID"); 
                Property name = modelOutput.createProperty(vocProd, "Name"); 
                Property productNumber = modelOutput.createProperty(vocProd, "ProductNumber"); 
                Property makeFlag = modelOutput.createProperty(vocProd, "MakeFlag");
                Property finishedGoodsFlag = modelOutput.createProperty(vocProd, "FinishedGoodsFlag"); 
                Property color = modelOutput.createProperty(vocProd, "Color"); 
                Property safetyStockLevel = modelOutput.createProperty(vocProd, "SafetyStockLevel"); 
                Property reorderPoint = modelOutput.createProperty(vocProd, "ReorderPoint");; 
                Property standardCost = modelOutput.createProperty(vocProd, "StandardCost");; 
                Property listPrice = modelOutput.createProperty(vocProd, "ListPrice");; 
                Property size = modelOutput.createProperty(vocProd, "Size");; 
                Property sizeUnitMeasureCode = modelOutput.createProperty(vocProd, "SizeUnitMeasureCode");;
                Property weightUnitMeasureCode = modelOutput.createProperty(vocProd, "WeightUnitMeasureCode");
                Property weight = modelOutput.createProperty(vocProd, "Weight");
                Property daysToManufacture = modelOutput.createProperty(vocProd, "DaysToManufacture");
                Property productLine = modelOutput.createProperty(vocProd, "ProductLine");
                Property pClass = modelOutput.createProperty(vocProd, "Class");
                Property style = modelOutput.createProperty(vocProd, "Style");
                Property productSubcategoryID = modelOutput.createProperty(vocProd, "ProductSubcategoryID");
                Property productModelID = modelOutput.createProperty(vocProd, "ProductModelID");
                Property sellStartDate = modelOutput.createProperty(vocProd, "SellStartDate");
                Property sellEndDate = modelOutput.createProperty(vocProd, "SellEndDate");
                Property discontinuedDate = modelOutput.createProperty(vocProd, "DiscontinuedDate");
                Property rowguide = modelOutput.createProperty(vocProd, "Rowguide");
                Property modifiedDate = modelOutput.createProperty(vocProd, "ModifiedDate");

                modelOutput.add(productResource, productID, listaProductos[0]);
                modelOutput.add(productResource, name, listaProductos[1].replace(" ", "_"));
                modelOutput.add(productResource, productNumber, listaProductos[2].replace(" ", "_"));
                modelOutput.add(productResource, makeFlag, listaProductos[3]);
                modelOutput.add(productResource, finishedGoodsFlag, listaProductos[4]);
                modelOutput.add(productResource, color, listaProductos[5].trim().replace(" ", "_"));
                modelOutput.add(productResource, safetyStockLevel, listaProductos[6]);
                modelOutput.add(productResource, reorderPoint,listaProductos[7]);
                modelOutput.add(productResource, standardCost,listaProductos[8]);
                modelOutput.add(productResource, listPrice, listaProductos[9]);
                modelOutput.add(productResource, size, listaProductos[10].trim().replace(" ", "_"));
                modelOutput.add(productResource, sizeUnitMeasureCode, listaProductos[11].trim().replace(" ", "_"));
                modelOutput.add(productResource, weightUnitMeasureCode, listaProductos[12].trim().replace(" ", "_"));
                modelOutput.add(productResource, weight, listaProductos[13]);
                modelOutput.add(productResource, daysToManufacture, listaProductos[14]);
                modelOutput.add(productResource, productLine, listaProductos[15].trim().replace(" ", "_"));
                modelOutput.add(productResource, pClass, listaProductos[16].trim().replace(" ", "_"));
                modelOutput.add(productResource, style, listaProductos[17].trim().replace(" ", "_"));
                modelOutput.add(productResource, productSubcategoryID, listaProductos[18]);
                modelOutput.add(productResource, productModelID, listaProductos[19]);
                modelOutput.add(productResource, sellStartDate, listaProductos[20].replace(" ", "T"));
                modelOutput.add(productResource, sellEndDate, listaProductos[21].replace(" ", "T"));
                modelOutput.add(productResource, discontinuedDate, listaProductos[22].replace(" ", "T"));
                modelOutput.add(productResource, rowguide, listaProductos[23]);
                modelOutput.add(productResource, modifiedDate, listaProductos[24].replace(" ", "T"));
            }
            
            while((line = csvReader2.readLine()) != null)
            {
                listaVendors = line.split("\t");
                
                productResource = modelOutput.createResource(nsVend + listaVendors[0]);

                Resource vendor = modelOutput.createResource(ns1 + listaVendors[0].replace(" ","_")); 
                
                Property businessEntityID = modelOutput.createProperty(vocVend, "BusinessEntityID");
                Property accountNo = modelOutput.createProperty(vocVend, "AccountNumber");
                Property name = modelOutput.createProperty(vocVend, "Name");
                Property creditRating = modelOutput.createProperty(vocVend, "CreditRating");
                Property preferredVendorStatus = modelOutput.createProperty(vocVend, "PreferredVendorStatus");
                Property activeFlag = modelOutput.createProperty(vocVend, "ActiveFlag");
                Property purchasingWebServiceURL = modelOutput.createProperty(vocVend, "PurchasingWebServiceURL");
                Property modifiedDate = modelOutput.createProperty(vocVend, "ModifiedDate");
                
                modelOutput.add(productResource, businessEntityID, listaVendors[0]);
                modelOutput.add(productResource, accountNo, listaVendors[1].trim().replace(" ", "_"));
                modelOutput.add(productResource, name, listaVendors[2].trim().replace(" ", "_"));
                modelOutput.add(productResource, creditRating, listaVendors[3]);
                modelOutput.add(productResource, preferredVendorStatus, listaVendors[4]);
                modelOutput.add(productResource, activeFlag, listaVendors[5]);
                modelOutput.add(productResource, purchasingWebServiceURL, listaVendors[6].trim().replace(" ", "_"));
                modelOutput.add(productResource, modifiedDate, listaVendors[7].trim().replace(" ", "T"));
            }
            
            while((line = csvReader3.readLine()) != null)
            {
                listaProdVend = line.split("\t");
                
                productResource = modelOutput.createResource(nsPV + listaProdVend[0] + "-" + listaProdVend[1]);

                Resource ProductVendor = modelOutput.createResource(ns1 + listaVendors[0] + "-" + listaProdVend[1]); 
                
                Property productID = modelOutput.createProperty(vocPV, "ProductID");
                Property businessEntityID = modelOutput.createProperty(vocPV, "EntityBusinessID");
                Property avgLeadTime = modelOutput.createProperty(vocPV, "AverageLeadTime");
                Property standardPrice = modelOutput.createProperty(vocPV, "StandardPrice");
                Property lastReceiptCost = modelOutput.createProperty(vocPV, "LastReceiptCost");
                Property lastReceiptDate = modelOutput.createProperty(vocPV, "LastReceiptDate");
                Property minOrderQty = modelOutput.createProperty(vocPV, "MinOrderQty");
                Property maxOrderQty = modelOutput.createProperty(vocPV, "MaxOrderQty");
                Property onOrderQty = modelOutput.createProperty(vocPV, "OnOrderQty");
                Property unitMeasueCode = modelOutput.createProperty(vocPV, "UnitMeasureCode");
                Property modifiedDate = modelOutput.createProperty(vocPV, "ModifiedDate");
                
                modelOutput.add(productResource, productID, listaProdVend[0]);
                modelOutput.add(productResource, businessEntityID, listaProdVend[1]);
                modelOutput.add(productResource, avgLeadTime, listaProdVend[2]);
                modelOutput.add(productResource, standardPrice, listaProdVend[3]);
                modelOutput.add(productResource, lastReceiptCost, listaProdVend[4]);
                modelOutput.add(productResource, lastReceiptDate, listaProdVend[5]);
                modelOutput.add(productResource, minOrderQty, listaProdVend[6]);
                modelOutput.add(productResource, maxOrderQty, listaProdVend[7]);
                modelOutput.add(productResource, onOrderQty, listaProdVend[8]);
                modelOutput.add(productResource, unitMeasueCode, listaProdVend[9].trim().replace(" ", "_"));
                modelOutput.add(productResource, modifiedDate, listaProdVend[10].trim().replace(" ", "T"));
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
        File salidaProducts = new File("output.ttl"); 
        FileOutputStream fos = new FileOutputStream(salidaProducts); 
        modelOutput. write(fos, "TURTLE"); 
        //model.write(System.out, "TURTLE"); 
    } 
    
    public static String generateCode() 
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String fullalphabet = alphabet + alphabet.toLowerCase() + "0123456789";
        Random random = new Random();

        char code = fullalphabet.charAt(random.nextInt(9));

        return Character.toString(code);
    }
     
} 
