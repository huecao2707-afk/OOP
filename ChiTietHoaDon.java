import java.util.Scanner;
public class ChiTietHoaDon{
    private VanPhongPham masp;
    private HoaDon mahoadon;
    private VanPhongPham dongia;
    private VanPhongPham soluong;
    private int thanhtien;
    public ChiTietHoaDon(){
        thanhtien=0;
    }
    public ChiTietHoaDon(HoaDon mahoadon, VanPhongPham masp, VanPhongPham soluong, VanPhongPham dongia, int thanhtien){
        this.mahoadon=mahoadon;
        this.masp=masp;
        this.soluong=soluong;
        this.dongia=dongia;
        this.thanhtien=thanhtien;
    }
    public ChiTietHoaDon(ChiTietHoaDon a){
        this.mahoadon=a.mahoadon;
        this.masp=a.masp;
        this.soluong=a.soluong;
        this.dongia=a.dongia;
        this.thanhtien=a.thanhtien;
    }
//    public void nhap(){
//        Scanner sc=new Scanner(System.in);
//        mahoadon=new HoaDon();
//        mahoadon.nhap();
//        System.out.println("Nhap ma san pham: ");
//        masp=new VanPhongPham();
//        masp.nhap();
//        System.out.println("Nhap so luong: ");
//        soluong=new VanPhongPham();
//        soluong.nhap();
//        System.out.println("Nhap don gia: ");
//        dongia=new VanPhongPham();
//        dongia.nhap();
//        thanhtien=soluong.getSoLuong()*dongia.getDonGia();
//    }
    public void xuat(){
        System.out.println("Ma hoa don: ");
        mahoadon.xuat();
        System.out.println("Ma san pham: ");
        masp.xuat();
        System.out.println("So luong: ");
        soluong.xuat();
        System.out.println("Don gia: ");
        dongia.xuat();
        System.out.println("Thanh tien: "+thanhtien);
    }
    public int getThanhTien(){
        return thanhtien;
    }
//    public static void main(String[] args){
//        ChiTietHoaDon cthd=new ChiTietHoaDon();
//        cthd.nhap();
//        cthd.xuat();
//    }

}

}
