package internetshop.dao.jdbc;

import java.sql.Connection;

class AbstractDao<T> {
    final Connection connection;

    AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
