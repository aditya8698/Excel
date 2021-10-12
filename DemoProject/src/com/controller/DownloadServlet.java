package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


import com.dbConnection.DConnection;


@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");  
	      PrintWriter out = response.getWriter();  
//	      String filename = "db1.sql";   
//	      String filepath = "D:\\sql data\\";   
//	      response.setContentType("APPLICATION/OCTET-STREAM");   
//	      response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
//	      
//	      FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
//          
//	      int i;   
//	      while ((i=fileInputStream.read()) != -1) {  
//	      out.write(i);   
//	      System.out.println(fileInputStream);
//	      }   
//	      fileInputStream.close();   
//	      out.close();   
		 try {
	    Workbook workbook = new HSSFWorkbook();   
	    
	    HSSFSheet sheet = (HSSFSheet) workbook.createSheet("January");   
	  //creating the 0th row using the createRow() method  
	  HSSFRow rowhead = sheet.createRow((short)0);  
	  //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
	  rowhead.createCell(0).setCellValue("S.No.");  
	  rowhead.createCell(1).setCellValue("Customer Name");  
	  rowhead.createCell(2).setCellValue("Account Number");  
	  rowhead.createCell(3).setCellValue("e-mail");  
	  rowhead.createCell(4).setCellValue("Balance");  
	  //creating the 1st row  
	  HSSFRow row = sheet.createRow((short)1);  
	  //inserting data in the first row  
	 
	 
	  
	  
	  //my personal code
	  DConnection db = new DConnection();
		Connection con = db.getConnection();
		ResultSet rs = null;
		int a;
	  String FETCH_SQL = "select * from data";
		try {
			PreparedStatement st = con.prepareStatement(FETCH_SQL);
			rs = st.executeQuery();
		
     
      
     ResultSetMetaData metadata = rs.getMetaData();
//    
     int columncount = metadata.getColumnCount();
//   
     
     
     row = sheet.createRow(0);
     int header = 0;
     while(header<columncount) {
  	   row.createCell(header).setCellValue(metadata.getColumnName(header+1));
  	  
  	   header++;
     }
   
      int i = 2;
      int j = 1;
      int wctrl = 0;
      int k=0;
      		

      while (rs.next()) {
          row = sheet.createRow(i);
        row.createCell(0).setCellValue(rs.getString("name"));
         
         
        
          i++;
      }
     
//          while (rs.next()) {
//          System.out.println("get string "+rs.getString(1)+"two "+rs.getString(2)+"three"+rs.getString(3));
//          row = spreadsheet1.createRow(i);
//          while(wctrl<columncount) {
//          cell = row.createCell(k);
//          System.out.println("rows"+i);
//          cell.setCellValue(rs.getString(j));
//          System.out.println("column "+j);
//          j++;
//          k++;
//          wctrl++;
//          	}
//          	wctrl=0;
//          	j=1;
//          	i++;
//          	k=0;
//          }
             
      
          System.out.println("file created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	  
	  //my personal code is closed
		
		
	  FileOutputStream fileOut = new FileOutputStream("D:\\abc.xls");  
	  workbook.write(fileOut);  
	  //closing the Stream  
	  fileOut.close();  
	  //closing the workbook  
	  workbook.close();  
	  //prints the message on the console  
	  System.out.println("Excel file has been generated successfully.");  
	  }   
	  catch (Exception e)   
	  {  
	  e.printStackTrace();  
	  }  
	    
	      }  	      
	}


