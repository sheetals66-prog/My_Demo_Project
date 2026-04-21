package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


	public class ExcelReader {
		
		public static Object[][] readExcel(int sheetIndex) throws IOException{
			
			FileInputStream file=new FileInputStream("src/test/resources/TestData.xlsx");
			XSSFWorkbook book=new XSSFWorkbook(file);
			
			XSSFSheet sheet=book.getSheetAt(sheetIndex);
			
			int rows=sheet.getPhysicalNumberOfRows();
			
			int cols=sheet.getRow(0).getPhysicalNumberOfCells();
			Object[][] data=new Object[rows-1][cols];
			
		   for(int i=1;i<rows;i++) {
			   
			   XSSFRow row=sheet.getRow(i);
			   
			   for(int j=0;j<cols;j++) {
				   XSSFCell cell=row.getCell(j);
				   
				   switch (cell.getCellType()) {
				case STRING: 
					String value=cell.getStringCellValue();
					data[i-1][j] = value.trim();
					System.out.println(value);
					break;
				
				case NUMERIC:
					int value1=(int) cell.getNumericCellValue();
					data[i-1][j] = value1;
					System.out.println(value1);
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + cell.getCellType());
				}
				   
				   
			   }
		 
			   
		   }
		   return data;
			
			
			
			
		}
		
		
			
		}
		
