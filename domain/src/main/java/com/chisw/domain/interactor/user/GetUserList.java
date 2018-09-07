package com.chisw.domain.interactor.user;

import com.chisw.data.db.repository.user.UserRepository;
import com.chisw.domain.executor.PostExecutionThread;
import com.chisw.domain.executor.ThreadExecutor;
import com.chisw.domain.interactor.UseCaseConcurrent;
import com.chisw.data.db.model.user.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUserList extends UseCaseConcurrent<List<User>, Void> {

    private final UserRepository userRepository;

    @Inject
    public GetUserList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<List<User>> buildUseCaseObservable(Void params) {
        return this.userRepository.users();
    }
}
