package org.jeecg.modules.movie_analysis.user_movie.service.impl;

import org.jeecg.modules.movie_analysis.user_movie.entity.UserMovie;
import org.jeecg.modules.movie_analysis.user_movie.mapper.UserMovieMapper;
import org.jeecg.modules.movie_analysis.user_movie.service.IUserMovieService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户_电影关联表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Service
public class UserMovieServiceImpl extends ServiceImpl<UserMovieMapper, UserMovie> implements IUserMovieService {

}
