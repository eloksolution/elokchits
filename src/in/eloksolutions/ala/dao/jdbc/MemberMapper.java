package in.eloksolutions.ala.dao.jdbc;

import in.eloksolutions.ala.beans.MemberVO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberMapper implements RowMapper<MemberVO> {
   public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	   MemberVO memberVO = new MemberVO();
	   memberVO.firstName=rs.getString("first_name");
	   memberVO.lastName=rs.getString("last_name");
	   memberVO.lotMemberId=rs.getString("id");
	   memberVO.lotId=rs.getString("lot_id");
	   memberVO.lotCode=rs.getString("code");
	  return memberVO;
   }
}