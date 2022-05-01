/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Author;
import domain.Authorship;
import domain.Book;
import domain.BookCopy;
import domain.BookGenre;
import domain.Librarian;
import domain.LoanNote;
import domain.LoanNoteItem;
import domain.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import ps.so.author.GetAllAuthorSO;
import ps.so.authorship.AddAuthorshipSO;
import ps.so.authorship.GetAllAuthorshipSO;
import ps.so.book.AddBookSO;
import ps.so.book.DeleteBookSO;
import ps.so.book.GetAllBookSO;
import ps.so.bookcopy.AddBookCopySO;
import ps.so.bookcopy.EditBookCopySO;
import ps.so.bookcopy.GetAllBookCopySO;
import ps.so.genre.GetAllGenreSO;
import ps.so.librarian.GetAllLibrarianSO;
import ps.so.loannote.AddLoanNoteSO;
import ps.so.loannote.EditLoanNoteSO;
import ps.so.loannote.GetAllLoanNoteSO;
import ps.so.member.AddMemberSO;
import ps.so.member.DeleteMemberSO;
import ps.so.member.EditMemberSO;
import ps.so.member.GetAllMemberSO;
//import ps.so.member.GetMemberSO;
import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.RepositoryAuthor;
import repository.db.impl.RepositoryAuthorship;
import repository.db.impl.RepositoryBook;
import repository.db.impl.RepositoryBookCopy;
import repository.db.impl.RepositoryDBGeneric;
import repository.db.impl.RepositoryGenre;
import repository.db.impl.RepositoryLN;
import repository.db.impl.RepositoryLNItem;
import repository.db.impl.RepositoryLibrarian;
import repository.db.impl.RepositoryMember;

/**
 *
 * @author LORA
 */
public class Controller {
    
    private static Controller instance;
   /* private final Repository storageMember;
    private final Repository storageLibrarian;
    private final Repository storageBook;
    private final Repository storageGenre;
    private final Repository storageAuthor;
    private final Repository storageBookCopy;
    private final Repository storageAuthorship;
    private final Repository storageLN;
    private final Repository storageLNItem;*/
    private final Repository repositoryGeneric;

    private Librarian currentLibrarian;

    public Controller() {
       /* this.storageMember = new RepositoryMember();
        this.storageLibrarian = new RepositoryLibrarian();
        this.storageBook = new RepositoryBook();
        this.storageGenre = new RepositoryGenre();
        this.storageAuthor=new RepositoryAuthor();
        this.storageBookCopy = new RepositoryBookCopy();
        this.storageAuthorship = new RepositoryAuthorship();
        this.storageLN=new RepositoryLN();
        this.storageLNItem=new RepositoryLNItem();*/
        this.repositoryGeneric=new RepositoryDBGeneric();
    }

 
    public static Controller getInstance() {
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public Librarian Login(String username, String password) throws Exception{
 
        AbstractSO getAllLib = new GetAllLibrarianSO();
        getAllLib.execute(new Librarian());
        Librarian lib = ((GetAllLibrarianSO) getAllLib).login(username,password);
        return lib;
    }
   
    
    public List<Book> getAllBooks() throws Exception{
        AbstractSO getAllBooks=new GetAllBookSO();
        getAllBooks.execute(new Book()); 
        List<Book> list = ((GetAllBookSO) getAllBooks).getList();
        return list;        
    }
    public List<Member> getAllMembers() throws Exception{
        AbstractSO getAllMembers=new GetAllMemberSO();
        getAllMembers.execute(new Member()); 
         List<Member> list = ((GetAllMemberSO) getAllMembers).getList();
        return list;
    }
   
    
    public  List<BookGenre> getAllGenres() throws Exception{
        AbstractSO getAllGenres=new GetAllGenreSO();
        getAllGenres.execute(new BookGenre()); 
        List<BookGenre> list = ((GetAllGenreSO) getAllGenres).getList();
        return list;
    }
    public  List<Author> getAllAuthors() throws  Exception{
        AbstractSO getAllAuthors=new GetAllAuthorSO();
        getAllAuthors.execute(new Author()); 
        List<Author> list = ((GetAllAuthorSO) getAllAuthors).getList();
        return list;        
    }
    
    public void addBook(Book book) throws Exception{
        AbstractSO addBook=new AddBookSO();
        addBook.execute(book);
    }
    
    public void addMember(Member member) throws Exception{
        AbstractSO addMemberSo=new AddMemberSO();
        addMemberSo.execute(member);
    }

    public void setCurrentLibrarian(Librarian currentLibrarian) {
        this.currentLibrarian = currentLibrarian;
    }

    public Librarian getCurrentLibrarian() {
        return currentLibrarian;
    }


    public void addBookCopy(BookCopy bc) throws Exception{
        AbstractSO addBookCopy=new AddBookCopySO();
        addBookCopy.execute(bc);
    }

    public void removeMember(Member m)throws Exception{
        AbstractSO deleteMember=new DeleteMemberSO();
        deleteMember.execute(m);        
    }

    public void updateMember(Member m) throws Exception {
        AbstractSO editMember=new EditMemberSO();
        editMember.execute(m);       
    }

   
    public void addAuthorship(Authorship authorship) throws Exception {
        AbstractSO addAuthorship=new AddAuthorshipSO();
        addAuthorship.execute(authorship);
  
    }

    public void removeBook(Book b) throws Exception {
        AbstractSO deleteBook=new DeleteBookSO();
        deleteBook.execute(b);
    }

    public List<Authorship> getAllAuthorships() throws Exception {
        AbstractSO getAllAuthorships=new GetAllAuthorshipSO();
        getAllAuthorships.execute(new Authorship()); 
        List<Authorship> list = ((GetAllAuthorshipSO) getAllAuthorships).getList();
        return list; 
    }

    public List<LoanNote> getAllLN()throws Exception{
        AbstractSO getAllLN = new GetAllLoanNoteSO();
        getAllLN.execute(new LoanNote());
        List<LoanNote> list = ((GetAllLoanNoteSO) getAllLN).getList();
        return list;
    }

    public List<BookCopy> getAllBookCopies() throws Exception {
        AbstractSO getAllBookCopies=new GetAllBookCopySO();
        getAllBookCopies.execute(new BookCopy()); 
        List<BookCopy> list = ((GetAllBookCopySO) getAllBookCopies).getList();
        return list;         
    }


    public void addLoanNote(LoanNote ln)throws Exception{
         AbstractSO addLoanNote = new AddLoanNoteSO();
         addLoanNote.execute(ln);
    }


   /* public List<LoanNoteItem> getLNItems(long id)throws Exception{
        return storageLNItem.getAll(id);
    }*/

    public void updateLoanNote(LoanNote ln) throws Exception  {
       AbstractSO editLoanNote = new EditLoanNoteSO();
       editLoanNote.execute(ln);
    }

    public List<Member> getMember(Member m)throws Exception{
        AbstractSO getMembers=new GetAllMemberSO();
        getMembers.execute(m); 
        List<Member> list = ((GetAllMemberSO) getMembers).getList();
        return list;  
    }

    public List<Book> getBooks(Book b) throws Exception{
        AbstractSO getBooks=new GetAllBookSO();
        getBooks.execute(b); 
        List<Book> list = ((GetAllBookSO) getBooks).getList();
        return list;
    }

    public void editBookCopy(BookCopy bc)throws Exception{
       AbstractSO editBC = new EditBookCopySO();
       editBC.execute(bc);   
    }
    
    
}
