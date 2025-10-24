import java.util.Scanner;
public class ChiTietHoaDon{
    private VanPhongPham ma_sp;
    private HoaDon ma_hoa_don;
    private VanPhongPham don_gia;
    private VanPhongPham so_luong;
    private int thanhtien;
    public ChiTietHoaDon(){
        thanhtien=0;
    }
    public ChiTietHoaDon(HoaDon ma_hoa_don, VanPhongPham ma_sp, VanPhongPham so_luong, VanPhongPham don_gia, int thanhtien){
        this.ma_hoa_don=ma_hoa_don;
        this.ma_sp=ma_sp;
        this.so_luong=so_luong;
        this.don_gia=don_gia;
        this.thanhtien=thanhtien;
    }
    public ChiTietHoaDon(ChiTietHoaDon a){
        this.ma_hoa_don=a.ma_hoa_don;
        this.ma_sp=a.ma_sp;
        this.so_luong=a.so_luong;
        this.don_gia=a.don_gia;
        this.thanhtien=a.thanhtien;
    }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        ma_hoa_don=new HoaDon();
        ma_hoa_don.nhap();
        System.out.println("Nhap ma san pham: ");
        ma_sp=new VanPhongPham();
        ma_sp.nhap();
        System.out.println("Nhap so luong: ");
        so_luong=new VanPhongPham();
        so_luong.nhap();
        System.out.println("Nhap don gia: ");
        don_gia=new VanPhongPham();
        don_gia.nhap();
        thanhtien=so_luong.getSoLuong()*don_gia.getDonGia();
    }
    public void xuat(){
        System.out.println("Ma hoa don: ");
        ma_hoa_don.xuat();
        System.out.println("Ma san pham: ");
        ma_sp.xuat();
        System.out.println("So luong: ");
        so_luong.xuat();
        System.out.println("Don gia: ");
        don_gia.xuat();
        System.out.println("Thanh tien: "+thanhtien);
    }
    public int getThanhTien(){
        return thanhtien;
    }
    public static void main(String[] args){
        ChiTietHoaDon cthd=new ChiTietHoaDon();
        cthd.nhap();
        cthd.xuat();
    }

}
