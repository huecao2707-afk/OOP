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

		// 1. LIÊN KẾT NHÂN VIÊN (tra theo mã NV dạng String đã lưu trong hóa đơn)
		    String manvcantim = hd.getMaNhanVien();
            if (manvcantim != null && !manvcantim.trim().isEmpty()) {
                NhanVien nvtimduoc = QuanLyBanHang.dsnv.timKiemTheoMa(manvcantim.trim()); 
                if (nvtimduoc != null) {
                    hd.setNhanVien(nvtimduoc);
                }
            }
            
		// 2. LIÊN KẾT KHÁCH HÀNG (tra theo mã KH dạng String đã lưu trong hóa đơn)
    		String makhcantim = hd.getMaKH();
            if (makhcantim != null && !makhcantim.trim().isEmpty()) {
                KhachHang khtimduoc = QuanLyBanHang.dskh.timKiemTheoMa(makhcantim.trim()); 
                if (khtimduoc != null) {
                    hd.setKhachHang(khtimduoc);
                }
            }
        }
        System.out.println("--- Liên kết Hóa đơn hoàn tất! ---");
    }    	
	public void themHDVaChiTietHD() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n--- THÊM HÓA ĐƠN + CHI TIẾT HÓA ĐƠN ---");
		
		// 1) Nhập và kiểm tra MÃ HÓA ĐƠN (không trùng)
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
		
		// 2) Nhập và kiểm tra MÃ KHÁCH HÀNG (phải tồn tại; nếu không thì thêm mới)
		KhachHang kh = null;
		do {
			System.out.print("Nhập Mã Khách hàng (VD: KH001): ");
			String makh = sc.nextLine().trim();
			kh = QuanLyBanHang.dskh.timKiemTheoMa(makh);
			if (kh == null) {
				System.out.println("❌ Không tìm thấy khách hàng. Thêm khách hàng mới trước khi tiếp tục.");
				QuanLyBanHang.dskh.themKhachHang();
			}
		} while (kh == null);
		
		// 3) Nhập và kiểm tra MÃ NHÂN VIÊN (phải tồn tại)
		NhanVien nv = null;
		do {
			System.out.print("Nhập Mã Nhân viên lập đơn (VD: NV001): ");
			String manv = sc.nextLine().trim();
			nv = QuanLyBanHang.dsnv.timKiemTheoMa(manv);
			if (nv == null) {
				System.out.println("❌ Không tìm thấy nhân viên. Nhập lại.");
			}
		} while (nv == null);
		
		// 4) Ngày lập hóa đơn
		System.out.print("Nhập Ngày lập HĐ (VD: dd/mm/yyyy): ");
		String ngaylaphoadon = sc.nextLine().trim();
		
		// Khởi tạo Hóa đơn
		HoaDon hoadon = new HoaDon();
		hoadon.setMaHoaDon(mahd);
		hoadon.setNhanVien(nv);
		hoadon.setKhachHang(kh);
		hoadon.setNgayLapHD(ngaylaphoadon);
		
		int tongtien = 0;
		
		// 5) Nhập các Chi tiết hóa đơn
		System.out.print("Nhập số lượng mặt hàng: ");
		int soct = 0;
		try {
			soct = Integer.parseInt(sc.nextLine().trim());
		} catch (Exception ignore) { soct = 0; }
		
		for (int i = 0; i < soct; i++) {
			System.out.println("--- Mặt hàng #" + (i + 1) + " ---");
			
			// 5.1) Chọn sản phẩm theo mã
			VanPhongPham sp = null;
			do {
				System.out.print("  > Nhập Mã sản phẩm: ");
				String masp = sc.nextLine().trim();
				sp = QuanLyBanHang.dsvpp.timMaSP(masp);
				if (sp == null) {
					System.out.println("  ❌ Không tìm thấy sản phẩm. Nhập lại.");
				}
			} while (sp == null);
			
			// 5.2) Kiểm tra số lượng tồn và nhập số lượng mua hợp lệ
			int soluong;
			while (true) {
				System.out.printf("  > Tồn kho: %d | Đơn giá: %,d%n", sp.getSoLuong(), sp.getDonGia());
				System.out.print("  > Nhập Số lượng mua: ");
				String soluongstring = sc.nextLine().trim(); //soluongstring
				try {
					soluong = Integer.parseInt(soluongstring);
				} catch (Exception e) {
					System.out.println("  ❌ Số lượng không hợp lệ. Nhập lại.");
					continue;
				}
				if (soluong <= 0) {
					System.out.println("  ❌ Số lượng phải > 0.");
					continue;
				}
				if (soluong > sp.getSoLuong()) {
					System.out.println("  ❌ Vượt quá tồn kho. Nhập lại.");
					continue;
				}
				break;
			}
			
			// 5.3) Tính thành tiền, khởi tạo CTHD và thêm vào DSCTHD
			int dongia = sp.getDonGia();
			int thanhtien = dongia * soluong;
			tongtien += thanhtien;
			
			// Cập nhật tồn kho: trừ số lượng đã bán
			QuanLyBanHang.dsvpp.capNhatSLSP(sp.getMaSP(), -soluong);
			
			ChiTietHoaDon ct = new ChiTietHoaDon();
			ct.setMaHoaDon(mahd);
			ct.setSanPham(sp);
			ct.setDonGia(dongia);
			ct.setSoLuong(soluong);
			ct.setThanhTien(thanhtien);
			
			QuanLyBanHang.dscthd.themMotChiTiet(ct);
		}
		
		// 6) Cập nhật tổng tiền và lưu hóa đơn
		hoadon.setTongTien(tongtien);
		QuanLyBanHang.dshd.themMotHoaDon(hoadon);
		
		System.out.println("✅ Đã thêm hóa đơn và chi tiết hóa đơn thành công!");
		hoadon.xuatHoaDonDayDu();
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
                    themHDVaChiTietHD();
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
