package org.jeecg.modules.movie_analysis.movie.service.impl;

import org.jeecg.modules.movie_analysis.movie.entity.Movie;
import org.jeecg.modules.movie_analysis.movie.mapper.MovieMapper;
import org.jeecg.modules.movie_analysis.movie.service.IMovieService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 电影表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

}
