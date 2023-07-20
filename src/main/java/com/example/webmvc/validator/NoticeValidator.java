package com.example.webmvc.validator;

import com.example.webmvc.entity.Notice;

import java.util.Map;

public interface NoticeValidator {
    Map<String, String> isValidNotice(Notice notice);

}
