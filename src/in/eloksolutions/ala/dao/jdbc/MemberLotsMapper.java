package in.eloksolutions.ala.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import in.eloksolutions.ala.model.Lot;

public class MemberLotsMapper implements RowMapper<Lot> {
	   public Lot mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Lot lot=new Lot();
		   lot.setLotId(rs.getInt("lot_id"));
		   lot.setCode(rs.getString("code"));
		   lot.setSumAmount(rs.getInt("sum_amount"));
		   lot.setNoOfMonths(rs.getInt("no_of_months"));
		   lot.setStatus(rs.getInt("status"));
		 
	      return lot;
	   }
}
