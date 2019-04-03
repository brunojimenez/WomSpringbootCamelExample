package cl.wom.middleware.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import oracle.jdbc.OracleTypes;

public class DatabaseDAO {

	private final static Logger LOGGER = Logger.getLogger(DatabaseDAO.class.toString());

	// CREATE OR REPLACE PROCEDURE test_sys_refcursor(c OUT SYS_REFCURSOR)
	// IS
	// BEGIN
	// OPEN c FOR SELECT 1 AS DATA_1, 2 AS DATA_2 FROM DUAL;
	// END test_sys_refcursor;

	public void testSysRefcursor() {
		LOGGER.info("testSysRefcursor inicio");

		String sql = "{ CALL TEST_SYS_REFCURSOR(?) }";
		LOGGER.info(sql);

		Connection conn = null;
		CallableStatement call = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			call = conn.prepareCall(sql);
			// call.setString(2, "");
			call.registerOutParameter(1, OracleTypes.CURSOR);
			call.execute();
			rs = (ResultSet) call.getObject(1);

			while (rs.next()) {
				String data1 = rs.getString("DATA_1");
				LOGGER.info("[data1=" + data1 + " ]");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (call != null)
					call.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		LOGGER.info("testSysRefcursor fin");
	}

	public void testQuery() throws ClassNotFoundException, SQLException {
		LOGGER.info("testQuery inicio");

		String sql = "SELECT 1 AS DATA_1, 2 AS DATA_2 FROM DUAL";
		LOGGER.info(sql);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String data1 = rs.getString("DATA_1");
				LOGGER.info("[data1=" + data1 + " ]");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		LOGGER.info("testQuery fin");
	}

}
