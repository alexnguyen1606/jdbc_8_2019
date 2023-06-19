package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.JpaRepository;
import org.apache.commons.lang3.StringUtils;

public abstract class SimpleJpaRepository<ID, T> implements JpaRepository<ID, T> {

    private Class<T> zClass;
    private Class<ID> idClass;

    public SimpleJpaRepository() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
    }

    @Override
    public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... where) {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table tableClass = zClass.getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder sql = new StringBuilder("select * from " + tableName + " AS A");
        sql.append(" WHERE  1=1 ");
        sql = makeCondition(sql, properties);
        if (where.length == 1) {
            sql.append(where[0]);
            System.out.println(where[0]);
        }

        sql.append(" LIMIT ").append(pageable.getOffSet()).append(" , ").append(pageable.getLimit()).append(";");
        System.out.println(sql.toString());

        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = EntityManagerFactory.getConnection();
        try {
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            return resultSetMapper.mapRow(rs, zClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> finAll(Map<String, Object> properties, Object... where) {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table tableClass = zClass.getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder sql = new StringBuilder("select * from " + tableName + " AS A  WHERE  1=1 ");
        sql = makeCondition(sql, properties);
        if (where != null && where.length > 0) {
            sql.append(where[0]);
        }
        System.out.println(sql.toString());

        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = EntityManagerFactory.getConnection();
        try {
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            return resultSetMapper.mapRow(rs, zClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            EntityManagerFactory.release(connection);
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        String tableName = "";

        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table tableClass = zClass.getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder sql = new StringBuilder("select * from " + tableName + " AS A  WHERE  1=1 ");
        sql.append(" LIMIT " + pageable.getOffSet() + " , " + pageable.getLimit() + ";");
        System.out.println(sql.toString());

        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = EntityManagerFactory.getConnection();
        try {
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            return resultSetMapper.mapRow(rs, zClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<>();
    }

    @Override
    public List<T> findAll(String sql1, Pageable pageable, Object... objects) {
        StringBuilder sql = new StringBuilder(sql1);
        if (pageable.getOffSet() != null && pageable.getLimit() != null) {
            sql.append(" LIMIT " + pageable.getOffSet() + " , " + pageable.getLimit() + ";");
        }
        System.out.println(sql.toString());
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = EntityManagerFactory.getConnection();
        try {
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            return resultSetMapper.mapRow(rs, zClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<>();
    }

    @Override
    public List<T> findAll(String sql, Object... objects) {
        //	System.out.println(sql.toString());
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = EntityManagerFactory.getConnection();
        try {
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            return resultSetMapper.mapRow(rs, zClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<>();
    }


    @Override
    public List<T> findAll() {
        String tableName = "";

        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table tableClass = zClass.getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder sql = new StringBuilder("select * from " + tableName + " AS A  WHERE  1=1 ");
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = EntityManagerFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            return resultSetMapper.mapRow(rs, zClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return new ArrayList<>();
    }


    @Override
    public T findById(Long id) {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        String sql = "Select * from " + tableName + " Where id = ?";
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = EntityManagerFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            rs = statement.executeQuery();
            List<T> list = resultSetMapper.mapRow(rs, zClass);
            return list.size() > 0 ? list.get(0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long id, Object... where) {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName + " WHERE 1=1 ");
        if (id != null) {
            sql.append(" AND id=" + id);
        }
        if (where != null && where.length > 0) {
            sql.append(" " + where[0]);
        }
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = EntityManagerFactory.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql.toString());
            //statement.setObject(1,id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteSpecial(Map<String, Object> properties, Object... objects) {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        StringBuilder sql = new StringBuilder("DELETE FROM " + tableName + " WHERE 1=1 ");
        sql = makeCondition(sql, properties);

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = EntityManagerFactory.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactory.release(connection);
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ID insert(T t) {

        String sql = createSqlInsert();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = EntityManagerFactory.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Class<?> aClass = t.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                int index = i + 1;
                Field field = fields[i];
                field.setAccessible(true);
                statement.setObject(index, field.get(t));
            }
            Class<?> parentClass = aClass.getSuperclass();
            int indexParent = fields.length + 1;
            while (parentClass != null) {
                Field[] fieldsParent = parentClass.getDeclaredFields();
                for (Field field : fieldsParent) {
                    field.setAccessible(true);
                    statement.setObject(indexParent, field.get(t));
                    indexParent++;
                }
                parentClass = parentClass.getSuperclass();
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            ID id = null;
            if (resultSet.next()) {
                id = resultSet.getObject(1, idClass);
            }

            connection.commit();
            return id;
        } catch (SQLException | IllegalAccessException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                EntityManagerFactory.release(connection);
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public T update(T t) {
        String sql = createSqlUpdate();
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = EntityManagerFactory.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            Class<?> aClass = t.getClass();
            Field[] fields = aClass.getDeclaredFields();
            int index = 1;
            Object objectId = null;
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                field.setAccessible(true);
                if (!column.name().equals("id")) {
                    statement.setObject(index, field.get(t));
                    index++;
                } else {
                    objectId = field.get(t);
                }
            }
            Class<?> parentClass = aClass.getSuperclass();
            int indexParent = fields.length + 1;
            while (parentClass != null) {
                Field[] fieldsParent = parentClass.getDeclaredFields();
                for (Field field : fieldsParent) {
                    Column column = field.getAnnotation(Column.class);
                    field.setAccessible(true);
                    if (!column.name().equals("id")) {

                        statement.setObject(indexParent, field.get(t));
                        indexParent++;
                    } else {
                        objectId = field.get(t);
                    }
                }
                parentClass = parentClass.getSuperclass();
            }
            statement.setObject(indexParent, objectId);
            statement.executeUpdate();
            connection.commit();
            return t;
        } catch (SQLException | IllegalAccessException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                EntityManagerFactory.release(connection);
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    protected String createSqlUpdate() {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        StringBuilder fields = new StringBuilder("");
        for (Field field : zClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (!column.name().equals("id")) {
                    if (fields.length() > 1) {
                        fields.append(",");
                        fields.append(column.name() + "=?");
                    } else {
                        fields.append(column.name() + "=?");
                    }
                }
            }
        }
        Class<?> parentClass = zClass.getSuperclass();
        while (parentClass != null) {
            for (Field field : parentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (!column.name().equals("id")) {
                        if (fields.length() > 1) {
                            fields.append(",");
                            fields.append(column.name() + "=?");
                        } else {
                            fields.append(column.name() + "=?");
                        }
                    }
                }
            }
            parentClass = parentClass.getSuperclass();
        }
        sql.append(fields.toString());
        sql.append(" WHERE id=?");
        return sql.toString();
    }

    protected String createSqlInsert() {
        String tableName = "";
        if (zClass.isAnnotationPresent(Table.class) && zClass.isAnnotationPresent(Entity.class)) {
            Table tableClass = zClass.getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder fields = new StringBuilder("");
        StringBuilder params = new StringBuilder("");
        for (Field field : zClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                if (fields.length() > 1) {
                    fields.append(",");
                    params.append(",");
                }
                Column column = field.getAnnotation(Column.class);
                fields.append(column.name());
                params.append("?");
            }
        }
        Class<?> parentClass = zClass.getSuperclass();
        while (parentClass != null) {
            for (Field field : parentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (fields.length() > 1) {
                        fields.append(",");
                        params.append(",");
                    }
                    Column column = field.getAnnotation(Column.class);
                    fields.append(column.name());
                    params.append("?");
                }
            }
            parentClass = parentClass.getSuperclass();
        }
        return "INSERT INTO " + tableName + " (" + fields + ") " + " VALUES(" + params + ")";
    }

    protected StringBuilder makeCondition(StringBuilder where, Map<String, Object> properties) {
        if (properties != null && properties.size() > 0) {
            properties.forEach((key, value) -> {
                if (value instanceof String && StringUtils.isNotBlank(value.toString())) {
                    where.append(" AND  ").append(key).append(" LIKE '%").append(value).append("%' ");
                } else if (value instanceof Integer) {
                    where.append(" AND  ").append(key).append(" = ").append(value).append(" ");
                } else if (value instanceof Long) {
                    where.append(" AND  ").append(key).append(" = ").append(value).append(" ");
                }
            });

        }
        return where;
    }
}
