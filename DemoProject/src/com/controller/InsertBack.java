package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@WebServlet("/InsertBack")
public class InsertBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("userdb");
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("name");
        cell = row.createCell(1);
        cell.setCellValue("country");
        cell = row.createCell(2);
        cell.setCellValue("city");
        
        FileOutputStream out = new FileOutputStream(new File(
                "exceldatabase.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("File Successfully created");
	}

}
