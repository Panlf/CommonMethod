package com.plf.pdf;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 * itext��ʹ��
 * �ٷ������ܶ�Ҳд�ú���ϸ�����ֻҪ�Լ�����һ�����ѧ�ᡣ
 * �ٷ�����:http://developers.itextpdf.com/content/itext-7-jump-start-tutorial/examples
 * �ٷ��ĵ�˵��:http://developers.itextpdf.com/content/itext-7-jump-start-tutorial
 * @author plf 2017��8��23������9:10:39
 *
 */
public class PdfExample {
	
	@Test
	public void createPdf() throws FileNotFoundException{
	    //��ʼ��PDF Writer
		PdfWriter writer = new PdfWriter("E:\\hello_world.pdf");

	    //��ʼ��PDF document
	    PdfDocument pdf = new PdfDocument(writer);

	    //��ʼ��document
	    Document document = new Document(pdf);

	    //��һ��������ӵ�document��
	    document.add(new Paragraph("Hello World!"));

	    //�ر�document
	    document.close();
	}
}
