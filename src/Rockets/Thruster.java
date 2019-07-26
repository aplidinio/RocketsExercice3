package Rockets;

public class Thruster extends Thread {
	
	private int rocket;
	private int identification;
	private int maxPower;
	private int currentPower;
	
	public Thruster(int rocket, int identification, int maxPower) {
		
		this.rocket=rocket;
		this.identification=identification;
		this.maxPower=maxPower;
	}
	
	public int getRocket() {
		return rocket;
	}
	public void setRocket(int rocket) {
		this.rocket = rocket;
	}
	public int getIdentification() {
		return identification;
	}
	public void setIdentification(int identification) {
		this.identification = identification;
	}
	public int getMaxPower() {
		return maxPower;
	}
	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}
	
	public int getCurrentPower() {
		return currentPower;
	}
	
	
	@Override
	public String toString() {
		
		return "Rocket " + rocket + " Thruster " + identification + " -->  Current Power: " + currentPower;
	}
		
	public void accelerate(int setAcceleration) {
		
		currentPower+=setAcceleration;
		
		if (currentPower>this.maxPower)
			currentPower=this.maxPower;
		
		System.out.println(toString());
		getCurrentPower();
			
		}
	
	public void decelerate(int setDeceleration) {
		
	
		currentPower+=setDeceleration;

	
		if (currentPower>=this.maxPower) 
			currentPower=this.maxPower;
		
		
		if (currentPower<0)
			currentPower=0;
		
		System.out.println(toString());
		getCurrentPower();
		
	}
	
}



