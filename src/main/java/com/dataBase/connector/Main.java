package com.dataBase.connector;

import com.dataBase.entity.Day;
import com.dataBase.hibernate.businessLogic.Services;
import com.dataBase.entity.Auditory;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static String url = "jdbc:postgresql://localhost:5433/mydbase";
    private static String user = "stanis";
    private static String password = "130263";

    public static void main(String[] args) {

        /*
        Connection connection = null;

        try{
            //Добавляем к драйвер менеджеру новый двайвер
            Class.forName("org.postgresql.Driver");

            //подключаемся к нужному драйверу и создаем соединение
            connection = DriverManager.getConnection(url, user, password);

            //создаем новый стейтмент
            Statement statement = connection.createStatement();

            //пишем запрос
            ResultSet resultSet = statement.executeQuery(
                    "select * from auditory_table");

            //считываем все
            int i = 0;
            while (resultSet.next()){
                System.out.println(++i + ") " + resultSet.getInt("id")
                        + " " + resultSet.getString("auditory"));
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null)
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }

 */

        Scanner scanner = new Scanner(System.in);

//        System.out.print("Введите номер аудитории: ");
//        String number = scanner.nextLine();

        Services services = new Services();

        Auditory auditory = new Auditory("777");

        //services.saveAuditory(auditory);


    }

}
