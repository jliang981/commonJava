package com.step.jliang.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jliang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaiDog implements Serializable {

    private String name;
    private Integer age;
}
