import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSVPP {
    private int n;
    private VanPhongPham dsvpp[];
    Scanner sc = new Scanner(System.in);
    public DSVPP(){
        dsvpp = new VanPhongPham[300];
        n = 0;
    }
    public DSVPP(int n, VanPhongPham [] dsvpp){
        this.n = n;
        this.dsvpp = dsvpp;
    }
    public DSVPP(DSVPP other){
        this.n = other.n;
        this.dsvpp = new VanPhongPham[n];
        this.dsvpp = new VanPhongPham[other.dsvpp.length];
        for (int i = 0; i < dsvpp.length; i++){
            this.dsvpp[i]= other.dsvpp[i];
        }
    }
    public int getN() {
        return n;
    }
    public void docFileSanPham() {
        n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("VanPhongPham.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");
                String loai = thongtin[0].trim();
                VanPhongPham vpp = null;

                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMaLoai(loai);

                if (loai.equals("VP")) {
                    vpp = new VanPhong();

                    vpp.setLoaiSP(lsp); 
                    vpp.setMaSP(thongtin[1]);
                    vpp.setTenSP(thongtin[2]);
                    vpp.setSoLuong(Integer.parseInt(thongtin[3]));
                    vpp.setDonGia(Integer.parseInt(thongtin[4]));
                    vpp.setDonViTinh(thongtin[5].trim());
                    ((VanPhong) vpp).setPhanLoaiChucNang(thongtin[6]); 

                } else if (loai.equals("DC")) {
                    vpp = new DoChoi();

                    vpp.setLoaiSP(lsp);
                    vpp.setMaSP(thongtin[1]);
                    vpp.setTenSP(thongtin[2]);
                    vpp.setSoLuong(Integer.parseInt(thongtin[3]));
                    vpp.setDonGia(Integer.parseInt(thongtin[4]));
                    vpp.setDonViTinh(thongtin[5]);
                    ((DoChoi) vpp).setLuaTuoi(thongtin[6]);
                    ((DoChoi) vpp).setTheLoai(thongtin[7]);
                } else {
                    System.out.println("Lỗi dữ liệu: Loại không hợp lệ! Bỏ qua: " + line);
                    continue; 
                }
                
                dsvpp[n] = vpp; 
                n++; 
            }
        }
        catch (IOException e) { 
            System.out.println("❌ Lỗi đọc file VanPhongPham.txt: " + e.getMessage());
        }
        catch (Exception e){ 
            System.out.println("❌ Lỗi dữ liệu file VanPhongPham.txt: " + e.getMessage());
        }
    }
    public void xuat() {
        if (n == 0) { 
            System.out.println("Danh sách sản phẩm rỗng.");
            return;
        }

        System.out.println("\n--- DANH SÁCH TẤT CẢ SẢN PHẨM ---");
        
        String format = "| %-8s | %-10s | %-20s | %-10s | %-13s | %-13s | %-20s | %-10s | %-10s |\n";
        String line = "+----------+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+";

        System.out.println(line);
        System.out.printf(format, 
                "Loại SP", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính", "Phân Loại Chức Năng", "Độ Tuổi", "Thể Loại");
        System.out.println(line);

        for (int i = 0; i < n; i++) {
            if (dsvpp[i] != null) {
                dsvpp[i].xuat();
            }
        }
        System.out.println(line); 
    }
    public void xuat(String ma) {
        if (ma == null) { 
            System.out.println("Không tìm thấy sản phầm cần tìm.");
            return;
        }

        System.out.println("\n--- SẢN PHẨM ---");
        
        String format = "| %-8s | %-10s | %-20s | %-10s | %-13s | %-13s | %-20s | %-10s | %-10s |\n";
        String line = "+----------+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+";

        System.out.println(line);
        System.out.printf(format, 
                "Loại SP", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính", "Phân Loại Chức Năng", "Độ Tuổi", "Thể Loại");
        System.out.println(line);

        for (int i = 0; i < n; i++) {
            if (dsvpp[i] != null && dsvpp[i].getMaSP().equalsIgnoreCase(ma)) {
                dsvpp[i].xuat();
            }
        }
        System.out.println(line); 
    }

    public void themSanPham() {
        if (n >= dsvpp.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return;
        }

        System.out.println("\n--- THÊM SẢN PHẨM MỚI ---");
        System.out.println("1. Nhập Sản Phẩm Đồ Chơi");
        System.out.println("2. Nhập Sản Phẩm Văn Phòng");
        System.out.print("Lựa chọn loại sản phẩm: ");
        
        int luachon;
        while (!sc.hasNextInt()) {
            System.out.println("❌ Lỗi: Vui lòng nhập số (1 hoặc 2).");
            System.out.print("Lựa chọn loại sản phẩm: ");
            sc.next();
        }
        luachon = sc.nextInt();
        sc.nextLine();

        VanPhongPham spmoi;
        LoaiSanPham loai = new LoaiSanPham();

        if (luachon == 1) {
            spmoi = new DoChoi();
            loai.setMaLoai("DC");
        } else if (luachon == 2) {
            spmoi = new VanPhong();
            loai.setMaLoai("VP");
        } else {
            System.out.println("❌ Lựa chọn không hợp lệ.");
            return;
        }

        String masp; 
        while (true) {
            System.out.print("Mã SP: "); 
            masp = sc.nextLine();
            if (maDuyNhat(masp)) { 
                break; 
            } else {
                System.out.println("❌ Lỗi: Mã SP '" + masp + "' đã tồn tại. Vui lòng nhập lại.");
            }
        }
        spmoi.setMaSP(masp);
        spmoi.nhap();
        spmoi.setLoaiSP(loai);
        dsvpp[n] = spmoi;
        n++; 
        ghiFile(spmoi);
        System.out.println("-> Đã thêm sản phẩm mới thành công !");
    }

    public void themSanPham(VanPhongPham sp){
        if (!maDuyNhat(sp.getMaSP())) {
            System.out.println("❌ Lỗi: Mã SP '" + sp.getMaSP() + "' đã tồn tại. Không thể thêm.");
            return;
        }
        dsvpp[n]= sp;
        n++;
        ghiFile(sp);
    }   
    public void suaSanPham() {
        System.out.println("\n--- SỬA THÔNG TIN SẢN PHẨM ---");
        System.out.print("Nhập Mã SP cần sửa: ");
        String masp = sc.nextLine();

        VanPhongPham spcansua = timMaSP(masp);

        if (spcansua == null) {
            System.out.println("❌ Không tìm thấy sản phẩm có mã: " + masp);
            return;
        }

        System.out.println("✅ Đã tìm thấy! Đang sửa sản phẩm: " + spcansua.getTenSP() + " (Mã: " + spcansua.getMaSP() + ")");
        boolean daLuu = false;

        while (!daLuu) {
            System.out.println("\nChọn thông tin cần sửa:");
            System.out.println("1. Sửa Tên sản phẩm");
            System.out.println("2. Sửa Số lượng");
            System.out.println("3. Sửa Đơn giá");
            System.out.println("4. Sửa Đơn vị tính");

            if (spcansua instanceof VanPhong) {
                System.out.println("5. Sửa Phân loại chức năng");
            } else if (spcansua instanceof DoChoi) {
                System.out.println("5. Sửa Lứa tuổi");
                System.out.println("6. Sửa Thể loại");
            }
            System.out.println("0. Hoàn tất và Lưu thay đổi");
            System.out.print("Lựa chọn của bạn: ");

            int luachon;
            
            if (sc.hasNextInt()) {
                luachon = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("❌ Lỗi: Vui lòng nhập một số.");
                sc.next();
                continue;
            }
            switch (luachon) {
                case 1:
                    System.out.print("Nhập tên mới: ");
                    spcansua.setTenSP(sc.nextLine());
                    System.out.println("-> Đã cập nhật tên.");
                    break;
                case 2:
                    int soluongmoi;
                    System.out.print("Nhập số lượng cần sửa: ");
                    soluongmoi= sc.nextInt();
                    sc.nextLine();
                    spcansua.setSoLuong(soluongmoi);
                    System.out.println("-> Đã cập nhật số lượng.");
                    break;
                case 3:
                    System.out.print("Nhập đơn giá mới: ");
                    int dongiamoi = sc.nextInt();
                    sc.nextLine();
                    spcansua.setDonGia(dongiamoi);
                    System.out.println("-> Đã cập nhật đơn giá.");
                    break;
                case 4:
                    System.out.print("Nhập đơn vị tính mới: ");
                    spcansua.setDonViTinh(sc.nextLine());
                    System.out.println("-> Đã cập nhật đơn vị tính.");
                    break;
                case 5:
                    if (spcansua instanceof VanPhong) {
                        System.out.print("Nhập phân loại chức năng mới: ");
                        ((VanPhong) spcansua).setPhanLoaiChucNang(sc.nextLine());
                        System.out.println("-> Đã cập nhật chức năng.");
                    } else if (spcansua instanceof DoChoi) {
                        System.out.print("Nhập lứa tuổi mới: ");
                        ((DoChoi) spcansua).setLuaTuoi(sc.nextLine());
                        System.out.println("-> Đã cập nhật lứa tuổi.");
                    } else {
                        System.out.println("❌ Lựa chọn không hợp lệ.");
                    }
                    break;
                case 6:
                    if (spcansua instanceof DoChoi) {
                        System.out.print("Nhập thể loại mới: ");
                        ((DoChoi) spcansua).setTheLoai(sc.nextLine());
                        System.out.println("-> Đã cập nhật thể loại.");
                    } else {
                        System.out.println("❌ Lựa chọn không hợp lệ.");
                    }
                    break;
                case 0:
                    daLuu = true;
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } 
        ghiLaiToanBoFileSanPham(); 
        
        System.out.println("✅ Đã lưu tất cả thay đổi cho sản phẩm " + spcansua.getMaSP() + " vào file.");
    }
    public void ghiFile(VanPhongPham spmoi){
        LoaiSanPham loaisp = spmoi.getLoaiSP();
        String line ="";
        String loai = loaisp.getMaLoai();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt", true))) {
            line = loai + "," +
                spmoi.getMaSP() + "," + spmoi.getTenSP() + "," + spmoi.getSoLuong() + "," +
                spmoi.getDonGia() + "," + spmoi.getDonViTinh();

            if (spmoi instanceof VanPhong) {
                line += "," + ((VanPhong) spmoi).getPhanLoaiChucNang();
            } else if (spmoi instanceof DoChoi) {
                line += "," + ((DoChoi) spmoi).getLuaTuoi() + "," + ((DoChoi) spmoi).getTheLoai();
            } else {
                System.out.println("⚠️ Lỗi: Loại đối tượng không xác định.");
                return;
            }
            bw.write(line);
            bw.newLine();
            System.out.println("-> Đã ghi thêm sản phẩm '" + spmoi.getTenSP() + "' vào file.");

        } 
        catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
        }
    }
    public VanPhongPham timMaSP(String masp) {
       for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equalsIgnoreCase(masp)) {
                return dsvpp[i];
            }
        }
        return null;

    }

    public String timMaSP() {
        System.out.print("Nhập mã sp cần tìm: ");
        String masp = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equalsIgnoreCase(masp)) {
                return masp;
            }
        }
        return null;

    }

    public boolean maDuyNhat(String masp) {
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equals(masp)) {
                return false;
            }
        }
        return true;
    }
    public void capNhatSLSP(String masp, int soluong) {
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equals(masp)) {
                int sanphamhientai = dsvpp[i].getSoLuong();
                int spmoi = sanphamhientai + soluong;
                if (spmoi < 0) {
                    System.out.println("❌ Không đủ tồn kho để trừ. Hiện tại: " + sanphamhientai + ", yêu cầu trừ: " + (-soluong));
                    return;
                }
                dsvpp[i].setSoLuong(spmoi);
                ghiLaiToanBoFileSanPham();
                System.out.println("Đã cập nhật SL sản phẩm " + dsvpp[i].getMaSP() + " từ " + sanphamhientai + " -> " + spmoi);
                return;
            }
        }
        System.out.println("❌ Không tìm thấy sản phẩm có mã: " + masp);
    }
    public void capNhatDGSP(String masp, int dongia) {
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equals(masp)) {
                int dongiahientai = dsvpp[i].getDonGia();
                dsvpp[i].setDonGia(dongia);
                ghiLaiToanBoFileSanPham();
                System.out.println("Đã cập nhật đơn giá sản phẩm " + dsvpp[i].getMaSP() + " từ " + dongiahientai + " -> " + dongia);
                return;
            }
        }
        System.out.println("❌ Không tìm thấy sản phẩm có mã: " + masp);
    }
    public void capNhatDonViTinh(String masp, String donvitinh) {
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equals(masp)) {
                String donvitinhhientai = dsvpp[i].getDonViTinh();
                dsvpp[i].setDonViTinh(donvitinh);
                ghiLaiToanBoFileSanPham();
                System.out.println("Đã cập nhật SL sản phẩm " + dsvpp[i].getMaSP() + " từ " + donvitinhhientai + " -> " + donvitinh);
                return;
            }
        }
        System.out.println("❌ Không tìm thấy sản phẩm có mã: " + masp);
    }




    private void ghiLaiToanBoFileSanPham() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt"))) {
            for (int i = 0; i < n; i++) {
                if (dsvpp[i] == null) continue;
                VanPhongPham sp = dsvpp[i];
                String loai = (sp.getLoaiSP() != null) ? sp.getLoaiSP().getMaLoai() : "";
                String line = loai + "," + sp.getMaSP() + "," + sp.getTenSP() + "," + sp.getSoLuong() + "," + sp.getDonGia() + "," +sp.getDonViTinh();
                if (sp instanceof VanPhong) {
                    line += "," + ((VanPhong) sp).getPhanLoaiChucNang();
                } else if (sp instanceof DoChoi) {
                    line += "," + ((DoChoi) sp).getLuaTuoi() + "," + ((DoChoi) sp).getTheLoai();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại file sản phẩm: " + e.getMessage());
        }
    }

public void xoaSanPham() {
    System.out.println("\n--- XÓA SẢN PHẨM ---");
    if (n == 0) {
        System.out.println("Danh sách sản phẩm rỗng. Không có gì để xóa.");
        return;
    }
    
    System.out.print("Nhập Mã SP cần xóa: ");
    String maspCanXoa = sc.nextLine();

    int viTriXoa = -1;
    
    for (int i = 0; i < n; i++) {
        if (dsvpp[i].getMaSP().equalsIgnoreCase(maspCanXoa)) {
            viTriXoa = i;
            break; 
        }
    }

    if (viTriXoa == -1) {
        System.out.println("❌ Không tìm thấy sản phẩm có mã: " + maspCanXoa);
        return;
    }
    
    System.out.println("✅ Đã tìm thấy sản phẩm: " + dsvpp[viTriXoa].getTenSP());
    System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (Y/N): ");
    String confirm = sc.nextLine();

    if (confirm.equalsIgnoreCase("Y")) {
        
        for (int i = viTriXoa; i < n - 1; i++) {
            dsvpp[i] = dsvpp[i + 1];
        }
        
        dsvpp[n - 1] = null; 
        
        n--; 
        
        ghiLaiToanBoFileSanPham(); 
        
        System.out.println("✅ Đã xóa thành công sản phẩm có mã: " + maspCanXoa);
    } else {
        System.out.println("Hủy bỏ thao tác xóa.");
    }
}
    public int tinhTongTonKho(){
        int tongtonkho = 0;
        for(int i = 0; i < n; i++){
            tongtonkho += dsvpp[i].getSoLuong();
        }
        return tongtonkho;
    }
    
    public int demSanPhamSapHetHang(int warning){
        int saphethang = 0;
        for(int i = 0; i < n; i++){
            if(dsvpp[i].getSoLuong() < warning){
                saphethang++;
            }
        }
        return saphethang;
    }

    public int demSanPhamHetHang(){
        int hethang = 0;
        for(int i = 0; i < n; i++){
            if(dsvpp[i].getSoLuong() == 0){
                hethang++;
            }
        }
        return hethang;
    }

    public int tongSoLuongSanPham(){
        int tongsanpham = 0;
        for(int i = 0; i < n; i++){
            if(dsvpp[i] != null){
                tongsanpham++;
            }
        }
        return tongsanpham;
    }
}