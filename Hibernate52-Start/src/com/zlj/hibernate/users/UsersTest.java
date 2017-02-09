package com.zlj.hibernate.users;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Administrator
 *
 */
public class UsersTest {
	private static Logger logger = Logger.getLogger(UsersTest.class);

	private Session session = null;
	private SessionFactory sessionFactory = null;
	
	/**
	 * 鑾峰彇session
	 * 
	 * @return
	 */
	public Session getSession() {
		// 璇诲彇閰嶇疆鏂囦欢锛屽垱寤簊essionFactory瀵硅薄
		sessionFactory = new Configuration().configure().buildSessionFactory();
		// 鍒涘缓session瀵硅薄
		session = sessionFactory.openSession();
		return session;
	}

	/**
	 * 鍏抽棴session
	 */
	public void closeSession() {
		if (session != null)
			session.close();
		if (sessionFactory != null)
			sessionFactory.close();
	}

	/**
	 * 鎻掑叆鏁版嵁
	 * 
	 * @param users
	 * @return
	 */
	public Long insert(Users users) {
		session = getSession();
		Transaction transaction = session.beginTransaction();// 寮�濮嬩簨鍔�
		Long id = (Long) session.save(users);// 淇濆瓨琛�
		transaction.commit();
		closeSession();
		return id;
	}

	/**
	 * 鏇存柊鏁版嵁
	 * 
	 * @param users
	 */
	public void update(Users users) {
		session = getSession();
		Transaction transaction = session.beginTransaction();// 寮�濮嬩簨鍔�
		session.update(users);
		transaction.commit();
		closeSession();
	}

	/**
	 * 鍒犻櫎琛ㄦ暟鎹�
	 * 
	 * @param users
	 */
	public void delete(Users users) {
		session = getSession();
		Transaction transaction = session.beginTransaction();// 寮�濮嬩簨鍔�
		session.delete(users);
		transaction.commit();
		closeSession();
	}

	/**
	 * 鑾峰彇涓�鏉℃暟鎹�
	 * 
	 * @param id
	 * @return
	 */
	public Users getUsersById(Long id) {
		session = getSession();
		Users users = (Users) session.get(Users.class, id);
		closeSession();
		return users;
	}

	/**
	 * 鑾峰彇鍒楄〃
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Users> getUsersList() {
		session = getSession();
		// List<Users> usersList = (List<Users>)session.createQuery("from
		// Users").list(); 鏃х増鐨勬柟娉曪紒
		List<Users> usersList = (List<Users>) session.createQuery("from Users").getResultList();
		closeSession();
		return usersList;
	}

	public static void main(String[] args) {
		UsersTest test = new UsersTest();
		// Long userId = test.insert(new Users(null,"jack11","jack11"));
		// logger.info(userId);
		// logger.info(test.getUsersById(userId));

		// test.update(new Users(6L,"tom11", "tom11"));

		Users users = new Users();
		users.setId(6L);
		// test.delete(users);

		// logger.info(test.getUsersById(5L));
		List<Users> usersList = test.getUsersList();
		for (Users users1 : usersList) {
			logger.info(users1);
		}
	}
}