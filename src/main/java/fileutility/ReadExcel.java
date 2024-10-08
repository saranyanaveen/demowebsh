package fileutility;

	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.*;

	import java.io.FileInputStream;
	import java.io.IOException;

	public class ReadExcel {
	    public static Object[][] readExcelData(String filePath) throws IOException {
	        FileInputStream fis = new FileInputStream("src\test\resources");
	        Workbook workbook = new HSSFWorkbook(fis); // Use HSSFWorkbook for .xls files
	        Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
	        Object[][] data = new Object[rowCount - 1][colCount]; // -1 to exclude header row

	        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < colCount; j++) {
	                Cell cell = row.getCell(j);
	                data[i - 1][j] = cell.toString();
	            }
	        }

	        workbook.close();
	        fis.close();
	        return data;
	        
	    }
	}



