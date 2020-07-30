package com.example.demo.concurrent;

import java.security.AllPermission;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class App0 {
    /**
     * 哲学家问题
     */
    class DiningPhilosophers {

        public DiningPhilosophers() {
        }

        final ReentrantLock[] LockFork = new ReentrantLock[5];

        {
            for (int i = 0; i < LockFork.length; i++) {
                LockFork[i] = new ReentrantLock();
            }
        }

        final Object[] Lock = new Object[5];

        {
            for (int i = 0; i < Lock.length; i++) {
                Lock[i] = new Object();
            }
        }

        final Object AllLock = new Object();

        volatile boolean[] lstflag = new boolean[]{true, true, true, true, true};

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {

            Random rad = new Random();
            int left = philosopher, right = philosopher - 1 < 0 ? 4 : philosopher - 1;
            boolean flag = true;
            while (flag) {
                if (LockFork[left].tryLock(1, TimeUnit.SECONDS)) {

                    if (LockFork[right].tryLock(1, TimeUnit.SECONDS)) {
                        pickLeftFork.run();
                        pickRightFork.run();
                        eat.run();
                        flag = false;
                        putRightFork.run();
                        putLeftFork.run();
                        LockFork[right].unlock();
                    }
                    LockFork[left].unlock();
                }
            }
        }


        public void test(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {

            boolean flag = true;
            int left = philosopher, right = philosopher - 1 < 0 ? 4 : philosopher - 1;
            while (flag) {
                /*竞态条件的复杂度 - 指数上升*/
                if (lstflag[left])
                    synchronized (Lock[left]) {
                        if (lstflag[left] && lstflag[right])
                            synchronized (Lock[right]) {
                                if (lstflag[right]) {
                                    lstflag[left] = false;
                                    lstflag[right] = false;
                                    flag = false;
                                }
                            }
                    }
                /*
                synchronized (AllLock) {
                    }
                    */
                if (flag)
                    Thread.sleep(1l);

            }
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putRightFork.run();
            putLeftFork.run();
            lstflag[left] = true;
            lstflag[right] = true;

        }

        /*{
            int left = philosopher, right = philosopher - 1 < 0 ? 4 : philosopher - 1;
            synchronized (Lock[Math.min(left, right)]){
                synchronized (Lock[Math.max(left, right)]){
                    pickLeftFork.run();
                    pickRightFork.run();
                    eat.run();
                    putRightFork.run();
                    putLeftFork.run();
                }
            }


        }*/

    }

    public static void main(String[] args) {
        Random rad = new Random();
        System.out.println(rad.nextInt(4));
        final ReentrantLock[] LockFork = new ReentrantLock[5];

    }
}
