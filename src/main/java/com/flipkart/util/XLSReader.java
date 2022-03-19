package com.flipkart.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;


public class XLSReader {
	
	private static XSSFWorkbook workBook;
	private static XSSFSheet workSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;
   
	public static Object[][] getExcelTestData(String SheetName) {
		String fileName= System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx";
		try {
			FileInputStream fis = new FileInputStream(new File(fileName));
				workBook = new XSSFWorkbook(fis);
				workSheet = workBook.getSheet(SheetName); 
				
		}catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		 int totalRowCount = workSheet.getLastRowNum();
		 int totalColumnCount = workSheet.getRow(0).getLastCellNum();
				
				Object[][] data = new Object[totalRowCount][totalColumnCount];
				// System.out.println(sheet.getLastRowNum() + "--------" +
				// sheet.getRow(0).getLastCellNum());
				for (int i = 0; i < totalRowCount; i++) {
					for (int k = 0; k < totalColumnCount; k++) {
						data[i][k] = workSheet.getRow(i + 1).getCell(k).toString();
						//System.out.println(data[i][k]);
					}
				}
		return data;

	}
}
