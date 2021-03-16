package org.jeecg.modules.movie_analysis.user_review.service.impl;

import org.jeecg.modules.movie_analysis.user_review.entity.UserReview;
import org.jeecg.modules.movie_analysis.user_review.mapper.UserReviewMapper;
import org.jeecg.modules.movie_analysis.user_review.service.IUserReviewService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户_评论交互表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Service
public class UserReviewServiceImpl extends ServiceImpl<UserReviewMapper, UserReview> implements IUserReviewService {

}
