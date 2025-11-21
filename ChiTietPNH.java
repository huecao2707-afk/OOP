import java.util.Scanner;

public class ChiTietPNH {
    private String mapnh; 
    private String masp;
    private int dongia, soluong, thanhtien;

    public ChiTietPNH() {
    }

    public ChiTietPNH(String mapnh, String masp, int dongia, int soluong, int thanhtien) {
        this.mapnh = mapnh;
        this.masp = masp;
        this.dongia = dongia;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }

    public ChiTietPNH(ChiTietPNH x) {
        mapnh = x.mapnh;
        masp = x.masp;
        dongia = x.dongia;
        soluong = x.soluong;
        thanhtien = x.thanhtien;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        String maspcantim;
        VanPhongPham vpptimduoc = null;
        do {
            System.out.print("  > Nhập Mã sản phẩm cần nhập hàng: ");
            maspcantim = sc.nextLine().trim();
            if (maspcantim.isEmpty()) {
                System.out.println("  ❌ Lỗi: Mã SP không được trống. Nhập lại!");
                continue;
            }
            vpptimduoc = QuanLyBanHang.dsvpp.timMaSP(maspcantim);
            if (vpptimduoc != null) {
                this.masp = vpptimduoc.getMaSP();
                System.out.println("  🎯 Đã tìm thấy: " + vpptimduoc.getTenSP() + "."); 
                break;
            } else {
                System.out.println("  ❌ Lỗi: Không tìm thấy Sản phẩm có mã: " + maspcantim + ". Vui lòng nhập lại!");
            }
        } while (true);
        System.out.print("  > Nhập Đơn giá nhập: ");
        this.dongia = sc.nextInt();
        sc.nextLine();
        System.out.print("  > Nhập Số lượng nhập: ");
        this.soluong = sc.nextInt();
        sc.nextLine();
        this.thanhtien = this.soluong * this.dongia;
    }

    public void xuatThongTinCT(int stt) {
        String tensp;
        VanPhongPham sptamthoi = QuanLyBanHang.dsvpp.timMaSP(this.masp);
        if (sptamthoi != null) {
            tensp = sptamthoi.getTenSP();
        } else {
            tensp = "N/A (Không tìm thấy)";
        }
        System.out.printf("| %-5d | %-10s | %-30s | %-10d | %-9d | %-15d |\n",stt, masp, tensp, dongia, soluong, thanhtien);
    }

    public String getMaPNH() {
        return mapnh;
    }
    public String getMaSP() {
        return masp;
    }
    public int getDonGia() {
        return dongia;
    }
    public int getSoLuong() {
        return soluong;
    }
    public int getThanhTien() {
        return thanhtien;
    }

    public void setMaPNH(String mapnh) {
        this.mapnh = mapnh;
    }
    public void setMaSP(String masp) {
        this.masp = masp;
    }
    public void setDonGia(int dongia) {
        this.dongia = dongia;
    }
    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }
    public void setThanhTien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}