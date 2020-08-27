package com.hyunwoo.function0;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scan {

	public static void main(String[] args) {

		/*
		 * Scanner sc = new Scanner(System.in); //int i = sc.nextInt();
		 * while(sc.hasNextInt()) System.out.println(sc.nextInt()*1000); sc.close();
		 */
		
		File file = new File("input.txt");
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextInt())
				System.out.println(sc.nextInt() * 100);
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("������ �о���� ���� ������ �߻��Ͽ����ϴ�.");
		}
	} 

}
