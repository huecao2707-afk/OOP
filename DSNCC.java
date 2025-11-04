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
            System.out.print("Nhap so luong nha cung cap n = ");
            n = sc.nextInt();
            dsncc = new NhaCungCap[n];
            for (int i = 0; i < n; i++){
                System.out.println("Nhap thong tin nha cung cap thu " + (i + 1) + ":");
                dsncc[i] = new NhaCungCap();
                dsncc[i].nhap();
            }
        }

        public void xuat(){
            String format = "| %-30s | %-35s | %-30s |\n";
            String line = "+--------------------------------+-------------------------------------+--------------------------------+";
            System.out.println(line);
            System.out.printf(format, 
                    "Mã NCC", "Tên NCC", "Địa Chỉ");
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
            return null; // Không tìm thấy
        }
        //them co tham so
        public void themNhaCungCap(NhaCungCap ncc){
            if (!maDuyNhat(ncc.getMaNCC())) {
                System.out.println("❌ Lỗi: Mã NCC '" + ncc.getMaNCC() + "' đã tồn tại. Không thể thêm.");
                return;
            }
        dsncc[n]= ncc;
        n++;
        ghiFile(ncc);
    } 
    //ghi file
    public void ghiFile(NhaCungCap ncc){
        String line ="";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhaCungCap.txt", true))) {
            line = ncc.getMaNCC() + "," + ncc.getTenNCC() + "," + ncc.getDiaChi();

            bw.write(line);
            bw.newLine();
            System.out.println(" Đã ghi thêm nhà cung cấp '" + ncc.getTenNCC() + "' vào file.");

            } 
        catch (IOException e) {
                System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
            }
    }
    //doc file
    public void docfile1(){
        n=0;
        try(BufferedReader br = new BufferedReader(new FileReader("NhaCungCap.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setmaNCC(parts[0]);
                    ncc.setTenNCC(parts[1]);
                    ncc.setDiaChi(parts[2]);
                    dsncc[n] = ncc;
                    n++;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi đọc file: " + e.getMessage());
        }
    }
    //kiểm tra mã duy nhất
    public boolean maDuyNhat(String mancc) {
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMaNCC().equalsIgnoreCase(mancc)) {
                return false; // Mã đã tồn tại
            }
        }
        return true; // Mã duy nhất
    }
    public static void main(String[] args) {
        DSNCC ds = new DSNCC();
        ds.docfile1();
        ds.xuat();  
        }
}
