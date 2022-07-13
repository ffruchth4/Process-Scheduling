package OperatingSystem;
import java.util.ArrayList;
import java.util.Random;

public class Main{
	public static void main(String[] args) {
				
		final int QUANTUM = 5; //number of times the process could run
		
		SimProcessor processor = new SimProcessor();
		
		
		//an arrayList for the ready and blocked PCB
		ArrayList<ProcessControlBlock> ready = new ArrayList<ProcessControlBlock>();
		ArrayList<ProcessControlBlock> blocked = new ArrayList<ProcessControlBlock>();
		
		//add all the processes to ready, creates a process and PCB
		ready.add(new ProcessControlBlock(new SimProcess(1, "Word", 109)));
		ready.add(new ProcessControlBlock(new SimProcess(2, "Eclipse", 205)));
		ready.add(new ProcessControlBlock(new SimProcess(3, "VisualStudio", 325)));
		ready.add(new ProcessControlBlock(new SimProcess(4, "Notepad", 267)));
		ready.add(new ProcessControlBlock(new SimProcess(5, "Slack", 327)));
		ready.add(new ProcessControlBlock(new SimProcess(6, "PowerShell", 289)));
		ready.add(new ProcessControlBlock(new SimProcess(7, "Zoom", 129)));	
		ready.add(new ProcessControlBlock(new SimProcess(8, "PowerPoint", 303)));
		ready.add(new ProcessControlBlock(new SimProcess(9, "SQL", 231)));
		ready.add(new ProcessControlBlock(new SimProcess(10, "XAMPP", 321)));
		
		
		processor.setCurrentProcess(ready.get(0).getProcess());//put first process on processor
		ready.remove(0); //remove it from the list
		ProcessState state = ProcessState.READY; //set the state to ready so it will run
		int count = 0; //count the number of times so it nows when quantum expires
		ProcessControlBlock temp = new ProcessControlBlock(processor.getCurrentProcess()); //set a temporary PCB with the process that is on the processor
		//set 4 registers and the instruction
		int register1;
		int register2;
		int register3;
		int register4;
		int instruction;
		boolean completed = false;
		
		//loop
		for(int i = 0; i < 3000; i++) {
			System.out.print("Step " + i+  " "); //print step
			
			//execute the next statement if none of these three things applied
			if(count != QUANTUM && !state.equals(ProcessState.FINISHED) && !state.equals(ProcessState.BLOCKED))  {
				state = processor.executeNextInstruction();
				count++; //add to count
				
			}
			
			//if the do apply do a context switch
			else if(count == QUANTUM || state.equals(ProcessState.FINISHED) || state.equals(ProcessState.BLOCKED)) {
				
				//get the register values
				register1 = processor.getRegister1();
				register2 = processor.getRegister2();
				register3 = processor.getRegister3();
				register4 = processor.getRegister4();
				//set the registers and instruction of temp PCB
				temp.setRegister1b(register1);				
				temp.setRegister2b(register2);
				temp.setRegister3b(register3);
				temp.setRegister4b(register4);
				instruction = processor.getCurrInstruction();
				temp.setCurrInstructionBlock(instruction);
				
				
				//if the process was blocked then remove temp PCB from the list and add it to the blocked list
				if(state.equals(ProcessState.BLOCKED)) {
					blocked.add(temp);
					System.out.println("*** Process blocked ***");
					state = ProcessState.READY;
					
				}
				//if the process is finished then go onto the next process
				else if(state.equals(ProcessState.FINISHED)) {	
					if(ready.size() == 0) {
						state = ProcessState.FINISHED;
						completed = true;
						System.out.println("*** Processor Idling ***");
					}
					else {
						completed = false;
						System.out.println("*** Process Completed ***");
						state = ProcessState.READY;
					}
				}
				//if it already looped five times then the quantum expired
				else if(count==QUANTUM) {
					
					ready.add(temp);
					System.out.println("*** Quantum expired ***");
					state = ProcessState.READY;
					
				}
				if(!completed) {
				System.out.print("Context Switch: ");
				//if there are still processes on the ready list then do a context switch
				if(ready.size() > 0) {
										
					temp = ready.get(0); //set the temp PCB to the first of the ready list
					ready.remove(0); //remove the first of the ready list
					
					//print the saving process, current instruction, and the 4 registers
					System.out.print("Saving Process: " + processor.getCurrentProcess().getPID() + "\t");
					System.out.print("Current Instruction: " + processor.getCurrInstruction() + "\t");
					System.out.print("R1:" + processor.getRegister1() + "\t");
					System.out.print("R2:" + processor.getRegister2()  + "\t");
					System.out.print("R3:" + processor.getRegister3() + "\t");
					System.out.println("R4:" + processor.getRegister4() + "\t");
					
					//set the processor, current instruction, and registers to the PCB
					processor.setCurrentProcess(temp.getProcess());
					processor.setCurrInstruction(temp.getCurrInstructionBlock());
					//print the restoring process, current Instruction and the 4 registers
					System.out.print("Restoring Process: " + processor.getCurrentProcess().getPID() + "\t");
					System.out.print("Current Instruction: " + processor.getCurrInstruction() + "\t");
					processor.setRegister1(temp.getRegister1b());
					processor.setRegister2(temp.getRegister2b());
					processor.setRegister3(temp.getRegister3b());
					processor.setRegister4(temp.getRegister4b());
				
				
					System.out.print("R1:" + processor.getRegister1() + "\t");
					System.out.print("R2:" + processor.getRegister2()  + "\t");
					System.out.print("R3:" + processor.getRegister3() + "\t");
					System.out.println("R4:" + processor.getRegister4() + "\t");
					
					
				}
				
				//set the counter back to 0 so that it will start again by the next process
				count = 0;
				}
				
				
			}
			//wake up call
			Random rand = new Random();
			for(int j = 0; j < blocked.size(); j++) {
				
			
				if(rand.nextInt(100) < 30) {
					ready.add(blocked.get(j));
					blocked.remove(blocked.get(j));
				}
				
			}
			
			
			
		}
	}
}