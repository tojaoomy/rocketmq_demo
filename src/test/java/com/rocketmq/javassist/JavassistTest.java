/*
 * Copyright (C), 2013-2014, 上海汽车集团股份有限公司
 */
package com.rocketmq.javassist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import org.junit.Test;

/**
 * @author hejian
 *
 */
public class JavassistTest {

	@Test
	public void makeClassTest() throws CannotCompileException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		ClassPool classPool = ClassPool.getDefault();
		CtClass makeClass = classPool.makeClass("com.rocketmq.Demo");
		CtField name = CtField.make("private String name = \"michael\";", makeClass);
		makeClass.addField(name);
		CtMethod setName = CtMethod.make("public void setName(String name){this.name = name;}", makeClass);
		makeClass.addMethod(setName);
		CtMethod getName = CtMethod.make("public String getName(){return this.name;}", makeClass);
		makeClass.addMethod(getName);
		Class<?> clazz = makeClass.toClass();
		Object instance = clazz.newInstance();
		Method m1 = clazz.getMethod("getName");
		Object value = m1.invoke(instance);
		System.out.println(value);
		Method method = clazz.getMethod("setName",String.class);
		method.invoke(instance, "测试");
		value = m1.invoke(instance);
		System.out.println(value);
		printOut(instance);
	}
	
	public void printOut(Object obj){
		System.out.println(obj.getClass().getName());
	}
}
