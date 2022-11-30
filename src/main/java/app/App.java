package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.ICategoriesDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.entities.Author;
import com.entities.Book;
import com.entities.Category;
import com.entities.Publisher;
import com.rmi.interfaces.IBook_dao;
import com.rmi.interfaces.ITestEntityManage;
import com.rmi.remote.BookDao_Remote;
import com.utils.Constants;

public class App {
	public static void main(String[] args) {
		BookDao bookDao = new BookDaoImpl();
		IAuthorDao authDao = new AuthorDaoImpl();
		ICategoriesDao cateDao = new CategoryDaoImpl();
		

		Author a = new Author(new ObjectId(), "cong", "0868283916");
		Publisher p = new Publisher(new ObjectId(), "publisher 1", "095848333", "Ha Giang");

		Set<Category> cates = new HashSet<>();

		cates.add(new Category(new ObjectId(), "category 1"));
		cates.add(new Category(new ObjectId(), "category 4"));

		Book b = new Book(new ObjectId(), "book_name", a, cates,p, 2001, 1000, 4);
		bookDao.add(b);
		
//		List<Book> list = bookDao.getAllBook();
//		System.out.println(list.size());
//		Book i = list.get(list.size() - 1);
		
		// test remove book
//		bookDao.delete(new ObjectId("6386f4d2505238452cb5c57f"));
		

//		Set<Category> cates2 = i.getCaterogies();
//		System.out.println(cates2.toString());
//		System.out.println(cates2.size());
//		
		// test category
//		cateDao.add(new Category(new ObjectId(), "cate 1"));
//		List<Category> list =  cateDao.getAll();
//		System.out.println(list.size());

	}
}
