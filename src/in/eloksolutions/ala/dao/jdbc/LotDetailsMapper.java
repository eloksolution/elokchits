package in.eloksolutions.ala.dao.jdbc;

import in.eloksolutions.ala.beans.LotDetailsVO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LotDetailsMapper implements RowMapper<LotDetailsVO> {
   public LotDetailsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	   LotDetailsVO lotDetails = new LotDetailsVO();
	   lotDetails.firstName=rs.getString("mem_name");
	   lotDetails.lastName="";
	   lotDetails.lot.setLotId(rs.getInt("lot_id"));
	   lotDetails.lot.setCode(rs.getString("code"));
	   lotDetails.lot.setSumAmount(rs.getInt("sum_amount"));
	   lotDetails.lot.setCommisionper(rs.getInt("commisionper"));
	   lotDetails.lot.setNoOfMonths(rs.getInt("no_of_months"));
	   lotDetails.lot.setType(rs.getInt("type"));
	   lotDetails.lotMember.setId(rs.getInt("id"));
	   lotDetails.lotMember.setMemId(rs.getInt("mem_id"));
	   lotDetails.lotMember.setAuctionAmt(rs.getInt("auction_amt"));
	   lotDetails.lotMember.setAuctionDate(rs.getDate("auction_date"));
	   lotDetails.lotMember.setMemName(rs.getString("mem_name"));
      return lotDetails;
   }
}