
package com.timecalculator.domain;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputFileManager {

    private File file;
    private List<String> ncList; // File as List of Strings

    public InputFileManager(File file){
        this.file = file;
        this.ncList = makeStringList();
    }

    public InputFileManager() {
        chooseFile();
    }

    private void chooseFile(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.file = chooser.getSelectedFile();
            this.ncList = makeStringList();
        }

    }

    private List<String> makeStringList(){
        List<String> gCodeList = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = null;
            while ( (line = reader.readLine()) != null) {
                gCodeList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gCodeList;
    }

    public List<String> extractOP(TextField blockN0){
        String blockNo = stringifyBlockNo(blockN0);
        if (!checkIfBlockExists(blockN0) || blockNo.isEmpty()){
            return ncList;
        }
        int opStart = 0;
        int opEnd = 0;
        for (int i = 0; i < ncList.size(); i++) {
            String iBlock = ncList.get(i);
            if (iBlock.contains(blockNo)) {
                opStart = i;
                for (int j = opStart; j < ncList.size(); j++) {
                    String jBlock = ncList.get(j);
                    if ( (jBlock.contains("M1") || jBlock.contains("M01")) && !jBlock.contains("(") ){
                        opEnd = j;
                        break;
                    }
                }
                break;
            }
        }
        return ncList.subList(opStart, opEnd + 1);
    }

    public boolean checkIfBlockExists(TextField textField){
        String blockNo = stringifyBlockNo(textField);
        if (blockNo.isEmpty()) return false;
        for (String s : ncList) {
            if (s.contains(blockNo)) {
                return true;
            }
        }
        return false;
    }

    // Turn user input block No to string, return "" if no
    private String stringifyBlockNo(TextField text){
        String blockNo = text.getText().toUpperCase();
        if (!blockNo.matches((".*\\d.*"))){
            return "";
        }
        if (!blockNo.contains("N")){
            blockNo = "N" + blockNo;
        }
        return  blockNo;
    }

    //Replace Operation
    public static List<String> replaceOpAndSaveFile(InputFileManager procFile, List<String> newOp, List<String> oldOp){
        List<String> ncList = procFile.getNcList();
        int opStart = Collections.indexOfSubList(ncList, oldOp);
        int opEnd = 0;
        for (int i = opStart; i < ncList.size(); i++) {
            String block = ncList.get(i);
            if ( (block.contains("M1") || block.contains("M01")) && !block.contains("(") ){
                opEnd = i;
                break;
            }
        }
        ncList.subList(opStart, opEnd + 1).clear();
        for (int i = 0; i <newOp.size() ; i++) {
            ncList.add(i + opStart, newOp.get(i));
        }

        return ncList;
    }

    public List<String> getNcList() {
        return ncList;
    }

    public File getFile() {
        return file;
    }
}