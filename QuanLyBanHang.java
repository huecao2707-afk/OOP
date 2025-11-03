
public abstract class QuanLyBanHang {
    static DSVPP dsvpp;
    static DSKH dskh ;
    static DSNCC dsncc;
    static DSNV dsnv;
    static DSPNH dspnh;
    static DSHD dshd;
    static DSCTHD dsctphd ;
    static DSCTPNH dsctpnh;
    
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
        // dsnv.docFile();
        // dspnh.docFile();
        // dshd.docFile();
        // dsctphd.docFile();
        // dsctpnh.docFile();

        System.out.println("Tải dữ liệu hoàn tất!");
    }
    public abstract void menuChinh();   
}
