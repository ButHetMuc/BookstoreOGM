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
import com.dao.IPublisherDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BillDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.dao.impl.PublisherDaoImpl;
import com.entities.Author;
import com.entities.Bill;
import com.entities.BillDetails;
import com.entities.Book;
import com.entities.Category;
import com.entities.Customer;

import com.entities.Publisher;
import com.utils.Constants;

public class App {
	public static void main(String[] args) throws RemoteException {
		BookDao bookDao = new BookDaoImpl();
		IAuthorDao authDao = new AuthorDaoImpl();
		IPublisherDao publisherDao = new PublisherDaoImpl();
		ICategoriesDao cateDao = new CategoryDaoImpl();
		BillDao billDao = new BillDaoImpl();
		


		Author a = new Author(new ObjectId(), "cong", "0868283916");
		authDao.add(a);
		
		
		Publisher p = new Publisher(new ObjectId(), "publisher 1", "095848333", "Ha Giang");
		publisherDao.add(p);
		
		Set<Category> cates = new HashSet<Category>();
		Category category1 = new Category(new ObjectId(), "Romantic");
		Category category2 = new Category(new ObjectId(), "Remote");
		cateDao.add(category2);
		cateDao.add(category1);

		cates.add(category1);
		cates.add(category2);

		Book b = new Book(new ObjectId(), "book_name", a, cates,p, 2001, 1000, 4);
		bookDao.add(b);
		
		
//		List<Book> list = bookDao.getAllBook();
//		System.out.println(list.toString());
//		System.out.println(list.size());
//		Book i = list.get(list.size() - 1);
		
		// test remove book
//		bookDao.delete(new ObjectId("6386f4d2505238452cb5c57f"));
		

//		Set<Category> cates2 = i.getCaterogies();
//		System.out.println(cates2.toString());
//		System.out.println(cates2.size());
//		
		// test category
//		List<Category> list =  cateDao.getAll();
//		System.out.println(list.size());
		
//		Customer cus = new Customer(new ObjectId(), "butle", "123456789");
//		BillDetails detail1 = new BillDetails(new ObjectId(),b,2 );
//		BillDetails detail2 = new BillDetails(new ObjectId(),b,2);
//		List<BillDetails> setDetails = new ArrayList<BillDetails>();
//		setDetails.add(detail1);
//		setDetails.add(detail2);
//		Bill bill = new Bill(new ObjectId(), new Date(), 100, cus ,setDetails);
//		billDao.add(bill);
		
//		List<Bill> bills = billDao.findByCustomerName("but");
//		System.out.println(bills.size());
//		List<Bill> bills1 = billDao.findByCustomerPhonenumber("1234");
//		System.out.println(bills1.size());
//		System.out.println(billDao.getAllBills().size());
//		List<Customer> cuss = billDao.getCustomers();
//		for(Customer c: cuss) {
//			System.out.println(c.toString());
////		}
//		System.out.println(publisherDao.findByName("publis").toString());
//		System.out.println(publisherDao.findBySdt("095").toString());


	}
}
