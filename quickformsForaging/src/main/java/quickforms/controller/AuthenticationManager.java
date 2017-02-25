package quickforms.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import quickforms.entity.LookupPair;
import quickforms.entity.MetaField;

public class AuthenticationManager {
	private static SecureRandom random = new SecureRandom();
	public static String getUserFromToken(Database db, String app, String token) throws Exception {
		db.connect(app);
		db.useApp(app);
		ResultSet rs = db.executeSQL("SELECT username from fact_users where token like '"+token+"'");
		String username = null;
		if(rs.next()) {
			username = rs.getString("username");
		}
		db.disconnect();
		return username;
	}

	public static String generateToken(Database database, String app,
			List<List<LookupPair>> rs) throws Exception {
		String whereClause = null;
		for(LookupPair pair : rs.get(0)) {
			if(pair.left.equals("username")) {
				whereClause = "username like '"+pair.right+"'";
			} else if(pair.left.equals("id")) {
				whereClause = "usersKey = "+pair.right;
			}
		}
		String token = new BigInteger(130, random).toString(32);

		database.execute("update fact_users set token = '"+token+"' where "+whereClause);
		rs.get(0).add(new LookupPair("token", token));
		return database.mapToJSON(rs);
	}
	
	public static boolean recaptcha(String publicToken, String requestIp) throws Exception {
		String url = "https://www.google.com/recaptcha/api/siteverify";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String secret = "6LfKlxYUAAAAAOmB9NDBBDAFdLhxX351-LEySVkU";
		String urlParameters = "secret="+secret+"&response="+publicToken+"&remoteip="+requestIp;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		if(response.toString().contains("\"success\": false")) {
			return false;
		}
		in.close();
		return true;
	}
}
