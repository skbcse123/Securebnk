package com.mit.MBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 * @author Thiyagu
 * 
 */

@ManagedBean(name = "emiCalculatorBean",eager=true)
@ViewScoped
public class emicalculatorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double principal;
	private double interest;
	private int duration;
	private String period = "Years";
	private int periodRange = 30;
	private double emi;

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getPeriodRange() {
		return periodRange;
	}

	public void setPeriodRange(int periodRange) {
		this.periodRange = periodRange;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public void periodConvertor(ValueChangeEvent v) {

		System.out.println(v.getNewValue() + " is selected.");

		if (v.getNewValue().equals("Months")) {
			this.period = "Months";

			// System.out.println("Before convertor : "+getDuration());
			// System.out.println("Before convertor getPeriodRange : "+getPeriodRange());

			int i = getDuration();
			i *= 12;
			this.duration = i;
			setDuration(duration);
			setPeriodRange(360);

			// System.out.println("After convertor getPeriodRange : "+getPeriodRange());
			// System.out.println("After convertor : " + getDuration());

		} else if (v.getNewValue().equals("Years")) {
			this.period = "Years";

			// System.out.println("Before convertor : "+getDuration());
			// System.out.println("Before convertor getPeriodRange : "+getPeriodRange());

			int i = getDuration();
			i /= 12;
			this.duration = i;
			setDuration(duration);
			setPeriodRange(30);

			// System.out.println("After convertor getPeriodRange : "+getPeriodRange());
			// System.out.println("After convertor : " + getDuration());
		}
	}

	public void calculateEmi() {
		double p = principal;
		int n = duration;
		double r = interest / 12 / 100;

		if (period.equals("Years")) {
			n = duration * 12;
		} else if (period.equals("Months")) {
			n = duration;
		}

		double powerValue = (double) Math.pow((1 + r), n); // power value

		emi = (float) ((p * r * (powerValue)) / ((powerValue) - 1));

		System.out.println("Emi : " + emi);

	}
}
