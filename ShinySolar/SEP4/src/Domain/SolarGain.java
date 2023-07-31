package Domain;

public class SolarGain
{
	public double a = 1.5 * Math.PI;
	public SolarGain() {
	}
	public int solarGain() {
		double i = Math.PI / 5;
		double j = (Math.random()* i);
		a += j;
		double m = 2 * Math.PI;
		if(a > m) {
			a -= m;
		}
		double l = 500;
		double x = (1000 + (Math.sin(a)* l));
		return (int) x;
	}
}