package app;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;

import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.entities.Author;
import com.entities.Book;
import com.entities.Category;


public class App {
	public static void main(String[] args) {
		BookDao bookDao = new BookDaoImpl();
		IAuthorDao authDao = new AuthorDaoImpl();
		
		Author a = new Author(new ObjectId(),"Coong","1234123412");
		
		Set<Category> cates = new HashSet<>();
		
		cates.add(new Category(new ObjectId(), "category 1"));

		Book b = new Book(new ObjectId(), "book2", a,cates,2001, 0);
		bookDao.add(b);
	}
}
