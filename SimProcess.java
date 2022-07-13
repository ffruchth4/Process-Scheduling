package OperatingSystem;
import java.util.Random;



public class SimProcess{
	int pid;
	String procName;
	int totalInstructions;
	
	/**
	 * The simProcess constructor takes pid, procName, totalInstructions and sets it.
	 * @param pid the process ID
	 * @param procName the process name
	 * @param totalInstructions the total instructions of the process
	 */
	public SimProcess(int pid, String procName, int totalInstructions) {
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
	}
	
	/**
	 * the execute method returns a state based on percentage
	 * @param i what number instruction it is up to.
	 * @return the state
	 */
	public ProcessState execute(int i) {
		ProcessState ps;
		Random rand = new Random();
		System.out.println("Process: " + procName + " PID: " + pid + " " + "Executing Instruction: " + i);
	
		if(i >= totalInstructions) {
			ps = ProcessState.FINISHED;
			return ps;
		}
		else if(rand.nextInt(100) < 15) {
			ps = ProcessState.BLOCKED;
			//System.out.println("Ps: blocked");
			return ps;
		}
		else {
			ps = ProcessState.READY;
			return ps;
		}
		
	}

	/**
	 * Gets the Process ID
	 * @return process ID
	 */
	public int getPID() {
		
		return pid;
	}

	public int getTotalInstructions() {
		return totalInstructions;
	}
}
