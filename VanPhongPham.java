import java.util.Scanner;
public abstract class VanPhongPham {
    private int don_gia,so_luong;
    private String ma_sp, ten_sp,don_vi_tinh;
    public VanPhongPham(){
        don_gia = 0;
        so_luong = 0;
        ma_sp = "";
        ten_sp = "";
        don_vi_tinh = "";
    }
    public VanPhongPham(int don_gia, int so_luong, String ma_sp, String ten_sp, String don_vi_tinh){
        this.don_gia = don_gia;
        this.so_luong = so_luong;
        this.ma_sp = ma_sp;
        this.ten_sp = ten_sp;
        this.don_vi_tinh = don_vi_tinh;
    }
    public VanPhongPham(VanPhongPham x){
        this.don_gia = x.don_gia;
        this.so_luong = x.so_luong;
        this.ma_sp = x.ma_sp;
        this.ten_sp = x.ten_sp;
        this.don_vi_tinh = x.don_vi_tinh;
    }

    public int getDonGia() {
        return don_gia;
    }

    public int getSoLuong() {
        return so_luong;
    }

    public String getDonViTinh() {
        return don_vi_tinh;
    }

    public String getMaSP() {
        return ma_sp;
    }

    public String getTenSP() {
        return ten_sp;
    }

    public void setDonGia(int don_gia) {
        this.don_gia = don_gia;
    }

    public void setDonViTinh(String don_vi_tinh) {
        this.don_vi_tinh = don_vi_tinh;
    }

    public void setMaSp(String ma_sp) {
        this.ma_sp = ma_sp;
    }

    public void setSoLuong(int so_luong) {
        this.so_luong = so_luong;
    }

    public void setTenSP(String ten_sp) {
        this.ten_sp = ten_sp;
    }
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma SP: ");
        ma_sp = sc.nextLine();
        System.out.print("Ten SP: ");
        ten_sp = sc.nextLine();
        System.out.print("Don gia: ");
        don_gia = sc.nextInt();
        sc.nextLine();
        System.out.print("So luong: ");
        so_luong = sc.nextInt();
        sc.nextLine();
        System.out.print("Don vi tinh: ");
        don_vi_tinh = sc.nextLine();
    }
    public void xuat(){
        System.out.println("Ma sp: " +ma_sp);
        System.out.println("Ten sp: " +ten_sp);
        System.out.println("Don gia: " +don_gia);
        System.out.println("So luong: " + so_luong);
        System.out.println("Don vi tinh: " + don_vi_tinh);
    }
}
