package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.AdressBook;
import model.Seach;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressDAOTest {
	int age, id;
	String name;
	String address;
	AddressDAO dao;
	AdressBook adressBook;
	Seach seach;

	@BeforeEach
	void setUp() throws Exception {
		dao = new AddressDAO();
		adressBook = new AdressBook();
		seach = new Seach();
	}

	@Order(1)
	@Test
	void testDriver() {
		try {
			dao.driver();
		} catch (Exception e) {
			e.printStackTrace();
			fail("例外検知");
		}
	}

	@Order(2)
	@Test
	void testFindAll() {
		List<AdressBook> AdressBookList = dao.findAll();
		assertNotNull(AdressBookList.size());
		assertEquals(3, AdressBookList.size());
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(i + 1, AdressBookList.get(i).getId());
			assertEquals("山田　花子", AdressBookList.get(0).getName());
			assertEquals(47, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());

			assertEquals("大阪　太郎", AdressBookList.get(1).getName());
			assertEquals(71, AdressBookList.get(1).getAge());
			assertEquals("大阪府大阪市中央区道頓堀１丁目８?２５", AdressBookList.get(1).getAddress());

			assertEquals("唐木　崇行", AdressBookList.get(2).getName());
			assertEquals(60, AdressBookList.get(2).getAge());
			assertEquals("大阪府茨木市□□町4-5-6", AdressBookList.get(2).getAddress());

		}
	}

//	@Test
//	void testFindAll_例外１() {
	// この例外はsql文が異常な時のみ有効
//		try {
//		List<AdressBook> AdressBookList = dao.findAll();
//		assertNull(AdressBookList);
//		assertNull(AdressBookList.size());
//		}catch(Exception e){
//			if(e instanceof SQLException) {
//				
//			}else {
//				e.printStackTrace();
//				fail("sqlエラーでない");
//			}
//		}
//	}

	@Order(3)
	@Test
	void testFindByAdress_1() {
		seach = new Seach("id", "1");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(1, AdressBookList.size());
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(i + 1, AdressBookList.get(i).getId());
			assertEquals("山田　花子", AdressBookList.get(0).getName());
			assertEquals(47, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());
		}

	}

	@Order(4)
	@Test
	void testFindByAdress_例外1() {
		try {
			seach = new Seach("id", "a");
			List<Seach> AdressBookList = dao.findByAdress(seach);
			assertNotNull(AdressBookList.size());
			assertEquals(0, AdressBookList.size());
			for (int i = 0; i < AdressBookList.size(); i++) {
				assertEquals(0, AdressBookList.get(i).getId());
				assertEquals(null, AdressBookList.get(0).getName());
				assertEquals(-1, AdressBookList.get(0).getAge());
				assertEquals("error id データベースへのアクセスでエラーが発生しました", AdressBookList.get(0).getAddress());

			}
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}

	}

	@Order(5)
	@Test
	void testFindByAdress_2() {
		seach = new Seach("name", "山田　花子");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(1, AdressBookList.size());
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(i + 1, AdressBookList.get(i).getId());
			assertEquals("山田　花子", AdressBookList.get(0).getName());
			assertEquals(47, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());

		}
	}

	@Order(6)
	@Test
	void testFindByAdress_例外2() {
		try {
			seach = new Seach("name", "abcd efgh");
			List<Seach> AdressBookList = dao.findByAdress(seach);
			assertNotNull(AdressBookList.size());
			assertEquals(1, AdressBookList.size());
			for (int i = 0; i < AdressBookList.size(); i++) {
				assertEquals(0, AdressBookList.get(i).getId());
				assertEquals(null, AdressBookList.get(0).getName());
				assertEquals(-1, AdressBookList.get(0).getAge());
				assertEquals("error name 全角で入力してください", AdressBookList.get(0).getAddress());
			}
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}

	}

	@Order(7)
	@Test
	void testFindByAdress_name例外() {
		try {
			seach = new Seach("name", "徳川　家康竹千代");
			List<Seach> AdressBookList = dao.findByAdress(seach);
			assertNotNull(AdressBookList.size());
			assertEquals(0, AdressBookList.size());
			for (int i = 0; i < AdressBookList.size(); i++) {
				assertEquals(0, AdressBookList.get(i).getId());
				assertEquals(null, AdressBookList.get(0).getName());
				assertEquals(-1, AdressBookList.get(0).getAge());
				assertEquals("error name データベースへのアクセスでエラーが発生しました", AdressBookList.get(0).getAddress());

			}
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}

	}

	@Order(8)
	@Test
	void testFindByAdress_3() {
		seach = new Seach("age", "1");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(0, AdressBookList.size());
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(i + 1, AdressBookList.get(i).getId());
			assertEquals("大阪　太郎", AdressBookList.get(0).getName());
			assertEquals(71, AdressBookList.get(0).getAge());
			assertEquals("大阪府大阪市中央区道頓堀１丁目８?２５", AdressBookList.get(0).getAddress());
		}
	}

	@Order(9)
	@Test
	void testFindByAdress_例外3() {
		try {
			seach = new Seach("age", "a");
			List<Seach> AdressBookList = dao.findByAdress(seach);
			assertNotNull(AdressBookList.size());
			assertEquals(0, AdressBookList.size());
			for (int i = 0; i < AdressBookList.size(); i++) {
				assertEquals(0, AdressBookList.get(i).getId());
				assertEquals(null, AdressBookList.get(0).getName());
				assertEquals(-1, AdressBookList.get(0).getAge());
				assertEquals("error age データベースへのアクセスでエラーが発生しました", AdressBookList.get(0).getAddress());
			}
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}

	}

	@Order(10)
	@Test
	void testFindByAdress_4() {
		seach = new Seach("address", "大阪府堺市北区");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(1, AdressBookList.size());
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(i + 1, AdressBookList.get(i).getId());
			assertEquals("山田　花子", AdressBookList.get(0).getName());
			assertEquals(47, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());
		}
	}

	@Order(11)
	@Test
	void testFindByAdress_例外4() {
		try {
			seach = new Seach("", null);
			List<Seach> AdressBookList = dao.findByAdress(seach);
			assertNotNull(AdressBookList.size());
			assertEquals(0, AdressBookList.size());
			for (int i = 0; i < AdressBookList.size(); i++) {
				assertEquals(0, AdressBookList.get(i).getId());
				assertEquals(null, AdressBookList.get(0).getName());
				assertEquals(-1, AdressBookList.get(0).getAge());
				assertEquals("error address データベースへのアクセスでエラーが発生しました", AdressBookList.get(0).getAddress());
			}
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}

	}

	@Order(11)
	@Test
	void testCreate() {
		adressBook = new AdressBook("阿部　一郎", 21, "堺市");
		boolean bo = dao.create(adressBook);
		assertTrue(bo);
		// 確認用
		seach = new Seach("id", "4");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(1, AdressBookList.size());
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(4, AdressBookList.get(i).getId());
			assertEquals("阿部　一郎", AdressBookList.get(0).getName());
			assertEquals(21, AdressBookList.get(0).getAge());
			assertEquals("堺市", AdressBookList.get(0).getAddress());
		}

	}

	@Order(12)
	@Test
	void testCreate_例外() {
		try {
			adressBook = new AdressBook("abcdefghi", 99, "堺市");
			boolean bo = dao.create(adressBook);
			assertFalse(bo);
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}
	}

	@Order(13)
	@Test
	void testRemove() {
		adressBook = new AdressBook("id", "4");
		boolean bo = dao.remove(adressBook);
		assertTrue(bo);
		// 確認用
		seach = new Seach("id", "4");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(0, AdressBookList.size());

	}

	@Order(14)
	@Test
	void testRemove_例外() {
		try {
			adressBook = new AdressBook("id", "aa");
			boolean bo = dao.remove(adressBook);
			assertFalse(bo);
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}
	}

	@Order(15)
	@Test
	void testUpdate1() {
		adressBook = new AdressBook("2", "6", "id");
		boolean bo = dao.update(adressBook);
		assertTrue(bo);
		// 確認用
		seach = new Seach("id", "6");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(6, AdressBookList.get(0).getId());
			assertEquals("大阪　太郎", AdressBookList.get(0).getName());
			assertEquals(71, AdressBookList.get(0).getAge());
			assertEquals("大阪府大阪市中央区道頓堀１丁目８?２５", AdressBookList.get(0).getAddress());
		}
	}

	@Order(16)
	@Test
	void testUpdate_失敗1() {
		adressBook = new AdressBook("10", "10", "id");
		boolean bo = dao.update(adressBook);
		assertFalse(bo);
		// 確認用
		seach = new Seach("id", "10");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		assertNotNull(AdressBookList.size());
		assertEquals(0, AdressBookList.size());
	}

	@Order(17)
	@Test
	void testUpdate_例外1() {
		try {
			adressBook = new AdressBook(null, "10", "id");
			boolean bo = dao.update(adressBook);
			assertFalse(bo);
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}
	}

	@Order(18)
	@Test
	void testUpdate2() {
		adressBook = new AdressBook("山田　花子", "佐藤　花子", "name");
		adressBook.setId(1);
		boolean bo = dao.update(adressBook);
		assertTrue(bo);
		// 確認用
		seach = new Seach("id", "1");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(1, AdressBookList.get(0).getId());
			assertEquals("佐藤　花子", AdressBookList.get(0).getName());
			assertEquals(47, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());
		}

	}

	@Order(19)
	@Test
	void testUpdate_失敗2() {
		try {
			adressBook = new AdressBook("佐藤　花子", "羽柴　秀吉（豊臣）", "name");
			adressBook.setId(1);
			boolean bo = dao.update(adressBook);
			assertFalse(bo);
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}
	}

	@Order(20)
	@Test
	void testUpdate_例外2() {
		adressBook = new AdressBook("", "10", "name");
		adressBook.setId(1);
		boolean bo = dao.update(adressBook);
		assertFalse(bo);
	}

	@Order(21)
	@Test
	void testUpdate3() {
		adressBook = new AdressBook("47", "48", "age");
		adressBook.setId(1);
		boolean bo = dao.update(adressBook);
		assertTrue(bo);
		// 確認用
		seach = new Seach("age", "47");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(1, AdressBookList.get(0).getId());
			assertEquals("佐藤　花子", AdressBookList.get(0).getName());
			assertEquals(48, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市北区○○町1-2-", AdressBookList.get(0).getAddress());
		}

	}

	@Order(22)
	@Test
	void testUpdate_例外3() {
		adressBook = new AdressBook("48", "a", "age");
		adressBook.setId(1);
		boolean bo = dao.update(adressBook);
		assertFalse(bo);
	}

	@Order(23)
	@Test
	void testUpdate4() {
		adressBook = new AdressBook("大阪府堺市北区○○町1-2-", "大阪府堺市南区○○町1-2-", "address");
		adressBook.setId(1);
		boolean bo = dao.update(adressBook);
		assertTrue(bo);
		// 確認用
		seach = new Seach("age", "47");
		List<Seach> AdressBookList = dao.findByAdress(seach);
		for (int i = 0; i < AdressBookList.size(); i++) {
			assertEquals(1, AdressBookList.get(0).getId());
			assertEquals("佐藤　花子", AdressBookList.get(0).getName());
			assertEquals(48, AdressBookList.get(0).getAge());
			assertEquals("大阪府堺市南区○○町1-2-", AdressBookList.get(0).getAddress());
		}

	}

	@Order(24)
	@Test
	void testUpdate_例外4() {
		try {
			adressBook = new AdressBook("", null, "");
			adressBook.setId(1);
			boolean bo = dao.update(adressBook);
			assertFalse(bo);
		} catch (Exception e) {
			if (e instanceof SQLException) {

			} else {
				e.printStackTrace();
				fail("sqlエラーでない");
			}
		}
	}

}
