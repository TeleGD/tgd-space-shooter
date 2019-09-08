package spaceShooter.entities;

public abstract class Movable extends Entity{

	protected double oldX,oldY;
	protected double speedX,speedY;
	protected double accelX,accelY;
	protected boolean jump;
	protected int jumpLeft;
	protected int jumpMax;
	protected double jumpPower;
	protected boolean collision;

	public void moveX(int delta){
		x+=speedX*delta;
	}

	public void moveY(int delta){
		y+=speedY*delta;
	}

	public double getOldX() {
		return oldX;
	}

	public void setOldX(double oldX) {
		this.oldX = oldX;
	}

	public double getOldY() {
		return oldY;
	}

	public void setOldY(double oldY) {
		this.oldY = oldY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

}
