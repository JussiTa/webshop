import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordGenerator {

	public static void main(String[] args) {
		// BCrypt encoder = new BCrypt();

		String plain_password = "jussi";

		String candidate_password = "jussi";

		String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt(10));

		/**
		 * INSERT INTO users(username,password,enabled) VALUES (
		 * 'admin','$2a$10$eYg/Ke78z6gv9JFwLbSb7u9lk3u20MsCh8NgkNN215LXHDUIluYWS',
		 * TRUE); INSERT INTO users(username,password,enabled) VALUES (
		 * 'alex','$2a$10$eYg/Ke78z6gv9JFwLbSb7u9lk3u20MsCh8NgkNN215LXHDUIluYWS',
		 * TRUE);
		 * 
		 * 
		 * INSERT INTO user_roles (username, ROLE) VALUES ('jussi',
		 * 'ROLE_USER'); INSERT INTO user_roles (username, ROLE) VALUES
		 * ('mkyong', 'ROLE_ADMIN'); INSERT INTO user_roles (username, ROLE)
		 * VALUES ('alex', 'ROLE_USER');
		 * 
		 * 
		 */

		// String stored_hash = null;
		if (BCrypt.checkpw(candidate_password, pw_hash))
			System.out.println("It matches");
		else
			System.out.println("It does not match");
	}

}