package dao;

import java.util.ArrayList;
import java.util.List;

import beans.User;

public class ResultDAO {
	private List<User> usersResult = new ArrayList<>();
	
	public ResultDAO() {
		
	}
	
	public ResultDAO(List<User> usersResult) {
		this.usersResult = usersResult;
	}

	public List<User> getUsersResult(){
		return usersResult;
	}
	public void setUsersResult(List<User> result) {
		this.usersResult = result;
	}
}
