import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

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
    public void ghiThemSP(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt", true))) {
            String line = "";

            // // Lấy thông tin chung
            // line = loai + "," +
            //     spmoi.getMaSP() + "," + spmoi.getTenSP() + "," + spmoi.getSoLuong() + "," +
            //     spmoi.getDonGia() + "," + spmoi.getDonViTinh();

            // // Thêm thông tin riêng dựa vào loại
            // if (spmoi instanceof VanPhong) {
            //     line += "," + ((VanPhong) spmoi).getPhanLoaiChucNang();
            // } else if (spmoi instanceof DoChoi) {
            //     line += "," + ((DoChoi) spmoi).getLuaTuoi() + "," + ((DoChoi) spmoi).getTheLoai();
            // }

            // Ghi dòng mới vào cuối file
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi thêm vào file: " + e.getMessage());
            return;
        }
    }
    
    public void docFileVPP() {
        n = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("VanPhongPham.txt"))){

            String line = ""; // Lưu từng dòng đọc được
            while(true){
                line = br.readLine();
                if (line == null)
                    break;
                VanPhongPham vpp = null;
                String [] thongtin = line.split(",");
                String loai = thongtin[0];
                if(loai.equals("VP")){
                    vpp = new VanPhong();
                }
                else if (loai.equals("DC")){
                    vpp = new DoChoi();
                }
                else {
                    System.out.println("Lỗi dữ liệu: Loại sản phẩm không hợp lệ! Bỏ qua dòng "+ line);
                    continue; // Bỏ qua dòng không hợp lệ
                }
                LoaiSanPham loaispmoi = new LoaiSanPham();
                loaispmoi.setMaLoai(loai);//set cua class loaisanpham
                vpp.setMaLoai(loaispmoi);// set cua class vanphongpham
                vpp.setMaSP(thongtin[1]);
                vpp.setTenSP(thongtin[2]);
                vpp.setSoLuong(Integer.parseInt(thongtin[3]));
                vpp.setDonGia(Integer.parseInt(thongtin[4]));
                vpp.setDonViTinh(thongtin[5]);
                if (loai.equals("VP")){
                    ((VanPhong)vpp).setPhanLoaiChucNang(thongtin[6]);
                }
                else if (loai.equals("DC")){
                    ((DoChoi)vpp).setLuaTuoi(thongtin[6]);
                    ((DoChoi)vpp).setTheloai(thongtin[7]);
                }
                dsvpp[n] = vpp; // Thêm sản phẩm vào mảng
                n++; // Tăng số lượng sản phẩm thực tế
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void xuat() {
        if (n == 0) { // n là số lượng sản phẩm thực tế
            System.out.println("Danh sách sản phẩm rỗng.");
            return;
        }

        System.out.println("\n                                                      DANH SÁCH TẤT CẢ SẢN PHẨM                                                              ");
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-13s | %-13s | %-20s | %-10s | %-10s |\n",
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính", "Phân Loại Chức Năng", "Độ Tuổi", "Thể Loại");
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");

        for (int i = 0; i < n; i++) {
            if (dsvpp[i] != null) {
                dsvpp[i].xuat(); // Gọi hàm xuat() của VanPhong hoặc DoChoi tùy theo đối tượng -> Đa hình
            }
        }
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");
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
    // Vòng lặp kiểm tra người dùng nhập số nguyên
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
        loai.setTenLoai("Đồ Chơi"); // (Nên gán cả tên)
    } else if (luachon == 2) {
        spmoi = new VanPhong();
        loai.setMaLoai("VP"); // 2. Gán Mã Loại ("VP") vào "Thẻ"
        loai.setTenLoai("Văn Phòng"); // (Nên gán cả tên)
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

    spmoi.setMaLoai(loai);

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
        LoaiSanPham loaisp = spmoi.getMaLoai();
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
    
}
