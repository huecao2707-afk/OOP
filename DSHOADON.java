public class DSHOADON {
    int n;
    public HOADON[] dshd = new HOADON[1];
    public DSHOADON(){
        n = 0;
        dshd = new HOADON[0];
    }
    public DSHOADON(int n){
        this.n = n;
        dshd = new HOADON[n];
        for(int i = 0; i < dshd.length; i++){
            dshd[i] = new HOADON();
        }
    }
    public DSHOADON(DSHOADON other){
        this.n = other.n;
        this.dshd = new HOADON[n];
        for (int i = 0; i < dshd.length; i++){
            this.dshd[i] = new HOADON(other.dshd[i]);
        }
    }
}
