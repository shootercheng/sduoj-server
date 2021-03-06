/**
 * The GNU General Public License
 * Copyright (c) 2020-2020 zhangt2333@gmail.com
 **/

package cn.edu.sdu.qd.oj.problem.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName ProblemJudgerBo
 * @Description TODO
 * @Author zhangt2333
 * @Date 2020/3/15 23:10
 * @Version V1.0
 **/

@Data
@Table(name = "oj_problems")
public class ProblemJudgerBo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "p_id")
    private Integer problemId;

    @Column(name = "p_is_public")
    private Byte isPublic;

    @Column(name = "p_time_limit")
    private Integer timeLimit;

    @Column(name = "p_memory_limit")
    private Integer memoryLimit;
}
