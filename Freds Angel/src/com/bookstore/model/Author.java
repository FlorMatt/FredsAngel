package com.bookstore.model;

public class Author {
	
	
	
	//declare variables that make up an author
	private int authorId;
	private String firstName;
	private String lastName;
	private String fullName;
	
	
	
	//default constructor
	public Author() {
		
	}
	
	
	
	//parameter constructor
	public Author(int authorId, String firstName, String lastName, String fullName) {		
		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;	
		this.fullName = firstName + " " + lastName;
	}
	
	
	
	//get and set values	
	//authorId
	public int getAuthorId() {		
		return authorId;		
	}
	public void setAuthorId( int authorId ) {		
		this.authorId = authorId;		
	}
	
	
	//first name
	public String getFirstName() {		
		return firstName;		
	}
	public void setFirstName( String firstName ) {		
		this.firstName = firstName;		
	}
	
	
	//last name
	public String getLastName() {		
		return lastName;		
	}
	public void setLastName( String lastName ) {		
		this.lastName = lastName;		
	}
	
	
	//full name
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String firstName, String lastName) {
		this.fullName = firstName + " " + lastName;
	}
	
	
	//logging set up
	//going to override toString for the logging
	@Override
	public String toString() {
		
		return "Author{" +
					"authorId: '" + authorId + '\'' +
					", firstName: '" + firstName + '\'' +
					", lastName: '" + lastName + '\'' +
					"fullName: '" + fullName + "}'";
		
	}

}
