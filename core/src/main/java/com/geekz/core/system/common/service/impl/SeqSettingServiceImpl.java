package com.geekz.core.system.common.service.impl;

import com.geekz.core.base.BaseService;
import com.geekz.core.constants.Constants;
import com.geekz.core.constants.ModelConstant;
import com.geekz.core.constants.NumConstant;
import com.geekz.core.system.common.dto.SeqSetting;
import com.geekz.core.system.common.service.SeqSettingService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 版权：(C) 版权所有 2000-2014
 * <简述> 设置流水号
 * <详细描述>
 * @author   zrz
 * @version  $Id$
 * @since
 * @see
 */
@Service
public class SeqSettingServiceImpl extends BaseService<SeqSetting> implements SeqSettingService {

    //定义一个字符串（A-Z，a-z，0-9）即62位；
    String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    @Override
    public int delete(String ids) {
        return 0;
    }

    /**
     * 生成流水号
     * @param type String 类型
     * @param prefix String 前缀
     * @return 流水号
     */
    public String doGenerate(String type, String prefix) {

        SeqSetting seqSetting = null;
        int count;

        seqSetting = this.get("type", type);

        if (seqSetting == null) {
            seqSetting = new SeqSetting();
            seqSetting.setSequence(NumConstant.ONETHOUSAND);
            seqSetting.setType(type);
            this.insertSelective(seqSetting);
            count = seqSetting.getSequence();
        } else {
            if (seqSetting.getSequence().equals(NumConstant.MAX_SEQ)) {
                seqSetting.setSequence(NumConstant.ONE);
            } else {
                seqSetting.setSequence(seqSetting.getSequence() + NumConstant.ONE);
            }
            this.updateSelective(seqSetting);
            count = seqSetting.getSequence();
        }

        String countStr = count + Constants.STRING_NULL;
        int len = countStr.length();
        if (len < NumConstant.FIVE) {
            for (int i = 0; i < NumConstant.FIVE - len; i++) {
                countStr = NumConstant.STRING_ZERO + countStr;
            }
        }

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i< NumConstant.FIVE; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }

        return prefix + new SimpleDateFormat(ModelConstant.YYR_DATE_FORMAT).format(new Date()) + sb.toString() + countStr;
    }
}
