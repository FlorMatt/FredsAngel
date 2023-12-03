package com.bookstore.database;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BookDAO {
	
	private JFrame frame = new JFrame();
	
	private Connection connection;
		
		public BookDAO() {
			//initialize the connection
			try {				
				this.connection = DatabaseConnection.getConnection();				
			}
			catch (SQLException e) {				
				JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		
	
	
	//search for book by title
	//action 1.1
	public Book getBookByBookTitle(String bookTitle) {		
		Book book = null;		
		String qry = "SELECT * FROM Book WHERE book_title = ?";		
		try( PreparedStatement preaparedStatement = connection.prepareStatement(qry) ){			
			preaparedStatement.setString(1, bookTitle);
			try(ResultSet resultSet = preaparedStatement.executeQuery()){				
				if(resultSet.next()) {					
					book = extractBookFromResultSet(resultSet);					
				}				
			}			
		} catch(SQLException e) {			
			JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);			
		}		
		return book;		
	}
	
	
	//search for book by isbn number
	//action 1.2
	public Book getBookByIsbn(String isbnNumber) {		
		Book book = null;
		String qry = "SELECT * FROM book JOIN author on author.author_id = book.author_id JOIN inventory on inventory.isbn_number = book.isbn_number WHERE book.isbn_number = ?";		
		try(PreparedStatement preaparedStatement = connection.prepareStatement(qry)){			
			preaparedStatement.setString( 1, isbnNumber );
			try( ResultSet resultSet = preaparedStatement.executeQuery() ){				
				if( resultSet.next() ) {					
					book = extractBookFromResultSet(resultSet);					
				}
				else {					
					System.out.println( "Book not found for ISBN: " + isbnNumber );					
				}				
			}			
		} catch(SQLException e) {			
			JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);			
		}		
		//return book;		
	}
	
	
	//search all books
	//action 1.3
	public List<Book> getAllBooks() {		
        List<Book> books = new ArrayList<>();
        String qry = "SELECT * FROM Book JOIN author on author.author_id = book.author_id JOIN inventory on inventory.isbn_number = book.isbn_number";        
        try (PreparedStatement preparedStatement = connection.prepareStatement(qry);        		
             ResultSet resultSet = preparedStatement.executeQuery()) {        	
            while (resultSet.next()) {            	
                Book book = extractBookFromResultSet(resultSet);
                books.add(book);                
            }            
        } catch (SQLException e) {        	
        	JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);            
        }        
        return books;        
    }
	
	
	//add a book
	//action 2
	public void addBook(Book book) {
        String qry = "INSERT INTO Book (isbn_number, book_title, purchase_cost, retail_price, active, genre, author_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(qry) ) {
            preparedStatement.setString( 1, book.getIsbnNumber() );
            preparedStatement.setString( 2, book.getBookTitle() );
            preparedStatement.setDouble( 3, book.getPurchaseCost() );
            preparedStatement.setDouble( 4, book.getRetailPrice() );
            preparedStatement.setBoolean( 5, book.isActive() );
            preparedStatement.setString( 6, book.getGenre() );
            preparedStatement.setInt( 7, book.getAuthor().getAuthorId() );
            preparedStatement.executeUpdate();              
        } catch (SQLException e) {        	
        	JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);            
        }
    }
	
	
	//change active status on the book
	//remove a book when no longer on sale
	//action 3
	public boolean changeActiveStatus(String isbnNumber, boolean newStatus) {
		String qry = "UPDATE Book Set active = ? WHERE isbn_number = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(qry)){
			preparedStatement.setBoolean(1, newStatus);
			preparedStatement.setString(2, isbnNumber);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected > 0) {
				//System.out.println("The status of the book has been updated successfully.");
				return true;
			} else {
				//System.out.println("The status of the book for isbn: " + isbnNumber + " was not updated. \nPlease check the isbn number and try again.");
				return false;
			}
			
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	
	
	
	
	//edit an existing book's data
	//action 6
	public boolean editBook(Book updateBook) {
		String qry = "UPDATE book SET isbn_number = ?, book_title = ?, purchase_cost = ?, retail_price = ?, genre = ?, author_id = ? WHERE isbn_number = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(qry)){
			preparedStatement.setString(1, updateBook.getIsbnNumber());
			preparedStatement.setString(2, updateBook.getBookTitle());
			preparedStatement.setDouble(3, updateBook.getPurchaseCost());
			preparedStatement.setDouble(4, updateBook.getRetailPrice());
			preparedStatement.setString(5, updateBook.getGenre());
			preparedStatement.setInt(6, updateBook.getAuthor().getAuthorId());
			preparedStatement.setString(7, updateBook.getIsbnNumber());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected > 0) {
				System.out.println("The book was successfully updated!");
				return true;
			} else {
				System.out.println("The book for isbn: " + updateBook.getIsbnNumber() + " was not updated.");
				return false;
			}
			
		} catch(SQLException e){
			JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	
	
	
	
	//Helper method to extract book from resultSet
	private Book extractBookFromResultSet(ResultSet resultSet) throws SQLException {		
        Book book = new Book();        
        book.setIsbnNumber( resultSet.getString("isbn_number"));
        book.setBookTitle( resultSet.getString("book_title"));
        book.setPurchaseCost( resultSet.getDouble("purchase_cost"));
        book.setRetailPrice( resultSet.getDouble("retail_price"));
        book.setActive( resultSet.getBoolean("active"));
        book.setGenre( resultSet.getString("genre"));
        
        //Author author = extractAuthorFromResultSet(resultSet);
        Author author = new Author();
        author.setFullName(resultSet.getString("first_name"), resultSet.getString("last_name"));
        book.setAuthor(author);
        
        //get the author id
        Author authorId = new Author();
        authorId.setAuthorId(resultSet.getInt("author_id"));
        book.setAuthor(authorId);
        
        Inventory inventory = new Inventory();
        inventory.setQty(resultSet.getInt("qty"));
        book.setInventory(inventory);

        return book;
    }
	
	
	
	
	
	// Helper method to handle SQLException
    private String handleSQLException(SQLException e) {    	
    	return "SQL Error: " + e.getMessage();        
    }
    
    
    
    
    
    //close the connection
    public void closeConnection() {    	
        try {        	
            if (connection != null && !connection.isClosed()) {            	
                connection.close();                
            }
        } catch (SQLException e) {        	
        	JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);            
        }
    }


    //check is the isbn number already exists before adding a new book
	public boolean doesIsbnExists(String isbn) {
		String qry = "SELECT COUNT(*) AS count FROM Book WHERE isbn_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(qry)) {
            preparedStatement.setString(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(frame, handleSQLException(e), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
	}

}
