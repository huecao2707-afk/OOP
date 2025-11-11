import java.util.Scanner;
class QLNCC extends QuanLyBanHang{
    @Override
    public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ NHÀ CUNG CẤP ---");
            System.out.println("1. Xuất danh sách nhà cung cấp ");
            System.out.println("2. Thêm nhà cung cấp mới");
            System.out.println("3. Xóa nhà cung cấp");
            System.out.println("4. Sửa thông tin nhà cung cấp");
            System.out.println("5. Tìm Kiếm nhà cung cấp");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dsncc.xuat();
                    break;
                case 2:
                    dsncc.them();
                    break;
                case 3:
                    dsncc.xoa();
                    break;
                case 4:
                    dsncc.sua();
                    break;
                case 5:
                    dsncc.timKiemTheoMa();
                    break;
            }
        } while (choice != 0);
    }
}
