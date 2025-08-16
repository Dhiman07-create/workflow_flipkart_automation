package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.Iterator;

public class ExcelUtils {
    @DataProvider(name = "searchData")
    public static Object[][] searchData() throws Exception {
        return loadSheet("testdata.xlsx", "Search");
    }

    private static Object[][] loadSheet(String file, String sheetName) throws Exception {
        try (InputStream in = ExcelUtils.class.getClassLoader().getResourceAsStream(file)) {
            Workbook wb = new XSSFWorkbook(in);
            Sheet sh = wb.getSheet(sheetName);
            int rows = sh.getPhysicalNumberOfRows() - 1;
            int cols = sh.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[rows][cols];
            Iterator<Row> it = sh.iterator();
            it.next(); // skip header
            int r = 0;
            while (it.hasNext()) {
                Row row = it.next();
                for (int c = 0; c < cols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[r][c] = cell.toString();
                }
                r++;
            }
            return data;
        }
    }
}
