import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void setTheloai(String theloai) {
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
    @Override public void xuat() {
         System.out.printf("| %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n",
            masp,             // Cột 1: Mã SP (String)
            tensp,            // Cột 2: Tên SP (String)
            soluong,          // Cột 3: Số Lượng (int)
            dongia,           // Cột 4: Đơn Giá (int)
            donvitinh,       // Cột 5: Đơn Vị Tính (String)
            "",                // Cột 6: Chức Năng (String) - Để trống
            luatuoi,            // Cột 7: Độ Tuổi (String) - Có
            theloai            // Cột 8: Thể Loại (String) - Có
        );
    }
    @Override
    public void ghiFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt", true))) { //Ghi tiếp vào file
            String loai = "DC";
            String line = loai + "," +
                          this.masp + "," + this.tensp + "," + this.soluong + "," +
                          this.dongia + "," + this.donvitinh + "," + 
                          "" + "," + this.luatuoi + "," + this.theloai; //Cộng thêm các cột rỗng cho Phân Loại Chức Năng ở class VanPhong
            bw.write(line);
            bw.newLine(); //Xuống dòng cho sản phẩm tiếp theo

        }catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
