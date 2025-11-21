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
        super.xuat(); 
        String format = "| %-20s | %-10s | %-10s |\n";
        System.out.printf(format,phanloaichucnang,"","");
    }
}