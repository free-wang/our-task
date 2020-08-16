package com.water76016.ourtask.service.impl;

import com.water76016.ourtask.entity.Label;
import com.water76016.ourtask.mapper.LabelMapper;
import com.water76016.ourtask.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author github:water76016
 * @since 2020-08-15
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

}
