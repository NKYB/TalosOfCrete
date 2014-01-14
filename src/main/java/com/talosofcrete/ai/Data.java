package com.talosofcrete.ai;

public class Data {
    public float[][] inputData;
    
    public float[] outputData;
    
    public void createRandom(int numRows, int numCols){
        inputData = new float[numRows][numCols];
        outputData = new float[numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j<numCols){
                    inputData[i][j] = (int)(java.lang.Math.random()*100);
                }
            }
            outputData[i] = inputData[i][2] * inputData[i][1] * inputData[i][3];
        }
    }
    
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
}
