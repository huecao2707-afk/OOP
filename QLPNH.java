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

        String mapnh;
        do {
            System.out.print("Nhập Mã Phiếu Nhận Hàng: ");
            mapnh = sc.nextLine().trim();
            if (mapnh.isEmpty()) {
                System.out.println("❌ Mã PNH không được trống.");
            } else if (!dspnh.maDuyNhat(mapnh)) {
                System.out.println("❌ Mã PNH đã tồn tại. Nhập lại.");
                mapnh = "";
            }
        } while (mapnh.isEmpty());

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

        LocalDate ngaynhanlocaldate = null;
        while (true) {
            System.out.print("Nhập Ngày Nhận Hàng (VD: dd/mm/yyyy): ");
            String ngaynhanStr = sc.nextLine().trim();
            try {
                ngaynhanlocaldate = LocalDate.parse(ngaynhanStr, DATE_FORMATTER);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
            }
        }

        pnh.setMaPNH(mapnh);
        pnh.setMaNV(nv.getMaNV());
        pnh.setMaNCC(ncc.getMaNCC());
        pnh.setNgayNhan(ngaynhanlocaldate);

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
            ctpnh.setMaPNH(mapnh);

            ctpnh.nhap();

            tongTienPNH += ctpnh.getThanhTien();

            QuanLyBanHang.dsvpp.capNhatSLSP(ctpnh.getMaSP(), ctpnh.getSoLuong());
            QuanLyBanHang.dsvpp.capNhatDGSP(ctpnh.getMaSP(), ctpnh.getDonGia());
            QuanLyBanHang.dsctpnh.themMotChiTiet(ctpnh);
        }

        pnh.setTongTien(tongTienPNH);
        QuanLyBanHang.dspnh.themMotPNH(pnh);

        System.out.println("✅ Đã tạo Phiếu Nhận Hàng thành công!");
        pnh.xuatPhieuDayDu();
    }

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
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số.");
                sc.nextLine();
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    themMotPhieuNhanHangMoi();
                    break;
                case 2:
                    dspnh.xuat();
                    break;
                case 3:
                    timPNH();
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
            pnh.xuatPhieuDayDu();
        } else {
            System.out.println("❌ Không tìm thấy Phiếu Nhận Hàng có mã: " + mapnh);
        }
    }

    public void xemChiTiet() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã Phiếu Nhận Hàng để xem chi tiết: ");
        String mapnh = sc.nextLine().trim();

        PhieuNhapHang pnh = dspnh.timPNHtheoMa(mapnh);
        if (pnh == null) {
            System.out.println("❌ Không tìm thấy Phiếu Nhận Hàng có mã: " + mapnh);
            return;
        }

        ChiTietPNH[] ctpnh_list = QuanLyBanHang.dsctpnh.timCTPNHTheoMaPNH(mapnh);

        System.out.println("\n--- THÔNG TIN PHIẾU NHẬN HÀNG " + mapnh + " ---");
        pnh.xuat();

        if (ctpnh_list != null && ctpnh_list.length > 0) {
            System.out.println("--- DANH SÁCH SẢN PHẨM ---");
            System.out.printf("%-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n",
                    "STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
            System.out.println("-----------------------------------------------------------------------------------------");

            int stt = 1;
            for (ChiTietPNH ctpnh : ctpnh_list) {
                if (ctpnh == null) break;
                ctpnh.xuatThongTinCT(stt);
                stt++;
            }
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("TỔNG TIỀN PHIẾU NHẬP: %d\n", pnh.getTongTien());
        } else {
            System.out.println("ℹ️ Phiếu Nhận Hàng này không có chi tiết sản phẩm nào.");
        }
    }
}