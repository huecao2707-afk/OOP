import java.util.Scanner;
public abstract class VanPhongPham {
    protected LoaiSanPham loaisp;
    protected String masp, tensp;
    protected int dongia;
    protected String donvitinh;
    protected int soluong;
    public VanPhongPham(){
    }
    public VanPhongPham(LoaiSanPham loaisp, String masp,String tensp,int dongia,String donvitinh, int soluong ){
        this.loaisp = loaisp;
        this.masp = masp;
        this.tensp = tensp;
        this.dongia = dongia;
        this.donvitinh = donvitinh;
        this.soluong = soluong;
    }
    public VanPhongPham(VanPhongPham x){
        this.loaisp = x.loaisp;
        this.masp = x.masp;
        this.tensp = x.tensp;
        this.dongia = x.dongia;
        this.donvitinh = x.donvitinh;
        this.soluong = x.soluong;
    }

    public LoaiSanPham getLoaiSP() {
        return loaisp;
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
    public void setLoaiSP(LoaiSanPham loaisp) {
        this.loaisp = loaisp;
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
        System.out.print("Đơn vị tính: ");
        donvitinh = sc.nextLine();
        soluong = 0;
        dongia = 0;
    }

    public void xuat() {
        String maloai = loaisp.getMaLoai();
        String format = "| %-8s | %-10s | %-20s | %-10d | %-13d | %-13s ";
        System.out.printf(format, maloai, masp, tensp, soluong, dongia, donvitinh);
    }
}