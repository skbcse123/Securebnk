// default package
// Generated Jun 7, 2014 3:29:52 PM by Hibernate Tools 3.4.0.CR1

package com.secure.Modal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * WatchlistNse generated by hbm2java
 */
@Entity
@Table(name = "watchlist_nse", catalog = "productd_securebank")
public class WatchlistNse implements java.io.Serializable {

	private Long watchlistNseId;
	private CustomerTable customerTable;
	private NseTable nseTable;
	private Double alertAbove;
	private Double alertBelow;

	public WatchlistNse() {
	}

	public WatchlistNse(CustomerTable customerTable, NseTable nseTable,
			Double alertAbove, Double alertBelow) {
		this.customerTable = customerTable;
		this.nseTable = nseTable;
		this.alertAbove = alertAbove;
		this.alertBelow = alertBelow;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "watchlist_nse_id", unique = true, nullable = false)
	public Long getWatchlistNseId() {
		return this.watchlistNseId;
	}

	public void setWatchlistNseId(Long watchlistNseId) {
		this.watchlistNseId = watchlistNseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_table_id")
	public CustomerTable getCustomerTable() {
		return this.customerTable;
	}

	public void setCustomerTable(CustomerTable customerTable) {
		this.customerTable = customerTable;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nse_table_id")
	public NseTable getNseTable() {
		return this.nseTable;
	}

	public void setNseTable(NseTable nseTable) {
		this.nseTable = nseTable;
	}

	@Column(name = "alert_above", precision = 22, scale = 0)
	public Double getAlertAbove() {
		return this.alertAbove;
	}

	public void setAlertAbove(Double alertAbove) {
		this.alertAbove = alertAbove;
	}

	@Column(name = "alert_below", precision = 22, scale = 0)
	public Double getAlertBelow() {
		return this.alertBelow;
	}

	public void setAlertBelow(Double alertBelow) {
		this.alertBelow = alertBelow;
	}

}