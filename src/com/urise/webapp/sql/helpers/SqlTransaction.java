package com.urise.webapp.sql.helpers;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlTransaction<T> {
    T execute(Connection conn) throws SQLException;
}
