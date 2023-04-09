package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel1 {


    public static void writeExcel(String fileFullPath, String sheetName, String[] dataToWrite)
            throws IOException {
//        File file = new File(fileFullPath);
        FileInputStream inputStream = new FileInputStream(fileFullPath);
        Workbook workbook = new XSSFWorkbook(inputStream);

//        assert workbook != null;
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        Row newRow = sheet.createRow(rowCount + 1);

        // Create a loop over the cell of newly created Row
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }

        // Close input stream
        inputStream.close();

        // Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(fileFullPath);

        // write data in the excel file
        workbook.write(outputStream);

        // close output stream
        outputStream.close();
    }
}
