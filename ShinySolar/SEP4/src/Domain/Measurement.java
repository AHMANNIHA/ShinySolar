package Domain;

import java.sql.Timestamp;
public class Measurement
{
	private final String type;
	private final int id;
	private final int solarGain;
	private final Timestamp date;
	private final double outputPower;
	private double voltage;
	private double current;
	private double tempIn;
	private double tempOut;
	private int timeInSec;
	private int waterFlow;
	public Measurement(int id, int solarGain, Timestamp time) {
		type = "PV";
		this.id = id;
		this.solarGain = solarGain;
		this.date = time;
		this.outputPower = outputPower();
		this.voltage = voltage();
		this.current = current();
	}
	public Measurement(int id, int solarGain, Timestamp time, boolean th) {
		type = "TH";
		this.id = id;
		this.solarGain = solarGain;
		this.date = time;
		this.outputPower = outputPower();
		this.tempIn = tempIn();
		this.tempOut = tempOut();
		this.timeInSec = time();
		this.waterFlow = waterFlow();
	}
	public double getOutputPower() {
		return outputPower;
	}
	public int getId() {
		return id;
	}
	public int getSolarGain() {
		return solarGain;
	}
	public double getVoltage() {
		return voltage;
	}
	public double getCurrent() {
		return current;
	}
	public double getTempIn() {
		return tempIn;
	}
	public double getTempOut() {
		return tempOut;
	}
	public int getTimeInSec() {
		return timeInSec;
	}
	public int getWaterFlow() {
		return waterFlow;
	}
	public Timestamp getDate() {
		return date;
	}
	private double tempIn() {
		double x = 10.5;
		double n = 8.7;
		return Math.random()*(x-n+1)+n;
	}
	private double tempOut() {
		double x = 12.8;
		double n = 12.6;
		return Math.random()*(x-n+1)+n;
	}
	private int time() {
		int x = 32;
		int n = 0;
		double number = Math.random() * (double)(x - n + 1) + (double)n;
		return (int)number;
	}
	private int waterFlow() {
		int x = 10;
		int n = 3;
		double r = Math.random()*(x-n+1)+n;
		return (int)r;
	}
	private double voltage() {
		double x = 20.6;
		double n = 9;
		return Math.random()*(x-n+1)+n;
	}
	private double current() {
		double x = 0.63;
		double n = 0.28;
		return Math.random()*(x-n+1)+n;
	}
	private double outputPower() {
		double e;
		if(type.equals("PV")) {
			e = (Math.random()*0.18)+0.10;
		}else {
			e = (Math.random()*0.20)+0.40;
		}
		double r = solarGain*e* (double) 1;
		if (r >= 10000) {
			return r/10000;
		}else {
			return r;
		}
	}
}