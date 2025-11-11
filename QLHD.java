import java.util.Scanner;

public class QLHD extends QuanLyBanHang {
// Trong file QLHD.java (Hàm LienKetDuLieuHoaDon)

    public void LienKetDuLieuHoaDon() {
        System.out.println("--- Đang liên kết dữ liệu Hóa đơn ---");

        // Dùng .length để lặp qua kích thước VẬT LÝ của mảng
        // KHÔNG CẦN dshd.n NỮA, vì n là private
        for (int i = 0; i < dshd.dshd.length; i++) { 
            
            HoaDon hd = dshd.dshd[i];
            
            // KIỂM TRA QUAN TRỌNG: Đảm bảo phần tử mảng KHÔNG phải là null
            if (hd == null) {
                // Khi gặp null, tức là đã hết các Hóa đơn thực tế được lưu.
                // Chúng ta có thể thoát vòng lặp để tránh lặp thừa.
                break; 
            }

            // 1. LIÊN KẾT NHÂN VIÊN
            String maNVCanTim = hd.getMaNV_string();
            NhanVien nvTimDuoc = QuanLyBanHang.dsnv.timKiemTheoMa(maNVCanTim); 
            if (nvTimDuoc != null) {
                hd.setNhanVien(nvTimDuoc);
            }
            
            // 2. LIÊN KẾT KHÁCH HÀNG
            String maKHCanTim = hd.getMaKH_string();
            KhachHang khTimDuoc = QuanLyBanHang.dskh.timKiemTheoMa(maKHCanTim); 
            if (khTimDuoc != null) {
                hd.setKhachHang(khTimDuoc);
            }
        }
        System.out.println("--- Liên kết Hóa đơn hoàn tất! ---");
    }    
    public void themMotHoaDonMoi() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- BẮT ĐẦU THÊM HÓA ĐƠN MỚI ---");

        // 1. TÌM KIẾM VÀ GÁN NHÂN VIÊN LẬP ĐƠN
        NhanVien nv_lap_don = null;
        do {
            System.out.print("Nhập Mã Nhân viên lập đơn (VD: NV001): ");
            String maNV = sc.nextLine();

            // Sử dụng DSNV (đã có trong QuanLyBanHang)
            nv_lap_don = QuanLyBanHang.dsnv.timKiemTheoMa(maNV);

            if (nv_lap_don == null) {
                System.out.println("❌ Lỗi: Không tìm thấy Nhân viên có mã " + maNV + ". Vui lòng nhập lại.");
            }
            else{            
                System.out.println("✅ Nhân viên: " + nv_lap_don.getHo() + " " + nv_lap_don.getTen());
            }

        } while (nv_lap_don == null);

        // 2. TÌM KIẾM VÀ GÁN KHÁCH HÀNG MUA
        KhachHang khmua = null;
        do {
            System.out.print("Nhập Mã Khách hàng (VD: KH001): ");
            String maKH = sc.nextLine();

            // Sử dụng DSKH (đã có trong QuanLyBanHang)
            if (QuanLyBanHang.dskh != null) {
                khmua = QuanLyBanHang.dskh.timKiemTheoMa(maKH);
            }

            if (khmua != null) {
                System.out.println("✅ Khách hàng: " + khmua.getHo() + " " + khmua.getTen());
                break;
            } else {
                System.out.println("Chưa có thông tin khách hàng ");
                System.out.println("Nhập khách hàng mới ");
                dskh.themKhachHang();
            }
        } while (true);

        // 3. TẠO VÀ NHẬP THÔNG TIN CƠ BẢN CỦA HÓA ĐƠN
        HoaDon hdMoi = new HoaDon();
        hdMoi.setNhanVien(nv_lap_don);
        hdMoi.setKhachHang(khmua);
        hdMoi.nhap(); // Nhập Mã HĐ, Ngày lập

        // LẤY MÃ HÓA ĐƠN VỪA NHẬP ĐỂ GÁN CHO CÁC CTHD
        String maHD_moi = hdMoi.getMaHoaDon();
        int tongTienHoaDon = 0;

        // 3.3. Nhập Chi tiết Hóa đơn LẶP LẠI
        System.out.print("Nhập số lượng mặt hàng (chi tiết HD): ");
        int soLuongCTHD = sc.nextInt();
        sc.nextLine();

        // TẠO VÀ NHẬP TỪNG CTHD
        for (int i = 0; i < soLuongCTHD; i++) {
            System.out.println("--- Nhập chi tiết mặt hàng thứ " + (i + 1) + " ---");
            ChiTietHoaDon cthdMoi = new ChiTietHoaDon();

            // 🎯 LOGIC MỚI: Gán Mã HĐ cho CTHD
            cthdMoi.setMaHoaDon(maHD_moi); 
            
            cthdMoi.nhap(); // Nhập Mã SP, Số lượng, tính Thành tiền
            
            // 🎯 LOGIC MỚI: Thêm CTHD vào DSCTHD TOÀN BỘ HỆ THỐNG
            QuanLyBanHang.dscthd.themMotChiTiet(cthdMoi); 

            tongTienHoaDon += cthdMoi.getThanhtien();
        }

        // 3.4. Cập nhật Tổng tiền vào Hóa đơn
        hdMoi.setTongTien(tongTienHoaDon);

        // 4. THÊM VÀO DANH SÁCH (DSHD)
        QuanLyBanHang.dshd.themMotHoaDon(hdMoi);

        System.out.println("✅ Đã thêm hóa đơn thành công!");
        // CHÚ Ý: CẦN TRUYỀN DSCTHD TOÀN BỘ ĐỂ XUẤT ĐẦY ĐỦ
        hdMoi.xuatHoaDonDayDu(); 
    }    
    public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n--- MENU QUẢN LÝ HÓA ĐƠN ---");
            System.out.println("1. Xuất danh sách hóa đơn ");
            System.out.println("2. Thêm hóa đơn mới");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Sửa hóa đơn");
            System.out.println("5. Tìm Kiếm hóa đơn");
            System.out.println("0. Quay lại Menu Chính");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dshd.xuat();
                    break;
                case 2:
                    themMotHoaDonMoi();
                break;
                case 3:
                    //dsvpp.xoaSanPham();
                    break;
                case 4:
                    // dsvpp.suaSanPham();
                    break;
                case 5:
                    // dsvpp.timKiemSanPham();
                    break;
            }
        } while (choice != 0);
    }
}