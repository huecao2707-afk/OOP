import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class DSCTPNH {
    int m;
    public ChiTietPNH[] dsctpnh;

    public DSCTPNH() {
        m = 0;
        dsctpnh = new ChiTietPNH[0];
    }

    public DSCTPNH(int m, ChiTietPNH[] dsctpnh) {
        this.m = m;
        this.dsctpnh = Arrays.copyOf(dsctpnh, m);
    }


    public DSCTPNH(DSCTPNH other) {
        this.m = other.m;
        this.dsctpnh = new ChiTietPNH[m];
        for (int i = 0; i < m; i++) {
            this.dsctpnh[i] = new ChiTietPNH(other.dsctpnh[i]);
        }
    }

    public void docFileCTPNH() {
        m = 0;
        // Khởi tạo mảng mới để tránh lỗi NullPointerException khi đọc
        dsctpnh = new ChiTietPNH[0];
        try (BufferedReader br = new BufferedReader(new FileReader("ChiTietPhieuNhapHang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");
                // Kiểm tra phải có 5 trường dữ liệu
                if (thongtin.length < 5) {
                    System.out.println("Lỗi dữ liệu: Không đủ 5 thông tin CTPNH. Bỏ qua: " + line);
                    continue;
                }

                try {
                    // mapnh(0), masp(1), dongia(2), soluong(3), thanhtien(4)
                    String mapnh = thongtin[0].trim();
                    String masp = thongtin[1].trim();
                    int dongia = Integer.parseInt(thongtin[2].trim());
                    int soluong = Integer.parseInt(thongtin[3].trim());
                    int thanhtien = Integer.parseInt(thongtin[4].trim());

                    // Tăng kích thước mảng trước khi gán
                    if (m >= dsctpnh.length) {
                        dsctpnh = Arrays.copyOf(dsctpnh, m + 1);
                    }

                    // SỬA: Tạo CTPNH theo format mới (không có đối tượng sp)
                    ChiTietPNH ctpnh = new ChiTietPNH();
                    ctpnh.setMaPNH(mapnh);
                    ctpnh.setMaSP(masp); // Lưu mã SP (String)
                    ctpnh.setDonGia(dongia);
                    ctpnh.setSoLuong(soluong);
                    ctpnh.setThanhTien(thanhtien);

                    dsctpnh[m] = ctpnh;
                    m++; // Tăng số lượng phần tử
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Số/Tiền không hợp lệ. Bỏ qua: " + line);
                }
            }
        } catch (IOException e) {
            // Đây có thể là lần chạy đầu tiên, không phải lỗi nghiêm trọng
            System.out.println("Thông báo: Không tìm thấy file ChiTietPhieuNhapHang.txt. Sẽ tạo file mới khi ghi.");
        }
    }

    /**
     * SỬA LẠI THEO FORMAT MỚI:
     * Đã xóa hàm 'LienKetDuLieuCTPNH()'.
     * Vì 'ChiTietPNH' không còn chứa đối tượng 'VanPhongPham sp' nữa,
     * nên không cần thực hiện việc liên kết sau khi đọc file.
     */

    /**
     * Hàm này đã đúng, ghi 5 thuộc tính ở chế độ GHI NỐI (append).
     */
    public void ghiFileMotChiTiet(ChiTietPNH ctpnh) {
        // 'true' = append (ghi nối vào cuối file)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietPhieuNhapHang.txt", true))) {
            // mapnh, masp, dongia, soluong, thanhtien
            String line = ctpnh.getMaPNH() + "," +
                    ctpnh.getMaSP() + "," +
                    ctpnh.getDonGia() + "," +
                    ctpnh.getSoLuong() + "," +
                    ctpnh.getThanhTien();
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file ChiTietPhieuNhapHang.txt: " + e.getMessage());
        }
    }

    /**
     * SỬA LỖI:
     * 1. Chuyển chế độ ghi từ 'true' (append) sang 'false' (overwrite)
     * để GHI ĐÈ toàn bộ file.
     * 2. Ghi đủ 5 thuộc tính.
     */
    public void ghiLaiToanBoFileCTPNH() {
        // 'false' = overwrite (ghi đè toàn bộ file)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietPhieuNhapHang.txt", false))) {
            for (int i = 0; i < m; i++) {
                ChiTietPNH ctpnh = dsctpnh[i];
                if (ctpnh == null) continue;

                // Logic lấy MaSP đã đơn giản hóa, chỉ cần lấy MaSP (String) là đủ
                String masp = ctpnh.getMaSP();
                if (masp == null || masp.isEmpty()) {
                    // Phòng trường hợp MaSP bị null (dù không nên xảy ra)
                    System.out.println("Lỗi: CTPNH của PNH " + ctpnh.getMaPNH() + " bị thiếu MaSP khi ghi file.");
                    continue;
                }

                // Ghi đủ 5 thuộc tính
                String line = ctpnh.getMaPNH() + "," +
                        masp + "," +
                        ctpnh.getDonGia() + "," +
                        ctpnh.getSoLuong() + "," +
                        ctpnh.getThanhTien();

                bw.write(line);
                bw.newLine();
            }
            System.out.println("-> Đã cập nhật lại toàn bộ file ChiTietPhieuNhapHang.txt.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file CTPNH: " + e.getMessage());
        }
    }

    /**
     * Hàm này đã đúng, thêm 1 chi tiết vào mảng và gọi ghi file.
     */
    public void themMotChiTiet(ChiTietPNH ctpnh) {
        if (ctpnh == null) return;
        this.dsctpnh = Arrays.copyOf(this.dsctpnh, this.m + 1);
        this.dsctpnh[this.m] = new ChiTietPNH(ctpnh); // Dùng copy constructor
        this.m++;
        ghiFileMotChiTiet(ctpnh); // Ghi chi tiết mới vào cuối file
    }

    /**
     * Hàm này đã đúng, tìm tất cả CTPNH theo MaPNH.
     */
    public ChiTietPNH[] timCTPNHTheoMaPNH(String mapnh) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (dsctpnh[i] != null && dsctpnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                count++;
            }
        }
        if (count == 0) return null; // Trả về null nếu không tìm thấy

        ChiTietPNH[] ketqua = new ChiTietPNH[count];
        int j = 0;
        for (int i = 0; i < m; i++) {
            // Thêm kiểm tra null để an toàn
            if (dsctpnh[i] != null && dsctpnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                ketqua[j++] = dsctpnh[i];
            }
        }
        return ketqua;
    }
}
