import java.util.Scanner;
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
    
    // ===============================================
    // HÀM MỚI: THÊM MỘT CHI TIẾT HÓA ĐƠN VÀO DANH SÁCH TOÀN HỆ THỐNG
    // (Được gọi từ QLHD.themMotHoaDonMoi() sau khi đã nhập CTHD)
    // ===============================================
    public void themMotChiTiet(ChiTietHoaDon cthd) {
        // Sử dụng Arrays.copyOf để tăng kích thước mảng hiệu quả hơn
        this.dscthd = Arrays.copyOf(this.dscthd, this.m + 1);
        
        // Thêm chi tiết hóa đơn mới
        this.dscthd[this.m] = new ChiTietHoaDon(cthd); // Thêm bản sao
        this.m++;
    }

    // ===============================================
    // HÀM MỚI: TÌM CÁC CTHD DỰA TRÊN MÃ HÓA ĐƠN
    // (Được gọi từ HoaDon.xuatHoaDonDayDu())
    // ===============================================
    public ChiTietHoaDon[] timCTHDTheoMaHD(String mahd) {
        // 1. Đếm số lượng CTHD có Mã HĐ trùng khớp
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (dscthd[i] != null && dscthd[i].getMahoadon().equalsIgnoreCase(mahd)) {
                count++;
            }
        }

        if (count == 0) return null; // Không tìm thấy

        // 2. Tạo mảng kết quả và sao chép các phần tử tìm thấy
        ChiTietHoaDon[] ketQua = new ChiTietHoaDon[count];
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (dscthd[i].getMahoadon().equalsIgnoreCase(mahd)) {
                ketQua[j++] = dscthd[i];
            }
        }
        return ketQua;
    }
    
    // --- CÁC HÀM CŨ (Cần cập nhật logic sửa/xóa nếu muốn dùng) ---
    // Lưu ý: Các hàm sua(), xoa(), them() dưới đây không được khuyến nghị
    // cho việc quản lý CTHD toàn hệ thống vì chúng không cập nhật Tổng tiền trong HoaDon.
    
    // Hàm them() cũ có thể đổi tên thành 'nhapVaThemVaoHeThong' nếu muốn giữ lại
    public void nhapVaThemVaoHeThong() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== NHẬP MỘT CHI TIẾT HÓA ĐƠN RIÊNG LẺ ===");
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        cthd.nhap(); 
        
        // Hàm này KHÔNG có Mã HĐ, nên không nên dùng để tạo CTHD cho Hóa đơn cụ thể.
        // Chỉ nên dùng themMotChiTiet(cthd) từ QLHD.
        
        this.dscthd = Arrays.copyOf(this.dscthd, this.m + 1);
        this.dscthd[this.m] = cthd;
        this.m++;
        System.out.println(">> Đã thêm chi tiết hóa đơn mới thành công!");
    }
    
    // Hàm sua() cũ (Giữ nguyên, nhưng không nên dùng)
    // public void sua() {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.print("Nhập mã sản phẩm cần sửa: ");
    //     String ma = sc.nextLine();

    //     int index = -1;
    //     for (int i = 0; i < m; i++) {
    //         if (dscthd[i].getMasp() != null && (dscthd[i].getMasp()).equalsIgnoreCase(ma)) { 
    //             index = i;
    //             break;
    //         }
    //     }
    //     if (index == -1) {
    //         System.out.println(">> Không tìm thấy mã sản phẩm cần sửa!");
    //         return;
    //     }

    //     System.out.println("=== NHẬP LẠI THÔNG TIN MỚI ===");
    //     dscthd[index].nhap();
    //     System.out.println(">> Đã cập nhật chi tiết hóa đơn thành công!");
    // }
    // Hàm xoa() cũ (Giữ nguyên, nhưng không nên dùng)
    public void xoa() {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.print("Nhập mã sản phẩm cần xóa: ");
    //     String ma = sc.nextLine();

    //     int index = -1;
    //     for (int i = 0; i < m; i++) {
    //         if (dscthd[i].masp != null && dscthd[i].masp.getMaSP().equalsIgnoreCase(ma)) {
    //             index = i;
    //             break;
    //         }
    //     }

    //     if (index == -1) {
    //         System.out.println(">> Không tìm thấy mã sản phẩm cần xóa!");
    //         return;
    //     }

    //     // Tạo mảng mới bỏ phần tử bị xóa
    //     ChiTietHoaDon[] temp = new ChiTietHoaDon[m - 1];
    //     int j = 0;
    //     for (int i = 0; i < m; i++) {
    //         if (i != index) {
    //             temp[j++] = dscthd[i];
    //         }
    //     }
    //     dscthd = temp;
    //     m--;
    //     System.out.println(">> Đã xóa chi tiết hóa đơn thành công!");
    // }
}}