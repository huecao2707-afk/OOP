public class NHACUNGCAP {
    private String ten_ncc, dia_chi, ma_ncc;

    public NHACUNGCAP() {
        ma_ncc = "";
        ten_ncc = "";
        dia_chi = "";
    }

    public NHACUNGCAP(String ma_ncc, String ten_ncc, String dia_chi) {
        this.ma_ncc = ma_ncc;
        this.ten_ncc = ten_ncc;
        this.dia_chi = dia_chi;
    }

    public NHACUNGCAP(NHACUNGCAP a) {
        ma_ncc = a.ma_ncc;
        ten_ncc = a.ten_ncc;
        dia_chi = a.dia_chi;
    }

    public String getMaNCC() {
        return ma_ncc;
    }

    public String getTenNCC() {
        return ten_ncc;
    }

    public String getDiaChi() {
        return dia_chi;
    }
}
