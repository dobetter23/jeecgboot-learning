package org.jeecg.modules.movie_analysis.movie.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 电影表
 * @Author: jeecg-boot
 * @Date:   2021-03-15
 * @Version: V1.0
 */
@Data
@TableName("movie")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="movie对象", description="电影表")
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**电影名称*/
	@Excel(name = "电影名称", width = 15)
    @ApiModelProperty(value = "电影名称")
    private String movieName;
	/**导演*/
	@Excel(name = "导演", width = 15)
    @ApiModelProperty(value = "导演")
    private String movieDirector;
	/**编剧*/
	@Excel(name = "编剧", width = 15)
    @ApiModelProperty(value = "编剧")
    private String movieScreenwriter;
	/**主演*/
	@Excel(name = "主演", width = 15)
    @ApiModelProperty(value = "主演")
    private String movieActors;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private String movieType;
	/**语言*/
	@Excel(name = "语言", width = 15)
    @ApiModelProperty(value = "语言")
    private String movieLanguage;
	/**地区、国家*/
	@Excel(name = "地区、国家", width = 15)
    @ApiModelProperty(value = "地区、国家")
    private String movieRegion;
	/**上映时间*/
	@Excel(name = "上映时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上映时间")
    private Date movieDebut;
	/**时长*/
	@Excel(name = "时长", width = 15)
    @ApiModelProperty(value = "时长")
    private String movieTime;
	/** 又名*/
	@Excel(name = " 又名", width = 15)
    @ApiModelProperty(value = " 又名")
    private String movieAlias;
	/**评分*/
	@Excel(name = "评分", width = 15)
    @ApiModelProperty(value = "评分")
    private Double movieGrade;
	/**封面/宣传图*/
	@Excel(name = "封面/宣传图", width = 15)
    @ApiModelProperty(value = "封面/宣传图")
    private String movieCoverage;
}
