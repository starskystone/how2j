import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Product;

public class TestHibernate {
	public static void main(String[] args) {
		// insert();
		// select();
		// delete();
		// update();
		//hqlSelect();
		standardSql();
	}

	public static void select() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Product p = (Product) s.get(Product.class, 3);
		System.out.println(p.getName());
		s.close();
		sf.close();
	}

	public static void insert() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		for (int i = 0; i < 10; ++i) {
			Product p = new Product();
			p.setName("iphone" + i);
			p.setPrice((float) 5666.9);
			System.out.println("此时p是瞬时状态");
			s.save(p);
			System.out.println("此时p是持久状态");
		}
		s.getTransaction().commit();
		s.close();
		System.out.println("此时p是脱管状态");
		sf.close();
	}

	public static void delete() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Product p = (Product) s.get(Product.class, 3);
		s.delete(p);
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

	public static void update() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Product p = (Product) s.get(Product.class, 4);
		p.setName("updateIphoneName");
		s.update(p);
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

	/**
	 * 使用HQL
	 */
	public static void hqlSelect() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		String name = "iphone";
		Query q = s.createQuery("from Product p where p.name like ?");
		q.setString(0, "%" + name + "%");
		List<Product> list = q.list();
		for (Product p : list) {
			System.out.println(p.getName());
		}
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

	/**
	 * 使用Criteria进行数据查询
	 */
	public static void criteriaHqlSelect() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		String name = "iphone";
		Criteria c = s.createCriteria(Product.class);
		c.add(Restrictions.ilike("name", "%" + name + "%"));
		List<Product> list = c.list();
		for (Product p : list) {
			System.out.println(p.getName());
		}
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

	/**
	 * 使用标准sql进行查询
	 */
	public static void standardSql() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		String name = "iphone";
		String sql = "select * from product_ p where p.name like '%" + name + "%'";
		Query q = s.createQuery(sql);
		List<Object[]> list = q.list();
		for (Object[] obj : list) {
			for (Object filed : obj) {
				System.out.print(filed + "\t");
			}
		}
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
