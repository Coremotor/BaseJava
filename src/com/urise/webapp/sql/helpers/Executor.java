package com.urise.webapp.sql.helpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Executor<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
