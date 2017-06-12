package com.plf.excel;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

/**
 * Java POI��������Excel
 * @author plf 2017��6��12������9:31:21
 *
 */
public class ExcelExample {
	
	@Test
	public void ExportExcel(){
		//����һ��������
		@SuppressWarnings("resource")
		HSSFWorkbook wb=new HSSFWorkbook();
		//����һ��sheet������
		HSSFSheet hs=wb.createSheet("������Ϣ��");
		//���ñ��Ĭ�Ͽ��
		hs.setDefaultColumnWidth(15);
		//���ɸ�ʽ
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //�±߿�
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�
		style.setFillBackgroundColor((short) 13);//���ñ���ɫ
		//���������ʽ
		HSSFFont font = wb.createFont(); 
		font.setBold(true);
		font.setFontHeight((short)15);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//Ӧ������
		style.setFont(font);
		
		
		//���ɸ�ʽ
		HSSFCellStyle anotherstyle=wb.createCellStyle();
		anotherstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		anotherstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //�±߿�
		anotherstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�
		anotherstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�
		anotherstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�
		//���������ʽ
		HSSFFont anotherfont = wb.createFont(); 
		anotherfont.setFontHeight((short)12);
		//Ӧ������
		anotherstyle.setFont(anotherfont);
		
		
		//���õ�һ��
		HSSFRow row = hs.createRow(0);
		HSSFCell cell=row.createCell(3);
		cell.setCellValue("������Ϣ��");
		cell.setCellStyle(style);
		
		//���õڶ���
		row = hs.createRow(1);
		cell=row.createCell(0);
		cell.setCellValue("��˾��");
		cell.setCellStyle(anotherstyle);
		cell=row.createCell(1);
		cell.setCellValue("����");
		cell.setCellStyle(anotherstyle);
		cell=row.createCell(2);
		cell.setCellValue("��Ϣ");
		cell.setCellStyle(anotherstyle);
		
		List<SkillInfo> data=new ArrayList<SkillInfo>();
		data.add(new SkillInfo("xx1","1��","����"));
		data.add(new SkillInfo("xx2","3��","���"));
		data.add(new SkillInfo("xx3","5��","�ܹ�"));
		
		for (int i=0;i<data.size();i++) {
			row=hs.createRow(i+2);
			row.createCell(0).setCellValue(data.get(i).getCompany());
			row.createCell(1).setCellValue(data.get(i).getYear());
			row.createCell(2).setCellValue(data.get(i).getInfo());
		}
		
		try {
			FileOutputStream out = new FileOutputStream("E://������Ϣ��.xls");
			wb.write(out);
	        out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
