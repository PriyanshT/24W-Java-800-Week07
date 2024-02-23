package org.georgiancollege.week07.Utilities;

import javafx.scene.chart.XYChart;
import org.georgiancollege.week07.Model.Book;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    // create variables to hold username, password and connection url
    private static String user = DBCreds.findUser();
    private static String pass = DBCreds.findPass();
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/" + user;

    // a static function which inserts the book data to db
    // this will return the generated book id
    public static int insertBookIntoDB(Book book) throws SQLException {
        int bookId = -1;
        ResultSet resultSet = null;

        // create the sql string to insert data
        String sql = "INSERT INTO books (book_name, author, genre, price, is_available) VALUES (?, ?, ?, ?, ?);";

        // try with resource block
        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"bookId"});
                ){
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setBoolean(5, book.isAvailable());

            // execute the insert statement
            preparedStatement.executeUpdate();

            // get the new book id from the result set
            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                bookId = resultSet.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                resultSet.close();
            }
        }

        return bookId;
    }

    // a static function which will retrieve from db and return an ArrayList
    public static ArrayList<Book> getBooksFromDB(String clause){
        ArrayList<Book> books = new ArrayList<>();

        String sql = "SELECT books.book_id, books.book_name, books.author, books.genre, books.price, books.is_available, COUNT(bookSales.sold_date) AS \"units_sold\"\n" +
                "FROM books\n" +
                "INNER JOIN bookSales\n" +
                "ON books.book_id = bookSales.book_id\n" +
                "WHERE " + clause + "\n" +
                "GROUP BY books.book_id;";

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pass);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            while (resultSet.next()){
                int bookId = resultSet.getInt("book_id");
                String bookName = resultSet.getString("book_name");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                double price = resultSet.getDouble("price");
                boolean isAvailable = resultSet.getBoolean("is_available");
                int unitsSold = resultSet.getInt("units_sold");

                Book book = new Book(bookId, bookName, author, genre, price, isAvailable, unitsSold);
                books.add(book);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return books;
    }

    public static XYChart.Series<String, Integer> getChartDataFromDB() {
        XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
        ArrayList<Book> books = getBooksFromDB("1");
        chartData.setName("2024");

//        chartData.getData().add(new XYChart.Data<>("Fake Book 1", 20));
//        chartData.getData().add(new XYChart.Data<>("Fake Book 2", 40));
//        chartData.getData().add(new XYChart.Data<>("Fake Book 3", 60));

        // add all the books data to chartData
        for(Book book:books){
            chartData.getData().add(new XYChart.Data<>(book.getBookName(), book.getUnitsSold()));
        }

        return chartData;
    }

    public static XYChart.Series<String, Integer> getAvailableChartDataFromDB() {
        XYChart.Series<String, Integer> chartData = new XYChart.Series<>();
        ArrayList<Book> books = getBooksFromDB("1");
        chartData.setName("2024");

        // add only available books data to chartData
        for(Book book:books){
            if(book.isAvailable()){
                chartData.getData().add(new XYChart.Data<>(book.getBookName(), book.getUnitsSold()));
            }
        }

        return chartData;
    }
}
