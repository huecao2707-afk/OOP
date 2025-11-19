import java.util.Scanner;

public class QLNV extends QuanLyBanHang{
    // Trong QLNV.java (Thêm phương thức mới này)
    public void thongKeHieuSuatBanHang() {
        System.out.println("\n--- THỐNG KÊ HIỆU SUẤT BÁN HÀNG CỦA NHÂN VIÊN ---");
        
        int soluongdonhang, tongtiendonhang, max, min;
        double avg;
        
        /*
        duyệt qua toàn bộ mảng nhân viên với từng nhân viên mik duyệt trong mảng đơn hàng để lấy tổng tiền và số lượng đơn tính max min avg
        */
        int nNV = QuanLyBanHang.dsnv.getN();
        int nHD = QuanLyBanHang.dshd.getN();
        String line = "+------------+---------------------------+-----------------+-----------------+-----------------+-----------------+-----------------+";
        String format = "| %-10s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s |\n";

        System.out.println(line);
        System.out.printf(format, "Mã NV", "Họ và Tên", "Tổng Tiền Bán", "Số Đơn Hàng", "Max Tiền/Đơn", "Min Tiền/Đơn", "Avg Tiền/Đơn");
        System.out.println(line);
        int nvCoBanHang = 0;
        for (int i = 0; i < nNV; i++) { 
            NhanVien nv = QuanLyBanHang.dsnv.dsnv[i];
            if (nv == null) continue; 

            long tongTien = 0;
            int soDon = 0;
            int minTienDon = Integer.MAX_VALUE;
            int maxTienDon = Integer.MIN_VALUE;

        // VÒNG LẶP CON: Duyệt qua toàn bộ Hóa Đơn (Dùng nHD)
        for (int j = 0; j < nHD; j++) {
            HoaDon hd = QuanLyBanHang.dshd.dshd[j];
            if (hd == null) continue; 
            // So sánh Mã NV của Hóa đơn với Mã NV hiện tại
            if (hd.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
                
                int tienDon = hd.getTongTien();
                tongTien += tienDon;
                soDon++;
                
                // Tìm Min/Max của các đơn hàng này
                if (tienDon < minTienDon) minTienDon = tienDon;
                if (tienDon > maxTienDon) maxTienDon = tienDon;
            }
        }
        
        // Xuất kết quả nếu Nhân viên có đơn hàng
        if (soDon > 0) {
            nvCoBanHang++;
            String tenNV = nv.getHo() + " " + nv.getTen();
            double avgTienDon = (double)tongTien / soDon;

            System.out.printf("| %-10s | %-25s | %,15d | %,15d | %,15d | %,15d | %,15.2f |\n", 
                                nv.getMaNV(), tenNV, tongTien, soDon, maxTienDon, minTienDon, avgTienDon);
        }
    }
    
        System.out.println(line);
        
        if (nvCoBanHang == 0) {
            System.out.println("❌ Không có nhân viên nào có dữ liệu bán hàng để thống kê.");
        }       
    }   
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
            System.out.println("6. Thống kê nhân viên");
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
                case 6:
                    thongKeHieuSuatBanHang();
                    break;
            }
        } while (choice != 0);
    }

}
