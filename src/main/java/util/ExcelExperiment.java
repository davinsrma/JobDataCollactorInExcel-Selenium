package util;

import java.io.*;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExperiment {

    static Row row;
    static Cell cell;
    static Column col;
    static FileOutputStream fos;
    static Object[][] data;
//    static int rowNum = 1;
    static int colNum;
    public static void main(String[] args) throws IOException {
        writeExcel2("./JobDataExcel/JobData.xlsx","Deepika","Padukon","aunty");
    }


        public static void writeExcel2(String filefullPath,String cellValue1,String cellValue2,String cellValue3) throws IOException {
            FileInputStream fis = new FileInputStream("./JobDataExcel/JobData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Data");
            for(int i=1;i<=30;i++){
                for(int j=1;j<=i;j++) {
                    row = sheet.createRow(i);
                    row.createCell(i).setCellValue(cellValue1);
                    row.createCell(i+1).setCellValue(cellValue2);
                    row.createCell(i+2).setCellValue(cellValue3);

            fos = new FileOutputStream(filefullPath);
                }
            }
            workbook.write(fos);
            fos.close();
        }





        // create a new workbook and sheet
    public static void writeExcel1(String value1,String value2,String value3,int rowNum){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");

        // create some data

        data = new Object[][]{
                {"Name", "Age", "City"},
                {value1, value2, value3}




        };


        // write the data to the sheet
//        rowNum = 1;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowNum);
            colNum = 0;
            for (Object field : rowData) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        // write the workbook to a file
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("./JobDataExcel/JobData.xlsx"));
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
