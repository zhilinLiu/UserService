package com.kando.dao;

import com.kando.entity.Authority;

import java.util.List;

public interface AuthorityDao {
    //查询
    List<Authority> selectAll();

    List<Authority> selectAllLike(String field);

    Authority selectOne(Integer id);

    boolean insertAuth(Authority authority);

    boolean deleteAuth(Integer id);

    boolean updateAuth(Authority authority);

}
