import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays; // Cần import để sử dụng Arrays.copyOf

public class DSCTHD { 
    int m;
    public ChiTietHoaDon[] dscthd; // Mảng chứa TẤT CẢ Chi tiết Hóa đơn của hệ thống

    public DSCTHD(){
        m = 0;
        dscthd = new ChiTietHoaDon[0]; // Khởi tạo mảng rỗng
    }
    public DSCTHD(int m, ChiTietHoaDon[] dscthd){
        this.m = m;
        this.dscthd = dscthd;
    }
    public DSCTHD(DSCTHD other){
        this.m = other.m;
        this.dscthd = new ChiTietHoaDon[m];
        for (int i = 0; i < m; i++){ // Dùng m thay vì dscthd.length để tránh lỗi nếu m nhỏ hơn capacity
            this.dscthd[i] = new ChiTietHoaDon(other.dscthd[i]);
        }
    }
        // =========================================================
        // 1. CÁC HÀM ĐỌC/GHI FILE
        // =========================================================
    
        public void docFileCTHD() {
            m = 0; 
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
                    
                    if (m >= dscthd.length) {
                        dscthd = Arrays.copyOf(dscthd, m + 1);
                    }
    
                    ChiTietHoaDon cthd = new ChiTietHoaDon();
                    cthd.setMaHoaDon(thongtin[0].trim());
                    cthd.setMaSP(thongtin[1].trim());                    
                    cthd.setSoLuong(soluong);
                    dscthd[m] = cthd; 
                    m++; 
                }
            } catch (IOException e) {
                System.out.println("❌ Lỗi đọc file ChiTietHoaDon.txt: " + e.getMessage());
            }
        }
    
    // Sửa trong class DSCTHD.java
    public void ghiFileMotChiTiet(ChiTietHoaDon cthd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietHoaDon.txt", true))) {
            
            // 1. Logic an toàn để lấy Mã SP, xử lý cả 2 trường hợp (đối tượng mới hoặc đối tượng đọc từ file)
            String maspantoan;
            if (cthd.getSanPham() != null) {
                // Trường hợp 1: Đối tượng mới, lấy mã từ đối tượng VanPhongPham
                maspantoan = cthd.getSanPham().getMaSP();
            } else {
                // Trường hợp 2: Đối tượng đọc từ file, lấy mã (String) đã lưu
                maspantoan = cthd.getMaSP();
            }
                
            String line = cthd.getMaHoaDon() + "," + maspantoan + "," + cthd.getSoLuong(); 
            
            bw.write(line);
            bw.newLine();
        } 
        catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm chi tiết vào file: " + e.getMessage());
        }
    }
    
    public void ghiLaiToanBoFileCTHD() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ChiTietHoaDon.txt", false))) {
            for (int i = 0; i < m; i++) {
                ChiTietHoaDon cthd = dscthd[i];
                if (cthd == null) continue; // Bỏ qua nếu phần tử cthd bị null

                // 1. Logic an toàn: Ưu tiên lấy mã từ đối tượng SP (nếu tồn tại)
                String maspantoan;
                if (cthd.getSanPham() != null) {
                    // Trường hợp 1: Đối tượng mới (được thêm khi chạy), 'sp' tồn tại.
                    maspantoan = cthd.getSanPham().getMaSP();
                } else {
                    // Trường hợp 2: Đối tượng đọc từ file, 'sp' là null.
                    // Lấy mã (String) đã được lưu khi đọc file.
                    maspantoan = cthd.getMaSP(); 
                }
                
                // 2. Sử dụng biến maspantoan
                String line = cthd.getMaHoaDon() + "," + maspantoan + "," + cthd.getSoLuong(); 
                
                bw.write(line);
                bw.newLine();
            }
            System.out.println("-> Đã cập nhật lại toàn bộ file ChiTietHoaDon.txt.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file CTHD: " + e.getMessage());
        }
    }    
        // =========================================================
        // 2. CÁC HÀM THAO TÁC DỮ LIỆU CHÍNH
        // =========================================================
    
        public void themMotChiTiet(ChiTietHoaDon cthd) {
            if (cthd == null) return;
            
            this.dscthd = Arrays.copyOf(this.dscthd, this.m + 1);
            
            this.dscthd[this.m] = new ChiTietHoaDon(cthd); 
            this.m++;
            
            // Ghi thêm vào file
            ghiFileMotChiTiet(cthd);
        }
        
        public ChiTietHoaDon[] timCTHDTheoMaHD(String mahd) {
            if (mahd == null || mahd.trim().isEmpty()) return null;
            
            // 1. Đếm số lượng CTHD có Mã HĐ trùng khớp
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (dscthd[i] != null && dscthd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                    count++;
                }
            }
    
            if (count == 0) return null; 
    
            // 2. Tạo mảng kết quả và sao chép các phần tử tìm thấy
            ChiTietHoaDon[] ketqua = new ChiTietHoaDon[count];
            int j = 0;
            for (int i = 0; i < m; i++) {
                if (dscthd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                    ketqua[j++] = dscthd[i];
                }
            }
            return ketqua;
        }
    
        public void xuat() {
            if (m == 0) {
                System.out.println("Danh sách chi tiết hóa đơn trống.");
                return;
            }
            System.out.println("----------- DANH SÁCH TẤT CẢ CHI TIẾT HÓA ĐƠN -----------");
            System.out.printf("%-10s | %-10s | %-10s | %-15s | %-15s\n", 
                "Mã HĐ", "Mã SP", "SL", "Đơn Giá (dự kiến)", "Thành Tiền (dự kiến)");
            System.out.println("-------------------------------------------------------------------------");
    
            for (int i = 0; i < m; i++) {
                // Cần hàm xuat() trong ChiTietHoaDon để hiển thị
                dscthd[i].xuatThongTinCT(i); 
            }
            System.out.println("-------------------------------------------------------------------------");
        }
    
        // =========================================================
        // 3. HÀM XÓA CTHD (THEO MÃ HĐ VÀ MÃ SP)
        // =========================================================
        public boolean xoaChiTiet(String mahd, String masp) {
            int index = -1;
            
            // 1. Tìm vị trí của CTHD cần xóa
            for (int i = 0; i < m; i++) {
                if (dscthd[i] != null && 
                    dscthd[i].getMaHoaDon().equalsIgnoreCase(mahd) && 
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
    
            // 2. Xóa khỏi mảng bằng cách dịch chuyển
            for (int i = index; i < m - 1; i++) {
                dscthd[i] = dscthd[i + 1];
            }
            dscthd[m - 1] = null;
            m--;
            
            // Giảm kích thước mảng trong bộ nhớ
            dscthd = Arrays.copyOf(dscthd, m); 
    
            System.out.println("✅ Đã xóa CTHD cho HĐ " + mahd + " (SP: " + masp + ").");
    
            // 3. Cập nhật lại toàn bộ file
            ghiLaiToanBoFileCTHD(); 
            return true;
        }
    
    }
