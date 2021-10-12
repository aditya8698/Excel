package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmdFile 
{ 
	public static void main(String args[]) throws IOException {

		String[] cmd = {"cmd.exe", "/c", " C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin >"+"mysqldump -u root -p Aditya@20 db1 data  --where \\\"name='Mara'\\\"> c:\\\\db1.sql"};
	Process process = Runtime.getRuntime().exec(cmd);
    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line = null;
    while (true) {
        line = in.readLine();
        if (line == null) { break; }
        System.out.println(line);
    }
}

}