package com.panther.study.monitorandtuning.lesson01.chapter08;

public class TryFinally {
	public static void main(String[] args) {
		System.out.println(f1());
	}
	/**
	 public static java.lang.String f1();
    descriptor: ()Ljava/lang/String;
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=3, args_size=0
         0: ldc           #34                 // String hello	# 将34号常量池中的常量压到栈顶中
         2: astore_0											# 将栈顶的值存到本地变量0中
         3: aload_0												# 将本地变量0中的值放到栈里
         4: astore_2											# 将栈里的值放到本地变量2中
         5: ldc           #36                 // String imooc	# 将36号常量压到栈里
         7: astore_0											# 将栈里的值放到本地变量0中
         8: aload_2												# 将本地变量中2的值压到栈里
         9: areturn												# 返回栈里的值
        10: astore_1											# 将异常存到本地变量1中
        11: ldc           #36                 // String imooc	# 将36号压栈
        13: astore_0											# 将栈里的值存放到本地变量0
        14: aload_1												# 本地变量1的值压栈
        15: athrow												#
	 * */
	public static String f1() {
		String str = "hello";
        try{
            return str;
        }
        finally{
            str = "imooc";
        }
	}
}