import java.util.Scanner;
import java.util.InputMismatchException;

public class QLPNH extends QuanLyBanHang{
    public void LienKetDuLieuPNH() {
        System.out.println("--- Đang liên kết dữ liệu Phiếu Nhận Hàng ---");

        for (int i = 0; i < dspnh.dspnh.length; i++) {
            PhieuNhapHang pnh = dspnh.dspnh[i];
            if (pnh == null) break;

            // 1. LIÊN KẾT NHÂN VIÊN
            String manvcantim = pnh.getManv();
            if (manvcantim != null && !manvcantim.trim().isEmpty()) {
                NhanVien nvtimduoc = QuanLyBanHang.dsnv.timKiemTheoMa(manvcantim.trim());
                if (nvtimduoc != null) {
                    pnh.setNhanVien(nvtimduoc);
                }
            }

            // 2. LIÊN KẾT NHÀ CUNG CẤP (Giả định QuanLyBanHang.dsncc và NhaCungCap tồn tại)
            String mancccantim = pnh.getMancc();
            if (mancccantim != null && !mancccantim.trim().isEmpty()) {
                // NhaCungCap ncctimduoc = QuanLyBanHang.dsncc.timKiemTheoMa(mancccantim.trim());
                // if (ncctimduoc != null) {
                //     pnh.setNhaCungCap(ncctimduoc);
                // }
            }
        }
        System.out.println("--- Liên kết PNH hoàn tất! ---");
    }

    public void LienKetDuLieuCTPNH() {
        // Gọi hàm liên kết trong DSCTPNH
        this.dsctpnh.LienKetDuLieuCTPNH();
        System.out.println("--- Liên kết Chi tiết PNH hoàn tất! ---");
    }

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
                System.out.println("❌ Mã PNH không được trống."); continue;
            }
            if (!dspnh.maDuyNhat(mapnh)) {
                System.out.println("❌ Mã PNH đã tồn tại. Nhập lại."); mapnh = "";
            }
        } while (mapnh.isEmpty());

        // 2) Nhập và kiểm tra MÃ NCC (phải tồn tại)
        NhaCungCap ncc = null;
        do {
            System.out.print("Nhập Mã Nhà Cung Cấp: ");
            String mancc = sc.nextLine().trim();
             ncc = QuanLyBanHang.dsncc.timKiemTheoMa(mancc); // Giả định hàm này
            if (ncc == null) {
                System.out.println("❌ Không tìm thấy nhà cùng cấp. Thêm nhà cung cấp mới trước khi tiếp tục.");
                QuanLyBanHang.dsncc.them();
            }
        } while (ncc == null);

        // 3) Nhập và kiểm tra MÃ NHÂN VIÊN (phải tồn tại)
        NhanVien nv = null;
        do {
            System.out.print("Nhập Mã Nhân viên lập phiếu: ");
            String manv = sc.nextLine().trim();
            nv = QuanLyBanHang.dsnv.timKiemTheoMa(manv);
            if (nv == null) {
                System.out.println("❌ Không tìm thấy nhân viên. Nhập lại.");
            }
        } while (nv == null);

        // 4) Ngày nhận hàng
        System.out.print("Nhập Ngày Nhận Hàng (VD: dd/mm/yyyy): ");
        String ngaynhan = sc.nextLine().trim();

        // 5) Thiết lập Phiếu Nhận Hàng
        pnh.setMaPNH(mapnh);
        pnh.setNhanVien(nv);
        // pnh.setNhaCungCap(ncc); // Gán đối tượng
        pnh.setManv(nv.getMaNV()); // Gán Mã String
        // pnh.setMancc(ncc.getMaNCC()); // Gán Mã String
        pnh.setNgaynhan(ngaynhan);
        // 6) Nhập Chi Tiết và tính Tổng tiền
        System.out.print("Nhập số lượng mặt hàng khác nhau được nhập: ");
        int soct = 0;
        try { soct = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ignore) { soct = 0; }

        int tongTienPNH = 0;

        for (int i = 0; i < soct; i++) {
            System.out.println("--- Chi Tiết Sản Phẩm thứ " + (i + 1) + " ---");
            ChiTietPNH ctpnh = new ChiTietPNH();
            ctpnh.setMaPNH(mapnh); // Gán Mã PNH cho chi tiết

            ctpnh.nhap(); // Hàm nhập sẽ tra cứu SP và nhập SL, Đơn giá nhập

            tongTienPNH += ctpnh.getThanhTien();

            // Cập nhật tồn kho: cộng số lượng đã nhập
             QuanLyBanHang.dsvpp.capNhatSLSP(ctpnh.getMaSP(), ctpnh.getSoLuong()); // Giả định hàm này

        QuanLyBanHang.dsctpnh.themMotChiTiet(ctpnh);
        }

        // 7) Cập nhật tổng tiền và lưu PNH
        pnh.setTongTien(tongTienPNH);
        QuanLyBanHang.dspnh.themMotPNH(pnh);

        System.out.println("✅ Đã tạo Phiếu Nhận Hàng thành công!");
        pnh.xuatPhieuDayDu();
    }
    @Override public void menuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== QUẢN LÝ PHIẾU NHẬN HÀNG =====");
            System.out.println("1. Thêm một Phiếu Nhận Hàng mới");
            System.out.println("2. Xem danh sách Phiếu Nhận Hàng");
            System.out.println("3. Tìm kiếm Phiếu Nhận Hàng");
            System.out.println("4. Xem Chi tiết Phiếu Nhận Hàng theo Mã");
            System.out.println("0. Quay lại Menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số.");
                sc.nextLine(); // clear buffer
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
                case 4:
//                    xemChiTiet();
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
            // Cần tạo hàm xuất tương ứng
            // pnh.xuatPNH();
            pnh.xuat(); // Tạm xuất mã
        } else {
            System.out.println("❌ Không tìm thấy Phiếu Nhận Hàng có mã: " + mapnh);
        }
    }

//    public void xemChiTiet() {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Nhập mã Phiếu Nhận Hàng để xem chi tiết: ");
//        String mapnh = sc.nextLine();
//
//        ChiTietPNH[] ctpnh_list = dsctpnh.timCTPNHTheoMaPNH(mapnh);
//
//        if (ctpnh_list != null) {
//            System.out.println("\n--- CHI TIẾT PHIẾU NHẬN HÀNG " + mapnh + " ---");
//            System.out.printf("%-15s %-15s %-15s %-15s %-15s\n", "Mã PNH", "Mã SP", "Đơn Giá", "Số Lượng", "Thành Tiền");
//            for (ChiTietPNH ctpnh : ctpnh_list) {
//                ctpnh.xuatThongTinCT();
//            }
//        } else {
//            System.out.println("❌ Không tìm thấy chi tiết cho Phiếu Nhận Hàng có mã: " + mapnh);
//        }
//    }
}