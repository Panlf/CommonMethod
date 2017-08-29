package com.plf.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * ʵ�ֽ�Excel�е��ֶ�ӳ��Ϊsql���(����.xls)
 * @author plf 2017��8��29������10:02:18
 *
 */
public class ImportHSSFExcel {

	public static void main(String[] args) {
		readExcel("E:\\test.xls","test",3);

	}
	public static void readExcel(String path,String table,int num){
		try {
			FileInputStream in=new FileInputStream(new File(path));
			@SuppressWarnings("resource")
			HSSFWorkbook wb=new HSSFWorkbook(in);
			//��ȡ��һ��sheet
			HSSFSheet st=wb.getSheetAt(0);
			//��ȡ������
			int rowNum=st.getLastRowNum();
			List<String[]> dataList=new ArrayList<String[]>();
			String[] str=new String[num];
			//String column;
			for(int i=0;i<=rowNum;i++){
				HSSFRow hr=st.getRow(i);
				if(i==0){
					for(int j=0;j<num;j++){
						str[j]=hr.getCell(j).getStringCellValue();
					}					
				}else if(i>1){
					String[] strdata=new String[num];
					for(int k=0;k<num;k++){
						strdata[k]=hr.getCell(k).getStringCellValue();
					}	
					dataList.add(strdata);
				}
			}
			
		if(dataList.size()>0 && dataList!=null){
			StringBuilder insert=new StringBuilder();
			for (String[] data : dataList) {
				insert.append("insert table(").append(str[0]);
				for(int m=1;m<num;m++){
					insert.append(",").append(str[m]);
				}
				insert.append(") values('").append(data[0]);
				for(int m=1;m<num;m++){
					insert.append("',").append(data[m]);
				}
				insert.append("');");
				System.out.println(insert.toString());
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
