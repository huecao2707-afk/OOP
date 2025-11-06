
public abstract class QuanLyBanHang {
    static DSVPP dsvpp;
    static DSKH dskh ;
    static DSNCC dsncc;
    static DSNV dsnv;
    static DSPNH dspnh;
    static DSCTPNH dsctpnh;
    static DSHD dshd;
    static DSCTHD dsctphd ;
   

    public QuanLyBanHang(){
        taiDuLieuTuFile();
    }
    public QuanLyBanHang(DSVPP dsvpp, DSKH dskh, DSNCC dsncc, DSNV dsnv, DSPNH dspnh, DSCTPNH dsctpnh, DSHD dshd, DSCTHD dscthd){
        this.dsvpp = dsvpp;
        this.dskh = dskh;
        this.dsncc = dsncc;
        this.dsnv = dsnv;
        this.dspnh = dspnh;
        this.dsctpnh = dsctpnh;
        this.dshd = dshd;
        this.dsctphd = dscthd;
    }
    public QuanLyBanHang(QuanLyBanHang other){
        this.dsvpp = other.dsvpp;
        this.dskh = other.dskh;
        this.dsncc = other.dsncc;
        this.dsnv = other.dsnv;
        this.dspnh = other.dspnh;
        this.dsctpnh = other.dsctpnh;
        this.dshd = other.dshd;
        this.dsctphd = other.dsctphd;
    }
    public static void taiDuLieuTuFile() {
        System.out.println("Đang tải dữ liệu từ các file...");
        
        // Khởi tạo các đối tượng danh sách
        dsvpp = new DSVPP();
        dskh = new DSKH();
        dsncc = new DSNCC();
        dsnv = new DSNV();
        dspnh = new DSPNH();
        dshd = new DSHD();
        dsctphd = new DSCTHD();
        dsctpnh = new DSCTPNH();
        dsvpp.docFile();
        // dskh.docFile(); 
        // dsncc.docFile();
        // dsnv.docFi   le();
        // dspnh.docFile();
        // dshd.docFile();
        // dsctphd.docFile();
        // dsctpnh.docFile();

        System.out.println("Tải dữ liệu hoàn tất!");
    }
    // public void
    public abstract void menuChinh();   
}
