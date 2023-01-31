package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, ID> {

        public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

        public boolean Save(T dto) throws SQLException, ClassNotFoundException;

        public boolean update(ID id) throws SQLException, ClassNotFoundException;

        public boolean delete(ID id) throws SQLException, ClassNotFoundException;

        public T search(ID id) throws SQLException, ClassNotFoundException;

        public boolean exist(ID id) throws SQLException, ClassNotFoundException;

        public String generateNewId() throws SQLException, ClassNotFoundException;

    }
