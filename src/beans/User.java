package beans;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String role;
	
	public User() {
	}

	public User(String firstName, String lastName, String email, String username, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String toString() {
		return firstName+";"+lastName+";"+email+";"+username+";"+password+";"+role;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || (getClass() != obj.getClass())) {
			return false;
		}
		User user = (User) obj; 
		if(!user.getUsername().toLowerCase().equals(this.username.toLowerCase())){
			return false;
		}
		return true;
	}
}
