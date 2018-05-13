package com.scrats.rent.conf;

import cn.jiguang.common.utils.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConvertConfig implements Converter<String, Date> {

    @Nullable
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (Exception e) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = sdf.parse(source);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return date;
    }
}
