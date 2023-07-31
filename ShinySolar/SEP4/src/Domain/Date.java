package Domain;

import java.sql.Timestamp;
public class Date
{
	private Timestamp date;
	public Date(Timestamp startDate) {
		this.date = startDate;
	}
	public Timestamp date(int i) {
		long j = i * 1000L;
		Timestamp time = new Timestamp(date.getTime() + j);
		date = time;
		return time;
	}
}