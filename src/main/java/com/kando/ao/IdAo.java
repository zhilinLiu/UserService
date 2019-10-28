package com.kando.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IdAo implements Serializable {
    private List<String> ids;
}
