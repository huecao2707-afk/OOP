public class DSKH {
        int n;
        public KHACHHANG[] dskh = new KHACHHANG[1];
        public DSKH(){
            n = 0;
            dskh = new KHACHHANG[0];
        }
        public DSKH(int n){
            this.n = n;
            dskh = new KHACHHANG[n];
            for(int i = 0; i < dskh.length; i++){
                dskh[i] = new KHACHHANG();
            }
        }
        public DSKH(DSKH other){
            this.n = other.n;
            this.dskh = new KHACHHANG[n];
            for (int i = 0; i < dskh.length; i++){
                this.dskh[i] = new KHACHHANG(other.dskh[i]);
            }
        }

}
