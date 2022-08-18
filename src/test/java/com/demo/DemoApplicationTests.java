package com.demo;

import com.demo.jdbc.dao.ChildDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() throws Exception{

		ChildDao dao = new ChildDao();
		UUID uuid = UUID.randomUUID();
		dao.createConnection();
		dao.createStatement("insert into book_with_uuid\n" +
				"values (UUID_TO_BIN(?), ?, \"Joe Mama\", now());",
				new String[] {String.valueOf(uuid), String.valueOf(uuid)});
		dao.executeUpdate();
//		ResultSet results = dao.executeUpdate();
//		Blob id = null;
//		while (results.next()) {
//			id = results.getBlob("id");
//		}
//
//		Assertions.assertThat("".equals(id));
		dao.closeConnection();
	}

}
