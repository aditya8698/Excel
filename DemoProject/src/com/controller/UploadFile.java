package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.dao.FileDao;
import com.model.Bean;

@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Bean bn = new Bean();
	FileDao dao;

	@Override
	public void init() throws ServletException {
		dao = new FileDao();
	}

	private ArrayList<String> bean = new ArrayList<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();

		HSSFWorkbook wb = new HSSFWorkbook(fileContent);

		HSSFSheet sheet = wb.getSheetAt(0);

		int b = 0, t = 1;
		ArrayList<String> local = new ArrayList<>();
		
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (Row row : sheet) // iteration over row using for each loop
		{
			for (Cell cell : row) // iteration over cell using for each loop
			{
				switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:

//					System.out.print(cell.getNumericCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_STRING:
					
//					 if(b==1) {
//						 bn.setName(cell.getStringCellValue()); 
//						 	  }
//					 if(b==2) {
//					 bn.setCity(cell.getStringCellValue()); 
//					 		  } 
//					 if(b==3) {
//					 bn.setAddress(cell.getStringCellValue());
//					 int y = dao.insertData(bn); 
//					 		 }
					 
					local.add(cell.getStringCellValue());
//					System.out.print("cell values "+cell.getStringCellValue());
					break;
				}
				
			}
			
			  if (t == 1) { 
//			  System.out.println("arraylist"+local); 
			  local.removeAll(local);
//			  System.out.println("removeall"+local.removeAll(local));
			  }
			  if(t>1) {
//				  System.out.println("123123123"+local);
				  int a = dao.fetch_Data(local);
//				  System.out.println(a);
				  if(a!=1) {
						setBean(local);
						int y=dao.insert_table_data(bean);
//						System.out.println("int return y"+""+y);
						local.removeAll(local);
					}else {
//						System.out.println("asdas"+local);
						local.removeAll(local);
					}
			  }
			  if(t>2) {
				  try {
					  System.out.println("writing in excel");
					int a = dao.getData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			  }
			 
			 
//			b = 0;
//			System.out.println();
			
//			 int no_column = sheet.getRow(0).getLastCellNum();
//			 System.out.println(no_column);
			t++; 
		}
//		if (t == 0) { System.out.println("in show");
//		 RequestDispatcher rd = request.getRequestDispatcher("./show.jsp"); 
//		 rd.forward(request, response); 
//		 }
	}

	 
	public ArrayList<String> getBean() {
		return bean;
	}

	public void setBean(ArrayList<String> bean) {
		this.bean = bean;
	}
}
	
