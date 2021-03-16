package org.jeecg.modules.movie_analysis.review.service.impl;

import org.jeecg.modules.movie_analysis.review.entity.Review;
import org.jeecg.modules.movie_analysis.review.mapper.ReviewMapper;
import org.jeecg.modules.movie_analysis.review.service.IReviewService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 评分_评论表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements IReviewService {

}
