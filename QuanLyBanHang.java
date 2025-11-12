
public abstract class QuanLyBanHang {
    static DSVPP dsvpp;
    static DSKH dskh ;
    static DSNCC dsncc;
    static DSNV dsnv;
    static DSPNH dspnh;
    static DSCTPNH dsctpnh;
    static DSHD dshd;
    static DSCTHD dscthd ;
   
    static {
        // System.out.println("Đang tải dữ liệu từ các file...");
        
        // Khởi tạo các đối tượng danh sách
        dsvpp = new DSVPP();
        dskh = new DSKH();
        dsncc = new DSNCC();
        dsnv = new DSNV();
        dspnh = new DSPNH();
        dshd = new DSHD();
        dscthd = new DSCTHD();
        dsctpnh = new DSCTPNH();
        dsvpp.docFileSanPham();
        dskh.docFileKhachHang();
        dsnv.docFileNhanVien();
        dsncc.docFileNhaCungCap();
        dshd.docFileHoaDon();
        dscthd.docFileCTHD();
         dspnh.docFilePNH();
         dsctpnh.docFileCTPNH();

        System.out.println("Tải dữ liệu hoàn tất!");
        QLHD qlhd = new QLHD(); // Khởi tạo để gọi các hàm liên kết
        qlhd.LienKetDuLieuHoaDon();
        QLPNH qlpnh = new QLPNH();
        qlpnh.LienKetDuLieuPNH();
    }

    public QuanLyBanHang(){

    }
    public QuanLyBanHang(DSVPP dsvpp, DSKH dskh, DSNCC dsncc, DSNV dsnv, DSPNH dspnh, DSCTPNH dsctpnh, DSHD dshd, DSCTHD dscthd){
        this.dsvpp = dsvpp;
        this.dskh = dskh;
        this.dsncc = dsncc;
        this.dsnv = dsnv;
        this.dspnh = dspnh;
        this.dsctpnh = dsctpnh;
        this.dshd = dshd;
        this.dscthd = dscthd;
    }
    public QuanLyBanHang(QuanLyBanHang other){
        this.dsvpp = other.dsvpp;
        this.dskh = other.dskh;
        this.dsncc = other.dsncc;
        this.dsnv = other.dsnv;
        this.dspnh = other.dspnh;
        this.dsctpnh = other.dsctpnh;
        this.dshd = other.dshd;
        this.dscthd = other.dscthd;
    }
    
    // public void
    public abstract void menuChinh();   
}
