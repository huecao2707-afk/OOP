import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
public class VanPhong extends VanPhongPham{
    private String phanloaichucnang;
    public VanPhong(){
    }
    public VanPhong(LoaiSanPham loaiSanPham, String masp,String tensp, int dongia,String donvitinh,int soluong, String phanloaichucnang){
        super(loaiSanPham,masp,tensp,dongia,donvitinh,soluong);
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
       System.out.printf("| %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n",
            masp,             // Cột 1: Mã SP (String)
            tensp,            // Cột 2: Tên SP (String)
            soluong,          // Cột 3: Số Lượng (int)
            dongia,           // Cột 4: Đơn Giá (int)
            donvitinh,        //Cột 5: Đơn Vị Tính (String)
            phanloaichucnang, // Cột 6: Chức Năng (String) - Có
            "",               // Cột 7: Độ Tuổi (String) - Để trống
            ""                // Cột 8: Thể Loại (String) - Để trống
        );
    }
    @Override
    public void ghiFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt", true))) { //Ghi tiếp vào file
            String loai = "VP";
            String line = loai + "," +
                          this.masp + "," + this.tensp + "," + this.soluong + "," +
                          this.dongia + "," + this.donvitinh + "," + 
                          this.phanloaichucnang + "," + "" + "," + ""; //Cộng thêm các cột rỗng cho Độ Tuổi và Thể Loại ở class DoChoi
            // Ghi dòng mới vào cuối file
            bw.write(line);
            bw.newLine(); //Xuống dòng cho sản phẩm tiếp theo

        }catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}

