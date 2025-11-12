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

    // Cac ham getter
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
    // Cac ham setter
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
        System.out.print("Số lượng: ");
        soluong = sc.nextInt();
        sc.nextLine();// tránh trôi
        System.out.print("Đơn Giá: ");
        dongia = sc.nextInt();
        sc.nextLine(); // Tránh trôi lệnh
        System.out.print("Đơn vị tính: ");
        donvitinh = sc.nextLine();
    }

    public void xuat() {
        System.out.println("\n--- DANH SÁCH TẤT CẢ SẢN PHẨM ---");

        // Chuỗi định dạng (6 cột)
        String format = "| %-15s | %-15s | %-20s | %-10s | %-10s | %-15s |\n";
        String line = "+-----------------+-----------------+----------------------+------------+------------+-----------------+";

        System.out.println(line);
        System.out.printf(format, 
                "Loại SP", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính");
        System.out.println(line);

        // Tránh lỗi null khi loaisp chưa được khởi tạo
        String maLoai = (loaisp == null) ? "Chưa có" : loaisp.getMaLoai();

        System.out.printf(format, 
                maLoai, 
                (masp == null ? "Chưa có" : masp), 
                (tensp == null ? "Chưa có" : tensp), 
                soluong, 
                dongia, 
                (donvitinh == null ? "Chưa có" : donvitinh)
        );
        System.out.println(line);
    }
}
