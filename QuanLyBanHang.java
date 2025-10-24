import java.util.Scanner;

public class QuanLyBanHang {
    DSVPP ds_vpp = new DSVPP();
    DSKH ds_kh = new DSKH();
    DSNCC ds_ncc = new DSNCC();
    DSNV ds_nv = new DSNV();
    DSPNH ds_pnh = new DSPNH();
    DSHD ds_hd = new DSHD();
    DSCTHD ds_ctphd = new DSCTHD();
    DSCTPNH ds_ctpnh = new DSCTPNH();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUẢN LÝ BÁN HÀNG =====");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Xem danh sách nhân viên");
            System.out.println("3. Xem danh sách khách hàng");
            System.out.println("4. Xem danh sách nhà cung cấp");
            System.out.println("5. Xem danh sách phiếu nhận hàng ");
            System.out.println("6. Xem danh sách phiếu chi tiết hóa đơn ");
            System.out.println("7. Xem danh sách phiếu hóa đơn");
            System.out.println("8. Xem danh sách phiếu chi tiết hóa đơn");
            System.out.println("9. Tìm kiếm sinh viên theo điểm rèn luyện");
            System.out.println("10. Tìm kiếm sinh viên theo năm tốt nghiệp cao đẳng");
            System.out.println("11. Tìm kiếm sinh viên theo tuổi của sinh viên");
            System.out.println("12. Thống kê (số lượng sinh viên theo nam và nữ)");
            System.out.println("13. Thống kê (số lượng sinh viên theo độ tuổi)");
            System.out.println("14. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            sc.nextLine(); // tránh trôi dòng
            switch (choice) {
                case 1:
                    ds_vpp.nhap();
                    ds_vpp.xuat();
                    break;
 /*               case 2:
                    dssv.xuatdssv();
                    break;
                case 3:
                    dssv.themSinhVien();
                    break;
                case 4:
                    dssv.xoaSinhVien();
                    break;
                case 5:
                    dssv.sua();
                    break;
                case 6:
//                    if (dssv.timKiemSinhVien() != null) sv.xuat();
//                    else System.out.println("Không tìm thấy sinh viên.");
//                    break;
                case 7:
                    dssv.timKiemTheoHo();
                    break;
                case 8:
                    dssv.timKiemTheoTen();
                    break;

                case 9:
                    System.out.print("Nhập điểm rèn luyện sinh viên cần tìm: ");
                    int drl = sc.nextInt();
                    dssv.timKiemTheoDRL(drl);
                    break;
                case 10:
                    System.out.print("Nhập năm tốt nghiệp cao đẳng của sinh viên cần tìm: ");
                    String nam = sc.nextLine();
                    dssv.timKiemTheoNamTotNghiep(nam);
                    break;
                case 11:
                    System.out.print("Nhập số tuổi cần tìm: ");
                    int tuoicantim = sc.nextInt();
                    dssv.timKiemTheoTuoi(tuoicantim);
                    break;
                case 12:
                    dssv.thongKeTheoGioiTinh();
                    break;
                case 13:
                    dssv.thongKeTheoDoTuoi();
                    break;
                case 14:
                    System.out.println("Đã thoát chương trình.");
                    break;*/
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
        while (choice != 14);
    }

    public static void main(String[] args) {
        QuanLyBanHang ql1 = new QuanLyBanHang();
        ql1.menu();
    }
}