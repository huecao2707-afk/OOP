import java.util.Scanner;

public class QLNV extends QuanLyBanHang{
    public void thongKeHieuSuatBanHang() {
        System.out.println("\n--- THỐNG KÊ HIỆU SUẤT BÁN HÀNG CỦA NHÂN VIÊN ---");
        
        int soluongdonhang, tongtiendonhang, max, min;
        double avg;
        
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

        for (int j = 0; j < nHD; j++) {
            HoaDon hd = QuanLyBanHang.dshd.dshd[j];
            if (hd == null) continue; 
            if (hd.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
                
                int tienDon = hd.getTongTien();
                tongTien += tienDon;
                soDon++;
                
                if (tienDon < minTienDon) minTienDon = tienDon;
                if (tienDon > maxTienDon) maxTienDon = tienDon;
            }
        }
        
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