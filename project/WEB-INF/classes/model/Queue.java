package model;

public class Queue {
	String name;
	int id;

	public Quiz(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "{\n\tname: " + name + ",\n\tid: " + id + "\n}";
	}
}
