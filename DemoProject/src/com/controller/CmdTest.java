package com.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdTest {
	public static void main(String[] args) throws Exception {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"cd C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin"+"");
		System.out.println("in process "+builder);
//		ProcessBuilder builder1 = new ProcessBuilder("cmd.exe", "/c",
//		"cd mysqldump -u root -p Aditya@20 db1 data");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
	}
}
