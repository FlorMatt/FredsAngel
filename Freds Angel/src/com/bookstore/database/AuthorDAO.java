package com.bookstore.database;

import com.bookstore.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO {
	
	private Connection connection;
	
	public AuthorDAO() {
		
		//initialize the connection
		try {
			
			this.connection = DatabaseConnection.getConnection();
			
		}
		catch ( SQLException e ) {
			
			handleSQLException(e);
			
		}
		
	}
	
	
	//add an author
	public void addAuthor( Author author ) {
		
		String qry = " INSERT INTO Author(first_name, last_name) VALUES( ?, ? ) ";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(qry)) {
			
			preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.executeUpdate();
			
		}
		catch( SQLException e ) {
			
			handleSQLException(e);
			
		}
		
	}
	
	
	//search for an author by id
	public Author getAuthorById( int authorId ) {
		
		Author author = null;
		
		String qry = " SELECT * FROM Author WHERE author_id = ? ";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(qry)) {
			
			preparedStatement.setInt( 1, authorId );
			
			try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
				
	            if ( resultSet.next() ) {
	            	
	                author = new Author();
	                
	                author.setAuthorId( resultSet.getInt( "author_id" ) );
	                author.setFirstName( resultSet.getString( "first_name" ) );
	                author.setLastName( resultSet.getString( "last_name" ) );
	                
	            }
	        }
	    } catch ( SQLException e ) {
	    	
	        handleSQLException(e);
	    }
		
	    return author;
		
	}
	
	
	//search for an author by first name
	public Author getAuthorByFirstName( String firstName ) {
		
		Author author = null;
		
		String qry = " SELECT * FROM Author WHERE first_name = ? ";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(qry)) {
			
			preparedStatement.setString(1, firstName);
			
			try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
				
	            if ( resultSet.next() ) {
	            	
	                author = new Author();
	                
	                author.setAuthorId( resultSet.getInt( "author_id" ) );
	                author.setFirstName( resultSet.getString( "first_name" ) );
	                author.setLastName( resultSet.getString( "last_name" ) );
	                
	            }
	        }
	    } catch ( SQLException e ) {
	    	
	        handleSQLException(e);
	    }
		
	    return author;
		
	}
	
	
	//search for an author by last name
	public Author getAuthorByLasttName( String lastName ) {
		
		Author author = null;
		
		String qry = " SELECT * FROM Author WHERE last_name = ? ";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(qry)) {
			
			preparedStatement.setString(1, lastName);
			
			try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
				
	            if ( resultSet.next() ) {
	            	
	                author = new Author();
	                
	                author.setAuthorId( resultSet.getInt( "author_id" ) );
	                author.setFirstName( resultSet.getString( "first_name" ) );
	                author.setLastName( resultSet.getString( "last_name" ) );
	                
	            }
	        }
	    } catch ( SQLException e ) {
	    	
	        handleSQLException(e);
	    }
		
	    return author;
		
	}
	
	
	// Helper method to handle SQLException
    private void handleSQLException(SQLException e) {
    	
        e.printStackTrace();
        
    }
    
    
    //close the connection
    public void closeConnection() {
    	
        try {
        	
            if ( connection != null && !connection.isClosed() ) {
            	
                connection.close();
                
            }
        } catch ( SQLException e ) {
        	
            handleSQLException(e);
            
        }
    }

}
