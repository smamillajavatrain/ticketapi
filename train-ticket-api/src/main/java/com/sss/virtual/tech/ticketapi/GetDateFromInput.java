/**
 * 
 */
package com.sss.virtual.tech.ticketapi;

/**
 * @author DELL
 *
 */
public class GetDateFromInput {

	public static void addDays(int daysToAdd) {
		// Initial date: 06-05-2025 (dd-mm-yyyy)
		int day = 6;
		int month = 5;
		int year = 2025;

		int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (daysToAdd >= 0) {
			day += daysToAdd;

			while (day > daysInMonth[month - 1]) {
				day -= daysInMonth[month - 1];
				month++;

				if (month > 12) {
					month = 1;
					year++;
				}
			}
		} else {
			day += daysToAdd; // since daysToAdd is negative

			while (day <= 0) {
				month--;

				if (month < 1) {
					month = 12;
					year--;
				}

				day += daysInMonth[month - 1];
			}
		}

		System.out.printf("New Date after adding %d days: %02d-%02d-%04d%n", daysToAdd, day, month, year);
	}

	private static int calculateNoOfDaysUsingMonth(int month) {
		int noOfDays = 0;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			noOfDays = 31;
		} else if (month == 2) {
			noOfDays = 29;
		} else {
			noOfDays = 30;
		}
		return noOfDays;
	}

	public static void main(String[] args) {
//	    Without use of any date aware functions or classes write a method (pseudocode acceptable) 
//	    called addDays that takes an integer number of days that will add the given number of days to the date. 
//	    You can assume that the Gregorian calendar exists for all time and that there are no leap years.
//	    06-05-2025      
		/*
		 * input to addDays = 34; currentDate is 06-05-2025 --> 34 need to add here 34+6
		 * = 40 after adding 31
		 * 
		 * 
		 * 
		 */
		addDays(60); // Forward
		addDays(-80); // Backward
	}
}