import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*Написать код, который выведет для каждого курса среднее количество покупок в месяц.
 Лучше только средствами SQL (вариант “со звёздочкой”), но группировку по месяцам можно реализовать и с помощью Java.*/


public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String password = "testtest";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select courses.name as course_name, count(subscriptions.student_id)" +
                    "/12 as average from courses join subscriptions on courses.id " +
                    "= subscriptions.course_id group by courses.id;");
            System.out.println("Среднее колл-во покупок в месяц курсов:");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String purchase = resultSet.getString("average");
                System.out.println("\t'" + courseName + "' = " + purchase);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
