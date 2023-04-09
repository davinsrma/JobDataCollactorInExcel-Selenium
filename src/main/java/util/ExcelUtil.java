package util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class ExcelUtil {

    static int rowNum=0;
    public static void writeDatainExce(String companyName, String jobTitle, String location, String salary){
        // create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");

        // create some data
        Object[][] data = {

                {companyName, jobTitle, location,salary},

        };

        // write the data to the sheet

        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
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
