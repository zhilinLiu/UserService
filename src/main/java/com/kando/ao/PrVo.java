package com.kando.ao;


import com.kando.entity.busEntity.PrParameterEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PrVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PrParameterEntity> list;
}
