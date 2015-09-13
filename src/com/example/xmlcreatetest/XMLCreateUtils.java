package com.example.xmlcreatetest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

public class XMLCreateUtils {
	/**
	 * 单个对象生成xml
	 * @param person
	 */
	public static void XmlFileCreator(Person person) {
	    File newxmlfile = new File(Environment.getExternalStorageDirectory() + "/xmlparser_person.xml");
	    try {
	        if (!newxmlfile.exists())
	            newxmlfile.createNewFile();
	    } catch (IOException e) {
	        Log.e("IOException", "exception in createNewFile() method");
	    }
	    FileOutputStream fileos = null;
	    try {
	        fileos = new FileOutputStream(newxmlfile);
	    } catch (FileNotFoundException e) {
	        Log.e("FileNotFoundException", "can't create FileOutputStream");
	    }
	    // XmlSerializer用于写xml数据
	    XmlSerializer serializer = Xml.newSerializer();
	    try {
	        // XmlSerializer 用 UTF-8 编码
	        serializer.setOutput(fileos, "UTF-8");
	        serializer.startDocument(null, Boolean.valueOf(true));
	        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
	        serializer.startTag(null, "person");
	        // xml-tree，由startTag开始，endTag结束
	        serializer.startTag(null, "id");
	        serializer.text(person.getId());
	        serializer.endTag(null, "id");
	        serializer.startTag(null, "password");
	        serializer.text(person.getPassword());
	        serializer.endTag(null, "password");
	        serializer.startTag(null, "name");
	        serializer.text(person.getName());
	        serializer.endTag(null, "name");
	        serializer.endTag(null, "person");
	        serializer.endDocument();
	        // 写xml数据到FileOutputStream
	        serializer.flush();
	        // 关闭fileos，释放资源
	        fileos.close();
	    } catch (Exception e) {
	        Log.e("Exception", "error occurred while creating xml file");
	    }
	}
}
