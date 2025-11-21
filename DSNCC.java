import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class DSNCC {
        private NhaCungCap[] dsncc;
        private int n;
        public DSNCC(){
            n = 0;
            dsncc = new NhaCungCap[300];
        }
        public DSNCC(NhaCungCap[] dsncc, int n){
            this.n = n;
            this.dsncc = dsncc;
        }
        public DSNCC(DSNCC other){
            this.n = other.n;
            this.dsncc = new NhaCungCap[n];
            for (int i = 0; i < dsncc.length; i++){
                this.dsncc[i] = new NhaCungCap(other.dsncc[i]);
            }
        }
        public void nhap(){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập số lượng nhà cung cấp ");
            n = sc.nextInt();
            dsncc = new NhaCungCap[n];
            for (int i = 0; i < n; i++){
                System.out.println("Nhập thông tin nhà cung cấp thứ" + (i + 1) + ":");
                dsncc[i] = new NhaCungCap();
                dsncc[i].nhap();
            }
        }

        public void xuat(){
            if (n == 0) { 
                System.out.println("Danh sách nhà cung cấp trống.");
                return;
            }
            String format = "| %-15s | %-20s | %-15s | %-15s | %-20s |\n";
            String line = "+-----------------+----------------------+-----------------+-----------------+----------------------+";
            System.out.println(line);
            System.out.printf(format, 
                    "Mã NCC", "Tên NCC", "Số Điện Thoại", "Năm thành lập","Địa Chỉ");
            System.out.println(line);
                    for (int i = 0; i < n; i++){
                        dsncc[i].xuat();
                    }
            System.out.println(line);
        }
        public NhaCungCap timKiemTheoMa(String mancc){
            for (int i = 0; i < n; i++){
                if (dsncc[i].getMaNCC().equalsIgnoreCase(mancc)){
                    return dsncc[i];
                }
            }
            return null;
        }
        public NhaCungCap timKiemTheoMa(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập mã của nhà cung cấp cần tìm: ");
            String mancc = sc.nextLine();
    
            for (int i = 0; i < n; i++){
                if (dsncc[i].getMaNCC().equalsIgnoreCase(mancc)){
                    System.out.printf("%s, %s , %s, %s, %s\n", dsncc[i].getMaNCC(), dsncc[i].getTenNCC(), dsncc[i].getNamThanhLap(), dsncc[i].getSDT(), dsncc[i].getDiaChi());
                    return dsncc[i];
                }
            }
            return null;
        }
        public void themNhaCungCap(NhaCungCap ncc){
            if (!maDuyNhat(ncc.getMaNCC())) {
                System.out.println("❌ Lỗi: Mã NCC '" + ncc.getMaNCC() + "' đã tồn tại. Không thể thêm.");
                return;
            }
        dsncc[n]= ncc;
        n++;
        ghiFileNhaCungCap(ncc);
    } 
    public void ghiFileNhaCungCap(NhaCungCap ncc){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhaCungCap.txt", true))) {
            String line = ncc.getMaNCC() + "," + ncc.getTenNCC() + "," + ncc.getSDT() + "," + ncc.getNamThanhLap() + "," + ncc.getDiaChi();

            bw.write(line);
            bw.newLine();
            } 
        catch (IOException e) {
                System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
            }
    }

    public void ghiLaiToanBoFileNhaCungCap() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhaCungCap.txt", false))) {
            for (int i = 0; i < n; i++) {
                NhaCungCap ncc = dsncc[i];
                String line = ncc.getMaNCC() + "," + ncc.getTenNCC() + "," + ncc.getSDT() + "," + ncc.getNamThanhLap() + "," + ncc.getDiaChi();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file: " + e.getMessage());
        }
    }

    public void docFileNhaCungCap(){
        n=0;
        try(BufferedReader br = new BufferedReader(new FileReader("NhaCungCap.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin nhà cung cấp. Bỏ qua: " + line);
                    continue;
                }
                NhaCungCap ncc = new NhaCungCap(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim()
                );

                dsncc[n] = ncc;
                n++;

                if (n >= dsncc.length) {
                    System.out.println("Cảnh báo: Đã đầy mảng lưu trữ khách hàng.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi đọc file: " + e.getMessage());
        }
    }
    public boolean maDuyNhat(String mancc) {
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMaNCC().equalsIgnoreCase(mancc)) {
                return false;
            }
        }
        return true;
    }

    public void them() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== THÊM NHÀ CUNG CẤP MỚI ===");
        NhaCungCap ncc = new NhaCungCap();
        ncc.nhap();

        NhaCungCap[] temp = new NhaCungCap[n + 1];
        for (int i = 0; i < n; i++) {
            temp[i] = dsncc[i];
        }
        temp[n] = ncc;
        dsncc = temp;
        n++;
        ghiFileNhaCungCap(ncc);
        System.out.println(">> Đã thêm nhà cung cấp thành công!");
    }
    
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã nhà cung cấp cần sửa: ");
        String ma = sc.nextLine();

        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMaNCC().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(">> Không tìm thấy mã nhà cung cấp cần sửa!");
            return;
        }

        System.out.println("=== NHẬP LẠI THÔNG TIN NHÀ CUNG CẤP ===");
        dsncc[index].nhap();
        System.out.println(">> Đã cập nhật nhà cung cấp thành công!");
        ghiLaiToanBoFileNhaCungCap();
    }
    
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã nhà cung cấp cần xóa: ");
        String ma = sc.nextLine();

        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMaNCC().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(">> Không tìm thấy mã nhà cung cấp cần xóa!");
            return;
        }

        NhaCungCap[] temp = new NhaCungCap[n - 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                temp[j++] = dsncc[i];
            }
        }
        dsncc = temp;
        n--;

        System.out.println(">> Đã xóa nhà cung cấp thành công!");
        ghiLaiToanBoFileNhaCungCap();
    }
    public int getN() {
        return n; 
    }
}