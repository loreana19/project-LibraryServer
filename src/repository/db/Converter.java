/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;

import domain.Author;
import domain.Authorship;
import domain.Book;
import domain.BookCopy;
import domain.BookGenre;
import domain.GenericEntity;
import domain.Librarian;
import domain.LoanNote;
import domain.LoanNoteItem;
import domain.Member;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LORA
 */
public class Converter {

    public static GenericEntity convert(GenericEntity entity, ResultSet rs)throws SQLException {
        if(entity instanceof Member){
            Librarian l=new Librarian();
            l.setLibrarianID(rs.getLong("LibrarianID"));
            l.setFirstName(rs.getString("lib.FirstName"));
            l.setLastName(rs.getString("lib.LastName"));
            l.setUsername(rs.getString("Username"));
            l.setPassword(rs.getString("Password"));
            
            Member m=new Member();
            m.setMemberID(rs.getLong("MemberID"));
            m.setFirstName(rs.getString("m.FirstName"));
            m.setLastName(rs.getString("m.LastName"));
            m.setContact(rs.getString("Contact"));
            m.setLibrarian(l);
            return m;
        }else if(entity instanceof Author){
            Author a=new Author(rs.getLong("AuthorID"), rs.getString("FirstName"), rs.getString("LastName"));
            return a;
        }else if(entity instanceof Librarian){
            Librarian l=new Librarian();
            l.setLibrarianID(rs.getLong("LibrarianID"));
            l.setFirstName(rs.getString("FirstName"));
            l.setLastName(rs.getString("LastName"));
            l.setUsername(rs.getString("Username"));
            l.setPassword(rs.getString("Password"));
            return l;
        }else if(entity instanceof Book){
            Book b=new Book();
            BookGenre bg=new BookGenre(rs.getLong("BookGenreID"), rs.getString("bg.Name"));
            b.setBookID(rs.getLong("BookID"));
            b.setName(rs.getString("b.Name"));
            b.setBookGenre(bg);
            b.setAuthors(null);
            b.setBookcopies(null);
            return b;
        }else if(entity instanceof BookGenre){
            BookGenre bg=new BookGenre(rs.getLong("BookGenreID"), rs.getString("Name"));
            return bg;
        }else if(entity instanceof BookCopy){
            Book b=new Book();
            BookGenre bg=new BookGenre(rs.getLong("BookGenreID"), rs.getString("bg.Name"));
            b.setBookID(rs.getLong("BookID"));
            b.setName(rs.getString("b.Name"));
            b.setBookGenre(bg);
            b.setAuthors(null);
            b.setBookcopies(null);
            
            BookCopy bc=new BookCopy(rs.getLong("BookCopyID"), rs.getInt("PublicityYear"), rs.getBoolean("Available"), b);
            return bc;
        }else if(entity instanceof Authorship){
            Book b=new Book();
            BookGenre bg=new BookGenre(rs.getLong("BookGenreID"), rs.getString("bg.Name"));
            b.setBookID(rs.getLong("BookID"));
            b.setName("b.Name");
            b.setBookGenre(bg);
            b.setAuthors(null);
            b.setBookcopies(null);
            
            Author a=new Author(rs.getLong("AuthorID"), rs.getString("FirstName"), rs.getString("LastName"));
            Authorship ash=new Authorship(b, a, rs.getInt("SerialNumber"));
            return ash;
        }else if(entity instanceof LoanNoteItem){
            Book b=new Book();
            BookGenre bg=new BookGenre(rs.getLong("BookGenreID"), rs.getString("bg.Name"));
            b.setBookID(rs.getLong("BookID"));
            b.setName(rs.getString("b.Name"));
            b.setBookGenre(bg);
            b.setAuthors(null);
            b.setBookcopies(null);
            
            BookCopy bc=new BookCopy(rs.getLong("BookCopyID"), rs.getInt("PublicityYear"), rs.getBoolean("Available"), b);
            LoanNoteItem lni=new LoanNoteItem();
            lni.setLoanNoteID(rs.getLong("LoanNoteID"));
            lni.setBookCopy(bc);
            lni.setLoanNoteItemID(rs.getLong("LoanNoteItemID"));
            if(rs.getDate("DateFrom")!=null){
                lni.setDateFrom(rs.getDate("DateFrom"));
            }else{
                lni.setDateFrom(null);
            }
            if(rs.getDate("DateTo")!=null){
                lni.setDateTo(rs.getDate("DateTo"));
            }else{
                lni.setDateTo(null);
            }
            return lni;
        }else if(entity instanceof LoanNote){
            Librarian l=new Librarian();
            l.setLibrarianID(rs.getLong("LibrarianID"));
            l.setFirstName(rs.getString("FirstName"));
            l.setLastName(rs.getString("LastName"));
            l.setUsername(rs.getString("Username"));
            l.setPassword(rs.getString("Password"));
            
            Member m=new Member();
            m.setMemberID(rs.getLong("MemberID"));
            m.setFirstName(rs.getString("m.FirstName"));
            m.setLastName(rs.getString("m.LastName"));
            m.setContact(rs.getString("Contact"));
            m.setLibrarian(l);
            
            LoanNote ln=new LoanNote();
            ln.setLoanNote(rs.getLong("LoanNoteID"));
            ln.setLibrarian(l);
            ln.setMember(m);
            if(rs.getDate("CreateDate")!=null){
                ln.setDate(rs.getDate("CreateDate"));
            }else{
                ln.setDate(null);
            }
            return ln;
        }
        
        return null;
    }
    
}
