package me.utlight.learnjdbc;

public class testCrudDemo {

	public static void main(String[] args) {
		
//		crudByStatement demo = new crudByStatement();
//		demo.update();
//		demo.findAll();
		
		crudByPreParedStatement demo = new crudByPreParedStatement();
//		demo.insert();
//		demo.update();
//		demo.findAll();
		demo.delete();

	}

}
