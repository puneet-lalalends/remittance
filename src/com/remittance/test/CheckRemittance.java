package com.remittance.test;

import com.remittance.bo.TransferwiseBo;
import com.remittance.dao.TransferwiseCallableDao;
import com.remittance.dao.TransferwiseDao;

import java.text.MessageFormat;
import java.util.concurrent.*;

public class CheckRemittance {

    public static void main(String[] args) {

//        try {
        long startTime = System.nanoTime();
        final CyclicBarrier barrier = new CyclicBarrier(101);
        for (int i = 0; i < 100; i++) {

                ExecutorService service = Executors.newFixedThreadPool(3);
//                Future<TransferwiseBo> transferwiseBo = service.submit(new TransferwiseCallableDao(barrier));
//                System.out.println(transferwiseBo.get());
                service.shutdown();

//            TransferwiseDao transferwiseDao = new TransferwiseDao(barrier);
//            transferwiseDao.setName("Thread " +s i);
//            transferwiseDao.start();

            /*while (!service.isTerminated()) {
            System.out.println("System is running...");
        }*/
        }

        /*try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }*/
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(Thread.currentThread().getName() + " Total Time Taken " + (totalTime / 1000000000.0));

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        String comment = "paymentOptions[{0}].targetAmount";
        comment = MessageFormat.format(comment, 3);

        System.out.println(comment);


    }
}
