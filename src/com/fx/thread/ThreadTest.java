package com.fx.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * author 方雄
 * date 2016年12月8日
 * Disc 
 * 线程测试以及多线程并发(同步)
 * 1.线程和进程的区别
 *		1.线程是运行时的程序（动态概念的程序）
 *		2.一个线程至少有一个至对个进程 	
 *		3.多个进程的内部数据和状态都是完全独立的,而多线程是共享一块内存空间和一组系统资源,有可能互相影响. 
 *		•线程本身的数据通常只有寄存器数据，以及一个程序执行时使用的堆栈，所以线程的切换比进程切换的负担要小。
 多线程编程的目的，就是"最大限度地利用CPU资源"，当某一线程的处理不需要占用CPU而只和I/O等资源打交道时，让需要占用CPU资源的其它线程有机会获得CPU资源。从根本上说，这就是多线程编程的最终目的。
 *
 *2.线程的几种状态
 *		创建状态（new Thred（））
 *		--就绪状态（t.start()）（等待Cpu或者其他资源）
 *		--运行状态（真正的执行run()方法）
 *		--阻塞状态（cpu调度时间到或者需要其他的资源）
 *	线程运行过程中，可能由于各种原因进入阻塞状态:
        1>线程通过调用sleep方法进入睡眠状态；
        2>线程调用一个在I/O上被阻塞的操作，即该操作在输入输出操作完成之前不会返回到它的调用者；
        3>线程试图得到一个锁，而该锁正被其他线程持有；
        4>线程在等待某个触发条件；
        --消亡状态（执行完或者外部销毁？）
          有两个原因会导致线程死亡：
        1) run方法正常退出而自然死亡，
        2) 一个未捕获的异常终止了run方法而使线程猝死。
        
 */
 
public class ThreadTest {

	public static void main(String[] args) throws Exception, Exception {
/*		//继承Thread类，调用start方法
		MyThread1 t1=new MyThread1();
		t1.start();
		
		for(int i=1;i<5;i++){
			new MyThread1().start();
		}
		
		
		//实现Runnable接口，新建Thread类传入自定义的线程类
		//创建多个线程
		for(int i=1;i<5;i++){
			new Thread(new MyThread2()).start();
		}
	
*/
		//用Executors
		//可以有多种new..pool
		//Executors.new
	/*	ExecutorService es=Executors.newCachedThreadPool();
		for(int i=1;i<5;i++){
			es.execute(new MyThread2());
		}
		es.shutdown();
		*/
		
		//产生返回结果
		//ExecutorService的submit方法
		//Callable接口 的call方法
		//
		ExecutorService es=Executors.newCachedThreadPool();
		
		List<Future<String>> results=new ArrayList<Future<String>>();
		
		//将返回结果放入list中
		for (int i = 0; i < 5; i++) {
			results.add((Future<String>) es.submit(new MyThread3(Integer.toString(i))));
		}
		
		for (Future<String>  s : results) {
			System.out.println(s);
			System.out.println(s.get());
		}
		
		
	}

}


/**
 * author 方雄
 * date 2016年12月8日
 * desc TODO
 * 线程创建方法之一
 * 		继承Thread类(Thread类实际实现了Runnable接口)
 */
class MyThread1 extends Thread{
	@Override
	public void run() {
		while((flag--)>0){
			System.out.println(this.getName()+"I am Thread:"+flag);
		}
		
	}
	private int flag=100;
	
	public MyThread1(){
		
	}
	
	public MyThread1(int flag){
		this.flag=flag;
	}
}

/**
 * 
 * author 方雄
 * date 2016年12月8日
 * desc TODO
 * 线程创建方法之二
 * 		实现Runnable接口
 */
class MyThread2 implements Runnable{
	private static int taskCount=0;
	
	private final int id=taskCount++;
	@Override
	public void run() {
		while((flag--)>0){
			//获得线程名称
			System.out.println("#"+id+Thread.currentThread().getName()+"I am Runnable:"+flag);
		}
		
	}
	private int flag=200;
	
	public MyThread2(){
		
	}
	
	public MyThread2(int flag){
		this.flag=flag;
	}
}

/**
 * 
 * author 方雄
 * date 2016年12月8日
 * desc TODO
 * 线程创建方法之三
 * 		实现Callable接口
 * 		运行call（）方法返回结果
 * 		产生返回结果的线程类
 * 
 */
class MyThread3 implements Callable<String>{

	private String id;
	
	public MyThread3(String id){
		this.id=id;
	}
	@Override
	public String call() throws Exception {
		return id;
	}
	
}




