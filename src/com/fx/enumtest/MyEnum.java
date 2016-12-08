package com.fx.enumtest;
/**
 * 
 * author 方雄
 * date 2016年12月8日
 * desc TODO
 * 枚举型测试
 * 		定义像类一样,继承了enum类
 * 		类的成员都是静态，final型的
 */
public class MyEnum {

	private Integer id;
	public enum Color{
		YELLOW,BLUE,GREEN,RED
	}
	
	
public static void main(String[] args) {
	//得到属性值
	Color red=Color.RED;
	System.out.println(red);
	
	Color[] colors=MyEnum.Color.values();
	for (Color color : colors) {
		System.out.println(color);
	}
	
	//得到属性的位置
	int i=Color.RED.ordinal();
	System.out.println(i);
}	
}
