public class DSNCC {
        int n;
        public NhaCungCap[] ds_ncc = new NhaCungCap[1];
        public DSNCC(){
            n = 0;
            ds_ncc = new NhaCungCap[0];
        }
        public DSNCC(int n, NhaCungCap[] ds_ncc){
            this.n = n;
            ds_ncc = new NhaCungCap[n];
            for(int i = 0; i < ds_ncc.length; i++){
                ds_ncc[i] = new NhaCungCap();
            }
        }
        public DSNCC(DSNCC other){
            this.n = other.n;
            this.ds_ncc = new NhaCungCap[n];
            for (int i = 0; i < ds_ncc.length; i++){
                this.ds_ncc[i] = new NhaCungCap(other.ds_ncc[i]);
            }
        }


}
