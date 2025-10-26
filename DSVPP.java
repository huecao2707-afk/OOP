import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DSVPP {
    private int n;// so luong san pham
    private VanPhongPham ds_vpp[];
    public DSVPP(){
        ds_vpp = new VanPhongPham[100];
        n = 0;
    }
    public DSVPP(int n, VanPhongPham [] ds_vpp){
        this.n = n;
        this.ds_vpp = ds_vpp;
    }
    public DSVPP(DSVPP other){
        this.n = other.n;
        this.ds_vpp = new VanPhongPham[n];
        this.ds_vpp = new VanPhongPham[other.ds_vpp.length];
        for (int i = 0; i < ds_vpp.length; i++){
            if(other.ds_vpp[i] instanceof DoChoi){
                this.ds_vpp[i] = new DoChoi((DoChoi) other.ds_vpp[i]);
            }else if(other.ds_vpp[i] instanceof VanPhong){
                this.ds_vpp[i] = new VanPhong((VanPhong) other.ds_vpp[i]);
            }
        }
    }
public void readFile() {
    n = 0; // Reset số lượng về 0
    try (BufferedReader br = new BufferedReader(new FileReader("VanPhongPham.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (n >= ds_vpp.length) { // Kiểm tra mảng (100 chỗ) đầy
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
            
            ds_vpp[n] = spMoi;
            n++; // Tăng số lượng thực tế
        }
    } catch (IOException e) { 
        System.out.println("Lỗi đọc file: ".toUpperCase() + e.getMessage());
    } catch (Exception e) {
        System.out.println("Lỗi xử lý dữ liệu file: " + e.getMessage());
    }
}

    public void writeFile() {
           
    }
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số lượng sản phẩm: ");
        n = sc.nextInt();
        sc.nextLine();
        ds_vpp = new VanPhongPham[n];
        int i = 0;
        while(i < ds_vpp.length){
            System.out.println("1.Nhập Sản Phẩm Đồ Chơi ");
            System.out.println("2.Nhập Sản Phẩm Văn Phòng ");
            int luachon;
            luachon = sc.nextInt();
            sc.nextLine();
            VanPhongPham svMoi;
            if(luachon == 1){
                svMoi = new DoChoi();

            }
            else if(luachon == 2){
                svMoi = new VanPhong();
            }
            else{
                System.out.println("❌ Lựa chọn không hợp lệ.");
                continue;
            }
            svMoi.nhap();
//            while (!kiemTraMaDuyNhat(svMoi.getMa())) {
//                System.out.println("❌ Mã sinh viên đã tồn tại. Vui lòng nhập lại.");
//                System.out.println("Nhập lại mã sinh viên: ");
//                String ma = sc.nextLine();
//                svMoi.setMa(ma);
//            }
            ds_vpp[i] = svMoi;
            i++;
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
            if (ds_vpp[i] != null) {
                ds_vpp[i].xuat(); // Gọi hàm xuat() của VanPhong hoặc DoChoi tùy theo đối tượng
            }
        }
        System.out.println("+------------+----------------------+------------+---------------+---------------+----------------------+------------+------------+");
    }
    public void themSanPham() {
        if (n >= ds_vpp.length) { 
            System.out.println("❌ Mảng đã đầy, không thể thêm!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("1.Nhập Sản Phẩm Đồ Chơi ");
        System.out.println("2.Nhập Sản Phẩm Văn Phòng ");
        System.out.print("Lựa chọn của bạn: ");
        int luachon = sc.nextInt();
        sc.nextLine();
        
        VanPhongPham spMoi;
        if (luachon == 1) {
            spMoi = new DoChoi();
        } else if (luachon == 2) {
            spMoi = new VanPhong();
        } else {
            System.out.println("❌ Lựa chọn không hợp lệ.");
            return;
        }
        
        spMoi.nhap(); // Gọi hàm nhap() của lớp con
        
        // (Nên kiểm tra trùng mã SP ở đây)
        
        ds_vpp[n] = spMoi; // Thêm vào vị trí 'n'
        n++; // Tăng số lượng thực tế
        System.out.println("-> Đã thêm sản phẩm vào bộ nhớ.");
    }
    
}
