public class LoaiSanPham {
    private String maloai, tenloai, mota;
    public LoaiSanPham(){}
    public LoaiSanPham(String maloai, String tenloai, String mota){
        this.maloai = maloai;
        this.tenloai= tenloai;
        this.mota = mota;
    }
    public LoaiSanPham(LoaiSanPham x){
        this.maloai = x.maloai;
        this.tenloai = x.tenloai;
        this.mota = x.mota;
    }
    public String getMaLoai(){
        return maloai;
    }
    public String getTenLoai(){
        return tenloai;
    }
    public String mota(){
        return mota;
    }
    public void setMaLoai(String maloai){
        this.maloai = maloai;
    }
    public void setTenLoai(String tenloai){
        this.tenloai = tenloai;
    }
    public void setMoTa(String mota){
        this.mota = mota;
    }
}