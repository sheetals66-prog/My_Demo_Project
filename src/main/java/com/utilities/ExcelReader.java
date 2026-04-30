package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static Object[][] readExcel(int sheetIndex) throws IOException {

		FileInputStream file = new FileInputStream("E:\\Myntra_Framework\\MyntraTestData.xlsx");

		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheetAt(sheetIndex);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rows - 1][cols];

		for (int i = 1; i < rows; i++) {

			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);

				if (cell == null) {
					data[i - 1][j] = "";
					continue;
				}

				CellType type = cell.getCellType();

				switch (type) {

				case STRING:
					data[i - 1][j] = cell.getStringCellValue().trim();
					break;

				case NUMERIC:
					// convert safely (avoid .0 issue)
					data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
					break;

				case BLANK:
					data[i - 1][j] = "";
					break;

				default:
					data[i - 1][j] = cell.toString();
					break;
				}
			}
		}

		book.close();
		file.close();

		return data;
	}
}
