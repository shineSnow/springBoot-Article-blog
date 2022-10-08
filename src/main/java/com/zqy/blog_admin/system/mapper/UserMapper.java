package com.zqy.blog_admin.system.mapper;

import com.zqy.blog_admin.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zqy
 * @since 2022-09-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
