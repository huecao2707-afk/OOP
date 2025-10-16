public class DSNCC {
        int n;
        public NHACUNGCAP[] dsncc = new NHACUNGCAP[1];
        public DSNCC(){
            n = 0;
            dsncc = new NHACUNGCAP[0];
        }
        public DSNCC(int n){
            this.n = n;
            dsncc = new NHACUNGCAP[n];
            for(int i = 0; i < dsncc.length; i++){
                dsncc[i] = new NHACUNGCAP();
            }
        }
        public DSNCC(DSNCC other){
            this.n = other.n;
            this.dsncc = new NHACUNGCAP[n];
            for (int i = 0; i < dsncc.length; i++){
                this.dsncc[i] = new NHACUNGCAP(other.dsncc[i]);
            }
        }


}
