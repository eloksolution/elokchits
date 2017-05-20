package in.eloksolutions.ala.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lot_members database table.
 * 
 */
@Entity
@Table(name="lot_members")
@NamedQuery(name="LotMember.findAll", query="SELECT l FROM LotMember l")
public class LotMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="auction_amt",nullable=true )
	private Integer auctionAmt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="auction_date")
	private Date auctionDate;

	private Integer commission;

	@Column(name="docs_provided")
	private String docsProvided;

	@Column(name="lot_id")
	private Integer lotId;

	@Column(name="mem_id")
	private Integer memId;
	
	@Column(name="mem_name")
	private String memName;
	
	@Column(name="ref_id")
	private Integer refId;

	@Column(name="month_lift")
	private Integer monthLift;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="paid_date")
	private Date paidDate;

	@Column(name="paid_to")
	private String paidTo;

	@Column(name="status")
	private Integer status;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	@Column(name="witness_name")
	private String witnessName;

	@Column(name="witness1_name")
	private String witness1Name;

	@Column(name="year_lift")
	private Integer yearLift;

	public LotMember() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	 
	public Integer getAuctionAmt() {
		return this.auctionAmt;
	}

	public void setAuctionAmt(Integer auctionAmt) {
		this.auctionAmt = auctionAmt;
	}

	public Date getAuctionDate() {
		return this.auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}

	public Integer getCommission() {
		return this.commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public String getDocsProvided() {
		return this.docsProvided;
	}

	public void setDocsProvided(String docsProvided) {
		this.docsProvided = docsProvided;
	}

	public Integer getLotId() {
		return this.lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public Integer getMemId() {
		return this.memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public Integer getMonthLift() {
		return this.monthLift;
	}

	public void setMonthLift(Integer monthLift) {
		this.monthLift = monthLift;
	}

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getPaidTo() {
		return this.paidTo;
	}

	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getWitnessName() {
		return this.witnessName;
	}

	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
	}

	public String getWitness1Name() {
		return this.witness1Name;
	}

	public void setWitness1Name(String witness1Name) {
		this.witness1Name = witness1Name;
	}

	public Integer getYearLift() {
		return this.yearLift;
	}

	public void setYearLift(Integer yearLift) {
		this.yearLift = yearLift;
	}

	@Override
	public String toString() {
		return "LotMember [id=" + id + ", auctionAmt=" + auctionAmt
				+ ", auctionDate=" + auctionDate + ", commission=" + commission
				+ ", docsProvided=" + docsProvided + ", lotId=" + lotId
				+ ", memId=" + memId + ", memName=" + memName + ", refId="
				+ refId + ", monthLift=" + monthLift + ", paidDate=" + paidDate
				+ ", paidTo=" + paidTo + ", status=" + status + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", witnessName="
				+ witnessName + ", witness1Name=" + witness1Name
				+ ", yearLift=" + yearLift + "]";
	}

}