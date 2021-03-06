/**
 * The GNU General Public License
 * Copyright (c) 2020-2020 zhangt2333@gmail.com
 **/

package cn.edu.sdu.qd.oj.problem.controller;

import cn.edu.sdu.qd.oj.common.entity.ApiResponseBody;
import cn.edu.sdu.qd.oj.problem.pojo.ProblemManageBo;
import cn.edu.sdu.qd.oj.problem.service.ProblemManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @ClassName ProblemManageController
 * @Description TODO
 * @Author zhangt2333
 * @Date 2020/4/3 21:30
 * @Version V1.0
 **/

@Controller
@RequestMapping("/problem/manage")
public class ProblemManageController {

    @Autowired
    private ProblemManageService problemManageService;


    @PostMapping("/query")
    @ApiResponseBody
    public ProblemManageBo queryManageBoById(@RequestBody Map json) {
        return this.problemManageService.queryById((Integer) json.get("problemId"));
    }

}