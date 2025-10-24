public class DSDC {
    private int n; //so luong phieu nhan hang
    private DoChoi ds_dc [] = new DoChoi[1];// mang ds phieu nhan hang
    public DSDC(){}
    public DSDC(int n, PhieuNhanHang [] ds_pnh){
        this.n = n;
        this.ds_dc = ds_dc;
    }
    public DSDC(DSDC other){
        this.n = other.n;
        this.ds_dc = new DoChoi[n];
        for (int i = 0; i < ds_dc.length; i++){
            this.ds_dc[i] = new DoChoi(other.ds_dc[i]);
        }
    }
}
