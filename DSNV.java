public class DSNV {
        int n;
        public NhanVien[] ds_nv = new NhanVien[1];
        public DSNV(){
            n = 0;
            ds_nv = new NhanVien[0];
        }
        public DSNV(int n, NhanVien[] ds_nv){
            this.n = n;
            this.ds_nv = ds_nv;
        }
        public DSNV(DSNV other){
            this.n = other.n;
            this.ds_nv = new NhanVien[n];
            for (int i = 0; i < ds_nv.length; i++){
                this.ds_nv[i] = new NhanVien(other.ds_nv[i]);
            }
        }


}
