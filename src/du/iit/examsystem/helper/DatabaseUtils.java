package du.iit.examsystem.helper;

import java.util.List;
import java.util.Properties;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import du.iit.examsystem.*;

public class DatabaseUtils {
	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory = getSessionFactory();
	}
    
	public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost/examsystem");
                settings.put(Environment.USER, "root");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put("hibernate.connection.characterEncoding", "utf8");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
                settings.put(Environment.C3P0_MIN_SIZE, 5);
                settings.put(Environment.C3P0_MAX_SIZE, 200);
                settings.put(Environment.C3P0_TIMEOUT, 300);
                configuration.setProperties(settings);
                
                configuration.addAnnotatedClass(Exam.class);
        		configuration.addAnnotatedClass(MCQ.class);
        		configuration.addAnnotatedClass(Answer.class);
        		configuration.addAnnotatedClass(Result.class);
        		configuration.addAnnotatedClass(User.class);
        		configuration.addAnnotatedClass(Student.class);
        		configuration.addAnnotatedClass(Teacher.class);
        		
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
	
	public static Session  openSession() {
		return sessionFactory.openSession();
	}
	
	public static void save(Object object){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(object);
		tx.commit();
		session.close();
	}
	
	public static void update(Object object){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(object);
		tx.commit();
		session.close();
	}
	
	public static void delete(Object object){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(object);
		tx.commit();
		session.close();
	}
	
	public static List<Object> getList(Class entity) {
		Session session = DatabaseUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Object> query = builder.createQuery(entity);
	    Root<Object> variableRoot = query.from(entity);
	    query.select(variableRoot);
	    query.orderBy(builder.desc(variableRoot.get("ID")));
		
	    List<Object> result = session.createQuery(query).getResultList();
		
		tx.commit();
		session.close();
		return result;
	}
}
