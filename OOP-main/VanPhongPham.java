
import java.util.Scanner;
public abstract class VanPhongPham {
    protected int don_gia,so_luong;
    protected String ma_sp, ten_sp,don_vi_tinh;
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
    
    public void nhap(DSVPP dsvpp){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Mã SP: ");
            this.ma_sp = sc.nextLine();
            if (dsvpp.maDuyNhat(ma_sp)) {
                break; // Mã duy nhất, thoát vòng lặp
            } else {
                System.out.println("❌ Mã SP '" + ma_sp + "' đã tồn tại! Vui lòng nhập mã khác.");    
            }
        }
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
    public abstract void docDuLieu(String[] parts);
    public abstract void xuat();
}
