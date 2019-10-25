package com.kando.service;

import com.kando.dto.QueryResult;
import com.kando.entity.Authority;
import com.kando.vo.PageVo;

import java.util.List;

public interface AuthorityService {
    QueryResult selectAll(PageVo vo);

    Authority selectOne(Integer id);

    boolean insertAuth(Authority authority);

    boolean deleteAuth(Integer id);

    boolean updateAuth(Authority authority);
}
