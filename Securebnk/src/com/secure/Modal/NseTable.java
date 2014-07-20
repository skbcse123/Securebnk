// default package
// Generated Jun 7, 2014 3:29:52 PM by Hibernate Tools 3.4.0.CR1

package com.secure.Modal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * NseTable generated by hbm2java
 */
@Entity
@Table(name = "nse_table", catalog = "productd_securebank")
public class NseTable implements java.io.Serializable {

	private Long nseTableId;
	private String companyQuote;
	private String companyName;
	private String sectorType;
	private Double open;
	private Double prevClose;
	private Double high;
	private Double low;
	private Double gain;
	private Set<WatchlistNse> watchlistNses = new HashSet<WatchlistNse>(0);
	private Set<UserNseStock> userNseStocks = new HashSet<UserNseStock>(0);

	public NseTable() {
	}

	public NseTable(String companyQuote, String companyName, String sectorType,
			Double open, Double prevClose, Double high, Double low,
			Double gain, Set<WatchlistNse> watchlistNses,
			Set<UserNseStock> userNseStocks) {
		this.companyQuote = companyQuote;
		this.companyName = companyName;
		this.sectorType = sectorType;
		this.open = open;
		this.prevClose = prevClose;
		this.high = high;
		this.low = low;
		this.gain = gain;
		this.watchlistNses = watchlistNses;
		this.userNseStocks = userNseStocks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "nse_table_id", unique = true, nullable = false)
	public Long getNseTableId() {
		return this.nseTableId;
	}

	public void setNseTableId(Long nseTableId) {
		this.nseTableId = nseTableId;
	}

	@Column(name = "company_quote", length = 50)
	public String getCompanyQuote() {
		return this.companyQuote;
	}

	public void setCompanyQuote(String companyQuote) {
		this.companyQuote = companyQuote;
	}

	@Column(name = "company_name", length = 50)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "sector_type", length = 50)
	public String getSectorType() {
		return this.sectorType;
	}

	public void setSectorType(String sectorType) {
		this.sectorType = sectorType;
	}

	@Column(name = "open", precision = 22, scale = 0)
	public Double getOpen() {
		return this.open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	@Column(name = "prev_close", precision = 22, scale = 0)
	public Double getPrevClose() {
		return this.prevClose;
	}

	public void setPrevClose(Double prevClose) {
		this.prevClose = prevClose;
	}

	@Column(name = "high", precision = 22, scale = 0)
	public Double getHigh() {
		return this.high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	@Column(name = "low", precision = 22, scale = 0)
	public Double getLow() {
		return this.low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	@Column(name = "gain", precision = 22, scale = 0)
	public Double getGain() {
		return this.gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nseTable")
	public Set<WatchlistNse> getWatchlistNses() {
		return this.watchlistNses;
	}

	public void setWatchlistNses(Set<WatchlistNse> watchlistNses) {
		this.watchlistNses = watchlistNses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nseTable")
	public Set<UserNseStock> getUserNseStocks() {
		return this.userNseStocks;
	}

	public void setUserNseStocks(Set<UserNseStock> userNseStocks) {
		this.userNseStocks = userNseStocks;
	}

}
