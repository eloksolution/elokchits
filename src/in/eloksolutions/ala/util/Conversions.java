package in.eloksolutions.ala.util;

import java.util.HashMap;
import java.util.Map;


public class Conversions {
	public static  Map<Integer, String> lotStatus=new HashMap<Integer,String>();
	
	static {
		
		//lotStatus
		lotStatus.put(0, "Completed");
		lotStatus.put(1, "Active");
		lotStatus.put(2, "Partial");
		
		//PartnerName
		
			
	
	}//end of static block
	 
	 public static Map<Integer, String> getLotStatus() {
		return lotStatus;
	}
	public static void setLotStatus(Map<Integer, String> lotStatus) {
		Conversions.lotStatus = lotStatus;
	}
}
