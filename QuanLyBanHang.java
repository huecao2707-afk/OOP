import java.util.Scanner;
public class QuanLyBanHang {
    static DSVPP dsvpp;
    static DSKH dskh ;
    static DSNCC dsncc;
    static DSNV dsnv;
    static DSPNH dspnh;
    static DSHD dshd;
    static DSCTHD dsctphd ;
    static DSCTPNH dsctpnh;
    
    public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUẢN LÝ BÁN HÀNG =====");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý khách hàng");
            System.out.println("4. Quản lý nhà cung cấp");
            System.out.println("5. Quản lý hóa đơn- Chi Tiết Hóa Đơn");
            System.out.println("6. Quản lý phiếu nhận hàng - Chi Tiết Phiếu Nhận Hàng");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            sc.nextLine(); // tránh trôi dòn
            switch (choice) {
                case 1:
                    menuConQuanLySanPham();
                    break;
                // case 2:
                //     menuConQuanLyNhanVien();
                //     break;
                // case 3:
                //     menuConQuanLyKhachHang();
                //     break;
                // case 4:
                //     menuConQuanLyNhaCungCap();
                //     break;
                // case 5:
                //     menuConQuanLyHoaDon();
                //     break;
                // case 6:
                //     menuConQuanLyPhieuNhanHang();
                //     break;
                case 7:
                    System.out.println("Tạm biệt!");
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
        while (choice != 14);
    }
    public void menuConQuanLySanPham() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ SẢN PHẨM ---");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Xuất danh sách sản phẩm ");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dsvpp.themSanPham();
                    break;
                case 2:
                    // xoa(); 
                    break;
                case 3:
                    // sua();
                    break;
                case 4:
                    dsvpp = new DSVPP();
                    dsvpp.docFileVPP();
                    dsvpp.xuat();
                     break;
            }
        } while (choice != 0);
    }
    public static void main(String[] args) {
        QuanLyBanHang ql1 = new QuanLyBanHang();
        ql1.menuChinh();
    }
}
