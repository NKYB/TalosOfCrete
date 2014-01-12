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
            outputData[i] = inputData[i][2] * inputData[i][1] + inputData[i][0] ;
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

//    void addConstant(int constantValue) {
//        int numColumns = inputData[0].length +1;
//        float[][] inputDataNew = new float[inputData.length][numColumns];
//        for (int i = 0; i < inputData.length; i++) {
//            for (int j = 0; j < numColumns; j++) {
//                if (j<numColumns-1){
//                    inputDataNew[i][j] = inputData[i][j];
//                } else {
//                    inputDataNew[i][j] = constantValue;
//                }
//            }
//        }
//        inputData = new float[inputData.length][numColumns];
//        inputData = inputDataNew;
//    }
}
