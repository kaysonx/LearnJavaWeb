package me.utlight.learnjdbc;

public class testBatchDemo {
	public static void main(String[] args) {
		testBatch demo = new testBatch();
//		demo.testJdbcBatchHandByStatement();
		System.out.println("²âÊÔ¿ªÊ¼...");
		demo.testJdbcBatchHandByPreparedStatement();
	}
}
