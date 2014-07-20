package com.mit.MBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mit.service.IService;
import com.mit.serviceImpl.ServiceImpl;
import com.secure.Modal.BseTable;
import com.secure.Modal.CommonTable;
import com.secure.Modal.NseTable;
import com.securebank.exception.SecureBankException;
import com.securebank.util.SecureBankUtil;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;

/**
 * @author Mit
 * 
 */

@ViewScoped
@ManagedBean(name = "MasterHomeMBean", eager = false)
public class MasterHomeMBean implements Serializable {

	@ManagedProperty(value = "#{service}")
	private IService service = new ServiceImpl();
	private ApplicationContext context;
	private List<NseTable> nseStockList;
	private List<BseTable> bseStockList;
	private List<CommonTable> generalValuesList;
	private double sensexOpen;
	private double sensexPrevClose;
	private double sensexHigh;
	private double sensexLow;
	private double niftyOpen;
	private double niftyPrevClose;
	private double niftyHigh;
	private double niftyLow;
	private double goldPrevClose;
	private List<SelectItem> symbolItemList;
	private List<SelectItem> newSymbolItemList;
	private static final Logger SECUREBANK_LOGGER = Logger
			.getLogger(MasterHomeMBean.class);
	private String header;
	private Long selectedSymbol;
	private String selectedString;
	private StringBuffer[] nseStockHistory;
	private StringBuffer[] bseStockHistory;
	private StringBuffer bsePreviousTime;
	private StringBuffer nsePreviousTime;
	private boolean sensexColorFlag;
	private boolean niftyColorFlag;
	private boolean sensexHighColorFlag;
	private boolean niftyHighColorFlag;
	

	private boolean loginButtonFlag;
	private boolean nseFirst = true;
	private boolean bseFirst = true;
	private boolean lineFlag = true;
	private boolean candleStickFlag;
	private boolean barFlag;
	private DateFormat formatter = new SimpleDateFormat("hh:mm");
	private CartesianChartModel niftyCategoryModel;
	private CartesianChartModel sensexCategoryModel;
	private OhlcChartModel ohlcModel2;
	private OhlcChartModel ohlcModel;
	private boolean bseStop;
	private boolean nseStop;
	private boolean chartStop;

	// setters and getters

	/**
	 * @return the chartStop
	 */
	public boolean isChartStop() {
		return chartStop;
	}

	/**
	 * @param chartStop
	 *            the chartStop to set
	 */
	public void setChartStop(boolean chartStop) {
		this.chartStop = chartStop;
	}

	/**
	 * @return the bseStop
	 */
	public boolean isBseStop() {
		return bseStop;
	}

	/**
	 * @param bseStop
	 *            the bseStop to set
	 */
	public void setBseStop(boolean bseStop) {
		this.bseStop = bseStop;
	}

	/**
	 * @return the nseStop
	 */
	public boolean isNseStop() {
		return nseStop;
	}

	/**
	 * @param nseStop
	 *            the nseStop to set
	 */
	public void setNseStop(boolean nseStop) {
		this.nseStop = nseStop;
	}

	public OhlcChartModel getOhlcModel() {
		return ohlcModel;
	}

	public void setOhlcModel(OhlcChartModel ohlcModel) {
		this.ohlcModel = ohlcModel;
	}

	public OhlcChartModel getOhlcModel2() {
		return ohlcModel2;
	}

	public void setOhlcModel2(OhlcChartModel ohlcModel2) {
		this.ohlcModel2 = ohlcModel2;
	}

	public CartesianChartModel getSensexCategoryModel() {
		return sensexCategoryModel;
	}

	public void setSensexCategoryModel(CartesianChartModel sensexCategoryModel) {
		this.sensexCategoryModel = sensexCategoryModel;
	}

	public CartesianChartModel getNiftyCategoryModel() {
		return niftyCategoryModel;
	}

	public void setNiftyCategoryModel(CartesianChartModel niftyCategoryModel) {
		this.niftyCategoryModel = niftyCategoryModel;
	}

	public boolean isBarFlag() {
		return barFlag;
	}

	public void setBarFlag(boolean barFlag) {
		this.barFlag = barFlag;
	}

	public boolean isLineFlag() {
		return lineFlag;
	}

	public boolean isCandleStickFlag() {
		return candleStickFlag;
	}

	public void setCandleStickFlag(boolean candleStickFlag) {
		this.candleStickFlag = candleStickFlag;
	}

	public void setLineFlag(boolean lineFlag) {
		this.lineFlag = lineFlag;
	}

	/**
	 * @return the nseFirst
	 */
	public boolean isNseFirst() {
		return nseFirst;
	}

	/**
	 * @param nseFirst
	 *            the nseFirst to set
	 */
	public void setNseFirst(boolean nseFirst) {
		this.nseFirst = nseFirst;
	}

	/**
	 * @return the bseFirst
	 */
	public boolean isBseFirst() {
		return bseFirst;
	}

	/**
	 * @param bseFirst
	 *            the bseFirst to set
	 */
	public void setBseFirst(boolean bseFirst) {
		this.bseFirst = bseFirst;
	}

	/**
	 * @return the bsePreviousTime
	 */
	public StringBuffer getBsePreviousTime() {
		return bsePreviousTime;
	}

	/**
	 * @param bsePreviousTime
	 *            the bsePreviousTime to set
	 */
	public void setBsePreviousTime(StringBuffer bsePreviousTime) {
		this.bsePreviousTime = bsePreviousTime;
	}

	/**
	 * @return the nsePreviousTime
	 */
	public StringBuffer getNsePreviousTime() {
		return nsePreviousTime;
	}

	/**
	 * @param nsePreviousTime
	 *            the nsePreviousTime to set
	 */
	public void setNsePreviousTime(StringBuffer nsePreviousTime) {
		this.nsePreviousTime = nsePreviousTime;
	}

	/**
	 * @return the formatter
	 */
	public DateFormat getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter
	 *            the formatter to set
	 */
	public void setFormatter(DateFormat formatter) {
		this.formatter = formatter;
	}

	/**
	 * @return the bseStockHistory
	 */
	public StringBuffer[] getBseStockHistory() {
		return bseStockHistory;
	}

	/**
	 * @param bseStockHistory
	 *            the bseStockHistory to set
	 */
	public void setBseStockHistory(StringBuffer[] bseStockHistory) {
		this.bseStockHistory = bseStockHistory;
	}

	/**
	 * @return the nseStockHistory
	 */
	public StringBuffer[] getNseStockHistory() {
		return nseStockHistory;
	}

	/**
	 * @param nseStockHistory
	 *            the nseStockHistory to set
	 */
	public void setNseStockHistory(StringBuffer[] nseStockHistory) {
		this.nseStockHistory = nseStockHistory;
	}

	/**
	 * @return the selectedString
	 */
	public String getSelectedString() {
		return selectedString;
	}

	/**
	 * @param selectedString
	 *            the selectedString to set
	 */
	public void setSelectedString(String selectedString) {
		this.selectedString = selectedString;
	}

	/**
	 * @return the selectedSymbol
	 */
	public Long getSelectedSymbol() {
		return selectedSymbol;
	}

	/**
	 * @param selectedSymbol
	 *            the selectedSymbol to set
	 */
	public void setSelectedSymbol(Long selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
	}

	/**
	 * @return the goldPrevClose
	 */
	public double getGoldPrevClose() {
		return goldPrevClose;
	}

	/**
	 * @param goldPrevClose
	 *            the goldPrevClose to set
	 */
	public void setGoldPrevClose(double goldPrevClose) {
		this.goldPrevClose = goldPrevClose;
	}

	/**
	 * @return the generalValuesList
	 */
	public List<CommonTable> getGeneralValuesList() {
		return generalValuesList;
	}

	/**
	 * @return the symbolItemList
	 */
	public List<SelectItem> getSymbolItemList() {
		return symbolItemList;
	}

	/**
	 * @param symbolItemList
	 *            the symbolItemList to set
	 */
	public void setSymbolItemList(List<SelectItem> symbolItemList) {
		this.symbolItemList = symbolItemList;
	}

	/**
	 * @return the newSymbolItemList
	 */
	public List<SelectItem> getNewSymbolItemList() {
		return newSymbolItemList;
	}

	/**
	 * @param newSymbolItemList
	 *            the newSymbolItemList to set
	 */
	public void setNewSymbolItemList(List<SelectItem> newSymbolItemList) {
		this.newSymbolItemList = newSymbolItemList;
	}

	/**
	 * @param generalValuesList
	 *            the generalValuesList to set
	 */
	public void setGeneralValuesList(List<CommonTable> generalValuesList) {
		this.generalValuesList = generalValuesList;
	}

	/**
	 * @return the sensexHighColorFlag
	 */
	public boolean isSensexHighColorFlag() {
		return sensexHighColorFlag;
	}

	/**
	 * @param sensexHighColorFlag
	 *            the sensexHighColorFlag to set
	 */
	public void setSensexHighColorFlag(boolean sensexHighColorFlag) {
		this.sensexHighColorFlag = sensexHighColorFlag;
	}

	/**
	 * @return the niftyHighColorFlag
	 */
	public boolean isNiftyHighColorFlag() {
		return niftyHighColorFlag;
	}

	/**
	 * @param niftyHighColorFlag
	 *            the niftyHighColorFlag to set
	 */
	public void setNiftyHighColorFlag(boolean niftyHighColorFlag) {
		this.niftyHighColorFlag = niftyHighColorFlag;
	}

	public boolean isSensexColorFlag() {
		return sensexColorFlag;
	}

	public void setSensexColorFlag(boolean sensexColorFlag) {
		this.sensexColorFlag = sensexColorFlag;
	}

	public boolean isNiftyColorFlag() {
		return niftyColorFlag;
	}

	public void setNiftyColorFlag(boolean niftyColorFlag) {
		this.niftyColorFlag = niftyColorFlag;
	}

	/**
	 * @return the nseStockList
	 */
	public List<NseTable> getNseStockList() {
		return nseStockList;
	}

	/**
	 * @param nseStockList
	 *            the nseStockList to set
	 */
	public void setNseStockList(List<NseTable> nseStockList) {
		this.nseStockList = nseStockList;
	}

	/**
	 * @return the bseStockList
	 */
	public List<BseTable> getBseStockList() {
		return bseStockList;
	}

	/**
	 * @param bseStockList
	 *            the bseStockList to set
	 */
	public void setBseStockList(List<BseTable> bseStockList) {
		this.bseStockList = bseStockList;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the sensexOpen
	 */
	public double getSensexOpen() {
		return sensexOpen;
	}

	/**
	 * @param sensexOpen
	 *            the sensexOpen to set
	 */
	public void setSensexOpen(double sensexOpen) {
		this.sensexOpen = sensexOpen;
	}

	/**
	 * @return the sensexPrevClose
	 */
	public double getSensexPrevClose() {
		return sensexPrevClose;
	}

	/**
	 * @param sensexPrevClose
	 *            the sensexPrevClose to set
	 */
	public void setSensexPrevClose(double sensexPrevClose) {
		this.sensexPrevClose = sensexPrevClose;
	}

	/**
	 * @return the sensexHigh
	 */
	public double getSensexHigh() {
		return sensexHigh;
	}

	/**
	 * @param sensexHigh
	 *            the sensexHigh to set
	 */
	public void setSensexHigh(double sensexHigh) {
		this.sensexHigh = sensexHigh;
	}

	/**
	 * @return the sensexLow
	 */
	public double getSensexLow() {
		return sensexLow;
	}

	/**
	 * @param sensexLow
	 *            the sensexLow to set
	 */
	public void setSensexLow(double sensexLow) {
		this.sensexLow = sensexLow;
	}

	/**
	 * @return the niftyOpen
	 */
	public double getNiftyOpen() {
		return niftyOpen;
	}

	/**
	 * @param niftyOpen
	 *            the niftyOpen to set
	 */
	public void setNiftyOpen(double niftyOpen) {
		this.niftyOpen = niftyOpen;
	}

	/**
	 * @return the niftyClose
	 */
	public double getNiftyPrevClose() {
		return niftyPrevClose;
	}

	/**
	 * @param niftyClose
	 *            the niftyClose to set
	 */
	public void setNiftyPrevClose(double niftyPrevClose) {
		this.niftyPrevClose = niftyPrevClose;
	}

	/**
	 * @return the niftyHigh
	 */
	public double getNiftyHigh() {
		return niftyHigh;
	}

	/**
	 * @param niftyHigh
	 *            the niftyHigh to set
	 */
	public void setNiftyHigh(double niftyHigh) {
		this.niftyHigh = niftyHigh;
	}

	/**
	 * @return the niftyLow
	 */
	public double getNiftyLow() {
		return niftyLow;
	}

	/**
	 * @param niftyLow
	 *            the niftyLow to set
	 */
	public void setNiftyLow(double niftyLow) {
		this.niftyLow = niftyLow;
	}

	/**
	 * @return the loginButtonFlag
	 */
	public boolean isLoginButtonFlag() {
		return loginButtonFlag;
	}

	/**
	 * @param loginButtonFlag
	 *            the loginButtonFlag to set
	 */
	public void setLoginButtonFlag(boolean loginButtonFlag) {
		this.loginButtonFlag = loginButtonFlag;
	}

	/**
	 * @return the securebankLogger
	 */
	public static Logger getSecurebankLogger() {
		return SECUREBANK_LOGGER;
	}


	public ApplicationContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	// Methods

	@PostConstruct
	public void init() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		if (httpSession.getAttribute("CUSTOMERLOGINID") == null) {
			setLoginButtonFlag(true);
		}
		// System.out.println("in home");
		loadInitialValues();

	}

	private void loadInitialValues() {

		context = new ClassPathXmlApplicationContext("beans.xml");
		symbolItemList = (List<SelectItem>) context.getBean("list");

		nsePreviousTime = new StringBuffer();
		bsePreviousTime = new StringBuffer();

		// executeThreads();

		nsePreviousTime.append("");
		bsePreviousTime.append("");

		// loadGeneralValues();
		// loadBseStockList();
		createCategoryModel();

	}

	private void executeThreads() {

		Thread first = new Thread(new Runnable() {
			public void run() {
				try {
					// //System.out.println("in first...");
					Thread.sleep(200);
					loadGeneralValues();
					// //System.out.println("in first after loading list...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		Thread second = new Thread(new Runnable() {
			public void run() {

				try {
					// //System.out.println("in second...");
					Thread.sleep(200);
					loadBseStockList();
					// //System.out.println("in second after loading list...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread third = new Thread(new Runnable() {
			public void run() {

				try {
					// //System.out.println("in third...");
					Thread.sleep(200);
					loadNseStockList();
					// //System.out.println("in third after loading list...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		try {
			// //System.out.println("starting first thread...");
			first.start();
			// //System.out.println("starting second thread...");
			second.start();
			// //System.out.println("starting third thread...");
			third.start();

			// //System.out.println("joinig thread...");

			first.join();
			second.join();
			third.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String updateValues() {
		return null;
	}

	public void loadNseStockList() {
		nseStockList = (List<NseTable>) context.getBean("list");

		try {
			nseStockList = (List<NseTable>) service.loadList(" from NseTable");
			for (NseTable nseTable : nseStockList)
				symbolItemList
						.add(new SelectItem(nseTable.getNseTableId(), nseTable
								.getCompanyQuote().trim()
								+ " --- "
								+ nseTable.getCompanyName().trim() + " --- NSE"));

			nseStockHistory = new StringBuffer[nseStockList.size()];
			for (int count = 0; count < nseStockList.size(); count++)
				nseStockHistory[count] = new StringBuffer();

			// for(int i =0;i<nseStockList.size();i++)
			// System.out.println(nseStockList.get(i).getCompanyQuote()+": "+nseStockList.get(i).getCompanyName()+" "+
			// nseStockList.get(i).getSectorType()+" "+nseStockList.get(i).getOpen()+" "+nseStockList.get(i).getPrevClose()
			// +" "+nseStockList.get(i).getHigh()+" "+nseStockList.get(i).getLow()+" "+nseStockList.get(i).getGain());
			/*
			 * niftyOpen = niftyValueList.get(0).getOpen(); niftyPrevClose =
			 * niftyValueList.get(0).getPrevClose(); niftyHigh =
			 * niftyValueList.get(0).getPrevClose(); niftyLow =
			 * niftyValueList.get(0).getLow();
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setNseStop(true);
	}

	public void loadBseStockList() {
		bseStockList = (List<BseTable>) context.getBean("list");

		try {
			bseStockList = (List<BseTable>) service.loadList(" from BseTable");

			for (BseTable bseTable : bseStockList)
				symbolItemList
						.add(new SelectItem(bseTable.getBseTableId(), bseTable
								.getCompanyQuote().trim()
								+ " --- "
								+ bseTable.getCompanyName().trim() + " --- BSE"));

			bseStockHistory = new StringBuffer[bseStockList.size()];
			for (int count = 0; count < bseStockList.size(); count++)
				bseStockHistory[count] = new StringBuffer();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadGeneralValues();
		setBseStop(true);
	}

	private void loadGeneralValues() {
		try {

			generalValuesList = (List<CommonTable>) service
					.loadList(" from CommonTable ");

			// for(int i =0;i<generalValuesList.size();i++)
			// System.out.println(generalValuesList.get(i).getStockQuote()+": "+generalValuesList.get(i).getOpen()+" "+generalValuesList.get(i).getPrevClose()
			// +" "+generalValuesList.get(i).getHigh()+" "+generalValuesList.get(i).getLow()+" "+generalValuesList.get(i).getGain());
			//
			//

			niftyOpen = generalValuesList.get(0).getOpen();
			niftyPrevClose = generalValuesList.get(0).getPrevClose();
			niftyHigh = generalValuesList.get(0).getPrevClose();
			niftyLow = generalValuesList.get(0).getLow();
			sensexOpen = generalValuesList.get(1).getOpen();
			sensexPrevClose = generalValuesList.get(1).getPrevClose();
			sensexHigh = generalValuesList.get(1).getPrevClose();
			sensexLow = generalValuesList.get(1).getLow();
			goldPrevClose = generalValuesList.get(2).getPrevClose();
			// symbolItemList.add(new SelectItem(generalValuesList.get(0)
			// .getStockQuote(), " --- CNXNIFTY --- NSEI"));
			// symbolItemList.add(new SelectItem(generalValuesList.get(1)
			// .getStockQuote(), " --- CNXSENSEX --- BSEN"));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String showMcxChart() {
		header = " MCX";
		return null;
	}

	public void run() {

		Random randomNum = new Random();
		NumberFormat formatter = new DecimalFormat("#0.00");
		double nse = niftyPrevClose;
		double bse = sensexPrevClose;
		int counter = 0;
		try {

			while (true) {

				++counter;
				nse = Double.parseDouble(formatter.format(nse
						+ (randomNum.nextDouble() < 0.50 ? 0.02 : 0.03)));
				performNseStockList(formatter, randomNum, "add");
				// drawNiftyChart(nse);
				this.niftyColorFlag = this.niftyPrevClose < nse ? true : false;
				this.setNiftyHigh(this.niftyHigh < nse ? nse : niftyHigh);
				this.niftyHighColorFlag = this.niftyHigh > nse ? false : true;
				this.setNiftyPrevClose(nse);
				Thread.sleep(4000);
				bse = Double.parseDouble(formatter.format(bse
						+ (randomNum.nextDouble() < 0.50 ? 0.01 : 0.03)));
				performBseStockList(formatter, randomNum, "add");

				// drawSensexChart(bse);
				this.sensexColorFlag = this.sensexPrevClose < bse ? true
						: false;
				this.setSensexPrevClose(bse);
				this.setSensexHigh(this.sensexHigh < bse ? bse : sensexHigh);
				this.sensexHighColorFlag = this.sensexHigh > bse ? false : true;

				if (counter == 6) {
					while (counter > 1) {

						nse = Double
								.parseDouble(formatter.format(nse
										- (randomNum.nextDouble() < 0.50 ? 0.01
												: 0.02)));
						performNseStockList(formatter, randomNum, "sub");

						// drawNiftyChart(nse);
						this.niftyColorFlag = this.niftyPrevClose < nse ? true
								: false;
						this.niftyHighColorFlag = false;
						this.setNiftyPrevClose(nse);
						Thread.sleep(2000);
						bse = Double
								.parseDouble(formatter.format(bse
										- (randomNum.nextDouble() < 0.50 ? 0.01
												: 0.02)));
						performBseStockList(formatter, randomNum, "sub");

						this.sensexColorFlag = this.sensexPrevClose < bse ? true
								: false;
						// drawSensexChart(bse);
						this.setSensexPrevClose(bse);
						this.sensexHighColorFlag = false;
						--counter;

						// Thread.sleep(500);

						// .out.println("in while counter="+counter);nifty
					}
				}

				/*
				 * synchronized (this) { while (suspendFlag) {
				 * .out.println("in while"); wait(); } }
				 */

			}
		} catch (InterruptedException e) {

		}

	}

	private void performNseStockList(NumberFormat formatter, Random randomNum,
			String operation) {

		try (FileOutputStream writeToNseStockFile = new FileOutputStream(
				"nseStock.properties");
				FileOutputStream writeToNseHistoryFile = new FileOutputStream(
						"nseHistory.properties");
				FileInputStream readFromNseHistoryFile = new FileInputStream(
						"nseHistory.properties")) {

			Properties writeNseStockProperty = new Properties();
			Properties readNseHistoryProperty = new Properties();
			Properties writeNseHistoryProperty = new Properties();
			readNseHistoryProperty.load(readFromNseHistoryFile);
			StringBuffer key = new StringBuffer();
			StringBuffer value = new StringBuffer();
			// //System.out.println("inside NSE");
			if (operation.equals("add")) {
				// ("in performNseStockList add");
				for (int count = 0; count < nseStockList.size(); count++) {
					nseStockList.get(count).setPrevClose(
							Double.parseDouble(formatter.format(nseStockList
									.get(count).getPrevClose()
									+ (randomNum.nextDouble() < 0.50 ? 0.02
											: 0.03))));
					commonNseMethod(count, key, value, writeNseStockProperty,
							writeNseHistoryProperty, writeToNseHistoryFile,
							readNseHistoryProperty);

				}
			} else if (operation.equals("sub")) {
				// ("in performNseStockList sub");
				for (int count = 0; count < nseStockList.size(); count++) {
					nseStockList.get(count).setPrevClose(
							Double.parseDouble(formatter.format(nseStockList
									.get(count).getPrevClose()
									- (randomNum.nextDouble() < 0.50 ? 0.01
											: 0.02))));
					commonNseMethod(count, key, value, writeNseStockProperty,
							writeNseHistoryProperty, writeToNseHistoryFile,
							readNseHistoryProperty);

				}

			}

			writeNseStockProperty.store(writeToNseStockFile, null);

			// readNseHistoryProperty.load(readFromNseHistoryFile);
			// //ln("***********NSE***************");
			// Enumeration enuKeys = readNseHistoryProperty.keys();
			// while (enuKeys.hasMoreElements()) {
			// String key1 = (String) enuKeys.nextElement();
			// String value1 = readNseHistoryProperty.getProperty(key1);
			// ln(key1 + ": " + value1);
			// }
			// ln("*****************************");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void commonNseMethod(int count, StringBuffer key,
			StringBuffer value, Properties writeNseStockProperty,
			Properties writeNseHistoryProperty,
			FileOutputStream writeToNseHistoryFile,
			Properties readNseHistoryProperty) throws IOException {
		// //System.out.println("count ="+count);
		key.replace(0, value.length(), nseStockList.get(count)
				.getCompanyQuote().trim());
		value.replace(
				0,
				value.length(),
				Double.toString(nseStockList.get(count).getOpen()).trim()
						+ "\t"
						+ Double.toString(nseStockList.get(count).getHigh())
								.trim()
						+ "\t"
						+ Double.toString(nseStockList.get(count).getLow())
								.trim()
						+ "\t"
						+ Double.toString(
								nseStockList.get(count).getPrevClose()).trim());

		writeNseStockProperty.setProperty(key.toString(), value.toString());

		if (!nsePreviousTime.toString().equals(formatter.format(new Date()))) {

			if (nseFirst) {
				// //System.out.println("in first readNseHistoryProperty.getProperty"+key.toString()+"="
				// +readNseHistoryProperty.getProperty(key.toString()));
				if (readNseHistoryProperty.getProperty(key.toString()) != null)
					nseStockHistory[count].append(readNseHistoryProperty
							.getProperty(key.toString()));

			}

			writeNseHistoryProperty.setProperty(
					key.toString(),
					nseStockHistory[count]
							.append(value.toString().substring(
									Math.max(0, value.length() - 7)))
							.append(";").toString());
			// //System.out.println(key.toString()+"="+nseStockHistory[count].toString());

			if (count == (nseStockList.size() - 1)) {
				nseFirst = false;
				// //System.out.println("writing to nse history...");
				nsePreviousTime.replace(0, formatter.format(new Date())
						.length(), formatter.format(new Date()));
				writeNseHistoryProperty.store(writeToNseHistoryFile, null);
			}
		}

	}

	private void performBseStockList(NumberFormat formatter, Random randomNum,
			String operation) {

		// //System.out.println("in Bse");
		try (FileOutputStream writeToBseStockFile = new FileOutputStream(
				"bseStock.properties");
				FileOutputStream writeToBseHistoryFile = new FileOutputStream(
						"bseHistory.properties");
				FileInputStream readFromBseHistoryFile = new FileInputStream(
						"bseHistory.properties")) {

			Properties writeBseStockProperty = new Properties();
			Properties writeBseHistoryProperty = new Properties();
			Properties readBseHistoryProperty = new Properties();
			StringBuffer key = new StringBuffer();
			StringBuffer value = new StringBuffer();
			// ////System.out.println("in BSE");
			// readBseHistoryProperty.load(readFromBseHistoryFile);
			// ////System.out.println("***********BSE**************");
			// Enumeration enuKeys = readBseHistoryProperty.keys();
			// while (enuKeys.hasMoreElements()) {
			// String key1 = (String) enuKeys.nextElement();
			// String value1 = readBseHistoryProperty.getProperty(key1);
			// ////System.out.println(key1 + ": " + value1);
			// }
			// ////System.out.println("*****************************");

			// for (int count = 0; count < bseStockList.size(); count++) {
			//
			// }
			//

			if (operation.equals("add")) {

				for (int count = 0; count < bseStockList.size(); count++) {

					bseStockList.get(count).setPrevClose(
							Double.parseDouble(formatter.format(bseStockList
									.get(count).getPrevClose()
									+ (randomNum.nextDouble() < 0.50 ? 0.02
											: 0.03))));
					commonBseMethod(count, key, value, writeBseStockProperty,
							writeBseHistoryProperty, writeToBseStockFile,
							writeToBseHistoryFile, readBseHistoryProperty);

				}
			} else if (operation.equals("sub")) {

				for (int count = 0; count < bseStockList.size(); count++) {

					// bseStockHistory[count]
					// .append(readBseHistoryProperty.getProperty(key.toString()));
					//
					bseStockList.get(count).setPrevClose(
							Double.parseDouble(formatter.format(bseStockList
									.get(count).getPrevClose()
									- (randomNum.nextDouble() < 0.50 ? 0.01
											: 0.02))));
					commonBseMethod(count, key, value, writeBseStockProperty,
							writeBseHistoryProperty, writeToBseStockFile,
							writeToBseHistoryFile, readBseHistoryProperty);

				}

			}
			writeBseStockProperty.store(writeToBseStockFile, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void commonBseMethod(int count, StringBuffer key,
			StringBuffer value, Properties writeBseStockProperty,
			Properties writeBseHistoryProperty,
			FileOutputStream writeToBseStockFile,
			FileOutputStream writeToBseHistoryFile,
			Properties readBseHistoryProperty) throws IOException {
		key.replace(0, value.length(), bseStockList.get(count)
				.getCompanyQuote().trim());
		value.replace(
				0,
				value.length(),
				Double.toString(bseStockList.get(count).getOpen()).trim()
						+ "\t"
						+ Double.toString(bseStockList.get(count).getHigh())
								.trim()
						+ "\t"
						+ Double.toString(bseStockList.get(count).getLow())
								.trim()
						+ "\t"
						+ Double.toString(
								bseStockList.get(count).getPrevClose()).trim());

		writeBseStockProperty.setProperty(key.toString(), value.toString());

		if (!bsePreviousTime.toString().equals(formatter.format(new Date()))) {

			if (bseFirst) {

				if (readBseHistoryProperty.getProperty(key.toString()) != null)
					bseStockHistory[count].append(readBseHistoryProperty
							.getProperty(key.toString()));

			}

			writeBseHistoryProperty.setProperty(
					key.toString(),
					bseStockHistory[count]
							.append(value.toString().substring(
									Math.max(0, value.length() - 7)))
							.append(';').toString());
			// //System.out.println(key.toString()+"="+bseStockHistory[count].toString());
			if (count == (bseStockList.size() - 1)) {
				bseFirst = false;
				// //System.out.println("writing to bse history...");
				bsePreviousTime.replace(0, formatter.format(new Date())
						.length(), formatter.format(new Date()));
				writeBseHistoryProperty.store(writeToBseHistoryFile, null);
			}

		}

	}

	public void redirect(String redirectTo) throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect(SecureBankUtil.getContext() + redirectTo);

	}

	public void loadLineChart() {
		barFlag = false;
		lineFlag = true;
		candleStickFlag = false;

	}

	public void loadBarChart() {
		barFlag = true;
		lineFlag = false;
		candleStickFlag = false;

	}

	public void loadCandleStickChart() {
		barFlag = false;
		lineFlag = false;
		candleStickFlag = true;
		createOhlcModel2();
	}

	public void createCategoryModel() {
		sensexCategoryModel = new CartesianChartModel();

		BarChartSeries bar = new BarChartSeries();
		bar.setLabel("Bar");
		bar.set("9.30", 19843.24);
		bar.set("9.35", 19844.24);
		bar.set("9.40", 19844.76);
		bar.set("9.45", 19844.90);
		bar.set("9.50", 19843.21);
		bar.set("9.55", 19842.03);
		bar.set("10.00", 19841.24);
		bar.set("10.30", 19842.15);
		bar.set("11.00", 19844.34);
		bar.set("11.30", 19846.54);
		bar.set("12.00", 19844.76);
		bar.set("12.30", 19843.51);
		bar.set("1.00", 19845.14);
		bar.set("1.30", 19849.24);
		bar.set("2.00", 19857.84);
		bar.set("2.30", 19856.97);
		bar.set("3.00", 19856.81);
		bar.set("3.30", 19856.24);

		sensexCategoryModel.addSeries(bar);

		niftyCategoryModel = new CartesianChartModel();

		LineChartSeries niftyBar = new LineChartSeries();
		niftyBar.setLabel("Line");
		niftyBar.set("9.30", 19843.24);
		niftyBar.set("9.35", 19844.24);
		niftyBar.set("9.40", 19844.76);
		niftyBar.set("9.45", 19844.90);
		niftyBar.set("9.50", 19843.21);
		niftyBar.set("9.55", 19842.03);
		niftyBar.set("10.00", 19841.24);
		niftyBar.set("10.30", 19842.15);
		niftyBar.set("11.00", 19844.34);
		niftyBar.set("11.30", 19846.54);
		niftyBar.set("12.00", 19844.76);
		niftyBar.set("12.30", 19843.51);
		niftyBar.set("1.00", 19845.14);
		niftyBar.set("1.30", 19849.24);
		niftyBar.set("2.00", 19857.84);
		niftyBar.set("2.30", 19856.97);
		niftyBar.set("3.00", 19856.81);
		niftyBar.set("3.30", 19856.24);

		// niftyCategoryModel.addSeries(niftyBar);
		sensexCategoryModel.addSeries(niftyBar);

		setChartStop(true);
	}

	private void createOhlcModel2() {
		ohlcModel = new OhlcChartModel();

		ohlcModel.add(new OhlcChartSeries(09.00, 019843.24, 19842.12, 19842.65,
				19841.20));
		ohlcModel.add(new OhlcChartSeries(09.30, 19841.24, 19843.24, 19842.12,
				19842.65));
		ohlcModel.add(new OhlcChartSeries(10.00, 19842.15, 19841.24, 19843.24,
				19842.12));
		ohlcModel.add(new OhlcChartSeries(10.30, 19844.34, 19842.15, 19841.24,
				19843.24));
		ohlcModel.add(new OhlcChartSeries(11.00, 19846.54, 19844.34, 19842.15,
				19841.24));
		ohlcModel.add(new OhlcChartSeries(11.30, 19844.76, 19846.54, 19844.34,
				19842.15));
		ohlcModel.add(new OhlcChartSeries(12.00, 19843.51, 19844.76, 19846.54,
				19844.34));
		ohlcModel.add(new OhlcChartSeries(12.30, 19845.14, 19843.51, 19844.76,
				19846.54));
		ohlcModel.add(new OhlcChartSeries(01.00, 19849.24, 19845.14, 19843.51,
				19844.76));
		ohlcModel.add(new OhlcChartSeries(01.30, 19857.84, 19849.24, 19845.14,
				19843.51));
		ohlcModel.add(new OhlcChartSeries(02.00, 19856.97, 19857.84, 19849.24,
				19845.14));
		ohlcModel.add(new OhlcChartSeries(02.30, 19856.81, 19856.97, 19857.84,
				19849.24));
		ohlcModel.add(new OhlcChartSeries(03.00, 19856.24, 19856.81, 19856.97,
				19857.84));

		ohlcModel2 = new OhlcChartModel();

		ohlcModel2.add(new OhlcChartSeries(9.30, 5800.22, 5799.25, 5795.23,
				5790.10));
		ohlcModel2.add(new OhlcChartSeries(10.00, 5802.22, 5800.25, 5795.23,
				5790.10));
		ohlcModel2.add(new OhlcChartSeries(10.30, 5804.33, 5802.22, 5800.22,
				5790.10));
		ohlcModel2.add(new OhlcChartSeries(11.00, 5803.22, 5804.33, 5802.22,
				5800.22));
		ohlcModel2.add(new OhlcChartSeries(11.30, 5809.52, 5803.22, 5804.33,
				5802.22));
		ohlcModel2.add(new OhlcChartSeries(12.00, 5822.77, 5809.52, 5803.22,
				5804.33));
		ohlcModel2.add(new OhlcChartSeries(12.30, 5811.66, 5822.77, 5809.52,
				5803.22));
		ohlcModel2.add(new OhlcChartSeries(1.00, 5814.22, 5811.66, 5822.77,
				5809.52));
		ohlcModel2.add(new OhlcChartSeries(1.30, 5834.22, 5814.22, 5811.66,
				5822.77));
		ohlcModel2.add(new OhlcChartSeries(2.00, 5839.44, 5834.22, 5814.22,
				5811.66));
		ohlcModel2.add(new OhlcChartSeries(2.30, 5848.22, 5839.44, 5834.22,
				5814.22));
		ohlcModel2.add(new OhlcChartSeries(3.00, 5865.64, 5848.22, 5839.44,
				5834.22));
		ohlcModel2.add(new OhlcChartSeries(3.30, 5865.12, 5865.64, 5848.22,
				5839.44));

	}
	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}
}
