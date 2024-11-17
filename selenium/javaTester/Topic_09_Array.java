package javaTester;

public class Topic_09_Array {
    int [] studentAge ={15,10, 20,22};
    static String [] studentName = {"Nguyễn Văn A", "Lê Văn Hòa"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];
        studentAddress[0] = "Đặng Ngọc Anh";
        studentAddress[1] = "Đào Duy Từ";
        studentAddress[2] = "Nguyễn Trãi 1";
        studentAddress[3] = "Nguyễn Trãi 2";
        studentAddress[4] = "Nguyễn Trãi 3";
        System.out.println(studentName[0]);

        //Muốn in studentAge -> cần khai báo 1 đối tượng đại diện cho class Topic_09_Array
        Topic_09_Array topic = new Topic_09_Array();
        System.out.println("Student Age:" + topic.studentAge[1]);
        for(int i = 0; i<studentAddress.length;i++){
            System.out.println(studentAddress[i]);
        }
    }

}
