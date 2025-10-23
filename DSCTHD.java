public class DSCTHD {
    int m;
    public ChiTietHoaDon[] dscthd = new ChiTietHoaDon[1];
    public DSCTHD(){
        m = 0;
        dscthd = new ChiTietHoaDon[0];
    }
    public DSCTHD(int m){
        this.m = m;
        dscthd = new ChiTietHoaDon[m];
        for(int i = 0; i < dscthd.length; i++){
            dscthd[i] = new ChiTietHoaDon();
        }
    }
    public DSCTHD(DSCTHD other){
        this.m = other.m;
        this.dscthd = new ChiTietHoaDon[m];
        for (int i = 0; i < dscthd.length; i++){
            this.dscthd[i] = new ChiTietHoaDon(other.dscthd[i]);
        }
    }
}