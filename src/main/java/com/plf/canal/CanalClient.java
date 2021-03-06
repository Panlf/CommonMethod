package com.plf.canal;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

public class CanalClient {
	private static String SERVER_ADDRESS = "127.0.0.1";
	
	private static Integer PORT = 11111;

	private static String DESTINATION = "example";
	
	//Canal Server的用户名和密码
	private static String USERNAME = "";
	private static String PASSWORD = "";
	
	public static void main(String[] args) {
		CanalConnector canalConnetor = CanalConnectors.newSingleConnector(
				new InetSocketAddress(SERVER_ADDRESS, PORT),
				DESTINATION, USERNAME, PASSWORD);
		
		
		int batchSize = 100;
	    try {
	    	canalConnetor.connect();
	    	canalConnetor.subscribe(".*\\..*");
	    	canalConnetor.rollback();
	        while (true) {
	            Message message = canalConnetor.getWithoutAck(batchSize); // 获取指定数量的数据
	            long batchId = message.getId();
	           
	            if (batchId != -1 ) {
	            	System.out.println("batchId===>"+batchId);
	            	printEntry(message.getEntries());
	            	//canalConnetor.ack(batchId); // 提交确认
		            // connector.rollback(batchId); // 处理失败, 回滚数据
	            }
	        }
	    } finally {
	    	canalConnetor.disconnect();
	    }
	}
	
	private static void printEntry(List<Entry> entrys) {
	    for (Entry entry : entrys) {
	        if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
	            continue;
	        }

	        RowChange rowChage = null;
	        try {
	            rowChage = RowChange.parseFrom(entry.getStoreValue());
	        } catch (Exception e) {
	            throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
	                                       e);
	        }

	        EventType eventType = rowChage.getEventType();
	        System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
	                                         entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
	                                         entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
	                                         eventType));

	        for (RowData rowData : rowChage.getRowDatasList()) {
	            if (eventType == EventType.DELETE) {
	                printColumn(rowData.getBeforeColumnsList());
	            } else if (eventType == EventType.INSERT) {
	                printColumn(rowData.getAfterColumnsList());
	            } else {
	                System.out.println("-------&gt; before");
	                printColumn(rowData.getBeforeColumnsList());
	                System.out.println("-------&gt; after");
	                printColumn(rowData.getAfterColumnsList());
	            }
	        }
	    }
	}

	private static void printColumn(List<Column> columns) {
	    for (Column column : columns) {
	        System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
	    }
	}
	
}
