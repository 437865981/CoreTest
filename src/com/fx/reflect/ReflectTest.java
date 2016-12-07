package com.fx.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * java反射机制
 * @author Administrator
 *获取某个类或某个对象所对应的Class对象的常用的3种方式（类类型）：
 *1.Class.forName()
 *2.类名.class
 *3.类的对象.getClass()
 *
 *
 *通过无参的构造函数获得实例的方法（两种）
 *	1
 */
/*
 *通过类类型可以干嘛呢？
 	*getName()：获得类的完整名字。
 	*getFields()：获得类的public类型的属性。
 	*getDeclaredFields()：获得类的所有属性。
 	*getMethods()：获得类的public类型的方法。
 	*getDeclaredMethods()：获得类的所有方法。
 	*getMethod(String name, Class[] parameterTypes)：获得类的特定方法，name参数指定方法的名字，parameterTypes参数指定方法的参数类型。
 	*getConstructors()：获得类的public类型的构造方法。
 	*getConstructor(Class[] parameterTypes)：获得类的特定构造方法，parameterTypes参数指定构造方法的参数类型。
 	*newInstance()
 */
public class ReflectTest {
	
	public String test="我是public的属性";
	private Integer id;
	private String name;
	

	public ReflectTest(){
		
	}
	public ReflectTest(Integer id,String name){
		this.name=name;
		this.id=id;
	}
	private ReflectTest(Integer id){
		this.id=id;
	}
	
	private boolean test(){
		return false;
	}
	private String sayHello(String name){
		return "Hello"+name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setAll(Integer id,String name) {
		this.id = id;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "ReflectTest [test=" + test + ", id=" + id + ", name=" + name
				+ "]";
	}
	public static void reflectTest() throws Exception{
		Class<?> clazz1=Class.forName("com.fx.reflect.ReflectTest");
		Class<?> clazz2=ReflectTest.class;
		
		//通过类的实例获得类类型需要实例化否则会出现NULLPOINT
		ReflectTest rf = new ReflectTest();
		Class<?> clazz3=rf.getClass();
		
		System.out.println("得到类名"+clazz1.getName());
		
		//获得类的public类型的属性。
		Field[] fields1=clazz1.getFields();
		
		//获得所有属性
		Field[] fields2=clazz1.getDeclaredFields();
 		System.out.println(fields2.toString());
		
 		//获得所有的public方法(名称)
 		Method[] methods=clazz1.getMethods();
 		for(int i=0;i<methods.length;i++){
 			System.out.println(methods[i].getName());
 		}
 		
 		//获得所有的方法（有test）
 		Method[] methods2=clazz1.getDeclaredMethods();
 		for(int i=0;i<methods2.length;i++){
 			System.out.println(methods2[i].getName());
 		}
 		
 		//获得特定的方法
 		Class [] classes={Integer.class,String.class};
 		Method method3=clazz1.getMethod("setAll",classes);
 		System.out.println(method3.getName());
 		
 		//获得实例
 		ReflectTest rt=(ReflectTest) clazz1.newInstance();
 		System.out.println(rt);
 		
 		//获得构造函数并实例化
 		rt=(ReflectTest) clazz1.getConstructor(new Class[]{Integer.class,String.class}).newInstance(1,"2");
 		System.out.println(rt);
		
 		//获得方法并执行
 		/**
 		 * 实际中可能没写getter and setter 方法，
 		 * 所以可通过属性名前面加上set然后首字母大写的到相应的set方法
 		 */
 		Method method=clazz1.getMethod("setName",String.class );
 		//得到setName方法，调用invoke,
 		//传入实例对象，和参数
 		method.invoke(rt, "fx");
 		System.out.println(rt);
	}
	
	/**
	 * 通过无参构造函数有两种方法
	 * 				1.类类型.newInstance();
	 * 				2.类类型.getConstruct().newInstance()
	 * 通过有参的构造函数只有一种（构造函数的newInstance）
	 * 				
	 * @throws Exception
	 */
	public static void getInstance() throws Exception{
		
		Class<?> clazz=ReflectTest.class;
		ReflectTest t1=(ReflectTest) clazz.newInstance();
		System.out.println(t1);
		
		t1=(ReflectTest) clazz.getConstructor().newInstance();
		System.out.println(t1);
		
		t1=(ReflectTest) clazz.getConstructor(Integer.class,String.class).newInstance(1,"fx");
		System.out.println(t1);
	}
	
	/**
	 * 利用反射获得私有的方法
	 * 并执行
	 */
	public static void getPrivateMethod() throws Exception{
		Class<?> clazz=ReflectTest.class;
		ReflectTest rt=new ReflectTest();
		//得到方法
		Method m=clazz.getDeclaredMethod("sayHello", String.class);
		System.out.println(m);
		//压制Java的访问控制检查，使允许访问private方法
		//private的访问权限为本类，当在其他包的类中访问这个私有方法时会抛出异常
		m.setAccessible(true);
		//调用方法（传入实例对象和属性参数）
		String str=(String) m.invoke(rt,"fangxiong");
		
		System.out.println(str);
	}
	
	/**
	 * 获得属性名并得到值
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void getPropertyAndValue() throws Exception{
		//获得属性名（全名）
		Field field=ReflectTest.class.getDeclaredField("name");
		System.out.println(field);
		String name=field.toString();
		name=name.substring(52);
		System.out.println(name);
		//获得一个实例对象
		ReflectTest rt=(ReflectTest)ReflectTest.class.getDeclaredConstructor(Integer.class,String.class)
					.newInstance(1,"fx");

		//得到属性值
		System.out.println(field.get(rt));
	}
	public static void main(String[] args) throws Exception {
		//reflectTest();
		//getInstance();
		//getPrivateMethod();
		
		getPropertyAndValue();
	}
}


