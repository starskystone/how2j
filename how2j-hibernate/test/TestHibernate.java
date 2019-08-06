import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate {
	public static void main(String[] args) {
		// insert();
		// select();
		// delete();
		// update();
		//hqlSelect();
		//standardSql();
		//manyToOne();
		//propertyLoad();
		//lazyLoad();
		//deleteCascade();
		//sessionOne();
		sessionTwo();
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
		String sql = "select * from product_ p where p.name like '%"+name+"%'";
		Query q = s.createSQLQuery(sql);
		List<Object[]> list = q.list();
		for (Object[] obj : list) {
			for (Object filed : obj) {
				System.out.println(filed + "\t");
			}
		}
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 测试many-to-one关系
	 */
	public static void manyToOne() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category c = new Category();
		c.setName("c1");
		s.save(c);
		
		Product p = (Product) s.get(Product.class,8);
		p.setCategory(c);
		s.update(p);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * hibernate属性的懒加载，在获取属性后去数据库内获得对象
	 */
	public static void propertyLoad() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p = (Product) s.load(Product.class, 1);
		System.out.println("---------------1");
		System.out.println(p.getName());
		System.out.println("---------------2");
		
		s.getTransaction().commit();
        s.close();
        sf.close();
	}
	
	public static void lazyLoad() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		 
        Session s = sf.openSession();
        s.beginTransaction();
        Category c = (Category) s.get(Category.class, 1);
 
        System.out.println("log1");
        System.out.println(c.getProducts());
        System.out.println("log1");
 
        s.getTransaction().commit();
        s.close();
        sf.close();
	}
	/**
	 * 级联，删除category数据product数据也会被删除
	 */
	public static void deleteCascade() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        Category c = (Category) s.get(Category.class, 3);
        s.delete(c);
        s.getTransaction().commit();
        s.close();
        sf.close();
	}
	
	/**
	 * 1级缓存，默认开启
	 */
	public static void sessionOne() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Category c =(Category) s.get(Category.class, 1);
		System.out.println(c.getName());
		Category c2 =(Category) s.get(Category.class, 1);
		System.out.println(c2.getName());
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	/**
	 * 2级缓存
	 */
	public static void sessionTwo() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Category c1 =(Category) s.get(Category.class, 1);
		System.out.println(c1.getName());
		Category c2 =(Category) s.get(Category.class, 1);
		System.out.println(c2.getName());
		s.getTransaction().commit();
        s.close();
		
		Session s2 = sf.openSession();
		s2.beginTransaction();
		Category c3 = (Category) s2.get(Category.class, 1);
		System.out.println(c3.getName());
		s2.getTransaction().commit();
        s2.close();
	}
} 
