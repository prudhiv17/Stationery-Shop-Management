class item
 {
         int q;
         boolean value = false;
 
         synchronized void put(int p)
         {
              if(value)
              {
               try{  wait(); }
               catch(InterruptedException e){ }
               }     
               this.q = p;
               System.out.println("Producer produced : "+ q);  
               value = true;
                notify();               
         }
         synchronized int get()
         {
              if(!value)
              {
               try{  wait(); }
               catch(InterruptedException e){ }
              }                   
               System.out.println("Consumer consumed : "+ q);  
               value = false;
               notify();                
               return q;
         }
 }
 class producer implements Runnable
 {
   	   int  i = 1;
    	   item I;
    	   producer(item I)
    	   {
              this.I = I;
              Thread th = new Thread(this,"Producer");
              th.start();  
               	System.out.println(th.getName() + " Started Executing");              
       	}
       	public void run()
       	{
             while(i < 21)
             {             
              I.put(i);   
              i++; 
              try
              {  
               	Thread.sleep(500);        
              }
              catch(InterruptedException e){}
             }
 }    
 }
 class consumer implements Runnable
 {
       int  i = 1;
       item I;
       Thread th;
       consumer(item I)
       {
              this.I = I;       
              th =  new Thread(this,"Consumer");
              th.start();
              System.out.println(th.getName() + " Started Executing"); 
       }
       public void run()
       {                
             while(i < 21)
             {
               I.get();                 
               i++;    
              try
              {  
           		    Thread.sleep(500);            
              }
              catch(InterruptedException e){}
            }                      
       }                   
 }
 class pro_con
 {
         public static void main(String args[])
         {
                 item I = new item();
                 new producer(I);
                 new consumer(I); 
         }                    
 }