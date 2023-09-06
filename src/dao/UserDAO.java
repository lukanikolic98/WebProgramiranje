package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import beans.User;

public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private List<User> usersResult = new ArrayList<>();
	private String contextPath;
	
	public UserDAO() {
	}
	
	public UserDAO(String contextPath) {
		this.contextPath = contextPath;
		loadUsers(contextPath);
	}
	// Find unique user by user name
	public User find(String username) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		return user;
	}
	// Find user used for logging in
	public User find(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	public List<User> searchUsers(String searchTerm, User user) {
		String[] tokens = searchTerm.split(" ");
		usersResult = new ArrayList<>();
		for(User temp : users.values()) {
			for(String term : tokens)
			if(temp.getFirstName().toLowerCase().contains(term.toLowerCase()) || temp.getLastName().toLowerCase().contains(term.toLowerCase()))
				//TODO: dodati proveru da li je u spisku prijatelja
				if(user != null || temp.getPublicStatus() == true) {
					usersResult.add(temp);
				}
		}
		return usersResult;
	}
	public void searchUsers(String firstName, String lastName, String startDate, String endDate, User user) {
		return;
	}
	public void add(User user) {
		users.put(user.getUsername(), user);
	}
	
	public Collection<User> findAll() {
		return users.values();
	}
	public List<User> getResult(){
		return usersResult;
	}
	// Loading users from users.txt file
	private void loadUsers(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String firstName = st.nextToken().trim();
					String lastName = st.nextToken().trim();
					String email = st.nextToken().trim();
					String username = st.nextToken().trim();
					String password = st.nextToken().trim();
					String role = st.nextToken().trim();
					String gender = st.nextToken().trim();
					Boolean publicStatus = Boolean.parseBoolean(st.nextToken().trim());
					users.put(username, new User(firstName, lastName, email, username, password, role, gender, publicStatus));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}

	// Saving users to users.txt file
	public void saveUsers() {
		try {
			FileWriter writer = new FileWriter(this.contextPath + "/users.txt", false);
			for (User user : users.values()) {
					writer.write(user.toString() +"\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
