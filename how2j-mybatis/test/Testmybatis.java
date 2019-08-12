import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Category;

public class Testmybatis {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession sf = ssf.openSession();
		List<Category> cs = sf.selectList("listCategory");
		for(Category c :cs) {
			System.out.println(c.getName());
		}
	}
}
