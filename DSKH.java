public class DSKH {
        int n;
        public KhachHang[] dskh = new KhachHang[1];
        public DSKH(){
            n = 0;
            dskh = new KhachHang[0];
        }
        public DSKH(int n){
            this.n = n;
            dskh = new KhachHang[n];
            for(int i = 0; i < dskh.length; i++){
                dskh[i] = new KhachHang();
            }
        }
        public DSKH(DSKH other){
            this.n = other.n;
            this.dskh = new KhachHang[n];
            for (int i = 0; i < dskh.length; i++){
                this.dskh[i] = new KhachHang(other.dskh[i]);
            }
        }

}
