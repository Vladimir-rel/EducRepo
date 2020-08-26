package ru.relex.education.sandbox;

public class MyFirstProgram {

	public static void main(String[] args){
		hello("world");
		hello("user");
		hello("Vladimir");

		Square s = new Square(5);
		System.out.println("Площадь квдрпата со стороной " + s.side + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.length + "  и " + r.width + " = " + r.area());

	}
	public static void hello(String some){
		System.out.println("Hello, " + some + "!");
	}

}