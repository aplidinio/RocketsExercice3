package Rockets;

import java.util.*;
import java.util.concurrent.*;

public class Rocket extends Thread{
	
	private String codeRocket;
	private ArrayList <Thruster> thrusters= new ArrayList<Thruster>();
	private int [] speed;
	private int [] power;
	private int setAcceleration;
	
	public Rocket(String codeRocket, ArrayList<Thruster> thrusters, int []speed, int[]power) {
		
		this.codeRocket=codeRocket;
		this.thrusters=thrusters;
		this.speed=speed;
		this.power=power;
		
	}
	public String getCodeRocket() {
		return codeRocket;
	}
	public void setCodeRocket(String codeRocket) {
		this.codeRocket = codeRocket;
	}
	public ArrayList<Thruster> getThrusters() {
		return thrusters;
	}
	public void setThrusters(ArrayList<Thruster> thrusters) {
		this.thrusters = thrusters;
	}	
	public int[] getSpeed() {
		return speed;
	}
	public void setSpeed(int[] speed) {
		this.speed = speed;
	}
	public int[] getPower() {
		return power;
	}
	public void setPower(int[] power) {
		this.power = power;
	}
	public void calculateSpeed() {
		
		int power=0, speed=0;
		
		for (Thruster e:thrusters) {
			power+=e.getCurrentPower();
		}
		speed = (int)(100 *Math.sqrt(power));
		System.out.println("Current speed: " + speed);
	}
	
	public void setAcceleration(int increment) {
		this.setAcceleration=increment;
	}
	
	public int getAcceleration() {
		return setAcceleration;
	}
	
	
	
	@Override
	public String toString() {
		return "Rocket [codeRocket=" + codeRocket + " has thrusters=" + thrusters.size() + "]";
	}
	
	public void run() {
		
		int speed;
				
		ExecutorService exec = Executors.newFixedThreadPool(thrusters.size());
		
		for (int i=0; i<thrusters.size(); i++){
			
			speed = Math.round((thrusters.get(i).getMaxPower())/(100*getAcceleration()));
			
			if (speed<1 && speed>=0)//Necesario para evitar speed=0
				speed=1;			
			
			if (getAcceleration()>=0) {
				thrusters.get(i).accelerate(speed);
			} else {
				thrusters.get(i).decelerate(speed);
			}
			Runnable engine = new Thread(thrusters.get(i));
			exec.execute(engine);
			
		}
		exec.shutdown();
		while(!exec.isTerminated()) {
			
			try {
				sleep(200);
				calculateSpeed();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
        
	}
	
	
}

