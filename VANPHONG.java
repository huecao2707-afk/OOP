public class VANPHONG extends VANPHONGPHAM{
    private String phan_loai_chuc_nang;
    private String doi_tuong_su_dung;
    public VANPHONG(){
    }
    public VANPHONG(String ma_sp,String ten_sp, String don_vi_tinh,int don_gia,int so_luong, String phan_loai_chuc_nang,String doi_tuong_su_dungi){
        super(don_gia, so_luong, ma_sp, ten_sp, don_vi_tinh);
        this.phan_loai_chuc_nang = phan_loai_chuc_nang;
        this.doi_tuong_su_dung = doi_tuong_su_dung;
    }
    public VANPHONG(VANPHONG other){
        super((VANPHONGPHAM)other);
        this.doi_tuong_su_dung = other.doi_tuong_su_dung;
        this.phan_loai_chuc_nang = other.phan_loai_chuc_nang;
    }

    public String getDoi_tuong_su_dung() {
        return doi_tuong_su_dung;
    }

    public String getPhan_loai_chuc_nang() {
        return phan_loai_chuc_nang;
    }

    public void setDoi_tuong_su_dung(String doi_tuong_su_dung) {
        this.doi_tuong_su_dung = doi_tuong_su_dung;
    }

    public void setPhan_loai_chuc_nang(String phan_loai_chuc_nang) {
        this.phan_loai_chuc_nang = phan_loai_chuc_nang;
    }
}
