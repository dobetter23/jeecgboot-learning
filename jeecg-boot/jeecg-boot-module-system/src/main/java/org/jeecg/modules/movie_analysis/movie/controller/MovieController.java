package org.jeecg.modules.movie_analysis.movie.controller;

import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.movie_analysis.movie.entity.Movie;
import org.jeecg.modules.movie_analysis.movie.service.IMovieService;

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
 * @Description: 电影表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Api(tags="电影表")
@RestController
@RequestMapping("/com.dobetter23.movie/movie")
@Slf4j
public class MovieController extends JeecgController<Movie, IMovieService> {
	@Resource
	private IMovieService movieService;
	
	/**
	 * 分页列表查询
	 *
	 * @param movie
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "电影表-分页列表查询")
	@ApiOperation(value="电影表-分页列表查询", notes="电影表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Movie movie,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Movie> queryWrapper = QueryGenerator.initQueryWrapper(movie, req.getParameterMap());
		Page<Movie> page = new Page<Movie>(pageNo, pageSize);
		IPage<Movie> pageList = movieService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param movie
	 * @return
	 */
	@AutoLog(value = "电影表-添加")
	@ApiOperation(value="电影表-添加", notes="电影表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Movie movie) {
		movieService.save(movie);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param movie
	 * @return
	 */
	@AutoLog(value = "电影表-编辑")
	@ApiOperation(value="电影表-编辑", notes="电影表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Movie movie) {
		movieService.updateById(movie);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "电影表-通过id删除")
	@ApiOperation(value="电影表-通过id删除", notes="电影表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		movieService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "电影表-批量删除")
	@ApiOperation(value="电影表-批量删除", notes="电影表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.movieService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "电影表-通过id查询")
	@ApiOperation(value="电影表-通过id查询", notes="电影表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Movie movie = movieService.getById(id);
		if(movie==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(movie);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param movie
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Movie movie) {
        return super.exportXls(request, movie, Movie.class, "电影表");
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
        return super.importExcel(request, response, Movie.class);
    }

}
