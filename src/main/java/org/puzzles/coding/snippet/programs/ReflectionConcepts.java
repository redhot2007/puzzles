package org.puzzles.coding.snippet.programs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


import org.junit.Test;

/**
 * This class contains code snippets to illustrate some concepts. For example java refelction
 * @author kmaruthavanan
 *
 */
public class ReflectionConcepts {

	class ReflectionSample{
		private int fld1 = 5;
		private int fld2;
		public ReflectionSample(){
			
		}
		public ReflectionSample(int i){
			
		}
		public void mtd1(){
			
		}
		private int mtd2(){
			System.out.println("Private method executed ...");
			return 1;
		}
	}
	
	@Test
	public void javaReflections() throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		// create any object
		ReflectionSample hash = new ReflectionSample();
		System.out.println("Constructors:");
		System.out.println("-------------");
		for(Constructor<?> constructor:hash.getClass().getConstructors()){
			System.out.println(constructor.getName());
		}
		System.out.println("============");
		System.out.println("Fields:");
		System.out.println("-------------");
		for(Field field:hash.getClass().getDeclaredFields()){
			System.out.println(field.getName());
		}
		System.out.println("============");
		System.out.println("Methods:");
		System.out.println("--------");
		for(Method method : hash.getClass().getDeclaredMethods()){
			method.setAccessible(true);
			System.out.println("Name: "+method.getName()+
					"\nReturn type: " +method.getReturnType()+ 
					"\nIs public? "+Modifier.isPublic(method.getModifiers())+
					"\n=========== "				
					);
		}
		
		// Make a field public
		Field fld1 = hash.getClass().getDeclaredField("fld1");
		try{
			System.out.println("Private Field fld1's value: "+fld1.get(hash));
		}catch(IllegalAccessException e){
			System.out.println("Before setting accesible, Cant access private field: "+fld1.getName());
		}
		// set accessible of private field to true
		fld1.setAccessible(true);
		System.out.println("After setting accessible:Private Field "+fld1.getName()+"'s value is "+fld1.get(hash));
		System.out.println("============");
		
		// Make a method public
		Method mtd =  hash.getClass().getDeclaredMethod("mtd2", null);
		try{
			System.out.println(mtd.invoke(hash, null));
		}catch(IllegalAccessException e){
			System.out.println("Before setting accesible, Cant access private method: "+mtd.getName());
		}
		mtd.setAccessible(true);
		System.out.println("After setting accesible, Return value from private method:" + mtd.invoke(hash, null));
	}
}
