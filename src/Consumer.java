import java.util.ArrayList;
import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * This class takes a LinkedBlockingQueue from the Producer Class, decodes
 * and runs the commands as it pulls the commands off the queue.
 */
class Consumer implements Runnable
{
    LinkedBlockingQueue<BitSet> queue;
    ArrayList<BitSet> list = new ArrayList<>();

    public Consumer(LinkedBlockingQueue<BitSet> queue, ArrayList<BitSet> list)
    {
        this.queue = queue;
        this.list = list;
        new Thread(this).start();
    }

    /**
     * This takes the command off the queue and sends it to the
     * robotWork method
     */
    public void run()
    { int len = queue.size();
        for (int i = 0; i < len; i++)
        {
          try
          {
              robotWork(queue.remove());
          }
          catch (NoSuchElementException f)
          {
              System.out.println("No such element exception");
          }
        }
    }

    /**
     * The execution of the robot arm commands. This decodes the bitset and calls the
     * command
     * @param: BitSet bitSet
     */
    public void robotWork (BitSet bitSet)
    {
        try
        {
            System.out.println("Robot arm number " +  bitSet.get(0, 5) + " is working.");

            if (bitSet.get(5)){ System.out.println("State: ON");}
            else { System.out.println("State: Off");}


            if (bitSet.get(6)){System.out.println("Speed: High");}
            else {System.out.println("Speed: Low");}

            if (bitSet.get(7)){ System.out.println("Rotation: Clockwise");}
            else {System.out.println("Rotation: Counterclockwise");}

            if (bitSet.get(8)){ System.out.println("Direction: Up");}
            else {System.out.println("Direction: Down");}

            // decodes the BitSet and puts the thread to sleep for the ascribed time period
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
    }

}
