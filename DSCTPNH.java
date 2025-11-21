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
        dsctpnh = new ChiTietPNH[0];
        try (BufferedReader br = new BufferedReader(new FileReader("ChiTietPhieuNhapHang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");
                if (thongtin.length < 5) {
                    System.out.println("Lỗi dữ liệu: Không đủ 5 thông tin CTPNH. Bỏ qua: " + line);
                    continue;
                }

                try {
                    String mapnh = thongtin[0].trim();
                    String masp = thongtin[1].trim();
                    int dongia = Integer.parseInt(thongtin[2].trim());
                    int soluong = Integer.parseInt(thongtin[3].trim());
                    int thanhtien = Integer.parseInt(thongtin[4].trim());

                    if (m >= dsctpnh.length) {
                        dsctpnh = Arrays.copyOf(dsctpnh, m + 1);
                    }

                    ChiTietPNH ctpnh = new ChiTietPNH();
                    ctpnh.setMaPNH(mapnh);
                    ctpnh.setMaSP(masp);
                    ctpnh.setDonGia(dongia);
                    ctpnh.setSoLuong(soluong);
                    ctpnh.setThanhTien(thanhtien);

                    dsctpnh[m] = ctpnh;
                    m++;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Số/Tiền không hợp lệ. Bỏ qua: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Thông báo: Không tìm thấy file ChiTietPhieuNhapHang.txt. Sẽ tạo file mới khi ghi.");
        }
    }

    public void ghiFileMotChiTiet(ChiTietPNH ctpnh) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietPhieuNhapHang.txt", true))) {
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

    public void ghiLaiToanBoFileCTPNH() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietPhieuNhapHang.txt", false))) {
            for (int i = 0; i < m; i++) {
                ChiTietPNH ctpnh = dsctpnh[i];
                if (ctpnh == null) continue;

                String masp = ctpnh.getMaSP();
                if (masp == null || masp.isEmpty()) {
                    System.out.println("Lỗi: CTPNH của PNH " + ctpnh.getMaPNH() + " bị thiếu MaSP khi ghi file.");
                    continue;
                }

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

    public void themMotChiTiet(ChiTietPNH ctpnh) {
        if (ctpnh == null) return;
        this.dsctpnh = Arrays.copyOf(this.dsctpnh, this.m + 1);
        this.dsctpnh[this.m] = new ChiTietPNH(ctpnh);
        this.m++;
        ghiFileMotChiTiet(ctpnh);
    }

    public ChiTietPNH[] timCTPNHTheoMaPNH(String mapnh) {
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
            if (dsctpnh[i] != null && dsctpnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                ketqua[j++] = dsctpnh[i];
            }
        }
        return ketqua;
    }
}