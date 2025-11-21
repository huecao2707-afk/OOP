public class DSDC {
    private int n;
    private DoChoi dsdc [] = new DoChoi[1];
    public DSDC(){}
    public DSDC(int n, PhieuNhapHang[] dspnh){
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