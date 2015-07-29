import java.util.ArrayList;
import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *  Takes in an ArrayList<BitSet> and its purpose is to create
 *  a blocking queue for the Consumer Class
 */
class Producer implements Runnable
{
    LinkedBlockingQueue<BitSet> queue;
    ArrayList<BitSet> list = new ArrayList<>();

    Producer(LinkedBlockingQueue<BitSet> queue, ArrayList<BitSet> list) throws InterruptedException
    {
        this.queue = queue;
        this.list = list;
        new Thread(this).start();
    }

    public void run()
    {
        queue = packTheQueue(queue, list);

    }

    /**
     * Takes in a LinkedBlockingQueue and an ArrayList of BitSets and
     * puts the BitSets into the LinkedBlockingQueue
     * @param: LinkedBlockingQueue<BitSet> queue
     * @param: ArrayList<BitSet> list
     * @return: LinkedBlockingQueue<BitSet></BitSet>
     */
    public LinkedBlockingQueue packTheQueue(LinkedBlockingQueue<BitSet> queue, ArrayList <BitSet> list)
    {
        try
        {
                for (int i = 0;i<list.size();i++)
                {
                    queue.put(this.list.get(i));
                }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException f)
        {
            System.out.println("OUT OF BOUNDS EXCEPTION");
        }
        catch (NoSuchElementException g)
        {
            System.out.println("no such element exception");
        }
        return queue;
    }

}
