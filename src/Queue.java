import java.util.ArrayList;
import java.util.concurrent.locks.*;
/*
 * ReentrantLock is mutual exclusive lock, similar to implicit
 * locking provided by synchronized keyword in Java, with extended
 * feature like fairness, which can be used to provide lock to
 * longest waiting thread. Lock is acquired by lock() method and
 * held by Thread until a call to unlock() method. Fairness  parameter
 * is provided while creating instance of ReentrantLock in constructor.
 *
 * The main difference between synchronized and ReentrantLock is ability
 * to trying for lock interruptibly, and with timeout. Thread doesnít need
 * to block infinitely, which was the case with synchronized.
 */
/*
class Queue
{
    String value;
    Lock lock = new ReentrantLock();
    int index;

    public Queue()
    {
        System.out.println(this.);
    }

    String get()
    {
        lock.lock();
        System.out.println("Got value " + value);
        lock.unlock();
        return value;
    }

        void put(int n, ArrayList<String[]> list)
        {
            lock.lock();
            this.value = list.get(n)[0];

            System.out.println("Put: " + n);
            lock.unlock();
    }
}
*/