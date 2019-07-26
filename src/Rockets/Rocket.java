package Rockets;

import java.util.*;
import java.util.concurrent.*;

public class Rocket extends Thread{
	
	private String codeRocket;
	private ArrayList <Thruster> thrusters= new ArrayList<Thruster>();
	private double acceleration;
	
	public Rocket(String codeRocket, ArrayList<Thruster> thrusters) {
		
		this.codeRocket=codeRocket;
		this.thrusters=thrusters;
		
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
	public void calculateSpeed() {
		
		int calcPower=0, calcSpeed=0;
		
		for (Thruster e:thrusters) {
			calcPower+=e.getCurrentPower();
		}
		calcSpeed = (int)(100 *Math.sqrt(calcPower));
		System.out.println("Current speed: " + calcSpeed);
	}
	
	public void setAcceleration(int speed, int power) {
		
		this.acceleration=Math.abs(power)/speed;
			}
	
	public double getAcceleration() {
		return acceleration;
	}
	
	@Override
	public String toString() {
		return "Rocket [codeRocket=" + codeRocket + " has thrusters=" + thrusters.size() + "]";
	}
	
	public void run() {
		
		double speed;
				
		ExecutorService exec = Executors.newFixedThreadPool(thrusters.size());
		
		for (int i=0; i<thrusters.size(); i++){
			
			speed = thrusters.get(i).getMaxPower()*getAcceleration()/100;
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

