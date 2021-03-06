/**
 * The GNU General Public License
 * Copyright (c) 2020-2020 zhangt2333@gmail.com
 **/

package cn.edu.sdu.qd.oj.submit.pojo;

import cn.edu.sdu.qd.oj.common.config.DateToTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Submission
 * @Description TODO
 * @Author zhangt2333
 * @Date 2020/3/6 16:03
 * @Version V1.0
 **/
@Data
@Table(name = "oj_submissions")
@NoArgsConstructor
@AllArgsConstructor
public class Submission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Long submissionId;

    @Column(name = "p_id")
    private Integer problemId;

    @Column(name = "u_id")
    private Integer userId;

    @Column(name = "l_id")
    private Byte languageId;

    @Column(name = "s_create_time")
    @JsonSerialize(using = DateToTimestampSerializer.class)
    private Date createTime;

    @Column(name = "s_judge_time")
    @JsonSerialize(using = DateToTimestampSerializer.class)
    private Date judgeTime;

    @Column(name = "s_ipv4")
    private String ipv4;

    @Column(name = "s_judger_id")
    private Integer judgerId;

    @Column(name = "s_judge_result")
    private String judgeResult;

    @Column(name = "s_judge_score")
    private Integer judgeScore;

    @Column(name = "s_used_time")
    private Integer usedTime;

    @Column(name = "s_used_memory")
    private Integer usedMemory;

    @Column(name = "s_judge_log")
    private String judgeLog;

    @Column(name = "s_code")
    private String code;

    public Submission(Integer problemId, Integer userId, Byte languageId, String ipv4, String code) {
        this.problemId = problemId;
        this.userId = userId;
        this.languageId = languageId;
        this.ipv4 = ipv4;
        this.code = code;
    }

    public Submission(Long submissionId, Integer judgerId, String judgeResult, Integer judgeScore, Integer usedTime, Integer usedMemory, String judgeLog) {
        this.submissionId = submissionId;
        this.judgerId = judgerId;
        this.judgeResult = judgeResult;
        this.judgeScore = judgeScore;
        this.usedTime = usedTime;
        this.usedMemory = usedMemory;
        this.judgeLog = judgeLog;
    }
}