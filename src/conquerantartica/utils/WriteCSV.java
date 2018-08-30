/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.utils;

/**
 *
 * @author franc
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.LinkedList;

public class WriteCSV {

    public static void print(String fileName, String charset, LinkedList<String[]> lstSA) throws IOException 
    {
        PrintWriter printWriter = new PrintWriter(
            new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(fileName), charset)), true);

        for (String[] sArr : lstSA)
            for (int i = 0; i < sArr.length; i++)
                if (i < (sArr.length - 1))
                    printWriter.print(sArr[i] + ";");
                else
                    printWriter.print(sArr[i] + "\r\n");

        printWriter.close();
    }
    
    public static void printRow(String fileName, String charset, String[] newRow) throws IOException 
    {
        FileWriter fileWriter = new FileWriter(new File(fileName), true);
        
        for (int i = 0; i < newRow.length; i++)
        {
                if (i < (newRow.length - 1))
                    fileWriter.append(newRow[i] + ";");
                else
                    fileWriter.append(newRow[i] + "\r\n");
        }
        
        fileWriter.close();
        
        
    }
} // end class