package in.eloksolutions.ala.dao.jdbc;

import in.eloksolutions.ala.model.LotMember;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LotMemberMapper implements RowMapper<LotMember> {
	
   public LotMember mapRow(ResultSet rs, int rowNum) throws SQLException {
	   LotMember lotMember = new LotMember();
	   lotMember.setId(rs.getInt("id"));
	   lotMember.setLotId(rs.getInt("lot_id"));
	   lotMember.setMemId(rs.getInt("mem_id"));
	   lotMember.setMonthLift(rs.getInt("month_lift"));
	   lotMember.setYearLift(rs.getInt("year_lift"));
	   lotMember.setAuctionAmt(rs.getInt("auction_amt"));
	   lotMember.setAuctionDate(rs.getDate("auction_date"));
	   lotMember.setPaidDate(rs.getDate("paid_date"));
	   lotMember.setPaidTo(rs.getString("paid_to"));
	   lotMember.setWitness1Name(rs.getString("witness_name"));
	   lotMember.setWitness1Name(rs.getString("witness1_name"));
	   lotMember.setDocsProvided(rs.getString("docs_provided"));
	   lotMember.setUpdatedBy(rs.getInt("updated_by"));
	   lotMember.setUpdatedDate(rs.getDate("updated_date"));
	   lotMember.setStatus(rs.getInt("status"));
	   lotMember.setCommission(rs.getInt("commission"));
	   lotMember.setMemName(rs.getString("mem_name"));
	   lotMember.setRefId(rs.getInt("ref_id"));
      return lotMember;
   }             
   
}