package com.example.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.model.Category;

public class Helper {
	public static String[] HEADERS= {
			"id",
			"title",
			"description",
			"coverImage"
	};
	public static String SHEET_NAME="category_data";
	public static ByteArrayInputStream dataToExcel(List<Category> list)
	{
		//create work book
		Workbook workBook=new XSSFWorkbook();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			
			//create sheet
			Sheet sheet = workBook.createSheet(SHEET_NAME);
			
			//create row: header row
			Row row = sheet.createRow(0);
			
			//create cells
			for(int i=0;i<HEADERS.length;i++)
			{
				Cell cell = row.createCell(i);
				cell.setCellValue(HEADERS[i]);
			}
			
			//value rows
			int rowIndex=1;
			for(Category c:list)
			{
				Row dataRow = sheet.createRow(rowIndex);
				rowIndex++;
				dataRow.createCell(0).setCellValue(c.getCategoryId());
				dataRow.createCell(1).setCellValue(c.getTitle());
				dataRow.createCell(2).setCellValue(c.getDescription());
				dataRow.createCell(3).setCellValue(c.getCoverImage());
			}
			workBook.write(out);
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Failed to import data in excel");
		}finally {
			try {
				workBook.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
}
