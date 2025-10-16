public class DSHD {
    int n;
    public HoaDon[] dshd = new HoaDon[1];
    public DSHD(){
        n = 0;
        dshd = new HoaDon[0];
    }
    public DSHD(int n){
        this.n = n;
        dshd = new HoaDon[n];
        for(int i = 0; i < dshd.length; i++){
            dshd[i] = new HoaDon();
        }
    }
    public DSHD(DSHD other){
        this.n = other.n;
        this.dshd = new HoaDon[n];
        for (int i = 0; i < dshd.length; i++){
            this.dshd[i] = new HoaDon(other.dshd[i]);
        }
    }
}
