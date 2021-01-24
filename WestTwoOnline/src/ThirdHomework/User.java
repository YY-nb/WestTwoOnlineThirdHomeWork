package ThirdHomework;

import java.util.Scanner;

public class User {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入1~5的一个整数（1为给数据库添加数据，2为删除数据库的数据，3为查询国家数据，4为查询四国所有省份数据,5为停止输入）");
        while(sc.hasNext()){
            int num=sc.nextInt();
            if(num==5) break;
            else{
                switch (num){
                    case 1:
                        DBUtil.insertAll();
                        break;
                    case 2:
                        DBUtil.deleteAll();
                        break;
                    case 3:
                        DBUtil.selectAllCountry();
                        break;
                    case 4:
                        DBUtil.selectAllProvince();
                        break;
                }
            }
        }
    }
}
