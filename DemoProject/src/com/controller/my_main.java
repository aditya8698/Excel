package com.controller;

import java.io.PrintWriter;

public class my_main {

	public static void main(String[] args) {
		String[] command = { "cmd", };
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
//			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("cd..");
			stdin.println("cd..");
			stdin.println("cd \"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin");
			stdin.println("mysqldump -u root -p Aditya@20 db1 data --where \"name='Mara'\"> c:\\db1.sql");
//			stdin.println("Aditya@20");
			stdin.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
