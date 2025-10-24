import java.util.Scanner;
public class DoChoi extends VanPhongPham{
    private String the_loai;
    private String lua_tuoi;

    public DoChoi(){
    }
    public DoChoi(String ma_sp,String ten_sp, String don_vi_tinh,int don_gia,int so_luong, String the_loai,String lua_tuoi){
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
    @Override public void xuat(){
        super.xuat();
        System.out.println("The loại: "+ the_loai);
        System.out.println("Lua tuoi su dung: "+ lua_tuoi);
    }
}
