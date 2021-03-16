package org.jeecg.modules.movie_analysis.user_movie.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.movie_analysis.user_movie.entity.UserMovie;
import org.jeecg.modules.movie_analysis.user_movie.service.IUserMovieService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 用户_电影关联表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Api(tags="用户_电影关联表")
@RestController
@RequestMapping("/com.dobetter23.user_movie/userMovie")
@Slf4j
public class UserMovieController extends JeecgController<UserMovie, IUserMovieService> {
	@Autowired
	private IUserMovieService userMovieService;
	
	/**
	 * 分页列表查询
	 *
	 * @param userMovie
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户_电影关联表-分页列表查询")
	@ApiOperation(value="用户_电影关联表-分页列表查询", notes="用户_电影关联表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(UserMovie userMovie,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<UserMovie> queryWrapper = QueryGenerator.initQueryWrapper(userMovie, req.getParameterMap());
		Page<UserMovie> page = new Page<UserMovie>(pageNo, pageSize);
		IPage<UserMovie> pageList = userMovieService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param userMovie
	 * @return
	 */
	@AutoLog(value = "用户_电影关联表-添加")
	@ApiOperation(value="用户_电影关联表-添加", notes="用户_电影关联表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody UserMovie userMovie) {
		userMovieService.save(userMovie);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param userMovie
	 * @return
	 */
	@AutoLog(value = "用户_电影关联表-编辑")
	@ApiOperation(value="用户_电影关联表-编辑", notes="用户_电影关联表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody UserMovie userMovie) {
		userMovieService.updateById(userMovie);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户_电影关联表-通过id删除")
	@ApiOperation(value="用户_电影关联表-通过id删除", notes="用户_电影关联表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		userMovieService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户_电影关联表-批量删除")
	@ApiOperation(value="用户_电影关联表-批量删除", notes="用户_电影关联表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userMovieService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户_电影关联表-通过id查询")
	@ApiOperation(value="用户_电影关联表-通过id查询", notes="用户_电影关联表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		UserMovie userMovie = userMovieService.getById(id);
		if(userMovie==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(userMovie);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param userMovie
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, UserMovie userMovie) {
        return super.exportXls(request, userMovie, UserMovie.class, "用户_电影关联表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, UserMovie.class);
    }

}
