package org.jeecg.modules.movie_analysis.review.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.movie_analysis.review.entity.Review;
import org.jeecg.modules.movie_analysis.review.service.IReviewService;

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
 * @Description: 评分_评论表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Api(tags="评分_评论表")
@RestController
@RequestMapping("/com.dobetter23.review/review")
@Slf4j
public class ReviewController extends JeecgController<Review, IReviewService> {
	@Autowired
	private IReviewService reviewService;
	
	/**
	 * 分页列表查询
	 *
	 * @param review
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评分_评论表-分页列表查询")
	@ApiOperation(value="评分_评论表-分页列表查询", notes="评分_评论表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Review review,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Review> queryWrapper = QueryGenerator.initQueryWrapper(review, req.getParameterMap());
		Page<Review> page = new Page<Review>(pageNo, pageSize);
		IPage<Review> pageList = reviewService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param review
	 * @return
	 */
	@AutoLog(value = "评分_评论表-添加")
	@ApiOperation(value="评分_评论表-添加", notes="评分_评论表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Review review) {
		reviewService.save(review);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param review
	 * @return
	 */
	@AutoLog(value = "评分_评论表-编辑")
	@ApiOperation(value="评分_评论表-编辑", notes="评分_评论表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Review review) {
		reviewService.updateById(review);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评分_评论表-通过id删除")
	@ApiOperation(value="评分_评论表-通过id删除", notes="评分_评论表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		reviewService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评分_评论表-批量删除")
	@ApiOperation(value="评分_评论表-批量删除", notes="评分_评论表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.reviewService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评分_评论表-通过id查询")
	@ApiOperation(value="评分_评论表-通过id查询", notes="评分_评论表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Review review = reviewService.getById(id);
		if(review==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(review);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param review
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Review review) {
        return super.exportXls(request, review, Review.class, "评分_评论表");
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
        return super.importExcel(request, response, Review.class);
    }

}
