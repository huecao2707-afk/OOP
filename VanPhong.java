import java.util.Scanner;
public class VanPhong extends VanPhongPham{
    private String phan_loai_chuc_nang;
    public VanPhong(){
    }
    public VanPhong(String ma_sp,String ten_sp, String don_vi_tinh,int don_gia,int so_luong, String phan_loai_chuc_nang){
        super(don_gia, so_luong, ma_sp, ten_sp, don_vi_tinh);
        this.phan_loai_chuc_nang = phan_loai_chuc_nang;
    }
    public VanPhong(VanPhong other){
        super((VanPhongPham)other);
        this.phan_loai_chuc_nang = other.phan_loai_chuc_nang;
    }
    public String getPhanLoaiChucNang() {
        return phan_loai_chuc_nang;
    }

    public void setPhanLoaiChucNang(String phan_loai_chuc_nang) {
        this.phan_loai_chuc_nang = phan_loai_chuc_nang;
    }
    @Override public void nhap(){
        super.nhap();
        System.out.print("Phan loai chuc nang: ");
        Scanner sc = new Scanner(System.in);
        phan_loai_chuc_nang = sc.nextLine();
    }
    @Override public void xuat(){
        super.xuat();
        System.out.println("Phan loai chuc nang: " + phan_loai_chuc_nang);
    }
    // public static void main(String[] args) {
    //     VanPhong vp = new VanPhong();
    //     vp.nhap();
    //     vp.xuat();
    // }
}
