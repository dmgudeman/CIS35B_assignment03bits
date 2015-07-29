import java.util.ArrayList;
import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable
{
    LinkedBlockingQueue<BitSet> queue;
    ArrayList<BitSet> list = new ArrayList<>();
    static int LENGTH = 0;

    Producer(LinkedBlockingQueue<BitSet> queue, ArrayList<BitSet> list) throws InterruptedException
    {
        this.queue = queue;
        this.list = list;
        new Thread(this).start();
        LENGTH = list.size();
    }

    public void run()
    {
        queue = packTheQueue(queue, list);

    }

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
            System.out.println("no susch element exception");
        }
        return queue;
    }

}
