package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;

public class ExcelUtil {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        Object[][] data = null;

        try {
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.xlsx"
            );

            Workbook wb = new XSSFWorkbook(file);
            Sheet sheet = wb.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();

            data = new Object[rows - 1][2];

            for (int i = 1; i < rows; i++) {
                data[i - 1][0] = sheet.getRow(i).getCell(0).toString();
                data[i - 1][1] = sheet.getRow(i).getCell(1).toString();
            }

            wb.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}