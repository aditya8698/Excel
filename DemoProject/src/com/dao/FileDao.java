package com.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dbConnection.DConnection;
import com.model.Bean;
import com.mysql.cj.result.Row;

public class FileDao {
	DConnection db = new DConnection();
	Connection con = db.getConnection();
	ResultSet rs = null;
	int a;
	

	public int insertData(Bean bn) {
		String INSERT_SQL = "insert into data (name,city,country)values (?,?,?)";
		PreparedStatement st;
		try {
			st = con.prepareStatement(INSERT_SQL);
			st.setString(1, bn.getName());
			st.setString(2, bn.getCountry());
			st.setString(3, bn.getCity());
			a = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	
	public int getData() throws Exception{
		String FETCH_SQL = "select * from data";
		try {
			PreparedStatement st = con.prepareStatement(FETCH_SQL);
			rs = st.executeQuery();
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet1
            = workbook.createSheet("user db");
        XSSFRow row;
        XSSFCell cell = null;
        
       ResultSetMetaData metadata = rs.getMetaData();
       String head = metadata.getColumnName(1);
       int columncount = metadata.getColumnCount();
       System.out.println("cloumn"+columncount);
       
       
       row = spreadsheet1.createRow(0);
       int header = 0;
       while(header<columncount) {
    	   cell = row.createCell(header);
    	   cell.setCellValue(metadata.getColumnName(header+1));
    	   header++;
       }
     
        int i = 2;
        int j = 1;
        int wctrl = 0;
        int k=0;
        		
 
        while (rs.next()) {
            row = spreadsheet1.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("name"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("country"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("city"));
          
            i++;
        }
       
//            while (rs.next()) {
//            System.out.println("get string "+rs.getString(1)+"two "+rs.getString(2)+"three"+rs.getString(3));
//            row = spreadsheet1.createRow(i);
//            while(wctrl<columncount) {
//            cell = row.createCell(k);
//            System.out.println("rows"+i);
//            cell.setCellValue(rs.getString(j));
//            System.out.println("column "+j);
//            j++;
//            k++;
//            wctrl++;
//            	}
//            	wctrl=0;
//            	j=1;
//            	i++;
//            	k=0;
//            }
               
        FileOutputStream output = new FileOutputStream(new File(
                "D:\\new record.xlsx"));
            workbook.write(output);
            System.out.println("file created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	


//	public int createTable(ArrayList<String> bean) {
//		int size = bean.size();
//		String q_data="",clean_data="";
//		for(int i=0;i<size;i++) {
//			q_data=q_data+bean.get(i)+" "+"varchar(20),";
//		}
//		char c[]=q_data.toCharArray();
//		int array_length=c.length;
//		System.out.println(array_length);
//		for(int i=0;i<(array_length-1);i++) {
//			clean_data=clean_data+c[i];
//		}
//		System.out.println(clean_data);
//		String CREATE_TABLE= "create table data "+"("+clean_data+")";
//		try {
//			PreparedStatement st = con.prepareStatement(CREATE_TABLE);
//			a = st.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return a;
//	}

//	public int insertData(ArrayList<String> bean) {
//		String INSERT_DATA = "insert into data (name,country,city) values (?,?,?)";
//		int i=0;
//		try {
//			PreparedStatement st = con.prepareStatement(INSERT_DATA);
//			while(i<bean.size()) {
//				st.setString(1, bean.get(1));
//				st.setString(1, bean.get(2));
//				st.setString(1, bean.get(3));
//				i++;
//			}
//		 a = st.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return a;
//	}


	public int insert_table_data(ArrayList<String> local) {
	
			String q="INSERT INTO data (name,country,city) VALUES (?,?,?)";
			int i=0;
			try {
				PreparedStatement ps = con.prepareStatement(q);
				System.out.println("sadasrar"+local.size());
				while(i<local.size()) {
				ps.setString(i+1, local.get(i));
				i++;
				}
				a=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return a;
	}


	public int fetch_Data(ArrayList<String> local) {
		String q="SELECT * FROM data WHERE city=?";
//		fetchn();
		Bean bn=new Bean();
//		System.out.println("arraylist data"+ local.get(2));
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, local.get(2));
			this.rs=ps.executeQuery();
			while(rs.next()) {
				bn.setCountry(rs.getString(1));
//				System.out.println("ggggggggggggggggg"+bn.getCountry());
			}
			if(bn.getCountry()!=null) {
//				System.out.println("hsggjdgjg"+bn.getCountry());
				a=1;
			}else {
				a=0;
//				System.out.println("hhhhhhhhhh"+bn.getCountry());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
//	public int fetchn() {
//		String sql = "SELECT table_name FROM information_schema.tables" + 
//				" WHERE table_schema = 'db1'";
//		PreparedStatement st;
//		try {
//			st = con.prepareStatement(sql);
//			rs = st.executeQuery();
//			int i=1;
//			while(rs.next()) {
//				System.out.println("this is the try of mysql dump"+rs.getString(1));
//				
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return a;
//	}


//	public int getDateData(Bean b) {
//		String q = "select * from data where date between '?' and  '?'";
//		try {
//			PreparedStatement st =con.prepareStatement(q);
//			st.setString(1, b.getDate1());
//			a = st.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return a;
//	}
//	
	
	
}
