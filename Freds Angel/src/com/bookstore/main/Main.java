package com.bookstore.main;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bookstore.database.*;
import com.bookstore.model.*;
import com.bookstore.ui.*;

public class Main {
	
	//add the logger
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		
		Scanner mScanner = new Scanner(System.in);
		int mOption;
		
		do {
			System.out.println("Welcome to Fred's Angel (I.M.S)!");
			System.out.println("Enter 1 to search for a Book by its Isbn Number");
			System.out.println("Enter 2 to add a Book");
			System.out.println("Enter 3 to change a Book's status");
			System.out.println("Enter 4 to edit a Book's inventory qty");
			System.out.println("Enter 5 to generate an Audit Report");
			System.out.println("Enter 6 to edit a Book");
			System.out.println("Enter 0 to exit the application");
			System.out.println("hello");
			
			mOption = mScanner.nextInt();
			
			switch (mOption) {
			case 1: //search for a book by isbn number
				optionOne(mScanner);
				break;
			case 2: //add a book to the system
				optionTwo(mScanner);
				break;
			case 3: //change a book's status
				optionThree(mScanner);
				break;
			case 4: //edit a book's inventory qty
				optionFour(mScanner);
				break;
			case 5: //generate audit excel report
				//optionFiveA(mScanner);
				optionFiveB(mScanner);
				break;
			case 6: //edit an existing book's information
				optionSix(mScanner);
				break;
			case 0:
				System.out.println("-------------------------------------------------------------");
				System.out.println("Now closing Fred's Angel (I.M.s)");
				System.out.println("-------------------------------------------------------------");
				break;
				default:
					logger.warning("Please enter a valid menu option.");;
			}
		} while (mOption != 0);
		
		
		mScanner.close();
	}
	
	
	
	
	
	
	
	
	
	
	//menu option 1
	//Search for book by an Isbn Number
    public static void optionOne(Scanner oScanner) {
    	
        oScanner.nextLine();
        
        System.out.println("Please enter an ISBN Number to search:");
        System.out.println("Sample ISBN number: 978-9-0123-4567-6");
   	 	
        String isbn;
        while (true) {
        	isbn = oScanner.nextLine();
            if (isValidIsbn(isbn)) {
                break;
            } else {
                logger.warning("Please enter a valid Isbn Number pattern");
            }
        }
    	
		BookDAO bookDAO = new BookDAO();		
		Book book = bookDAO.getBookByIsbn(isbn);
		
		if(book != null) {
			System.out.println("Results: " + book);			
		} else {			
			logger.info("No books match your search criteria.");			
		}		
		
		bookDAO.closeConnection();
		System.out.println("Press 'Enter' to return to the main menu");
		oScanner.nextLine();
    }
    
    
    
    
    
    //menu option 2
    //add a book
    public static void optionTwo(Scanner oScanner) {
    	
    	oScanner.nextLine();
    	
    	/**
    	 * declare book variables
    	 */
    	String isbn = "";
    	String title = "";
    	double purchase = 0.0;
    	double retail = 0.0;
    	boolean active = true;
    	String genre = "";
    	int authorId = 0;
    	
    	//input validation for isbn
    	while (true) {
    		System.out.println("Enter the isbn number: ");
            isbn = oScanner.nextLine();
            if (isValidIsbn(isbn)) {
                break;
            } else {
            	logger.warning("Please enter a valid Isbn Number pattern");
            }
        }
    	
    	//input validation book book title
    	while (true) {
    		System.out.println("Enter the book title: ");
            title = oScanner.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            } else {
            	logger.warning("Please enter a valid book title");
            }
        }
    	
    	//input validation for purchase cost
    	while (true) {
    		try {
    			System.out.println("Enter the purchase cost: ");
    			purchase = Double.parseDouble(oScanner.nextLine());
    			break;
    		} catch (NumberFormatException e) {
    			logger.warning("Please enter a valid purchase cost");
    		}
    	}
    	
    	//input validation for retail price
    	while (true) {
    		try {
    			System.out.println("Enter the retail price: ");
    			retail = Double.parseDouble(oScanner.nextLine());
    			break;
    		} catch (NumberFormatException e) {
    			logger.warning("Please enter a valid purchase cost");
    		}
    	}
    	
    	// Input validation for genre
        while (true) {
            System.out.println("Enter the book's genre:");
            genre = oScanner.nextLine();
            if (!genre.trim().isEmpty()) {
                break; // break the loop if the input is not empty
            } else {
            	logger.warning("Please enter a valid genre");
            }
        }
        
        //input validation for author id
        while (true) {
            try {
                System.out.println("Enter the author Id: ");
                authorId = Integer.parseInt(oScanner.nextLine());
                if (authorId > 0) {
                    break;
                } else {
                    logger.warning("Please enter a positive value for the author id");
                }
            } catch (NumberFormatException e) {
                logger.warning("Please enter a valid value for the author id");
            }
        }
    	
    	BookDAO bookDAO = new BookDAO();
    	Book newBook = new Book(isbn, title, purchase, retail, active, genre, authorId);
    	bookDAO.addBook(newBook);
    	
    	System.out.println("The new book was successfully added!");

	    bookDAO.closeConnection();
	    System.out.println("Press 'Enter' to return to the main menu");
	    oScanner.nextLine();
	    
    }
    
    
    
    
    
    //menu option3
    //change a book's status
    public static void optionThree(Scanner oScanner) {
    	
    	oScanner.nextLine();
    	
    	/**
    	 * @param isbn
    	 */
    	String isbn = "";
    	
    	//input validation for isbn
    	while (true) {
    		System.out.println("Enter the isbn number: ");
            isbn = oScanner.nextLine();
            if (isValidIsbn(isbn)) {
                break;
            } else {
            	logger.warning("Please enter a valid Isbn Number pattern");
            }
        }
    	
    	// Input validation for the new status
        boolean newStatus;
        while (true) {
            System.out.println("Enter the new status for the book (true or false): ");
            String statusInput = oScanner.nextLine().toLowerCase();
            if (statusInput.equals("true") || statusInput.equals("false")) {
                newStatus = Boolean.parseBoolean(statusInput);
                break;
            } else {
            	logger.warning("Please enter 'true' or 'false'");
            }
        }
        
        BookDAO bookDAO = new BookDAO();
        boolean statusChanged = bookDAO.changeActiveStatus(isbn, newStatus);

        if (statusChanged) {
            System.out.println("The book's status successfully updated!");
        } else {
        	logger.warning("Failed to update the book's status'");
        }

        bookDAO.closeConnection();
        System.out.println("Press 'Enter' to return to the main menu");
        oScanner.nextLine();
    }
    
    
    
    
    
    //menu option 4
    //edit inventory qty for a book
    public static void optionFour(Scanner oScanner) {
    	
    	oScanner.nextLine();
    	
    	InventoryDAO inventoryDAO = new InventoryDAO();
    	
    	//input validation for the isbn number to search for
    	String isbn;
    	while (true) {
    		System.out.println("Enter the isbn number to search by: ");
            isbn = oScanner.nextLine();
            if (isValidIsbn(isbn)) {
                break;
            } else {
            	logger.warning("Please enter a valid Isbn Number pattern");
            }
    	}
    	
    	//get the list of inventory records based on the isbn number
    	List<Inventory> inventoryList = inventoryDAO.getInventoryByIsbn(isbn);
    	
    	if (inventoryList.isEmpty()) {
    		logger.warning("No inventory record(s) found\nPlease try again");
    	} else {
    		System.out.println("Inventory found: ");
    		System.out.println(inventoryList);
    	}
    	
    	
    	//input validation for inventory id
    	int inventoryId;
    	while (true) {
    		try {
    			System.out.println("Enter the inventory id for the record you want to update: ");
    			inventoryId = Integer.parseInt(oScanner.nextLine());
    			if (isValidInventoryId(inventoryId, inventoryList)) {
    				break;
    			} else {
    				logger.warning("Please enter a valid value for the list");
    			}
    		} catch (NumberFormatException e) {
    			logger.warning("Please enter a valid value for the inventory id");
    		}
    	}
    	
    	
    	//input validation for the new inventory qty
    	int newQty;
    	while (true) {
    		try {
    			System.out.println("Enter the new inventory qty: ");
    			newQty = Integer.parseInt(oScanner.nextLine());
    			
    			if (newQty >= 0) {
    				break;
    			} else {
    				logger.warning("Please enter a positive value for the new qty");
    			}
    		} catch (NumberFormatException e) {
    			logger.warning("Please enter a valid value for the new qty");
    		}
    	}
    	
    	
    	//update the inventory
    	Inventory updatedInventory = new Inventory(inventoryId, isbn, newQty);
    	boolean updateSuccess = inventoryDAO.updateInventory(updatedInventory);
    	
    	if (updateSuccess) {
    		System.out.println("The inventory qty was successfully updated!");
    	} else {
    		logger.warning("Failed to update the inventory qty\nPlease try again");
    	}
    	
    	inventoryDAO.closeConnection();
    	System.out.println("Press 'Enter' to return to the main menu");
    	oScanner.nextLine();
    	
    }
    
    
    
    
    
    //menu option 5
    //generate audit report
    public static void optionFiveA(Scanner oScanner) {
    	
    	oScanner.nextLine();
    	
    	//ask the user where they want to save the file
    	System.out.println("Enter the file path to save the report to: ");
    	String filePath = oScanner.nextLine();
    	
    	//get the list of books
    	BookDAO bookDAO = new BookDAO();
    	List<Book> books = bookDAO.getAllBooks();
    	bookDAO.closeConnection();
    	
    	//check if there is no books
    	if (books.isEmpty()) {
    		logger.warning("No books found to generate the report\nPlease try again");
    	} else {
    		ExcelExporter.generateInventoryAudit(books, filePath);
    	}
    	
    	System.out.println("Press 'Enter' to return to the main menu");
    	oScanner.nextLine();
    }
    
    public static void optionFiveB(Scanner oScanner) {
    	
    	oScanner.nextLine();
    	
    	//get the list of books
    	BookDAO bookDAO = new BookDAO();
    	List<Book> books = bookDAO.getAllBooks();
    	bookDAO.closeConnection();
    	
    	//check if there is no books
    	if (books.isEmpty()) {
    		logger.warning("No books found to generate the report\nPlease try again");
    	} else {
    		System.out.println("Books were found: ");
    		
    		for(Book book : books) {
    			System.out.println(book);
    		}
    		
    		//ask the user if they want to export the report to excel
    		//ask the user where they want to save the file
        	System.out.println("Do you want to export the report? (yes/no) ");
        	String eChoice = oScanner.nextLine().toLowerCase();
        	
        	if (eChoice.equals("yes")) {
        		//ask the user where they want to save the file
            	System.out.println("Enter the file path to save the report to: ");
            	String filePath = oScanner.nextLine();
            	
            	ExcelExporter.generateInventoryAudit(books, filePath);
            	System.out.println("The report was successfully exported to: \" + filePath");
        	} else {
        		logger.warning("Report was not exported\nPlease try again");
        	}
    	}
    	
    	System.out.println("Press 'Enter' to return to the main menu");
    	oScanner.nextLine();
    }
    
    
    
    
    //menu option 6
    //edit a book's data
    public static void optionSix(Scanner oScanner) {
    	
    	oScanner.nextLine();
    	
    	String updatedIsbn;
    	String updatedTitle;
    	Double updatedPurchase;
    	Double updatedRetail;
    	String updatedGenre;
    	int updatedAuthorId;
    	//Book updatedBook;
    	
    	//input validation for the isbn number to edit
    	String isbn;
    	while (true) {
    		System.out.println("Enter the isbn number of the book you want to edit: ");
            isbn = oScanner.nextLine();
            if (isValidIsbn(isbn)) {
                break;
            } else {
            	logger.warning("Please enter a valid Isbn Number pattern");
            }
    	}
    	
    	
    	BookDAO bookDAO = new BookDAO();
    	Book existingBook = bookDAO.getBookByIsbn(isbn);
    	
    	if (existingBook != null) {
    		System.out.println("Existing book information:");
    		System.out.println(bookDAO.getBookByIsbn(isbn));
    		
    		
    		//update the book's isbn number
    		//input validation for isbn number
    		while (true) {
    			System.out.println("Enter the updated isbn number - Enter the same if unchanged");
        		updatedIsbn = oScanner.nextLine();
        		if (isValidIsbn(updatedIsbn)) {
        			break;
        		} else {
        			logger.warning("Please enter a valid Isbn Number pattern");
        		}
    		}
    		
    		
    		//update the book's title
    		//input validation for the book title
        	while (true) {
        		System.out.println("Enter the updated book title - Enter the same is unchanged: ");
                updatedTitle = oScanner.nextLine();
                if (!updatedTitle.trim().isEmpty()) {
                    break;
                } else {
                	logger.warning("Please enter a valid book title");
                }
            }
        	
        	
        	//update the book's purchase cost
        	//input validation for purchase cost
        	while (true) {
        		try {
        			System.out.println("Enter the purchase cost: ");
        			updatedPurchase = Double.parseDouble(oScanner.nextLine());
        			if (updatedPurchase >= 0.0) {
        				break;
        			} else {
        				logger.warning("Please enter a positive value for the purchase cost");
        			}
        		} catch (NumberFormatException e) {
        			logger.warning("Please enter a valid purchase cost");
        		}
        	}
        	
        	
        	//update the book's retail price
        	//input validation for retail price
        	while (true) {
        		try {
        			System.out.println("Enter the retail price: ");
        			updatedRetail = Double.parseDouble(oScanner.nextLine());
        			if (updatedRetail >= 0.0) {
        				break;
        			} else {
        				logger.warning("Please enter a positive value for the purchase cost");
        			}
        		} catch (NumberFormatException e) {
        			logger.warning("Please enter a valid purchase cost");
        		}
        	}
        	
        	
        	//update the book's genre
        	// Input validation for genre
            while (true) {
                System.out.println("Enter the book's genre:");
                updatedGenre = oScanner.nextLine();
                if (!updatedGenre.trim().isEmpty()) {
                    break; // break the loop if the input is not empty
                } else {
                	logger.warning("Please enter a valid genre");
                }
            }
        	
        	
        	//update the book's author id
            //input validation for author id
            while (true) {
                try {
                    System.out.println("Enter the author Id: ");
                    updatedAuthorId = Integer.parseInt(oScanner.nextLine());
                    if (updatedAuthorId > 0) {
                    	Author updatedAuthor = new Author();
                        break;
                    } else {
                        logger.warning("Please enter a positive value for the author id");
                    }
                } catch (NumberFormatException e) {
                    logger.warning("Please enter a valid value for the author id");
                }
            }
        	
            
            //edit book method
            Book updatedBook = new Book(updatedIsbn, updatedTitle, updatedPurchase, updatedRetail, true, updatedGenre, updatedAuthorId);
            boolean updateSuccess = bookDAO.editBook(updatedBook);
            
            if (!updateSuccess) {
            	logger.warning("The book for Isbn Number" + isbn + " was not updated");
            }
    	} else {
    		logger.warning("No book was found for isbn: " + isbn);
    	}
    }
    
    
    
    
    
    //validate Isbn Number format
    private static boolean isValidIsbn(String isbn) {
        String isbnPattern = "^\\d{3}-\\d-\\d{4}-\\d{4}-\\d{1}$";
        return isbn.matches(isbnPattern);
    }
    
    
    
    //validate the provided inventory id
    private static boolean isValidInventoryId(int inventoryId, List<Inventory> inventoryList) {
        return inventoryList.stream().anyMatch(inv -> inv.getInventoryId() == inventoryId);
    }

}
	


