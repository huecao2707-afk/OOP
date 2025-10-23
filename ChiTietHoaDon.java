public class ChiTietHoaDon extends VanPhongPham {
    private String ma_hd;
    private int soluong,don_gia;
    public ChiTietHoaDon(){
        super();
        ma_hd="";
        soluong=0;
    }
    public ChiTietHoaDon(String ten_sp, String ma_sp, int don_gia, String ma_hd, int soluong, int so_luong, String don_vi_tinh) {
        super(don_gia, so_luong, ma_sp, ten_sp, don_vi_tinh);
        this.ma_hd = ma_hd;
        this.soluong = soluong;
    }

    public ChiTietHoaDon(ChiTietHoaDon a){
        super(a);
        ma_hd=a.ma_hd;
        soluong=a.soluong;
    }
    public double thanhTien(){
        return don_gia * soluong;
    }
}