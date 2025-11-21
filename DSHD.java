import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DSHD {
    private int n;
    public HoaDon[] dshd;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public DSHD(){
        dshd = new HoaDon[100];
        n = 0;
    }
    public DSHD(int n,HoaDon[] dshd){
        this.n = n;
        this.dshd = dshd;
    }
   public DSHD(DSHD other) {
        this.n = other.n; 
        this.dshd = new HoaDon[other.dshd.length];
        for (int i = 0; i < this.n; i++) { 
            this.dshd[i] = new HoaDon(other.dshd[i]);
        }
    }

    public void ghiFileHoaDon(HoaDon hd){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("HoaDon.txt", true))) { 
            
            String manv = hd.getMaNV();        
            String makh = hd.getMaKH();
            String ngay = hd.getNgayLapHDString();
            
            String line = hd.getMaHD() + "," + manv + "," + makh + "," + ngay + "," + hd.getTongTien();

            bw.write(line);
            bw.newLine();
        } 
        catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
        }
    }

    public void ghiLaiToanBoFileHoaDon() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("HoaDon.txt", false))) { 
            for (int i = 0; i < n; i++) {
                HoaDon hd = dshd[i]; 
                
                String manv = hd.getMaNV();        
                String makh = hd.getMaKH();
                String ngay = hd.getNgayLapHDString();
                
                String line = hd.getMaHD() + "," + manv + "," + makh + "," + ngay + "," + hd.getTongTien();
                
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file: " + e.getMessage());
        }
    }
    
        public void docFileHoaDon() {
            n = 0;
            try (BufferedReader br = new BufferedReader(new FileReader("HoaDon.txt"))) {
                String line;
        
                while ((line = br.readLine()) != null) {
                    String[] thongtin = line.split(",");
        
                    if (thongtin.length < 5) {
                        System.out.println("Lỗi dữ liệu: Không đủ thông tin hóa đơn. Bỏ qua: " + line);
                        continue;
                    }
        
                    int tongtien;
                    try {
                        tongtien = Integer.parseInt(thongtin[4].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi dữ liệu: Tổng tiền không hợp lệ. Bỏ qua: " + line);
                        continue;
                    }
                    
                    HoaDon hd = new HoaDon();
                    
                    hd.setMaHD(thongtin[0].trim());
                    hd.setMaNV(thongtin[1].trim());
                    hd.setMaKH(thongtin[2].trim());
                    
                    try {
                        LocalDate ngay = LocalDate.parse(thongtin[3].trim(), DATE_FORMATTER);
                        hd.setNgayLapHD(ngay);
                    } catch (DateTimeParseException e) {
                        System.out.println("Lỗi dữ liệu: Ngày không hợp lệ. Gán là null: " + line);
                        hd.setNgayLapHD(null);
                    }
                    
                    hd.setTongTien(tongtien);
        
                    dshd[n] = hd; 
                    n++; 
        
                    if (n >= dshd.length) {
                        System.out.println("Cảnh báo: Đã đầy mảng lưu trữ hóa đơn.");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("❌ Lỗi đọc file HoaDon.txt: " + e.getMessage());
            }
        }

    public void nhap(NhanVien nvdangnhap) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong hoa don: ");
        int soluongmoi = sc.nextInt();
        sc.nextLine();

        this.dshd = Arrays.copyOf(this.dshd, this.n + soluongmoi);

        for (int i = 0; i < soluongmoi; i++) {
            System.out.println("----------HOA DON " + (this.n + i + 1) + "----------"); 
            HoaDon hd = new HoaDon();

            if (nvdangnhap != null) {
                hd.setMaNV(nvdangnhap.getMaNV());
            }
            
            hd.nhap();

            this.dshd[this.n + i] = hd;
        }

        this.n += soluongmoi;
    }

    public HoaDon timKiemTheoMa(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dshd[i] != null && dshd[i].getMaHD().equalsIgnoreCase(mahd)) {
                dshd[i].xuatHoaDonDayDu();
                return dshd[i]; 
            }
        }
        return null; 
    }
    
    public int timViTriHD(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dshd[i] != null && dshd[i].getMaHD().equalsIgnoreCase(mahd)) {
                return i; 
            }
        }
        return -1; 
    }
    
    public boolean maDuyNhat(String mahd){
        for(int i = 0; i < n; i++)
            if(dshd[i].getMaHD().equals(mahd))
                return false;
        return true;
    }
    
    public void themMotHoaDon(HoaDon hd) {
        if (maDuyNhat(hd.getMaHD())) { 
            
            if (n >= dshd.length) {
                 this.dshd = Arrays.copyOf(this.dshd, this.n + 1);
            }
           
            this.dshd[this.n] = new HoaDon(hd);
            this.n++;
            System.out.println("Đã thêm thành công 1 hóa đơn mới vào danh sách.");
        } else {
            System.out.println("❌ Lỗi: Mã hóa đơn '" + hd.getMaHD() + "' đã tồn tại. Không thể thêm.");
            return;
        }
        ghiFileHoaDon(hd);
    }
    
    public void xoaHoaDon(String mahd) {
        int vitrixoa = timViTriHD(mahd);

        if (vitrixoa == -1) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + mahd);
            return;
        }
        for (int i = vitrixoa; i < n - 1; i++) {
            dshd[i] = dshd[i + 1];
        }
        dshd[n - 1] = null;
        n--;
        System.out.println("Đã xóa hóa đơn " + mahd);
        ghiLaiToanBoFileHoaDon();
    }
    
    public void xuat(){
        if (n == 0) {
            System.out.println("Danh sách hóa đơn trống.");
            return;
        }
        String line = "+------------+------------+------------+-----------------+------------+";
        String format = "| %-10s | %-10s | %-10s | %-15s | %-10s |\n";
        System.out.println("\n--- Danh sách Đơn Hàng ---");
        System.out.println(line);
        System.out.printf(format,"Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
        System.out.println(line);
        for(int i = 0; i < n; i++){
            dshd[i].xuat();
        }
        System.out.println(line);

    }

    public void xuatHoaDonDayDu(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã hóa đơn: ");
        String ma = sc.nextLine();
        for(int i = 0; i < n; i++){
            if (dshd[i].getMaHD().contains(ma)) {
                dshd[i].xuatHoaDonDayDu();
            }
        }
    }
    public int getN() {
        return n; 
    }
}