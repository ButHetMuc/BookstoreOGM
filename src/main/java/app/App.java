package app;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.entities.Author;
import com.entities.Book;

public class App {
	public static void main(String[] args) {
		Author a = new Author(1,"butle");
		Book b = new Book(2,"sach",a,2001,200);
		
		BookDao bookDao = new BookDaoImpl();
		bookDao.addBook(b);
			
	}
}
