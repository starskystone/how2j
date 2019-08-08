import java.util.List;
import java.util.Set;

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
		// hqlSelect();
		// standardSql();
		// manyToOne();
		// propertyLoad();
		// lazyLoad();
		// deleteCascade();
		// sessionOne();
		// sessionTwo();
		// paping();
		// version();
		//c3p0Connecttion();
		annotaionTest();
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

		Product p = (Product) s.get(Product.class, 8);
		// p.setCategory(c);
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
		// System.out.println(c.getProducts());
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
		Category c = (Category) s.get(Category.class, 1);
		System.out.println(c.getName());
		Category c2 = (Category) s.get(Category.class, 1);
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
		Category c1 = (Category) s.get(Category.class, 1);
		System.out.println("log1");
		Category c2 = (Category) s.get(Category.class, 1);
		System.out.println("log2");
		s.getTransaction().commit();
		s.close();
		Session s2 = sf.openSession();
		s2.beginTransaction();
		Category c3 = (Category) s2.get(Category.class, 1);
		System.out.println("log3");
		s2.getTransaction().commit();
		s2.close();
		sf.close();
	}

	/**
	 * 分页
	 */
	public static void paping() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		String name = "iphone";
		Criteria c = s.createCriteria(Product.class);
		c.add(Restrictions.like("name", "%" + name + "%"));
		c.setFirstResult(2);
		c.setMaxResults(5);

		List<Product> p = c.list();
		for (Product p1 : p) {
			System.out.println(p1.getName());
		}
		s.close();
		sf.close();
	}

	/**
	 * 增加一个version字段进行版本控制，这是乐观锁的核心，配置文件中注意： version元素必须紧跟着id后面，否则会出错
	 * 这样写hibernate会抛出异常
	 */
	public static void version() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s1 = sf.openSession();
		Session s2 = sf.openSession();
		s1.beginTransaction();
		s2.beginTransaction();

		Product p1 = (Product) s1.get(Product.class, 1);
		System.out.println("产品原价格为" + p1.getPrice());
		p1.setPrice(p1.getPrice() + 1000);
		Product p2 = (Product) s2.get(Product.class, 1);
		System.out.println("产品原价格为" + p2.getPrice());
		p2.setPrice(p2.getPrice() + 1000);
		s1.update(p1);
		s2.update(p2);
		s1.getTransaction().commit();
		s2.getTransaction().commit();
		Product p = (Product) s1.get(Product.class, 1);

		System.out.println("经过两次价格增加后，价格变为: " + p.getPrice());
		s1.close();
		s2.close();
		sf.close();
	}

	/**
	 * c3p0 数据库连接池
	 */

	public static void c3p0Connecttion() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		s.createQuery("from Category").list();

		s.getTransaction().commit();
		s.close();
		sf.close();
	}

	/**
	 * 使用注解
	 */
	public static void annotaionTest() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Category c = (Category) s.get(Category.class, 1);
		s.getTransaction().commit();
		s.close();
		sf.close();
		Set<Product> ps = c.getProducts();
		for (Product p : ps) {
			System.out.println(p.getName());
		}
	}
}
