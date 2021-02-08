package com.t3bank.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnector {									//tcubedbanking_db
	private static String url = "jdbc:postgresql://localhost/tcubedbanking_db";
	private static String username = "postgres";
	private static String password = "password";
	private static Connection conn = null;
	private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			try {
				conn = DriverManager.getConnection(url, username, password);
				LOGGER.info("Connected to the PostgreSQL DB!");
			}catch (SQLException e) {
				LOGGER.warn("Connection Failure.");
				//e.printStackTrace();
			}
		}catch (ClassNotFoundException e) {
			LOGGER.warn("Class driver not found.");
		}
		return conn;
	}
	public static void closeConnection(Connection conn ) {
		try {
			conn.close();
			LOGGER.info("DB connection closed");
		} catch (SQLException e) {
			LOGGER.debug("connection not closed, Error in DBConnector.closeConnection()");
			//e.printStackTrace();
		}
	}
	public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
				}
			if (rs != null) {
				rs.close();
				}
			if (ps != null) {
				ps.close();
				}
		} catch (SQLException e) {
			LOGGER.warn("Problem: Error closing db resources. DAOImpl closeResources()");
			//e.printStackTrace();
		}
	}

}