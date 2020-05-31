package com.github.shniu.ddd.leave.domain.leave.entity.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

/**
 * @author niushaohan
 * @date 2020/5/31 18
 */
@Getter
@ToString
@AllArgsConstructor
@Value(staticConstructor = "of")
public class Applicant {
    private String personId;
    private String personName;
    private String personType;
}
