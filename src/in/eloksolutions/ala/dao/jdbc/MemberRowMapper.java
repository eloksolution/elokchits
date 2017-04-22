package in.eloksolutions.ala.dao.jdbc;

import in.eloksolutions.ala.beans.MemberVO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<MemberVO> {
	   public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   MemberVO mem=new MemberVO();
		   mem.setMemberId(rs.getInt("mem_id"));
		   mem.setFirstName(rs.getString("first_name"));
		   mem.setLastName(rs.getString("last_name"));
		   mem.setPhone(rs.getString("phone"));
		   mem.setRefId(rs.getInt("ref_id"));
		   mem.setAuctionAmt(rs.getString("auction_amt"));
		   mem.setPaidDate(rs.getString("paid_date"));
		   mem.setLotMemberId(rs.getString("id"));
	      return mem;
	   }

}
