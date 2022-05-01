/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Operations;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import communication.Sender;
import controller.Controller;
import domain.Author;
import domain.Authorship;
import domain.Book;
import domain.BookCopy;
import domain.BookGenre;
import domain.Librarian;
import domain.LoanNote;
import domain.LoanNoteItem;
import domain.Member;
import java.io.IOException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LORA
 */
public class HandleClientThread extends Thread{
    private Socket socket;
    private Librarian librarian;
            
    public HandleClientThread(Socket socket) throws IOException {
        this.socket = socket;
        
    }

    @Override
    public void run() {
        while(!socket.isClosed()){
            try {
                Request request=(Request) new Receiver(socket).receive();
                Response response=handleRequest(request);
                new Sender(socket).send(response);
            } catch (Exception ex) {
                Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
    
    private Response handleRequest(Request request){
        int operation=request.getOperation();
        switch(operation){
            case Operations.LOGIN:
                return login(request);
            case Operations.GET_ALL_BOOKGENRES:
                return getAllBookGenres(request);
            case Operations.ADD_BOOK:
                return addBook(request);
            case Operations.ADD_MEMBER:
                return addMember(request);
            case Operations.ADD_BOOKCOPY:
                return addBookCopy(request);
            case Operations.GET_BOOKS:
                return getAllBooks(request);
            case Operations.GET_ALL_MEMBERS:
                return getAllMembers(request);
            case Operations.REMOVE_MEMBER:
                return removeMember(request);
            case Operations.UPDATE_MEMBER:
                return updateMember(request);
            case Operations.ADD_AUTHORSHIP:
                return addAuthorship(request);
            case Operations.GET_AUTHORS:
                return  getAllAuthors(request);
            case Operations.REMOVE_BOOK:
                return  removeBook(request);
            case Operations.GET_ALL_AUTHORSHIPS:
                return getAllAuthorships(request);
            case Operations.GET_ALL_LN:
                return getAllLN(request);
            case Operations.GET_ALL_BOOKCOPIES:
                return getAllBookCopies(request);
            case Operations.ADD_LOANNOTE:
                return addLoanNote(request);
            case Operations.UPDATE_LN:
                return updateLoanNote(request);
            case Operations.GET_MEMBER:
                return  getMember(request);
            case Operations.GET_ALL_BOOKS:
                return getBooks(request);
            case Operations.EDIT_BOOKCOPY:
                return editBookCopy(request);
        }
        return null;
    }
    private Response login(Request request){
        Response response=new Response();
        Librarian requestLibrarian=(Librarian) request.getArgument();
        
        try {
            Librarian librarian=Controller.getInstance().Login(requestLibrarian.getUsername(), requestLibrarian.getPassword());
            System.out.println("Successful login");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(librarian);
            this.librarian=librarian;
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }
    public Librarian getLibrarian(){
        return librarian;
    }

    private Response getAllBooks(Request request) {
        Response response=new Response();
        
        try {
            List<Book> books=Controller.getInstance().getAllBooks();
            System.out.println("Successfully loaded list of books ");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(books);
            //this.librarian=librarian;
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;    }

    private Response addBook(Request request) {
        Response response=new Response();
        Book book=(Book) request.getArgument();
        try {
            Controller.getInstance().addBook(book);
            System.out.println("Book is successfully created");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response; 
    }

    private Response addMember(Request request) {
        Response response=new Response();
        Member member=(Member) request.getArgument();
        try {
            Controller.getInstance().addMember(member);
            System.out.println("Member is successfully created");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;     }



    private Response addBookCopy(Request request) {
        Response response=new Response();
        BookCopy bc=(BookCopy) request.getArgument();
        try {
            Controller.getInstance().addBookCopy(bc);
            System.out.println("BookCopy is successfully created");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response; 
    }

    private Response getAllBookGenres(Request request) {
        Response response=new Response();
        
        try {
            List<BookGenre> bookG=Controller.getInstance().getAllGenres();
            System.out.println("Successfully loaded list of book genres ");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(bookG);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;   
    }

    private Response getAllMembers(Request request) {
        Response response=new Response();
        
        try {
            List<Member> members=Controller.getInstance().getAllMembers();
            System.out.println("Successfully loaded list of members");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(members);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;   
    }

    private Response removeMember(Request request) {
        Response response=new Response();
        Member m=(Member) request.getArgument();
        try {
            Controller.getInstance().removeMember(m);
            System.out.println("Member is successfully deleted");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;     }

    private Response updateMember(Request request) {
        Response response=new Response();
        Member m=(Member) request.getArgument();
        try {
            Controller.getInstance().updateMember(m);
            System.out.println("Member is successfully updated");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response; 
    }


    private Response getAllAuthors(Request request) {
        Response response=new Response();
        try {
            List<Author> authors=Controller.getInstance().getAllAuthors();
            System.out.println("Successfully loaded list of authors");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(authors);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;     }

    private Response addAuthorship(Request request) {
        Response response=new Response();
        Authorship authorship=(Authorship) request.getArgument();
        try {
            Controller.getInstance().addAuthorship(authorship);
            System.out.println("Authorship is successfully created");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;     }

    private Response removeBook(Request request) {
        Response response=new Response();
        Book b=(Book) request.getArgument();
        try {
            Controller.getInstance().removeBook(b);
            System.out.println("Book is successfully deleted");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response; 
    }

    private Response getAllAuthorships(Request request) {
        Response response=new Response();
        try {
            List<Authorship> authorships=Controller.getInstance().getAllAuthorships();
            System.out.println("Successfully loaded list of authorships");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(authorships);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;   
    }

    private Response getAllLN(Request request) {
        Response response=new Response();
        try {
            List<LoanNote> ln=Controller.getInstance().getAllLN();
            System.out.println("Successfully loaded list of loanNotes");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(ln);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;  
    }

    private Response getAllBookCopies(Request request) {
        Response response=new Response();
        try {
            List<BookCopy> bc=Controller.getInstance().getAllBookCopies();
            System.out.println("Successfully loaded list of bookcopies");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(bc);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response; 
    }


    private Response addLoanNote(Request request) {
        Response response=new Response();
        LoanNote ln=(LoanNote) request.getArgument();
        try {
            Controller.getInstance().addLoanNote(ln);
            System.out.println("LoanNote is successfully created");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;   
    }

   
    private Response updateLoanNote(Request request) {
        Response response=new Response();
        LoanNote ln=(LoanNote) request.getArgument();
        try {
            Controller.getInstance().updateLoanNote(ln);
            System.out.println("Successfully updated loan note");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response; 
    }

    private Response getMember(Request request) {
        Response response=new Response();
        Member m=(Member) request.getArgument();
        try {
            List<Member> list=Controller.getInstance().getMember(m);
            System.out.println("Successfully loaded list of members");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;
    }

   

    private Response getBooks(Request request) {
        Response response=new Response();
        Book b=(Book) request.getArgument();
        try {
            List<Book> list=Controller.getInstance().getBooks(b);
            System.out.println("Successfully loaded list of books");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;      }

    private Response editBookCopy(Request request) {
        Response response=new Response();
        BookCopy bc=(BookCopy) request.getArgument();
        try {
            Controller.getInstance().editBookCopy(bc);
            System.out.println("Successfully edited bookcopy");
            response.setResponseType(ResponseType.SUCCESS);
            response.setResponse(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setResponseType(ResponseType.ERROR);
            response.setException(ex);
        }
        return response;    }
}

