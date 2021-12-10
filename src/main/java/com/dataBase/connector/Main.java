package com.dataBase.connector;

import com.dataBase.entity.*;
import com.dataBase.hibernate.businessLogic.*;
import com.dataBase.hibernate.classes.AuditoryDAO;
import com.dataBase.hibernate.classes.GroupDAO;
import com.dataBase.hibernate.classes.ScheduleDAO;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Работа с базой данных при помощи Hibernate");

        mainExit:
        while (true) {
            consoleView();

            Scanner scanner = new Scanner(System.in);

            System.out.print("Выполнить функцию: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1->{
                    innerExit:
                    while (true) {

                        System.out.println("\n1. Добавить запись в таблицу расписания");
                        System.out.println("2. Добавить запись в таблицу аудиторий");
                        System.out.println("3. Добавить запись в таблицу групп");
                        System.out.println("4. Закончить с добавлением");

                        System.out.print("\nНомер функции: ");
                        int newChoice = scanner.nextInt();

                        switch (newChoice) {
                            case 1 -> {
                                System.out.print("Номер недели: ");
                                int numberWeek = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("День недели: ");
                                String dayOfWeek = scanner.nextLine();
                                Day day = new Day(dayOfWeek);
                                System.out.print("Время начала: ");
                                String timeStart = scanner.nextLine();
                                System.out.print("Время окончания: ");
                                String timeEnd = scanner.nextLine();
                                Time time = new Time(timeStart, timeEnd);
                                System.out.print("Группа: ");
                                String groupStr = scanner.nextLine();
                                Group group = new Group(groupStr);
                                System.out.print("Аудитория: ");
                                String auditoryStr = scanner.nextLine();
                                Auditory auditory = new Auditory(auditoryStr);
                                ServicesSchedule servicesSchedule = new ServicesSchedule();
                                if(numberWeek>0 && numberWeek <18 && day.getId() != -1L && time.getId() !=-1L) {
                                    try {
                                        servicesSchedule.save(new Schedule(numberWeek, day, time, group, auditory));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                else {
                                    System.out.println("Ошибка! Один или несколько параметров введены неправильно!");
                                }
                            }
                            case 2 -> {
                                scanner.nextLine();
                                System.out.print("Аудитория: ");
                                String auditoryStr = scanner.nextLine();
                                ServicesAuditory servicesAuditory = new ServicesAuditory();
                                try {
                                    servicesAuditory.save(new Auditory(auditoryStr));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            case 3 -> {
                                scanner.nextLine();
                                System.out.print("Группа: ");
                                String groupStr = scanner.nextLine();
                                ServicesGroup servicesGroup = new ServicesGroup();
                                try {
                                    servicesGroup.save(new Group(groupStr));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            case 4 -> {
                                break innerExit;
                            }
                            default -> System.out.println("Вы ввели что-то не то! Попробуйте еще раз");

                        }
                    }
                }
                case 2->{
                    innerExit:
                    while (true) {
                        System.out.println("\n1. Удалить запись в таблице расписаний");
                        System.out.println("2. Удалить записи в таблице аудиторий по аудитории");
                        System.out.println("3. Удалить записи в таблице групп по группе");
                        System.out.println("4. Закончить с удалением");

                        System.out.print("\nНомер функции: ");
                        int newChoice = scanner.nextInt();

                        switch (newChoice) {
                            case 1 -> {
                                ServicesSchedule servicesSchedule = new ServicesSchedule();
                                scanner.nextLine();
                                System.out.print("Введите порядковый номер записи: ");
                                int id = scanner.nextInt();
                                try {
                                    servicesSchedule.delete(id);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Успешное удаление!");
                            }
                            case 2 -> {
                                scanner.nextLine();
                                System.out.print("Удаляем аудиторию: ");
                                String auditoryStr = scanner.nextLine();
                                ServicesAuditory servicesAuditory = new ServicesAuditory();
                                try {
                                    servicesAuditory.delete(new Auditory(auditoryStr));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Успешное удаление!");
                            }
                            case 3 -> {
                                scanner.nextLine();
                                System.out.print("Удаляем группу: ");
                                String groupStr = scanner.nextLine();
                                ServicesGroup servicesGroup = new ServicesGroup();
                                try {
                                    servicesGroup.delete(new Group(groupStr));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Успешное удаление!");
                            }
                            case 4 -> {
                                break innerExit;
                            }
                            default -> System.out.println("Вы ввели что-то не то! Попробуйте еще раз");
                        }
                    }

                }
                case 3->{
                    innerExit:
                    while (true) {

                        System.out.println("\n1.  Редактировать всю запись по номеру");
                        System.out.println("2.  Редактировать номер недели в таблице расписаний по номеру");
                        System.out.println("3.  Редактировать день в таблице расписаний по номеру");
                        System.out.println("4.  Редактировать время в таблице расписаний по номеру");
                        System.out.println("5.  Редактировать группу в таблице расписаний по номеру");
                        System.out.println("6.  Редактировать аудиторию в таблице расписаний по номеру");
                        System.out.println("7.  Редактировать аудиторию в таблице аудиторий по номеру");
                        System.out.println("8.  Редактировать аудиторию в таблице аудиторий по названию");
                        System.out.println("9.  Редактировать группу в таблице групп по номеру");
                        System.out.println("10. Редактировать группу в таблице групп по названию");
                        System.out.println("11. Закончить с редактированием");

                        System.out.print("\nНомер функции: ");
                        int newChoice = scanner.nextInt();

                        switch (newChoice) {
                            case 1 -> {
                                scanner.nextLine();
                                System.out.print("Номер записи в таблице расписаний: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Номер недели: ");
                                int numberWeek = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("День недели: ");
                                String dayOfWeek = scanner.nextLine();
                                Day day = new Day(dayOfWeek);
                                System.out.print("Время начала: ");
                                String timeStart = scanner.nextLine();
                                System.out.print("Время окончания: ");
                                String timeEnd = scanner.nextLine();
                                Time time = new Time(timeStart, timeEnd);
                                System.out.print("Группа: ");
                                String groupStr = scanner.nextLine();
                                Group group = new Group(groupStr);
                                System.out.print("Аудитория: ");
                                String auditoryStr = scanner.nextLine();
                                Auditory auditory = new Auditory(auditoryStr);
                                ServicesSchedule servicesSchedule = new ServicesSchedule();

                                if (day.getId() != -1l & time.getId() != -1l & numberWeek > 0 & numberWeek < 18) {
                                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                                    scheduleDAO.update(new Schedule(id, numberWeek, day, time, group, auditory));
                                } else {
                                    System.out.println("Вы что-то ввели неправильно! Попробуйте еще разок");
                                }

                            }
                            case 2 -> {
                                scanner.nextLine();
                                System.out.print("Номер записи в таблице расписаний для редактирования номера недели: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новый номер недели: ");
                                int weekNumber = scanner.nextInt();

                                if (weekNumber > 0 & weekNumber < 18) {
                                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                                    scheduleDAO.update(id, weekNumber);
                                } else {
                                    System.out.println("Такого номера недели не существует!");
                                }

                            }
                            case 3 -> {
                                scanner.nextLine();
                                System.out.print("Номер записи в таблице расписаний для редактирования дня: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новый День: ");
                                String dayStr = scanner.nextLine();

                                Day day = new Day(dayStr);

                                if (day.getId() != -1l) {
                                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                                    scheduleDAO.update(id, day);
                                } else {
                                    System.out.println("Такого дня нет!");
                                }

                            }
                            case 4 -> {
                                scanner.nextLine();
                                System.out.print("Номер записи в таблице расписаний для редактирования времени: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новое время начала: ");
                                String timeStartStr = scanner.nextLine();
                                System.out.print("Новое время окончания: ");
                                String timeEndStr = scanner.nextLine();

                                Time time = new Time(timeStartStr, timeEndStr);

                                if (time.getId() != -1l) {
                                    ScheduleDAO scheduleDAO = new ScheduleDAO();
                                    scheduleDAO.update(id, time);
                                } else {
                                    System.out.println("Таких промежутков времени нет!");
                                }

                            }
                            case 5 -> {
                                scanner.nextLine();
                                System.out.print("Номер записи в таблице расписаний для редактирования группы: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новая Группа: ");
                                String groupStr = scanner.nextLine();

                                Group group = new Group(id, groupStr);

                                ScheduleDAO scheduleDAO = new ScheduleDAO();
                                scheduleDAO.update(group);

                            }
                            case 6 -> {
                                scanner.nextLine();
                                System.out.print("Номер записи в таблице расписаний для редактирования аудитории: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новая аудитория: ");
                                String auditoryStr = scanner.nextLine();

                                Auditory auditory = new Auditory(id, auditoryStr);

                                ScheduleDAO scheduleDAO = new ScheduleDAO();
                                scheduleDAO.update(auditory);

                            }
                            case 7 -> {
                                scanner.nextLine();
                                System.out.print("Номер аудитории в таблице аудиторий для редактирования: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новая аудитория: ");
                                String auditoryStr = scanner.nextLine();

                                Auditory auditory = new Auditory(id, auditoryStr);

                                AuditoryDAO auditoryDAO = new AuditoryDAO();
                                auditoryDAO.update(auditory);

                            }
                            case 8 -> {
                                scanner.nextLine();
                                System.out.print("Название аудитории в таблице аудиторий для редактирования: ");
                                String auditoryStrBefore = scanner.nextLine();
                                System.out.print("Новая аудитория: ");
                                String auditoryStrAfter = scanner.nextLine();

                                Auditory auditoryBefore = new Auditory(auditoryStrBefore);
                                Auditory auditoryAfter = new Auditory(auditoryStrAfter);

                                AuditoryDAO auditoryDAO = new AuditoryDAO();
                                auditoryDAO.update(auditoryBefore, auditoryAfter);

                            }
                            case 9 -> {
                                scanner.nextLine();
                                System.out.print("Номер группы в таблице групп для редактирования: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Новая группа: ");
                                String groupStr = scanner.nextLine();

                                Group group = new Group(id, groupStr);

                                GroupDAO groupDAO = new GroupDAO();
                                groupDAO.update(group);

                            }
                            case 10 -> {
                                scanner.nextLine();
                                System.out.print("Название группы в таблице групп для редактирования: ");
                                String groupStrBefore = scanner.nextLine();
                                System.out.print("Новая группа: ");
                                String groupStrAfter = scanner.nextLine();

                                Group groupBefore = new Group(groupStrBefore);
                                Group groupAfter = new Group(groupStrAfter);

                                GroupDAO groupDAO = new GroupDAO();
                                groupDAO.update(groupBefore, groupAfter);

                            }
                            case 11 -> {
                                break innerExit;
                            }
                            default -> System.out.println("Вы ввели что-то не то! Попробуйте еще раз");
                        }
                    }
                }
                case 4->{
                    innerExit:
                    while (true) {
                        System.out.println("\n1. Поиск свободной аудитории в заданные часы в течение всего семестра");
                        System.out.println("2. Поиск свободной аудитории на заданное число часов в указанную неделю");
                        System.out.println("3. Закончить с поиском");

                        System.out.print("\nНомер функции: ");
                        int newChoice = scanner.nextInt();

                        switch (newChoice) {
                            case 1 -> {

                                scanner.nextLine();
                                System.out.print("Время начала: ");
                                String timeStartStr = scanner.nextLine();
                                System.out.print("Время окончания: ");
                                String timeEndStr = scanner.nextLine();

                                ScheduleDAO scheduleDAO = new ScheduleDAO();
                                scheduleDAO.findByTime(timeStartStr, timeEndStr);

                            }
                            case 2 -> {

                                scanner.nextLine();
                                System.out.print("Введите количество часов: ");
                                int numberOfHours = scanner.nextInt();
                                System.out.print("Введите номер недели: ");
                                int numberOfWeek = scanner.nextInt();

                                ScheduleDAO scheduleDAO = new ScheduleDAO();
                                scheduleDAO.findByNumberOfHours(numberOfHours, numberOfWeek);
                            }
                            case 3 -> {
                                break innerExit;
                            }
                            default -> System.out.println("Вы ввели что-то не то! Попробуйте еще раз");
                        }
                    }

                }
                case 5->{
                    innerExit:
                    while (true) {
                        System.out.println("\n1. Просмотр таблицы расписания");
                        System.out.println("2. Просмотр таблицы аудиторий");
                        System.out.println("3. Просмотр таблицы групп");
                        System.out.println("4. Закончить с просмотром");

                        System.out.print("\nНомер функции: ");
                        int newChoice = scanner.nextInt();

                        switch (newChoice) {
                            case 1 -> {
                                ScheduleDAO scheduleDAO = new ScheduleDAO();
                                List<Schedule> scheduleList = scheduleDAO.findAll();
                                int i = 1;
                                System.out.println();
                                for (Schedule sc : scheduleList) {
                                    System.out.print(i + ".\t");
                                    System.out.println("Номер недели: \t\t" + sc.getWeek());
                                    System.out.println("\tДень недели: \t\t" + sc.getDay().getDay());
                                    System.out.println("\tВремя начала: \t\t" + sc.getTime().getTimeStart());
                                    System.out.println("\tВремя окончания: \t" + sc.getTime().getTimeEnd());
                                    System.out.println("\tГруппа: \t\t\t" + sc.getGroup().getGroup());
                                    System.out.println("\tАудитория: \t\t\t" + sc.getAuditory().getAuditory());
                                    System.out.println();
                                    i++;
                                }
                            }
                            case 2 -> {
                                AuditoryDAO auditoryDAO = new AuditoryDAO();
                                List<Auditory> auditoryList = auditoryDAO.findAll();
                                int i = 1;
                                System.out.println();
                                for (Auditory au : auditoryList) {
                                    System.out.println(i + " -> " + au.getAuditory());
                                    i++;
                                }
                                System.out.println();
                            }
                            case 3 -> {
                                GroupDAO groupDAO = new GroupDAO();
                                List<Group> groupList = groupDAO.findAll();
                                int i = 1;
                                System.out.println();
                                for (Group gr : groupList) {
                                    System.out.println(i + " -> " + gr.getGroup());
                                    i++;
                                }
                                System.out.println();
                            }
                            case 4 -> {
                                break innerExit;
                            }
                            default -> System.out.println("Вы ввели что-то не то! Попробуйте еще раз");
                        }
                    }
                }
                case 6-> consoleView();
                case 7-> {
                    break mainExit;
                }
                default -> System.out.println("Такой функции не сущетсвует! Повторите попытку...");
            }

        }

    }

    public static void consoleView(){

        System.out.println();
        System.out.println("Список функций:");
        System.out.println("1. Добавление записей");
        System.out.println("2. Удаление записей из таблицы");
        System.out.println("3. Редактирование записей");
        System.out.println("4. Поиск по заданию");
        System.out.println("5. Просмотр таблиц");
        System.out.println("6. Список функций еще раз");
        System.out.println("7. Завершение\n");

    }

}

/*    private static String url = "jdbc:postgresql://localhost:5433/mydbase";
    private static String user = "stanis";
    private static String password = "130263";
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
