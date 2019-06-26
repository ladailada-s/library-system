package com.company;

public class Books {
    private String sBookClsCde;
    private String sBookCde;
    private String sBookName;
    private String sBookAuthor;
    private String sBookStatus;  //N = can borrow | Y = can't borrow | w = wait for approve
    private String sBookBorrowBy ;
    private String sBookBorrowDte;
    private String sBookBorrowRetrn;

    public Books(String sBookClsCde,String sBookCde,String sBookName,String sBookAuthor) {
        setsBookClsCde(sBookClsCde);
        setsBookCde(sBookCde);
        setsBookName(sBookName);
        setsBookAuthor(sBookAuthor);
        setsBookStatus("N");
        setsBookBorrowDte("-");
        setsBookBorrowRetrn("-");
    }

    public String getsBookClsCde() {
        return sBookClsCde;
    }

    public void setsBookClsCde(String sBookClsCde) {
        this.sBookClsCde = sBookClsCde;
    }

    public String getsBookCde() {
        return sBookCde;
    }

    public void setsBookCde(String sBookCde) {
        this.sBookCde = sBookCde;
    }

    public String getsBookName() {
        return sBookName;
    }

    public void setsBookName(String sBookName) {
        this.sBookName = sBookName;
    }

    public String getsBookAuthor() {
        return sBookAuthor;
    }

    public void setsBookAuthor(String sBookAuthor) {
        this.sBookAuthor = sBookAuthor;
    }

    public String getsBookStatus() {
        return sBookStatus;
    }

    public void setsBookStatus(String sBookStatus) {
        this.sBookStatus = sBookStatus;
    }

    public String getsBookBorrowBy() {
        return sBookBorrowBy;
    }

    public void setsBookBorrowBy(String sBookBorrowBy) {
        this.sBookBorrowBy = sBookBorrowBy;
    }

    public String getsBookBorrowDte() {
        return sBookBorrowDte;
    }

    public void setsBookBorrowDte(String sBookBorrowDte) {
        this.sBookBorrowDte = sBookBorrowDte;
    }

    public String getsBookBorrowRetrn() {
        return sBookBorrowRetrn;
    }

    public void setsBookBorrowRetrn(String sBookBorrowRetrn) {
        this.sBookBorrowRetrn = sBookBorrowRetrn;
    }
}
