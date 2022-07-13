package OperatingSystem;

public class ProcessControlBlock {
	SimProcess process;
	int currInstructionBlock;
	int register1b;
	int register2b;
	int register3b;
	int register4b;
	
	//is this how the constructor is supposed to look??
	public ProcessControlBlock(SimProcess process/*, int currInstructionBlock, int register1b, int register2b, int register3b, int register4b*/) {
		this.process = process;
		this.currInstructionBlock = 0;
		/*this.register1b = register1b;
		this.register2b = register2b;
		this.register3b = register3b;
		this.register4b = register4b;*/
	}

	public int getCurrInstructionBlock() {
		return currInstructionBlock;
	}
	public void setProcess(SimProcess process) {
		this.process = process;
	}

	public void setCurrInstructionBlock(int currInstructionBlock) {
		this.currInstructionBlock = currInstructionBlock;
	}

	public int getRegister1b() {
		return register1b;
	}

	public void setRegister1b(int register1b) {
		this.register1b = register1b;
	}

	public int getRegister2b() {
		return register2b;
	}

	public void setRegister2b(int register2b) {
		this.register2b = register2b;
	}

	public int getRegister3b() {
		return register3b;
	}

	public void setRegister3b(int register3b) {
		this.register3b = register3b;
	}

	public int getRegister4b() {
		return register4b;
	}

	public void setRegister4b(int register4b) {
		this.register4b = register4b;
	}

	public SimProcess getProcess() {
		return process;
	}
}
