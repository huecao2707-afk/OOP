import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays; // Cần import để sử dụng Arrays.copyOf

public class DSCTHD { 
int n; // <-- ĐÃ SỬA
    public ChiTietHoaDon[] dscthd; // Mảng chứa TẤT CẢ Chi tiết Hóa đơn của hệ thống

    public DSCTHD(){
        n = 0; // <-- ĐÃ SỬA
        dscthd = new ChiTietHoaDon[0]; // Khởi tạo mảng rỗng
    }
    public DSCTHD(int n, ChiTietHoaDon[] dscthd){ // <-- ĐÃ SỬA
        this.n = n; // <-- ĐÃ SỬA
        this.dscthd = dscthd;
    }
    public DSCTHD(DSCTHD other){
        this.n = other.n; // <-- ĐÃ SỬA
        this.dscthd = new ChiTietHoaDon[n]; // <-- ĐÃ SỬA
        for (int i = 0; i < n; i++){ // <-- ĐÃ SỬA
            this.dscthd[i] = new ChiTietHoaDon(other.dscthd[i]);
        }
    }
        // =========================================================
        // 1. CÁC HÀM ĐỌC/GHI FILE
        // =========================================================
    
    
    // Sửa trong class DSCTHD.java
    public void ghiFileMotChiTiet(ChiTietHoaDon cthd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietHoaDon.txt", true))) {
            
            String maspantoan = cthd.getMaSP();
                
            String line = cthd.getMaHD() + "," + maspantoan + "," + cthd.getSoLuong(); 
            
            bw.write(line);
            bw.newLine();
        } 
        catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm chi tiết vào file: " + e.getMessage());
        }
    }
    public void ghiLaiToanBoFileCTHD() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietHoaDon.txt", false))) {
            for (int i = 0; i < n; i++) { // <-- ĐÃ SỬA
                ChiTietHoaDon cthd = dscthd[i];
                if (cthd == null) continue; 

                String maspantoan = cthd.getMaSP(); 
                
                String line = cthd.getMaHD() + "," + maspantoan + "," + cthd.getSoLuong(); 
                
                bw.write(line);
                bw.newLine();
            }
            System.out.println("-> Đã cập nhật lại toàn bộ file ChiTietHoaDon.txt.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file CTHD: " + e.getMessage());
        }
    }

    public void docFileCTHD() {
        n = 0; // <-- ĐÃ SỬA
        try (BufferedReader br = new BufferedReader(new FileReader("ChiTietHoaDon.txt"))) {
            String line;
            while ((line = br.readLine()) != null) { 
                String[] thongtin = line.split(",");
                if (thongtin.length < 3) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin chi tiết hóa đơn. Bỏ qua: " + line);
                    continue;
                }
                int soluong;
                try {
                    soluong = Integer.parseInt(thongtin[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Số lượng không hợp lệ. Bỏ qua: " + line);
                    continue;
                }    
                if (n >= dscthd.length) { // <-- ĐÃ SỬA
                    dscthd = Arrays.copyOf(dscthd, n + 1); // <-- ĐÃ SỬA
                }
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaHD(thongtin[0].trim());
                cthd.setMaSP(thongtin[1].trim());                     
                cthd.setSoLuong(soluong);
                dscthd[n] = cthd; // <-- ĐÃ SỬA
                n++; // <-- ĐÃ SỬA
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file ChiTietHoaDon.txt: " + e.getMessage());
        }
    }   
        // =========================================================
        // 2. CÁC HÀM THAO TÁC DỮ LIỆU CHÍNH
        // =========================================================
    
        public void themMotChiTiet(ChiTietHoaDon cthd) {
            if (cthd == null) return;
            
            this.dscthd = Arrays.copyOf(this.dscthd, this.n + 1);
            
            this.dscthd[this.n] = new ChiTietHoaDon(cthd); 
            this.n++;
            
            // Ghi thêm vào file
            ghiFileMotChiTiet(cthd);
        }
        
        public ChiTietHoaDon[] timCTHDTheoMaHD(String mahd) {
            if (mahd == null || mahd.trim().isEmpty()) return null;
            
            int count = 0;
            for (int i = 0; i < n; i++) { // <-- ĐÃ SỬA
                if (dscthd[i] != null && dscthd[i].getMaHD().equalsIgnoreCase(mahd)) {
                    count++;
                }
            }
    
            if (count == 0) return null; 
    
            ChiTietHoaDon[] ketqua = new ChiTietHoaDon[count];
            int j = 0;
            for (int i = 0; i < n; i++) { // <-- ĐÃ SỬA
                if (dscthd[i].getMaHD().equalsIgnoreCase(mahd)) {
                    ketqua[j++] = dscthd[i];
                }
            }
            return ketqua;
        }
    
        public void xuat() {
            if (n == 0) { // <-- ĐÃ SỬA
                System.out.println("Danh sách chi tiết hóa đơn trống.");
                return;
            }
            System.out.println("----------- DANH SÁCH TẤT CẢ CHI TIẾT HÓA ĐƠN -----------");
            System.out.printf("%-10s | %-10s | %-10s | %-15s | %-15s\n", 
                "Mã HĐ", "Mã SP", "SL", "Đơn Giá (dự kiến)", "Thành Tiền (dự kiến)");
            System.out.println("-------------------------------------------------------------------------");
    
            for (int i = 0; i < n; i++) { // <-- ĐÃ SỬA
                dscthd[i].xuatThongTinCT(i + 1); 
            }
            System.out.println("-------------------------------------------------------------------------");
        }
    
        // =========================================================
        // 3. HÀM XÓA CTHD (THEO MÃ HĐ VÀ MÃ SP)
        // =========================================================
        public boolean xoaChiTiet(String mahd, String masp) {
            int index = -1;
            
            for (int i = 0; i < n; i++) { // <-- ĐÃ SỬA
                if (dscthd[i] != null && 
                    dscthd[i].getMaHD().equalsIgnoreCase(mahd) && 
                    dscthd[i].getMaSP().equalsIgnoreCase(masp)) 
                {
                    index = i;
                    break;
                }
            }
            
            if (index == -1) {
                System.out.println("❌ Không tìm thấy chi tiết hóa đơn cần xóa.");
                return false;
            }
    
            for (int i = index; i < n - 1; i++) { // <-- ĐÃ SỬA
                dscthd[i] = dscthd[i + 1];
            }
            dscthd[n - 1] = null; // <-- ĐÃ SỬA
            n--; // <-- ĐÃ SỬA
            
            dscthd = Arrays.copyOf(dscthd, n); // <-- ĐÃ SỬA
    
            System.out.println("✅ Đã xóa CTHD cho HĐ " + mahd + " (SP: " + masp + ").");
            ghiLaiToanBoFileCTHD(); 
            return true;
        }
    
    }
