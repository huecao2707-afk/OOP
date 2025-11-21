import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class QLTK extends QuanLyBanHang {

    public void tongQuan(){
        System.out.println("\n--- TỔNG QUAN ---");
        String line = "+------------+-----------------+-----------------+-----------------+-----------------+-----------------+";
        String format = "| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s |\n";

        System.out.println(line);
        System.out.printf(format, "Doanh Thu", "Tổng SP", "Tổng Tồn Kho", "Sắp Hết Hàng", "Hết Hàng", "Số Đơn Hàng");
        System.out.println(line);

        long tongdoanhthu = 0;
        int tongsanpham = 0;
        int tongtonkho = 0;
        int saphethang = 0;
        int hethang = 0;

        int nHD = QuanLyBanHang.dshd.getN();
        for(int i = 0; i < nHD; i++){
            tongdoanhthu += QuanLyBanHang.dshd.dshd[i].getTongTien();
        }

        if (QuanLyBanHang.dsvpp != null) {
            tongtonkho = QuanLyBanHang.dsvpp.tinhTongTonKho(); 
            saphethang = QuanLyBanHang.dsvpp.demSanPhamSapHetHang(20);
            hethang = QuanLyBanHang.dsvpp.demSanPhamHetHang();
            tongsanpham = QuanLyBanHang.dsvpp.tongSoLuongSanPham();
        }

        int sodonhang = QuanLyBanHang.dshd.getN();

        System.out.printf(format, tongdoanhthu, tongsanpham, tongtonkho, saphethang, hethang, sodonhang);
        System.out.println(line);
    }
    
    public void thongKeDoanhThuNgay() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ngayChon = null;
        
        System.out.println("\n--- THỐNG KÊ DOANH THU THEO NGÀY CỤ THỂ ---");
        
        while (ngayChon == null) {
            System.out.print("Nhập ngày cần thống kê (dd/MM/yyyy): ");
            String ngaystr = sc.nextLine().trim();
            try {
                ngayChon = LocalDate.parse(ngaystr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
            }
        }
    
        long tongDoanhThuNgay = 0;
        
        int nHD = QuanLyBanHang.dshd.getN();
        for (int i = 0; i < nHD; i++) {
            HoaDon hd = QuanLyBanHang.dshd.dshd[i];
            
            if (hd.getNgayLapHD() != null && hd.getNgayLapHD().isEqual(ngayChon)) {
                tongDoanhThuNgay += hd.getTongTien();
            }
        }
        
        System.out.println("------------------------------------------------------------------");
        System.out.printf("✅ Tổng doanh thu cho ngày %s là: %,d VNĐ\n", 
                        ngayChon.format(formatter), tongDoanhThuNgay);
        System.out.println("------------------------------------------------------------------");
    }

    public void thongKeTongHopTheoQuy() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- THỐNG KÊ TỔNG HỢP DOANH THU/CHI PHÍ THEO QUÝ ---");
        System.out.print("Nhập năm cần thống kê: ");
        int nam;
        try {
            nam = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Năm không hợp lệ.");
            sc.nextLine();
            return;
        }

        long[][] quyStats = new long[5][3]; 

        int nHD = QuanLyBanHang.dshd.getN();
        for (int i = 0; i < nHD; i++) {
            HoaDon hd = QuanLyBanHang.dshd.dshd[i];
            LocalDate ngayLap = hd.getNgayLapHD();
            long tongTien = hd.getTongTien();

            if (ngayLap.getYear() == nam) {
                int thang = ngayLap.getMonthValue();
                int quy = 0;
                
                if (thang >= 1 && thang <= 3) {
                    quy = 1;
                } else if (thang >= 4 && thang <= 6) {
                    quy = 2;
                } else if (thang >= 7 && thang <= 9) {
                    quy = 3;
                } else if (thang >= 10 && thang <= 12) {
                    quy = 4;
                }
                
                if (quy != 0) {
                    quyStats[quy][0] += tongTien;
                }
            }
        }
        
        if (QuanLyBanHang.dspnh != null) {
            int nPNH = QuanLyBanHang.dspnh.getN();
            for (int i = 0; i < nPNH; i++) {
                PhieuNhapHang pnh = QuanLyBanHang.dspnh.dspnh[i];
                LocalDate ngayLap = pnh.getNgayNhan();
                long tongChiPhi = pnh.getTongTien();

                if (ngayLap.getYear() == nam) {
                    int thang = ngayLap.getMonthValue();
                    int quy = 0;
                    
                    if (thang >= 1 && thang <= 3) {
                        quy = 1;
                    } else if (thang >= 4 && thang <= 6) {
                        quy = 2;
                    } else if (thang >= 7 && thang <= 9) {
                        quy = 3;
                    } else if (thang >= 10 && thang <= 12) {
                        quy = 4;
                    }
                    
                    if (quy != 0) {
                        quyStats[quy][1] += tongChiPhi;
                    }
                }
            }
        } else {
            System.out.println("Cảnh báo: Danh sách Phiếu Nhập Hàng chưa được khởi tạo. Không thể tính Chi Phí.");
        }

        long tongDT = 0, tongCP = 0, tongLN = 0;
        for (int i = 1; i <= 4; i++) {
            quyStats[i][2] = quyStats[i][0] - quyStats[i][1];
            tongDT += quyStats[i][0];
            tongCP += quyStats[i][1];
            tongLN += quyStats[i][2];
        }
        
        quyStats[0][0] = tongDT;
        quyStats[0][1] = tongCP;
        quyStats[0][2] = tongLN;

        String line = "+-----------------------+-----------------+-----------------+-----------------+-----------------+-----------------+";
        String format = "| %-21s | %-15s | %-15s | %-15s | %-15s | %-15s |\n";

        System.out.println(line);
        System.out.printf(format, "CHỈ TIÊU (" + nam + ")", "Quý 1", "Quý 2", "Quý 3", "Quý 4", "Tổng Cộng (TC)");
        System.out.println(line);
        
        System.out.printf(format, "1. Doanh Thu Hóa Đơn", 
            String.format("%,d", quyStats[1][0]), 
            String.format("%,d", quyStats[2][0]), 
            String.format("%,d", quyStats[3][0]), 
            String.format("%,d", quyStats[4][0]), 
            String.format("%,d", quyStats[0][0]));
        System.out.println(line);
        
        System.out.printf(format, "2. Chi phí Nhập Hàng", 
            String.format("%,d", quyStats[1][1]), 
            String.format("%,d", quyStats[2][1]), 
            String.format("%,d", quyStats[3][1]), 
            String.format("%,d", quyStats[4][1]), 
            String.format("%,d", quyStats[0][1]));
        System.out.println(line);
        
        System.out.printf(format, "3. Lợi Nhuận (1 - 2)", 
            String.format("%,d", quyStats[1][2]), 
            String.format("%,d", quyStats[2][2]), 
            String.format("%,d", quyStats[3][2]), 
            String.format("%,d", quyStats[4][2]), 
            String.format("%,d", quyStats[0][2]));
        System.out.println(line);
}

    public void thongKeDoanhThuKhoangThoiGian() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ngayBatDau = null;
        LocalDate ngayKetThuc = null;
        
        System.out.println("\n--- THỐNG KÊ DOANH THU THEO KHOẢNG THỜI GIAN ---");
    
        while (ngayBatDau == null) {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String start = sc.nextLine().trim();
            try {
                ngayBatDau = LocalDate.parse(start, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
            }
        }
    
        while (ngayKetThuc == null) {
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            String end = sc.nextLine().trim();
            try {
                ngayKetThuc = LocalDate.parse(end, formatter);
                if (ngayKetThuc.isBefore(ngayBatDau)) {
                    System.out.println("Ngày kết thúc phải sau hoặc bằng ngày bắt đầu. Vui lòng nhập lại.");
                    ngayKetThuc = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
            }
        }
    
        long tongDoanhThu = 0;
        
        int nHD = QuanLyBanHang.dshd.getN();
        for (int i = 0; i < nHD; i++) {
            LocalDate ngayLapHD = QuanLyBanHang.dshd.dshd[i].getNgayLapHD(); 
            
            if (!ngayLapHD.isBefore(ngayBatDau) && !ngayLapHD.isAfter(ngayKetThuc)) {
                tongDoanhThu += QuanLyBanHang.dshd.dshd[i].getTongTien();
            }
        }
        
        System.out.println("------------------------------------------------------------------");
        System.out.printf("✅ Tổng doanh thu từ %s đến %s là: %,d VNĐ\n", 
                        ngayBatDau.format(formatter), ngayKetThuc.format(formatter), tongDoanhThu);
        System.out.println("------------------------------------------------------------------");
    }

    @Override public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ THỐNG KÊ ---");
            System.out.println("1. Tổng quan ");
            System.out.println("2. Thống kê doanh thu theo quý ");
            System.out.println("3. Thống kê doanh thu ngày");
            System.out.println("4. Thống kê doanh thu theo khoảng thời gian");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    tongQuan();
                    break;
                case 2:
                    thongKeTongHopTheoQuy();
                    break;
                case 3:
                    thongKeDoanhThuNgay();
                    break;
                case 4:
                    thongKeDoanhThuKhoangThoiGian();
                    break;
            }
        } while (choice != 0);
    }

}