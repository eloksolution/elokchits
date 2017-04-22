package in.eloksolutions.ala.beans;

import java.util.Date;

public class MemberBillVO {
	public Integer lotId;
	public String lotCode;
	public Integer lotType;
	public Integer memId;
	public Integer sumAmount;
	public Integer auctionAmt;
	public Integer totalMon;
	public Integer runMon;
	public Integer balMon;
	public Integer premium;
	public Integer  commission;
	public Integer payAmt;
	public String name;
	public Date startDate;
	public Integer beforeLiftAmount;
	public Integer afterLiftAmount;
	

	@Override
	public String toString() {
		return "MemberBillVO [lotId=" + lotId + ", lotCode=" + lotCode
				+ ", lotType=" + lotType + ", memId=" + memId + ", sumAmount="
				+ sumAmount + ", auctionAmt=" + auctionAmt + ", totalMon="
				+ totalMon + ", runMon=" + runMon + ", balMon=" + balMon
				+ ", premium=" + premium + ", commission=" + commission
				+ ", payAmt=" + payAmt + ", name=" + name + ", startDate="
				+ startDate + ", beforeLiftAmount=" + beforeLiftAmount
				+ ", afterLiftAmount=" + afterLiftAmount + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberBillVO other = (MemberBillVO) obj;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		return true;
	}
	
	

}
