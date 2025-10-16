public class DSNCC {
        int n;
        public NhaCungCap[] dsncc = new NhaCungCap[1];
        public DSNCC(){
            n = 0;
            dsncc = new NhaCungCap[0];
        }
        public DSNCC(int n){
            this.n = n;
            dsncc = new NhaCungCap[n];
            for(int i = 0; i < dsncc.length; i++){
                dsncc[i] = new NhaCungCap();
            }
        }
        public DSNCC(DSNCC other){
            this.n = other.n;
            this.dsncc = new NhaCungCap[n];
            for (int i = 0; i < dsncc.length; i++){
                this.dsncc[i] = new NhaCungCap(other.dsncc[i]);
            }
        }


}
