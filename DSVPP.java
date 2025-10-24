import java.util.Scanner;

public class DSVPP {
    private int n;
    private VanPhongPham ds_vpp[] = new VanPhongPham[1];
    public DSVPP(){}
    public DSVPP(int n, VanPhongPham [] ds_vpp){
        this.n = n;
        this.ds_vpp = ds_vpp;
    }
    public DSVPP(DSVPP other){
        this.n = other.n;
        this.ds_vpp = new VanPhongPham[n];
        for (int i = 0; i < ds_vpp.length; i++){
            this.ds_vpp[i] = new VanPhongPham(other.ds_vpp[i]);
        }
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

    public void  xuat(){
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-30s %-30s %-30s \n",
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Đơn Vị Tính" , "Phân Loại Theo Chức Năng" , "Phân Loại Theo Độ Tuổi", "Thể Loại");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < ds_vpp.length; i++) {
            ds_vpp[i].xuat(); // Gọi hàm xuat() của từng đối tượng
        }
    }
}
