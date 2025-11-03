
import java.util.Scanner;
public class VanPhong extends VanPhongPham{
    private String phanloaichucnang;
    public VanPhong(){
    }
    public VanPhong(LoaiSanPham loaisanpham, String masp,String tensp, int dongia,String donvitinh,int soluong, String phanloaichucnang){
        super(loaisanpham,masp,tensp,dongia,donvitinh,soluong);
        this.phanloaichucnang = phanloaichucnang;
    }
    public VanPhong(VanPhong other){
        super((VanPhongPham)other);
        this.phanloaichucnang = other.phanloaichucnang;
    }
    public String getPhanLoaiChucNang() {
        return phanloaichucnang;
    }
    public void setPhanLoaiChucNang(String phanloaichucnang) {
        this.phanloaichucnang = phanloaichucnang;
    }
    @Override public void nhap(){
        super.nhap();
        System.out.print("Phân loại chức năng: ");
        Scanner sc = new Scanner(System.in);
        phanloaichucnang = sc.nextLine();
    }
    @Override
    public void xuat() {
        String maloai = "VP";
            if (this.getLoaiSP() != null) 
            // 3. Nếu ĐÚNG: Lấy mã loại từ đối tượng
                maloai = this.getLoaiSP().getMaLoai();

        // Chuỗi định dạng 9 cột (Giống hệt DSVPP)
        String format = "| %-8s | %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n";

        System.out.printf(format,
                maloai,            // Cột 1: Loại SP
                masp,              // Cột 2: Mã SP
                tensp,             // Cột 3: Tên SP
                soluong,           // Cột 4: Số Lượng (dùng %d)
                dongia,            // Cột 5: Đơn Giá (dùng %d)
                donvitinh,         // Cột 6: Đơn Vị Tính
                phanloaichucnang,  // Cột 7: Chức Năng (Có)
                "",                // Cột 8: Độ Tuổi (Trống)
                ""                 // Cột 9: Thể Loại (Trống)
        );
    }
}
