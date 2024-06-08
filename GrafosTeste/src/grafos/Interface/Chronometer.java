/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.Interface;

/**
 *
 * @author vinic
 */
public class Chronometer
{
    private boolean running;
    private long startTime;

    // set running to false
    public Chronometer ()
    {
        running = false;
    }
	
    // Set running to true (is running the chornometer)
    public void start()
    {
        running = true;

        // ge current time in milli seconds
        startTime = System.currentTimeMillis();
    }

    // Call the get time function and sign false to running (end of execution)
    public void stop()
    {
        startTime = getTime();
        running = false;
    }

    // if running, return the difference of current time to the
    // start time (too logic!)
    // else, just return the start time
    public long getTime()
    {
        if (running) return System.currentTimeMillis() - startTime;
        else  return startTime;
    }
    
    public boolean isRunning() {
        return running;
    }
}
