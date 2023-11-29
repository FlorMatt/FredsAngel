package com.bookstore.ui;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bookstore.model.Book;

public class ExcelExporter {
	
	public static void generateInventoryAudit(List<Book> books, String filePath) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Inventory Audit Report");
			
			//create the header row of the excel workbook
			Row headerRow = sheet.createRow(0);
			String[] headers = {"Isbn Number, Book Title, Genre, Author, Purchase Cost, Reatil Price, Inventory Qty"};
			
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
			}
			
			//add the data to the workbook
			for (int a = 0; a < books.size(); a++) {
				Row dataRow = sheet.createRow(a + 1);
				Book book = books.get(a);
				
				//populate cells
				dataRow.createCell(0).setCellValue(book.getIsbnNumber());;
				dataRow.createCell(1).setCellValue(book.getBookTitle());
				dataRow.createCell(2).setCellValue(book.getGenre());
				dataRow.createCell(3).setCellValue(book.getAuthor().getFullName());
				dataRow.createCell(4).setCellValue(book.getPurchaseCost());
				dataRow.createCell(5).setCellValue(book.getRetailPrice());
				dataRow.createCell(6).setCellValue(book.getInventory().getQty());
			}
			
			//write the workbook to the file
			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			}
			
			System.out.println("The report was successfully exported to: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
