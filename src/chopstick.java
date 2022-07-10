import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class chopstick {
    private final ReentrantLock lock = new ReentrantLock(true);         //For synchronization and fairness
    private int id;
    public chopstick(int id){
        this.id = id;
    }

    public boolean pickUp(philosopher p) throws InterruptedException{
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)){                //A philosopher tries to get a lock for 10ms
            return true;
        }else {
            return false;
        }
    }
    public void putDown(philosopher p){
        lock.unlock();
    }                   //The philosopher returns the lock
}
