package ru.relex.education.sandbox;

public class MyFirstProgram {

	public static void main(String[] args){
		hello("world");
		hello("user");
		hello("Vladimir");

		double l = 5;
		System.out.println("Площадь квдрпата со стороной " + l + " = " + area(l));

		double a = 4;
		double b = 6;
		System.out.println("Площадь прямоугольника со сторонами " + a + "  и " + b + " = " + area(a,b));

	}
	public static void hello(String some){
		System.out.println("Hello, " + some + "!");
	}
	public static double area(double len){
		return len * len;
	}
	public static double area(double a, double b){
		return a * b;
	}
}