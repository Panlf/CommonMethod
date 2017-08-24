package com.plf.pdf;

import java.io.IOException;

import org.junit.Test;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
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
	public void createPdf() throws IOException{
	    //��ʼ��PDF Writer
		PdfWriter writer = new PdfWriter("E:\\hello_world.pdf");

	    //��ʼ��PDF document
	    PdfDocument pdf = new PdfDocument(writer);

	    //��ʼ��document
	    Document document = new Document(pdf);
	    
	    //����PdfFont
	    PdfFont font=PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	    //��һ��������ӵ�document��
	    document.add(new Paragraph("Hello World!").setFont(font));
	    
	    //�½�list
	    List list=new List()
	    		.setSymbolIndent(12)
	    		.setListSymbol("\u2202")
	    		.setFont(font);
	    
	    list.add(new ListItem("A line"))
	    	.add(new ListItem("A new Line"))
	    	.add(new ListItem("A another new Line"));
	    
	    document.add(list);

	    //�ر�document
	    document.close();
	}
}
