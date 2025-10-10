public class DSKHACHHANG {
        int n;
        public KHACHHANG[] dskh = new KHACHHANG[1];
        public DSKHACHHANG(){
            n = 0;
            dskh = new KHACHHANG[0];
        }
        public DSKHACHHANG(int n){
            this.n = n;
            dskh = new KHACHHANG[n];
            for(int i = 0; i < dskh.length; i++){
                dskh[i] = new KHACHHANG();
            }
        }
        public DSKHACHHANG(DSKHACHHANG other){
            this.n = other.n;
            this.dskh = new KHACHHANG[n];
            for (int i = 0; i < dskh.length; i++){
                this.dskh[i] = new KHACHHANG(other.dskh[i]);
            }
        }

}
