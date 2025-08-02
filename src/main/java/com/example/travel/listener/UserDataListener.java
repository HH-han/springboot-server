package com.example.travel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.example.travel.entity.User;
import com.example.travel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class UserDataListener implements ReadListener<User> {
    
    private static final int BATCH_COUNT = 100;
    private List<User> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final UserService userService;
    
    public UserDataListener(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        cachedDataList.add(user);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }
    
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        userService.saveBatch(cachedDataList);
        log.info("存储数据库成功！");
    }
}