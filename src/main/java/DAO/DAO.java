package DAO;

import java.io.Serializable;
import java.util.List;

public interface DAO  <T , ID extends Serializable>{

    void create(T t);

    T read(ID id);

    List<T> readAll();

    void updateById(T t);

    void delete (ID id);

    void deleteAll();



}
