package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.answerEntity.AnswerBean;

public interface INlpService {
    AnswerBean getAnswer(String sourceTerminal, String content, String iss_uid, String type,String url);
}
