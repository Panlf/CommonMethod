package com.plf.pinyin;

import org.junit.Test;

import net.sourceforge.pinyin4j.PinyinHelper;

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
}
