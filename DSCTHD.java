import java.util.Scanner;
public class DoChoi extends VanPhongPham{ 

    private String theloai;
    private String luatuoi;

    public DoChoi(){
    }
    public DoChoi(String masp,String tensp, int soluong,int dongia, String donvitinh, String theloai,String luatuoi){
        super(dongia, soluong, masp, tensp, donvitinh);
        this.theloai = theloai;
        this.luatuoi = luatuoi;
    }
    public DoChoi(DoChoi other){
        super((VanPhongPham)other);
        this.luatuoi = other.luatuoi;
        this.theloai = other.theloai;
    }

    public String getLuatuoi() {
        return luatuoi;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setLuatuoi(String luatuoi) {
        this.luatuoi = luatuoi;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
    @Override public void nhap(DSVPP dsvpp){
        super.nhap(dsvpp);
        Scanner sc = new Scanner(System.in);
        System.out.print("The loai: ");
        theloai = sc.nextLine();
        System.out.print("Lua tuoi: ");
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
    public void docDuLieu(String[] parts) {
        // File: DC,SP002,Xe hơi,5,250000,chiếc,Nhựa,6+
        try {
            this.masp = parts[1];
        this.tensp = parts[2];
        this.soluong = Integer.parseInt(parts[3]);
        this.dongia = Integer.parseInt(parts[4]);
        this.donvitinh = parts[5];
        this.luatuoi = parts[6];
        this.theloai = parts[7];
        } catch (Exception e) {
            System.out.println("Lỗi đọc dòng DoChoi!");
        }
    }
}
