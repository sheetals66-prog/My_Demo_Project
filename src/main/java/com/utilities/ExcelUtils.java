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

public class ExcelUtils {
    public static Object[][] getData(String filePath, String sheetName) throws IOException {
        List<Object[]> dataList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet not found: " + sheetName);
            }

            Row header = sheet.getRow(0);
            if (header == null) {
                return new Object[0][];
            }

            int cols = header.getLastCellNum();
            int lastRow = sheet.getLastRowNum();

            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);

                // Skip empty rows
                if (row == null || row.getCell(0) == null || row.getCell(0).toString().trim().isEmpty()) {
                    continue;
                }

                Object[] rowData = new Object[cols];

                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        rowData[j] = "";
                        continue;
                    }

                    CellType type = cell.getCellType();
                    switch (type) {
                        case STRING:
                            rowData[j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData[j] = cell.getDateCellValue().toString();
                            } else {
                                double d = cell.getNumericCellValue();
                                if (d == (long) d) {
                                    rowData[j] = String.valueOf((long) d);
                                } else {
                                    rowData[j] = String.valueOf(d);
                                }
                            }
                            break;
                        case BOOLEAN:
                            rowData[j] = cell.getBooleanCellValue();
                            break;
                        case FORMULA:
                            // Use cached formula result
                            CellType cachedType = cell.getCachedFormulaResultType();
                            if (cachedType == CellType.STRING) {
                                rowData[j] = cell.getStringCellValue();
                            } else if (cachedType == CellType.NUMERIC) {
                                double d = cell.getNumericCellValue();
                                if (d == (long) d) rowData[j] = String.valueOf((long) d);
                                else rowData[j] = String.valueOf(d);
                            } else if (cachedType == CellType.BOOLEAN) {
                                rowData[j] = cell.getBooleanCellValue();
                            } else {
                                rowData[j] = "";
                            }
                            break;
                        case BLANK:
                        default:
                            rowData[j] = "";
                    }
                }

                dataList.add(rowData);
            }

        } catch (EncryptedDocumentException e) {
            throw new IOException(e);
        }

        return dataList.toArray(new Object[0][]);
    }
}