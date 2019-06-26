package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static ArrayList<Users> users = new ArrayList<>();
    private static ArrayList<Bookshelf> booksShelf = new ArrayList<>();
    private static ArrayList<Books> books= new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        defaultData();
        keyUserLogin();
    }

    private static void keyUserLogin(){
        System.out.println("Please key name for log in : ");
        String iUserName = scan.next();
        String userName = iUserName;
        String usersPosition = checkUserLogin(iUserName);
        if (checkUserLogin(iUserName).equals("Staff")) {
            //   System.out.println("Staff");
            mainMenuStaff(userName,usersPosition);
        }else if ((checkUserLogin(iUserName).equals("Librarian"))){
            //  System.out.println("Librarian");
            mainMenuLibrarian(userName,usersPosition);
        }else{
            System.out.println("Not found user "+iUserName+" in system.");
        }
    }

    private static String checkUserLogin(String iName){
        String tPosition = "";
        for(int i=0; i < users.size();i++){
            if (users.get(i).getsName().equals(iName)){
                tPosition = users.get(i).getsPosition();
                return tPosition;
            }
        }
        return tPosition;
    }

    private static void mainMenuLibrarian(String iUserName,String iUserPosition){
        System.out.println("============= Menu For Librarian=============");
        System.out.println("1. Show all books");
        System.out.println("2. Search books");
        System.out.println("3. Check status books");
        System.out.println("4. Approve borrow book");
        System.out.println("5. Receive return book");
        System.out.println("6. Exit program");
        System.out.println("7. New log in");
        System.out.println("===================================");

        System.out.println("Please select menu : ");
        int key = scan.nextInt();
        menuLibrarianSelect(key,iUserName,iUserPosition);
    }

    private static void menuLibrarianSelect(int key,String iUserName,String iUserPosition){
        switch (key){
            case 1:{
                showAllBooks();
                mainMenuLibrarian(iUserName,iUserPosition);
                break;
            }
            case 2:{//Search books
                menuSearchBook(iUserName,iUserPosition);
                break;
            }
            case 3:{ //Check status books
                System.out.println("Status : N = Can borrow | Y = Can't borrow | W = Wait for Librarian approve");
                System.out.println("Please key status(N/Y/ W) : ");
                String iStatus = scan.next();
                if ( (iStatus == "N")|| (iStatus == "Y")||(iStatus == "W") ){
                    int n = 0;
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getsBookStatus().equals(iStatus)) {
                            if (n == 0) {
                                System.out.println("Classes code.||Classes description||Book code||Book name||Book Author||Book status||Borrow by||Borrow date||Borrow return date");
                                n = n+1;
                            }
                            System.out.println(books.get(i).getsBookClsCde() + "||" +
                                    getClassesDesc(books.get(i).getsBookClsCde()) + "||" +
                                    books.get(i).getsBookCde() + "||" +
                                    books.get(i).getsBookName() + "||" +
                                    books.get(i).getsBookAuthor()+ "||" +
                                    books.get(i).getsBookStatus()+ "||" +
                                    books.get(i).getsBookBorrowBy()+ "||" +
                                    books.get(i).getsBookBorrowDte()+ "||" +
                                    books.get(i).getsBookBorrowRetrn());
                        }
                    }
                }else {
                    System.out.println("Not found status code :"+iStatus+" in system");
                    mainMenuLibrarian(iUserName,iUserPosition);
                    break;
                }
                mainMenuLibrarian(iUserName,iUserPosition);
                break;
            }
            case 4:{  //Approve Borrow book
                System.out.println("Please select book code : ");
                String iBookCde = scan.next();
                if (getBorrowSts(iBookCde)=="W"){
                    setApproveBorrowBook(iBookCde, iUserName);
                }else{
                    System.out.println("Can not approve borrow this book code : "+iBookCde);
                }
                mainMenuLibrarian(iUserName,iUserPosition);
                break;
            }
            case 5:{  //Return book
                System.out.println("=== Return Book === Please key book code : ");
                String iBookCde = scan.next();
                String iStatus = getBorrowSts(iBookCde);
               // String iName = getNameLastNameUser(iUserName);
                if ( (iStatus=="N")){
                    System.out.println("Can not return this book code : "+iBookCde+" because this book no one borrowed.");
                }else{
                    setReceiveReturnBook(iBookCde);
                    mainMenuLibrarian(iUserName,iUserPosition);
                }
                mainMenuLibrarian(iUserName,iUserPosition);
                break;
            }
            case 6:{
                Runtime.getRuntime().exit(0);
                break;
            }
            case  7:{
                keyUserLogin();
                break;
            }
            default:
                System.out.println("*** You choose wrong number menu. ***");
                mainMenuLibrarian(iUserName,iUserPosition);
        }
    }

    private static void mainMenuStaff(String iUserName,String iUserPosition){
        System.out.println("============= Menu For Staff=============");
        System.out.println("1. Show all books");
        System.out.println("2. Search books");
        System.out.println("3. Check status books");
        System.out.println("4. Borrow book");
        System.out.println("5. Return book");
        System.out.println("6. Exit program");
        System.out.println("7. New log in");
        System.out.println("===================================");

        System.out.println("Please select menu : ");
        int key = scan.nextInt();
        menuStaffSelect(key,iUserName,iUserPosition);
    }

    private static void menuStaffSelect(int key,String iUserName,String iUserPosition){
        switch (key){
            case 1:{
                showAllBooks();
                mainMenuStaff(iUserName,iUserPosition);
                break;
            }
            case 2:{//Search books
                menuSearchBook(iUserName,iUserPosition);
                break;
            }
            case 3:{ //Check status books
                System.out.println("Status : N = Can borrow | Y = Can't borrow | W = Wait for Librarian approve");
                System.out.println("Please key status(N/Y/ W) : ");
                String iStatus = scan.next();
                if ( (iStatus == "N")|| (iStatus == "Y")||(iStatus == "W") ){
                    int n = 0;
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getsBookStatus().equals(iStatus)) {
                            if (n == 0) {
                                System.out.println("Classes code.||Classes description||Book code||Book name||Book Author||Book status||Borrow by||Borrow date||Borrow return date");
                                n = n+1;
                            }
                            System.out.println(books.get(i).getsBookClsCde() + "||" +
                                    getClassesDesc(books.get(i).getsBookClsCde()) + "||" +
                                    books.get(i).getsBookCde() + "||" +
                                    books.get(i).getsBookName() + "||" +
                                    books.get(i).getsBookAuthor()+ "||" +
                                    books.get(i).getsBookStatus()+ "||" +
                                    books.get(i).getsBookBorrowBy()+ "||" +
                                    books.get(i).getsBookBorrowDte()+ "||" +
                                    books.get(i).getsBookBorrowRetrn());
                        }
                    }
                }else {
                    System.out.println("Not found status code :"+iStatus+" in system");
                    mainMenuStaff(iUserName,iUserPosition);
                    break;
                }
                mainMenuStaff(iUserName,iUserPosition);
                break;
            }
            case 4:{  //Borrow book
                System.out.println("Please select book code : ");
                String iBookCde = scan.next();
                if (getBorrowSts(iBookCde)=="N"){
                    setBorrowBook(iBookCde, iUserName);
                }else{
                    System.out.println("Can not borrow this book code : "+iBookCde);
                }
                mainMenuStaff(iUserName,iUserPosition);
                break;
            }
            case 5:{  //Return book
                System.out.println("=== Return Book === Please key book code : ");
                String iBookCde = scan.next();
                String iStatus = getBorrowSts(iBookCde);
                String iName = getNameLastNameUser(iUserName);
                if ( ((iStatus=="N")||(iStatus=="W") && (iName != getBorrowBy(iBookCde))) ){
                    System.out.println("Can not return this book code : "+iBookCde+" because this book no one borrowed.");
                }else{
                    setReturnBook(iBookCde);
                }
                mainMenuStaff(iUserName,iUserPosition);
                break;
            }
            case 6:{
                Runtime.getRuntime().exit(0);
                break;
            }
            case  7:{
                keyUserLogin();
                break;
            }
            default:
                System.out.println("*** You choose wrong number menu. ***");
                mainMenuStaff(iUserName,iUserPosition);
        }
    }

    private static void menuSearchBook(String iUserName,String iUserPosition){
        System.out.println("============= Search books By=============");
        System.out.println("1. Classes code");
        System.out.println("2. Book code");
        System.out.println("3. Book Author");
        System.out.println("4. Back to Main Menu");
        System.out.println("5. Sort by Book Author");
        System.out.println("===================================");

        System.out.println("Please select search by : ");
        int iMenu = scan.nextInt();
        switch (iMenu){
            case 1: {  //Search by Classes code
                System.out.println("Please search Classes code : ");
                String iCode = scan.next();
                int n = 0;
                for (int ii = 0; ii < books.size(); ii++) {
                    if (books.get(ii).getsBookClsCde().equals(iCode)) {
                        if (n == 0) {
                            System.out.println("Classes code.||Classes description||Book code||Book name||Book Author||Book status||Borrow by||Borrow date||Borrow return date");
                            n = n+1;
                        }
                        System.out.println(books.get(ii).getsBookClsCde() + "||" +
                                getClassesDesc(books.get(ii).getsBookClsCde()) + "||" +
                                books.get(ii).getsBookCde() + "||" +
                                books.get(ii).getsBookName() + "||" +
                                books.get(ii).getsBookAuthor() + "||" +
                                books.get(ii).getsBookStatus() + "||" +
                                books.get(ii).getsBookBorrowBy() + "||" +
                                books.get(ii).getsBookBorrowDte() + "||" +
                                books.get(ii).getsBookBorrowRetrn() );
                    }
                }
                if (n == 0){
                    System.out.println("Not found Classes code: "+iCode);
                }
                menuSearchBook(iUserName,iUserPosition);
                break;
            }
            case 2:{ //Search by Book code
                System.out.println("Please search Book code : ");
                String iCode = scan.next();
                int n =0;
                for (int ii = 0; ii < books.size(); ii++) {
                    if (books.get(ii).getsBookCde().equals(iCode)) {
                        if (n == 0) {
                            System.out.println("Classes code.||Classes description||Book code||Book name||Book Author||Book status||Borrow by||Borrow date||Borrow return date");
                            n = n+1;
                        }
                        System.out.println(books.get(ii).getsBookClsCde() + "||" +
                                getClassesDesc(books.get(ii).getsBookClsCde()) + "||" +
                                books.get(ii).getsBookCde() + "||" +
                                books.get(ii).getsBookName() + "||" +
                                books.get(ii).getsBookAuthor() + "||" +
                                books.get(ii).getsBookStatus() + "||" +
                                books.get(ii).getsBookBorrowBy() + "||" +
                                books.get(ii).getsBookBorrowDte() + "||" +
                                books.get(ii).getsBookBorrowRetrn() );
                    }
                }
                if (n == 0){
                    System.out.println("Not found Book code : "+iCode);
                }
                menuSearchBook(iUserName,iUserPosition);
                break;
            }
            case 3 :{ //Book Author
                System.out.println("Please search Book Author: ");
                String iName = scan.next();
                int n = 0;
                for (int ii = 0; ii < books.size(); ii++) {
                    if (books.get(ii).getsBookAuthor().contains(iName)) {
                        if (n == 0) {
                            System.out.println("Classes code.||Classes description||Book code||Book name||Book Author||Book status||Borrow by||Borrow date||Borrow return date");
                            n = n+1;
                        }
                        System.out.println(books.get(ii).getsBookClsCde() + "||" +
                                getClassesDesc(books.get(ii).getsBookClsCde()) + "||" +
                                books.get(ii).getsBookCde() + "||" +
                                books.get(ii).getsBookName() + "||" +
                                books.get(ii).getsBookAuthor() + "||" +
                                books.get(ii).getsBookStatus() + "||" +
                                books.get(ii).getsBookBorrowBy() + "||" +
                                books.get(ii).getsBookBorrowDte() + "||" +
                                books.get(ii).getsBookBorrowRetrn() );                    }
                }
                if (n == 0){
                    System.out.println("Not found Book Author : "+iName);
                }
                menuSearchBook(iUserName,iUserPosition);
                break;
            }
            case 4: {
                if (iUserPosition == "Staff") {
                    mainMenuStaff(iUserName, iUserPosition);
                }else
                    mainMenuLibrarian(iUserName, iUserPosition);
                break;
            }
            case 5: {

                break;
            }
            default:
                System.out.println("*** You choose wrong number menu. ***");
                if (iUserPosition == "Staff") {
                    mainMenuStaff(iUserName, iUserPosition);
                }else
                    mainMenuLibrarian(iUserName, iUserPosition);
        }

    }

    private static void showAllBooks(){
        System.out.println("Classes code.||Classes description||Book code||Book name||Book Author||Book status||Borrow by||Borrow date||Borrow return date");
        for(int i=0;i < books.size();i++) {
            System.out.println(books.get(i).getsBookClsCde() + "||" +
                    getClassesDesc(books.get(i).getsBookClsCde()) + "||" +
                    books.get(i).getsBookCde() + "||" +
                    books.get(i).getsBookName() + "||" +
                    books.get(i).getsBookAuthor() + "||" +
                    books.get(i).getsBookStatus() + "||" +
                    books.get(i).getsBookBorrowBy() + "||" +
                    books.get(i).getsBookBorrowDte() + "||" +
                    books.get(i).getsBookBorrowRetrn() );
        }
    }

    private static String getClassesDesc(String iCode){
        String tClassesDesc = "";
        for(int i=0; i < booksShelf.size();i++){
            if (booksShelf.get(i).getsClassesCde().equals(iCode)){
                tClassesDesc = booksShelf.get(i).getsClassesDesc();
                return tClassesDesc;
            }
        }
        return tClassesDesc;
    }

    private static String getNameLastNameUser(String iUserName){
        String tName = "";
        for(int i=0; i < users.size();i++){
            if (users.get(i).getsName().equals(iUserName)){
                tName = users.get(i).getsName()+" "+users.get(i).getsLastName();
                return tName;
            }
        }
        return tName;
    }
    private static String getBorrowSts(String iBookCde){
        String tSts = "";
        for(int i=0; i < books.size();i++){
            if (books.get(i).getsBookCde().equals(iBookCde)){
                tSts = books.get(i).getsBookStatus();
                return tSts;
            }
        }
        return tSts;
    }

    private static String getBorrowBy(String iBookCde){
        String tBy = "";
        for(int i=0; i < books.size();i++){
            if (books.get(i).getsBookCde().equals(iBookCde)){
                tBy = books.get(i).getsBookBorrowBy();
                return tBy;
            }
        }
        return tBy;
    }

    private static void setBorrowBook(String iBookCde,String iUserName){
        System.out.println("Please key number of days borrow (Normal is 7-15 days): ");
        int iDate = scan.nextInt();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE,iDate );
        dt = c.getTime();
        if ((iDate<=15)&&(iDate >= 7)) {
            for (int i = 0; i < books.size();i++){
                if(books.get(i).getsBookCde().equals(iBookCde)){
                    books.get(i).setsBookStatus("W");
                    books.get(i).setsBookBorrowDte(dateFormat.format(new Date()));
                    books.get(i).setsBookBorrowRetrn(dateFormat.format(dt));
                    books.get(i).setsBookBorrowBy(getNameLastNameUser(iUserName));
                }
            }
        }else{
            System.out.println("Can not borrow because number of days beyond");
        }
    }

    private static void setReturnBook(String iBookCde){
        for (int i = 0; i < books.size();i++){
            if(books.get(i).getsBookCde().equals(iBookCde)){
                    books.get(i).setsBookStatus("N");
                    books.get(i).setsBookBorrowDte("-");
                    books.get(i).setsBookBorrowRetrn("-");
                    books.get(i).setsBookBorrowBy("-");
            }
        }
    }

    private static void setApproveBorrowBook(String iBookCde,String iUserName){
            for (int i = 0; i < books.size();i++){
                if(books.get(i).getsBookCde().equals(iBookCde)){
                    books.get(i).setsBookStatus("Y");
                }
            }
    }

    private static void setReceiveReturnBook(String iBookCde){
        for (int i = 0; i < books.size();i++){
            if(books.get(i).getsBookCde().equals(iBookCde)){
                books.get(i).setsBookStatus("N");
                books.get(i).setsBookBorrowDte("-");
                books.get(i).setsBookBorrowRetrn("-");
                books.get(i).setsBookBorrowBy("-");
            }
        }
    }

    private static void defaultData(){
        users.add(new Users("Ilada","Sukumapai","Librarian"));
        users.add(new Users("Wisoot","Nogthed","Staff"));
        users.add(new Users("Aekkaphoom","Neamchaona","Staff"));
        /*--------------------------------------------------------------------------------------------*/
        booksShelf.add(new Bookshelf("000","Generalities"));
        booksShelf.add(new Bookshelf("100","Religion"));
        booksShelf.add(new Bookshelf("200","Language"));
        booksShelf.add(new Bookshelf("300","Technology"));
        /*--------------------------------------------------------------------------------------------*/
        books.add(new Books("000","001","100 Best Books for Children"," Anita"));
        books.add(new Books("000","002","10 Books You Must Read Before You Die","Peter"));
        books.add(new Books("100","101","Siddhartha","Hermann"));
        books.add(new Books("100","102","The Muslim Home","Darussalam"));
        books.add(new Books("200","201","Beginner for english language","Jame"));
        books.add(new Books("200","202","Thai language","Somchai"));
        books.add(new Books("300","301","Delphi7","Sajcha"));
        books.add(new Books("300","302","Java","Bancha"));
    }

}
