import java.util.Scanner;
public class QuanLyBanHang {
    public DSKH dskh;
    public DSPNH dspnh;
    public DSNCC dsncc;
    public DSHD dshd;
    public DSNV dsnv;
     public QuanLyBanHang(){
        dskh = new DSKH();
        dspnh = new DSPNH();
        dsncc = new DSNCC();
        dshd = new DSHD();
        dsnv = new DSNV();
    }
    public QuanLyBanHang(DSKH dskh, DSPNH dspnh, DSNCC dsncc, DSHD dshd, DSNV dsnv){
        this.dskh = dskh;
        this.dspnh = dspnh;
        this.dsncc = dsncc;
        this.dshd = dshd;
        this.dsnv = dsnv;
    }
    public QuanLyBanHang(QuanLyBanHang other){
        this.dskh = new DSKH(other.dskh);
        this.dspnh = new DSPNH(other.dspnh);
        this.dsncc = new DSNCC(other.dsncc);
        this.dshd = new DSHD(other.dshd);
        this.dsnv = new DSNV(other.dsnv);
    }
    
}
