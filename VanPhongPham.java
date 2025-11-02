
import java.util.Scanner;
public abstract class VanPhongPham {
    protected int dongia,soluong;
    protected String masp, tensp,donvitinh;
    protected LoaiSanPham maloai;
    public VanPhongPham(){
        maloai = null;
        masp = "";
        tensp = "";
        dongia = 0;
        donvitinh = "";
        soluong = 0;
    }
    public VanPhongPham(LoaiSanPham maloai, String masp,String tensp,int dongia,String donvitinh, int soluong  ){
        this.maloai = maloai;
        this.masp = masp;
        this.tensp = tensp;
        this.dongia = dongia;
        this.donvitinh = donvitinh;
        this.soluong = soluong;
    }
    public VanPhongPham(VanPhongPham x){
        this.maloai = x.maloai;
        this.masp = x.masp;
        this.tensp = x.tensp;
        this.dongia = x.dongia;
        this.donvitinh = x.donvitinh;
        this.soluong = x.soluong;
    }

    // Cac ham getter
    public LoaiSanPham getMaLoai() {
        return maloai;
    }
    public String getMaSP() {
        return masp;
    }
      public String getTenSP() {
        return tensp;
    }
    public int getDonGia() {
        return dongia;
    }
    public String getDonViTinh() {
        return donvitinh;
    }
    public int getSoLuong() {
        return soluong;
    }
    // Cac ham setter
    public void setMaLoai(LoaiSanPham maloai) {
        this.maloai = maloai;
    }
    public void setMaSP(String masp) {
        this.masp = masp;
    }
    public void setTenSP(String tensp) {
        this.tensp = tensp;
    }
    public void setDonGia(int dongia) {
        this.dongia = dongia;
    }
    public void setDonViTinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }
    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }

    
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tên SP: ");
        tensp = sc.nextLine();
        do {
            System.out.print("Số Lượng: ");
            while (!sc.hasNextInt()) {// Có phải nhập số nguyên hay không ?
                System.out.println("Lỗi: Vui lòng nhập số nguyên.");
                System.out.print("Số Lượng: ");
                sc.next();// bỏ input không hơp lệ
            }
            soluong = sc.nextInt();
            if (soluong < 0) System.out.println("Số lượng phải >= 0.");
        } while (soluong < 0);

        do {
            System.out.print("Đơn Giá: ");
             while (!sc.hasNextInt()) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên.");
                System.out.print("Đơn Giá: ");
                sc.next();
            }
            dongia = sc.nextInt();
            if (dongia < 0) System.out.println("Đơn giá phải >= 0.");
        } while (dongia < 0);

        sc.nextLine(); // Tránh trôi lệnh
        System.out.print("Đơn vị tính: ");
        donvitinh = sc.nextLine();
    }
    // public abstract void docDuLieu();
    public abstract void xuat();
    public abstract void ghiFile();
}
