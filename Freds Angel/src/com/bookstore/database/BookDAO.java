package com.bookstore.database;

import com.bookstore.model.Author;
import com.bookstore.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	
	private Connection connection;
		
		public BookDAO() {
			//initialize the connection
			try {				
				this.connection = DatabaseConnection.getConnection();				
			}
			catch (SQLException e) {				
				handleSQLException(e);				
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
			handleSQLException(e);			
		}		
		return book;		
	}
	
	
	//search for book by isbn number
	//action 1.2
	public Book getBookByIsbn(String isbnNumber) {		
		Book book = null;
		String qry = "SELECT * FROM book JOIN author on author.author_id = book.author_id WHERE isbn_number = ?";		
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
			handleSQLException(e);			
		}		
		return book;		
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
            handleSQLException(e);            
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
            preparedStatement.setInt( 7, book.getAuthorId() );
            preparedStatement.executeUpdate();              
        } catch (SQLException e) {        	
            handleSQLException(e);            
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
				System.out.println("The status of the book has been updated successfully.");
				return true;
			} else {
				System.out.println("The status of the book for isbn: " + isbnNumber + " was not updated. \nPlease check the isbn number and try again.");
				return false;
			}
			
			
		} catch(SQLException e) {
			handleSQLException(e);
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
			preparedStatement.setInt(6, updateBook.getAuthorId());
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
			handleSQLException(e);
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

        return book;
    }
	
	
	//Helper method to extract author from resultSet
	private Author extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
		
		Author author = new Author();
		/*
		author.setAuthorId(resultSet.getInt("author_id"));
		author.setFirstName(resultSet.getString("first_name"));
	    author.setLastName(resultSet.getString("last_name"));
	    */
	    author.setFullName(resultSet.getString("first_name"),resultSet.getString("last_name"));

	    return author;     
    }
	
	
	// Helper method to handle SQLException
    private void handleSQLException(SQLException e) {    	
        e.printStackTrace();        
    }
    
    
    
    
    
    //close the connection
    public void closeConnection() {    	
        try {        	
            if (connection != null && !connection.isClosed()) {            	
                connection.close();                
            }
        } catch (SQLException e) {        	
            handleSQLException(e);            
        }
    }

}
