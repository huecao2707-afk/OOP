import java.util.Scanner;

public class ChiTietPNH{
    private String mapnh;// ma phieu nhan hang
    private VanPhongPham sp; // Lưu đối tượng sản phẩm
    private int dongia, soluong, thanhtien;
    private String masp; // Lưu mã SP String để ghi file

    public ChiTietPNH(){}

    // Constructor cho đối tượng đầy đủ
    public ChiTietPNH(String mapnh, VanPhongPham sp, int dongia, int soluong, int thanhtien){
        this.mapnh = mapnh;
        this.sp = sp;
        this.dongia = dongia;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
        this.masp = sp.getMaSP();
    }

    // Constructor cho việc copy hoặc đọc file (chỉ có mã SP)
    public ChiTietPNH(ChiTietPNH x){
        mapnh = x.mapnh;
        sp = x.sp; // Sao chép đối tượng SP
        dongia = x.dongia;
        soluong = x.soluong;
        thanhtien = x.thanhtien;
        masp = x.masp; // Sao chép mã SP String
    }

    public void nhap(){
        Scanner sc = new Scanner(System.in);
        String maspcantim;
        VanPhongPham vpptimduoc = null;

        do {
            System.out.print("  > Nhập Mã sản phẩm cần nhập hàng: ");
            maspcantim = sc.nextLine();

            // SỬ DỤNG QuanLyBanHang.dsvpp để tra cứu sản phẩm
            vpptimduoc = QuanLyBanHang.dsvpp.timMaSP(maspcantim);

            if (vpptimduoc != null) {
                this.sp = vpptimduoc;
                this.masp = vpptimduoc.getMaSP(); // Lưu mã String

                System.out.println("  🎯 Đã tìm thấy: " + vpptimduoc.getTenSP() + ".");

                // Giá nhập thường khác Giá bán
                System.out.print("  > Nhập Đơn giá nhập: ");
                this.dongia = sc.nextInt();
                sc.nextLine();

                int soluongcanmua;
                do {
                    System.out.print("  > Nhập Số lượng nhập: ");
                    soluongcanmua = sc.nextInt();
                    sc.nextLine();

                    if (soluongcanmua <= 0) {
                        System.out.println("  ❌ Lỗi: Số lượng phải lớn hơn 0. Nhập lại!");
                    }
                } while (soluongcanmua <= 0);

                this.soluong = soluongcanmua;
                this.thanhtien = this.soluong * this.dongia;
                break;
            } else {
                System.out.println("❌ Lỗi: Không tìm thấy Sản phẩm có mã: " + maspcantim + ". Vui lòng nhập lại!");
            }
        } while (true);
    }

    // Hàm xuất chi tiết theo format (để dùng trong xuatPhieuDayDu)
    public void xuatThongTinCT(int stt) {
        // Cần phải tra cứu Tên SP nếu đối tượng 'sp' là null (khi đọc từ file)
        String tenSP = "N/A (Lỗi Tra Cứu)";
        if (sp != null) {
            tenSP = sp.getTenSP();
        } else {
            // Tra cứu từ mã SP String (khi đọc file, đối tượng sp chưa được liên kết)
            // VanPhongPham sp_temp = QuanLyBanHang.dsvpp.timMaSP(this.masp);
            // if (sp_temp != null) tenSP = sp_temp.getTenSP();
            tenSP = this.masp + " (Cần liên kết)";
        }

        System.out.printf("| %-5d | %-10s | %-30s | %-10d | %-9d | %-15d |\n",
                stt, masp, tenSP, dongia, soluong, thanhtien);
    }

    // Getters/Setters
    public String getMaPNH() { return mapnh; }
    public void setMaPNH(String mapnh) { this.mapnh = mapnh; }
    public String getMaSP() { return masp; }
    public void setMaSP(String masp) { this.masp = masp; }
    public VanPhongPham getSanPham() { return sp; }
    public void setSanPham(VanPhongPham sp) { this.sp = sp; }
    public int getDonGia() { return dongia; }
    public void setDonGia(int dongia) { this.dongia = dongia; }
    public int getSoLuong() { return soluong; }
    public void setSoLuong(int soluong) { this.soluong = soluong; }
    public int getThanhTien() { return thanhtien; }
    public void setThanhTien(int thanhtien) { this.thanhtien = thanhtien; }
}