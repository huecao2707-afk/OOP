import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class QLHD extends QuanLyBanHang {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);
	public void themHDVaChiTietHD() {
        System.out.println("\n--- THÊM HÓA ĐƠN + CHI TIẾT HÓA ĐƠN ---");
        
        // 1) Nhập Mã Hóa Đơn (Không đổi)
        String mahd;
        do {
            System.out.print("Nhập Mã hóa đơn: ");
            mahd = sc.nextLine().trim();
            if (mahd.isEmpty()) {
                System.out.println("❌ Mã hóa đơn không được trống.");
                continue;
            }
            if (!QuanLyBanHang.dshd.maDuyNhat(mahd)) {
                System.out.println("❌ Mã hóa đơn đã tồn tại. Nhập lại.");
                mahd = "";
            }
        } while (mahd.isEmpty());
        
        // 2) Nhập Mã Khách Hàng (Vẫn cần 'kh' để xác thực)
        KhachHang kh = null;
        String makh; // Biến lưu Mã KH (String)
        do {
            System.out.print("Nhập Mã Khách hàng (VD: KH001): ");
            makh = sc.nextLine().trim();
            kh = QuanLyBanHang.dskh.timKiemTheoMa(makh);
            if (kh == null) {
                System.out.println("❌ Không tìm thấy khách hàng. Thêm khách hàng mới trước khi tiếp tục.");
                QuanLyBanHang.dskh.themKhachHang();
            }
        } while (kh == null);
        
        // 3) Nhập Mã Nhân Viên (Vẫn cần 'nv' để xác thực)
        NhanVien nv = null;
        String manv; // Biến lưu Mã NV (String)
        do {
            System.out.print("Nhập Mã Nhân viên lập đơn (VD: NV001): ");
            manv = sc.nextLine().trim();
            nv = QuanLyBanHang.dsnv.timKiemTheoMa(manv);
            if (nv == null) {
                System.out.println("❌ Không tìm thấy nhân viên. Nhập lại.");
            }
        } while (nv == null);
        
        // 4) SỬA LỖI: Nhập Ngày lập hóa đơn (với kiểu LocalDate)
        LocalDate ngaylocaldate = null;
        while(true) {
            System.out.print("Nhập Ngày lập HĐ (VD: dd/mm/yyyy): ");
            String ngaystr = sc.nextLine().trim();
            try {
                // Chuyển đổi String sang LocalDate
                ngaylocaldate= LocalDate.parse(ngaystr, DATE_FORMATTER);
                break; // Thoát lặp nếu thành công
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
            }
        }
        
        // Khởi tạo Hóa đơn
        HoaDon hoadon = new HoaDon();
        hoadon.setMaHD(mahd);
        hoadon.setMaNV(manv); 
        hoadon.setMaKH(makh); 
        hoadon.setNgayLapHD(ngaylocaldate); 
        
        int tongtien = 0;
        
        // 5) Nhập các Chi tiết hóa đơn
        System.out.print("Nhập số lượng mặt hàng: ");
        int soct = 0;
        try {
            soct = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception ignore) { soct = 0; }
        
        for (int i = 0; i < soct; i++) {
            System.out.println("--- Mặt hàng #" + (i + 1) + " ---");
            
            // 5.1) Chọn sản phẩm theo mã (Không đổi)
            VanPhongPham sp = null;
            do {
                System.out.print("  > Nhập Mã sản phẩm: ");
                String masp = sc.nextLine().trim();
                sp = QuanLyBanHang.dsvpp.timMaSP(masp);
                if (sp == null) {
                    System.out.println("  ❌ Không tìm thấy sản phẩm. Nhập lại.");
                }
            } while (sp == null);
            
            // 5.2) Kiểm tra số lượng tồn (Không đổi)
            int soluong;
            while (true) {
            System.out.printf("  > Tồn kho: %d | Đơn giá: %,d%n", sp.getSoLuong(), sp.getDonGia());
            System.out.print("  > Nhập Số lượng mua: ");
            
            try {
                // 1. Dùng int bình thường
                soluong = sc.nextInt(); 
            } catch (java.util.InputMismatchException e) {
                // Bắt lỗi nếu người dùng nhập "abc"
                System.out.println("  ❌ Số lượng không hợp lệ. Nhập lại.");
                sc.nextLine(); // QUAN TRỌNG: Dọn dẹp input sai
                continue;
            }
            
            // 2. Dọn dẹp phím Enter (ký tự '\n') còn sót lại
            sc.nextLine(); 

            // (Các bước kiểm tra logic còn lại)
            if (soluong <= 0) {
                System.out.println("  ❌ Số lượng phải > 0.");
                continue;
            }
            if (soluong > sp.getSoLuong()) {
                System.out.println("  ❌ Vượt quá tồn kho. Nhập lại.");
                continue;
            }
            
            // Nếu mọi thứ OK, thoát vòng lặp
            break;
        }
            
            // 5.3) Tính thành tiền, khởi tạo CTHD
            int dongia = sp.getDonGia();
            int thanhtien = dongia * soluong;
            tongtien += thanhtien;
            QuanLyBanHang.dsvpp.capNhatSLSP(sp.getMaSP(), -soluong);
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setMaHD(mahd);
            ct.setMaSP(sp.getMaSP()); 
            ct.setDonGia(dongia);
            ct.setSoLuong(soluong);
            ct.setThanhTien(thanhtien);
            
            QuanLyBanHang.dscthd.themMotChiTiet(ct);
        }
        
        // 6) Cập nhật tổng tiền và lưu hóa đơn (Không đổi)
        hoadon.setTongTien(tongtien);
        QuanLyBanHang.dshd.themMotHoaDon(hoadon);
        
        hoadon.xuatHoaDonDayDu();
        System.out.println("Cảm ơn quý khách và hẹn gặp lại!");

    }

    public void xoaHoaDon(){
        System.out.print("Nhập mã hóa đơn cần xóa: ");
        String mahd = sc.nextLine();
        dshd.xoaHoaDon(mahd);
        dscthd.xoaChiTiet(mahd);
    }

    public void timKiemHoaDon(){
        System.out.print("Nhập mã hóa đơn: ");
        String ma = sc.nextLine();
        HoaDon kq = QuanLyBanHang.dshd.timKiemTheoMa(ma); 
    
        if (kq == null) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + ma);
        }    
    }

    public void suaHoaDon() {
    System.out.println("\n--- SỬA THÔNG TIN HÓA ĐƠN ---");
    System.out.print("Nhập mã hóa đơn cần sửa: ");
    String mahd = sc.nextLine().trim();

    // 1. Tìm Hóa đơn
    HoaDon hdCanSua = QuanLyBanHang.dshd.timKiemTheoMa(mahd);

    if (hdCanSua == null) {
        System.out.println("❌ Không tìm thấy hóa đơn có mã: " + mahd);
        return;
    }
    
    // Xuất thông tin cũ (Hàm timKiemTheoMa đã tự xuất)
    System.out.println("\n✅ Đã tìm thấy hóa đơn. Vui lòng chọn thông tin cần sửa:");

    // 2. Chọn thông tin cần sửa
    System.out.println("1. Sửa Ngày Lập Hóa Đơn (Hiện tại: " + hdCanSua.getNgayLapHDString() + ")");
    System.out.println("2. Sửa Mã Nhân Viên Lập (Hiện tại: " + hdCanSua.getMaNV() + ")");
    System.out.println("3. Sửa Mã Khách Hàng (Hiện tại: " + hdCanSua.getMaKH() + ")");
    System.out.println("0. Hủy");
    System.out.print("Chọn: ");
    
    int choice = 0;
    try {
        choice = sc.nextInt();
    } catch (java.util.InputMismatchException e) { 
        // Bắt lỗi nếu người dùng nhập "abc"
        System.out.println("❌ Lựa chọn không hợp lệ.");
        sc.nextLine(); 
        return; 
    }
    sc.nextLine(); // Tránh trôi lệnh

    switch (choice) {
        case 1:
            // Sửa Ngày Lập HĐ
            LocalDate ngayMoi = null;
            while (true) {
                System.out.print("Nhập Ngày lập HĐ MỚI (dd/mm/yyyy): ");
                String ngaystr = sc.nextLine().trim();
                try {
                    ngayMoi = LocalDate.parse(ngaystr, DATE_FORMATTER);
                    hdCanSua.setNgayLapHD(ngayMoi);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
                }
            }
            break;
        case 2:
            // Sửa Mã Nhân Viên
            String manvMoi;
            NhanVien nvMoi = null;
            do {
                System.out.print("Nhập Mã Nhân viên MỚI: ");
                manvMoi = sc.nextLine().trim();
                // Tận dụng QuanLyBanHang.dsnv để xác thực
                nvMoi = QuanLyBanHang.dsnv.timKiemTheoMa(manvMoi);
                if (nvMoi == null) {
                    System.out.println("❌ Không tìm thấy nhân viên. Nhập lại.");
                } else {
                    hdCanSua.setMaNV(manvMoi);
                    break;
                }
            } while (true);
            break;
        case 3:
            // Sửa Mã Khách Hàng
            String makhMoi;
            KhachHang khMoi = null;
            do {
                System.out.print("Nhập Mã Khách hàng MỚI: ");
                makhMoi = sc.nextLine().trim();
                // Tận dụng QuanLyBanHang.dskh để xác thực
                khMoi = QuanLyBanHang.dskh.timKiemTheoMa(makhMoi);
                if (khMoi == null) {
                    System.out.println("❌ Không tìm thấy khách hàng. Nhập lại.");
                } else {
                    hdCanSua.setMaKH(makhMoi);
                    break;
                }
            } while (true);
            break;
        case 0:
            System.out.println("Hủy thao tác sửa.");
            return;
        default:
            System.out.println("Lựa chọn không hợp lệ.");
            return;
    }

    // 3. Ghi lại toàn bộ file sau khi sửa
    QuanLyBanHang.dshd.ghiLaiToanBoFileHoaDon();
    System.out.println("\n✅ Cập nhật hóa đơn thành công. Chi tiết mới:");
    hdCanSua.xuatHoaDonDayDu();
}
    public void menuConXuat(){
        int choice = 0;
        do{
            System.out.println("\n1. Xuất danh sách hóa đơn ");
            System.out.println("2. Xuất danh sách chi tiết hóa đơn ");
            System.out.println("3. Thoát ");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    dshd.xuat();
                    break;
                case 2:
                    dscthd.xuat();
                    break;
            }
        }
        while (choice != 3);
    }
    public void menuChinh() {
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ HÓA ĐƠN ---");
            System.out.println("1. Xuất danh sách hóa đơn - chi tiết hóa đơn ");
            System.out.println("2. Thêm hóa đơn mới");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Sửa hóa đơn");
            System.out.println("5. Tìm Kiếm hóa đơn");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    menuConXuat();
                    break;
                case 2:
                    themHDVaChiTietHD();
                    break;
                case 3:
                    xoaHoaDon();
                    break;
                case 4:
                    suaHoaDon();
                    break;
                case 5:
                    timKiemHoaDon();
                    break;
            }
        } while (choice != 0);
    }
}
