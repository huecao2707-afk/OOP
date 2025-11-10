import java.util.Scanner;

public class HeThong {
    QuanLyBanHang qlbh;
    
    public void menuChinh(){
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
                    qlbh = new QLSP();
                    qlbh.menuChinh();
                    break;
                case 2:
                     qlbh = new QLNV();
                     qlbh.menuChinh();
                    break;
                case 3:
                    qlbh = new QLKH();
                    qlbh.menuChinh();
                    break;
                case 4:
                    break;
                case 5:
                    qlbh = new QLHD();
                    qlbh.menuChinh();
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Tạm biệt!");
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
        while (choice != 14);
    }
    public static void main(String[] args) {
        HeThong ht = new HeThong();
        QuanLyBanHang.taiDuLieuTuFile();
        ht.menuChinh();
    }
}
