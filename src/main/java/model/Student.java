package model;
/**
 * 
 * @author Paulo Pereira
 *
 */
public class Student {
	
	private String name;
	private int age;
	private boolean sex;
	
	//construtor generico
	public Student(){
		
	}
	//construtor completo
	public Student(String name, int age, boolean sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	//getters e setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	
}
