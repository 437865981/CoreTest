package com.fx.thread;
/**
 * 
 * author 方雄
 * date 2016年12月8日
 * desc TODO
 * java多线程并发实现
 * 
 * 1.Java中的每个对象都有一个锁（lock）或者叫做监视器（monitor），
 * 当访问某个对象的synchronized方法时，表示将该对象上锁，
 * 此时其他任何线程都无法再去访问该synchronized方法了，
 * 直到之前的那个线程执行方法完毕后（或者是抛出了异常），
 * 那么将该对象的锁释放掉，其他线程才有可能再去访问该synchronized方法。 
 * 
  2. 如果一个对象有多个synchronized方法，
      某一时刻某个线程已经进入到了某个synchronized方法，
    那么在该方法没有执行完毕前，其他线程是无法访问该对象的任何synchronized方法的。 
 
3.如果某个synchronized方法是static的，
那么当线程访问该方法时，它锁的并不是synchronized方法所在的对象，
而是synchronized方法所在的对象所对应的Class对象，
因为Java中无论一个类有多少个对象，这些对象会对应唯一一个Class对象，
因此当线程分别访问同一个类的两个对象的两个static，synchronized方法时，
他们的执行顺序也是顺序的
 *  */
public class SyncronizedTest {

	public static void main(String[] args) {

	}

}

