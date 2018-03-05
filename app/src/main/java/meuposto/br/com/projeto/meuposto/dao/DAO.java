package meuposto.br.com.projeto.meuposto.dao;

import java.util.List;

import meuposto.br.com.projeto.meuposto.Control.DAOException;

/**
 * Created by leandro on 31/01/18.
 */

public abstract class DAO<T> {

    public abstract void insert(T obj) throws DAOException;
    public abstract void update(T obj)throws DAOException;
    public abstract void delete(Integer id)throws DAOException;
    public abstract T selectById(Integer id)throws DAOException;
    public abstract List<T> selectByName(String name)throws DAOException;
    public abstract List<T> selectAll()throws DAOException;
}
