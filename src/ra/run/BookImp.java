package ra.run;

import ra.entity.Book;

import java.util.Scanner;

public class BookImp {
    static Book[] arrBook = new Book[100];//Toàn cục
    static int numberBook = 0;

    public static void main(String[] args) {
        //Khởi tạo mảng sách chứa các sách cần quản lý
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***********************MENU**************************");
            System.out.println("1. Nhập thông tin n sách");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("6. Tìm sách theo tên sách");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số luợng sách theo tên tác giả");
            System.out.println("9. Thoát");
            System.out.print("Sự lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookImp.inputListBook(scanner);
                    break;
                case 2:
                    BookImp.calListInterest();
                    break;
                case 3:
                    BookImp.displayListBook();
                    break;
                case 4:
                    BookImp.sortBookByExportPriceASC();
                    break;
                case 5:
                    BookImp.sortBookByInterestDESC();
                    break;
                case 6:
                    BookImp.searchBookByName(scanner);
                    break;
                case 7:
                    BookImp.getNumberBookByYear(scanner);
                    break;
                case 8:
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-9");
            }
        } while (true);
    }

    public static void inputListBook(Scanner scanner) {
        System.out.println("Nhập vào số sách cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrBook[numberBook] = new Book();
            arrBook[numberBook].inputData(scanner, arrBook, numberBook);
            numberBook++;
        }
    }

    public static void calListInterest() {
        for (int i = 0; i < numberBook; i++) {
            arrBook[i].calInterest();
        }
        System.out.println("Đã tính xong lợi nhuận các sách");
    }

    public static void displayListBook() {
        for (int i = 0; i < numberBook; i++) {
            arrBook[i].displayData();
        }
    }

    public static void sortBookByExportPriceASC() {
        for (int i = 0; i < numberBook - 1; i++) {
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getExportPrice() > arrBook[j].getExportPrice()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[j];
                    arrBook[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong sách theo giá bán tăng dần");
    }

    public static void sortBookByInterestDESC() {
        for (int i = 0; i < numberBook - 1; i++) {
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getInterest() < arrBook[j].getInterest()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[j];
                    arrBook[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp xong sách theo lợi nhuận giảm dần");
    }

    public static void searchBookByName(Scanner scanner) {
        System.out.println("Nhập vào tên sách cần tìm kiếm:");
        String bookNameSearch = scanner.nextLine();
        boolean isSearched = false;
        System.out.println("Thông tin các sách tìm kiếm: ");
        for (int i = 0; i < numberBook; i++) {
            if (arrBook[i].getBookName().toLowerCase().contains(bookNameSearch.toLowerCase())) {
                arrBook[i].displayData();
                isSearched = true;
            }
        }
        if (!isSearched) {
            System.out.println("Không tìm thấy sách");
        }
    }

    public static void getNumberBookByYear(Scanner scanner) {
        //Tìm tất cả các năm xuất bản sách và lưu 1 mảng
        int[] yearsBook = new int[numberBook];
        int cnt = 0;
        for (int i = 0; i < numberBook - 1; i++) {
            boolean isExist = false;
            for (int j = i + 1; j < numberBook; j++) {
                if (arrBook[i].getYear() == arrBook[j].getYear()) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                //year của sách thứ i không trùng với các sách khác
                yearsBook[cnt] = arrBook[i].getYear();
                cnt++;
            }
        }
        yearsBook[cnt] = arrBook[numberBook - 1].getYear();
        //Thống kê sách theo năm
        int[] arrNumberBookByYear = new int[cnt+1];
        for (int i = 0; i <= cnt; i++) {
            int numberBookByYear = 0;
            for (int j = 0; j < numberBook; j++) {
                if (yearsBook[i] == arrBook[j].getYear()) {
                    numberBookByYear++;
                }
            }
            arrNumberBookByYear[i] = numberBookByYear;
        }
        //In thống kê
        System.out.println("Thống kê số lượng sách theo năm xuất bản:");
        for (int i = 0; i <= cnt; i++) {
            System.out.printf("%20d", yearsBook[i]);
        }
        System.out.printf("\n");
        for (int i = 0; i <= cnt; i++) {
            System.out.printf("%20d",arrNumberBookByYear[i]);
        }
        System.out.printf("\n");
    }
}
