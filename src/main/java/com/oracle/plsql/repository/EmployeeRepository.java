package com.oracle.plsql.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.oracle.plsql.model.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Employee> getEmployeesList(Employee employee){
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("TESTDB")
				.withProcedureName("CUSTOMERPROCEDURE")
				.declareParameters(new SqlParameter[] {
						new SqlParameter("empid", Types.INTEGER),
						new SqlOutParameter("c_cursor", Types.REF_CURSOR),
						new SqlOutParameter("o_sqlcode", Types.INTEGER),
						new SqlOutParameter("o_sqlmsg", Types.VARCHAR)
				})
				.returningResultSet("c_cursor", new RowMapper<Employee>() {
					@Override
					public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
						Employee employee1 = new Employee();
						employee1.setEmployeeId(rs.getInt(1));
						employee1.setFirstName(rs.getString(3));
						employee1.setLastName(rs.getString(4));
						employee1.setSalary(rs.getFloat(2));
						return employee1;
					}
				});
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("empid", "1234");
		Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
		List<Employee> employeeList = (List<Employee>) results.get("c_cursor");
		
		return employeeList;

	}
}
