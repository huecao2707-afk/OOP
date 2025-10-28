import java.util.Scanner;
public class VanPhong extends VanPhongPham{
    private String phanloaichucnang;
    public VanPhong(){
    }
    public VanPhong(String masp,String tensp, String donvitinh,int dongia,int soluong, String phanloaichucnang){
        super(dongia, soluong, masp, tensp, donvitinh);
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
    // @Override public void nhap(DSVPP dsvpp){
    //     super.nhap(dsvpp);
    //     System.out.print("Phan loai chuc nang: ");
    //     Scanner sc = new Scanner(System.in);
    //     phanloaichucnang = sc.nextLine();
    // }

  @Override
    public void xuat() {
       System.out.printf("| %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n",
            masp,             // Cột 1: Mã SP (String)
            tensp,            // Cột 2: Tên SP (String)
            soluong,          // Cột 3: Số Lượng (int)
            dongia,           // Cột 4: Đơn Giá (int)
            donvitinh,       // Cột 5: Đơn Vị Tính (String)
            phanloaichucnang,          // Cột 6: Chức Năng (String) - Có
            "",                // Cột 7: Độ Tuổi (String) - Để trống
            ""                 // Cột 8: Thể Loại (String) - Để trống
        );
    }
    @Override
    public void docDuLieu(String[] parts) {
        try {
            this.masp = parts[1];
            this.tensp = parts[2];
            this.soluong = Integer.parseInt(parts[3]);
            this.dongia = Integer.parseInt(parts[4]);
            this.donvitinh = parts[5];
            this.phanloaichucnang = parts[6];
        } catch (Exception e) {
            System.out.println("Lỗi đọc dòng VanPhong!");
        }
    }
}

