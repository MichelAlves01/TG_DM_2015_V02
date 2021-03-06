package delivery.api.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Esta classe é responsavel por estabelecer conexões com o banco de dados
 * @author Michel
 *
 */
public class ConnectionFactory {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
		 
		final String resource = "mybatis-config.xml";
		final Reader reader = Resources.getResourceAsReader(resource);
		
		
		if (sqlSessionFactory == null) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}
		}
		catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		catch (IOException iOException) {
		
		iOException.printStackTrace();
		
		}
		}
		 
		public static SqlSessionFactory getSqlSessionFactory() {
		 
		return sqlSessionFactory;
		}
}

