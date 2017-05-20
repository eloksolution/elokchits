package in.eloksolutions.ala.beans;

import in.eloksolutions.ala.model.Lot;
import in.eloksolutions.ala.model.LotMember;

public class LotDetailsVO {
	public Lot lot=new Lot();
	public LotMember lotMember=new LotMember();
	public String firstName;
	public String lastName;
	@Override
	public String toString() {
		return "LotDetailsVO [lot=" + lot + ", lotMember=" + lotMember
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
