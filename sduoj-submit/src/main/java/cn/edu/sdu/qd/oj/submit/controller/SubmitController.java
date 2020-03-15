/**
 * The GNU General Public License
 * Copyright (c) 2020-2020 zhangt2333@gmail.com
 **/

package cn.edu.sdu.qd.oj.submit.controller;

import cn.edu.sdu.qd.oj.auth.entity.UserInfo;
import cn.edu.sdu.qd.oj.auth.utils.JwtUtils;
import cn.edu.sdu.qd.oj.common.entity.ApiResponseBody;
import cn.edu.sdu.qd.oj.common.enums.ApiExceptionEnum;
import cn.edu.sdu.qd.oj.common.exception.ApiException;
import cn.edu.sdu.qd.oj.common.utils.CookieUtils;
import cn.edu.sdu.qd.oj.submit.config.JwtProperties;
import cn.edu.sdu.qd.oj.submit.pojo.Submission;
import cn.edu.sdu.qd.oj.submit.pojo.SubmissionJudgeBo;
import cn.edu.sdu.qd.oj.submit.service.SubmitService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SubmitController
 * @Description TODO
 * @Author zhangt2333
 * @Date 2020/3/6 16:03
 * @Version V1.0
 **/

@Controller
@CrossOrigin
@EnableConfigurationProperties(JwtProperties.class)
public class SubmitController {

    @Autowired
    private SubmitService submitService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/create")
    @ApiResponseBody
    public Long submissionCreate(@RequestBody Map json,
                                                    HttpServletRequest request) {
        int problemId = (int) json.get("problemId");
        int languageId = (int) json.get("languageId");
        String code = (String) json.get("code");
        String ipv4 = request.getHeader("X-FORWARDED-FOR");
        if (ipv4 == null) {
            ipv4 = request.getRemoteAddr();
        }
        String token = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());
        UserInfo userInfo;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
        }catch (Exception e) {
            throw new ApiException(ApiExceptionEnum.UNKNOWN_ERROR);
        }
        Submission submission = new Submission(problemId, userInfo.getId(), languageId, new Date(), ipv4, code);
        if(this.submitService.createSubmission(submission)) {
            return submission.getId();
        }
        throw new ApiException(ApiExceptionEnum.UNKNOWN_ERROR);
    }

    @PostMapping("/queryByJudger")
    @ApiResponseBody
    public SubmissionJudgeBo queryByJudger(@RequestBody Map json) {
        int id = (int) json.get("id");
        return this.submitService.queryByJudger(id);
    }
}