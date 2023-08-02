package ra.entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, float interest, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(Scanner scanner, Book[] arrBook, int numberBook) {
        //1. Mã sách duy nhất
        System.out.println("Nhập vào mã sách");
        boolean checkBookId = true;
        do {
            this.bookId = scanner.nextLine();
            //Kiểm tra mã sách là duy nhất
            boolean isBookIdExist = false;
            for (int i = 0; i < numberBook; i++) {
                if (arrBook[i].bookId.equals(this.bookId)) {
                    isBookIdExist = true;
                    break;
                }
            }
            if (!isBookIdExist) {
                //Mã sách không bị trùng lặp
                break;
            } else {
                //Mã sách đã bị trùng lặp
                System.err.println("Mã sách đã tồn tại, vui lòng nhập lại");
            }
        } while (checkBookId);
        //2. Tên sách không trùng lặp, gồm 4 ký tự, bắt đầu là B
        boolean checkBookName = true;
        System.out.println("Nhập vào tên sách:");
        do {
            this.bookName = scanner.nextLine();
            boolean isBookNameExist = false;
            for (int i = 0; i < numberBook; i++) {
                if (arrBook[i].bookName.equals(this.bookName)) {
                    isBookNameExist = true;
                    break;
                }
            }
            if (isBookNameExist) {
                //Tên sách bị trùng lặp
                System.err.println("Tên sách đã tồn tại, vui lòng nhập lại");
            } else {
                //Tên sách không bị trùng lặp
                //Kiểm tra độ dài tên sách = 4
                if (this.bookName.length() == 4) {
                    //Kiểm tra tên sách bắt đầu là ký tự B
                    if (this.bookName.charAt(0) == 'B') {
                        break;
                    } else {
                        System.err.println("Tên sách bắt đầu là ký tự B, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Tên sách phải gồm 4 ký tự, vui lòng nhập lại");
                }
            }
        } while (checkBookName);
        //3. Giá nhập >0
        boolean checkImportPrice = true;
        System.out.println("Nhập vào giá nhập của sách:");
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.err.println("Giá sách phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (checkImportPrice);
        //4. Giá xuất >=130% giá nhập
        boolean checkExportPrice = true;
        System.out.println("Nhập vào giá xuất của sách:");
        do {
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if (this.exportPrice >= this.importPrice * 1.3) {
                break;
            } else {
                System.err.println("Giá xuất phải có giá trị lớn hơn 30% so với giá nhập, vui lòng nhập lại");
            }
        } while (checkExportPrice);
        //5. Tác giả có từ 6-50 ký tự
        boolean checkAuthor = true;
        System.out.println("Nhập vào tên tác giả:");
        do {
            this.author = scanner.nextLine();
            if (this.author.length() >= 6 && this.author.length() <= 50) {
                break;
            } else {
                System.err.println("Tên tác giả từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (checkAuthor);
        //6. Năm xuất bản sau năm 2000
        boolean checkYear = true;
        System.out.println("Nhập vào năm xuất bản của sách:");
        do {
            this.year = Integer.parseInt(scanner.nextLine());
            if (this.year >= 2000) {
                break;
            } else {
                System.err.println("Năm xuất bản phải sau năm 2000, vui lòng nhập lại");
            }
        } while (checkYear);
    }

    public void displayData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Tên tác giả: %s\n", this.bookId, this.bookName, this.author);
        System.out.printf("Giá nhập: %.1f - Giá xuất: %.1f - Lợi nhuận: %.1f - Năm xuất bản: %d\n", this.importPrice, this.exportPrice, this.interest, this.year);
    }

    public void calInterest() {
        this.interest = this.exportPrice - this.importPrice;
    }
}
