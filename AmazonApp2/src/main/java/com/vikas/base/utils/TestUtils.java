package com.vikas.base.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import com.vikas.base.TestBase;

public class TestUtils {

	
	public static final long WAIT = 30;
	
	
	public HashMap<String, String> parseStringXML(InputStream file) throws Exception{
		HashMap<String, String> stringMap = new HashMap<String, String>();
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(file);
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		 
		//Get all elements
		NodeList nList = document.getElementsByTagName("string");
		 
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    Element eElement = (Element) node;
		    // Store each element key value in map
		    stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
		 }
		}
		return stringMap;
	}
	
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void log(String txt) {
		TestBase base = new TestBase();
		String msg = Thread.currentThread().getId() + ":" + "Amazon" + ":" + "AddressNewTest" + ":"
				+ Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;

		System.out.println(msg);

		String strFile = "logs" + File.separator + "Amazon" + File.separator + "AddressNewTest" + File.separator
				 + File.separator + base.getDateTime();

		File logFile = new File(strFile);

		if (!logFile.exists()) {
			logFile.mkdirs();
		}

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(logFile + File.separator + "consoleLog.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(msg);
		printWriter.close();
	}
	
	
}
