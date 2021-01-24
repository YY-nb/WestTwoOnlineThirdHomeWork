package ThirdHomework;

import com.alibaba.fastjson.JSONReader;
import jdk.nashorn.internal.scripts.JD;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {


    //存放国家和省份的列表，用完即删，因为要准备存放下一个国家和对应省份
    private static List<Country> countryList=new ArrayList<>();
    private static List<Province> provinceList=new ArrayList<>();
    private static String foreignKey=null;  //存的是province表的外键即国家的缩写



    /*  数据插入  */
//解析json文件将json字符串转成javaBean,然后再传入数据库

//用于解析json文件然后将值记录在country和province类中
    public static void fillList(String filePath) throws FileNotFoundException {

        JSONReader reader = new JSONReader(new FileReader(filePath));  //获取json文件

        //开始解析最外层  (包含all和省份名）
        reader.startObject();
        Country country = new Country();
        while (reader.hasNext()) {

            Province province = new Province();
            String key = reader.readString();
            //把相应的数据存入country表
            if (key.equals("All")) {
                //解析All中的数据 (解析内层数据)
                reader.startObject();

                while (reader.hasNext()) {
                    String innerKey = reader.readString();
                    String value = reader.readObject().toString();
                    setForCountry(country, innerKey, value);

                }

                countryList.add(country);
                reader.endObject();
            }
            //当key不是All时，也就是此时处理的是省份，key存的是province_name
            else{
                //解析内层数据
                reader.startObject();

                while (reader.hasNext()) {
                    String innerKey = reader.readString();
                    String value = reader.readObject().toString();
                    setForProvince(province,key,innerKey,value);

                }
                provinceList.add(province);

                reader.endObject();
            }


        }
        reader.endObject();


        reader.close();

    }
    public  static void insertAll(){
        try {
            insert(".\\WestTwoOnline\\src\\ThirdHomework\\Json_text\\China.txt");  //这里是相对路径
            insert(".\\WestTwoOnline\\src\\ThirdHomework\\Json_text\\Japan.txt");  //这里是相对路径
            insert(".\\WestTwoOnline\\src\\ThirdHomework\\Json_text\\US.txt");  //这里是相对路径
            insert(".\\WestTwoOnline\\src\\ThirdHomework\\Json_text\\UK.txt");  //这里是相对路径
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public  static void insert(String path) throws FileNotFoundException, SQLException {
        fillList(path);
        String sqlForCountry = "INSERT INTO `epidemic`.`country` (`confirmed`, `recovered`, `deaths`, `country_name`, `population`, `sq_km_area`, `life_expectancy`, `elevation_in_meters`, `continent`, `country_abbreviation`, `location`, `iso`, `capital_city`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlForProvince = "INSERT INTO `epidemic`.`province` (`province_name`, `confirmed`, `recovered`, `deaths`, `latitude`, `longitude`, `updated_time`, `country_abbreviation`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement psForCountry=null;
        PreparedStatement psForProvince=null;

        connection = JDBC.getConnection();
        psForCountry=connection.prepareStatement(sqlForCountry);
        psForProvince=connection.prepareStatement(sqlForProvince);

        //往mysql插份数据

        insertForCountry(psForCountry,countryList);
        insertForProvince(psForProvince,provinceList);

        JDBC.close(psForCountry);
        JDBC.close(psForProvince);
        JDBC.close(connection);



    }
    //根据json数据中不同的key，把对应的值存入Country类中
    public static void setForCountry(Country country, String Key, String value)  {

        switch (Key) {
            case "confirmed":
                country.setConfirmed(Integer.parseInt(value));

                break;
            case "recovered":
                country.setRecovered(Integer.parseInt(value));

                break;
            case "deaths":
                country.setDeaths(Integer.parseInt(value));

                break;
            case "country":
                country.setCountry_name(value);

                break;
            case "population":
                country.setPopulation(Integer.parseInt(value));

                break;
            case "sq_km_area":
                country.setSq_km_area(Double.parseDouble(value));

                break;
            case "life_expectancy":
                country.setLife_expectancy(Double.parseDouble(value));

                break;
            case"elevation_in_meters":
                country.setElevation_in_meters(Double.parseDouble(value));

                break;
            case "continent":
                country.setContinent(value);

                break;
            case "abbreviation":
                country.setCountry_abbreviation(value);
                foreignKey=value;  //province表的外键
                break;
            case "location":
                country.setLocation(value);

                break;
            case "iso":
                country.setIso(Integer.parseInt(value));

                break;
            case "capital_city":
                country.setCapital_city(value);

                break;

        }

    }
    public static void setForProvince(Province province, String key,String innerKey, String value){
        province.setProvince_name(key);
        province.setCountry_abbreviation(foreignKey);
        if(value.equals("")){

            return;
        }
        switch (innerKey){
            case "lat":
                province.setLatitude(Double.parseDouble(value));
                break;
            case "long":
                province.setLongitude(Double.parseDouble(value));
                break;
            case "confirmed":
                province.setConfirmed(Integer.parseInt(value));
                break;
            case "recovered":
                province.setRecovered(Integer.parseInt(value));
                break;
            case "deaths":
                province.setDeaths(Integer.parseInt(value));
                break;
            case "updated":
                province.setUpdated_time(value);
                break;
        }
    }
    //把保存在countryList中的数据存入mysql
    public static void insertForCountry(PreparedStatement ps,List<Country> countryList) throws SQLException {

        for(Country country:countryList) {
            int index = 1; //prepareStatement的set方法的索引
            ps.setInt(index++, country.getConfirmed());
            ps.setInt(index++, country.getRecovered());
            ps.setInt(index++, country.getDeaths());
            ps.setString(index++, country.getCountry_name());
            ps.setInt(index++, country.getPopulation());
            ps.setDouble(index++, country.getSq_km_area());
            ps.setDouble(index++, country.getLife_expectancy());
            ps.setDouble(index++, country.getElevation_in_meters());
            ps.setString(index++, country.getContinent());
            ps.setString(index++, country.getCountry_abbreviation());
            ps.setString(index++, country.getLocation());
            ps.setInt(index++, country.getIso());
            ps.setString(index++, country.getCapital_city());
            ps.executeUpdate();
            System.out.println(country.getCountry_name()+"的country表插入成功！");

        }
            countryList.clear();
    }
    //把保存在provinceList中的数据存入mysql
    public static void insertForProvince(PreparedStatement ps,List<Province> provinceList) throws SQLException {

        for(Province province:provinceList) {
            int index=1;
            ps.setString(index++, province.getProvince_name());
            ps.setInt(index++, province.getConfirmed());
            ps.setInt(index++, province.getRecovered());
            ps.setInt(index++, province.getDeaths());
            ps.setDouble(index++, province.getLatitude());
            ps.setDouble(index++, province.getLongitude());
            ps.setString(index++, province.getUpdated_time());
            ps.setString(index++, province.getCountry_abbreviation());
            ps.executeUpdate();

        }
        System.out.println("当前国家的province表插入成功！");
        provinceList.clear();
    }

 /*  数据删除   */
    public static void deleteAll() {
            String sql1="create procedure truncate() begin SET foreign_key_checks = 0;truncate table epidemic.province;" +
                    "truncate table epidemic.country; SET foreign_key_checks = 1; end";   //存储过程
            String sql2="call truncate()";


            PreparedStatement ps= null;

            Connection connection=null;

            try {
                connection=JDBC.getConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(sql1);
                ps=connection.prepareStatement(sql2);
                ps.executeUpdate();

                connection.commit();
                System.out.println("删除成功！");
            } catch (SQLException throwables) {
                if(connection!=null){
                    try {
                        connection.rollback();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                throwables.printStackTrace();
            }finally {
                JDBC.close(ps);


            }


        }
/* 查询数据  */
    public static  void selectAllCountry(){
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        String sql1="select* from epidemic.country";


        try {
            connection=JDBC.getConnection();

            ps=connection.prepareStatement(sql1);
            resultSet= ps.executeQuery();
            while(resultSet.next()){
                String confirmed=resultSet.getString("confirmed");
                String recovered=resultSet.getString("recovered");
                String deaths=resultSet.getString("deaths");
                String country_name=resultSet.getString("country_name");
                String population=resultSet.getString("population");
                String  sq_km_area=resultSet.getString("sq_km_area");
                String life_expectancy=resultSet.getString("life_expectancy");
                String elevation_in_meters=resultSet.getString("elevation_in_meters");
                String continent=resultSet.getString("continent");
                String country_abbreviation=resultSet.getString("country_abbreviation");
                String location=resultSet.getString("location");
                String iso=resultSet.getString("iso");
                String capital_city=resultSet.getString("capital_city");
                System.out.println("confirmed:"+confirmed+" ,recovered:"+recovered+" ,deaths:"+deaths+" ,country_name:"+country_name+" ,population:"+population+" ,sq_km_area"+sq_km_area+" life_expectancy:"+life_expectancy+" ,elevation_in_meters"+elevation_in_meters+" ,continent:"+continent+" ,country_abbreviation:"+country_abbreviation+" ,location:"+location+" ,iso:"+iso+" ,capital_city:"+capital_city);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC.close(connection);
            JDBC.close(ps);
            JDBC.close(resultSet);
        }

    }
    public  static void selectAllProvince(){
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        String sql1="select* from epidemic.province";


        try {
            connection=JDBC.getConnection();

            ps=connection.prepareStatement(sql1);
            resultSet= ps.executeQuery();
            while(resultSet.next()){
                String province_name=resultSet.getString("province_name");
                String confirmed=resultSet.getString("confirmed");
                String recovered=resultSet.getString("recovered");
                String deaths=resultSet.getString("deaths");
                String latitude=resultSet.getString("latitude");
                String  longitude=resultSet.getString("longitude");
                String updated_time=resultSet.getString("updated_time");
                String country_abbreviation=resultSet.getString("country_abbreviation");

                System.out.println("province_name:"+province_name+" ,confirmed:"+confirmed+" ,recovered:"+recovered+" ,deaths:"+deaths+" ,latitude:"+latitude+" ,longitude:"+longitude+" ,updated_time:"+updated_time+" ,country_abbreviation:"+country_abbreviation);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC.close(connection);
            JDBC.close(ps);
            JDBC.close(resultSet);
        }

    }


}
