package OperatingSystem;
import java.util.Random;

public class SimProcessor {
	SimProcess currentProcess;
	int register1;
	int register2;
	int register3;
	int register4;
	int currInstruction;
	
	
	
	public ProcessState executeNextInstruction() {
		ProcessState state = currentProcess.execute(currInstruction);
		currInstruction++;//increment current instruction
		
		//generates 4 random numbers for the registers
		Random rand = new Random();
		register1 = rand.nextInt(100)+1;
		register2 = rand.nextInt(100)+1;
		register3 = rand.nextInt(100)+1;
		register4 = rand.nextInt(100)+1;
		return state;
		
	}

	public SimProcess getCurrentProcess() {
		return currentProcess;
	}

	public void setCurrentProcess(SimProcess currentProcess) {
		this.currentProcess = currentProcess;
	}

	public int getRegister1() {
		return register1;
	}

	public void setRegister1(int register1) {
		this.register1 = register1;
	}

	public int getRegister2() {
		return register2;
	}

	public void setRegister2(int register2) {
		this.register2 = register2;
	}

	public int getRegister3() {
		return register3;
	}

	public void setRegister3(int register3) {
		this.register3 = register3;
	}

	public int getRegister4() {
		return register4;
	}

	public void setRegister4(int register4) {
		this.register4 = register4;
	}

	public int getCurrInstruction() {
		return currInstruction;
	}

	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	
	

}
