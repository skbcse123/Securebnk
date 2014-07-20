package com.mit.MBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.securebank.util.CurrencyConverter;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.StringTokenizer;

@ManagedBean(name = CurrencyConverterMBean.BEAN_NAME)
@ViewScoped
public class CurrencyConverterMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "currencyConverterMBean";
	NumberFormat formatter = new DecimalFormat("#0.00");

	public NumberFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(NumberFormat formatter) {
		this.formatter = formatter;
	}

	private Double amount;
	private String from;
	private String to;
	private Double convResult;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@PostConstruct
	public void init() {
		// System.out.println("in Currency");
	}

	public String calculate() {

		try {

			// convResult=Double.parseDouble(formatter.format(CurrencyConverter.convert(amount,from,to)));
			convResult = CurrencyConverter.convert(amount, from, to);

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return null;
	}

	public Double getConvResult() {
		return convResult;
	}

	public void setConvResult(Double convResult) {
		this.convResult = convResult;
	}
}