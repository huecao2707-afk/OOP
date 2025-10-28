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
        dsvpp = new VanPhongPham[100];
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
            if(other.dsvpp[i] instanceof DoChoi){
                this.dsvpp[i] = new DoChoi((DoChoi) other.dsvpp[i]);
            }else if(other.dsvpp[i] instanceof VanPhong) {
                this.dsvpp[i] = new VanPhong((VanPhong) other.dsvpp[i]);
            }
        }
    }
    public void readFile() {
        n = 0; // Reset số lượng về 0
        try (BufferedReader br = new BufferedReader(new FileReader("VanPhongPham.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (n >= dsvpp.length) { // Kiểm tra mảng (100 chỗ) đầy
                    System.out.println("Mảng đầy, dừng đọc file!");
                    break; 
                }
                
                String[] parts = line.split(",");
                if (parts.length < 1) continue; 

                String loai = parts[0]; // Đọc cột loại (VP, DC)
                VanPhongPham spMoi;

                if (loai.equalsIgnoreCase("VP")) {
                    spMoi = new VanPhong();
                } else if (loai.equalsIgnoreCase("DC")) {
                    spMoi = new DoChoi();
                } else {
                    continue; // Bỏ qua nếu loại không xác định
                }

                // Yêu cầu lớp con tự đọc dữ liệu của nó
                spMoi.docDuLieu(parts);
                // (Bạn phải có hàm này trong lớp con)
                
                dsvpp[n] = spMoi;
                n++; // Tăng số lượng thực tế
            }
        } catch (IOException e) { 
            System.out.println("Lỗi đọc file: ".toUpperCase() + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi xử lý dữ liệu file: " + e.getMessage());
        }
    }
    
    public void xuat() {
        if (n == 0) { // n là số lượng sản phẩm thực tế
            System.out.println("Danh sách sản phẩm rỗng.");
            return;
        }

        System.out.println("\n--- DANH SÁCH TẤT CẢ SẢN PHẨM ---");
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-13s | %-13s | %-20s | %-10s | %-10s |\n",
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính", "Phân Loại Chức Năng", "Độ Tuổi", "Thể Loại");
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");

        for (int i = 0; i < n; i++) {
            if (dsvpp[i] != null) {
                dsvpp[i].xuat(); // Gọi hàm xuat() của VanPhong hoặc DoChoi tùy theo đối tượng
            }
        }
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");
    }
    public void themSanPham() {
        // 1. Kiểm tra mảng đầy (cho bộ nhớ)
        if (n >= dsvpp.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- THÊM SẢN PHẨM MỚI ---");
        System.out.println("1. Nhập Sản Phẩm Đồ Chơi");
        System.out.println("2. Nhập Sản Phẩm Văn Phòng");
        System.out.print("Lựa chọn loại sản phẩm: ");
        int luachon = sc.nextInt();
        sc.nextLine(); // Tránh trôi lệnh

        VanPhongPham spMoi;
        String loaiPrefix = ""; // Chuỗi "VP" hoặc "DC" để ghi file

        if (luachon == 1) {
            spMoi = new DoChoi();
            loaiPrefix = "DC";
        } else if (luachon == 2) {
            spMoi = new VanPhong();
            loaiPrefix = "VP";
        } else {
            System.out.println("❌ Lựa chọn không hợp lệ.");
            return;
        }

        // 2. Nhập thông tin sản phẩm từ bàn phím
        spMoi.nhap(this); // Gọi hàm nhap() của lớp con (DoChoi hoặc VanPhong)
        // --- BẮT ĐẦU PHẦN GHI FILE ---
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("VanPhongPham.txt", true))) {
            String line = "";

            // Lấy thông tin chung
            line = loaiPrefix + "," +
                spMoi.getMaSP() + "," + spMoi.getTenSP() + "," + spMoi.getSoLuong() + "," +
                spMoi.getDonGia() + "," + spMoi.getDonViTinh();

            // Thêm thông tin riêng dựa vào loại
            if (spMoi instanceof VanPhong) {
                line += "," + ((VanPhong) spMoi).getPhanLoaiChucNang();
            } else if (spMoi instanceof DoChoi) {
                line += "," + ((DoChoi) spMoi).getLua_tuoi() + "," + ((DoChoi) spMoi).getThe_loai();
            }

            // Ghi dòng mới vào cuối file
            bw.write(line);
            bw.newLine();

            System.out.println("-> Đã ghi thêm sản phẩm '" + spMoi.getTenSP() + "' vào file.");

        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
            // Nếu ghi file lỗi, có thể bạn không muốn thêm vào mảng nữa
            return; // Thoát hàm nếu ghi file thất bại
        }
        // --- KẾT THÚC PHẦN GHI FILE ---

        // 3. Thêm sản phẩm mới vào mảng trong bộ nhớ
        dsvpp[n] = spMoi;
        n++; // Tăng số lượng trong mảng
        System.out.println("-> Đã thêm sản phẩm vào bộ nhớ.");
    }
    public VanPhongPham timMaSP(String ma_sp) {
       for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equalsIgnoreCase(ma_sp)) {
                return dsvpp[i]; // Trả về đối tượng sản phẩm nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy

    }
    public boolean maDuyNhat(String ma_sp) {
        for (int i = 0; i < n; i++) {
            if (dsvpp[i].getMaSP().equalsIgnoreCase(ma_sp)) {
                return false; // Mã đã tồn tại
            }
        }
        return true; // Mã duy nhất
    }
    
}
