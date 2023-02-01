import java.util.*;

public class ProducerConsumer {
    static final int N = 100; // constant for the buffer size

    static Producer p = new Producer(); // instantiate a new Producer thread

    static Consumer c = new Consumer(); // instantiate a new Consumer thread

    static OurMonitor mon = new OurMonitor(); // instantiate a new OurMonitor

   public static void main(String args[]) {

      p.start(); // start the Producer thread
      c.start(); // start the Consumer thread
   }

   static class Producer extends Thread {
      public void run() { // run method with the thread code
         int item;

          while (true) { // producer loop: infinite loop

             item = produceItem();

             mon.insert(item);

         } // end of while           
      } // run()


      private int produceItem() {

         Random rand = new Random();

         int num = rand.nextInt(100)+1;

         System.out.println("P:" + num + " ");

         return num;

      } // produceItem()

   } // class Producer

   static class Consumer extends Thread {
      public void run() { // run method with the thread code
         int item;

          while (true) { // consumer loop : infinite loop

             item = mon.remove();

             consumeItem(item);

         } // end of while           
      } // run()


      private void consumeItem(int item) {

         System.out.println("C:" + item + " ");

      } // consumeItem()

   } // class Consumer

   static class OurMonitor { // Monitor definition

       // shared data of the Monitor

       private int buffer[] = new int [N];

       private int count = 0;

       private int lo = 0, hi = 0;

       // Monitor operations

      // 1. insert() operator
      public synchronized void insert(int val) {
         if (count==N) gotoSleep(); // if the buffer is full, go to sleep

          buffer[hi] = val; // insert an item into the buffer

          hi = (hi+1) % N; // slot to place next item in

          count = count + 1;

          if (count==1) notify(); // if consumer was sleeping, wake it up

       } // insert()

       // 2. remove() operator
      public synchronized int remove() {
         int val;

         if (count==0) gotoSleep(); // if the buffer is empty, go to sleep

          val = buffer[lo]; // fetch an item from the buffer

          lo = (lo+1) % N; // slot to fetch next item from

          count = count - 1;

          if (count==N-1) notify(); // if producer was sleeping, wake it up

         return val;

       } // remove()

      private void gotoSleep() {

          try {

             wait(); // wait() can be interrupted

          } catch(InterruptedException exc) {

             System.out.println("Interrupt occurred!");

          };

      } // gotoSleep()

   } // class OurMonitor


} 