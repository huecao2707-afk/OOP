import java.util.Scanner;

public class QLNV extends QuanLyBanHang{
    @Override public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ NHÂN VIÊN ---");
            System.out.println("1. Xuất danh sách nhân viên ");
            System.out.println("2. Thêm nhân viên mới");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Sửa thông tin nhân viên");
            System.out.println("5. Tìm Kiếm nhân viên");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    dsnv.xuat();
                    break;
                case 2:
                    dsnv.themNhanVien();
                    break;
                case 3:
                    System.out.println("Nhập mã của nhân viên cần xóa: ");
                    String ma = sc.nextLine();
                    dsnv.xoaSinhVien(ma);
                    break;
                case 4:
                    dsnv.sua();
                    break;
                case 5:
                    dsnv.timKiemTheoMa();
                    break;
            }
        } while (choice != 0);
    }

}
