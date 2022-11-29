package app;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.entities.Author;
import com.entities.Book;
import org.bson.types.ObjectId;

public class App {
	public static void main(String[] args) {
		Author a = new Author(2,"butle","123443");
		Book b = new Book("sach1",a,2001,200);
		
		BookDao bookDao = new BookDaoImpl();
		bookDao.addBook(b);
			
	}
}
