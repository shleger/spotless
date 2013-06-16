package ru.saa.carpet.util;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/15/13
 * Time: 23:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadCocurs {

    public static void main(String[] args) throws InterruptedException {

        ThreadCocurs threadCocurs = new ThreadCocurs();

        threadCocurs.go();


    }

    Object o = new Object();


    private void go() throws InterruptedException {

        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {
                    runSync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };



        Thread t1 = new Thread(r,"a");
        Thread t2 = new Thread(r,"b");

        t1.start();
        t2.start();


    }

    private void runSync() throws InterruptedException {

        synchronized (o) {
            o.notifyAll();

            System.out.println(Thread.currentThread().getName() + "1");

            o.wait();

            System.out.println(Thread.currentThread().getName() + "2");

        }

    }

}
