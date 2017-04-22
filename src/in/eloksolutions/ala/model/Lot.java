package in.eloksolutions.ala.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;




/**
 * The persistent class for the lot database table.
 * 
 */
@Entity
@NamedQuery(name="Lot.findAll", query="SELECT l FROM Lot l")
public class Lot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="lot_id")
	private Integer lotId;

	@Column(name="code")
	private String code;

	@Column(name="description")
	private String description;

	@Column(name="no_of_months")
	private Integer noOfMonths;

	@Column(name="status")
	private Integer status;

	@Column(name="sum_amount")
	private Integer sumAmount;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private java.util.Date updatedDate;
	
	@Column(name="type")
	private Integer type;

	@Column(name="commisionper")
	private Integer commisionper;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date" ,updatable = true)
	private Date startDate;
	
	@Transient
	private String statusName;
	
	
	@Column(name="before_lift_amt")
	private Integer beforeLiftAmount;
	
	@Column(name="after_lift_amt")
	private Integer afterLiftAmount;

	public Lot() {
	}

	public Integer getLotId() {
		return this.lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfMonths() {
		return this.noOfMonths;
	}

	public void setNoOfMonths(Integer noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBeforeLiftAmount() {
		return beforeLiftAmount;
	}

	public void setBeforeLiftAmount(Integer beforeLiftAmount) {
		this.beforeLiftAmount = beforeLiftAmount;
	}

	public Integer getAfterLiftAmount() {
		return afterLiftAmount;
	}

	public void setAfterLiftAmount(Integer afterLiftAmount) {
		this.afterLiftAmount = afterLiftAmount;
	}

	public Integer getSumAmount() {
		return this.sumAmount;
	}

	public void setSumAmount(Integer sumAmount) {
		this.sumAmount = sumAmount;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public java.util.Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getCommisionper() {
		return commisionper;
	}

	public void setCommisionper(Integer commisionper) {
		this.commisionper = commisionper;
	}
	
	

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public String toString() {
		return "Lot [lotId=" + lotId + ", code=" + code + ", description="
				+ description + ", noOfMonths=" + noOfMonths + ", status="
				+ status + ", sumAmount=" + sumAmount + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", type=" + type
				+ ", commisionper=" + commisionper + ", startDate=" + startDate
				+ ", statusName=" + statusName + ", beforeLiftAmount="
				+ beforeLiftAmount + ", afterLiftAmount=" + afterLiftAmount
				+ "]";
	}

	

	
}