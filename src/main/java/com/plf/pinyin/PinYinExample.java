package com.plf.pinyin;

import org.junit.Test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * ƴ��Jar�Ļ����÷�
 * @author plf 2017��6��7������10:23:01
 *
 */
public class PinYinExample {
	
	@Test
	public void TestPinyin(){
		//��ȡһ�����ֵ�ƴ��
		String[] array=PinyinHelper.toGwoyeuRomatzyhStringArray('��');
		for (String string : array) {
			System.out.println(string);
		}
	}
	
	@Test
	public void TestToString(){
		HanyuPinyinOutputFormat outFormat = new HanyuPinyinOutputFormat();  
        outFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);//����ƴ���Ĵ�Сд
        //�Ƿ������WITH_TONE_NUMBER�������� PAN1 LIANG2 FENG1
        //WITH_TONE_MARK ���������ű�ʾ
        outFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //����ƴ��u����ʾ��ʽ
        outFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			System.out.println(PinyinHelper.toHanYuPinyinString("������", outFormat, " ",true));
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHanyuString(){
		//������
		String[] data=PinyinHelper.toHanyuPinyinStringArray('��');
		for (String string : data) {
			System.out.println(string);
		}
		
		HanyuPinyinOutputFormat outFormat = new HanyuPinyinOutputFormat();  
        outFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);//����ƴ���Ĵ�Сд
        //�Ƿ������WITH_TONE_NUMBER�������� PAN1 LIANG2 FENG1
        //WITH_TONE_MARK ���������ű�ʾ
        outFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //����ƴ��u����ʾ��ʽ
        outFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			//�о��Ƚ���ֵ��﷨
			System.out.println(PinyinHelper.toHanyuPinyinStringArray('��', outFormat)[0]);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
