import java.util.*;
import java.util.concurrent.*;

/**
 * Created by davidgudeman on 7/23/15.
 */
public class CommandEnumerationTask
{
    private LinkedBlockingQueue<String[]> queue;
    private ArrayList<String[]> commandList;

    /**
     */
    public CommandEnumerationTask(LinkedBlockingQueue<String[]> queue, ArrayList<String[]> commandList) throws InterruptedException
    {
        this.queue = queue;

        this.commandList = commandList;
        enumerate(commandList);
        System.out.println("INSIDE COMMANDENUMERATIONTASK CONSTRUCOTR queue.size" + queue.size());
    }


    /**
     */
    public LinkedBlockingQueue enumerate(ArrayList<String[]> alist) throws InterruptedException
    {
        System.out.println("enumeration has been called alist.size()= " + alist.size());
        for (int i = 0; i < alist.size()-1; i++)
        {
            queue.put(alist.get(i));
            System.out.println();

            System.out.println("inside enumeration loop alist.get(i)[0]" + alist.get(i)[0] + "      aList.size() " + alist.size());
            System.out.println("queue.size() ==================== " + queue.size());
        }
       return queue;
    }


    public void examineQueue (BlockingQueue<String[]> queue) throws InterruptedException
    {
        int len = queue.size();
        System.out.println("MADE IT HERE EXAMINEQUEUE!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < len - 1; i++)
        {
            String[] s = queue.take();
            for (int j = 0; j <= 5; j++)
                System.out.println("inside examineQueue loop poll" + s[j] );

        }
    }
}

