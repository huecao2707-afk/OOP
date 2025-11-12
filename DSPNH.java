import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSPNH {
    private int n;
    public PhieuNhapHang[] dspnh;

    public DSPNH(){
        dspnh = new PhieuNhapHang[100]; n = 0;
    }
    public DSPNH(int n, PhieuNhapHang[] dspnh){
        this.n = n;
        this.dspnh = DSPNH.this.dspnh;
    }
    public DSPNH(DSPNH other) {
        this.n = other.n;
        this.dspnh = new PhieuNhapHang[other.dspnh.length];
        for (int i = 0; i < this.n; i++) {
            this.dspnh[i] = new PhieuNhapHang(other.dspnh[i]);
        }
    }

    public PhieuNhapHang timPNHtheoMa(String mapnh) {
        for (int i = 0; i < n; i++) {
            if (dspnh[i] != null && dspnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                return dspnh[i];
            }
        }
        return null;
    }

    public boolean maDuyNhat(String mapnh){
        for(int i = 0; i < n; i++)
            if(dspnh[i].getMaPNH().equals(mapnh))
                return false;
        return true;
    }

    public void ghiFilePNH(PhieuNhapHang pnh){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PhieuNhanHang.txt", true))) {
            // mapnh, manv, mancc, ngaynhan, tongtien
            String line = pnh.getMaPNH() + "," + pnh.getManv() + "," + pnh.getMancc() + "," + pnh.getNgaynhan() + "," + pnh.getTongTien();
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
        }
    }
    public void ghiLaiToanBoFilePNH() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PhieuNhanHang.txt", false))) {
            for (int i = 0; i < n; i++) {
                PhieuNhapHang pnh = dspnh[i];
                String line = pnh.getMaPNH() + "," + pnh.getManv() + "," + pnh.getMancc() + "," + pnh.getNgaynhan() + "," + pnh.getTongTien();

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file: " + e.getMessage());
        }
    }

    public void docFilePNH() {
        n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("PhieuNhapHang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");
                if (thongtin.length < 5)
                {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin phiếu nhập hàng. Bỏ qua: " + line);
                    continue;
                }

                int tongtien;
                try {
                    tongtien = Integer.parseInt(thongtin[4].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Tổng tiền không hợp lệ. Bỏ qua: " + line);
                    continue;
                }

                if (n >= dspnh.length) {
                    dspnh = Arrays.copyOf(dspnh, n + 1);
                }

                PhieuNhapHang pnh = new PhieuNhapHang();
                pnh.setMaPNH(thongtin[0].trim());
                pnh.setManv(thongtin[1].trim()); // Lưu Mã NV String
                pnh.setMancc(thongtin[2].trim()); // Lưu Mã NCC String
                pnh.setNgaynhan(thongtin[3].trim());
                pnh.setTongTien(tongtien);

                dspnh[n] = pnh; n++;
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file HoaDon.txt: " + e.getMessage());
        }
    }

    public void xuat(){
        String line = "+------------+------------+------------+-----------------+------------+";
        String format = "| %-10s | %-10s | %-10s | %-15s | %-10s |\n";
        System.out.println("\n--- Danh sách Phiếu Nhận Hàng ---");
        System.out.println(line);
        System.out.printf(format,"Mã PNH","Mã NV","Mã NCC","Ngày Nhận","Tổng tiền");
        System.out.println(line);
        for(int i = 0; i < n; i++){
            dspnh[i].xuat(); // Gọi hàm xuất đã được sửa để dùng đối tượng đã liên kết
        }
        System.out.println(line);
    }

    public void themMotPNH(PhieuNhapHang pnh) {
        if (maDuyNhat(pnh.getMaPNH())) {
            this.dspnh = Arrays.copyOf(this.dspnh, this.n + 1);
            this.dspnh[this.n] = new PhieuNhapHang(pnh);
            this.n++;
            ghiFilePNH(pnh);
        } else {
            System.out.println("❌ Lỗi: Mã PNH '" + pnh.getMaPNH() + "' đã tồn tại.");
        }
    }
    public int timViTriPNH(String mapnh) {
        for (int i = 0; i < n; i++) {
            if (dspnh[i] != null && dspnh[i].getMaPNH().equalsIgnoreCase(mapnh)) {
                return i; // Trả về vị trí (index)
            }
        }
        return -1;
    }

    public void xoaHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã phiếu nhập hàng cần xóa: ");
        String mspnh = sc.nextLine();
        int vitrixoa = timViTriPNH(mspnh);

        if (vitrixoa == -1) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + mspnh);
            return;
        }
        for (int i = vitrixoa; i < n - 1; i++) {
            dspnh[i] = dspnh[i + 1];
        }
        dspnh[n - 1] = null;
        n--;
        System.out.println("Đã xóa hóa đơn " + mspnh);
        ghiLaiToanBoFilePNH();
    }

}