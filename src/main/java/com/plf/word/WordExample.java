package com.plf.word;

import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.junit.Test;

/**
 * word�ִ����ļ�ʵ��
 * Ŀǰ�ܶ಻�Ǻ��˽� --�����о�
 *  https://github.com/ysc/word(�ĵ���ַ)
 * @author plf 2017��6��14������9:41:54
 *
 */
public class WordExample {

	@Test
	//�ı��ִ�
	public void TestSegmenterWord(){
		String text="word�ִ���һ��Javaʵ�ֵķֲ�ʽ�����ķִ�������ṩ�˶��ֻ��ڴʵ�ķִ��㷨��������ngramģ�����������塣"
				+ "��׼ȷʶ��Ӣ�ġ����֣��Լ����ڡ�ʱ��������ʣ���ʶ����������������֯��������δ��¼�ʡ�"
				+ "��ͨ���Զ��������ļ����ı������Ϊ�����Զ����û��ʿ⡢�Զ����ʿ�仯��֧�ִ��ģ�ֲ�ʽ�����������ָ�����ִַ��㷨��"
				+ "��ʹ��refine���������Ʒִʽ��������ʹ�ô�Ƶͳ�ơ����Ա�ע��ͬ���ע�������ע��ƴ����ע�ȹ��ܡ��ṩ��10�ִַ��㷨�����ṩ��10���ı����ƶ��㷨��ͬʱ���޷��Lucene��Solr��ElasticSearch��Luke���ɡ�";
		List<Word> words = WordSegmenter.seg(text);//�Ƴ�ͣ�ô�
		System.out.println(words);
		words = WordSegmenter.segWithStopWords(text);//δ�Ƴ�ͣ�ô�
		System.out.println(words);
	}
}
