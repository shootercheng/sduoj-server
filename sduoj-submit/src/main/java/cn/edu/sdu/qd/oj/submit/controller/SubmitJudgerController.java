/**
 * The GNU General Public License
 * Copyright (c) 2020-2020 zhangt2333@gmail.com
 **/

package cn.edu.sdu.qd.oj.submit.controller;

import cn.edu.sdu.qd.oj.common.entity.ApiResponseBody;
import cn.edu.sdu.qd.oj.submit.config.JwtProperties;
import cn.edu.sdu.qd.oj.submit.pojo.Submission;
import cn.edu.sdu.qd.oj.submit.pojo.SubmissionJudgeBo;
import cn.edu.sdu.qd.oj.submit.service.SubmitJudgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @ClassName SubmitJudgerController
 * @Description TODO
 * @Author zhangt2333
 * @Date 2020/4/3 21:48
 * @Version V1.0
 **/

@Controller
@RequestMapping("/submit/judger")
public class SubmitJudgerController {

    @Autowired
    private SubmitJudgerService submitJudgerService;


    @PostMapping("/query")
    @ApiResponseBody
    public SubmissionJudgeBo query(@RequestBody Map json) {
        int submissionId = (int) json.get("submissionId");
        return this.submitJudgerService.query(submissionId);
    }

    @PostMapping("/update")
    @ApiResponseBody
    public Void update(@RequestBody Map json) {
        int submissionId = (int) json.get("submissionId");
        int judgerId = (int) json.get("judgerId");
        String judgeResult = (String) json.get("judgeResult");
        int judgeScore = (int) json.get("judgeScore");
        int usedTime = (int) json.get("usedTime");
        int usedMemory = (int) json.get("usedMemory");
        String judgeLog = (String) json.get("judgeLog");
        Submission submission = new Submission(Long.valueOf(submissionId), judgerId, judgeResult, judgeScore, usedTime, usedMemory, judgeLog);
        this.submitJudgerService.updateSubmission(submission);
        return null;
    }
}