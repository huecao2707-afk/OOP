import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
// Các hàm tương tự DSCTHD (docFile, ghiFile, themMotChiTiet, timCTPNHTheoMaPNH)
// Cần thêm hàm LienKetDuLieuCTPNH để gán đối tượng VanPhongPham (sp) sau khi đọc file

public class DSCTPNH{
    int m;
    public ChiTietPNH[] dsctpnh;

    public DSCTPNH(){ m = 0; dsctpnh = new ChiTietPNH[0]; }
    public DSCTPNH(int m, ChiTietPNH[] dsctpnh){
        this.m = m;
        this.dsctpnh = DSCTPNH.this.dsctpnh;
    }
    public DSCTPNH(DSCTPNH other){
        this.m = other.m;
        this.dsctpnh = new ChiTietPNH[m];
        for (int i = 0; i < m; i++){ // Dùng m thay vì dsctpnh.length để tránh lỗi nếu m nhỏ hơn capacity
            this.dsctpnh[i] = new ChiTietPNH(other.dsctpnh[i]);
        }
    }

    public void docFileCTPNH() {
        m = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("ChiTietPhieuNhapHang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");
                if (thongtin.length < 3) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin chi tiết phiếu nhập hàng. Bỏ qua: " + line);
                    continue;
                }

                int  soluong;
                try {
                    soluong = Integer.parseInt(thongtin[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Số lượng không hợp lệ. Bỏ qua: " + line);
                    continue;
                }

                if (m >= dsctpnh.length) { dsctpnh = Arrays.copyOf(dsctpnh, m + 1); }

                ChiTietPNH ctpnh = new ChiTietPNH();
                ctpnh.setMaPNH(thongtin[0].trim());
                ctpnh.setMaSP(thongtin[1].trim()); // Lưu mã SP String
                ctpnh.setSoLuong(soluong);
                dsctpnh[m] = ctpnh; m++;
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file ChiTietPhieuNhapHang.txt: " + e.getMessage());
            }
    }

    public void LienKetDuLieuCTPNH() {
        // Thực hiện liên kết Mã SP (String) với Đối tượng VanPhongPham (Object)
        for (int i = 0; i < m; i++) {
            ChiTietPNH ctpnh = dsctpnh[i];
            if (ctpnh == null || ctpnh.getSanPham() != null) continue; // Bỏ qua nếu đã liên kết

            String maspcantim = ctpnh.getMaSP();
            if (maspcantim != null && !maspcantim.trim().isEmpty()) {
                VanPhongPham sptimduoc = QuanLyBanHang.dsvpp.timMaSP(maspcantim.trim());
                if (sptimduoc != null) {
                    ctpnh.setSanPham(sptimduoc);
                }
            }
        }
    }

    public void ghiFileMotChiTiet(ChiTietPNH ctpnh) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietPhieuNhapHang.txt", true))) {
            // mapnh, masp, dongia, soluong, thanhtien
            String line = ctpnh.getMaPNH() + "," + ctpnh.getMaSP() + "," + ctpnh.getDonGia() + "," + ctpnh.getSoLuong() + "," + ctpnh.getThanhTien();
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file ChiTietPhieuNhapHang.txt: " + e.getMessage());
        }
    }
    public void ghiLaiToanBoFileCTPNH() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietPhieuNhapHang.txt", true))) {
            for (int i = 0; i < m; i++) {
                ChiTietPNH ctpnh = dsctpnh[i];
                if (ctpnh == null) continue; // Bỏ qua nếu phần tử ctpnh bị null

                // 1. Logic an toàn: Ưu tiên lấy mã từ đối tượng SP (nếu tồn tại)
                String maspantoan;
                if (ctpnh.getSanPham() != null) {
                    // Trường hợp 1: Đối tượng mới (được thêm khi chạy), 'sp' tồn tại.
                    maspantoan = ctpnh.getSanPham().getMaSP();
                } else {
                    // Trường hợp 2: Đối tượng đọc từ file, 'sp' là null.
                    // Lấy mã (String) đã được lưu khi đọc file.
                    maspantoan = ctpnh.getMaSP();
                }

                // 2. Sử dụng biến maspantoan
                String line = ctpnh.getMaPNH() + "," + maspantoan + "," + ctpnh.getSoLuong();

                bw.write(line);
                bw.newLine();
            }
            System.out.println("-> Đã cập nhật lại toàn bộ file ChiTietHoaDon.txt.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file CTHD: " + e.getMessage());
        }
    }

    public void themMotChiTiet(ChiTietPNH ctpnh) {
        if (ctpnh == null) return;
        this.dsctpnh = Arrays.copyOf(this.dsctpnh, this.m + 1);
        this.dsctpnh[this.m] = new ChiTietPNH(ctpnh);
        this.m++;
        ghiFileMotChiTiet(ctpnh);
    }

    public ChiTietPNH[] timCTPNHTheoMaPNH(String mapnh) {
        // Tương tự hàm timCTHDTheoMaHD
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (dsctpnh[i] != null && dsctpnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                count++;
            }
        }
        if (count == 0) return null;
        ChiTietPNH[] ketqua = new ChiTietPNH[count];
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (dsctpnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                ketqua[j++] = dsctpnh[i];
            }
        }
        return ketqua;
    }
    public void xuat() {
        if (m == 0) {
            System.out.println("Danh sách chi tiết phiếu nhập hàng trống.");
            return;
        }
        System.out.println("----------- DANH SÁCH TẤT CẢ CHI TIẾT PHIẾU NHẬP HÀNG -----------");
        System.out.printf("%-10s | %-10s | %-10s | %-15s | %-15s\n",
                "Mã HĐ", "Mã SP", "SL", "Đơn Giá (dự kiến)", "Thành Tiền (dự kiến)");
        System.out.println("-------------------------------------------------------------------------");

        for (int i = 0; i < m; i++) {
            // Cần hàm xuat() trong ChiTietPhieuNhapHang để hiển thị
            dsctpnh[i].xuatThongTinCT(i);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
    public boolean xoaChiTiet(String mapnh, String masp) {
        int index = -1;

        // 1. Tìm vị trí của CTHD cần xóa
        for (int i = 0; i < m; i++) {
            if (dsctpnh[i] != null &&
                    dsctpnh[i].getMaPNH().equalsIgnoreCase(mapnh) &&
                    dsctpnh[i].getMaSP().equalsIgnoreCase(masp))
            {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("❌ Không tìm thấy chi tiết hóa đơn cần xóa.");
            return false;
        }

        // 2. Xóa khỏi mảng bằng cách dịch chuyển
        for (int i = index; i < m - 1; i++) {
            dsctpnh[i] = dsctpnh[i + 1];
        }
        dsctpnh[m - 1] = null;
        m--;

        // Giảm kích thước mảng trong bộ nhớ
        dsctpnh = Arrays.copyOf(dsctpnh, m);

        System.out.println("✅ Đã xóa CTPNH cho PNH " + mapnh + " (SP: " + masp + ").");

        // 3. Cập nhật lại toàn bộ file
        ghiLaiToanBoFileCTPNH();
        return true;
    }

}