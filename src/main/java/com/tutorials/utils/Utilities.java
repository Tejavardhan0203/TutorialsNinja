package com.tutorials.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=10;
	
	public static String generateemailwithtimestamp() {
		Date date=new Date();
		String timestamp=date.toString().replace(" ","_").replace(":", "_");
		return "tejavardhan"+timestamp+"@gmail.com";	
	}
	
	public static Object[][] gettestdatafromexcel(String sheetname) {
		File excelfile=new File(System.getProperty("user.dir"+"\\src\\main\\tutorialninja\\"));
		XSSFWorkbook workbook = null;
		try {
		FileInputStream fisexcel=new FileInputStream(excelfile);
		workbook=new XSSFWorkbook(fisexcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetname);
		
		int rowcount=sheet.getLastRowNum();
		int columncount=sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[rowcount][columncount];
		for(int i=0;i<rowcount;i++) {
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<columncount;j++) {
				XSSFCell cell=row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}
	
}
