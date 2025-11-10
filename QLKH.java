import java.util.Scanner;

public class QLKH extends QuanLyBanHang {

    @Override
    public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ KHÁCH HÀNG ---");
            System.out.println("1. Xuất danh sách khách hàng ");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Sửa thông tin khách hàng");
            System.out.println("5. Tìm Kiếm khách hàng");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    dskh.xuatDanhSachKhachHang();
                    break;
                case 2:
                    dskh.themKhachHang();
                    break;
                case 3:
                    System.out.println("Nhập mã của khách hàng cần xóa: ");
                    String ma = sc.nextLine();

                    dskh.xoaKhachHang(ma);
                    break;
                case 4:
                     dskh.sua();
                    break;
                case 5:
                    dskh.timKiemTheoMa();
                    break;
            }
        } while (choice != 0);
    }

}