// Use java threads to simulate the Dining Philosophers Problem
// YOUR NAME HERE.  Programming assignment 2 (from ece.gatech.edu) */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class dining
{
    public static void main(String args[]) throws Exception
    {
        System.out.println("Starting the Dining Philosophers Simulation\n");
        miscsubs.InitializeChecking();
        philosopher[] p = new philosopher[miscsubs.NUMBER_PHILOSOPHERS];            //Make philosophers array
        chopstick[] c = new chopstick[miscsubs.NUMBER_CHOPSTICKS];                   //Make chopsticks array
        ExecutorService executorService;                                        //Create thread pool and do parallel processing
        executorService = Executors.newFixedThreadPool(miscsubs.NUMBER_PHILOSOPHERS);   //Create thread

        for (int i = 0; i < miscsubs.NUMBER_CHOPSTICKS; i++) {                      //Make chopstick objects
            c[i] = new chopstick(i);
        }
        for (int i = 0; i < miscsubs.NUMBER_PHILOSOPHERS; i++) {                     //Make philosopher objects with chopsticks
            p[i] = new philosopher(i, c[i], c[(i + 1) % miscsubs.NUMBER_PHILOSOPHERS]);
            executorService.execute(p[i]);                                          //Execute each philosopher
        }

        executorService.shutdown();                                                     //Shut down thread
        while (!executorService.isTerminated()) {                                     //If all tasks have completed following shut down, then quit while statement
            Thread.sleep(1000);
        }

        // End of your code
        System.out.println("Simulation Ends..");
        miscsubs.LogResults();
    }
};


