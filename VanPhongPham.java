
import java.util.Scanner;
public abstract class VanPhongPham {
    protected int dongia,soluong;
    protected String masp, tensp,donvitinh;
    public VanPhongPham(){
        masp = "";
        tensp = "";
        dongia = 0;
        donvitinh = "";
        soluong = 0;
    }
    public VanPhongPham( String masp,String tensp,int dongia,String donvitinh, int soluong  ){
        this.masp = masp;
        this.tensp = tensp;
        this.dongia = dongia;
        this.donvitinh = donvitinh;
        this.soluong = soluong;
    }
    public VanPhongPham(VanPhongPham x){
        this.masp = x.masp;
        this.tensp = x.tensp;
        this.dongia = x.dongia;
        this.donvitinh = x.donvitinh;
        this.soluong = x.soluong;
    }

    public int getDonGia() {
        return dongia;
    }

    public int getSoLuong() {
        return soluong;
    }

    public String getDonViTinh() {
        return donvitinh;
    }

    public String getMaSP() {
        return masp;
    }

    public String getTenSP() {
        return tensp;
    }

    public void setDonGia(int dongia) {
        this.dongia = dongia;
    }

    public void setDonViTinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public void setMaSp(String masp) {
        this.masp = masp;
    }

    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }

    public void setTenSP(String tensp) {
        this.tensp = tensp;
    }
    
    public void nhap(DSVPP dsvpp){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Mã SP: ");
            this.masp = sc.nextLine();
            if (dsvpp.maDuyNhat(masp)) {
                break; // Mã duy nhất, thoát vòng lặp
            } else {
                System.out.println("❌ Mã SP '" + masp + "' đã tồn tại! Vui lòng nhập mã khác.");
            }
        }
        System.out.print("Ten SP: ");
        tensp = sc.nextLine();
        System.out.print("Don gia: ");
        dongia = sc.nextInt();
        sc.nextLine();
        System.out.print("So luong: ");
        soluong = sc.nextInt();
        sc.nextLine();
        System.out.print("Don vi tinh: ");
        donvitinh = sc.nextLine();
    }
    public abstract void docDuLieu(String[] parts);
    public abstract void xuat();
}
