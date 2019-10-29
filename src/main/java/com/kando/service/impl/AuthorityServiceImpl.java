package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.dto.QueryResult;
import com.kando.entity.Authority;
import com.kando.dao.AuthorityDao;
import com.kando.service.AuthorityService;
import com.kando.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityDao dao;

    @Override
    public QueryResult selectAll(PageVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit());
        QueryResult<Authority> listQueryResult = new QueryResult<>();
        if(vo.getKey()==null||vo.getKey().equals("")){
            List<Authority> list = dao.selectAll();
            PageInfo<Authority> pageInfo = new PageInfo<>(list);
            listQueryResult.setPageNo(vo.getPage());
            listQueryResult.setRows(pageInfo.getList());
            listQueryResult.setTotalRows(pageInfo.getTotal());
            listQueryResult.setPageSize(vo.getLimit());

        }else {
            String str = "%"+vo.getKey()+"%";
            List<Authority> list = dao.selectAllLike(str);
            PageInfo<Authority> pageInfo = new PageInfo<>(list);
            listQueryResult.setPageNo(vo.getPage());
            listQueryResult.setRows(pageInfo.getList());
            listQueryResult.setTotalRows(pageInfo.getTotal());
            listQueryResult.setPageSize(vo.getLimit());
        }
        return listQueryResult;
    }


    @Override
    public Authority selectOne(Integer id) {
        return dao.selectOne(id);
    }

    @Override
    @Transactional
    public boolean insertAuth(Authority authority) {
        return dao.insertAuth(authority);
    }

    @Override
    public boolean deleteAuth(Integer id) {
        return dao.deleteAuth(id);
    }

    @Override
    public boolean updateAuth(Authority authority) {
        return dao.updateAuth(authority);
    }

    @Override
    public List<Authority> all() {
        return dao.selectAll();
    }
}
