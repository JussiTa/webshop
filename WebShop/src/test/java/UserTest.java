 
/*import static org.junit.Assert.*;

import java.io.File;
 




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import fi.webshop.users.dao.UserDao;
import fi.webshop.users.model.User;
 

public class UserTest {
     
    private static Configuration config;
        private static SessionFactory factory;
    private static Session hibernateSession;
     
     
    @SuppressWarnings("deprecation")
	@BeforeClass
    public void init() {
        config = new AnnotationConfiguration();
        config.configure(new File("spring-database.xml"));
            factory = config.buildSessionFactory();
            hibernateSession = factory.openSession();
        }
     
    @Test
    public void insertEmployee(){
    	UserDao userdao = null;
        String username = "jussi";
        String password = "India";
        boolean enabled = true;
        //Add new employee using the session created by Mysql configuration
        userdao.addNewUser(new User(username,password, enabled));
        User user = userdao.findByUserName(username);
        assertEquals("jussi", user.getUsername());
    }
}*/