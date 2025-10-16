public class DSNV {
        int n;
        public NhanVien[] dsnv = new NhanVien[1];
        public DSNV(){
            n = 0;
            dsnv = new NhanVien[0];
        }
        public DSNV(int n){
            this.n = n;
            dsnv = new NhanVien[n];
            for(int i = 0; i < dsnv.length; i++){
                dsnv[i] = new NhanVien();
            }
        }
        public DSNV(DSNV other){
            this.n = other.n;
            this.dsnv = new NhanVien[n];
            for (int i = 0; i < dsnv.length; i++){
                this.dsnv[i] = new NhanVien(other.dsnv[i]);
            }
        }


}
