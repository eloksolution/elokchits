package in.eloksolutions.ala.dao.jdbc;

import in.eloksolutions.ala.beans.MemberBillVO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberBillMapper implements RowMapper<MemberBillVO> {
   public MemberBillVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	   MemberBillVO memberBillVO = new MemberBillVO();
	   memberBillVO.name=rs.getString("first_name")+","+rs.getString("last_name");
	   memberBillVO.lotId=rs.getInt("lot_id");
	   memberBillVO.auctionAmt=rs.getInt("auction_amt");
	   memberBillVO.balMon=rs.getInt("BALMON");
	   memberBillVO.commission=rs.getInt("COMM");
	   memberBillVO.payAmt=rs.getInt("PAYAMT");
	   memberBillVO.premium=rs.getInt("PREMIUM");
	   memberBillVO.runMon=rs.getInt("RUNMON");
	   memberBillVO.sumAmount=rs.getInt("sum_amount");
	   memberBillVO.totalMon=rs.getInt("no_of_months");
	  return memberBillVO;
   }
   
}