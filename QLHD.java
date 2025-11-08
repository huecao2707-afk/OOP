import java.util.Scanner;

public class QLHD extends QuanLyBanHang {
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
            System.out.println("✅ Khách hàng: " + nv_lap_don.getHo() + " " + nv_lap_don.getTen());

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

        // 3. TẠO VÀ NHẬP THÔNG TIN CỦA HÓA ĐƠN
        HoaDon hdMoi = new HoaDon();

        // 3.1. Thiết lập NV & KH
        hdMoi.setNhanVien(nv_lap_don);
        hdMoi.setKhachHang(khmua);

        // 3.2. Gọi hàm nhập cơ bản của Hóa đơn (chỉ nhập Mã HĐ, Ngày lập)
        hdMoi.nhap(); // Gọi hàm nhập không tham số mới

        // 3.3. Nhập Chi tiết Hóa đơn (dùng hàm đã sửa trong HoaDon.java)
        hdMoi.nhapChiTietHoaDon();

        // 4. THÊM VÀO DANH SÁCH (DSHD)
        QuanLyBanHang.dshd.themMotHoaDon(hdMoi);

        System.out.println("✅ Đã thêm hóa đơn thành công!");
        hdMoi.xuatHoaDonDayDu(); // Xuất để người dùng thấy ngay
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