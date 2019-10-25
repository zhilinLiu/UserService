package com.kando.ao;


import com.kando.entity.busEntity.PrParameterEntity;
import com.kando.entity.busEntity.PrProductionEntity;
import lombok.Data;

import java.util.List;

@Data
public class PrProdVo extends PrProductionEntity {
    private List<PrParameterEntity> list;
}
