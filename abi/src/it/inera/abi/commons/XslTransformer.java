package it.inera.abi.commons;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;

import java.io.*;

public final class XslTransformer {

	public static String transform(String body, String xsltPath) throws TransformerException  {
		StringWriter ostr = new StringWriter();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xsltPath));
		transformer.transform(new StreamSource(new StringReader(body)), new StreamResult(ostr));
		return ostr.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String body = FileUtils.readFileToString(new File("/home/marina/test.xml"));
		System.out.println(transform(body, "/home/marina/workspace_abi/abiapplication/statistiche/TAVOLA01b.xsl"));
	}
}
