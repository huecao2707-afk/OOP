public class NHACUNGCAP {
    private String ten, diachi, maNCC;

    public NHACUNGCAP() {
        maNCC = "";
        ten = "";
        diachi = "";
    }

    public NHACUNGCAP(String maNCC, String ten, String diachi) {
        this.maNCC = maNCC;
        this.ten = ten;
        this.diachi = diachi;
    }

    public NHACUNGCAP(NHACUNGCAP a) {
        maNCC = a.maNCC;
        ten = a.ten;
        diachi = a.diachi;
    }

    public String getmaNCC() {
        return maNCC;
    }

    public String getten() {
        return ten;
    }

    public String getDC() {
        return diachi;
    }
}
