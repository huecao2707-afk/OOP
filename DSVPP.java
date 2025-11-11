import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSVPP {
    private int n;// so luong san pham
    private VanPhongPham dsvpp[];
    public DSVPP(){
        dsvpp = new VanPhongPham[300]; // 300 san pham t
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
    
    // Trong file DSVPP.java
    public void docFileSanPham() {
        n = 0; // Reset số lượng về 0
        try (BufferedReader br = new BufferedReader(new FileReader("VanPhongPham.txt"))) {
            String line;

            while ((line = br.readLine()) != null) { // Đọc từng dòng
                // FIX: Bỏ qua các dòng trống hoặc chỉ có dấu phẩy do file hỏng
                if (line.trim().isEmpty() || line.trim().startsWith(",")) {
                    System.out.println("Lỗi dữ liệu: Bỏ qua dòng hỏng: " + line);
                    continue;
                }

                String[] thongtin = line.split(",");
                String loai = thongtin[0].trim(); // Lấy mã loại (VP hoặc DC)
                VanPhongPham vpp = null;

                // === 1. TẠO "THẺ LOẠI" (LoaiSanPham) ===
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMaLoai(loai);
                // ======================================

                if (loai.equalsIgnoreCase("VP")) {
                    vpp = new VanPhong();
                    // === 2. GÁN "THẺ LOẠI" CHO SẢN PHẨM ===
                    vpp.setLoaiSP(lsp); 
                    
                    vpp.setMaSP(thongtin[1].trim());
                    vpp.setTenSP(thongtin[2].trim());
                    vpp.setSoLuong(Integer.parseInt(thongtin[3].trim()));
                    vpp.setDonGia(Integer.parseInt(thongtin[4].trim()));
                    vpp.setDonViTinh(thongtin[5].trim());
                    ((VanPhong) vpp).setPhanLoaiChucNang(thongtin[6].trim()); 

                } else if (loai.equalsIgnoreCase("DC")) {
                    vpp = new DoChoi();
                    // === 2. GÁN "THẺ LOẠI" CHO SẢN PHẨM ===
                    vpp.setLoaiSP(lsp);

                    vpp.setMaSP(thongtin[1].trim());
                    vpp.setTenSP(thongtin[2].trim());
                    vpp.setSoLuong(Integer.parseInt(thongtin[3].trim()));
                    vpp.setDonGia(Integer.parseInt(thongtin[4].trim()));
                    vpp.setDonViTinh(thongtin[5].trim());
                    ((DoChoi) vpp).setLuaTuoi(thongtin[6].trim());
                    ((DoChoi) vpp).setTheLoai(thongtin[7].trim());
                } else {
                    System.out.println("Lỗi dữ liệu: Loại không hợp lệ! Bỏ qua: " + line);
                    continue; 
                }
                
                dsvpp[n] = vpp; // Thêm sản phẩm vào mảng
                n++; 
            }
        }
        catch (IOException e) { 
            System.out.println("❌ Lỗi đọc file VanPhongPham.txt: " + e.getMessage());
        }
        catch (Exception e){ // Bắt các lỗi khác như NumberFormat, NullPointer...
            System.out.println("❌ Lỗi dữ liệu file VanPhongPham.txt: " + e.getMessage());
        }
    }
    public void xuat() {
        if (n == 0) { // n là số lượng sản phẩm thực tế
            System.out.println("Danh sách sản phẩm rỗng.");
            return;
        }

        System.out.println("\n--- DANH SÁCH TẤT CẢ SẢN PHẨM ---");
        
        // Chuỗi định dạng (9 cột)
        String format = "| %-8s | %-10s | %-20s | %-10s | %-13s | %-13s | %-20s | %-10s | %-10s |\n";
        String line = "+----------+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+";

        System.out.println(line);
        System.out.printf(format, 
                "Loại SP", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính", "Phân Loại Chức Năng", "Độ Tuổi", "Thể Loại");
        System.out.println(line);

        for (int i = 0; i < n; i++) {
            if (dsvpp[i] != null) {
                dsvpp[i].xuat(); // Gọi hàm xuat() của VanPhong hoặc DoChoi
            }
        }
        System.out.println(line); // In dòng kẻ dưới cùng
    }
    public void themSanPham() {
    // 1. Kiểm tra mảng còn chỗ không
        if (n >= dsvpp.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- THÊM SẢN PHẨM MỚI ---");
        System.out.println("1. Nhập Sản Phẩm Đồ Chơi");
        System.out.println("2. Nhập Sản Phẩm Văn Phòng");
        System.out.print("Lựa chọn loại sản phẩm: ");
        
        int luachon;
        while (!sc.hasNextInt()) {
            System.out.println("❌ Lỗi: Vui lòng nhập số (1 hoặc 2).");
            System.out.print("Lựa chọn loại sản phẩm: ");
            sc.next(); // Loại bỏ input sai
        }
        luachon = sc.nextInt();
        sc.nextLine(); // Tránh trôi lệnh

        VanPhongPham spmoi; // Đối tượng sản phẩm mới
        LoaiSanPham loai = new LoaiSanPham(); // 1. Tạo đối tượng LoaiSanPham ("Thẻ")

        if (luachon == 1) {
            spmoi = new DoChoi();
            loai.setMaLoai("DC"); // 2. Gán Mã Loại ("DC") vào "Thẻ"
        } else if (luachon == 2) {
            spmoi = new VanPhong();
            loai.setMaLoai("VP"); // 2. Gán Mã Loại ("VP") vào "Thẻ"
        } else {
            System.out.println("❌ Lựa chọn không hợp lệ.");
            return;
        }

        // 3. Nhập và kiểm tra Mã SP
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
        spmoi.setMaSP(masp); // Gán mã SP đã kiểm tra
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
                return; // Không ghi
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
                return dsvpp[i]; // Trả về đối tượng sản phẩm nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy

    }
    public boolean maDuyNhat(String masp) {
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equalsIgnoreCase(masp)) {
                return false; // Mã đã tồn tại
            }
        }
        return true; // Mã duy nhất
    }
    
    // Ghi lại toàn bộ file sản phẩm dựa trên dữ liệu hiện tại trong bộ nhớ
    private void ghiLaiToanBoFileSanPham() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt"))) {
            for (int i = 0; i < n; i++) {
                if (dsvpp[i] == null) continue;
                VanPhongPham sp = dsvpp[i];
                String loai = (sp.getLoaiSP() != null) ? sp.getLoaiSP().getMaLoai() : "";
                String line = loai + "," +
                              sp.getMaSP() + "," +
                              sp.getTenSP() + "," +
                              sp.getSoLuong() + "," +
                              sp.getDonGia() + "," +
                              sp.getDonViTinh();
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
    
    // Cập nhật số lượng sản phẩm theo mã (cho phép cộng/trừ tồn kho)
    public void capNhatSLSP(String masp, int soluong) {
        if (masp == null || masp.trim().isEmpty()) {
            System.out.println("❌ Mã sản phẩm không hợp lệ.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (dsvpp[i] != null && dsvpp[i].getMaSP().equalsIgnoreCase(masp)) {
                int sanphamhientai = dsvpp[i].getSoLuong();
                int spmoi = sanphamhientai + soluong; // soluong có thể âm (bán hàng) hoặc dương (nhập hàng)
                if (spmoi < 0) {
                    System.out.println("❌ Không đủ tồn kho để trừ. Hiện tại: " + sanphamhientai + ", yêu cầu trừ: " + (-soluong));
                    return;
                }
                dsvpp[i].setSoLuong(spmoi);
                ghiLaiToanBoFileSanPham();
                System.out.println("✅ Đã cập nhật SL sản phẩm " + dsvpp[i].getMaSP() + " từ " + sanphamhientai + " -> " + spmoi);
                return;
            }
        }
        System.out.println("❌ Không tìm thấy sản phẩm có mã: " + masp);
    }
    
}
