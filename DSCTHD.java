public class DSCTHD {
    int m;
    public ChiTietHoaDon[] ds_cthd = new ChiTietHoaDon[1];
    public DSCTHD(){
        m = 0;
        ds_cthd = new ChiTietHoaDon[0];
    }
    public DSCTHD(int m, ChiTietHoaDon[] ds_cthd){
        this.m = m;
        this.ds_cthd = ds_cthd;
    }
    public DSCTHD(DSCTHD other){
        this.m = other.m;
        this.ds_cthd = new ChiTietHoaDon[m];
        for (int i = 0; i < ds_cthd.length; i++){
            this.ds_cthd[i] = new ChiTietHoaDon(other.ds_cthd[i]);
        }
    }
}