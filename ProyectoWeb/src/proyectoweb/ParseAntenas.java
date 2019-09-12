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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Nicolás Hervias
 */
public class ParseAntenas
{
    private File antenas;
    private String path;
    
    public ParseAntenas() throws FileNotFoundException, IOException
    {
        this.path = "Antenas.csv";
        this.antenas = new File(path);
        this.readCSV(this.antenas);
    }
    
    public void readCSV(File file) throws FileNotFoundException, IOException
    {
        if(file.isFile())
        {
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));
            for (int i = 0; i < 10; i++)
            {
                System.out.println(csvReader.readLine());
            }
        }
        else
        {
            System.out.println("Archivo no váldo.");
            return;
        }
        
    }
}
