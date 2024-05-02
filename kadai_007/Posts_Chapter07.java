package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Emanano2003"
            );
            System.out.println("データベース接続成功");

            // SQLクエリを実行するためのStatementを作成
            statement = con.createStatement();

            // ここで「レコード追加を実行します」というメッセージを出力
            System.out.println("レコード追加を実行します");

            // レコードを追加するSQLクエリ
            String sqlInsert = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
                    + "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),"
                    + "(1002, '2023-02-08', 'お疲れ様です！', 12),"
                    + "(1003, '2023-02-09', '今日も頑張ります！', 18),"
                    + "(1001, '2023-02-09', '無理は禁物ですよ！', 17),"
                    + "(1002, '2023-02-10', '明日から連休ですね！', 20);";
            // レコード追加を実行
            statement.executeUpdate(sqlInsert);
            // 追加されたレコードの件数を出力するには、固定メッセージを使用
            System.out.println("5件のレコードが追加されました");

            // user_idの値を変数で定義
            int userId = 1002;

            // ユーザーIDが変数userIdのレコードを検索することをメッセージで表示
            System.out.println("ユーザーIDが" + userId + "のレコードを検索しました");

            // データを検索して表示
            String selectSql = "SELECT * FROM posts WHERE user_id = " + userId + ";";
            ResultSet resultSet = statement.executeQuery(selectSql);

            int count = 1; // 結果表示用のカウンタ
            while(resultSet.next()) {
                String posted_at = resultSet.getString("posted_at");
                String post_content = resultSet.getString("post_content");
                int likes = resultSet.getInt("likes");
                System.out.println(count + "件目：投稿日時=" + posted_at
                    + "／投稿内容=" + post_content + "／いいね数=" + likes);
                ++count;
            }
             
        } catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // StatementとConnectionを閉じる
            try { if(statement != null) statement.close(); } catch(SQLException ignore) {}
            try { if(con != null) con.close(); } catch(SQLException ignore) {}
        }
    }
}
