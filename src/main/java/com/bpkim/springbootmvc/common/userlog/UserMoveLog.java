package com.bpkim.springbootmvc.common.userlog;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMoveLog {

    @Id
    @GeneratedValue
    private Integer seq;

    private Integer userId;
    private String userNm;
    private String prePage;
    private String nowPage;

}
