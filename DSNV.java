public class DSNHANVIEN {
        int n;
        public NHANVIEN[] dsnv = new NHANVIEN[1];
        public DSNHANVIEN(){
            n = 0;
            dsnv = new NHANVIEN[0];
        }
        public DSNHANVIEN(int n){
            this.n = n;
            dsnv = new NHANVIEN[n];
            for(int i = 0; i < dsnv.length; i++){
                dsnv[i] = new NHANVIEN();
            }
        }
        public DSNHANVIEN(DSNHANVIEN other){
            this.n = other.n;
            this.dsnv = new NHANVIEN[n];
            for (int i = 0; i < dsnv.length; i++){
                this.dsnv[i] = new NHANVIEN(other.dsnv[i]);
            }
        }


}
