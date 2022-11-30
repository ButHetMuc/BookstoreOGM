package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet; 
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.dao.BillDao;
import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.ICategoriesDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BillDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.entities.Author;
import com.entities.Bill;
import com.entities.BillDetails;
import com.entities.Book;
import com.entities.Category;
import com.entities.Customer;
import com.rmi.interfaces.IBook_dao;
import com.rmi.interfaces.ITestEntityManage;
import com.rmi.remote.BookDao_Remote;
import com.utils.Constants;

public class App {
	public static void main(String[] args) {
		BookDao bookDao = new BookDaoImpl();
		IAuthorDao authDao = new AuthorDaoImpl();
		ICategoriesDao cateDao = new CategoryDaoImpl();
		BillDao billDao = new BillDaoImpl();

		Author a = new Author(new ObjectId(), "butle", "0868283916");

		Set<Category> cates = new HashSet<Category>();

		cates.add(new Category(new ObjectId(), "category 1"));
		cates.add(new Category(new ObjectId(), "category 4"));

		Book b = new Book(new ObjectId(), "book_name", a, cates, 2001, 1000, 4);
		bookDao.add(b);

//		List<Book> list = bookDao.getAllBook();
//		System.out.println(list.size());
//		Book i = list.get(list.size() - 1);

//		Set<Category> cates2 = i.getCaterogies();
//		System.out.println(cates2.toString());
//		System.out.println(cates2.size());
		
		// test category
//		cateDao.add(new Category(new ObjectId(), "cate 1"));
//		List<Category> list =  cateDao.getAll();
//		System.out.println(list.size());
		Customer cus = new Customer(new ObjectId(), "butle", "123456789");
		BillDetails detail1 = new BillDetails(1,b,2, b.getPrice());
		BillDetails detail2 = new BillDetails(2,b,2, b.getPrice());
		Set<BillDetails> setDetails = new HashSet<BillDetails>();
		setDetails.add(detail1);
		setDetails.add(detail2);
		Bill bill = new Bill(new ObjectId(), new Date(), 100, cus ,setDetails);
		billDao.add(bill);
//		

	}
}
