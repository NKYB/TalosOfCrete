/**
 * Create data sequenced correctly from both external sources and generated
 * for testing
 * 
 * @author nkyb
 */
package com.talosofcrete.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    public float[][] inputData;
    
    public float[] outputData;
    
    /**
     * Creates a random input array and uses those values to create the output
     * value. Used for testing, should always result in a solution.
     * 
     * @param numRows
     * @param numCols 
     */
    public void createRandom(int numRows, int numCols){
        inputData = new float[numRows][numCols];
        outputData = new float[numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j<numCols){
                    inputData[i][j] = (int)(java.lang.Math.random()*100);
                }
            }
            outputData[i] = inputData[i][2] * inputData[i][1] / 0.1F;
        }
    }
    
    /**
     * Creates a sequential input array and uses those values to create the 
     * output value. Used for testing, should always result in a solution.
     * 
     * @param numRows
     * @param numCols 
     */
    public void createSequential(int numRows, int numCols){
        inputData = new float[numRows][numCols];
        outputData = new float[numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j<numCols){
                    inputData[i][j] = (i*numRows)+j;
                }
            }
            outputData[i] = inputData[i][2] * inputData[i][1] + inputData[i][0];
        }
    }
    
    /**
     * Import from a CSV file
     * 
     * TODO: change the path from hard coded!
     */
    public void importCSVData(){
        
        try{
//            BufferedReader dataBR = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Documents\\GitHub\\TalosOfCrete\\data\\weather_data.csv")));
//            BufferedReader dataBR = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Documents\\GitHub\\TalosOfCrete\\data\\yahoo2.csv")));
            BufferedReader dataBR = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Documents\\GitHub\\TalosOfCrete\\data\\compfirm.csv")));
            String line = "";

            ArrayList<String> dataArr = new ArrayList<String>(); 
            int countLines = 0;
            int countFields = 0;
            while ((line = dataBR.readLine()) != null) { 
                if (!line.equals("")){
                    countLines++;
                    countFields = line.split(",").length;
                    dataArr.add(line); 
                }
            }

            inputData = new float[countLines][countFields-1];
            outputData = new float[countLines];

            for (int i = 0; i < dataArr.size(); i++) {
                String[] lineData = dataArr.get(i).split(",");
                for (int x = 0; x < lineData.length; x++) {
                    if (x < lineData.length-1){
                        inputData[i][x] = Float.parseFloat(lineData[x]);
                    } else {
                        outputData[i] = Float.parseFloat(lineData[x]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
