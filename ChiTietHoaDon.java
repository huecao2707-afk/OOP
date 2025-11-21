import java.util.Scanner;
public class ChiTietHoaDon {
    private String mahd;
    private String masp;
    private int dongia;
    private int soluong;
    private int thanhtien;
    public ChiTietHoaDon() {
        thanhtien = 0;
    }   

    public ChiTietHoaDon(String mahd, String masp, int dongia, int soluong, int thanhtien) {
        this.mahd = mahd;  
        this.masp = masp;
        this.dongia = dongia;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }

    public ChiTietHoaDon(ChiTietHoaDon a) {
        this.mahd = a.mahd;
        this.masp = a.masp;
        this.dongia = a.dongia;           
        this.soluong = a.soluong;
        this.thanhtien = a.thanhtien; 
    }

    public void nhap() { 
        Scanner sc = new Scanner(System.in);
        String maspcantim;
        VanPhongPham vpptimduoc = null;

        do {
            System.out.print("  > Nhập Mã sản phẩm cần mua: ");
            maspcantim = sc.nextLine();
            vpptimduoc = QuanLyBanHang.dsvpp.timMaSP(maspcantim); 
            if (vpptimduoc != null) {
                this.masp = maspcantim; 
                System.out.println("  🎯 Đã tìm thấy: " + vpptimduoc.getTenSP() + ".");
                this.dongia = vpptimduoc.getDonGia(); 
                System.out.printf("  > Giá Bán: %,d | Tồn Kho: %d%n", this.dongia, vpptimduoc.getSoLuong());
                int soluongcanmua;
                do {
                    System.out.print("  > Nhập Số lượng mua: ");
                    soluongcanmua = sc.nextInt();
                    sc.nextLine();
                    if (soluongcanmua <= 0 || soluongcanmua > vpptimduoc.getSoLuong()) {
                        System.out.println("  ❌ Lỗi: Số lượng không hợp lệ hoặc vượt quá tồn kho (" + vpptimduoc.getSoLuong() + "). Nhập lại!");
                    }
                } while (soluongcanmua <= 0 || soluongcanmua > vpptimduoc.getSoLuong());
                this.soluong = soluongcanmua;
                this.thanhtien = this.soluong * this.dongia;
                break;
            } else {
                System.out.println("❌ Lỗi: Không tìm thấy Sản phẩm có mã: " + maspcantim + ". Vui lòng nhập lại!");
            }
        } while (true);
    }

    public void xuatThongTinCT(int stt) {
        String tensp = "N/A";
        VanPhongPham vpp = QuanLyBanHang.dsvpp.timMaSP(this.masp);
        if (vpp != null) {
            tensp = vpp.getTenSP();
        }
        System.out.printf("| %-5d | %-10s | %-30s | %-10d | %-9d | %-15d |\n", stt, this.masp, tensp, dongia, soluong, thanhtien);
    }
    public void xuat(){
        System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-15s |\n" ,mahd, masp, soluong, dongia, thanhtien);

    }
    public String getMaHD() {
        return mahd;
    }
    
    public void setMaHD(String mahd) { 
        this.mahd = mahd; 
    }
    
    public int getDonGia() {
        return dongia;
    }
    
    public void setDonGia(int dongia) {
        this.dongia = dongia;
    }
    
    public int getSoLuong() {
        return soluong;
    }
    
    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }
    
    public int getThanhTien() {
        return thanhtien;
    }
        
    public void setThanhTien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getMaSP() {
        return masp;
    }
    
    public void setMaSP(String masp) {
        this.masp = masp;
    }
}