import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 
import java.time.format.DateTimeParseException; 


public class QLPNH extends QuanLyBanHang {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public void themMotPhieuNhanHangMoi() {
        Scanner sc = new Scanner(System.in);
        PhieuNhapHang pnh = new PhieuNhapHang();
        System.out.println("\n--- THÊM PHIẾU NHẬP HÀNG + CHI TIẾT PHIẾU NHẬP HÀNG ---");

        // 1) Nhập và kiểm tra MÃ PNH
        String mapnh;
        do {
            System.out.print("Nhập Mã Phiếu Nhận Hàng: ");
            mapnh = sc.nextLine().trim();
            if (mapnh.isEmpty()) {
                System.out.println("❌ Mã PNH không được trống.");
            } else if (!dspnh.maDuyNhat(mapnh)) {
                System.out.println("❌ Mã PNH đã tồn tại. Nhập lại.");
                mapnh = ""; // Reset để lặp lại
            }
        } while (mapnh.isEmpty());

        // 2) Nhập và kiểm tra MÃ NCC (phải tồn tại)
        NhaCungCap ncc = null;
        do {
            System.out.print("Nhập Mã Nhà Cung Cấp: ");
            String mancc = sc.nextLine().trim();
            if (mancc.isEmpty()) {
                System.out.println("❌ Mã NCC không được trống.");
                continue;
            }
            ncc = QuanLyBanHang.dsncc.timKiemTheoMa(mancc);
            if (ncc == null) {
                System.out.println("❌ Không tìm thấy nhà cung cấp. Vui lòng nhập lại.");
            }
        } while (ncc == null);

        // 3) Nhập và kiểm tra MÃ NHÂN VIÊN (phải tồn tại)
        NhanVien nv = null;
        do {
            System.out.print("Nhập Mã Nhân viên lập phiếu: ");
            String manv = sc.nextLine().trim();
            if (manv.isEmpty()) {
                System.out.println("❌ Mã NV không được trống.");
                continue;
            }
            nv = QuanLyBanHang.dsnv.timKiemTheoMa(manv);
            if (nv == null) {
                System.out.println("❌ Không tìm thấy nhân viên. Nhập lại.");
            }
        } while (nv == null);

        // 4) Ngày nhận hàng (SỬA: Thêm vòng lặp và try-catch để parse LocalDate)
        LocalDate ngaynhanlocaldate = null;
        while (true) {
            System.out.print("Nhập Ngày Nhận Hàng (VD: dd/mm/yyyy): ");
            String ngaynhanStr = sc.nextLine().trim();
            try {
                ngaynhanlocaldate = LocalDate.parse(ngaynhanStr, DATE_FORMATTER);
                break; // Thoát lặp nếu ngày hợp lệ
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
            }
        }

        // 5) Thiết lập Phiếu Nhận Hàng (SỬA LỖI)
        pnh.setMaPNH(mapnh);
        // pnh.setNhanVien(nv); // SỬA: Lỗi. Đã xóa.
        pnh.setMaNV(nv.getMaNV()); // Chỉ gán Mã String
        pnh.setMaNCC(ncc.getMaNCC()); // Chỉ gán Mã String
        pnh.setNgayNhan(ngaynhanlocaldate); // SỬA: Gán đối tượng LocalDate

        // 6) Nhập Chi Tiết và tính Tổng tiền
        System.out.print("Nhập số lượng mặt hàng khác nhau được nhập: ");
        int soct = 0;
        try {
            soct = Integer.parseInt(sc.nextLine().trim());
            if (soct < 0) soct = 0;
        } catch (NumberFormatException e) {
            System.out.println("❌ Nhập không hợp lệ, đặt số lượng là 0.");
            soct = 0;
        }

        int tongTienPNH = 0;

        for (int i = 0; i < soct; i++) {
            System.out.println("--- Chi Tiết Sản Phẩm thứ " + (i + 1) + " ---");
            ChiTietPNH ctpnh = new ChiTietPNH();
            ctpnh.setMaPNH(mapnh); // Gán Mã PNH cho chi tiết

            // Giả định hàm nhap() này cũng tự tạo Scanner riêng của nó
            ctpnh.nhap();

            tongTienPNH += ctpnh.getThanhTien();

            // Cập nhật tồn kho: cộng số lượng đã nhập
            // (Đảm bảo getMaSP() trả về String masp đã lưu)
            QuanLyBanHang.dsvpp.capNhatSLSP(ctpnh.getMaSP(), ctpnh.getSoLuong());

            QuanLyBanHang.dsctpnh.themMotChiTiet(ctpnh);
        }

        // 7) Cập nhật tổng tiền và lưu PNH
        pnh.setTongTien(tongTienPNH);
        QuanLyBanHang.dspnh.themMotPNH(pnh);

        System.out.println("✅ Đã tạo Phiếu Nhận Hàng thành công!");
        pnh.xuatPhieuDayDu(); // Giả định hàm này xuất đầy đủ
        // Không đóng sc (sc.close()) ở đây
    }

    /**
     * Menu chính.
     * Phương thức này tự tạo Scanner riêng.
     */
    @Override
    public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== QUẢN LÝ PHIẾU NHẬN HÀNG =====");
            System.out.println("1. Thêm một Phiếu Nhận Hàng mới");
            System.out.println("2. Xem danh sách Phiếu Nhận Hàng");
            System.out.println("3. Tìm kiếm Phiếu Nhận Hàng");
            System.out.println("0. Quay lại Menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // Tiêu thụ ký tự Enter
            } catch (InputMismatchException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số.");
                sc.nextLine(); // clear buffer
                choice = -1; // Đặt lại choice để lặp lại
                continue;
            }

            switch (choice) {
                case 1:
                    themMotPhieuNhanHangMoi(); // Không truyền tham số
                    break;
                case 2:
                    dspnh.xuat();
                    break;
                case 3:
                    timPNH(); // Không truyền tham số
                    break;
                case 0:
                    System.out.println("Quay lại Menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public void timPNH() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã Phiếu Nhận Hàng cần tìm: ");
        String mapnh = sc.nextLine();

        PhieuNhapHang pnh = dspnh.timPNHtheoMa(mapnh);

        if (pnh != null) {
            System.out.println("--- Thông tin Phiếu Nhận Hàng ---");
            pnh.xuatPhieuDayDu(); // Hàm này tự tra cứu dữ liệu liên quan
        } else {
            System.out.println("❌ Không tìm thấy Phiếu Nhận Hàng có mã: " + mapnh);
        }
    }

    public void xemChiTiet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã Phiếu Nhận Hàng để xem chi tiết: ");
        String mapnh = sc.nextLine().trim();

        // 1. Tìm PNH trước
        PhieuNhapHang pnh = dspnh.timPNHtheoMa(mapnh);
        if (pnh == null) {
            System.out.println("❌ Không tìm thấy Phiếu Nhận Hàng có mã: " + mapnh);
            return; // Dừng lại nếu không thấy PNH
        }

        // 2. Nếu thấy PNH, tìm các chi tiết của nó
        ChiTietPNH[] ctpnh_list = QuanLyBanHang.dsctpnh.timCTPNHTheoMaPNH(mapnh);

        // 3. In thông tin
        System.out.println("\n--- THÔNG TIN PHIẾU NHẬN HÀNG " + mapnh + " ---");
        pnh.xuat(); // Hàm này tự tra cứu tên NV/NCC

        if (ctpnh_list != null && ctpnh_list.length > 0) {
            System.out.println("--- DANH SÁCH SẢN PHẨM ---");
            System.out.printf("%-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n",
                    "STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
            System.out.println("-----------------------------------------------------------------------------------------");

            int stt = 1;
            for (ChiTietPNH ctpnh : ctpnh_list) {
                if (ctpnh == null) break;
                ctpnh.xuatThongTinCT(stt); // Hàm này tự tra cứu tên SP
                stt++;
            }
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("TỔNG TIỀN PHIẾU NHẬP: %d\n", pnh.getTongTien());
        } else {
            System.out.println("ℹ️ Phiếu Nhận Hàng này không có chi tiết sản phẩm nào.");
        }
        // Không đóng sc (sc.close())
    }
}
