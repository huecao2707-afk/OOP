import java.util.Scanner;
public class DoChoi extends VanPhongPham{ 

    private String the_loai;
    private String lua_tuoi;

    public DoChoi(){
    }
    public DoChoi(String ma_sp,String ten_sp, int so_luong,int don_gia, String don_vi_tinh, String the_loai,String lua_tuoi){
        super(don_gia, so_luong, ma_sp, ten_sp, don_vi_tinh);
        this.the_loai = the_loai;
        this.lua_tuoi = lua_tuoi;
    }
    public DoChoi(DoChoi other){
        super((VanPhongPham)other);
        this.lua_tuoi = other.lua_tuoi;
        this.the_loai = other.the_loai;
    }

    public String getLua_tuoi() {
        return lua_tuoi;
    }

    public String getThe_loai() {
        return the_loai;
    }

    public void setLua_tuoi(String lua_tuoi) {
        this.lua_tuoi = lua_tuoi;
    }

    public void setThe_loai(String the_loai) {
        this.the_loai = the_loai;
    }
    @Override public void nhap(){
        super.nhap();
        Scanner sc = new Scanner(System.in);
        System.out.print("The loai: ");
        the_loai = sc.nextLine();
        System.out.print("Lua tuoi: ");
        lua_tuoi = sc.nextLine();
    }
    @Override public void xuat() {
         System.out.printf("| %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n",
            ma_sp,             // Cột 1: Mã SP (String)
            ten_sp,            // Cột 2: Tên SP (String)
            so_luong,          // Cột 3: Số Lượng (int)
            don_gia,           // Cột 4: Đơn Giá (int)
            don_vi_tinh,       // Cột 5: Đơn Vị Tính (String)
            "",                // Cột 6: Chức Năng (String) - Để trống
            lua_tuoi,            // Cột 7: Độ Tuổi (String) - Có
            the_loai            // Cột 8: Thể Loại (String) - Có
        );
    }
    @Override
    public void docDuLieu(String[] parts) {
        // File: DC,SP002,Xe hơi,5,250000,chiếc,Nhựa,6+
        try {
            this.ma_sp = parts[1];
        this.ten_sp = parts[2];
        this.so_luong = Integer.parseInt(parts[3]);
        this.don_gia = Integer.parseInt(parts[4]);
        this.don_vi_tinh = parts[5];
        this.lua_tuoi = parts[6]; 
        this.the_loai = parts[7];  
        } catch (Exception e) {
            System.out.println("Lỗi đọc dòng DoChoi!");
        }
    }
}
