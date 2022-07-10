
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class philosopher implements Runnable{
    private int id;
    private chopstick left;
    private chopstick right;

    public philosopher(int id, chopstick left, chopstick right){
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        try{
            while(miscsubs.TotalEats < miscsubs.MAX_EATS){                  //If totalEats is over 500, then stop
                miscsubs.RandomDelay();                                     //Wait
                if(left.pickUp(this)){                                   //Pick left chopstick
                    if(right.pickUp(this)) {                             //Pick right chopstick
                        //If philosopher gets both chopsticks, then start eating
                        if(miscsubs.TotalEats < miscsubs.MAX_EATS){         //There might be some remaining thread needed to be finished
                            miscsubs.StartEating(id);                       //Start eating
                            miscsubs.RandomDelay();                         //Do nothing for a random time
                            miscsubs.DoneEating(id);                        //Finish eating
                        }
                    }
                    right.putDown(this);                                 //Put down right chopstick
                }
                left.putDown(this);                                      //Put down left chopstick
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
