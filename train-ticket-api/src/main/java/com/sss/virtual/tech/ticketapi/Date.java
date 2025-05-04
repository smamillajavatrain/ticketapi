/**
 * 
 */
package com.sss.virtual.tech.ticketapi;

/**
 * @author DELL
 *
 */
class Date {

	public int year;
    public int month;
    public int dayOfMonth;
    
    public Date(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
      }
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Date [year=");
		builder.append(year);
		builder.append(", month=");
		builder.append(month);
		builder.append(", dayOfMonth=");
		builder.append(dayOfMonth);
		builder.append("]");
		return builder.toString();
	}



	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the dayOfMonth
	 */
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	/**
	 * @param dayOfMonth the dayOfMonth to set
	 */
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
}