
import java.util.Scanner;
public class DoChoi extends VanPhongPham{ 

    private String theloai;
    private String luatuoi;

    public DoChoi(){
    }
    public DoChoi(LoaiSanPham loaisanpham, String masp,String tensp,int dongia, String donvitinh,int soluong, String theloai,String luatuoi){
        super(loaisanpham,masp, tensp, dongia,donvitinh,soluong);
        this.theloai = theloai;
        this.luatuoi = luatuoi;
    }
    public DoChoi(DoChoi other){
        super((VanPhongPham)other);
        this.luatuoi = other.luatuoi;
        this.theloai = other.theloai;
    }

    public String getLuaTuoi() {
        return luatuoi;
    }

    public String getTheLoai() {
        return theloai;
    }

    public void setLuaTuoi(String luatuoi) {
        this.luatuoi = luatuoi;
    }

    public void setTheLoai(String theloai) {
        this.theloai = theloai;
    }
    @Override public void nhap(){
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("Thể loại: ");
        theloai = sc.nextLine();
        System.out.print("Lứa tuổi: ");
        luatuoi = sc.nextLine();
    }
   
    @Override
    public void xuat() {
        String maloai = "DC"; // Giá trị mặc định nếu loaisp bị null
        if (this.getLoaiSP() != null) {
            maloai = this.getLoaiSP().getMaLoai();
        }
        String format = "| %-8s | %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n";

        System.out.printf(format,
                maloai,        // Cột 1: Loại SP (String)
                masp,          // Cột 2: Mã SP (String)
                tensp,         // Cột 3: Tên SP (String)
                soluong,       // Cột 4: Số Lượng (int)
                dongia,        // Cột 5: Đơn Giá (int)
                donvitinh,     // Cột 6: Đơn Vị Tính (String)
                "",            // Cột 7: Chức Năng (Để trống)
                luatuoi,       // Cột 8: Lứa Tuổi (String - Riêng của Đồ Chơi)
                theloai        // Cột 9: Thể Loại (String - Riêng của Đồ Chơi)
        );
    }
}
