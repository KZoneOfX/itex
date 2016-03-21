package com.paipai.function.connectMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/21/2016
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class PdfPageNumberUpdateCase {
    private String file_id;
    private int file_page;

    public PdfPageNumberUpdateCase(String file_id, int file_page) {
        this.file_id = file_id;
        this.file_page = file_page;
    }

    public PdfPageNumberUpdateCase() {
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public int getFile_page() {
        return file_page;
    }

    public void setFile_page(int file_page) {
        this.file_page = file_page;
    }

    public void update_pdf_file_page() throws SQLException {
        Connection connection = ConnectDBFactory.getConnection();
        String sql = "update yunprint_file set file_page = "+file_page +" where file_id = '"+file_id+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
