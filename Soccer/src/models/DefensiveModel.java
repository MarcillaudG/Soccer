package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import ui.ResWindow;

public class DefensiveModel {

	private Connection conn;

	private Map<String,Double> scores;

	private ResWindow resWindow;

	public DefensiveModel() {
		scores = new HashMap<String,Double>();
		try {
			// db parameters
			String url = "jdbc:sqlite:C:\\Users\\Guilhem Marcillaud\\Travail\\Travail\\M2\\ChefDoeuvre\\database.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	public Double choiceToWeight(String choice) {
		Double res = 0.0;

		if(choice.equals("High"))
			return 2.0;
		if(choice.equals("Medium"))
			return 1.0;
		if(choice.equals("Low"))
			return 0.5;
		if(choice.equals("Right"))
			return -5.0;
		if(choice.equals("Left"))
			return -6.0;
		else
			return 0.0;
	}

	public Double calculateScore(HashMap<String, Double> map,List<Double> listD) {
		Double res = 0.0;
		res = selectAll(map,listD);
		return res;
	}

	public Double selectAll(HashMap<String, Double> map,List<Double> listD){
		Map<String,Double> resMap = new HashMap<String,Double>();
		Double res = 0.0;
		String resPlayer = "";
		String sql = "SELECT id,player_name FROM Player";
		String sql2 = "SELECT id,potential,long_passing,marking,interceptions,positioning,standing_tackle,sliding,tackle,short_passing,defensive_work_rate";
		boolean first = true;
		sql2 += " FROM Player_Attributes Where id = ";
		List<Integer> ids = new ArrayList<Integer>();
		List<String> names = new ArrayList<String>();
		try (
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				ids.add(rs.getInt("id"));
				names.add(rs.getString("player_name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		String sqltmp = sql2;
		int ind = 0;
		for(int i : ids) {
			String player = names.get(ind);
			ind++;
			Double score = 0.0;
			sqltmp += i;
			try (
					Statement stmt  = conn.createStatement();
					ResultSet rs    = stmt.executeQuery(sqltmp)){
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();  

				// loop through the result set
				while (rs.next()) {
					System.out.println(rs.getInt("id"));
					for(String s : map.keySet()) {
						if(s.equals("defensive_work_rate") && rs.getString(s) != null){
							double value = 0.0;
							if(rs.getString(s).equals("high"))
								value = 50.0;
							else
								if(rs.getString(s).equals("medium"))
									value = 25.0;
								else
									value = 10.0;
							score += value * map.get(s);
						}
						else {
							score+= rs.getInt(s)*map.get(s);
						}
					}
					if(score > res) {
						res = score;
						resPlayer = player;
					}
					resMap.put(player, score);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			sqltmp = sql2;
		}
		resWindow();
		this.resWindow.calculate(resMap);
		return res;
	}

	public void resWindow() {
		if(this.resWindow == null) {
			this.resWindow = new ResWindow();
			this.resWindow.setVisible(true);
		}
	}


}
