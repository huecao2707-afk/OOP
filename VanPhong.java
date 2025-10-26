import java.util.Scanner;
public class VanPhong extends VanPhongPham{
    private String phan_loai_chuc_nang;
    public VanPhong(){
    }
    public VanPhong(String ma_sp,String ten_sp, String don_vi_tinh,int don_gia,int so_luong, String phan_loai_chuc_nang){
        super(don_gia, so_luong, ma_sp, ten_sp, don_vi_tinh);
        this.phan_loai_chuc_nang = phan_loai_chuc_nang;
    }
    public VanPhong(VanPhong other){
        super((VanPhongPham)other);
        this.phan_loai_chuc_nang = other.phan_loai_chuc_nang;
    }
    public String getPhanLoaiChucNang() {
        return phan_loai_chuc_nang;
    }

    public void setPhanLoaiChucNang(String phan_loai_chuc_nang) {
        this.phan_loai_chuc_nang = phan_loai_chuc_nang;
    }
    @Override public void nhap(){
        super.nhap();
        System.out.print("Phan loai chuc nang: ");
        Scanner sc = new Scanner(System.in);
        phan_loai_chuc_nang = sc.nextLine();
    }
    @Override
    public void tieude() {
        System.out.println("+------------+----------------------+------------+--------------+------------+----------------------+");
                    |   10s   |      20s       |    10s    |      12s    |     10s   |       20s          |
        System.out.printf("| %-10s | %-20s | %-10s | %-12s | %-10s | %-20s |\n",
            "MÃ SP", "TÊN SP", "SỐ LƯỢNG", "ĐƠN GIÁ", "ĐƠN VỊ", "CHỨC NĂNG");
        System.out.println("+------------+----------------------+------------+--------------+------------+----------------------+");
    }

  @Override
    public void xuat() {
       System.out.printf("| %-10s | %-20s | %-10d | %-13d | %-13s | %-20s | %-10s | %-10s |\n",
            ma_sp,             // Cột 1: Mã SP (String)
            ten_sp,            // Cột 2: Tên SP (String)
            so_luong,          // Cột 3: Số Lượng (int)
            don_gia,           // Cột 4: Đơn Giá (int)
            don_vi_tinh,       // Cột 5: Đơn Vị Tính (String)
            phan_loai_chuc_nang,// Cột 6: Chức Năng (String) - Có
            "",                // Cột 7: Độ Tuổi (String) - Để trống
            ""                 // Cột 8: Thể Loại (String) - Để trống
        );
    }
    @Override
    public void docDuLieu(String[] parts) {
        try {
            this.ma_sp = parts[1];
            this.ten_sp = parts[2];
            this.so_luong = Integer.parseInt(parts[3]);
            this.don_gia = Integer.parseInt(parts[4]);
            this.don_vi_tinh = parts[5];
            this.phan_loai_chuc_nang = parts[6];
        } catch (Exception e) {
            System.out.println("Lỗi đọc dòng VanPhong!");
        }
    }
}

