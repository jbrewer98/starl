package edu.illinois.mitra.starl.models;

import java.util.Random;

import edu.illinois.mitra.starl.exceptions.ItemFormattingException;
import edu.illinois.mitra.starl.interfaces.TrackedRobot;
import edu.illinois.mitra.starl.objects.ItemPosition;
import edu.illinois.mitra.starl.objects.ObstacleList;
import edu.illinois.mitra.starl.objects.Point3d;
import edu.illinois.mitra.starl.objects.PositionList;
/**
 * This class represents a simple model of the quadcopter
 * @author Yixiao Lin
 * @version 1.0
 */

public class Model_quadcopter extends ItemPosition implements TrackedRobot{
	// for default values, see initial_helper()
	public double yaw;
	public double pitch;
	public double roll;
	public double thrust;
	public int radius;
	public int mass;
	public int height;
	
	public double v_x;
	public double v_y;
	public double v_z;
	
	public double v_yaw;
	public double v_pitch;
	public double v_roll;
	
	private double a_yaw;
	private double a_pitch;
	private double a_roll;
		
	public Random rand;
	
	private int x_p;
	private int y_p;
	private int z_p;
	
	private double yaw_p;
	private double pitch_p;
	private double roll_p;
	
	private double v_yaw_p;
	private double v_pitch_p;
	private double v_roll_p;
	
	private double v_x_p;
	private double v_y_p;
	private double v_z_p;
	
	/**
	 * Construct an Model_quadcopter from a received GPS broadcast message
	 * 
	 * @param received GPS broadcast received 
	 * @throws ItemFormattingException
	 */

	public Model_quadcopter(String received) throws ItemFormattingException{
		initial_helper();
		String[] parts = received.replace(",", "").split("\\|");
		if(parts.length == 9) {
			this.name = parts[1];
			this.x = Integer.parseInt(parts[2]);
			this.y = Integer.parseInt(parts[3]);
			this.z = Integer.parseInt(parts[4]);
			this.yaw = Integer.parseInt(parts[5]);
			this.pitch = Integer.parseInt(parts[6]);
			this.roll = Integer.parseInt(parts[7]);			
		} else {
			throw new ItemFormattingException("Should be length 9, is length " + parts.length);
		}
	}
	
	public Model_quadcopter(String name, int x, int y) {
		super(name, x, y, 0);
		initial_helper();
	}
	
	public Model_quadcopter(String name, int x, int y, int z) {
		super(name, x, y, z);
		initial_helper();
	}
	
	public Model_quadcopter(String name, int x, int y, int z, double yaw, double pitch, double roll, int radius) {
		super(name, x, y, z);
		initial_helper();
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;
		this.radius = radius;
	}

	public Model_quadcopter(String name, int x, int y, int z, int yaw) {
		super(name, x, y, z);
		initial_helper();
		this.yaw = yaw;
		}

	
	public Model_quadcopter(ItemPosition t_pos) {
		super(t_pos.name, t_pos.x, t_pos.y, t_pos.z);
		initial_helper();
	}

	@Override 
	public String toString() {
		return name + ": " + x + ", " + y + ", " + z + ", yaw, pitch, roll: " + yaw + ", " + pitch + ", " + roll;
	}
	
	/** 
	 * 
	 * @return true if one robot is facing another robot/point
	 */
	/*
	public boolean isFacing(Point3d other) { 
		if(other == null) {
			return false;
		}
		if(other.x == this.x && other.y == this.y){
			return true;
		}
    	double angleT = Math.toDegrees(Math.atan2((other.y - this.y) , (other.x - this.x)));
    	if(angleT  == 90){
    		if(this.y < other.y)
    			angleT = angleT + 90;
    		double temp = this.angle % 360;
    		if(temp > 0)
    			return true;
    		else
    			return false;
    	}
		if(angleT < 0)
		{
			angleT += 360;
		}
		double angleT1, angleT2, angleself;
		angleT1 = (angleT - 90) % 360;
		if(angleT1 < 0)
		{
			angleT1 += 360;
		}
		angleT2 = (angleT + 90) % 360;
		if(angleT2 < 0)
		{
			angleT2 += 360;
		}
		angleself = this.angle % 360;
		if(angleself < 0)
		{
			angleself += 360;
		}
		if(angleT2 <= 180)
		{
			if((angleself < angleT1) && (angleself > angleT2))
				return false;
			else
				return true;
		}
		else
		{
			if(angleself > angleT2 || angleself < angleT1)
				return false;
			else
				return true;
				
		}
	}
	*/

	/** 
	 * @param other The ItemPosition to measure against
	 * @return Number of degrees this position must rotate to face position other
	 */
	/*
	public <T extends Point3d> int angleTo(T other) {
		if(other == null) {
			return 0;
		}
		
		int delta_x = other.x - this.x;
		int delta_y = other.y - this.y;
		double angle = this.angle;
		int otherAngle = (int) Math.toDegrees(Math.atan2(delta_y,delta_x));
		if(angle > 180) {
			angle -= 360;
		}
		int retAngle = Common.min_magitude((int)(otherAngle - angle),(int)(angle - otherAngle));
		retAngle = retAngle%360;
		if(retAngle > 180) {
			retAngle = retAngle-360;
		}
		if(retAngle <= -180) {
			retAngle = retAngle+360;
		}
		if(retAngle > 180 || retAngle< -180){
			System.out.println(retAngle);
		}
		return  Math.round(retAngle);
	}
	
	public void setPos(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public void setPos(Model_Quadcopter other) {
		this.x = other.x;
		this.y = other.y;
		this.angle = other.angle;
	}
	*/
	
	
	private void initial_helper(){
		height = 50;
		yaw = 0;
		pitch = 0;
		roll = 0;
		radius = 340;
		mass = 500; // default mass is 500 grams
		v_x = 0;
		v_y = 0;
		v_z = 0;
		a_yaw = 0;
		a_pitch = 0;
		a_roll = 0;
	}

	@Override
	public Point3d predict(double[] noises, double timeSinceUpdate) {
		if(noises.length != 6){
			System.out.println("Incorrect number of noises parameters passed in, please pass in x noise, y, noise and angle noise");
			return new Point3d(x,y);
		}
		double xNoise = (rand.nextDouble()*2*noises[0]) - noises[0];
		double yNoise = (rand.nextDouble()*2*noises[1]) - noises[1];
		double zNoise = (rand.nextDouble()*2*noises[2]) - noises[2];

		double yawNoise = (rand.nextDouble()*2*noises[3]) - noises[3];
		double pitchNoise = (rand.nextDouble()*2*noises[4]) - noises[4];
		double rollNoise = (rand.nextDouble()*2*noises[5]) - noises[5];
		
		//TODO: calculate v based on yaw, pitch, roll, thrust
		
		
		
		int dX = (int) (xNoise + v_x*timeSinceUpdate);
		int dY= (int) (yNoise +  v_y*timeSinceUpdate);
		int dZ= (int) (zNoise +  v_z*timeSinceUpdate);
		
		x_p = x+dX;
		y_p = y+dY;
		z_p = z+dZ;
		
		double dv_x = - thrust / mass * (Math.sin(roll) * Math.sin(yaw) + Math.cos(roll) * Math.sin(pitch) * Math.cos(yaw));
		double dv_y = thrust / mass * (Math.sin(roll) * Math.cos(yaw) - Math.cos(roll) * Math.sin(pitch) * Math.sin(yaw));
		double dv_z = 10 - thrust / mass * Math.cos(roll) * Math.cos(pitch);
		
		v_x_p = v_x + dv_x * timeSinceUpdate;
		v_y_p = v_y + dv_y * timeSinceUpdate;
		v_z_p = v_z + dv_z * timeSinceUpdate;
			
		return new Point3d(x_p, y_p, z_p);
	}

	@Override
	public void collision(Point3d collision_point) {
		// No collision point, set both sensor to false
		if(collision_point == null){
			return;
		}
	}

	@Override
	public void updatePos(boolean followPredict) {
		if(followPredict){
			x = x_p;
			y = y_p;
			z = z_p;
			
			yaw = yaw_p;
			pitch = pitch_p;
			roll = roll_p;
			
			v_yaw = v_yaw_p;
			v_pitch = v_pitch_p;
			v_roll = v_roll_p;
			
			v_x = v_x_p;
			v_y = v_y_p;
			v_z = v_z_p;	
		}
	}

	@Override
	public boolean inMotion() {
		return (v_x != 0 || v_y != 0 || v_z != 0 || v_yaw != 0 || v_pitch != 0 || v_roll != 0);
	}

	@Override
	public void initialize() {
		rand = new Random(); //initialize random variable for TrackedRobot
	}

	@Override
	public void updateSensor(ObstacleList obspoint_positions,
			PositionList<ItemPosition> sensepoint_positions) {
		return;
		// no sensor model yet
	}
}
