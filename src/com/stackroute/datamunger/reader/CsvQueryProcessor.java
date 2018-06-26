package com.stackroute.datamunger.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	// Parameterized constructor to initialize filename
	String fileName;
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName =fileName;
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {

		// read the first line

		// populate the header object with the String array containing the header names
		 File file = new File(fileName);
	       FileReader fileReader = new FileReader(file);
	       BufferedReader bufferedReader = new BufferedReader(fileReader);
	       String line=bufferedReader.readLine();
	       bufferedReader.close();
	       Header head=new Header();
	       head.setHeaders(line.trim().split(","));
		return head;
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {

		File file = new File("data/ipl.csv");
	       FileReader fileReader = new FileReader(file);
	       BufferedReader bufferedReader = new BufferedReader(fileReader);
	       String line=bufferedReader.readLine();
	       String line2=bufferedReader.readLine();
	       bufferedReader.close();
	       DataTypeDefinitions dataTypeDefinitions=new DataTypeDefinitions();
	       String[] firstLine=line2.trim().split(",",line.length());
	       String[] dataTypes=new String[firstLine.length];
	       Integer intdata;
	       Double doubleData;
	       for(int i=0;i<firstLine.length;i++)
	       {
	           try
	           {
	               intdata=Integer.parseInt(firstLine[i]);
	               dataTypes[i]=intdata.getClass().getName();
	               
	               System.out.println(dataTypes[i]);
	           }
	           catch(NumberFormatException e)
	           {
	               try {
	                   doubleData=Double.parseDouble(firstLine[i]);
	                   dataTypes[i]=doubleData.getClass().getName();
	                   System.out.println(dataTypes[i]);
	               }
	               catch(NumberFormatException f){
	                   dataTypes[i]=firstLine[i].getClass().getName();
	                   System.out.println(dataTypes[i]);
	               }
	           }
	       }
	       
	       dataTypeDefinitions.setDataTypes(dataTypes);
		return dataTypeDefinitions;
	}
}
