package com.github.shniu.ddd.leave.domain.person.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author niushaohan
 * @date 2020/5/31 19
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String personId;
    private String personName;
    private int roleLevel;
}
