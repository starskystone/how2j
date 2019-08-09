import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.how2java.dao.CategoryDAO;
import com.how2java.pojo.Category;

public class Test {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		CategoryDAO cd =  (CategoryDAO) app.getBean("dao");
		List<Category> list = cd.find("from Category c");
		System.out.println(list);
		for(Category c: list) {
			System.out.println(c.getName());
		}
	}
}
