public class DSDC {
    private int n; //so luong phieu nhan hang
    private DoChoi dsdc [] = new DoChoi[1];// mang ds phieu nhan hang
    public DSDC(){}
    public DSDC(int n, PhieuNhanHang [] dspnh){
        this.n = n;
        this.dsdc = dsdc;
    }
    public DSDC(DSDC other){
        this.n = other.n;
        this.dsdc = new DoChoi[n];
        for (int i = 0; i < dsdc.length; i++){
            this.dsdc[i] = new DoChoi(other.dsdc[i]);
        }
    }
}
