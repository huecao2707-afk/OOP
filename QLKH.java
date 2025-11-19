import java.util.Scanner;

public class QLKH extends QuanLyBanHang {

    public void thongKeKhachHang() {
        System.out.println("\n--- THỐNG KÊ DOANH SỐ CỦA KHÁCH HÀNG ---");

        // 1. Lấy dữ liệu cần thiết (Sử dụng trực tiếp QuanLyBanHang.ds...)
        int nKH = QuanLyBanHang.dskh.getN(); // Giả định getN() tồn tại
        
        int nHD = QuanLyBanHang.dshd.getN(); // Giả định getN() tồn tại
        
        String line = "+------------+---------------------------+-----------------+-----------------+-----------------+-----------------+-----------------+";
        String format = "| %-10s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s |\n";

        System.out.println(line);
        System.out.printf(format, "Mã KH", "Họ và Tên", "Tổng Tiền Mua", "Số Đơn Hàng", "Max Tiền/Đơn", "Min Tiền/Đơn", "Avg Tiền/Đơn");
        System.out.println(line);
        
        int khCoDonHang = 0;

        // VÒNG LẶP CHÍNH: Duyệt qua từng Khách hàng
        for (int i = 0; i < nKH; i++) { 
            KhachHang kh = QuanLyBanHang.dskh.dskh[i];
            if (kh == null) continue; 

            long tongTien = 0;
            int soDon = 0;
            int minTienDon = Integer.MAX_VALUE;
            int maxTienDon = Integer.MIN_VALUE;

            // VÒNG LẶP CON: Duyệt qua toàn bộ Hóa Đơn (DSHD) để tìm các đơn hàng của KH hiện tại
            for (int j = 0; j < nHD; j++) {
                HoaDon hd = QuanLyBanHang.dshd.dshd[j];
                if (hd == null) continue; 
                
                // So sánh Mã KH của Hóa đơn với Mã KH hiện tại
                if (hd.getMaKH().equalsIgnoreCase(kh.getMaKH())) {
                    
                    int tienDon = hd.getTongTien();
                    tongTien += tienDon;
                    soDon++;
                    
                    // Tìm Min/Max của các đơn hàng này
                    if (tienDon < minTienDon) minTienDon = tienDon;
                    if (tienDon > maxTienDon) maxTienDon = tienDon;
                }
            }
            
            // 3. Xuất kết quả nếu Khách hàng có đơn hàng
            if (soDon > 0) {
                khCoDonHang++;
                String tenKH = kh.getHo() + " " + kh.getTen();
                double avgTienDon = (double)tongTien / soDon;

                System.out.printf("| %-10s | %-25s | %,15d | %,15d | %,15d | %,15d | %,15.2f |\n", 
                                    kh.getMaKH(), tenKH, tongTien, soDon, maxTienDon, minTienDon, avgTienDon);
            }
        }
        
        System.out.println(line);
        
        if (khCoDonHang == 0) {
            System.out.println("❌ Không có khách hàng nào có dữ liệu mua hàng để thống kê.");
        }
    }

    @Override
    public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ KHÁCH HÀNG ---");
            System.out.println("1. Xuất danh sách khách hàng ");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Xóa khách hàng");
            System.out.println("4. Sửa thông tin khách hàng");
            System.out.println("5. Tìm Kiếm khách hàng");
            System.out.println("6. Thống kê khách hàng"); // <<< THÊM MỤC 6
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
                    System.out.print("Nhập mã của khách hàng cần xóa: ");
                    String ma = sc.nextLine();
                    dskh.xoaKhachHang(ma);
                    break;
                case 4:
                    dskh.sua();
                    break;
                case 5:
                    dskh.timKiemTheoMa();
                    break;
                case 6: // <<< THÊM CASE 6
                    thongKeKhachHang();
                    break;
            }
        } while (choice != 0);
    }
}