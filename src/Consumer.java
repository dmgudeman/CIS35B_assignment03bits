import java.util.ArrayList;
import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

class Consumer implements Runnable
{
    LinkedBlockingQueue<BitSet> queue;
    ArrayList<BitSet> list = new ArrayList<>();
   // CommandEnumerationTask task = new CommandEnumerationTask(queue, list);

    Consumer(LinkedBlockingQueue<BitSet> queue, ArrayList<BitSet> list) throws InterruptedException
    {
        this.queue = queue;
        this.list = list;



        new Thread(this).start();
    }
    public void run()
    { int len = queue.size();
        for (int i = 0; i < len; i++)
        {
          try {
             /*   String sparky = queue.element()[0];
                if(sparky.equals("A1"))
                {
                    System.out.println("\n");
                    System.out.println("COMMAND NUMBER: "+ i);
                    robotWork(queue.remove());
                   // queue.remove(i);
                }
                else if (sparky.equals("A2"))
                {
                    System.out.println("\n");
                    System.out.println("COMMAND NUMBER: "+ i);
                    robotWork(queue.remove());
                   // queue.remove(i);
                }
                else if (sparky.equals("A3"))
                {
                    System.out.println("\n");
                    System.out.println("COMMAND NUMBER: "+ i);
                    robotWork(queue.remove());
                  //  queue.remove(i);
                }
                else if (sparky.equals("A4"))
                {
                    System.out.println("\n");
                    System.out.println("COMMAND NUMBER: "+ i );
                    robotWork(queue.remove());
                   // queue.remove(i);
                }
                else
               {
                    System.out.println("\n");
                    System.out.println("COMMAND NUMBER: "+ i + "Command not found: " + queue.remove()[0]);

               }
             */

           // } catch (InterruptedException e) {
                // TODO Auto-generated catch block
         //       e.printStackTrace();
              robotWork(queue.remove());
          }catch (NoSuchElementException f)
            {
                System.out.println("");
            }
        }
    }
    public void robotWork (BitSet bitSet)
    {
        try
        {
            System.out.println("Robot arm number" +  bitSet.get(0, 4) + " is working.");

            if (bitSet.get(5)){ System.out.println("State: ON");}
            else { System.out.println("State: Off");}


            if (bitSet.get(6)){System.out.println("Speed: High");}
            else {System.out.println("Speed: Low");}

            if (bitSet.get(7)){ System.out.println("Rotation: Clockwise");}
            else {System.out.println("Rotation: Counterclockwise");}

            if (bitSet.get(8)){ System.out.println("Direction: Up");}
            else {System.out.println("Direction: Down");}

            BitSet timeBS = new BitSet();
            timeBS = bitSet.get(9, 17);
            int value = 0;for (int k = 0; k<9; ++k)
            {
                  value += timeBS.get(k) ? (1<< k) : 0;
            }
            System.out.println("MILLISECONDS " + value);
            System.out.println("\n");

            Thread.sleep(value);
            }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted Exception caught");
        }

      //      System.out.println("bitSet.get(5, 9) " + bitSet.get(5,9));
       //     System.out.println("bitSet.get(9, 17) " + bitSet.get(9, 17));
        //    System.out.println("bitSet " + bitSet);
          //  System.out.println("Sleeping " + command[5] + "milliseconds");
         //   Thread.sleep(Integer.parseInt(command[5]));
    }

}
