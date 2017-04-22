package in.eloksolutions.ala.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utils {
	protected static final Log logger = LogFactory.getLog(Utils.class);
	
	
	public static boolean isNull(String s){
		return (s==null || s.trim().length()==0);
	}
	
	public static String trim(String s){
		if (s==null || s.trim().length()==0) return "";
		return s.trim();
	}
	
	public static String strVal(Object s){
		System.out.println("**************utils null"+s);
		return (s==null)?"":s.toString();
	}
	public static String strValue(Object s){
		System.out.println("**************utils454545 null"+s);
		if(s==null || s.equals("null"))
			return " ";
		else
		 return (s==null)?"":s.toString();
	}
	public static int setIntValue(String val) {
		if(val==null || val.trim().length()==0) return 0;
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Date parseDate(String cld) {
		if(cld==null || cld.trim().length()==0)return null;
		System.out.println("from date "+cld);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return formatter.parse(cld);
		} catch (ParseException e) {
			logger.info("Error while parsing date",e);
		}
		return null;
	}
	

	public static Date parseDateTime(String cld) {
		if(cld==null || cld.trim().length()==0)return null;
		System.out.println("from date "+cld);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			return formatter.parse(cld);
		} catch (ParseException e) {
			logger.info("Error while parsing date",e);
		}
		return null;
	}
	
	public static Date parseDateSlash(String cld) {
		if(cld==null || cld.trim().length()==0)return null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse(cld);
		} catch (ParseException e) {
			logger.info("Error while parsing date",e);
		}
		return null;
	}
//
	
	public static String  formatDateTime(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		return formatter.format(dat);
	}
	
	
	
	//
	public static String  formatDate(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		return formatter.format(dat);
	}
	
	public static String  formatDateYearMon(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("MMM-yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		return formatter.format(dat);
	}
	
	public static String  formatDateHome(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		return format.format(dat);
	}
	public static String  formatDateSlash(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		return formatter.format(dat);
	}
	public static String  formatDateMYSQL(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		return formatter.format(dat);
	}
}
