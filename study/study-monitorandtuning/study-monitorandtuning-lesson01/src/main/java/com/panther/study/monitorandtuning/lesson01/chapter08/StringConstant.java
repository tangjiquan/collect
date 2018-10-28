package com.panther.study.monitorandtuning.lesson01.chapter08;

public class StringConstant {
		public static void main1(String[] args) {
	        String hello = "Hello", lo = "lo";
	        System.out.println((hello == "Hello") ); 					// true，字面常量存放在常量池中的
	        System.out.println((Other.hello == hello) ); 				// true, 字面常量存放在常量池中的
	        System.out.println((hello == ("Hel"+"lo")) );				// ture, "hel"+"lo" 是两个常量相加， 还是放在常量池中
	        System.out.println((hello == ("Hel"+lo)));					// false, "Hel"+lo是常量和变量想加， 并不能在编译时替换。
	        System.out.println(hello == ("Hel"+lo).intern());			// true, ("Hel"+lo).intern()标识是hello对应常量池中的字符串
	    }
		public static class Other{
			public static String hello = "Hello";
		}


		public static void main(String[] args){
			String s =new String("1");	// "1"在常量池中存储一份， s变量存放在堆里
			s.intern();					// 检查常量池中是否有字符串"1"
			String s2 = "1";			// s2引用的是常量池中的字符串
			System.out.println(s == s2);

			String s3 = new String("1") + new String("1");	// s3存放在堆中, 常量池也是放在堆中
			s3.intern();									// 在常量池中存放一个"11"字符串
			String s4 = "11";								// s4引用了常量池中的"11", 常量池也是放在堆中
			System.out.println(s3==s4);

			// false
			// true
		}
}


